package pl.projekt.pablo.kajet2;

public class Notatka {

    private long id;
    private String tytul;
    private String tresc;
    private String date;
    private String prior;

    public Notatka() {
        this.id = 0;
        this.tytul = null;
        this.tresc = null;
        this.prior = null;
        this.date = null;
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
    public Notatka(String tytul, String tresc, String date, String prior) {
        this.tytul = tytul;
        this.tresc = tresc;
        this.date = date;
        this.prior = prior;
    }
    public Notatka(long id, String tytul, String tresc, String date) {
        this.tytul = tytul;
        this.tresc = tresc;
        this.date = date;
        this.id =id;
    }

    public Notatka(long id, String tytul, String tresc, String date, String prior) {
        this.tytul = tytul;
        this.tresc = tresc;
        this.date = date;
        this.id = id;
        this.prior = prior;
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

    public String getPrior() {
        return prior;
    }

    public void setPrior(String prior) {
        this.prior = prior;
    }

    @Override
    public String toString() {
        return tytul;
    }
}
