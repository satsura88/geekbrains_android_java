package ru.geekbrains.notes;

import android.content.res.TypedArray;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class InfoNoteFragment extends Fragment {

    public static String ARG_NOTE = "note";
    private Note note;

    public static InfoNoteFragment newInstance(Note note){
        InfoNoteFragment fragment = new InfoNoteFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(ARG_NOTE, note);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments()!=null){
            this.note = getArguments().getParcelable(ARG_NOTE);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_info_note,container,false);
        TextView descriptionsView = view.findViewById(R.id.descriptionsView);
        TextView datesView = view.findViewById(R.id.datesView);
        descriptionsView.setText(this.note.getDescription());
        datesView.setText(this.note.getDate());
        TypedArray descriptions = getResources().obtainTypedArray(R.array.description);
        TypedArray dates = getResources().obtainTypedArray(R.array.dates);
        return view;
    }
}