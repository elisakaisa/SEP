package com.example.sep;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.sep.model.Task;
import com.example.sep.utils.HelperFunctions;
import com.example.sep.view.taskRecyclerView.TaskItem;
import com.example.sep.view.taskRecyclerView.TaskItemAdapter;
import com.example.sep.viewModel.RoleTransfer;
import com.example.sep.viewModel.taskVM.TaskItemViewModel;
import com.example.sep.viewModel.taskVM.TaskListViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentTaskListSubTeam#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentTaskListSubTeam extends Fragment {

    private RecyclerView rv_tasks;
    private ArrayList<TaskItem> itemList;
    private TaskItemViewModel taskVM;
    private TaskListViewModel taskListVM;

    private FloatingActionButton fabAddTask;
    private String name;

    public FragmentTaskListSubTeam() {
        // Required empty public constructor
    }

    public static FragmentTaskListSubTeam newInstance(String param1, String param2) {
        return new FragmentTaskListSubTeam();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        name = RoleTransfer.getName();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_task_list, container, false);
        rv_tasks = view.findViewById(R.id.rv_tasks);
        fabAddTask = view.findViewById(R.id.fab_add_task);
        fabAddTask.setVisibility(View.INVISIBLE);

        /*------------ VM ------------*/
        taskVM = new ViewModelProvider(requireActivity()).get(TaskItemViewModel.class);
        taskListVM = new ViewModelProvider(requireActivity()).get(TaskListViewModel.class);

        taskListVM.getTask().observe(requireActivity(), tasks -> {
            itemList = new ArrayList<>();
            Integer i = 0;
            for (Task singleEvent : tasks){
                if (Objects.equals(singleEvent.getAssignedTo(), name)){
                    itemList.add(new TaskItem(singleEvent, i));
                    i++;
                }
            }

            TaskItemAdapter resultItemAdapter = new TaskItemAdapter(itemList);
            rv_tasks.setLayoutManager(new LinearLayoutManager(getActivity()));
            rv_tasks.setAdapter(resultItemAdapter);
            resultItemAdapter.setOnItemClickListener(onItemClickListener);
        });

        return view;
    }

    private final View.OnClickListener onItemClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            RecyclerView.ViewHolder viewHolder = (RecyclerView.ViewHolder) view.getTag();
            int position = viewHolder.getAdapterPosition();
            TaskItem taskItem = itemList.get(position);
            taskVM.setTask(taskItem);

            HelperFunctions.loadFragment(requireActivity().getSupportFragmentManager(), new FragmentTaskDetails());
        }
    };
}