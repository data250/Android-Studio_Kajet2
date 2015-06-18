package pl.projekt.pablo.kajet2;

public class Notatka {

    private int id;
    private String tytul;
    private String tresc;

    public Notatka() {
        this.id = 0;
        this.tytul = null;
        this.tresc = null;
    }
    public Notatka(int id, String tytul, String tresc) {
        this.id = id;
        this.tytul = tytul;
        this.tresc = tresc;
    }
    public Notatka(String tytul, String tresc) {
        this.tytul = tytul;
        this.tresc = tresc;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTytul() {
        return tytul;
    }

    public void setTytul(String tytul) {
        this.tytul = tytul;
    }

    public String getTresc() {
        return tresc;
    }

    public void setTresc(String tresc) {
        this.tresc = tresc;
    }

    @Override
    public String toString() {
        return tytul;
    }
}
