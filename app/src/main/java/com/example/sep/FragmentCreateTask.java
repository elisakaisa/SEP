package com.example.sep;

import static java.lang.Integer.parseInt;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.sep.database.Employees;
import com.example.sep.model.Employee;
import com.example.sep.model.Task;
import com.example.sep.utils.HelperFunctions;
import com.example.sep.viewModel.RoleTransfer;
import com.example.sep.viewModel.eventVM.EventViewModel;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.textfield.TextInputEditText;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;


public class FragmentCreateTask extends Fragment implements AdapterView.OnItemSelectedListener{

    TextInputEditText projectReferenceEditText, taskDescriptionEditText, assignBudgetEditText;
    Spinner subTeamMembersSpinner, taskPrioritySpinner;
    MaterialButton submitTaskButton;
    TabLayout productionSubTeamsTabLayout, serviceSubTeamsTabLayout;

    String eventId;
    String taskPriority;
    String assignedToMember;
    String assignedToTeam;
    String loggedInPersonName;
    String role;
    String departmentName;


    public static FragmentCreateTask newInstance() {
        return new FragmentCreateTask();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        // get role
        role = RoleTransfer.getRole();
        departmentName = RoleTransfer.getDepartment();
        loggedInPersonName = RoleTransfer.getName();

        View view = inflater.inflate(R.layout.fragment_create_task, container, false);

        projectReferenceEditText = view.findViewById(R.id.project_reference_text_input);
        taskDescriptionEditText =  view.findViewById(R.id.description_text_input);
        subTeamMembersSpinner =  view.findViewById(R.id.spinner_sub_team_people);
        taskPrioritySpinner =  view.findViewById(R.id.spinner_priority);
        assignBudgetEditText = view.findViewById(R.id.initial_budget_task_text_input);
        submitTaskButton =  view.findViewById(R.id.btn_submit_task);

        productionSubTeamsTabLayout = view.findViewById(R.id.tabs_production);
        serviceSubTeamsTabLayout = view.findViewById(R.id.tabs_service);

        if (role.equals("Production department manager")) {
            productionSubTeamsTabLayout.setVisibility(View.VISIBLE);
            serviceSubTeamsTabLayout.setVisibility(View.INVISIBLE);
            if (assignedToTeam == null){
                assignedToTeam = "Decor";
                setSpinnerSubTeam(assignedToTeam);
            }

        } else if (role.equals("Services department manager")) {
            serviceSubTeamsTabLayout.setVisibility(View.VISIBLE);
            productionSubTeamsTabLayout.setVisibility(View.INVISIBLE);
            if (assignedToTeam == null){
                assignedToTeam = "Food";
                setSpinnerSubTeam(assignedToTeam);
            }
        }



        EventViewModel eventVM = new ViewModelProvider(requireActivity()).get(EventViewModel.class);

        /* ------- LISTENERS --------*/
        eventVM.getEvent().observe(requireActivity(), eventItem -> {
            eventId = String.valueOf(eventItem.getId());
        });

        subTeamMembersSpinner.setOnItemSelectedListener(this);
        taskPrioritySpinner.setOnItemSelectedListener(this);

        productionSubTeamsTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab!=null){
                    assignedToTeam = String.valueOf(tab.getText());
                    setSpinnerSubTeam(assignedToTeam);
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {}

            @Override
            public void onTabReselected(TabLayout.Tab tab) {}
        });

        serviceSubTeamsTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab!=null){
                    assignedToTeam = String.valueOf(tab.getText());
                    setSpinnerSubTeam(assignedToTeam);
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {}

            @Override
            public void onTabReselected(TabLayout.Tab tab) {}
        });

        submitTaskButton.setOnClickListener(v -> submitTask());
        return view;
    }


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
        if(adapterView.getId() == R.id.spinner_priority) {
            taskPriority = String.valueOf(adapterView.getItemAtPosition(position));
        } else if (adapterView.getId() == R.id.spinner_sub_team_people) {
            assignedToMember = String.valueOf(adapterView.getItemAtPosition(position));
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }


    private void submitTask(){
        Task newTask = new Task (
                BaseActivity.taskList.setNewTaskId(),
                eventId,
                departmentName,
                loggedInPersonName,
                assignedToTeam,
                assignedToMember,
                String.valueOf(projectReferenceEditText.getText()),
                String.valueOf(taskDescriptionEditText.getText()),
                taskPriority,
                "",
                String.valueOf(assignBudgetEditText.getText()),
                Boolean.FALSE,
                Boolean.FALSE
                );
        saveResultsTaskList(newTask);
        HelperFunctions.loadFragment(requireActivity().getSupportFragmentManager(), new FragmentTaskListManager());
    }

    private void saveResultsTaskList(Task task) {
        BaseActivity.taskList.addTask(task);
        try {
            FileOutputStream fos = requireActivity().openFileOutput(BaseActivity.TASK_LIST_FILE, Context.MODE_PRIVATE);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(BaseActivity.taskList);
            oos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setSpinnerSubTeam(String assignedToTeam){
        Employees cEmployees = new Employees();
        cEmployees.initEmployees();
        String subTeam = cEmployees.assignRoleToSubTeam(assignedToTeam);
        ArrayList<Employee> employeesSubTeam = cEmployees.getEmployeesFromDbBySubTeam(RoleTransfer.getDepartment(), subTeam);

        ArrayList<String> members = new ArrayList<>();

        for (int i=0 ; i< employeesSubTeam.size() ; i++) {
            members.add(employeesSubTeam.get(i).getName());
        }

        ArrayAdapter<String> adapterSubTeamMembers = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, members);
        ArrayAdapter<CharSequence> adapterPriorities = ArrayAdapter.createFromResource(getContext(), R.array.priority_options, android.R.layout.simple_spinner_item);

        // Specify the layout to use when the list of choices appears - from android library
        adapterSubTeamMembers.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapterPriorities.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Apply the adapter to the spinners
        subTeamMembersSpinner.setAdapter(adapterSubTeamMembers);
        taskPrioritySpinner.setAdapter(adapterPriorities);

    }

}