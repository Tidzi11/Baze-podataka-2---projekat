package rs.ac.uns.ftn.db.jdbc.exam.dao;


import java.sql.SQLException;
import java.util.List;

import rs.ac.uns.ftn.db.jdbc.exam.dto.PosmatranjePoNebeskomObjektuDTO;
import rs.ac.uns.ftn.db.jdbc.exam.model.Posmatranje;

public interface PosmatranjeDAO extends CRUDDao<Posmatranje, Integer>  {

	public List<String> getKompleksnoPosmatranje();

	public List<String> getBrojPosmatranjaPoAstronomima();

	public void dodajPosmatranjeSaTransakcijom(int i, int j, String string, String string2, String string3);

	public List<Posmatranje> findPosmatranjeByNebeskiObjekat(int idnebobj) throws SQLException;

	public List<PosmatranjePoNebeskomObjektuDTO> getPosmatranja() throws SQLException;

	
}
