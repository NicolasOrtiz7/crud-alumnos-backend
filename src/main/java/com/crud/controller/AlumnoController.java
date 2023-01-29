package com.crud.controller;

import com.crud.exception.AlumnoNotFound;
import com.crud.model.Alumno;
import com.crud.service.AlumnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
//@RequestMapping("/alumno")
@CrossOrigin(origins = "http://localhost:4200")
public class AlumnoController {

    @Autowired
    public AlumnoService servicio;

    @GetMapping("/lista")
    public ResponseEntity<List<Alumno>> buscarTodos(){
        List<Alumno> lista = servicio.buscarTodos(); // Guarda todos los alumnos en un List
        return new ResponseEntity(lista, HttpStatus.OK); // Devuelve la lista
    }

    @GetMapping("/{id}")
    public ResponseEntity<Alumno> buscarId(@PathVariable Long id){
        Optional<Alumno> alumno = servicio.buscarId(id); // Busca el alumno por el id

        if(!alumno.isPresent()){ // Si el alumno no existe
            throw new AlumnoNotFound("El alumno con " + id + " no existe.");
        }
        return new ResponseEntity(alumno, HttpStatus.OK);
    }

    @GetMapping("/habilitados")
    public ResponseEntity<List<Alumno>> habilitados(){
        List<Alumno> lista = servicio.findByHabilitado();
        return new ResponseEntity(lista, HttpStatus.OK);
    }

    @GetMapping("/papelera")
    public ResponseEntity<List<Alumno>> papelera(){
        List<Alumno> lista = servicio.findByDeshabilitado();
        return new ResponseEntity(lista, HttpStatus.OK);
    }

    @PostMapping("/nuevo")
    public ResponseEntity<Alumno> nuevo(@RequestBody Alumno alumno){
        servicio.nuevo(alumno);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @PutMapping("/editar/{id}")
    public ResponseEntity<Alumno> editar(@PathVariable Long id, @RequestBody Alumno alumno){
        Optional<Alumno> alumno1 = servicio.buscarId(id); // Primero busca el alumno por id

        if(!alumno1.isPresent()){  // Si el alumno no existe
            throw new AlumnoNotFound("El alumno con " + id + " no existe.");
        }
        Alumno nuevoAlumno = alumno1.get(); // Crea un nuevo objeto alumno y le asigna los nuevos valores

        nuevoAlumno.setNombre(alumno.getNombre());
        nuevoAlumno.setApellido(alumno.getApellido());
        nuevoAlumno.setEdad(alumno.getEdad());
        nuevoAlumno.setDetallesAlumno(alumno.getDetallesAlumno());
        nuevoAlumno.setHabilitado(alumno.getHabilitado());

        servicio.nuevo(nuevoAlumno); // Guarda el nuevo alumno

        return new ResponseEntity(HttpStatus.CREATED);
    }

    @PutMapping("/deshabilitar/{id}") // manda al alumno de la lista a la papelera de reciclaje
    public void deshabilitar(@PathVariable Long id){
        Optional<Alumno> alumno1 = servicio.buscarId(id);

        if(!alumno1.isPresent()) throw new AlumnoNotFound("El alumno con " + id + " no existe.");

        alumno1.get().setHabilitado(0); // Actualiza el campo "habilitado" otorgandole valor 0

        servicio.editar(alumno1.get()); // el .get() pertenece a Optional<>
    }
    @PutMapping("/habilitar/{id}") // manda al alumno de la papelera a la lista
    public void habilitar(@PathVariable Long id){
        Optional<Alumno> alumno1 = servicio.buscarId(id);
        if(!alumno1.isPresent()){
            throw new AlumnoNotFound("El alumno con " + id + " no existe.");
        }
        alumno1.get().setHabilitado(1); // Actualiza el campo "habilitado" otorgandole valor 1

        servicio.editar(alumno1.get());
    }

    @DeleteMapping("/eliminar/{id}") // se usa solo en la papelera de reciclaje
    public void eliminarDefinitivo(@PathVariable Long id){
        servicio.eliminar(id);
    }

}
