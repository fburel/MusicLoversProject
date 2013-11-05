package fr.florianBurel.musiquelovers;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;

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

        this.listView.setAdapter(new MusicAdapter(this.musics));

        this.listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                didSelectMusic(musics.get(i));
            }
        });

    }

    private void didSelectMusic(Music music) {
        
    }

    private void bind()
    {
        this.listView = (ListView) findViewById(R.id.listView);
    }

    private class MusicAdapter extends BaseAdapter
    {

        private  ArrayList<Music> list;

        private MusicAdapter(ArrayList<Music> list) {
            this.list = list;
        }

        @Override
        public int getCount() {
            return this.list.size();
        }

        @Override
        public Object getItem(int i) {
            return this.list.get(i);
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {

            // Recupération de la cellule
            View cell = getLayoutInflater().inflate(R.layout.music_cell, null);

//            // Recupération de la musique
            Music music = this.list.get(i);

//            // Recupération des composant de la cellule

            TextView mainTextView = (TextView) cell.findViewById(R.id.mainTextView);
            TextView detailTextView = (TextView) cell.findViewById(R.id.detailTextView);
            CheckBox checkBox = (CheckBox) cell.findViewById(R.id.checkBox);

//            //Affectation des valeur
            mainTextView.setText(music.getName());
            detailTextView.setText(music.getAuthor());
            checkBox.setChecked(music.isLiked());

            return cell;
        }
    }
}