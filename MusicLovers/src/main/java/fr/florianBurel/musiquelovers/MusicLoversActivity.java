package fr.florianBurel.musiquelovers;

import android.app.Activity;
import android.app.FragmentManager;
import android.os.Bundle;
import android.widget.FrameLayout;


/**
 * Created by fl0 on 04/11/2013.
 */
public class MusicLoversActivity extends Activity
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
    }
}