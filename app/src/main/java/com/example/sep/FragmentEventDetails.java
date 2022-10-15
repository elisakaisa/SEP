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
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.sep.databinding.FragmentEventDetailsBinding;
import com.example.sep.model.Event;
import com.example.sep.utils.HelperFunctions;
import com.example.sep.viewModel.eventVM.EventViewModel;
import com.example.sep.viewModel.RoleTransfer;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.android.material.textview.MaterialTextView;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentEventDetails#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentEventDetails extends Fragment {

    /*__________ SAVING/DELETING __________*/
    private int itemIdentifier;

    private Event mEvent;
    private TextInputEditText etFMReview;
    private TextInputLayout tiFMReview;

    public FragmentEventDetails() {
        // Required empty public constructor
    }

    public static FragmentEventDetails newInstance() {
        return new FragmentEventDetails();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        FragmentEventDetailsBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_event_details, container, false);
        View view = binding.getRoot();

        /* ------- HOOKS --------*/
        MaterialTextView tvApprovedBy = view.findViewById(R.id.tv_approved_by_view2);
        MaterialTextView tvFMReview = view.findViewById(R.id.tv_fm_review_view2);
        LinearLayout llFinancialManager = view.findViewById(R.id.ll_fm);
        etFMReview = view.findViewById(R.id.et_fm_review);
        tiFMReview = view.findViewById(R.id.ti_fm_review);
        MaterialButton btnDelete = view.findViewById(R.id.btn_event_details_delete);
        MaterialButton btnApprove = view.findViewById(R.id.btn_event_details_approve);
        MaterialButton btnReview = view.findViewById(R.id.btn_add_comment);
        MaterialButton btnCheckTaskList = view.findViewById(R.id.btn_check_task_list);

        switch (RoleTransfer.getRole()) {
            case "Senior Customer Service Officer":
            case "Administration department manager":
                buttonVisibilitySetter(btnDelete, btnApprove, btnReview, btnCheckTaskList, View.VISIBLE, View.VISIBLE, View.INVISIBLE, View.INVISIBLE);
                llFinancialManager.setVisibility(View.INVISIBLE);
                break;

            case "Financial manager":
                buttonVisibilitySetter(btnDelete, btnApprove, btnReview, btnCheckTaskList, View.INVISIBLE, View.INVISIBLE, View.VISIBLE, View.INVISIBLE);
                llFinancialManager.setVisibility(View.VISIBLE);
                break;

            case "Production department manager":
            case "Services department manager":
                buttonVisibilitySetter(btnDelete, btnApprove, btnReview, btnCheckTaskList, View.INVISIBLE, View.INVISIBLE, View.INVISIBLE, View.VISIBLE);
                llFinancialManager.setVisibility(View.INVISIBLE);
                break;

            case "Customer Service":
            default:
                buttonVisibilitySetter(btnDelete, btnApprove, btnReview, btnCheckTaskList, View.INVISIBLE, View.INVISIBLE, View.INVISIBLE, View.INVISIBLE);
                llFinancialManager.setVisibility(View.INVISIBLE);
                break;
        }

        /* ------- VM --------*/
        EventViewModel eventVM = new ViewModelProvider(requireActivity()).get(EventViewModel.class);

        /* ------- LISTENERS --------*/
        eventVM.getEvent().observe(requireActivity(), event -> {
            if (event.getLevel() == 1 || event.getLevel() == 3) btnApprove.setEnabled(false);
            itemIdentifier = eventVM.getIdentifier();

            binding.setEventVM(eventVM);
            mEvent = event;

            /* ------ UI ---------*/
            tvApprovedBy.setText(getApprovedBy(event.getLevel()));
            if (event.getLevel() > 1) tvFMReview.setText(event.getFMReview());
        });

        btnDelete.setOnClickListener(v -> onDismiss());
        btnApprove.setOnClickListener(v -> onApprove());
        btnReview.setOnClickListener(v -> onReview());

        btnCheckTaskList.setOnClickListener(v -> {
            HelperFunctions.loadFragment(requireActivity().getSupportFragmentManager(), new FragmentTaskListManager());
        });

        return view;
    }



    private void onReview() {
        if (RoleTransfer.getRole().equals("Financial manager")) {
            if (mEvent != null) {
                if (Objects.equals(String.valueOf(etFMReview.getText()), "")) {
                    tiFMReview.setError("Required");
                    Toast.makeText(getActivity(), "Please review event", Toast.LENGTH_SHORT).show();
                } else {
                    mEvent.setFMReview(String.valueOf(etFMReview.getText()));
                    mEvent.setLevel(Event.FM_REVIEWED);
                    BaseActivity.eventList.updateEvent(mEvent, itemIdentifier);

                    //Update list in local storage
                    saveResultList();
                    Toast.makeText(getActivity(), "Event reviewed", Toast.LENGTH_SHORT).show();
                    HelperFunctions.loadFragment(requireActivity().getSupportFragmentManager(), new FragmentEventList());
                }
            }
        }
    }

    private String getApprovedBy(int level) {
        String s;
        if (level == 0) s = "not approved";
        else if (level == 1) s = "Senior CS officer";
        else if (level == 2) s = "Financial manager";
        else if (level == 3) s = "Administration manager";
        else s = "error";
        return s;
    }

    private void onApprove() {
        if (RoleTransfer.getRole().equals("Senior Customer Service Officer") || RoleTransfer.getRole().equals("Administration department manager")) {
            if (mEvent != null) {
                if (RoleTransfer.getRole().equals("Administration department manager")) {
                    // if the administration manager approves the event, change the status
                    mEvent.setStatus(Event.APPROVED);
                    mEvent.setLevel(Event.AM_APPROVED);
                } else {
                    mEvent.setLevel(Event.SCS_APPROVED);
                }
                // Update the event in the eventList
                BaseActivity.eventList.updateEvent(mEvent, itemIdentifier);

                //Update list in local storage
                saveResultList();
                Toast.makeText(getActivity(), "Event is approved", Toast.LENGTH_SHORT).show();
                HelperFunctions.loadFragment(requireActivity().getSupportFragmentManager(), new FragmentEventList());
            }
        }
    }

    private void onDismiss() {
        if (RoleTransfer.getRole().equals("Senior Customer Service Officer") || RoleTransfer.getRole().equals("Administration department manager")) {
            if (mEvent != null) {
                // set the status to dismissed
                mEvent.setStatus(Event.DISMISSED);
                // Update the event in the eventList
                BaseActivity.eventList.updateEvent(mEvent, itemIdentifier);

                //Update list in local storage
                saveResultList();
                Toast.makeText(getActivity(), "Event is dismissed", Toast.LENGTH_SHORT).show();
                HelperFunctions.loadFragment(requireActivity().getSupportFragmentManager(), new FragmentEventList());
            }
        }
    }

    private void onDelete() {
        // method to delete the event
        BaseActivity.eventList.deleteEvent(itemIdentifier);
        //Update list in local storage
        saveResultList();
        Toast.makeText(getActivity(), "Event is deleted", Toast.LENGTH_SHORT).show();

        HelperFunctions.loadFragment(requireActivity().getSupportFragmentManager(), new FragmentEventList());
    }

    private void saveResultList() {
        try {
            FileOutputStream fos = requireActivity().openFileOutput(BaseActivity.EVENT_LIST_FILE, Context.MODE_PRIVATE);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(BaseActivity.eventList);
            oos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void buttonVisibilitySetter(MaterialButton btnDelete, MaterialButton btnApprove, MaterialButton btnReview, MaterialButton btnCheckTaskList, int view1, int view2, int view3, int view4) {
        btnDelete.setVisibility(view1);
        btnApprove.setVisibility(view2);
        btnReview.setVisibility(view3);
        btnCheckTaskList.setVisibility(view4);
    }
}