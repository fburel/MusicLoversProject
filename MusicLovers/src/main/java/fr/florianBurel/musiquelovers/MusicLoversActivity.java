package fr.florianBurel.musiquelovers;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

/**
 * Created by fl0 on 04/11/2013.
 */
public class MusicLoversActivity extends Activity {

    private ListView listView;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);

        bind();
    }

    private void bind() {
        this.listView = (ListView) findViewById(R.id.listView);
    }
}