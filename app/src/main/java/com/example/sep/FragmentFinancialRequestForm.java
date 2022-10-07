package com.example.sep;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.sep.model.Event;
import com.example.sep.model.FinancialRequest;
import com.example.sep.utils.HelperFunctions;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentFinancialRequestForm#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentFinancialRequestForm extends Fragment {

    /*-------- HOOKS -----------*/
    private TextInputEditText etRecordNumber, etRequiredAmount, etReason;
    private TextInputLayout tiRecordNumber, tiRequiredAmount, tiReason;
    private RadioGroup radioGroup;

    private String chosenDepartment = "Administration";

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FragmentFinancialRequestForm() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentFinancialRequestForm.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentFinancialRequestForm newInstance(String param1, String param2) {
        FragmentFinancialRequestForm fragment = new FragmentFinancialRequestForm();
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
        View view = inflater.inflate(R.layout.fragment_financial_request_form, container, false);

        /*-------- HOOKS -----------*/
        etRecordNumber = view.findViewById(R.id.et_financial_req_record_number);
        etRequiredAmount = view.findViewById(R.id.et_required_amount);
        etReason = view.findViewById(R.id.et_reason);
        tiRecordNumber = view.findViewById(R.id.ti_financial_req_record_number);
        tiRequiredAmount = view.findViewById(R.id.ti_required_amount);
        tiReason = view.findViewById(R.id.ti_reason);
        MaterialButton btnCancel = view.findViewById(R.id.btn_financial_request_cancel);
        MaterialButton btnSubmit = view.findViewById(R.id.btn_financial_request_submit);
        radioGroup = view.findViewById(R.id.radioGroup);

        /*------ LISTENERS --------*/
        btnSubmit.setOnClickListener(v -> {
            if (!isFieldEmpty(String.valueOf(etRequiredAmount.getText()))) tiRequiredAmount.setError("Amount required");
            if (!isFieldEmpty(String.valueOf(etReason.getText()))) tiReason.setError("Reason required");
            else {
                tiRequiredAmount.setError(null);
                tiReason.setError(null);
                submitRequest();
            }
        });
        btnCancel.setOnClickListener(v -> {
            etRecordNumber.setText("");
            etRequiredAmount.setText("");
            etReason.setText("");
        });

        // TODO: make it so that the user can only choose its own department
        radioGroup.setOnCheckedChangeListener((radioGroup, i) -> {
            RadioButton radioButton = view.findViewById(i);
            chosenDepartment = String.valueOf(radioButton.getText());
        });

        // removes error messages
        etRequiredAmount.setOnKeyListener((view1, i, keyEvent) -> {
            if (!isFieldEmpty(String.valueOf(etRequiredAmount.getText())))
                tiRequiredAmount.setError(null);
            return false;
        });
        etReason.setOnKeyListener((view1, i, keyEvent) -> {
            if (!isFieldEmpty(String.valueOf(etReason.getText()))) tiReason.setError(null);
            return false;
        });

        return view;
    }

    private void submitRequest() {
        // Todo: take record number from event

        FinancialRequest newRequest = new FinancialRequest(
                String.valueOf(etRecordNumber.getText()),
                chosenDepartment,
                String.valueOf(etRequiredAmount.getText()),
                String.valueOf(etReason.getText())
        );
        saveResultsList(newRequest);
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.content_container, new FragmentFinancialRequestsList(), "");
        fragmentTransaction.commit();
    }

    private Boolean isFieldEmpty(String text) {
        // for error messages in text fields
        return text != null && text.length() > 1;
    }

    private void saveResultsList(FinancialRequest request) {
        BaseActivity.fRequestList.addFinancialRequest(request);
        try {
            FileOutputStream fos = getActivity().openFileOutput(BaseActivity.FIN_REQUEST_FILE, Context.MODE_PRIVATE);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(BaseActivity.fRequestList);
            oos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}