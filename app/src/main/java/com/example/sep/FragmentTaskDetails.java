package com.example.sep;

import static com.example.sep.model.Task.PLANNING_EXTRA_BUDGET;
import static com.example.sep.model.Task.PLANNING_OK;
import static com.example.sep.model.Task.REQUESTS_NO_NEED;
import static com.example.sep.model.Task.REQUESTS_PENDING;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TableRow;

import com.example.sep.model.Task;
import com.example.sep.utils.HelperFunctions;
import com.example.sep.viewModel.eventVM.EventViewModel;
import com.example.sep.viewModel.RoleTransfer;
import com.example.sep.viewModel.taskVM.TaskViewModel;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textview.MaterialTextView;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentTaskDetails#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentTaskDetails extends Fragment {
    private Task task;
    TextInputEditText tvTaskExtraBudget;
    private int itemIdentifierTask;
    CheckBox cbTaskBudgetRequest;


    public FragmentTaskDetails() {
        // Required empty public constructor
    }

    public static FragmentTaskDetails newInstance() {
        return new FragmentTaskDetails();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
        cbTaskBudgetRequest = view.findViewById(R.id.cb_budget);
        tvTaskExtraBudget = view.findViewById(R.id.et_extra_budget);
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
        TaskViewModel taskVM = new ViewModelProvider(requireActivity()).get(TaskViewModel.class);
        EventViewModel eventVM = new ViewModelProvider(requireActivity()).get(EventViewModel.class);

        /* ------- LISTENERS --------*/
        eventVM.getEvent().observe(requireActivity(), event -> {
            tvEventType.setText(event.getEventType());
            tvEventFrom.setText(event.getFromDate());
            tvEventTo.setText(event.getToDate());
            tvEventAttendees.setText(String.valueOf(event.getAttendees()));
        });

        taskVM.getTask().observe(requireActivity(), taskItem -> {
            task = taskItem;

            tvTaskBudget.setText(task.getBudgetForTask());
            tvTaskDescription.setText(task.getTaskDescription());
            tvEventId.setText(task.getBelongsToEvent());
            // TODO: instead of getting the event data from eventVM, get it by searching in EventList with eventId

        });

        itemIdentifierTask = taskVM.getIdentifierTask();

        cbTaskBudgetRequest.setOnClickListener(v -> tvTaskExtraBudget.setVisibility(View.VISIBLE));

        btnSubmitTaskPlanning.setOnClickListener(v -> submitTaskPlanning());
        btnFinancialRequest.setOnClickListener(v -> HelperFunctions.loadFragment(requireActivity().getSupportFragmentManager(), new FragmentFinancialRequestForm()));
        btnResourcesRequest.setOnClickListener(v -> HelperFunctions.loadFragment(requireActivity().getSupportFragmentManager(), new FragmentRecruitmentRequestForm()));

        return view;
    }

    private void submitTaskPlanning(){
        if (cbTaskBudgetRequest.isChecked()){
            task.setTaskPlanningStatus(PLANNING_EXTRA_BUDGET);
            task.setExtraBudgetRequest(REQUESTS_PENDING);
            task.setBudgetForTask(String.valueOf(tvTaskExtraBudget.getText()));
        }else{
            task.setTaskPlanningStatus(PLANNING_OK);
            task.setExtraBudgetRequest(REQUESTS_NO_NEED);
        }

        BaseActivity.taskList.updateTask(task, itemIdentifierTask);

        //Update list in local storage
        saveResultsTaskList();
        HelperFunctions.loadFragment(requireActivity().getSupportFragmentManager(), new FragmentTaskListSubTeam());
    }

    private void saveResultsTaskList() {
        try {
            FileOutputStream fos = requireActivity().openFileOutput(BaseActivity.TASK_LIST_FILE, Context.MODE_PRIVATE);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(BaseActivity.taskList);
            oos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}