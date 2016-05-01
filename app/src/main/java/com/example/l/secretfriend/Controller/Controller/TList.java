package com.example.l.secretfriend.Controller.Controller;

import android.app.Activity;
import android.os.Bundle;
import android.content.Intent;

import com.example.l.secretfriend.R;

/**
 * Created by L on 20/04/2016.
 */
public class TList extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.v_list);
        Intent intent = getIntent();



        if(intent!= null)
        {
            Bundle params = intent.getExtras();
            if(params != null) {
             //   String nome = params.getString("nomeGrupo");
               // TextView nomeView = (TextView) findViewById(R.id.nomeTexto);
                //nomeView.setText(nome);

            }
        }
    }
}
