package rs.ac.uns.ftn.db.jdbc.exam.dao;

import java.sql.SQLException;
import java.util.List;

import rs.ac.uns.ftn.db.jdbc.exam.dto.TeleskopIzvestajDTO;
import rs.ac.uns.ftn.db.jdbc.exam.model.Teleskop;

public interface TeleskopDAO extends CRUDDao<Teleskop, Integer>  {

	public List<String> getBrojTeleskopaPoOpservatorijumu();
	public List<TeleskopIzvestajDTO> fetchTeleskopIzvestaj();
}
