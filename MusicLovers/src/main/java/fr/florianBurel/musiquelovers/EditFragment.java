package fr.florianBurel.musiquelovers;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

/**
 * Created by fl0 on 04/11/2013.
 */
public class EditFragment extends Fragment
{

    public interface OnEditFinishedListener
    {
        public void onEditFinished(boolean finishedWithOKButtonClicked);
    }

    private OnEditFinishedListener onEditFinishedListener;

    public void setOnEditFinishedListener(OnEditFinishedListener onEditFinishedListener) {
        this.onEditFinishedListener = onEditFinishedListener;
    }

    Music music;

    private EditText musicTitleEditText;
    private RadioButton musicLikeRadioBtn;
    private RadioButton musicDontLikeRadioBtn;
    private EditText musicArtistEditText;
    private EditText musicCategoryEditText;
    private EditText musicDescriptionEditText;

    private Button cancelBtn;
    private Button okBtn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);

        View view = inflater.inflate(R.layout.edit_layout, container, false);


        bind(view);

        setMusic(music);


        this.okBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                update();
                if(onEditFinishedListener != null)
                    onEditFinishedListener.onEditFinished(true);
            }
        });

        this.cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setMusic(music);
                if(onEditFinishedListener != null)
                    onEditFinishedListener.onEditFinished(false);
            }
        });

        return view;
    }

    private void update() {
        this.music.setAuthor(this.musicArtistEditText.getText().toString());
        this.music.setName(this.musicTitleEditText.getText().toString());
        this.music.setDescription(this.musicDescriptionEditText.getText().toString());
        this.music.setCategory(this.musicCategoryEditText.getText().toString());
        this.music.setLiked(this.musicLikeRadioBtn.isChecked());

        Log.i(this.getClass().toString(), this.music.getDescription());
    }

    public void setMusic(Music music) {

        this.music = music;

        if(music == null) return;
        if(this.musicArtistEditText == null) return;

        this.musicTitleEditText.setText(this.music.getName());
        this.musicArtistEditText.setText(this.music.getAuthor());
        this.musicCategoryEditText.setText(this.music.getCategory());
        this.musicDescriptionEditText.setText(this.music.getDescription());
        this.musicLikeRadioBtn.setChecked(this.music.isLiked());
        this.musicDontLikeRadioBtn.setChecked(!this.music.isLiked());
    }

    private void bind(View view)
    {
        this.musicArtistEditText = (EditText) view.findViewById(R.id.musicArtistEditText);
        this.musicCategoryEditText = (EditText) view.findViewById(R.id.musicCategoryEditText);
        this.musicDescriptionEditText = (EditText) view.findViewById(R.id.musicDescriptionEditText);
        this.musicDontLikeRadioBtn = (RadioButton) view.findViewById(R.id.musicDontLikeRadioBtn);
        this.musicLikeRadioBtn = (RadioButton) view.findViewById(R.id.musicLikeRadioBtn);
        this.musicTitleEditText = (EditText) view.findViewById(R.id.musicTitleEditText);

        this.okBtn = (Button) view.findViewById(R.id.okBtn);
        this.cancelBtn = (Button) view.findViewById(R.id.cancelBtn);

    }
}