package br.com.feedev.jdtjsp.conn;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * Responsável por realizar conexão com o banco de dados
 * 
 * @author fernando-barbosa
 *
 */
public class SingleConnection {

	private static final String DATABASE = "jdbc:postgresql://localhost:5432/jdt_jsp_db?autoReconnect=true";
	private static final String USERNAME = "postgres";
	private static final String PASSWORD = "admin";
	private static Connection connection = null;
	
	static {
		try {
			conectar();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public SingleConnection() throws Exception {
		conectar();
	}
	
	private static void conectar() throws Exception {
		try {
			
			if (connection == null) {
				Class.forName("org.postgresql.Driver");
				connection = DriverManager.getConnection(DATABASE, USERNAME, PASSWORD);
				connection.setAutoCommit(false);
			} 
			
			System.out.println("Conectado com sucesso.");
			
		} catch (Exception e) {
			throw new Exception("Erro ao conectar ao banco de dados: " + e.getMessage());
		}
	}
	
	public static Connection getConnection() {
		return connection;
	}
	
	public static void closeConnection() throws Exception {
		try {
			connection.close();
			System.out.println("Conexão fechada com sucesso.");
		} catch (Exception e) {
			throw new Exception("Erro ao fechar conexão com o banco de dados." + e.getMessage());
		}
	}
	
	public static void main(String[] args) throws Exception {
		SingleConnection.getConnection();
		SingleConnection.closeConnection();
	}
	
}
