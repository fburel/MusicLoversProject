package fr.florianBurel.musiquelovers;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;

import com.androidquery.util.XmlDom;

import org.xml.sax.SAXException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by fl0 on 04/11/2013.
 */
public class MusicLoversActivity extends Activity {

    /*
    compile 'com.android.support:support-v4:13.0.+'
     */
    private static final int EDIT_ACTION = 1001;
    private static final int DELETE_ACTION = 1002;
    private ListView listView;

    private ArrayList<Music> musics;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.main_layout);

        bind();


        new MusicFetcher().execute();

        this.listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                didSelectMusic(musics.get(i));
            }
        });

        // ajoute un menu contextuelle sur le long click
        registerForContextMenu(this.listView);

        this.listView.setOnCreateContextMenuListener(new View.OnCreateContextMenuListener() {
            public static final int VIEW_GROUP = 0;

            @Override
            public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
                contextMenu.add(0, EDIT_ACTION, VIEW_GROUP, "Edit");
                contextMenu.add(0, DELETE_ACTION, VIEW_GROUP, "Delete");

            }
        });

    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {

        // Recupération de la musique
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)item.getMenuInfo();
        Music music = this.musics.get(info.position);

        switch (item.getItemId())
        {
            case EDIT_ACTION:
                didSelectMusic(music);
                break;
            case DELETE_ACTION:
                // TODO : Delete the music from the DB
                this.musics.remove(music);
                this.listView.setAdapter(new ArrayAdapter<Music>(this, android.R.layout.simple_list_item_1, this.musics));

        }

        return super.onContextItemSelected(item);
    }

    private void didSelectMusic(Music music) {
        Log.e("toto", music.toString());
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
            return i;
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



    private class MusicFetcher extends AsyncTask<Void, Void, ArrayList<Music>>
    {

        @Override
        protected ArrayList<Music> doInBackground(Void... voids) {
            try {
                return Music.getAllMusics();
            } catch (Exception e) {
                return new ArrayList<Music>();
            }
        }

        @Override
        protected void onPostExecute(ArrayList<Music> downloadedMusics) {
            musics = downloadedMusics;
            listView.setAdapter(new MusicAdapter(musics));
            super.onPostExecute(musics);
        }
    }
}