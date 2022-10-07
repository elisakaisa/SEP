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

import com.example.sep.view.eventRecyclerView.EventItem;
import com.example.sep.view.eventRecyclerView.EventItemAdapter;
import com.example.sep.model.Event;
import com.example.sep.viewModel.EventListViewModel;
import com.example.sep.viewModel.EventViewModel;
import com.example.sep.viewModel.RoleTransfer;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentEventList#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentEventList extends Fragment {

    private RecyclerView rv_events;
    private ArrayList<EventItem> itemList;
    private EventViewModel eventVM;
    private FloatingActionButton fabAdd;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FragmentEventList() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentEventList.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentEventList newInstance(String param1, String param2) {
        FragmentEventList fragment = new FragmentEventList();
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
        View view = inflater.inflate(R.layout.fragment_event_list, container, false);

        /*------------ UI ------------*/
        rv_events = view.findViewById(R.id.rv_events);
        fabAdd = view.findViewById(R.id.fab_add);

        int accessLevel = 0;
        String role = RoleTransfer.getRole();
        switch (role) {
            case "Customer Service":
                fabAdd.setVisibility(View.VISIBLE);
                break;
            case "Senior Customer Service Officer":
                accessLevel = 1;
                fabAdd.setVisibility(View.INVISIBLE);
                break;
            case "Financial manager":
                accessLevel = 2;
                fabAdd.setVisibility(View.INVISIBLE);
                break;
            case "Administration department manager":
                accessLevel = 3;
                fabAdd.setVisibility(View.INVISIBLE);
                break;
        }

        /*------------ VM ------------*/
        eventVM = new ViewModelProvider(requireActivity()).get(EventViewModel.class);
        EventListViewModel eventListVM = new ViewModelProvider(requireActivity()).get(EventListViewModel.class);

        /*---------- LISTENERS ----------*/
        int finalAccessLevel = accessLevel-1;
        eventListVM.getEvent().observe(requireActivity(), events -> {
            itemList = new ArrayList<>();
            int i = 0;
            for (Event singleEvent : events){
                // add to the recyclerView only the events that the employee can view
                if (singleEvent.getLevel() >= finalAccessLevel) {
                    itemList.add(new EventItem(singleEvent, i));
                }
                i++;
            }

            EventItemAdapter resultItemAdapter = new EventItemAdapter(itemList);
            rv_events.setLayoutManager(new LinearLayoutManager(getActivity()));
            rv_events.setAdapter(resultItemAdapter);
            resultItemAdapter.setOnItemClickListener(onItemClickListener);
        });

        fabAdd.setOnClickListener(v -> {
            loadFragment(new FragmentCreateEvent());
        });

        return view;
    }

    private final View.OnClickListener onItemClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            RecyclerView.ViewHolder viewHolder = (RecyclerView.ViewHolder) view.getTag();
            int position = viewHolder.getAdapterPosition();
            EventItem eventItem = itemList.get(position);
            eventVM.setEvent(eventItem);

            loadFragment(new FragmentEventDetails());
        }
    };

    private void loadFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.content_container, fragment, "");
        fragmentTransaction.commit();
    }
}