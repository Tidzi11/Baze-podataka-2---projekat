package rs.ac.uns.ftn.db.jdbc.exam.model;

import java.sql.Date;
import java.util.Objects;

public class Teleskop {
	
	private int tel_idtel;
	private String tel_naz;
	private String tel_type;
	private float tel_prec;
	private float tel_kvalsl;
	private float tel_dmt;
	private float tel_rezl;
	
	public static String getFormattedHeader() {
		return String.format("%-6s %-37.37s %-30.30s %-30.30s %-30.30s %-30.30s %-30.30s", 
				"ID", "NAZIV", "TIP", "PRECNIK", "KVAL. SL.", "DOMET", "REZOLUCIJA");
	}
	

	public Teleskop(int tel_idtel, String tel_naz, String tel_type, float tel_prec, float tel_kvalsl, float tel_dmt,
			float tel_rezl) {
		super();
		this.tel_idtel = tel_idtel;
		this.tel_naz = tel_naz;
		this.tel_type = tel_type;
		this.tel_prec = tel_prec;
		this.tel_kvalsl = tel_kvalsl;
		this.tel_dmt = tel_dmt;
		this.tel_rezl = tel_rezl;
	}

	public Teleskop() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public int hashCode() {
		return Objects.hash(tel_dmt, tel_idtel, tel_kvalsl, tel_naz, tel_prec, tel_rezl, tel_type);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Teleskop other = (Teleskop) obj;
		return Float.floatToIntBits(tel_dmt) == Float.floatToIntBits(other.tel_dmt) && tel_idtel == other.tel_idtel
				&& Float.floatToIntBits(tel_kvalsl) == Float.floatToIntBits(other.tel_kvalsl)
				&& Objects.equals(tel_naz, other.tel_naz)
				&& Float.floatToIntBits(tel_prec) == Float.floatToIntBits(other.tel_prec)
				&& Float.floatToIntBits(tel_rezl) == Float.floatToIntBits(other.tel_rezl)
				&& Objects.equals(tel_type, other.tel_type);
	}

	public float getTel_prec() {
		return tel_prec;
	}

	public void setTel_prec(float tel_prec) {
		this.tel_prec = tel_prec;
	}

	public float getTel_kvalsl() {
		return tel_kvalsl;
	}

	public void setTel_kvalsl(float tel_kvalsl) {
		this.tel_kvalsl = tel_kvalsl;
	}

	public float getTel_dmt() {
		return tel_dmt;
	}

	public void setTel_dmt(float tel_dmt) {
		this.tel_dmt = tel_dmt;
	}

	public float getTel_rezl() {
		return tel_rezl;
	}

	public void setTel_rezl(float tel_rezl) {
		this.tel_rezl = tel_rezl;
	}

	public int getTel_idtel() {
		return tel_idtel;
	}

	public void setTel_idtel(int tel_idtel) {
		this.tel_idtel = tel_idtel;
	}

	public String getTel_naz() {
		return tel_naz;
	}

	public void setTel_naz(String tel_naz) {
		this.tel_naz = tel_naz;
	}

	public String getTel_type() {
		return tel_type;
	}

	public void setTel_type(String tel_type) {
		this.tel_type = tel_type;
	}


	@Override
	public String toString() {
		return "Teleskop [id=" + tel_idtel + ", naziv=" + tel_naz + ", tip=" + tel_type + ", precnik="
				+ tel_prec + ", kvalitet slike=" + tel_kvalsl + ", domet=" + tel_dmt + ", rezolucija=" + tel_rezl + "]";
	}

	

	
	
	
	
}
