package com.example.sep;

import android.app.DatePickerDialog;
import android.content.Context;
import android.icu.util.Calendar;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import com.example.sep.model.Event;
import com.example.sep.utils.HelperFunctions;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

public class FragmentCreateEvent extends Fragment {

    /*-------- HOOKS -----------*/
    private TextInputEditText etRecordNumber, etClientName, etEventType, etAttendees,
                        etBudget, etDateFrom, etDateTo, etComments;

    /*------- VARIABLES --------*/
    private boolean decorations, food, parties, drinks, photo = false;

    public FragmentCreateEvent() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
        TextInputLayout tiRecordNumber = view.findViewById(R.id.ti_record_number);
        TextInputLayout tiClientName = view.findViewById(R.id.ti_client_name);
        TextInputLayout tiEventType = view.findViewById(R.id.ti_event_type);
        TextInputLayout tiAttendees = view.findViewById(R.id.ti_attendees);
        TextInputLayout tiBudget = view.findViewById(R.id.ti_expected_budget);
        TextInputLayout tiDateTo = view.findViewById(R.id.ti_to);
        TextInputLayout tiDateFrom = view.findViewById(R.id.ti_from);
        CheckBox cbDecorations = view.findViewById(R.id.cb_decorations);
        CheckBox cbFood = view.findViewById(R.id.cb_food);
        CheckBox cbParties = view.findViewById(R.id.cb_parties);
        CheckBox cbDrinks = view.findViewById(R.id.cb_drinks);
        CheckBox cbPhoto = view.findViewById(R.id.cb_photo);
        MaterialButton btnCancel = view.findViewById(R.id.btn_create_event_cancel);
        MaterialButton btnSubmit = view.findViewById(R.id.btn_create_event_submit);

        /*------ LISTENERS --------*/
        // datePicker
        etDateFrom.setOnClickListener(v -> datePickerDialog(etDateFrom));
        etDateTo.setOnClickListener(v -> datePickerDialog(etDateTo));
        // CheckBoxes
        cbDecorations.setOnClickListener(v -> decorations = onCheckboxClicked(decorations));
        cbFood.setOnClickListener(v -> food = onCheckboxClicked(food));
        cbParties.setOnClickListener(v -> parties = onCheckboxClicked(parties));
        cbDrinks.setOnClickListener(v -> drinks = onCheckboxClicked(drinks));
        cbPhoto.setOnClickListener(v -> photo = onCheckboxClicked(photo));
        //Buttons
        btnSubmit.setOnClickListener(v -> {
            if (!isFieldEmpty(String.valueOf(etRecordNumber.getText()))) tiRecordNumber.setError("Record number required");
            if (!isFieldEmpty(String.valueOf(etClientName.getText()))) tiClientName.setError("Client name required");
            if (!isFieldEmpty(String.valueOf(etEventType.getText()))) tiEventType.setError("Event type required");
            if (!isFieldEmpty(String.valueOf(etAttendees.getText()))) tiAttendees.setError("Number of attendees required");
            if (!isFieldEmpty(String.valueOf(etBudget.getText()))) tiBudget.setError("Expected budget required");
            if (!isFieldEmpty(String.valueOf(etDateFrom.getText()))) tiDateFrom.setError("Date required");
            if (!isFieldEmpty(String.valueOf(etDateTo.getText()))) tiDateTo.setError("Date required");
            else submitEvent();
        });
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

        // clear error messages
        etRecordNumber.setOnKeyListener(((view1, i, keyEvent) -> {
            if (!isFieldEmpty(String.valueOf(etRecordNumber.getText()))) tiRecordNumber.setError(null);
            return false;
        }));
        etClientName.setOnKeyListener(((view1, i, keyEvent) -> {
            if (!isFieldEmpty(String.valueOf(etClientName.getText()))) tiClientName.setError(null);
            return false;
        }));
        etEventType.setOnKeyListener(((view1, i, keyEvent) -> {
            if (!isFieldEmpty(String.valueOf(etEventType.getText()))) tiEventType.setError(null);
            return false;
        }));
        etAttendees.setOnKeyListener(((view1, i, keyEvent) -> {
            if (!isFieldEmpty(String.valueOf(etAttendees.getText()))) tiAttendees.setError(null);
            return false;
        }));
        etBudget.setOnKeyListener(((view1, i, keyEvent) -> {
            if (!isFieldEmpty(String.valueOf(etBudget.getText()))) tiBudget.setError(null);
            return false;
        }));
        etDateFrom.setOnKeyListener(((view1, i, keyEvent) -> {
            if (!isFieldEmpty(String.valueOf(etDateFrom.getText()))) tiDateFrom.setError(null);
            return false;
        }));
        etDateTo.setOnKeyListener(((view1, i, keyEvent) -> {
            if (!isFieldEmpty(String.valueOf(etDateTo.getText()))) tiDateTo.setError(null);
            return false;
        }));

        return view;
    }

    private void submitEvent() {
        // TODO: check that date to and from are coherent
        Event newEvent = new Event(
                BaseActivity.eventList.setNewEventId(),
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
                Event.CS_CREATED,
                Event.PRELIMINARY);
        saveResultList(newEvent);
        HelperFunctions.loadFragment(requireActivity().getSupportFragmentManager(), new FragmentEventList());
    }

    private void saveResultList(Event event) {
        // method to save the result to the eventList and serialize the EventList
        BaseActivity.eventList.addEvent(event);
        try {
            FileOutputStream fos = requireActivity().openFileOutput(BaseActivity.EVENT_LIST_FILE, Context.MODE_PRIVATE);
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

    private Boolean isFieldEmpty(String text) {
        // for error messages in text fields
        return text != null && text.length() > 1;
    }

    private void datePickerDialog(TextInputEditText textInputEditText){
        // method to have a fancy date picker
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