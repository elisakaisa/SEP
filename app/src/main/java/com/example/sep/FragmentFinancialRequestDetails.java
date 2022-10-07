package com.example.sep;

import android.content.Context;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.sep.databinding.FragmentFinancialRequestDetailsBinding;
import com.example.sep.model.FinancialRequest;
import com.example.sep.viewModel.FinancialRequestViewModel;
import com.example.sep.viewModel.RoleTransfer;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textview.MaterialTextView;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentFinancialRequestDetails#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentFinancialRequestDetails extends Fragment {

    /*__________ SAVING/DELETING __________*/
    private int itemIdentifier;

    private FinancialRequest mRequest;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FragmentFinancialRequestDetails() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentFinancialRequestDetails.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentFinancialRequestDetails newInstance(String param1, String param2) {
        FragmentFinancialRequestDetails fragment = new FragmentFinancialRequestDetails();
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
                loadFragment(new FragmentFinancialRequestsList());
            }
        //}
    }

    private void onDelete() {
        BaseActivity.fRequestList.deleteFinancialRequest(itemIdentifier);
        //Update list in local storage
        saveResultList();
        Toast.makeText(getActivity(), "Request is deleted", Toast.LENGTH_SHORT).show();

        loadFragment(new FragmentEventList());
    }

    private void saveResultList() {
        try {
            FileOutputStream fos = getActivity().openFileOutput(BaseActivity.FIN_REQUEST_FILE, Context.MODE_PRIVATE);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(BaseActivity.fRequestList);
            oos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.content_container, fragment, "");
        fragmentTransaction.commit();
    }
}