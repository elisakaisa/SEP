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

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FragmentRecruitmentRequestsList() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentRecruitmentRequestsList.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentRecruitmentRequestsList newInstance(String param1, String param2) {
        FragmentRecruitmentRequestsList fragment = new FragmentRecruitmentRequestsList();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_recruitment_requests_list, container, false);

        /*------------ UI ------------*/
        rvRequests = view.findViewById(R.id.rv_requests);
        fabAdd = view.findViewById(R.id.fab_add_request);

        // TODO: set who can add requests, see how this can be done
        fabAdd.setVisibility(View.VISIBLE);

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
        requestVM.setRequest(requestItem.getiRequest());
        requestVM.setIdentifier(requestItem.getIdx());

        HelperFunctions.loadFragment(requireActivity().getSupportFragmentManager(), new FragmentRecruitmentRequestDetails());
    };
}