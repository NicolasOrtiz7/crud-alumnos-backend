package com.crud.service;

import com.crud.exception.AlumnoNotFound;
import com.crud.model.Alumno;
import com.crud.repository.AlumnoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AlumnoServiceImpl implements AlumnoService{

    @Autowired
    private AlumnoRepository repositorio;

    @Override
    public List<Alumno> buscarTodos() {
        List<Alumno> lista = repositorio.findAll();
        return lista;
    }

    @Override
    public Optional<Alumno> buscarId(Long id) {
        return repositorio.findById(id);
    }

    @Override
    public List<Alumno> findByHabilitado() {
        List<Alumno> lista = repositorio.findByHabilitado(1); // busca los que estan habilitados
        return lista;
    }

    @Override
    public List<Alumno> findByDeshabilitado() {
        List<Alumno> lista = repositorio.findByHabilitado(0); // busca los que estan deshabilitados
        return lista;
    }

    @Override
    public void editar(Alumno alumno) {
        repositorio.save(alumno);
    }

    @Override
    public void nuevo(Alumno alumno) {
        repositorio.save(alumno);
    }

    @Override
    public void eliminar(Long id) { // solo se usa en la papelera, no en la lista.
        repositorio.deleteById(id);
    }
}
