package fr.florianBurel.musiquelovers;

import android.app.Activity;
import android.os.Bundle;
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


    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_layout);

        this.music = new Music();


    }
}