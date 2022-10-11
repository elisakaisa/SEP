package com.example.sep;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.sep.databinding.FragmentRecruitmentRequestDetailsBinding;
import com.example.sep.model.RecruitmentRequest;
import com.example.sep.viewModel.recruitmentRequestVM.RecruitmentRequestViewModel;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentRecruitmentRequestDetails#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentRecruitmentRequestDetails extends Fragment {

    /*__________ SAVING/DELETING __________*/
    private int itemIdentifier;

    private RecruitmentRequest mRequest;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FragmentRecruitmentRequestDetails() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentRecruitmentRequestDetails.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentRecruitmentRequestDetails newInstance(String param1, String param2) {
        FragmentRecruitmentRequestDetails fragment = new FragmentRecruitmentRequestDetails();
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
        FragmentRecruitmentRequestDetailsBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_recruitment_request_details, container, false);
        View view = binding.getRoot();

        /*---------- VM ----------*/
        RecruitmentRequestViewModel recruitmentRequestVM = new ViewModelProvider(requireActivity()).get(RecruitmentRequestViewModel.class);

        /*------ LISTENERS ------*/
        recruitmentRequestVM.getRequest().observe(requireActivity(), request -> {
            binding.setRequestVM(recruitmentRequestVM);
            mRequest = request;
            itemIdentifier = recruitmentRequestVM.getIdentifier();
        });
        return view;
    }
}