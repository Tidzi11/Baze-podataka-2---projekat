package rs.ac.uns.ftn.db.jdbc.exam.model;

import java.util.Objects;

public class NebeskiObjekat {
	
	private int idnebobj;
	private String ime;
	private String tip;
	private float udalj;
	private float mag;
	private float ra;
	private float dec;

	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public NebeskiObjekat() {
		super();
	}

	public NebeskiObjekat(int idnebobj, String ime, String tip, float udalj, float mag, float ra, float dec) {
		this.idnebobj = idnebobj;
		this.ime = ime;
		this.tip = tip;
		this.udalj = udalj;
		this.mag = mag;
		this.ra = ra;
		this.dec = dec;
	}

	public static String getFormattedHeader() {
		return String.format("%-6s %-37.37s %-30.30s %-30.30s %-30.30s %-30.30s %-30.30s", 
				"ID", "IME", "TIP", "UDALJENOST", "MAGNITUDA", "RA", "DEC");
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(dec, idnebobj, ime, mag, ra, tip, udalj);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		NebeskiObjekat other = (NebeskiObjekat) obj;
		return Float.floatToIntBits(dec) == Float.floatToIntBits(other.dec) && idnebobj == other.idnebobj
				&& Objects.equals(ime, other.ime) && Float.floatToIntBits(mag) == Float.floatToIntBits(other.mag)
				&& Float.floatToIntBits(ra) == Float.floatToIntBits(other.ra) && Objects.equals(tip, other.tip)
				&& Float.floatToIntBits(udalj) == Float.floatToIntBits(other.udalj);
	}

	public int getIdnebobj() {
		return idnebobj;
	}

	public void setIdnebobj(int idnebobj) {
		this.idnebobj = idnebobj;
	}

	public String getIme() {
		return ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public String getTip() {
		return tip;
	}

	public void setTip(String tip) {
		this.tip = tip;
	}

	public float getUdalj() {
		return udalj;
	}

	public void setUdalj(float udalj) {
		this.udalj = udalj;
	}

	public float getMag() {
		return mag;
	}

	public void setMag(float mag) {
		this.mag = mag;
	}

	public float getRa() {
		return ra;
	}

	public void setRa(float ra) {
		this.ra = ra;
	}

	public float getDec() {
		return dec;
	}

	public void setDec(float dec) {
		this.dec = dec;
	}

	@Override
	public String toString() {
		return "NebeskiObjekat [id=" + idnebobj + ", naziv=" + ime + ", tip=" + tip + ", udaljenost=" + udalj + ", magnituda="
				+ mag + ", ra=" + ra + ", dec=" + dec + "]";
	}
	
	
	

}
