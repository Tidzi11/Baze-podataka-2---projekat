package rs.ac.uns.ftn.db.jdbc.exam.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import rs.ac.uns.ftn.db.jdbc.exam.model.Teleskop;
import rs.ac.uns.ftn.db.jdbc.exam.dao.TeleskopDAO;
import rs.ac.uns.ftn.db.jdbc.exam.dao.impl.TeleskopDAOImpl;
import rs.ac.uns.ftn.db.jdbc.exam.model.Teleskop;

public class TeleskopService {

	private static final TeleskopDAO TeleskopDAO = new TeleskopDAOImpl();

	public ArrayList<Teleskop> getAll() throws SQLException {
		return (ArrayList<Teleskop>) TeleskopDAO.findAll();
	}

	public Teleskop getById(int id) throws SQLException {
		return TeleskopDAO.findById(id);
	}

	public boolean existsById(int id) throws SQLException {
		return TeleskopDAO.existsById(id);
	}

	public boolean save(Teleskop p) throws SQLException {
		return TeleskopDAO.save(p);
	}

	public boolean deleteById(int id) throws SQLException {
		return TeleskopDAO.deleteById(id);
	}

	public int saveAll(List<Teleskop> pozoristeList) throws SQLException {
		return TeleskopDAO.saveAll(pozoristeList);
	}

}
