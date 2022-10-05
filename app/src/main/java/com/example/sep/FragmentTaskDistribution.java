package com.example.sep;

import static java.lang.Integer.parseInt;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.sep.model.Task;
import com.example.sep.viewModel.RoleTransfer;
import com.example.sep.viewModel.TaskDistributionViewModel;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.textfield.TextInputLayout;


public class FragmentTaskDistribution extends Fragment implements AdapterView.OnItemSelectedListener{

    TextView departmentName, loggedInPersonName;
    TextInputLayout projectReferenceEditText, taskDescriptionEditText;
    Spinner subTeamMembersSpinner, taskPrioritySpinner;
    MaterialButton submitTaskButton;
    TabLayout productionSubTeamsTabLayout, serviceSubTeamsTabLoayout;


    String taskPriority;
    String assignedToMember;
    String assignedToTeam;


    private TaskDistributionViewModel mViewModel;

    public static FragmentTaskDistribution newInstance() {
        return new FragmentTaskDistribution();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        // get role
        String role = RoleTransfer.getRole();

        View view = inflater.inflate(R.layout.fragment_task_distribution, container, false);

        productionSubTeamsTabLayout = view.findViewById(R.id.tabs_production);
        serviceSubTeamsTabLoayout = view.findViewById(R.id.tabs_service);
        if (role.equals("Production department manager")) {
            productionSubTeamsTabLayout.setVisibility(View.VISIBLE);
            serviceSubTeamsTabLoayout.setVisibility(View.INVISIBLE);
        } else if (role.equals("Services department manager")) {
            serviceSubTeamsTabLoayout.setVisibility(View.VISIBLE);
            productionSubTeamsTabLayout.setVisibility(View.INVISIBLE);
        }
        projectReferenceEditText = view.findViewById(R.id.project_reference_text_input);
        taskDescriptionEditText =  view.findViewById(R.id.description_text_input);
        subTeamMembersSpinner =  view.findViewById(R.id.spinner_sub_team_people);
        taskPrioritySpinner =  view.findViewById(R.id.spinner_priority);
        submitTaskButton =  view.findViewById(R.id.btn_submit_task);

        subTeamMembersSpinner.setOnItemSelectedListener(this);
        taskPrioritySpinner.setOnItemSelectedListener(this);

        // Spinners
        // TODO: Get the members from the department and sub-team
        String [] members = {"Elisa", "Giota"};
        ArrayAdapter<String> adapterSubTeamMembers = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, members);
        ArrayAdapter<CharSequence> adapterPriorities = ArrayAdapter.createFromResource(getContext(), R.array.priority_options, android.R.layout.simple_spinner_item);

        // Specify the layout to use when the list of choices appears - from android library
        adapterSubTeamMembers.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapterPriorities.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Apply the adapter to the spinners
        subTeamMembersSpinner.setAdapter(adapterSubTeamMembers);
        taskPrioritySpinner.setAdapter(adapterPriorities);

        submitTaskButton.setOnClickListener(v -> submitTask());

        productionSubTeamsTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab!=null){
                    assignedToTeam = String.valueOf(tab.getText());
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });




        return view;
    }


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
        if(adapterView.getId() == R.id.spinner_priority)
        {
            assignedToMember = String.valueOf(adapterView.getItemAtPosition(position));
        }
        else if(adapterView.getId() == R.id.spinner_sub_team_people)
        {
            taskPriority = String.valueOf(adapterView.getItemAtPosition(position));
        }


    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }


    private void submitTask(){
        Task newTask = new Task (
                String.valueOf(departmentName),
                assignedToTeam,
                String.valueOf(projectReferenceEditText),
                String.valueOf(taskDescriptionEditText),
                assignedToMember,
                taskPriority
                );

    }


}