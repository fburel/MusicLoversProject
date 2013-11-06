package fr.florianBurel.musiquelovers;

import android.app.Activity;
import android.app.FragmentManager;
import android.os.Bundle;
import android.widget.FrameLayout;


/**
 * Created by fl0 on 04/11/2013.
 */
public class MusicLoversActivity extends Activity implements MusicListFragment.OnMusicSelectedListener
{

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
        }

        this.musicListFragment.setOnMusicSelectedListener(this);
    }

    @Override
    public void onMusicSelected(Music selected) {

        // Si on est sur tablette
        if(this.editFragment !=null)
        {

        }
        else
        {
            // Crée un nouveau musicListFragment
            this.editFragment = new EditFragment();



            // Add the fragment
            getFragmentManager().beginTransaction()
                    .remove(this.musicListFragment)
                    .add(R.id.frameLayout, this.editFragment).commit();
        }
    }
}