package rs.ac.uns.ftn.db.jdbc.exam.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import rs.ac.uns.ftn.db.jdbc.exam.dao.NebeskiObjekatDAO;
import rs.ac.uns.ftn.db.jdbc.exam.dto.PosmatranjePoNebeskomObjektuDTO;
import rs.ac.uns.ftn.db.jdbc.exam.model.NebeskiObjekat;
import rs.ac.uns.ftn.db.jdbc.exam.connection.ConnectionUtil_HikariCP;
import rs.ac.uns.ftn.db.jdbc.exam.model.NebeskiObjekat;

public class NebeskiObjekatDAOImpl implements NebeskiObjekatDAO {

	@Override
	public int count() throws SQLException {
		String query = "select count(*) from nebeskiobjekat";

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
	public boolean delete(NebeskiObjekat entity) throws SQLException {
		return deleteById(entity.getIdnebobj());
	}

	@Override
	public int deleteAll() throws SQLException {
		String query = "delete from nebeskiobjekat";

		try (Connection connection = ConnectionUtil_HikariCP.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(query);) {
			int rowsAfffected = preparedStatement.executeUpdate();
			return rowsAfffected;
		}
	}

	@Override
	public boolean deleteById(Integer id) throws SQLException {
		String query = "delete from nebeskiobjekat where idnebobj=?";

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
		String query = "select * from nebeskiobjekat where idnebobj=?";
		try (PreparedStatement preparedStatement = connection.prepareStatement(query);) {
			preparedStatement.setInt(1, id);
			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				return resultSet.isBeforeFirst();
			}
		}
	}
	
	@Override
	public Iterable<NebeskiObjekat> findAll() throws SQLException {
		String query = "select idnebobj, ime, tip, udalj, mag, ra, dec  from nebeskiobjekat";
		List<NebeskiObjekat> NebeskiObjekatList = new ArrayList<NebeskiObjekat>();
		Connection connection = ConnectionUtil_HikariCP.getConnection();
		try (PreparedStatement preparedStatement = connection.prepareStatement(query);
				ResultSet resultSet = preparedStatement.executeQuery()) {

			while (resultSet.next()) {
				NebeskiObjekat NebeskiObjekat = new NebeskiObjekat(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3),
						resultSet.getFloat(4), resultSet.getFloat(5), resultSet.getFloat(6), resultSet.getFloat(7));
				NebeskiObjekatList.add(NebeskiObjekat);
			}

		}
		return NebeskiObjekatList;
	}

	@Override
	public Iterable<NebeskiObjekat> findAllById(Iterable<Integer> ids) throws SQLException {
		List<NebeskiObjekat> NebeskiObjekatList = new ArrayList<>();

		StringBuilder stringBuilder = new StringBuilder();

		String queryBegin = "select idnebobj, ime, tip, udalj, mag, ra, dec  from nebeskiobjekat where idnebobj in(";
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
					NebeskiObjekatList.add(new NebeskiObjekat(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3),
							resultSet.getFloat(4), resultSet.getFloat(5), resultSet.getFloat(6), resultSet.getFloat(7)));
				}
			}
		}

		return NebeskiObjekatList;
	}

	@Override
	public NebeskiObjekat findById(Integer id) throws SQLException {
		String query = "select idnebobj, ime, tip, udalj, mag, ra, dec  from nebeskiobjekat where idnebobj = ?";
		NebeskiObjekat pozoriste = null;

		try (Connection connection = ConnectionUtil_HikariCP.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(query);) {

			preparedStatement.setInt(1, id);

			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				if (resultSet.isBeforeFirst()) {
					resultSet.next();

					pozoriste = new NebeskiObjekat(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3),
							resultSet.getFloat(4), resultSet.getFloat(5), resultSet.getFloat(6), resultSet.getFloat(7));
				}
			}
		}

		return pozoriste;
	}

	@Override
	public boolean save(NebeskiObjekat entity) throws SQLException {
		try (Connection connection = ConnectionUtil_HikariCP.getConnection()) {
			return saveTransactional(connection, entity);
		}
	}

	@Override
	public int saveAll(Iterable<NebeskiObjekat> entities) throws SQLException {
		int rowsSaved = 0;
		
		try (Connection connection = ConnectionUtil_HikariCP.getConnection()) {
			connection.setAutoCommit(false); // transaction start

			// insert or update every NebeskiObjekat
			for (NebeskiObjekat entity : entities) {
				boolean success = saveTransactional(connection, entity); // changes are visible only to current connection
				if (success) rowsSaved++;
			}

			connection.commit(); // transaction ends successfully, changes are now visible to other connections as well
		}
		
		return rowsSaved;
	}
	
	// used by save and saveAll
		private boolean saveTransactional(Connection connection, NebeskiObjekat entity) throws SQLException {
			// id_th intentionally in the last place, so that the order between commands remains the same
			String insertCommand = "insert into nebeskiobjekat (ime, tip, udalj, mag, ra, dec, idnebobj) values (?, ? , ?, ?,?)";
			String updateCommand = "update nebeskiobjekat set ime=?, tip=?, udalj=?, mag=?, ra=?, dec=?, where idnebobj=?";

			try (PreparedStatement preparedStatement = connection.prepareStatement(
					existsByIdTransactional(connection, entity.getIdnebobj()) ? updateCommand : insertCommand)) {
				int i = 1;
				preparedStatement.setString(i++, entity.getIme());
				preparedStatement.setString(i++, entity.getTip());
				preparedStatement.setFloat(i++, entity.getUdalj());
				preparedStatement.setFloat(i++, entity.getMag());
				preparedStatement.setFloat(i++, entity.getRa());
				preparedStatement.setFloat(i++, entity.getDec());
				preparedStatement.setInt(i++, entity.getIdnebobj());
				int rowsAffected = preparedStatement.executeUpdate();
				return rowsAffected == 1;
			}
		}

	
}
