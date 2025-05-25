package rs.ac.uns.ftn.db.jdbc.exam.model;

import java.util.Objects;

public class Akcija {
	
	private int idakc;
	private String naz;
	private String teh;
	private float rez;
	private String mer;
	private int posmatranje_idpos;
	private int vremesnkiuslovi_idusl;

	public static String getFormattedHeader() {
		return String.format("%-10s %-20s %-20s %-16s %-13s %-10s %-10s", 
				"ID", "NAZIV", "TEHNIKA", "REZULTAT",
				"JEDINICA MERE", "ID POSMATRANNJA", "ID VREM. USL.");
	}
	
	public Akcija() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Akcija(int idakc, String naz, String teh, float rez, String mer, int posmatranje_idpos,
			int vremesnkiuslovi_idusl) {
		super();
		this.idakc = idakc;
		this.naz = naz;
		this.teh = teh;
		this.rez = rez;
		this.mer = mer;
		this.posmatranje_idpos = posmatranje_idpos;
		this.vremesnkiuslovi_idusl = vremesnkiuslovi_idusl;
	}

	@Override
	public int hashCode() {
		return Objects.hash(idakc, mer, naz, posmatranje_idpos, rez, teh, vremesnkiuslovi_idusl);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Akcija other = (Akcija) obj;
		return idakc == other.idakc && Objects.equals(mer, other.mer) && Objects.equals(naz, other.naz)
				&& posmatranje_idpos == other.posmatranje_idpos
				&& Float.floatToIntBits(rez) == Float.floatToIntBits(other.rez) && Objects.equals(teh, other.teh)
				&& vremesnkiuslovi_idusl == other.vremesnkiuslovi_idusl;
	}

	public int getIdakc() {
		return idakc;
	}

	public void setIdakc(int idakc) {
		this.idakc = idakc;
	}

	public String getNaz() {
		return naz;
	}

	public void setNaz(String naz) {
		this.naz = naz;
	}

	public String getTeh() {
		return teh;
	}

	public void setTeh(String teh) {
		this.teh = teh;
	}

	public float getRez() {
		return rez;
	}

	public void setRez(float rez) {
		this.rez = rez;
	}

	public String getMer() {
		return mer;
	}

	public void setMer(String mer) {
		this.mer = mer;
	}

	public int getPosmatranje_idpos() {
		return posmatranje_idpos;
	}

	public void setPosmatranje_idpos(int posmatranje_idpos) {
		this.posmatranje_idpos = posmatranje_idpos;
	}

	public int getVremesnkiuslovi_idusl() {
		return vremesnkiuslovi_idusl;
	}

	public void setVremesnkiuslovi_idusl(int vremesnkiuslovi_idusl) {
		this.vremesnkiuslovi_idusl = vremesnkiuslovi_idusl;
	}

	@Override
	public String toString() {
		return "Akcija [id=" + idakc + ", naziv=" + naz + ", tehnika=" + teh + ", rezultat=" + rez + ", merna jedinica=" + mer
				+ ", id posmatranja=" + posmatranje_idpos + ", id vremenskih uslova=" + vremesnkiuslovi_idusl + "]";
	}
	
	
}
