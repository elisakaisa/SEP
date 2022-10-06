package com.example.sep;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.sep.model.Event;
import com.example.sep.viewModel.EventViewModel;
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
    Integer itemIdentifier;

    Event event;
    TextInputEditText etFMReview;
    TextInputLayout tiFMReview;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FragmentEventDetails() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentEventDetails.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentEventDetails newInstance(String param1, String param2) {
        FragmentEventDetails fragment = new FragmentEventDetails();
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
        View view = inflater.inflate(R.layout.fragment_event_details, container, false);

        /* ------- HOOKS --------*/
        MaterialTextView tvRecordNumber = view.findViewById(R.id.tv_record_number_view2);
        MaterialTextView tvClientName = view.findViewById(R.id.tv_client_name_view2);
        MaterialTextView tvEventType = view.findViewById(R.id.tv_event_type_view2);
        MaterialTextView tvFrem = view.findViewById(R.id.tv_from_view2);
        MaterialTextView tvTo = view.findViewById(R.id.tv_to_view2);
        MaterialTextView tvAttendees = view.findViewById(R.id.tv_attendees_view2);
        MaterialTextView tvBudget = view.findViewById(R.id.tv_budget_view2);
        MaterialTextView tvApprovedBy = view.findViewById(R.id.tv_approved_by_view2);
        MaterialTextView tvFMReview = view.findViewById(R.id.tv_fm_review_view2);

        LinearLayout llFinancialManager = view.findViewById(R.id.ll_fm);
        etFMReview = view.findViewById(R.id.et_fm_review);
        tiFMReview = view.findViewById(R.id.ti_fm_review);

        MaterialButton btnDelete = view.findViewById(R.id.btn_event_details_delete);
        MaterialButton btnApprove = view.findViewById(R.id.btn_event_details_approve);
        MaterialButton btnReview = view.findViewById(R.id.btn_add_comment);

        switch (RoleTransfer.getRole()) {
            case "Customer Service":
                buttonVisibilitySetter(btnDelete, btnApprove, btnReview, View.INVISIBLE, View.INVISIBLE, View.INVISIBLE);
                llFinancialManager.setVisibility(View.INVISIBLE);
                break;
            case "Senior Customer Service Officer":
            case "Administration department manager":
                buttonVisibilitySetter(btnDelete, btnApprove, btnReview, View.VISIBLE, View.VISIBLE, View.INVISIBLE);
                llFinancialManager.setVisibility(View.INVISIBLE);
                break;
            case "Financial manager":
                buttonVisibilitySetter(btnDelete, btnApprove, btnReview, View.INVISIBLE, View.INVISIBLE, View.VISIBLE);
                llFinancialManager.setVisibility(View.VISIBLE);
                break;
            default:
                buttonVisibilitySetter(btnDelete, btnApprove, btnReview, View.INVISIBLE, View.INVISIBLE, View.INVISIBLE);
                break;
        }

        /* ------- VM --------*/
        EventViewModel eventVM = new ViewModelProvider(requireActivity()).get(EventViewModel.class);

        /* ------- LISTENERS --------*/
        eventVM.getEvent().observe(getActivity(), eventItem -> {
            event = eventItem.getEvent();
            if (event.getLevel() == 1 || event.getLevel() == 3) btnApprove.setEnabled(false);
            itemIdentifier = eventItem.getIdx();

            /* ------ UI ---------*/
            tvRecordNumber.setText(event.getRecordNumber());
            tvClientName.setText(event.getClientName());
            tvEventType.setText(event.getEventType());
            tvFrem.setText(event.getFromDate());
            tvTo.setText(event.getToDate());
            tvAttendees.setText(String.valueOf(event.getAttendees()));
            tvBudget.setText(String.valueOf(event.getBudget()));
            tvApprovedBy.setText(getApprovedBy(event.getLevel()));
            if (event.getLevel() > 1) tvFMReview.setText(event.getFMReview());
        });

        btnDelete.setOnClickListener(v -> onDelete());
        btnApprove.setOnClickListener(v -> onApprove());
        btnReview.setOnClickListener(v -> onReview());

        return view;
    }



    private void onReview() {
        if (RoleTransfer.getRole().equals("Financial manager")) {
            if (event != null) {
                if (Objects.equals(String.valueOf(etFMReview.getText()), "")) {
                    tiFMReview.setError("Required");
                    Toast.makeText(getActivity(), "Please review event", Toast.LENGTH_SHORT).show();
                } else {
                    event.setFMReview(String.valueOf(etFMReview.getText()));
                    event.addLevel();
                    BaseActivity.eventList.updateEvent(event, itemIdentifier);

                    //Update list in local storage
                    saveResultList();
                    Toast.makeText(getActivity(), "Event reviewed", Toast.LENGTH_SHORT).show();
                    loadFragment(new FragmentEventList());
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
        else s= "error";
        return s;
    }

    private void onApprove() {
        if (RoleTransfer.getRole().equals("Senior Customer Service Officer") || RoleTransfer.getRole().equals("Administration department manager")) {
            if (event != null) {
                event.addLevel();
                BaseActivity.eventList.updateEvent(event, itemIdentifier);

                //Update list in local storage
                saveResultList();
                Toast.makeText(getActivity(), "Event is approved", Toast.LENGTH_SHORT).show();
                loadFragment(new FragmentEventList());
            }
        }
    }

    private void onDelete() {
        BaseActivity.eventList.deleteEvent(itemIdentifier);
        //Update list in local storage
        saveResultList();
        Toast.makeText(getActivity(), "Event is deleted", Toast.LENGTH_SHORT).show();

        loadFragment(new FragmentEventList());
    }

    private void saveResultList() {
        try {
            FileOutputStream fos = getActivity().openFileOutput(BaseActivity.EVENT_LIST_FILE, Context.MODE_PRIVATE);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(BaseActivity.eventList);
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

    private void buttonVisibilitySetter(MaterialButton btnDelete, MaterialButton btnApprove, MaterialButton btnReview, int view1, int view2, int view3) {
        btnDelete.setVisibility(view1);
        btnApprove.setVisibility(view2);
        btnReview.setVisibility(view3);
    }
}