package rs.ac.uns.ftn.db.jdbc.exam.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import rs.ac.uns.ftn.db.jdbc.exam.dao.OpservatorijumDAO;
import rs.ac.uns.ftn.db.jdbc.exam.model.Opservatorijum;
import rs.ac.uns.ftn.db.jdbc.exam.model.Opservatorijum;
import rs.ac.uns.ftn.db.jdbc.exam.connection.ConnectionUtil_HikariCP;

public class OpservatorijumDAOImpl implements OpservatorijumDAO {

	@Override
	public int count() throws SQLException {
		String query = "select count(*) from opservatorijum";

		try (Connection connection = ConnectionUtil_HikariCP.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(query);
				ResultSet resultSet = preparedStatement.executeQuery()) {
			if (resultSet.next()) {
				return resultSet.getInt(1);
			} else {
				return -1;
			}
		}
	}

	@Override
	public boolean delete(Opservatorijum entity) throws SQLException {
		return deleteById(entity.getIdops());
	}

	@Override
	public int deleteAll() throws SQLException {
		String query = "delete from opservatorijum";

		try (Connection connection = ConnectionUtil_HikariCP.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(query);) {
			int rowsAfffected = preparedStatement.executeUpdate();
			return rowsAfffected;
		}
	}

	@Override
	public boolean deleteById(Integer id) throws SQLException {
		String query = "delete from opservatorijum where idops=?";

		try (Connection connection = ConnectionUtil_HikariCP.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(query);) {
			preparedStatement.setInt(1, id);
			int rowsAffected = preparedStatement.executeUpdate();
			return rowsAffected == 1;
		}
	}

	@Override
	public boolean existsById(Integer id) throws SQLException {
		try (Connection connection = ConnectionUtil_HikariCP.getConnection()) {
			return existsByIdTransactional(connection, id);
		}
	}

	private boolean existsByIdTransactional(Connection connection, Integer id) throws SQLException {
		String query = "select * from opservatorijum where idops=?";
		try (PreparedStatement preparedStatement = connection.prepareStatement(query);) {
			preparedStatement.setInt(1, id);
			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				return resultSet.isBeforeFirst();
			}
		}
	}

	@Override
	public Iterable<Opservatorijum> findAll() throws SQLException {
		String query = "select idops, naz, lokacija_idlok, vlas, dat, vis  from opservatorijum";
		List<Opservatorijum> opservatorijumList = new ArrayList<Opservatorijum>();
		Connection connection = ConnectionUtil_HikariCP.getConnection();
		try (PreparedStatement preparedStatement = connection.prepareStatement(query);
				ResultSet resultSet = preparedStatement.executeQuery()) {

			while (resultSet.next()) {
				Opservatorijum opservatorijum = new Opservatorijum(resultSet.getInt(1), resultSet.getString(2), resultSet.getInt(3),
						resultSet.getString(4), resultSet.getDate(5), resultSet.getFloat(6));
				opservatorijumList.add(opservatorijum);
			}

		}
		return opservatorijumList;
	}

	@Override
	public Iterable<Opservatorijum> findAllById(Iterable<Integer> ids) throws SQLException {
		List<Opservatorijum> OpservatorijumList = new ArrayList<>();

		StringBuilder stringBuilder = new StringBuilder();

		String queryBegin = "select idops, naz, lokacija_idlok, vlas, dat, vis  from opservatorijum where idops in(";
		stringBuilder.append(queryBegin);

		for (@SuppressWarnings("unused")
		Integer id : ids) {
			stringBuilder.append("?,");
		}

		stringBuilder.deleteCharAt(stringBuilder.length() - 1); // delete last ','
		stringBuilder.append(")");

		try (Connection connection = ConnectionUtil_HikariCP.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(stringBuilder.toString());) {

			int i = 0;
			for (Integer id : ids) {
				preparedStatement.setInt(++i, id);
			}

			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				while (resultSet.next()) {
					OpservatorijumList.add(new Opservatorijum(resultSet.getInt(1), resultSet.getString(2), resultSet.getInt(3),
							resultSet.getString(4), resultSet.getDate(5), resultSet.getFloat(6)));
				}
			}
		}

		return OpservatorijumList;
	}

	@Override
	public Opservatorijum findById(Integer id) throws SQLException {
		String query = "select idops, naz, lokacija_idlok, vlas, dat, vis  from opservatorijum where idops = ?";
		Opservatorijum opservatorijum = null;

		try (Connection connection = ConnectionUtil_HikariCP.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(query);) {

			preparedStatement.setInt(1, id);

			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				if (resultSet.isBeforeFirst()) {
					resultSet.next();

					opservatorijum = new Opservatorijum(resultSet.getInt(1), resultSet.getString(2), resultSet.getInt(3),
							resultSet.getString(4), resultSet.getDate(5), resultSet.getFloat(6));
				}
			}
		}

		return opservatorijum;
	}

	@Override
	public boolean save(Opservatorijum entity) throws SQLException {
		try (Connection connection = ConnectionUtil_HikariCP.getConnection()) {
			return saveTransactional(connection, entity);
		}
	}

	@Override
	public int saveAll(Iterable<Opservatorijum> entities) throws SQLException {
		int rowsSaved = 0;
		
		try (Connection connection = ConnectionUtil_HikariCP.getConnection()) {
			connection.setAutoCommit(false); // transaction start

			// insert or update every Opservatorijum
			for (Opservatorijum entity : entities) {
				boolean success = saveTransactional(connection, entity); // changes are visible only to current connection
				if (success) rowsSaved++;
			}

			connection.commit(); // transaction ends successfully, changes are now visible to other connections as well
		}
		
		return rowsSaved;
	}
	
	// used by save and saveAll
		private boolean saveTransactional(Connection connection, Opservatorijum entity) throws SQLException {
			// id_th intentionally in the last place, so that the order between commands remains the same
			String insertCommand = "insert into opservatorijum (naz, lokacija_idlok, vlas, dat, vis, idops) values (?, ? , ?, ?,?,?)";
			String updateCommand = "update opservatorijum set naz=?, lokacija_idlok=?, vlas=?, dat=?, vis=? where idops=?";

			try (PreparedStatement preparedStatement = connection.prepareStatement(
					existsByIdTransactional(connection, entity.getIdops()) ? updateCommand : insertCommand)) {
				int i = 1;
				preparedStatement.setString(i++, entity.getNaz());
				preparedStatement.setInt(i++, entity.getLokacija_idlok());
				preparedStatement.setInt(i++, entity.getLokacija_idlok());
				preparedStatement.setString(i++, entity.getVlas());
				preparedStatement.setDate(i++, entity.getDat());
				preparedStatement.setFloat(i++, entity.getVis());
				preparedStatement.setInt(i++, entity.getIdops());
				int rowsAffected = preparedStatement.executeUpdate();
				return rowsAffected == 1;
			}
		}

}
