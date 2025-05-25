package rs.ac.uns.ftn.db.jdbc.exam.model;

import java.sql.Date;
import java.util.Objects;

public class Astronom {
	
	private int idast;
	private String ime;
	private String prz;
	private String spec;
	private Date god;
	private int isk;
	private String eml;
	private String tel;
	
	
	public Astronom() {
		super();
		// TODO Auto-generated constructor stub
	}

	public static String getFormattedHeader() {
		return String.format("%-6s %-10s %-15s %-15s %-15s %-15s %-15s %-15s", 
				"ID", "IME", "PREZIME", "SPECIJALIZACIJA", "GODINA RODJ.", "ISKUSTVO", "EMAIL", "TELEFON");
	}

	public Astronom(int idast, String ime, String prz, String spec, Date god, int isk, String eml, String tel) {
		super();
		this.idast = idast;
		this.ime = ime;
		this.prz = prz;
		this.spec = spec;
		this.god = god;
		this.isk = isk;
		this.eml = eml;
		this.tel = tel;
	}



	@Override
	public int hashCode() {
		return Objects.hash(eml, god, idast, ime, isk, prz, spec, tel);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Astronom other = (Astronom) obj;
		return Objects.equals(eml, other.eml) && Objects.equals(god, other.god) && idast == other.idast
				&& Objects.equals(ime, other.ime) && isk == other.isk && Objects.equals(prz, other.prz)
				&& Objects.equals(spec, other.spec) && Objects.equals(tel, other.tel);
	}

	public Date getGod() {
		return god;
	}

	public void setGod(Date god) {
		this.god = god;
	}

	public int getIdast() {
		return idast;
	}

	public void setIdast(int idast) {
		this.idast = idast;
	}

	public int getIsk() {
		return isk;
	}

	public void setIsk(int isk) {
		this.isk = isk;
	}

	public String getEml() {
		return eml;
	}

	public void setEml(String eml) {
		this.eml = eml;
	}

	public String getIme() {
		return ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public String getSpec() {
		return spec;
	}

	public void setSpec(String spec) {
		this.spec = spec;
	}

	public String getPrz() {
		return prz;
	}

	public void setPrz(String prz) {
		this.prz = prz;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	@Override
	public String toString() {
		return "Astronom [id=" + idast + ", ime=" + ime + ", przime=" + prz + ", specijalizacija=" + spec + ", godina rodjenja=" + god
				+ ", godine iskustva=" + isk + ", email=" + eml + ", telefon=" + tel + "]";
	}

	



	
	
	
}
