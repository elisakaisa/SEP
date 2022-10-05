package com.example.sep;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.sep.model.Event;
import com.example.sep.viewModel.EventViewModel;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textview.MaterialTextView;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentEventDetails#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentEventDetails extends Fragment {

    /*__________ SAVING/DELETING __________*/
    Integer itemIdentifier;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FragmentEventDetails() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentEventDetails.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentEventDetails newInstance(String param1, String param2) {
        FragmentEventDetails fragment = new FragmentEventDetails();
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
        View view = inflater.inflate(R.layout.fragment_event_details, container, false);

        MaterialTextView tvClientName = view.findViewById(R.id.tv_client_name_view2);
        MaterialButton btnDelete = view.findViewById(R.id.btn_event_details_delete);

        EventViewModel eventVM = new ViewModelProvider(requireActivity()).get(EventViewModel.class);

        eventVM.getEvent().observe(getActivity(), eventItem -> {
            Event event = eventItem.getiEvent();
            tvClientName.setText(event.getClientName());
            itemIdentifier = eventItem.getIdx();
        });

        btnDelete.setOnClickListener(v -> onDelete());

        return view;
    }

    private void onDelete() {
        BaseActivity.eventList.deleteEvent(itemIdentifier);
        //Update list in local storage
        saveResultList();
        Toast.makeText(getActivity(), "Event is deleted", Toast.LENGTH_SHORT).show();

        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.content_container, new FragmentEventList(), "");
        fragmentTransaction.commit();
    }

    private void saveResultList() {
        try {
            FileOutputStream fos = getActivity().openFileOutput("eventlist.ser", Context.MODE_PRIVATE);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(BaseActivity.eventList);
            oos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}