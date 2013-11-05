package fr.florianBurel.musiquelovers;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
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

    private class MusicAdapter extends BaseAdapter
    {

        @Override
        public int getCount() {
            return 0;
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            return null;
        }
    }
}