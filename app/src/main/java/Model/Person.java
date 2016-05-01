package Model;

/**
 * Created by L on 24/04/2016.
 */
public class Person {


    private String nome;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Person)) return false;

        Person person = (Person) o;

        if (getNome() != null ? !getNome().equals(person.getNome()) : person.getNome() != null)
            return false;
        return getAmigoSecreto() != null ? getAmigoSecreto().equals(person.getAmigoSecreto()) : person.getAmigoSecreto() == null;

    }

    @Override
    public int hashCode() {
        int result = getNome() != null ? getNome().hashCode() : 0;
        result = 31 * result + (getAmigoSecreto() != null ? getAmigoSecreto().hashCode() : 0);
        return result;
    }

    private Person amigoSecreto;

    public Person(String nome) {
        this.nome = nome;
    }

    public Person() {

    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Person getAmigoSecreto() {
        return amigoSecreto;
    }

    public void setAmigoSecreto(Person amigoSecreto) {
        this.amigoSecreto = amigoSecreto;
    }

    @Override
    public String toString() {

        return this.nome;
    }

}
