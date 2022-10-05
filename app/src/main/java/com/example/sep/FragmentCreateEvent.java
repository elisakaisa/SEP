package com.example.sep;

import android.app.DatePickerDialog;
import android.content.Context;
import android.icu.util.Calendar;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import com.example.sep.model.Event;
import com.example.sep.utils.HelperFunctions;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentCreateEvent#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentCreateEvent extends Fragment {

    /*-------- HOOKS -----------*/
    TextInputEditText etRecordNumber, etClientName, etEventType, etAttendees,
                        etBudget, etDateFrom, etDateTo, etComments;
    MaterialButton btnSubmit, btnCancel;
    CheckBox cbDecorations, cbFood, cbParties, cbDrinks, cbPhoto;

    /*------- VARIABLES --------*/
    boolean decorations, food, parties, drinks, photo = false;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FragmentCreateEvent() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentCreateEvent.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentCreateEvent newInstance(String param1, String param2) {
        FragmentCreateEvent fragment = new FragmentCreateEvent();
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
        View view = inflater.inflate(R.layout.fragment_create_event, container, false);

        /*-------- HOOKS -----------*/
       etRecordNumber = view.findViewById(R.id.et_record_number);
       etClientName = view.findViewById(R.id.et_client_name);
       etEventType = view.findViewById(R.id.et_event_type);
       etAttendees = view.findViewById(R.id.et_attendees);
       etBudget = view.findViewById(R.id.et_expected_budget);
       etDateTo = view.findViewById(R.id.et_to);
       etDateFrom = view.findViewById(R.id.et_from);
       etComments = view.findViewById(R.id.et_comments);
       cbDecorations = view.findViewById(R.id.cb_decorations);
       cbFood = view.findViewById(R.id.cb_food);
       cbParties = view.findViewById(R.id.cb_parties);
       cbDrinks = view.findViewById(R.id.cb_drinks);
       cbPhoto = view.findViewById(R.id.cb_photo);
       btnCancel = view.findViewById(R.id.btn_create_event_cancel);
       btnSubmit = view.findViewById(R.id.btn_create_event_submit);

       /*------ LISTENERS --------*/
        etDateFrom.setOnClickListener(v -> datePickerDialog(etDateFrom));
        etDateTo.setOnClickListener(v -> datePickerDialog(etDateTo));
        cbDecorations.setOnClickListener(v -> {
            decorations = onCheckboxClicked(decorations);
        });
        cbFood.setOnClickListener(v -> {
            food = onCheckboxClicked(food);
        });
        cbParties.setOnClickListener(v -> {
            parties = onCheckboxClicked(parties);
        });
        cbDrinks.setOnClickListener(v -> {
            drinks = onCheckboxClicked(drinks);
        });
        cbPhoto.setOnClickListener(v -> {
            photo = onCheckboxClicked(photo);
        });
        btnSubmit.setOnClickListener(v -> submitEvent());
        btnCancel.setOnClickListener(v -> {
            // clear all inputs
            etRecordNumber.setText("");
            etClientName.setText("");
            etEventType.setText("");
            etAttendees.setText("");
            etBudget.setText("");
            etDateTo.setText("");
            etDateFrom.setText("");
            etComments.setText("");
        });

        return view;
    }

    private void submitEvent() {
        // TODO add safety feature if numbers emptySara
        Event newEvent = new Event(
                "todoId",
                String.valueOf(etRecordNumber.getText()),
                String.valueOf(etClientName.getText()),
                String.valueOf(etEventType.getText()),
                String.valueOf(etDateFrom.getText()),
                String.valueOf(etDateTo.getText()),
                String.valueOf(etComments.getText()),
                Integer.parseInt(String.valueOf(etAttendees.getText())),
                Integer.parseInt(String.valueOf(etBudget.getText())),
                decorations,
                food,
                parties,
                drinks,
                photo,
                0);
        saveResultList(newEvent);
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.content_container, new FragmentEventList(), "");
        fragmentTransaction.commit();
    }

    private void saveResultList(Event event) {
        BaseActivity.eventList.addEvent(event);
        try {
            FileOutputStream fos = getActivity().openFileOutput("eventlist.ser", Context.MODE_PRIVATE);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(BaseActivity.eventList);
            oos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private boolean onCheckboxClicked(boolean preferences) {
        return !preferences;
    }

    private void datePickerDialog(TextInputEditText textInputEditText){
        // method to have a fancy datepicker
        // calender class's instance and get current date , month and year from calender
        final Calendar calendar = Calendar.getInstance();
        int mYear = calendar.get(Calendar.YEAR);
        int mMonth = calendar.get(Calendar.MONTH); // ADD +1 to get actual month!!!
        int mDay = calendar.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(),
                (view, year, month, day) -> {
                    String sMonth = HelperFunctions.padWithZeroes(month + 1);
                    String sDay = HelperFunctions.padWithZeroes(day);
                    String date = year + "-"  + sMonth + "-" + sDay;
                    textInputEditText.setText(date);
                }, mYear, mMonth, mDay);
        datePickerDialog.getDatePicker().setFirstDayOfWeek(Calendar.MONDAY);
        datePickerDialog.show();
    }
}