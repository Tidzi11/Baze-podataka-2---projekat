package rs.ac.uns.ftn.db.jdbc.exam.connection;

public class ConnectionParams {
	public static final String DRIVER = "oracle.jdbc.OracleDriver";

	public static final String LOCAL_CONNECTION_STRING = "jdbc:oracle:thin:@localhost:1521/xepdb1";
	public static final String CLASSROOM_CONNECTION_STRING = "jdbc:oracle:thin:@192.168.0.102:1522:db2016";

	public static final String USERNAME = "tidzi";
	public static final String PASSWORD = "ftn";
}
