package es.eoi.jdbc.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import es.eoi.jdbc.entity.Alumno;

public class AlumnoRepositoryImpl implements AlumnoRepository {

	private Connection openConnection() {
		String url = "jdbc:mysql://localhost:3306/jdbc?serverTimezone=UTC";
		String user = "root";
		String pass = "1234";

		Connection conexion = null;

		try {
			conexion = DriverManager.getConnection(url, user, pass);
			System.out.println("Conectado a ".concat(url));

		} catch (Exception e) {
			System.out.println("No se ha realizado correctamente la accion");
		}

		return conexion;
	}

	public Alumno findByDni(String dni) {
		String dniFound = null;
		String nombre = null;
		String apellido = null;

		StringBuilder sb = new StringBuilder();
		sb.append("SELECT * ");
		sb.append("FROM ALUMNO ");
		sb.append("WHERE DNI = ? ");

		// ResultSet rs = null;
		try {

			Connection conn = openConnection();

			PreparedStatement pst = conn.prepareStatement(sb.toString());
			pst.setString(1, "48774753X");

			ResultSet rs = pst.executeQuery();
			
			while (rs.next()) {
				dniFound = rs.getString("Dni");
				nombre = rs.getString("Nombre");
				apellido = rs.getString("Apellidos");
			}

		} catch (Exception e) {
			System.out.println("Algo ha ido mal");
		}

		Alumno newAlumno = new Alumno(nombre, apellido, 24, dniFound);
		System.out.println("DEBUG Nombre:" + newAlumno.getNombre() + " edad: " + newAlumno.getEdad());
		return newAlumno;
	}

	public List<Alumno> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean create(Alumno alumno) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean delete(String dni) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean update(String dni, String nombre, String apellidos) {
		// TODO Auto-generated method stub
		return false;
	}

}
