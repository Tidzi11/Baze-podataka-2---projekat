package rs.ac.uns.ftn.db.jdbc.exam.dao;

import java.sql.SQLException;
import java.util.List;

import rs.ac.uns.ftn.db.jdbc.exam.dto.AkcijaDTO;
import rs.ac.uns.ftn.db.jdbc.exam.model.Akcija;

public interface AkcijaDAO extends CRUDDao<Akcija, Integer> {

	public List<AkcijaDTO> deleteNullVremenskeUslove() throws SQLException;

}
