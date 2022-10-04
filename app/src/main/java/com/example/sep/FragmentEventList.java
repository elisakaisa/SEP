package com.example.sep;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.EventLogTags;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.sep.database.EventList;
import com.example.sep.eventRecyclerView.EventItem;
import com.example.sep.eventRecyclerView.EventItemAdapter;
import com.example.sep.model.Event;
import com.example.sep.viewModel.EventListViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentEventList#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentEventList extends Fragment {

    RecyclerView rv_events;
    public ArrayList<EventItem> itemList;

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

        rv_events = view.findViewById(R.id.rv_events);

        EventListViewModel eventListVM = new ViewModelProvider(requireActivity()).get(EventListViewModel.class);
        eventListVM.getEvent().observe(requireActivity(), events -> {
            itemList = new ArrayList<>();
            Integer i = 0;
            for (Event singleEvent : events){
                itemList.add(new EventItem(singleEvent, i));
                i++;
            }

            EventItemAdapter resultItemAdapter = new EventItemAdapter(itemList);
            rv_events.setLayoutManager(new LinearLayoutManager(getActivity()));
            rv_events.setAdapter(resultItemAdapter);
        });
        return view;
    }
}