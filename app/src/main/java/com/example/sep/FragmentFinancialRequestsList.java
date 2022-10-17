package com.example.sep;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.sep.model.FinancialRequest;
import com.example.sep.utils.HelperFunctions;
import com.example.sep.view.financialRequestRecyclerView.FinancialRequestItem;
import com.example.sep.view.financialRequestRecyclerView.FinancialRequestItemAdapter;
import com.example.sep.viewModel.financialRequestVM.FinancialRequestListViewModel;
import com.example.sep.viewModel.financialRequestVM.FinancialRequestViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentFinancialRequestsList#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentFinancialRequestsList extends Fragment {

    private RecyclerView rvRequests;
    private ArrayList<FinancialRequestItem> itemList;
    private FloatingActionButton fabAdd;
    private FinancialRequestViewModel requestVM;

    public FragmentFinancialRequestsList() {
        // Required empty public constructor
    }

    public static FragmentFinancialRequestsList newInstance() {
        return new FragmentFinancialRequestsList();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_financial_requests_list, container, false);

        /*------------ UI ------------*/
        rvRequests = view.findViewById(R.id.rv_requests);
        fabAdd = view.findViewById(R.id.fab_add_request);

        // TODO: delete this button, request onhly added from task details
        fabAdd.setVisibility(View.VISIBLE);

        /*------------ VM ------------*/
        FinancialRequestListViewModel requestsVM = new ViewModelProvider(requireActivity()).get(FinancialRequestListViewModel.class);
        requestVM = new ViewModelProvider(requireActivity()).get(FinancialRequestViewModel.class);

        /*---------- LISTENERS ----------*/
        requestsVM.getRequests().observe(requireActivity(), requests -> {
            itemList = new ArrayList<>();
            int i = 0;
            for (FinancialRequest singleRequest : requests) {
                itemList.add(new FinancialRequestItem(singleRequest, i));
                i++;
            }
            FinancialRequestItemAdapter requestItemAdapter = new FinancialRequestItemAdapter(itemList);
            rvRequests.setLayoutManager(new LinearLayoutManager(getActivity()));
            rvRequests.setAdapter(requestItemAdapter);
            requestItemAdapter.setOnItemClickListener(onItemClickListener);
        });

        fabAdd.setOnClickListener(v -> {
            HelperFunctions.loadFragment(requireActivity().getSupportFragmentManager(), new FragmentFinancialRequestForm());
        });

        return view;
    }

    private final View.OnClickListener onItemClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            RecyclerView.ViewHolder viewHolder = (RecyclerView.ViewHolder) view.getTag();
            int position = viewHolder.getAdapterPosition();
            FinancialRequestItem requestItem = itemList.get(position);
            requestVM.setRequest(requestItem.getRequest());
            requestVM.setIdentifier(requestItem.getIdx());

            HelperFunctions.loadFragment(requireActivity().getSupportFragmentManager(), new FragmentFinancialRequestDetails());
        }
    };

}