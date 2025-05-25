package rs.ac.uns.ftn.db.jdbc.exam.model;

import java.sql.Date;
import java.util.Objects;

public class Posmatranje {
	private int idpos;
	private Date poc;
	private Date kraj;
	private int ops_tel_teleskop_tel_idtel;
	private int ops_tel_opservatorijum_idops;
	private int nebeskiobjekat_idnebobj;
	
	public Posmatranje(int idpos, Date poc, Date kraj, int ops_tel_teleskop_tel_idtel, int ops_tel_opservatorijum_idops,
			int nebeskiobjekat_idnebobj) {
		
		this.idpos = idpos;
		this.poc = poc;
		this.kraj = kraj;
		this.ops_tel_teleskop_tel_idtel = ops_tel_teleskop_tel_idtel;
		this.ops_tel_opservatorijum_idops = ops_tel_opservatorijum_idops;
		this.nebeskiobjekat_idnebobj = nebeskiobjekat_idnebobj;
	}
	public static String getFormattedHeader() {
		return String.format("%-6s %-37.37s %-30.30s %-30.30s %-30.30s %-30.30s", 
				"ID", "POCETAK", "KRAJ", "ID TELESKOPA", "ID OPSERVATORIJUMA", "ID NEBESKOG OBJEKTA");
	}
	
	public Posmatranje() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public int hashCode() {
		return Objects.hash(idpos, kraj, nebeskiobjekat_idnebobj, ops_tel_opservatorijum_idops,
				ops_tel_teleskop_tel_idtel, poc);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Posmatranje other = (Posmatranje) obj;
		return idpos == other.idpos && Objects.equals(kraj, other.kraj)
				&& nebeskiobjekat_idnebobj == other.nebeskiobjekat_idnebobj
				&& ops_tel_opservatorijum_idops == other.ops_tel_opservatorijum_idops
				&& ops_tel_teleskop_tel_idtel == other.ops_tel_teleskop_tel_idtel && Objects.equals(poc, other.poc);
	}

	public int getIdpos() {
		return idpos;
	}

	public void setIdpos(int idpos) {
		this.idpos = idpos;
	}

	public Date getPoc() {
		return poc;
	}

	public void setPoc(Date poc) {
		this.poc = poc;
	}

	public Date getKraj() {
		return kraj;
	}

	public void setKraj(Date kraj) {
		this.kraj = kraj;
	}

	public int getOps_tel_teleskop_tel_idtel() {
		return ops_tel_teleskop_tel_idtel;
	}

	public void setOps_tel_teleskop_tel_idtel(int ops_tel_teleskop_tel_idtel) {
		this.ops_tel_teleskop_tel_idtel = ops_tel_teleskop_tel_idtel;
	}

	public int getOps_tel_opservatorijum_idops() {
		return ops_tel_opservatorijum_idops;
	}

	public void setOps_tel_opservatorijum_idops(int ops_tel_opservatorijum_idops) {
		this.ops_tel_opservatorijum_idops = ops_tel_opservatorijum_idops;
	}

	public int getNebeskiobjekat_idnebobj() {
		return nebeskiobjekat_idnebobj;
	}

	public void setNebeskiobjekat_idnebobj(int nebeskiobjekat_idnebobj) {
		this.nebeskiobjekat_idnebobj = nebeskiobjekat_idnebobj;
	}

	@Override
	public String toString() {
		return "Posmatranje [id=" + idpos + ", pocetak=" + poc + ", kraj=" + kraj + ", id teleskopa="
				+ ops_tel_teleskop_tel_idtel + ", id opservatorijuma=" + ops_tel_opservatorijum_idops
				+ ", id nebeskog objekta=" + nebeskiobjekat_idnebobj + "]";
	}
	
	
	
}
