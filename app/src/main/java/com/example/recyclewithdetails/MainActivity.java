package com.example.recyclewithdetails;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    final String STATE_TITLE = "state_string";
    final String STATE_MODE = "state_mode";
    int mode;

    private ArrayList<String> names = new ArrayList<>();
    private ArrayList<String> description = new ArrayList<>();
    private ArrayList<String> imageUrl = new ArrayList<>();
    private ArrayList<String> descriptionDetail = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate:started.");
        initImageBitmaps();

        if (savedInstanceState == null) {
            setActionBarTitle("Gunung Indonesia Mode List");
            showRecyleList();
            mode = R.id.action_list;
        } else {
            String stateTitle = savedInstanceState.getString(STATE_TITLE);
            int stateMode = savedInstanceState.getInt(STATE_MODE);
            setActionBarTitle(stateTitle);
            setMode(stateMode);
        }

    }

    private void initImageBitmaps() {
        imageUrl.add("https://www.insureandgo.com.au/images/mount-agung_tcm1005-430753.jpg");
        names.add("Gunung Agung");
        description.add("Karangasem, Bali");
        descriptionDetail.add("Lokasi         : Karangasem, Bali \n" +
                "Ketinggian :  3.031 m diatas permukaan laut\n" +
                "Status         : Gunung Aktif");

        imageUrl.add("https://indonesia.tripcanvas.co/wp-content/uploads/2016/02/Abang_and_Agung.jpg");
        names.add("Gunung Batur");
        description.add("Bangli, Bali");
        descriptionDetail.add("Lokasi         : Bangli, Bali \n" +
                "Ketinggian : 1.717 m diatas permukaan laut\n" +
                "Status         : Gunung Aktif");

        imageUrl.add("http://disk.mediaindonesia.com/thumbs/1200x-/news/2016/06/semeru.jpg");
        names.add("Gunung Semeru");
        description.add("Lumajang, Jawa Timur");
        descriptionDetail.add("Lokasi         : Lumajang, Jawa Timur\n" +
                "Ketinggian : 3.676 m diatas permukaan laut\n" +
                "Status         : Gunung Aktif");

        imageUrl.add("https://www.adventureinyou.com/wp-content/uploads/2015/04/mount-rinjani-hike1.jpg");
        names.add("Gunung Rinjani");
        description.add("Lombok");
        descriptionDetail.add("Lokasi         : Lombok\n" +
                "Ketinggian : 3.726 m diatas permukaan laut\n" +
                "Status         : Gunung Aktif");

        imageUrl.add("http://www.wallpapers13.com/wp-content/uploads/2015/12/Mount-Bromo-Wallpapers-Hd-915x515.jpg");
        names.add("Gunung Bromo");
        description.add("Malang, Jawa Timur");
        descriptionDetail.add("Lokasi         : Malang, Jawa Timur\n" +
                "Ketinggian : 2.329 m diatas permukaan laut\n" +
                "Status         : Gunung Aktif");

        imageUrl.add("https://airebobichon.files.wordpress.com/2018/01/2-pesonabromo-com.jpg");
        names.add("Gunung Ijen");
        description.add("Banyuwangi, Jawa Timur");
        descriptionDetail.add("Lokasi         : Banyuwangi, Jawa Timur\n" +
                "Ketinggian : 2.443 m diatas permukaan laut\n" +
                "Status         : Gunung Aktif");

        imageUrl.add("https://damniloveindonesia.com/image/catalog/explore_indonesia/Artikel/Puncak_Jaya_1.jpg");
        names.add("Gunung Puncak Jaya WIjaya");
        description.add("Jayapura");
        descriptionDetail.add("Lokasi         : Jayapura\n" +
                "Ketinggian : 4.884 m diatas permukaan laut\n" +
                "Status         : Gunung Aktif");

        imageUrl.add("https://awsimages.detik.net.id/community/media/visual/2018/02/28/d96590ad-272b-4a18-b0f6-15473a97d180_169.jpeg?w=780&q=90");
        names.add("Gunung Kerinci");
        description.add("Kerinci, Jambi");
        descriptionDetail.add("Lokasi         : Kerinci, Jambi\n" +
                "Ketinggian : 3.805 m diatas permukaan laut\n" +
                "Status         : Gunung Aktif");
        initRecyclerView();

    }

    private void initRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.recyclerv_view);
        recyclerView.setHasFixedSize(false);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        RecycleViewAdapter adapter = new RecycleViewAdapter(this, names, imageUrl, description, descriptionDetail);
        recyclerView.setAdapter(adapter);
    }

    private void showRecyleList() {
        RecyclerView recyclerView = findViewById(R.id.recyclerv_view);
        recyclerView.setHasFixedSize(false);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        ListActivity adapter = new ListActivity(this, names, imageUrl, description, descriptionDetail);
        recyclerView.setAdapter(adapter);

    }

    private void showRecyclerGrid() {
        RecyclerView recyclerView = findViewById(R.id.recyclerv_view);
        recyclerView.setHasFixedSize(false);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        GridActivity adapter = new GridActivity(this, names, imageUrl, description, descriptionDetail);
        recyclerView.setAdapter(adapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        setMode(item.getItemId());
        return super.onOptionsItemSelected(item);
    }

    public void setMode(int selectedMode) {
        String title = null;
        switch (selectedMode) {
            case R.id.action_list:
                title = "Gunung Indonesia Mode List";
                showRecyleList();
                break;

            case R.id.action_grid:
                title = "Gunung Indonesia Mode Grid";
                showRecyclerGrid();
                break;

            case R.id.action_cardview:
                title = "Gunung Indonesia Mode CardView";
                initRecyclerView();
                break;
        }
        mode = selectedMode;
        setActionBarTitle(title);
    }

    private void setActionBarTitle(String title) {
        getSupportActionBar().setTitle(title);
    }

    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(STATE_TITLE, getSupportActionBar().getTitle().toString());
        outState.putInt(STATE_MODE, mode);
    }
}