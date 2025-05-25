package rs.ac.uns.ftn.db.jdbc.exam.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import rs.ac.uns.ftn.db.jdbc.exam.dao.TeleskopDAO;
import rs.ac.uns.ftn.db.jdbc.exam.dto.TeleskopIzvestajDTO;
import rs.ac.uns.ftn.db.jdbc.exam.model.Teleskop;
import rs.ac.uns.ftn.db.jdbc.exam.connection.ConnectionUtil_HikariCP;
import rs.ac.uns.ftn.db.jdbc.exam.model.Teleskop;

public class TeleskopDAOImpl implements TeleskopDAO {

	@Override
	public int count() throws SQLException {
		String query = "select count(*) from teleskop";

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
	public boolean delete(Teleskop entity) throws SQLException {
		return deleteById(entity.getTel_idtel());
	}

	@Override
	public int deleteAll() throws SQLException {
		String query = "delete from teleskop";

		try (Connection connection = ConnectionUtil_HikariCP.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(query);) {
			int rowsAfffected = preparedStatement.executeUpdate();
			return rowsAfffected;
		}
	}

	@Override
	public boolean deleteById(Integer id) throws SQLException {
		String query = "delete from teleskop where tel_idtel=?";

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
		String query = "select * from teleskop where tel_idtel=?";
		try (PreparedStatement preparedStatement = connection.prepareStatement(query);) {
			preparedStatement.setInt(1, id);
			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				return resultSet.isBeforeFirst();
			}
		}
	}
	
	@Override
	public Iterable<Teleskop> findAll() throws SQLException {
		String query = "select tel_idtel, tel_naz, tel_type, tel_prec, tel_kvalsl, tel_dmt, tel_rezl  from teleskop";
		List<Teleskop> TeleskopList = new ArrayList<Teleskop>();
		Connection connection = ConnectionUtil_HikariCP.getConnection();
		try (PreparedStatement preparedStatement = connection.prepareStatement(query);
				ResultSet resultSet = preparedStatement.executeQuery()) {

			while (resultSet.next()) {
				Teleskop Teleskop = new Teleskop(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3),
						resultSet.getFloat(4), resultSet.getFloat(5), resultSet.getFloat(6), resultSet.getFloat(7));
				TeleskopList.add(Teleskop);
			}

		}
		return TeleskopList;
	}

	@Override
	public Iterable<Teleskop> findAllById(Iterable<Integer> ids) throws SQLException {
		List<Teleskop> TeleskopList = new ArrayList<>();

		StringBuilder stringBuilder = new StringBuilder();

		String queryBegin = "select tel_idtel, tel_naz, tel_type, tel_prec, tel_kvalsl, tel_dmt, tel_rezl  from teleskop where tel_idtel in(";
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
					TeleskopList.add(new Teleskop(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3),
							resultSet.getFloat(4), resultSet.getFloat(5), resultSet.getFloat(6), resultSet.getFloat(7)));
				}
			}
		}

		return TeleskopList;
	}

	@Override
	public Teleskop findById(Integer id) throws SQLException {
		String query = "select tel_idtel, tel_naz, tel_type, tel_prec, tel_kvalsl, tel_dmt, tel_rezl  from teleskop where tel_idtel = ?";
		Teleskop pozoriste = null;

		try (Connection connection = ConnectionUtil_HikariCP.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(query);) {

			preparedStatement.setInt(1, id);

			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				if (resultSet.isBeforeFirst()) {
					resultSet.next();

					pozoriste = new Teleskop(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3),
							resultSet.getFloat(4), resultSet.getFloat(5), resultSet.getFloat(6), resultSet.getFloat(7));
				}
			}
		}

		return pozoriste;
	}

	@Override
	public boolean save(Teleskop entity) throws SQLException {
		try (Connection connection = ConnectionUtil_HikariCP.getConnection()) {
			return saveTransactional(connection, entity);
		}
	}

	@Override
	public int saveAll(Iterable<Teleskop> entities) throws SQLException {
		int rowsSaved = 0;
		
		try (Connection connection = ConnectionUtil_HikariCP.getConnection()) {
			connection.setAutoCommit(false); // transaction start

			// insert or update every Teleskop
			for (Teleskop entity : entities) {
				boolean success = saveTransactional(connection, entity); // changes are visible only to current connection
				if (success) rowsSaved++;
			}

			connection.commit(); // transaction ends successfully, changes are now visible to other connections as well
		}
		
		return rowsSaved;
	}
	
	// used by save and saveAll
		private boolean saveTransactional(Connection connection, Teleskop entity) throws SQLException {
			// id_th intentionally in the last place, so that the order between commands remains the same
			String insertCommand = "insert into teleskop (tel_naz, tel_type, tel_prec, tel_kvalsl, tel_dmt, tel_rezl, tel_idtel) values (?, ? , ?, ?,?, ?,?)";
			String updateCommand = "update teleskop set tel_naz=?, tel_type=?, tel_prec=?, tel_kvalsl=?, tel_dmt=?, tel_rezl=?  where tel_idtel=?";

			try (PreparedStatement preparedStatement = connection.prepareStatement(
					existsByIdTransactional(connection, entity.getTel_idtel()) ? updateCommand : insertCommand)) {
				int i = 1;
				preparedStatement.setString(i++, entity.getTel_naz());
				preparedStatement.setString(i++, entity.getTel_type());
				preparedStatement.setFloat(i++, entity.getTel_prec());
				preparedStatement.setFloat(i++, entity.getTel_kvalsl());
				preparedStatement.setFloat(i++, entity.getTel_dmt());
				preparedStatement.setFloat(i++, entity.getTel_rezl());
				preparedStatement.setInt(i++, entity.getTel_idtel());
				int rowsAffected = preparedStatement.executeUpdate();
				return rowsAffected == 1;
			}
		}

	@Override
	public List<String> getBrojTeleskopaPoOpservatorijumu() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TeleskopIzvestajDTO> fetchTeleskopIzvestaj() {
	    String query = """
	        SELECT 
	            o.naz AS opservatorijum_naziv,
	            t.tel_naz AS teleskop_naziv,
	            COUNT(p.idpos) AS broj_posmatranja,
	            AVG(p.kraj - p.poc) AS prosečno_trajanje_posmatranja
	        FROM 
	            opservatorijum o
	        LEFT JOIN posmatranje p ON o.idops = p.ops_tel_opservatorijum_idops
	        LEFT JOIN teleskop t ON t.tel_idtel = p.ops_tel_teleskop_tel_idtel
	        WHERE 
	            p.poc >= '10.01.2024.'
	        GROUP BY 
	            o.naz, t.tel_naz
	        HAVING 
	            COUNT(p.idpos) >= 1
	        ORDER BY 
	            broj_posmatranja DESC, prosečno_trajanje_posmatranja ASC
	    """;

	    List<TeleskopIzvestajDTO> rezultati = new ArrayList<>();

	    // Konektovanje i izvršavanje upita sa try-with-resources za automatsko zatvaranje resursa
	    try (Connection conn = ConnectionUtil_HikariCP.getConnection();
	         PreparedStatement ps = conn.prepareStatement(query);
	         ResultSet rs = ps.executeQuery()) {

	        // Iteriranje kroz rezultate i mapiranje u DTO objekat
	        while (rs.next()) {
	            TeleskopIzvestajDTO dto = new TeleskopIzvestajDTO();
	            dto.setOpservatorijumNaziv(rs.getString("opservatorijum_naziv"));
	            dto.setTeleskopNaziv(rs.getString("teleskop_naziv"));
	            dto.setBrojPosmatranja(rs.getInt("broj_posmatranja"));
	            dto.setProsecnoTrajanje(rs.getDouble("prosečno_trajanje_posmatranja"));
	            rezultati.add(dto);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();  // Ovdje bi trebalo koristiti logovanje umesto printStackTrace u produkciji
	    }

	    return rezultati;
	}

		
		

}
