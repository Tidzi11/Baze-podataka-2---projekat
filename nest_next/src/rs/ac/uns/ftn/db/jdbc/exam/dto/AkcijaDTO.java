package rs.ac.uns.ftn.db.jdbc.exam.dto;

public class AkcijaDTO {
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
	
	public AkcijaDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public AkcijaDTO(int idakc, String naz, String teh, float rez, String mer, int posmatranje_idpos,
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
		return "Akcija [id:" + idakc + ", naziv:" + naz + ", tehnika:" + teh + ", rezultat:" + rez + ", merna jedinica:" + mer
				+ ", posmatranje:" + posmatranje_idpos + ", vremesnki uslovi:" + vremesnkiuslovi_idusl + "]";
	}
	
	
}
