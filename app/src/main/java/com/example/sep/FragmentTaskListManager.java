package com.example.sep;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sep.model.Task;
import com.example.sep.utils.HelperFunctions;
import com.example.sep.view.taskRecyclerView.TaskItem;
import com.example.sep.view.taskRecyclerView.TaskItemAdapter;
import com.example.sep.viewModel.bottomNavigationVM.BottomNavigationViewModel;
import com.example.sep.viewModel.eventVM.EventViewModel;
import com.example.sep.viewModel.taskVM.TaskViewModel;
import com.example.sep.viewModel.taskVM.TaskListViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentTaskListManager#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentTaskListManager extends Fragment {

    private RecyclerView rv_tasks;
    private ArrayList<TaskItem> itemTaskList;
    private TaskViewModel taskVM;
    private TaskListViewModel taskListVM;
    private BottomNavigationViewModel bottomNavVM;
    private FloatingActionButton fabAddTask;
    private String eventId, navPage;


    public FragmentTaskListManager() {
        // Required empty public constructor
    }

    public static FragmentTaskListManager newInstance(String param1, String param2) {
        return new FragmentTaskListManager();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_task_list, container, false);
        rv_tasks = view.findViewById(R.id.rv_tasks);
        fabAddTask = view.findViewById(R.id.fab_add_task);


        /*------------ VM ------------*/
        EventViewModel eventVM = new ViewModelProvider(requireActivity()).get(EventViewModel.class);
        taskVM = new ViewModelProvider(requireActivity()).get(TaskViewModel.class);
        taskListVM = new ViewModelProvider(requireActivity()).get(TaskListViewModel.class);
        bottomNavVM = new ViewModelProvider(requireActivity()).get(BottomNavigationViewModel.class);


        bottomNavVM.getSelectedNavigationPage().observe(requireActivity(), navItem -> {
            navPage = String.valueOf(navItem);
        });

        eventVM.getEvent().observe(requireActivity(), eventItem -> {
            eventId = String.valueOf(eventItem.getId());
        });

        taskListVM.getTask().observe(requireActivity(), tasks -> {
            itemTaskList = new ArrayList<>();
            Integer i = 0;
            for (Task singleTask : tasks){
                if (Objects.equals(navPage, "Events")){
                    if (Objects.equals(singleTask.getBelongsToEvent(), eventId)){
                        itemTaskList.add(new TaskItem(singleTask, i));
                        i++;
                    }
                } else if (Objects.equals(navPage, "Tasks")){
                    itemTaskList.add(new TaskItem(singleTask, i));
                    i++;
                }

            }
            TaskItemAdapter resultTaskItemAdapter = new TaskItemAdapter(itemTaskList);
            rv_tasks.setLayoutManager(new LinearLayoutManager(getActivity()));
            rv_tasks.setAdapter(resultTaskItemAdapter);
            resultTaskItemAdapter.setOnItemClickListener(onItemTaskClickListener);
        });


        fabAddTask.setOnClickListener(v -> {
            HelperFunctions.loadFragment(requireActivity().getSupportFragmentManager(), new FragmentCreateTask());
        });

        return view;
    }

    private final View.OnClickListener onItemTaskClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            RecyclerView.ViewHolder viewHolder = (RecyclerView.ViewHolder) view.getTag();
            int position = viewHolder.getAdapterPosition();
            TaskItem taskItem = itemTaskList.get(position);
            taskVM.setTask(taskItem.getTask());
            taskVM.setIdentifierTask(taskItem.getTaskID());

            HelperFunctions.loadFragment(requireActivity().getSupportFragmentManager(), new FragmentTaskDetails());
        }
    };

}