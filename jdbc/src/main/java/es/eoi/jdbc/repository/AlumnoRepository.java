package es.eoi.jdbc.repository;

import java.util.List;

import es.eoi.jdbc.entity.Alumno;

public interface AlumnoRepository {

	public Alumno findByDni(String dni);

	public List<Alumno> findAll();

	public boolean create(Alumno alumno);

	public boolean delete(String dni);

	public boolean update(String dni, String nombre, String apellidos);
}
