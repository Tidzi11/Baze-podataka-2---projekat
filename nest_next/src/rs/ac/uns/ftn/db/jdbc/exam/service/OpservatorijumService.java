package rs.ac.uns.ftn.db.jdbc.exam.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import rs.ac.uns.ftn.db.jdbc.exam.dao.OpservatorijumDAO;
import rs.ac.uns.ftn.db.jdbc.exam.dao.impl.OpservatorijumDAOImpl;
import rs.ac.uns.ftn.db.jdbc.exam.model.Opservatorijum;

public class OpservatorijumService {
	private static final OpservatorijumDAO OpservatorijumDAO = new OpservatorijumDAOImpl();

	public ArrayList<Opservatorijum> getAll() throws SQLException {
		return (ArrayList<Opservatorijum>) OpservatorijumDAO.findAll();
	}

	public Opservatorijum getById(int id) throws SQLException {
		return OpservatorijumDAO.findById(id);
	}

	public boolean existsById(int id) throws SQLException {
		return OpservatorijumDAO.existsById(id);
	}

	public boolean save(Opservatorijum p) throws SQLException {
		return OpservatorijumDAO.save(p);
	}

	public boolean deleteById(int id) throws SQLException {
		return OpservatorijumDAO.deleteById(id);
	}

	public int saveAll(List<Opservatorijum> pozoristeList) throws SQLException {
		return OpservatorijumDAO.saveAll(pozoristeList);
	}
}
