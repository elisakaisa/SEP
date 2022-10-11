package com.example.sep;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.sep.model.FinancialRequest;
import com.example.sep.model.RecruitmentRequest;
import com.example.sep.utils.HelperFunctions;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentRecruitmentRequestForm#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentRecruitmentRequestForm extends Fragment {

    /*-------- HOOKS -----------*/
    private TextInputEditText etYearsOfExperience, etJobTitle, etDescription;
    private TextInputLayout tiYearsOfExperience, tiJobTitle, tiDescription;
    private RadioGroup rgContractType, rgRequestingDepartment;

    private String chosenContractType = "Full time";
    private String chosenDepartment = "Administration";

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FragmentRecruitmentRequestForm() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentRecruitmentRequestForm.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentRecruitmentRequestForm newInstance(String param1, String param2) {
        FragmentRecruitmentRequestForm fragment = new FragmentRecruitmentRequestForm();
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
        View view = inflater.inflate(R.layout.fragment_recruitment_request_form, container, false);

        /*-------- HOOKS -----------*/
        etYearsOfExperience = view.findViewById(R.id.et_years_of_experience_form);
        etJobTitle = view.findViewById(R.id.et_job_title_form);
        etDescription = view.findViewById(R.id.et_description_from);
        tiYearsOfExperience = view.findViewById(R.id.ti_years_of_experience_form);
        tiJobTitle = view.findViewById(R.id.ti_job_title_form);
        tiDescription = view.findViewById(R.id.ti_description_from);
        MaterialButton btnCancel = view.findViewById(R.id.btn_recruitment_request_cancel);
        MaterialButton btnSubmit = view.findViewById(R.id.btn_recruitment_request_submit);
        rgContractType = view.findViewById(R.id.radioGroup_contract);
        rgRequestingDepartment = view.findViewById(R.id.radioGroup_dep);

        /*------ LISTENERS --------*/
        btnSubmit.setOnClickListener(v -> {
            if (!isFieldEmpty(String.valueOf(etJobTitle.getText()))) tiJobTitle.setError("Job title required");
            if (!isFieldEmpty(String.valueOf(etYearsOfExperience.getText()))) tiYearsOfExperience.setError("Years of experience required");
            if (!isFieldEmpty(String.valueOf(etDescription.getText()))) tiDescription.setError("Description required");
            else {
                tiJobTitle.setError(null);
                tiYearsOfExperience.setError(null);
                tiDescription.setError(null);
                submitRequest();
            }
        });
        btnCancel.setOnClickListener(v -> {
            etYearsOfExperience.setText("");
            etJobTitle.setText("");
            etDescription.setText("");
        });

        // TODO: make it so that the user can only choose its own department
        rgRequestingDepartment.setOnCheckedChangeListener((radioGroup, i) -> {
            RadioButton radioButton = view.findViewById(i);
            chosenDepartment = String.valueOf(radioButton.getText());
        });
        rgContractType.setOnCheckedChangeListener((radioGroup, i) -> {
            RadioButton radioButton = view.findViewById(i);
            chosenContractType = String.valueOf(radioButton.getText());
        });

        // removes error messages
        etJobTitle.setOnKeyListener((view1, i, keyEvent) -> {
            if (!isFieldEmpty(String.valueOf(etJobTitle.getText()))) tiJobTitle.setError(null);
            return false;
        });
        etYearsOfExperience.setOnKeyListener((view1, i, keyEvent) -> {
            if (!isFieldEmpty(String.valueOf(etYearsOfExperience.getText()))) tiYearsOfExperience.setError(null);
            return false;
        });
        etDescription.setOnKeyListener((view1, i, keyEvent) -> {
            if (!isFieldEmpty(String.valueOf(etDescription.getText()))) tiDescription.setError(null);
            return false;
        });

        return view;
    }

    private void submitRequest() {
        // Todo: add event id
        RecruitmentRequest newRequest = new RecruitmentRequest(
                chosenContractType,
                chosenDepartment,
                String.valueOf(etYearsOfExperience.getText()),
                String.valueOf(etJobTitle.getText()),
                String.valueOf(etDescription.getText()),
                RecruitmentRequest.PENDING,
                12
        );
        saveResultsList(newRequest);
        HelperFunctions.loadFragment(requireActivity().getSupportFragmentManager(), new FragmentRecruitmentRequestsList());
    }

    private Boolean isFieldEmpty(String text) {
        // for error messages in text fields
        return text != null && text.length() > 1;
    }

    private void saveResultsList(RecruitmentRequest request) {
        BaseActivity.recruitmentRequestList.addRecruitmentRequest(request);
        try {
            FileOutputStream fos = requireActivity().openFileOutput(BaseActivity.RES_REQUEST_FILE, Context.MODE_PRIVATE);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(BaseActivity.fRequestList);
            oos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}