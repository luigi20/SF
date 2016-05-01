package Model;

import java.util.ArrayList;

/**
 * Created by L on 24/04/2016.
 */
public class SecretFriend {

    private String nome;
    private ArrayList<Person> listaAmigoSecreto;


    public SecretFriend(String nome) {
        this.nome = nome;
        this.listaAmigoSecreto = new ArrayList<Person>();
    }


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public ArrayList<Person> getListaAmigoSecreto() {
        return listaAmigoSecreto;
    }

    public void setListaAmigoSecreto(ArrayList<Person> listaAmigoSecreto) {
        this.listaAmigoSecreto = listaAmigoSecreto;
    }

    @Override
    public String toString()
    {
        return  nome;
    }
}
