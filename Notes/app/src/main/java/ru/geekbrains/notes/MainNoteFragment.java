package ru.geekbrains.notes;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class MainNoteFragment extends Fragment {

    Note currentNote;
    boolean isLandScape;
    public static String KEY_NOTE = "note";

    public static MainNoteFragment newInstance() {
        return new MainNoteFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        isLandScape = getResources().getConfiguration().orientation== Configuration.ORIENTATION_LANDSCAPE;

        if(isLandScape)
            if(currentNote!=null){
                showInfoNoteFragment(currentNote.getDate());
            }else{
                showInfoNoteFragment(0);
            }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putParcelable(KEY_NOTE,currentNote);
        super.onSaveInstanceState(outState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main_note, container, false);
        LinearLayout linearLayout = (LinearLayout) view;
        String[] names = getResources().getStringArray(R.array.names);

        for(int i = 0;i<names.length;i++){
            String name = names[i];
            TextView textView = new TextView(getContext());
            textView.setText(name);
            textView.setTextSize(35);
            linearLayout.addView(textView);

            int finalI = i;
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showInfoNoteFragment(finalI);
                }
            });
        }
        return view;
    }

    private void showInfoNoteFragment(int index) {
        currentNote = new Note(index,
                (getResources().getStringArray(R.array.names)[index]));
        if (isLandScape) {
            showInfoNoteFragmentLand();
        }else{
            showInfoNoteFragmentPort();
        }
    }

    private void showInfoNoteFragmentPort() {
        requireActivity()
                .getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.main_note_container, InfoNoteFragment.newInstance(currentNote))
                .addToBackStack("")
                .commit();
    }

    private void showInfoNoteFragmentLand() {
        requireActivity()
                .getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.info_note_container, InfoNoteFragment.newInstance(currentNote))
                .commit();
    }
}