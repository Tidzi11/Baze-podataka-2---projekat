package rs.ac.uns.ftn.db.jdbc.exam.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import rs.ac.uns.ftn.db.jdbc.exam.connection.ConnectionUtil_HikariCP;
import rs.ac.uns.ftn.db.jdbc.exam.dao.AkcijaDAO;
import rs.ac.uns.ftn.db.jdbc.exam.dto.AkcijaDTO;
import rs.ac.uns.ftn.db.jdbc.exam.model.Akcija;

public class AkcijaDAOImpl implements AkcijaDAO{

	@Override
	public int count() throws SQLException {
		String query = "select count(*) from akcija";

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
	public boolean delete(Akcija entity) throws SQLException {
		return deleteById(entity.getIdakc());
	}

	@Override
	public int deleteAll() throws SQLException {
		String query = "delete from akcija";

		try (Connection connection = ConnectionUtil_HikariCP.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(query);) {
			int rowsAfffected = preparedStatement.executeUpdate();
			return rowsAfffected;
		}
	}

	@Override
	public boolean deleteById(Integer id) throws SQLException {
		String query = "delete from akcija where idakc=?";

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
		String query = "select * from akcija where idakc=?";
		try (PreparedStatement preparedStatement = connection.prepareStatement(query);) {
			preparedStatement.setInt(1, id);
			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				return resultSet.isBeforeFirst();
			}
		}
	}
	
	@Override
	public Iterable<Akcija> findAll() throws SQLException {
		String query = "select idakc, naz, teh, rez, mer, posmatranje_idpos, vremesnkiuslovi_idusl  from akcija";
		List<Akcija> AkcijaList = new ArrayList<Akcija>();
		Connection connection = ConnectionUtil_HikariCP.getConnection();
		try (PreparedStatement preparedStatement = connection.prepareStatement(query);
				ResultSet resultSet = preparedStatement.executeQuery()) {

			while (resultSet.next()) {
				Akcija Akcija = new Akcija(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3),
						resultSet.getFloat(4), resultSet.getString(5), resultSet.getInt(6), resultSet.getInt(7));
				AkcijaList.add(Akcija);
			}

		}
		return AkcijaList;
	}

	@Override
	public Iterable<Akcija> findAllById(Iterable<Integer> ids) throws SQLException {
		List<Akcija> AkcijaList = new ArrayList<>();

		StringBuilder stringBuilder = new StringBuilder();

		String queryBegin = "select idakc, naz, teh, rez, mer, posmatranje_idpos, vremesnkiuslovi_idusl  from akcija where idakc in(";
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
					AkcijaList.add(new Akcija(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3),
							resultSet.getFloat(4), resultSet.getString(5), resultSet.getInt(6), resultSet.getInt(7)));
				}
			}
		}

		return AkcijaList;
	}

	@Override
	public Akcija findById(Integer id) throws SQLException {
		String query = "select idakc, naz, teh, rez, mer, posmatranje_idpos, vremesnkiuslovi_idusl  from akcija where idakc = ?";
		Akcija pozoriste = null;

		try (Connection connection = ConnectionUtil_HikariCP.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(query);) {

			preparedStatement.setInt(1, id);

			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				if (resultSet.isBeforeFirst()) {
					resultSet.next();

					pozoriste = new Akcija(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3),
							resultSet.getFloat(4), resultSet.getString(5), resultSet.getInt(6), resultSet.getInt(7));
				}
			}
		}

		return pozoriste;
	}

	@Override
	public boolean save(Akcija entity) throws SQLException {
		try (Connection connection = ConnectionUtil_HikariCP.getConnection()) {
			return saveTransactional(connection, entity);
		}
	}

	@Override
	public int saveAll(Iterable<Akcija> entities) throws SQLException {
		int rowsSaved = 0;
		
		try (Connection connection = ConnectionUtil_HikariCP.getConnection()) {
			connection.setAutoCommit(false); // transaction start

			// insert or update every Akcija
			for (Akcija entity : entities) {
				boolean success = saveTransactional(connection, entity); // changes are visible only to current connection
				if (success) rowsSaved++;
			}

			connection.commit(); // transaction ends successfully, changes are now visible to other connections as well
		}
		
		return rowsSaved;
	}

	// used by save and saveAll
		private boolean saveTransactional(Connection connection, Akcija entity) throws SQLException {
			// id_th intentionally in the last place, so that the order between commands remains the same
			String insertCommand = "insert into akcija (naz, teh, rez, mer, posmatranje_idpos, vremesnkiuslovi_idusl, idakc) values (?, ? , ?, ?,?)";
			String updateCommand = "update akcija set naz=?, teh=?, rez=?, mer=?, posmatranje_idpos=?, vremesnkiuslovi_idusl=?  where idakc=?";

			try (PreparedStatement preparedStatement = connection.prepareStatement(
					existsByIdTransactional(connection, entity.getIdakc()) ? updateCommand : insertCommand)) {
				int i = 1;
				preparedStatement.setString(i++, entity.getNaz());
				preparedStatement.
				setString(i++, entity.getTeh());
				preparedStatement.setFloat(i++, entity.getRez());
				preparedStatement.setString(i++, entity.getMer());
				preparedStatement.setInt(i++, entity.getPosmatranje_idpos());
				preparedStatement.setInt(i++, entity.getVremesnkiuslovi_idusl());
				int rowsAffected = preparedStatement.executeUpdate();
				return rowsAffected == 1;
			}
		}

		@Override
		public List<AkcijaDTO> deleteNullVremenskeUslove() throws SQLException {
		    List<AkcijaDTO> deletedActions = new ArrayList<>();
		    String query = "select idakc, naz, teh, rez, mer, posmatranje_idpos, vremesnkiuslovi_idusl from akcija where vremesnkiuslovi_idusl IS NULL";
		    
		    try (Connection connection = ConnectionUtil_HikariCP.getConnection();
		         PreparedStatement preparedStatement = connection.prepareStatement(query);
		         ResultSet resultSet = preparedStatement.executeQuery()) {

		        // Iterate through all actions where vremesnkiuslovi_idusl is NULL
		        while (resultSet.next()) {
		            int idakc = resultSet.getInt("idakc");
		            int posmatranjeId = resultSet.getInt("posmatranje_idpos");
		            
		            // Create DTO for this action
		            AkcijaDTO akcijaDTO = new AkcijaDTO(
		                idakc,
		                resultSet.getString("naz"),
		                resultSet.getString("teh"),
		                resultSet.getFloat("rez"),
		                resultSet.getString("mer"),
		                posmatranjeId,
		                resultSet.getInt("vremesnkiuslovi_idusl")
		            );

		            deletedActions.add(akcijaDTO);
		            
		            // Delete or modify associated Posmatranje (Observation) based on your needs
		            if (posmatranjeId != 0) {
		                // If you want to delete the associated Posmatranje
		                deletePosmatranjeById(posmatranjeId);

		                // Alternatively, modify the dates if you don't want to delete it
		                // modifyPosmatranjeDates(posmatranjeId);
		            }

		            // Finally, delete the action itself
		            deleteById(idakc);
		        }
		    }
		    
		    return deletedActions;
		}
		
		private void deletePosmatranjeById(int posmatranjeId) throws SQLException {
		    String query = "delete from posmatranje where idpos = ?";
		    try (Connection connection = ConnectionUtil_HikariCP.getConnection();
		         PreparedStatement preparedStatement = connection.prepareStatement(query)) {
		        preparedStatement.setInt(1, posmatranjeId);
		        preparedStatement.executeUpdate();
		    }
		}

		private void modifyPosmatranjeDates(int posmatranjeId) throws SQLException {
		    String query = "update posmatranje set poc = null, kraj = null where idpos = ?";
		    try (Connection connection = ConnectionUtil_HikariCP.getConnection();
		         PreparedStatement preparedStatement = connection.prepareStatement(query)) {
		        preparedStatement.setInt(1, posmatranjeId);
		        preparedStatement.executeUpdate();
		    }
		}
}
