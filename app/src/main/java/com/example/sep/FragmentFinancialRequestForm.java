package com.example.sep;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.sep.database.FinancialRequestList;
import com.example.sep.model.FinancialRequest;
import com.example.sep.utils.HelperFunctions;
import com.example.sep.viewModel.taskVM.TaskViewModel;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

public class FragmentFinancialRequestForm extends Fragment {

    /*-------- HOOKS -----------*/
    private TextInputEditText etRecordNumber, etRequiredAmount, etReason;
    private TextInputLayout tiRecordNumber, tiRequiredAmount, tiReason;
    private RadioGroup radioGroup;

    private String chosenDepartment = "Administration";
    private String eventId = null;

    public FragmentFinancialRequestForm() {
        // Required empty public constructor
    }

    public static FragmentFinancialRequestForm newInstance() {
        return new FragmentFinancialRequestForm();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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

        /*--------- VM -----------*/
        TaskViewModel taskVM = new ViewModelProvider(requireActivity()).get(TaskViewModel.class);
        taskVM.getTask().observe(requireActivity(), taskItem -> {
            eventId = taskItem.getBelongsToEvent();
        });

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
        FinancialRequest newRequest = new FinancialRequest(
                eventId,
                String.valueOf(etRecordNumber.getText()),
                chosenDepartment,
                Integer.parseInt(String.valueOf(etRequiredAmount.getText())),
                String.valueOf(etReason.getText()),
                FinancialRequest.PENDING
        );
        saveResultsList(newRequest);
        HelperFunctions.loadFragment(requireActivity().getSupportFragmentManager(), new FragmentFinancialRequestsList());
    }

    private Boolean isFieldEmpty(String text) {
        // for error messages in text fields
        return text != null && text.length() > 1;
    }

    private void saveResultsList(FinancialRequest request) {
        if (BaseActivity.fRequestList == null) BaseActivity.fRequestList = new FinancialRequestList();
        BaseActivity.fRequestList.addFinancialRequest(request);
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