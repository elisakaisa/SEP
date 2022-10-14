package com.example.sep;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TableRow;

import com.example.sep.model.Event;
import com.example.sep.model.Task;
import com.example.sep.utils.HelperFunctions;
import com.example.sep.viewModel.eventVM.EventViewModel;
import com.example.sep.viewModel.RoleTransfer;
import com.example.sep.viewModel.taskVM.TaskItemViewModel;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textview.MaterialTextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentTaskDetails#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentTaskDetails extends Fragment {
    Task task;
    Event event;
    Boolean extraBudget;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FragmentTaskDetails() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentTasksPerPerson.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentTaskDetails newInstance(String param1, String param2) {
        FragmentTaskDetails fragment = new FragmentTaskDetails();
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
        View view = inflater.inflate(R.layout.fragment_task_details, container, false);

        MaterialTextView tvEventId = view.findViewById(R.id.tv_event_id_view);
        MaterialTextView tvEventType = view.findViewById(R.id.tv_event_type_view);
        MaterialTextView tvEventFrom = view.findViewById(R.id.tv_from_view);
        MaterialTextView tvEventTo = view.findViewById(R.id.tv_to_view);
        MaterialTextView tvEventAttendees = view.findViewById(R.id.tv_attendees_view);
        MaterialTextView tvTaskBudget = view.findViewById(R.id.tv_budget_view);
        MaterialTextView tvTaskDescription = view.findViewById(R.id.tv_approved_by_view);

        TableRow trPlanning = view.findViewById(R.id.row_planning);
        TableRow trBudget = view.findViewById(R.id.row_budget);
        TableRow trBudget2 = view.findViewById(R.id.row_budget_2);

        TextInputEditText tvTaskPlanning = view.findViewById(R.id.et_task_plan);
        CheckBox cbTaskBudgetRequest = view.findViewById(R.id.cb_budget);
        TextInputEditText tvTaskExtraBudget = view.findViewById(R.id.et_extra_budget);
        MaterialButton btnSubmitTaskPlanning = view.findViewById(R.id.btn_submit_task_planning);
        MaterialButton btnResourcesRequest = view.findViewById(R.id.btn_resources_request);
        MaterialButton btnFinancialRequest = view.findViewById(R.id.btn_financial_request);

        switch (RoleTransfer.getRole()) {
            case "Service department manager":
            case "Production department manager":
                tvTaskPlanning.setVisibility(View.INVISIBLE);
                cbTaskBudgetRequest.setVisibility(View.INVISIBLE);
                tvTaskExtraBudget.setVisibility(View.INVISIBLE);
                btnSubmitTaskPlanning.setVisibility(View.INVISIBLE);
                trPlanning.setVisibility(View.GONE);
                trBudget.setVisibility(View.GONE);
                trBudget2.setVisibility(View.GONE);
                btnSubmitTaskPlanning.setVisibility(View.INVISIBLE);
                btnResourcesRequest.setVisibility(View.VISIBLE);
                btnFinancialRequest.setVisibility(View.VISIBLE);
                break;

            default:
                tvTaskPlanning.setVisibility(View.VISIBLE);
                cbTaskBudgetRequest.setVisibility(View.VISIBLE);
                tvTaskExtraBudget.setVisibility(View.INVISIBLE);
                btnSubmitTaskPlanning.setVisibility(View.VISIBLE);
                trPlanning.setVisibility(View.VISIBLE);
                trBudget.setVisibility(View.VISIBLE);
                trBudget2.setVisibility(View.VISIBLE);
                btnSubmitTaskPlanning.setVisibility(View.VISIBLE);
                btnResourcesRequest.setVisibility(View.INVISIBLE);
                btnFinancialRequest.setVisibility(View.INVISIBLE);
                break;
        }

        /* ------- VM --------*/
        TaskItemViewModel taskVM = new ViewModelProvider(requireActivity()).get(TaskItemViewModel.class);
        EventViewModel eventVM = new ViewModelProvider(requireActivity()).get(EventViewModel.class);

        /* ------- LISTENERS --------*/
        eventVM.getEvent().observe(requireActivity(), eventItem -> {

            tvEventId.setText(String.valueOf(eventItem.getId()));
            tvEventType.setText(eventItem.getEventType());
            tvEventFrom.setText(eventItem.getFromDate());
            tvEventTo.setText(eventItem.getToDate());
            tvEventAttendees.setText(String.valueOf(eventItem.getAttendees()));
        });

        taskVM.getTask().observe(requireActivity(), taskItem -> {
            task = taskItem.getTask();

            tvTaskBudget.setText(task.getBudgetForTask());
            tvTaskDescription.setText(task.getTaskDescription());

        });

        cbTaskBudgetRequest.setOnClickListener(v -> {
            tvTaskExtraBudget.setVisibility(View.VISIBLE);
        });

        btnSubmitTaskPlanning.setOnClickListener(v -> submitTaskPlanning());
        btnFinancialRequest.setOnClickListener(v -> HelperFunctions.loadFragment(requireActivity().getSupportFragmentManager(), new FragmentFinancialRequestForm()));
        btnResourcesRequest.setOnClickListener(v -> HelperFunctions.loadFragment(requireActivity().getSupportFragmentManager(), new FragmentRecruitmentRequestForm()));

        return view;
    }

    private void submitTaskPlanning(){

    }
}