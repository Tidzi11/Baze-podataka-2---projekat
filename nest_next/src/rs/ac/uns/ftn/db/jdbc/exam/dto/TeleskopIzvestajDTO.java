package rs.ac.uns.ftn.db.jdbc.exam.dto;

public class TeleskopIzvestajDTO {
    @Override
	public String toString() {
    	return "Izvestaj [opservatorijum:" + opservatorijumNaziv + ", teleskop:" + teleskopNaziv + ", broj posmatranja:" + brojPosmatranja + ", prosecno trajanje:" + prosecnoTrajanje + "]";
    	//return "" + opservatorijumNaziv + " " + teleskopNaziv + " " + brojPosmatranja + " " + prosecnoTrajanje + "";
    }
	private String opservatorijumNaziv;
    private String teleskopNaziv;
    private int brojPosmatranja;
    private double prosecnoTrajanje;
    	
    public static String getFormattedHeader() {
		return String.format("%-25s %-20s %-20s %-20s", 
				"OPSERVATORIJUM", "TELESKOP", "BROJ POSMATRANJA", "PROSECNO TRAJANJE");
	}
    
	public String getOpservatorijumNaziv() {
		return opservatorijumNaziv;
	}
	public void setOpservatorijumNaziv(String opservatorijumNaziv) {
		this.opservatorijumNaziv = opservatorijumNaziv;
	}
	public String getTeleskopNaziv() {
		return teleskopNaziv;
	}
	public void setTeleskopNaziv(String teleskopNaziv) {
		this.teleskopNaziv = teleskopNaziv;
	}
	public int getBrojPosmatranja() {
		return brojPosmatranja;
	}
	public void setBrojPosmatranja(int brojPosmatranja) {
		this.brojPosmatranja = brojPosmatranja;
	}
	public double getProsecnoTrajanje() {
		return prosecnoTrajanje;
	}
	public void setProsecnoTrajanje(double prosecnoTrajanje) {
		this.prosecnoTrajanje = prosecnoTrajanje;
	}

   
}
