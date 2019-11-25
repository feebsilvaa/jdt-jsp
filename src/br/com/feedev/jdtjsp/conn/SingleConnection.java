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
		conectar();
	}
	
	public SingleConnection() {
		conectar();
	}
	
	private static void conectar() {
		try {
			
			if (connection == null) {
				Class.forName("org.postgresql.Driver");
				connection = DriverManager.getConnection(DATABASE, USERNAME, PASSWORD);
				connection.setAutoCommit(false);
			} 
			
			System.out.println("Conectado com sucesso.");
			
		} catch (Exception e) {
			throw new RuntimeException("Erro ao conectar ao banco de dados: " + e.getMessage());
		}
	}
	
	public static Connection getConnection() {
		return connection;
	}
	
	public static void closeConnection() {
		try {
			connection.close();
			System.out.println("Conexão fechada com sucesso.");
		} catch (Exception e) {
			throw new RuntimeException("Erro ao fechar conexão com o banco de dados." + e.getMessage());
		}
	}
	
	public static void main(String[] args) {
		SingleConnection.getConnection();
		SingleConnection.closeConnection();
	}
	
}
