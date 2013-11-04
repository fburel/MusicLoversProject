package fr.florianBurel.musiquelovers;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

/**
 * Created by fl0 on 04/11/2013.
 */
public class EditActivity extends Activity
{
    Music music;

    private EditText musicTitleEditText;
    private RadioButton musicLikeRadioBtn;
    private RadioButton musicDontLikeRadioBtn;
    private EditText musicArtistEditText;
    private EditText musicCategoryEditText;
    private EditText musicDescriptionEditText;

    private Button cancelBtn;
    private Button okBtn;


    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_layout);

        this.music = new Music();

        bind();

        refresh();


        this.okBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                update();
            }
        });

        this.cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                refresh();
            }
        });


    }

    private void update() {
        this.music.setAuthor(this.musicArtistEditText.getText().toString());
        this.music.setName(this.musicTitleEditText.getText().toString());
        this.music.setDescription(this.musicDescriptionEditText.getText().toString());
        this.music.setCategory(this.musicCategoryEditText.getText().toString());
        this.music.setLiked(this.musicLikeRadioBtn.isChecked());

        Log.i(this.getClass().toString(), this.music.getDescription());
    }

    private void refresh() {
        this.musicTitleEditText.setText(this.music.getName());
        this.musicArtistEditText.setText(this.music.getAuthor());
        this.musicCategoryEditText.setText(this.music.getCategory());
        this.musicDescriptionEditText.setText(this.music.getDescription());
        this.musicLikeRadioBtn.setChecked(this.music.isLiked());
        this.musicDontLikeRadioBtn.setChecked(!this.music.isLiked());
    }

    private void bind()
    {
        this.musicArtistEditText = (EditText) findViewById(R.id.musicArtistEditText);
        this.musicCategoryEditText = (EditText) findViewById(R.id.musicCategoryEditText);
        this.musicDescriptionEditText = (EditText) findViewById(R.id.musicDescriptionEditText);
        this.musicDontLikeRadioBtn = (RadioButton) findViewById(R.id.musicDontLikeRadioBtn);
        this.musicLikeRadioBtn = (RadioButton) findViewById(R.id.musicLikeRadioBtn);
        this.musicTitleEditText = (EditText) findViewById(R.id.musicTitleEditText);

        this.okBtn = (Button) findViewById(R.id.okBtn);
        this.cancelBtn = (Button) findViewById(R.id.cancelBtn);

    }
}