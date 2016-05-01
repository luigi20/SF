package com.example.l.secretfriend.Controller.Controller;

/**
 * Created by L on 24/04/2016.
 */


import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

import com.example.l.secretfriend.R;

import Model.Person;

public class TGroup extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener {

    private ArrayList<Person> listSecretFriend = new ArrayList<Person>();
    private int count = 0;
    private Person personClick;//atributo da classe.

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
            case R.id.Sobre:
                //    Toast.makeText(this, "Item: " + item.getItemId(), Toast.LENGTH_SHORT).show();
                break;

            case R.id.Ajuda:
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

        if (count > 0) {


            exemplo_alerta(position);


        } else {
            Message(this.getString(R.string.listSecretFriendBeforeRaffle));
        }

    }

    private void exemplo_alerta(int position) {

        ListView listSecret = (ListView) findViewById(R.id.listSecretFriend);

        final ArrayAdapter<Person> adapter = new ArrayAdapter<Person>(this, R.layout.support_simple_spinner_dropdown_item, listSecretFriend);
        listSecret.setAdapter(adapter);
        final Person person = adapter.getItem(position);
        personClick = person;
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(TGroup.this);
        alertDialog.setTitle(getString(R.string.password));
        alertDialog.setMessage(personClick.getNome().toUpperCase() + " " + getString(R.string.addPassword));
        final EditText input = new EditText(TGroup.this);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);
        input.setLayoutParams(lp);
        input.requestFocus();
        alertDialog.setView(input);
        alertDialog.setIcon(R.drawable.ic_secretfriend);

        alertDialog.setPositiveButton(this.getString(R.string.ok),
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        String password = "";
                        password = input.getText().toString();
                        if (!password.toString().trim().isEmpty()) {
                            if (password.equals(personClick.getSenha())) {
                                Message(getApplicationContext().getString(R.string.yourSecretFriend1) + " " + person.getNome() + " " +
                                        (getApplicationContext().getString(R.string.yourSecretFriend2) + " " + person.getAmigoSecreto().getNome()));

                            } else {
                                Message(getApplicationContext().getString(R.string.passwordmessageerror));
                            }
                        }
                    }
                });

        alertDialog.setNegativeButton(this.getString(R.string.cancel),
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

        alertDialog.show();
    }


    public void AddPerson() {

        ListView listSecret = (ListView) findViewById(R.id.listSecretFriend);
        // definindo a implementação ArrayAdapter como ListAdapter da ListView
        final ArrayAdapter<Person> adapter = new ArrayAdapter<Person>(this, R.layout.support_simple_spinner_dropdown_item, listSecretFriend);
        listSecret.setAdapter(adapter);
        // recuperando o texto digitado pelo usuario
        EditText edtPerson = (EditText) findViewById(R.id.edtPerson);
        EditText edtPassword = (EditText) findViewById(R.id.edtPassword);
        Person secretFriend = new Person(edtPerson.getText().toString().toUpperCase());
        secretFriend.setSenha(edtPassword.getText().toString());
        // caso o texto for preenchido, adiciona na lista e atualiza o adapter
        // caso contrario exibe uma mensagem solicitando ao usuário que digite uma série
        if ((!edtPerson.getText().toString().trim().isEmpty()) && (!edtPassword.getText().toString().trim().isEmpty())) {
            edtPerson.setText("");
            edtPassword.setText("");
            edtPerson.findFocus();
            listSecretFriend.add(secretFriend);
            adapter.notifyDataSetChanged();
        } else {
            Message(this.getString(R.string.warningMessageAddPerson));
        }
    }

    @Override
    public void onClick(View v) {

        if (count <= 0) {
            this.AddPerson();
        } else {
            listSecretFriend.clear();
            this.count = 0;
            this.AddPerson();
        }

    }

    public void Raffle(ArrayList<Person> selectList, ArrayList<Person> selectedList) {
        for (int i = 0; i < selectList.size(); i++) {
            for (int j = 0; j < selectedList.size(); j++) {
                if (selectedList.size() % 2 != 0) {
                    Random random = new Random();
                    int withdrawn = random.nextInt(selectedList.size()) + 1;
                    selectedList.remove(withdrawn - 1);
                    selectList.remove(withdrawn - 1);
                }
                while (selectedList.size() != 0) {
                    boolean equal = true;
                    while (equal == true) {

                        //int itens = listaSorteado.size();
                        Random aleatorio = new Random();
                        int raffle = aleatorio.nextInt(selectedList.size()) + 1;
                        if (selectList.get(i).equals(selectedList.get(raffle - 1)) != true) {
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


        if ((listSecretFriend.size() != 0) && (listSecretFriend.size() != 1)) {
            ArrayList<Person> selectedList = new ArrayList<Person>();
            ArrayList<Person> selectList = new ArrayList<Person>();
            count++;
            if (count <= 1) {

                selectList.addAll(listSecretFriend);
                selectedList.addAll(listSecretFriend);
                listSecretFriend.clear();
                Raffle(selectList, selectedList);
                ListView listSecret = (ListView) findViewById(R.id.listSecretFriend);
                final ArrayAdapter<Person> adapter = new ArrayAdapter<Person>(this, R.layout.support_simple_spinner_dropdown_item, listSecretFriend);
                listSecret.setAdapter(adapter);
                adapter.notifyDataSetChanged();
                Message(this.getString(R.string.successRaffle));
                EditText edtPerson = (EditText) findViewById(R.id.edtPerson);
                EditText edtPassword = (EditText) findViewById(R.id.edtPassword);
                ImageView imgAdd = (ImageView) findViewById(R.id.bt_add);
                edtPerson.setVisibility(View.INVISIBLE);
                imgAdd.setVisibility(View.INVISIBLE);
                edtPassword.setVisibility(View.INVISIBLE);
            } else {
                Message(this.getString(R.string.messageAfterRaffleOneMoreTime));
            }

        } else {
            Message(this.getString(R.string.emptyList));

        }
    }


}