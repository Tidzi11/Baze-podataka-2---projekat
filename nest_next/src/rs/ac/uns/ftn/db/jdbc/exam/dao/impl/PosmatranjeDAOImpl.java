package rs.ac.uns.ftn.db.jdbc.exam.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import rs.ac.uns.ftn.db.jdbc.exam.dao.PosmatranjeDAO;
import rs.ac.uns.ftn.db.jdbc.exam.dto.PosmatranjePoNebeskomObjektuDTO;
import rs.ac.uns.ftn.db.jdbc.exam.model.Posmatranje;
import rs.ac.uns.ftn.db.jdbc.exam.connection.ConnectionUtil_HikariCP;
import rs.ac.uns.ftn.db.jdbc.exam.model.Posmatranje;

public class PosmatranjeDAOImpl implements PosmatranjeDAO {

	@Override
	public int count() throws SQLException {
		String query = "select count(*) from posmatranje";

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
	public boolean delete(Posmatranje entity) throws SQLException {
		return deleteById(entity.getIdpos());
	}

	@Override
	public int deleteAll() throws SQLException {
		String query = "delete from posmatranje";

		try (Connection connection = ConnectionUtil_HikariCP.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(query);) {
			int rowsAfffected = preparedStatement.executeUpdate();
			return rowsAfffected;
		}
	}
	
	@Override
	public boolean deleteById(Integer id) throws SQLException {
		String query = "delete from posmatranje where idpos=?";

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
		String query = "select * from posmatranje where idpos=?";
		try (PreparedStatement preparedStatement = connection.prepareStatement(query);) {
			preparedStatement.setInt(1, id);
			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				return resultSet.isBeforeFirst();
			}
		}
	}

	@Override
	public Iterable<Posmatranje> findAll() throws SQLException {
		String query = "select idpos, poc, kraj, ops_tel_teleskop_tel_idtel, ops_tel_opservatorijum_idops, nebeskiobjekat_idnebobj  from posmatranje";
		List<Posmatranje> PosmatranjeList = new ArrayList<Posmatranje>();
		Connection connection = ConnectionUtil_HikariCP.getConnection();
		try (PreparedStatement preparedStatement = connection.prepareStatement(query);
				ResultSet resultSet = preparedStatement.executeQuery()) {

			while (resultSet.next()) {
				Posmatranje Posmatranje = new Posmatranje(resultSet.getInt(1), resultSet.getDate(2), resultSet.getDate(3),
						resultSet.getInt(4), resultSet.getInt(5), resultSet.getInt(6));
				PosmatranjeList.add(Posmatranje);
			}

		}
		return PosmatranjeList;
	}

	@Override
	public Iterable<Posmatranje> findAllById(Iterable<Integer> ids) throws SQLException {
		List<Posmatranje> PosmatranjeList = new ArrayList<>();

		StringBuilder stringBuilder = new StringBuilder();

		String queryBegin = "select idpos, poc, kraj, ops_tel_teleskop_tel_idtel, ops_tel_opservatorijum_idops, nebeskiobjekat_idnebobj  from posmatranje where idpos in(";
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
					PosmatranjeList.add(new Posmatranje(resultSet.getInt(1), resultSet.getDate(2), resultSet.getDate(3),
							resultSet.getInt(4), resultSet.getInt(5), resultSet.getInt(6)));
				}
			}
		}

		return PosmatranjeList;
	}

	@Override
	public Posmatranje findById(Integer id) throws SQLException {
		String query = "select idpos, poc, kraj, ops_tel_teleskop_tel_idtel, ops_tel_opservatorijum_idops, nebeskiobjekat_idnebobj  from posmatranje where idpos = ?";
		Posmatranje pozoriste = null;

		try (Connection connection = ConnectionUtil_HikariCP.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(query);) {

			preparedStatement.setInt(1, id);

			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				if (resultSet.isBeforeFirst()) {
					resultSet.next();

					pozoriste = new Posmatranje(resultSet.getInt(1), resultSet.getDate(2), resultSet.getDate(3),
							resultSet.getInt(4), resultSet.getInt(5), resultSet.getInt(6));
				}
			}
		}

		return pozoriste;
	}

	@Override
	public boolean save(Posmatranje entity) throws SQLException {
		try (Connection connection = ConnectionUtil_HikariCP.getConnection()) {
			return saveTransactional(connection, entity);
		}
	}

	@Override
	public int saveAll(Iterable<Posmatranje> entities) throws SQLException {
		int rowsSaved = 0;
		
		try (Connection connection = ConnectionUtil_HikariCP.getConnection()) {
			connection.setAutoCommit(false); // transaction start

			// insert or update every Posmatranje
			for (Posmatranje entity : entities) {
				boolean success = saveTransactional(connection, entity); // changes are visible only to current connection
				if (success) rowsSaved++;
			}

			connection.commit(); // transaction ends successfully, changes are now visible to other connections as well
		}
		
		return rowsSaved;
	}
	
	// used by save and saveAll
		private boolean saveTransactional(Connection connection, Posmatranje entity) throws SQLException {
			// id_th intentionally in the last place, so that the order between commands remains the same
			String insertCommand = "insert into Posmatranje (name_th, address_th, website_th,place_id_pl, id_th) values (?, ? , ?, ?,?)";
			String updateCommand = "update Posmatranje set name_th=?, address_th=?, website_th=?, place_id_pl=? where id_th=?";

			try (PreparedStatement preparedStatement = connection.prepareStatement(
					existsByIdTransactional(connection, entity.getIdpos()) ? updateCommand : insertCommand)) {
				int i = 1;
				preparedStatement.setDate(i++, entity.getPoc());
				preparedStatement.setDate(i++, entity.getKraj());
				preparedStatement.setInt(i++, entity.getOps_tel_teleskop_tel_idtel());
				preparedStatement.setInt(i++, entity.getOps_tel_opservatorijum_idops());
				preparedStatement.setInt(i++, entity.getNebeskiobjekat_idnebobj());
				preparedStatement.setInt(i++, entity.getIdpos());
				int rowsAffected = preparedStatement.executeUpdate();
				return rowsAffected == 1;
			}
		}

		@Override
		public List<String> getKompleksnoPosmatranje() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public List<String> getBrojPosmatranjaPoAstronomima() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public void dodajPosmatranjeSaTransakcijom(int i, int j, String string, String string2, String string3) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public List<Posmatranje> findPosmatranjeByNebeskiObjekat(int idnebobj) throws SQLException {
			List<Posmatranje> posmatranjaList = new ArrayList<>();
		    
		    // SQL upit koji vraća posmatranja za dati nebeski objekat
		    String query = "SELECT idpos, poc, kraj, ops_tel_teleskop_tel_idtel, ops_tel_opservatorijum_idops, nebeskiobjekat_idnebobj "
		                 + "FROM posmatranje WHERE nebeskiobjekat_idnebobj = ?";
		    
		    try (Connection connection = ConnectionUtil_HikariCP.getConnection();
		         PreparedStatement preparedStatement = connection.prepareStatement(query)) {
		        
		        // Postavljamo vrednost za ID nebeskog objekta
		        preparedStatement.setInt(1, idnebobj);
		        
		        // Izvršavamo upit i dobijamo rezultate
		        try (ResultSet resultSet = preparedStatement.executeQuery()) {
		            while (resultSet.next()) {
		                // Kreiramo objekat Posmatranje sa rezultatima iz ResultSet-a
		                Posmatranje posmatranje = new Posmatranje(
		                    resultSet.getInt("idpos"),
		                    resultSet.getDate("poc"),
		                    resultSet.getDate("kraj"),
		                    resultSet.getInt("ops_tel_teleskop_tel_idtel"),
		                    resultSet.getInt("ops_tel_opservatorijum_idops"),
		                    resultSet.getInt("nebeskiobjekat_idnebobj")
		                );
		                
		                // Dodajemo objekt u listu
		                posmatranjaList.add(posmatranje);
		            }
		        }
		    }
		    
		    // Vraćamo listu svih posmatranja koja odgovaraju datom nebeskom objektu
		    return posmatranjaList;
		
		}

		@Override
		public List<PosmatranjePoNebeskomObjektuDTO> getPosmatranja() throws SQLException {
			// Lista koja će čuvati rezultate upita
		    List<PosmatranjePoNebeskomObjektuDTO> results = new ArrayList<>();

		    // SQL upit za dobavljanje broja posmatranja po opservatorijumu i nebeskom objektu
		    String query = """
		    		SELECT o.naz AS opservatorijum_naziv, n.ime AS nebeski_objekat_naziv, COUNT(p.idpos) AS broj_posmatranja 
		            FROM opservatorijum o 
		            JOIN posmatranje p ON o.idops = p.ops_tel_opservatorijum_idops 
		            JOIN nebeskiobjekat n ON n.idnebobj = p.nebeskiobjekat_idnebobj 
		            GROUP BY o.naz, n.ime 
		            ORDER BY broj_posmatranja DESC
		    		""";

		    // Korišćenje try-with-resources za automatsko zatvaranje resursa
		    try (Connection connection = ConnectionUtil_HikariCP.getConnection();
		         PreparedStatement preparedStatement = connection.prepareStatement(query);
		         ResultSet resultSet = preparedStatement.executeQuery()) {

		        // Obrada rezultata upita
		        while (resultSet.next()) {
		            // Čitanje podataka iz ResultSet-a
		            String opservatorijumNaziv = resultSet.getString("opservatorijum_naziv");
		            String nebeskiObjekatNaziv = resultSet.getString("nebeski_objekat_naziv");
		            int brojPosmatranja = resultSet.getInt("broj_posmatranja");

		            // Dodavanje rezultata u listu
		            results.add(new PosmatranjePoNebeskomObjektuDTO(opservatorijumNaziv, nebeskiObjekatNaziv, brojPosmatranja));
		        }
		    }

		    // Vraćanje rezultata
		    return results;
		}

		
}
