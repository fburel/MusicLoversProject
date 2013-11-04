package fr.florianBurel.musiquelovers;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fl0 on 04/11/2013.
 */
public class MusicLoversActivity extends Activity {

    private ListView listView;

    private ArrayList<Music> musics;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);

        bind();

        this.musics = Music.getAllMusics();

        this.listView.setAdapter(new ArrayAdapter<Music>(this, android.R.layout.simple_list_item_1, this.musics));

    }

    private void bind()
    {
        this.listView = (ListView) findViewById(R.id.listView);
    }
}