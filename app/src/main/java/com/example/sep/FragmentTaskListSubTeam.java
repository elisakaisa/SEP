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

    RecyclerView rv_tasks;
    public ArrayList<TaskItem> itemList;
    TaskItemViewModel taskVM;
    TaskListViewModel taskListVM;

    FloatingActionButton fabAddTask;


    String name;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FragmentTaskListSubTeam() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentTaskListPerPerson.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentTaskListSubTeam newInstance(String param1, String param2) {
        FragmentTaskListSubTeam fragment = new FragmentTaskListSubTeam();
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

            loadFragment(new FragmentTaskDetails());
        }
    };

    private void loadFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = requireActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.content_container, fragment, "");
        fragmentTransaction.commit();
    }
}