package ba.unsa.etf.rpr;

public class Grad {
    private String nazivGrada;
    private Drzava drzava;
    private int brojStanovnika;

    public Grad() {
    }

    public Grad(String nazivGrada, Drzava drzava, int brojStanovnika) {
        setNaziv(nazivGrada);
        setDrzava(drzava);
        setBrojStanovnika(brojStanovnika);
    }

    public String getNaziv() {
        return nazivGrada;
    }

    public void setNaziv(String nazivGrada) {
        this.nazivGrada = nazivGrada;
    }

    public Drzava getDrzava() {
        return drzava;
    }

    public void setDrzava(Drzava drzava) {
        this.drzava = drzava;
    }

    public int getBrojStanovnika() {
        return brojStanovnika;
    }

    public void setBrojStanovnika(int brojStanovnika) {
        this.brojStanovnika = brojStanovnika;
    }

    @Override
    public String toString() {
        String ispis = "";
        ispis += getNaziv() + " (" + getDrzava().getNaziv() + ") - " + getBrojStanovnika() + "\n";
        return ispis;
    }
}
