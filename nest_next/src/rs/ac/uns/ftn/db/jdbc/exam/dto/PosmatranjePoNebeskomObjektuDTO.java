package rs.ac.uns.ftn.db.jdbc.exam.dto;

public class PosmatranjePoNebeskomObjektuDTO {

    private String opservatorijumNaziv;
    private String nebeskiObjekatNaziv;
    private int brojPosmatranja;

    public PosmatranjePoNebeskomObjektuDTO(String opservatorijumNaziv, String nebeskiObjekatNaziv, int brojPosmatranja) {
        this.opservatorijumNaziv = opservatorijumNaziv;
        this.nebeskiObjekatNaziv = nebeskiObjekatNaziv;
        this.brojPosmatranja = brojPosmatranja;
    }

    // Getters and setters
    public String getOpservatorijumNaziv() {
        return opservatorijumNaziv;
    }

    public void setOpservatorijumNaziv(String opservatorijumNaziv) {
        this.opservatorijumNaziv = opservatorijumNaziv;
    }

    public String getNebeskiObjekatNaziv() {
        return nebeskiObjekatNaziv;
    }

    public void setNebeskiObjekatNaziv(String nebeskiObjekatNaziv) {
        this.nebeskiObjekatNaziv = nebeskiObjekatNaziv;
    }

    public int getBrojPosmatranja() {
        return brojPosmatranja;
    }

    public void setBrojPosmatranja(int brojPosmatranja) {
        this.brojPosmatranja = brojPosmatranja;
    }

    @Override
    public String toString() {
        return String.format("Opservatorijum: %s, Nebeski objekat: %s, Broj posmatranja: %d", 
                             opservatorijumNaziv, nebeskiObjekatNaziv, brojPosmatranja);
    }
}
