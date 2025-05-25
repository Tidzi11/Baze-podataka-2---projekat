package rs.ac.uns.ftn.db.jdbc.exam.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import rs.ac.uns.ftn.db.jdbc.exam.dao.AstronomDAO;
import rs.ac.uns.ftn.db.jdbc.exam.dao.impl.AstronomDAOImpl;
import rs.ac.uns.ftn.db.jdbc.exam.model.Astronom;

public class AstronomService {
	
	private static final AstronomDAO AstronomDAO = new AstronomDAOImpl();

	public static ArrayList<Astronom> getAll() throws SQLException {
		return (ArrayList<Astronom>) AstronomDAO.findAll();
	}

	public static Astronom getById(int id) throws SQLException {
		return AstronomDAO.findById(id);
	}

	public boolean existsById(int id) throws SQLException {
		return AstronomDAO.existsById(id);
	}

	public boolean save(Astronom p) throws SQLException {
		return AstronomDAO.save(p);
	}

	public boolean deleteById(int id) throws SQLException {
		return AstronomDAO.deleteById(id);
	}

	public int saveAll(List<Astronom> pozoristeList) throws SQLException {
		return AstronomDAO.saveAll(pozoristeList);
	}
}
