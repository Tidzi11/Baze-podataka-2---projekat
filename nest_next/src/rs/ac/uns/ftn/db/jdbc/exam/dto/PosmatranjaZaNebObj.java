package rs.ac.uns.ftn.db.jdbc.exam.dto;

import java.util.List;

import rs.ac.uns.ftn.db.jdbc.exam.model.NebeskiObjekat;
import rs.ac.uns.ftn.db.jdbc.exam.model.Posmatranje;

public class PosmatranjaZaNebObj {
	@Override
	public String toString() {
		return "Nebeski objekat:" + nebeskiObjekat + ", Posmatranja:" + posmatranja + "]";
	}
	private NebeskiObjekat nebeskiObjekat;
	private List<Posmatranje> posmatranja;
	
	public NebeskiObjekat getNebeskiObjekat() {
		return nebeskiObjekat;
	}
	public void setNebeskiObjekat(NebeskiObjekat nebeskiObjekat) {
		this.nebeskiObjekat = nebeskiObjekat;
	}
	public List<Posmatranje> getPosmatranja() {
		return posmatranja;
	}
	public void setPosmatranja(List<Posmatranje> posmatranja) {
		this.posmatranja = posmatranja;
	}
	
	
}
