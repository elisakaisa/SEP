package com.example.sep;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.sep.model.RecruitmentRequest;
import com.example.sep.utils.HelperFunctions;
import com.example.sep.view.recruitmentRequestRecyclerView.RecruitmentRequestItem;
import com.example.sep.view.recruitmentRequestRecyclerView.RecruitmentRequestItemAdapter;
import com.example.sep.viewModel.RoleTransfer;
import com.example.sep.viewModel.recruitmentRequestVM.RecruitmentRequestListViewModel;
import com.example.sep.viewModel.recruitmentRequestVM.RecruitmentRequestViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentRecruitmentRequestsList#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentRecruitmentRequestsList extends Fragment {

    private RecyclerView rvRequests;
    private ArrayList<RecruitmentRequestItem> itemList;
    private FloatingActionButton fabAdd;
    private RecruitmentRequestViewModel requestVM;

    public FragmentRecruitmentRequestsList() {
        // Required empty public constructor
    }

    public static FragmentRecruitmentRequestsList newInstance(String param1, String param2) {
        return new FragmentRecruitmentRequestsList();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_recruitment_requests_list, container, false);

        /*------------ UI ------------*/
        rvRequests = view.findViewById(R.id.rv_requests);
        fabAdd = view.findViewById(R.id.fab_add_request);
        fabAdd.setVisibility(View.INVISIBLE);

        if (RoleTransfer.getRole().equals("Production department manager") || RoleTransfer.getRole().equals("Services department manager")) {
            fabAdd.setVisibility(View.VISIBLE);
        }

        /*------------ VM ------------*/
        RecruitmentRequestListViewModel requestsVM = new ViewModelProvider(requireActivity()).get(RecruitmentRequestListViewModel.class);
        requestVM = new ViewModelProvider(requireActivity()).get(RecruitmentRequestViewModel.class);

        /*---------- LISTENERS ----------*/
        requestsVM.getRequests().observe(requireActivity(), requests -> {
            itemList = new ArrayList<>();
            int i = 0;
            for (RecruitmentRequest singleRequest : requests) {
                itemList.add(new RecruitmentRequestItem(singleRequest, i));
                i++;
            }
            RecruitmentRequestItemAdapter requestItemAdapter = new RecruitmentRequestItemAdapter(itemList);
            rvRequests.setLayoutManager(new LinearLayoutManager(getActivity()));
            rvRequests.setAdapter(requestItemAdapter);
            requestItemAdapter.setOnItemClickListener(onItemClickListener);
        });

        fabAdd.setOnClickListener(v -> {
            HelperFunctions.loadFragment(requireActivity().getSupportFragmentManager(), new FragmentRecruitmentRequestForm());
        });

        return view;
    }

    private final View.OnClickListener onItemClickListener = view -> {
        RecyclerView.ViewHolder viewHolder = (RecyclerView.ViewHolder) view.getTag();
        int position = viewHolder.getAdapterPosition();
        RecruitmentRequestItem requestItem = itemList.get(position);
        requestVM.setRequest(requestItem.getRequest());
        requestVM.setIdentifier(requestItem.getIdx());

        HelperFunctions.loadFragment(requireActivity().getSupportFragmentManager(), new FragmentRecruitmentRequestDetails());
    };
}