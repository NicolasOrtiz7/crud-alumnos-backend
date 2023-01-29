package com.crud.service;

import com.crud.model.Alumno;
import com.crud.repository.AlumnoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public interface AlumnoService {


    public List<Alumno> buscarTodos();

    public Optional<Alumno> buscarId(Long id);

    public List<Alumno> findByHabilitado();

    public List<Alumno> findByDeshabilitado();

    public void editar(Alumno alumno);

    public void nuevo(Alumno alumno);

    public void eliminar(Long id);

}
