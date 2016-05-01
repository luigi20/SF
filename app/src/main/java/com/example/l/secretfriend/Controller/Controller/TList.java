package com.example.l.secretfriend.Controller.Controller;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.l.secretfriend.R;

/**
 * Created by L on 20/04/2016.
 */
public class TList extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.v_group);
        //ActionBar actionBar = getSupportActionBar();

      //  actionBar.setDisplayHomeAsUpEnabled(true);

        ImageView btAdd = (ImageView) findViewById(R.id.bt_add);
        ListView listSecret = (ListView) findViewById(R.id.listSecretFriend);
        //btAdd.setOnClickListener(this);
        //listSecret.setOnItemClickListener(this);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
            case R.id.Sobre:
                //    Toast.makeText(this, "Item: " + item.getItemId(), Toast.LENGTH_SHORT).show();
                break;

            case R.id.Ajuda:
                //  Toast.makeText(this, "Item: " + item.getItemId(), Toast.LENGTH_SHORT).show();
                break;

        }
        return true;


    }
}
