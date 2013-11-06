package fr.florianBurel.musiquelovers;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Context;
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

import java.util.ArrayList;

/**
 * Created by fl0 on 06/11/2013.
 */
public class MusicListFragment extends Fragment
{

    /*
    Utilistaion d'un interface pour remonter l'information Music Selectionnée
     */

    public interface OnMusicSelectedListener
    {
        public void onMusicSelected(Music selected);
    }

    private OnMusicSelectedListener onMusicSelectedListener;

    public void setOnMusicSelectedListener(OnMusicSelectedListener onMusicSelectedListener) {
        this.onMusicSelectedListener = onMusicSelectedListener;
    }


    `
    /*
        compile 'com.android.support:support-v4:13.0.+'
         */
    private static final int EDIT_ACTION = 1001;
    private static final int DELETE_ACTION = 1002;
    private ListView listView;


    private ArrayList<Music> musics;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        View view = inflater.inflate(R.layout.music_list_fragment, container, false);

        this.listView = (ListView) view.findViewById(R.id.listView);


        new MusicFetcher(this.getActivity()).execute();

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

        return view;
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
                this.listView.setAdapter(new MusicAdapter(this.musics, this.getActivity()));

        }

        return super.onContextItemSelected(item);
    }

    private void didSelectMusic(Music music) {
        Log.e("toto", music.toString());
    }


    private class MusicAdapter extends BaseAdapter
    {

        private  ArrayList<Music> list;
        private Context context;

        private MusicAdapter(ArrayList<Music> list, Context context) {
            this.list = list;
            this.context = context;
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
            LayoutInflater layoutInflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            View cell = layoutInflater.inflate(R.layout.music_cell, null);

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

        private ProgressDialog progressDialog;

        public MusicFetcher(Context c)
        {
            this.progressDialog = new ProgressDialog(c);
            this.progressDialog.setMessage("Please Wait");
            this.progressDialog.setCancelable(false);
            this.progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);

        }

        @Override
        protected void onPreExecute() {
            this.progressDialog.show();
            super.onPreExecute();
        }


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
            this.progressDialog.dismiss();
            musics = downloadedMusics;
            listView.setAdapter(new MusicAdapter(musics, getActivity()));
            super.onPostExecute(musics);
        }
    }
}