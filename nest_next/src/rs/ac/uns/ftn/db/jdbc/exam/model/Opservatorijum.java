package rs.ac.uns.ftn.db.jdbc.exam.model;

import java.sql.Date;
import java.util.Objects;

public class Opservatorijum {

	private int idops;
	private String naz;
	private int lokacija_idlok;
	private String vlas;
	private Date dat;
	private float vis;

	
	public Opservatorijum() {
		super();
		// TODO Auto-generated constructor stub
	}


	


	public Opservatorijum(int idops, String naz, int lokacija_idlok, String vlas, Date dat, float vis) {
		super();
		this.idops = idops;
		this.naz = naz;
		this.lokacija_idlok = lokacija_idlok;
		this.vlas = vlas;
		this.dat = dat;
		this.vis = vis;
	}





	@Override
	public int hashCode() {
		return Objects.hash(dat, idops, lokacija_idlok, naz, vis, vlas);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Opservatorijum other = (Opservatorijum) obj;
		return Objects.equals(dat, other.dat) && idops == other.idops && lokacija_idlok == other.lokacija_idlok
				&& Objects.equals(naz, other.naz) && Float.floatToIntBits(vis) == Float.floatToIntBits(other.vis)
				&& Objects.equals(vlas, other.vlas);
	}


	public Date getDat() {
		return dat;
	}


	public void setDat(Date dat) {
		this.dat = dat;
	}


	public float getVis() {
		return vis;
	}


	public void setVis(float vis) {
		this.vis = vis;
	}


	public int getLokacija_idlok() {
		return lokacija_idlok;
	}


	public void setLokacija_idlok(int lokacija_idlok) {
		this.lokacija_idlok = lokacija_idlok;
	}


	public int getIdops() {
		return idops;
	}


	public void setIdops(int idops) {
		this.idops = idops;
	}


	public String getVlas() {
		return vlas;
	}


	public void setVlas(String vlas) {
		this.vlas = vlas;
	}


	public String getNaz() {
		return naz;
	}


	public void setNaz(String naz) {
		this.naz = naz;
	}

	@Override
	public String toString() {
		return "Opservatorijum [id=" + idops + ", naziv=" + naz + ", id lokacije=" + lokacija_idlok + ", vlasnik="
				+ vlas + ", datum osnivanja=" + dat + ", nadmorska visina=" + vis + "]";
	}





	public static String getFormattedHeader() {
		return String.format("%-6s %-37.37s %-30.30s %-30.30s %-30.30s %-30.30s", 
				"ID", "NAZIV", "LOKACIJA", "VLASNIK", "DATUM", "VISINA");
	}








	
	
	
}
