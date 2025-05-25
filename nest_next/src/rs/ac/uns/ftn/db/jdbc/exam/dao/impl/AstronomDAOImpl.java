package rs.ac.uns.ftn.db.jdbc.exam.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import rs.ac.uns.ftn.db.jdbc.exam.dao.AstronomDAO;
import rs.ac.uns.ftn.db.jdbc.exam.model.Astronom;
import rs.ac.uns.ftn.db.jdbc.exam.connection.ConnectionUtil_HikariCP;
import rs.ac.uns.ftn.db.jdbc.exam.model.Astronom;


public class AstronomDAOImpl implements AstronomDAO {

	@Override
	public int count() throws SQLException {
		String query = "select count(*) from astronom";

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
	public boolean delete(Astronom entity) throws SQLException {
		return deleteById(entity.getIdast());
	}

	@Override
	public int deleteAll() throws SQLException {
		String query = "delete from astronom";

		try (Connection connection = ConnectionUtil_HikariCP.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(query);) {
			int rowsAfffected = preparedStatement.executeUpdate();
			return rowsAfffected;
		}
	}

	@Override
	public boolean deleteById(Integer id) throws SQLException {
		String query = "delete from astronom where idast=?";

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
		String query = "select * from astronom where idast=?";
		try (PreparedStatement preparedStatement = connection.prepareStatement(query);) {
			preparedStatement.setInt(1, id);
			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				return resultSet.isBeforeFirst();
			}
		}
	}

	@Override
	public Iterable<Astronom> findAll() throws SQLException {
		String query = "select idast, ime, prz, spec, god, isk, eml, tel  from astronom";
		List<Astronom> AstronomList = new ArrayList<Astronom>();
		Connection connection = ConnectionUtil_HikariCP.getConnection();
		try (PreparedStatement preparedStatement = connection.prepareStatement(query);
				ResultSet resultSet = preparedStatement.executeQuery()) {

			while (resultSet.next()) {
				Astronom Astronom = new Astronom(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3),
						resultSet.getString(4), resultSet.getDate(5), resultSet.getInt(6), resultSet.getString(7), resultSet.getString(8));
				AstronomList.add(Astronom);
			}

		}
		return AstronomList;
	}

	@Override
	public Iterable<Astronom> findAllById(Iterable<Integer> ids) throws SQLException {
		List<Astronom> AstronomList = new ArrayList<>();

		StringBuilder stringBuilder = new StringBuilder();

		String queryBegin = "select idast, ime, prz, spec, god, isk, eml, tel  from astronom where idast in(";
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
					AstronomList.add(new Astronom(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3),
							resultSet.getString(4), resultSet.getDate(5), resultSet.getInt(6), resultSet.getString(7), resultSet.getString(8)));
				}
			}
		}

		return AstronomList;
	}

	@Override
	public Astronom findById(Integer id) throws SQLException {
		String query = "select idast, ime, prz, spec, god, isk, eml, tel  from astronom where idast = ?";
		Astronom pozoriste = null;

		try (Connection connection = ConnectionUtil_HikariCP.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(query);) {

			preparedStatement.setInt(1, id);

			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				if (resultSet.isBeforeFirst()) {
					resultSet.next();

					pozoriste = new Astronom(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3),
							resultSet.getString(4), resultSet.getDate(5), resultSet.getInt(6), resultSet.getString(7), resultSet.getString(8));
				}
			}
		}

		return pozoriste;
	}

	@Override
	public boolean save(Astronom entity) throws SQLException {
		try (Connection connection = ConnectionUtil_HikariCP.getConnection()) {
			return saveTransactional(connection, entity);
		}
	}

	@Override
	public int saveAll(Iterable<Astronom> entities) throws SQLException {
		int rowsSaved = 0;
		
		try (Connection connection = ConnectionUtil_HikariCP.getConnection()) {
			connection.setAutoCommit(false); // transaction start

			// insert or update every Astronom
			for (Astronom entity : entities) {
				boolean success = saveTransactional(connection, entity); // changes are visible only to current connection
				if (success) rowsSaved++;
			}

			connection.commit(); // transaction ends successfully, changes are now visible to other connections as well
		}
		
		return rowsSaved;
	}
	
	// used by save and saveAll
		private boolean saveTransactional(Connection connection, Astronom entity) throws SQLException {
			// id_th intentionally in the last place, so that the order between commands remains the same
			String insertCommand = "insert into astronom (ime, prz, spec, god, isk, eml, tel, idast) values (?, ? , ?, ?,?, ?,?, ?)";
			String updateCommand = "update astronom set ime=?, prz=?, spec=?, god=?, isk=?, eml=?, tel=? where idast=?";

			try (PreparedStatement preparedStatement = connection.prepareStatement(
					existsByIdTransactional(connection, entity.getIdast()) ? updateCommand : insertCommand)) {
				int i = 1;
				preparedStatement.setString(i++, entity.getIme());
				preparedStatement.setString(i++, entity.getPrz());
				preparedStatement.setString(i++, entity.getSpec());
				preparedStatement.setDate(i++, entity.getGod());
				preparedStatement.setInt(i++, entity.getIsk());
				preparedStatement.setString(i++, entity.getEml());
				preparedStatement.setString(i++, entity.getTel());
				preparedStatement.setInt(i++, entity.getIdast());
				int rowsAffected = preparedStatement.executeUpdate();
				return rowsAffected == 1;
			}
		}
}
