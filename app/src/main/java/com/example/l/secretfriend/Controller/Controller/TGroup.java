package com.example.l.secretfriend.Controller.Controller;

/**
 * Created by L on 24/04/2016.
 */

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

import com.example.l.secretfriend.R;

import Model.Person;

public class TGroup extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener {

    private ArrayList<Person> listSecretFriend = new ArrayList<Person>();
    private int count = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.v_group);
        ActionBar actionBar = getSupportActionBar();

        actionBar.setDisplayHomeAsUpEnabled(true);

        ImageView btAdd = (ImageView) findViewById(R.id.bt_add);
        ListView listSecret = (ListView) findViewById(R.id.listSecretFriend);
        btAdd.setOnClickListener(this);
        listSecret.setOnItemClickListener(this);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        super.onCreateOptionsMenu(menu);

        return (true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
            case R.id.Sorteio:
                //    Toast.makeText(this, "Item: " + item.getItemId(), Toast.LENGTH_SHORT).show();
                break;

            case R.id.Sobre:
                //  Toast.makeText(this, "Item: " + item.getItemId(), Toast.LENGTH_SHORT).show();
                break;

        }
        return true;


    }

    public void Message(String msg) {

        Toast warningScreen = Toast.makeText(this, msg, Toast.LENGTH_LONG);
        warningScreen.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL, 0, 0);
        warningScreen.show();
    }


    @Override
    public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {

        if (count > 0)
        {
            ListView listSecret = (ListView) findViewById(R.id.listSecretFriend);

            final ArrayAdapter<Person> adapter = new ArrayAdapter<Person>(this, R.layout.support_simple_spinner_dropdown_item, listSecretFriend);
            listSecret.setAdapter(adapter);
            Person person = adapter.getItem(position);
            if(!person.getAmigoSecreto().getNome().isEmpty())
            {
                Message(this.getString(R.string.yourSecretFriend1) +" "  + person.getNome() +" "+
                        this.getString(R.string.yourSecretFriend2)+" "  + person.getAmigoSecreto().getNome());
            }

        }
        else
        {
            Message(this.getString(R.string.listSecretFriendBeforeRaffle));
        }
    }


    @Override
    public void onClick(View v) {


        ListView listSecret = (ListView) findViewById(R.id.listSecretFriend);
        // definindo a implementação ArrayAdapter como ListAdapter da ListView
        final ArrayAdapter<Person> adapter = new ArrayAdapter<Person>(this, R.layout.support_simple_spinner_dropdown_item, listSecretFriend);
        listSecret.setAdapter(adapter);
        // recuperando o texto digitado pelo usuario
        EditText edtPerson = (EditText) findViewById(R.id.edtPerson);
        Person secretFriend = new Person(edtPerson.getText().toString());
        // caso o texto for preenchido, adiciona na lista e atualiza o adapter
        // caso contrario exibe uma mensagem solicitando ao usuário que digite uma série
        if ((!edtPerson.getText().toString().trim().isEmpty()))
        {
            edtPerson.setText("");
            edtPerson.findFocus();
            listSecretFriend.add(secretFriend);
            adapter.notifyDataSetChanged();
        }
        else
        {
            Message(this.getString(R.string.warningMessageAddPerson));
        }

    }

    public void Raffle(ArrayList<Person>selectList ,ArrayList<Person> selectedList)
    {
        for (int i = 0; i < selectList.size(); i++)
        {
            for (int j = 0; j < selectedList.size(); j++)
            {
                if (selectedList.size() % 2 != 0)
                {
                    Random aleatorio1 = new Random();
                    int retirado = aleatorio1.nextInt(selectedList.size()) + 1;
                    selectedList.remove(retirado - 1);
                    selectList.remove(retirado - 1);
                }
                while (selectedList.size() != 0)
                {
                    boolean equal = true;
                    while (equal == true)
                    {

                        //int itens = listaSorteado.size();
                        Random aleatorio = new Random();
                        int raffle = aleatorio.nextInt(selectedList.size()) + 1;
                        if (selectList.get(i).equals(selectedList.get(raffle - 1)) != true)
                        {
                            Person secretFriend = selectList.get(i);
                            secretFriend.setAmigoSecreto(selectedList.get(raffle - 1));
                            listSecretFriend.add(selectList.remove(i));
                            selectedList.remove(raffle - 1);
                            equal = false;

                        }
                    }
                }
            }
        }
    }
    public void onClickRaffle(View v) {


        if ((listSecretFriend.size() != 0) && (listSecretFriend.size() != 1))
        {
            ArrayList<Person> selectedList = new ArrayList<Person>();
            ArrayList<Person> selectList = new ArrayList<Person>();
            count++;
            if (count <= 1)
            {

                selectList.addAll(listSecretFriend);
                selectedList.addAll(listSecretFriend);
                listSecretFriend.clear();
                Raffle(selectList,selectedList);
                ListView listSecret = (ListView) findViewById(R.id.listSecretFriend);
                final ArrayAdapter<Person> adapter = new ArrayAdapter<Person>(this, R.layout.support_simple_spinner_dropdown_item, listSecretFriend);
                listSecret.setAdapter(adapter);
                adapter.notifyDataSetChanged();
                Message(this.getString(R.string.successRaffle));

            }
            else
            {
                Message(this.getString(R.string.messageAfterRaffleOneMoreTime));
            }

        }
        else
        {
            Message(this.getString(R.string.emptyList));

        }
    }


}