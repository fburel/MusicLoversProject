package fr.florianBurel.musiquelovers;

import android.app.Activity;
import android.app.FragmentManager;
import android.os.Bundle;
import android.widget.FrameLayout;


/**
 * Created by fl0 on 04/11/2013.
 */
public class MusicLoversActivity extends Activity implements MusicListFragment.OnMusicSelectedListener, EditFragment.OnEditFinishedListener {

    EditFragment editFragment;
    MusicListFragment musicListFragment;



    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.main_layout);

        // Verifie que l'on utilise un layout avec le frameLayout
        if (findViewById(R.id.frameLayout) != null) {

            // Verifie que l'on est pas restauré depuis un état précédent (rotation)
            if (savedInstanceState != null) {
                return;
            }

            // Crée un nouveau musicListFragment
            this.musicListFragment = new MusicListFragment();



            // Add the fragment
            getFragmentManager().beginTransaction()
                    .add(R.id.frameLayout, this.musicListFragment).commit();
        }
        else
        {
            // Gestion du cas tablette
            this.musicListFragment = (MusicListFragment) getFragmentManager().findFragmentById(R.id.listFragment);
            this.editFragment = (EditFragment) getFragmentManager().findFragmentById(R.id.editFragment);
        }

        this.musicListFragment.setOnMusicSelectedListener(this);
    }

    @Override
    public void onMusicSelected(Music selected) {

        // Si on est sur tablette
        if(this.editFragment !=null)
        {
            this.editFragment.setMusic(selected);
        }
        else
        {
            // Crée un nouveau musicListFragment
            this.editFragment = new EditFragment();

            this.editFragment.setMusic(selected);

            this.editFragment.setOnEditFinishedListener(this);

            // Add the fragment
            getFragmentManager().beginTransaction()
                    .remove(this.musicListFragment)
                    .add(R.id.frameLayout, this.editFragment).commit();
        }


    }

    @Override
    public void onEditFinished(boolean finishedWithOKButtonClicked) {

        // Si on est sur telephone
        if(findViewById(R.id.frameLayout) != null)
        {
            // bascule vers la music list
            getFragmentManager().beginTransaction()
                    .remove(this.editFragment)
                    .add(R.id.frameLayout, this.musicListFragment).commit();

            // Destruction du edit Fragment
            this.editFragment = null;
        }

        if(finishedWithOKButtonClicked)
        {
            this.musicListFragment.reloadData();
        }
    }
}