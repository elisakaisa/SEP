package com.example.sep;

import android.content.Context;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.sep.databinding.FragmentFinancialRequestDetailsBinding;
import com.example.sep.model.FinancialRequest;
import com.example.sep.utils.HelperFunctions;
import com.example.sep.viewModel.financialRequestVM.FinancialRequestViewModel;
import com.google.android.material.button.MaterialButton;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;


public class FragmentFinancialRequestDetails extends Fragment {

    /*__________ SAVING/DELETING __________*/
    private int itemIdentifier;
    private FinancialRequest mRequest;


    public FragmentFinancialRequestDetails() {
        // Required empty public constructor
    }

    public static FragmentFinancialRequestDetails newInstance() {
        return new FragmentFinancialRequestDetails();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        FragmentFinancialRequestDetailsBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_financial_request_details, container, false);
        View view = binding.getRoot();

        /* ------- HOOKS --------*/
        MaterialButton btnDelete = view.findViewById(R.id.btn_fin_request_details_delete);
        MaterialButton btnApprove = view.findViewById(R.id.btn_fin_request_details_approve);

        FinancialRequestViewModel financialRequestVM = new ViewModelProvider(requireActivity()).get(FinancialRequestViewModel.class);

        financialRequestVM.getRequest().observe(requireActivity(), request -> {
            binding.setRequestVM(financialRequestVM);
            mRequest = request;
            itemIdentifier = financialRequestVM.getIdentifier();
        });

        btnDelete.setOnClickListener(v -> onDelete());
        btnApprove.setOnClickListener(v -> onApprove());

        return view;
    }

    private void onApprove() {
        //if (RoleTransfer.getRole().equals("Senior Customer Service Officer") || RoleTransfer.getRole().equals("Administration department manager")) {
            if (mRequest != null) {
                // TODO: approve request
                //event.addLevel();
                BaseActivity.fRequestList.updateEvent(mRequest, itemIdentifier);

                //Update list in local storage
                saveResultList();
                Toast.makeText(getActivity(), "Event is approved", Toast.LENGTH_SHORT).show();
                HelperFunctions.loadFragment(requireActivity().getSupportFragmentManager(), new FragmentFinancialRequestsList());
            }
        //}
    }

    private void onDelete() {
        BaseActivity.fRequestList.deleteFinancialRequest(itemIdentifier);
        //Update list in local storage
        saveResultList();
        Toast.makeText(getActivity(), "Request is deleted", Toast.LENGTH_SHORT).show();

        HelperFunctions.loadFragment(requireActivity().getSupportFragmentManager(), new FragmentEventList());
    }

    private void saveResultList() {
        try {
            FileOutputStream fos = requireActivity().openFileOutput(BaseActivity.FIN_REQUEST_FILE, Context.MODE_PRIVATE);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(BaseActivity.fRequestList);
            oos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}