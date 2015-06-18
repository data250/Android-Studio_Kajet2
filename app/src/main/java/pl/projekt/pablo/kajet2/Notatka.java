package pl.projekt.pablo.kajet2;

public class Notatka {

    private long id;
    private String tytul;
    private String tresc;
    private String date;

    public Notatka() {
        this.id = 0;
        this.tytul = null;
        this.tresc = null;
    }
    public Notatka(long id, String tytul, String tresc) {
        this.id = id;
        this.tytul = tytul;
        this.tresc = tresc;
    }
    public Notatka(String tytul, String tresc) {
        this.tytul = tytul;
        this.tresc = tresc;
    }
    public Notatka(String tytul, String tresc, String date) {
        this.tytul = tytul;
        this.tresc = tresc;
        this.date = date;
    }
    public Notatka(long id, String tytul, String tresc, String date) {
        this.tytul = tytul;
        this.tresc = tresc;
        this.date = date;
        this.id =id;
    }


    public long getId() {
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return tytul;
    }
}
