package com.loistronics.app_cursos.controller;

import com.loistronics.app_cursos.entity.Curso;
import com.loistronics.app_cursos.service.CursoServiceimpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
public class CursoRepository {

    @Autowired
    private CursoServiceimpl service;

    @GetMapping("/")
    public ResponseEntity<?> listar(){
        return ResponseEntity.ok(service.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> listById(@PathVariable Long id){
        Optional<Curso> o = service.porId(id);
        if(o.isPresent()){
            return ResponseEntity.ok(service.porId(id));
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> guardar(@Valid @RequestBody Curso curso, BindingResult result){
        if(result.hasErrors()){
            return validar(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(service.guardar(curso));
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> actualizar(@Valid @RequestBody Curso curso, BindingResult result, @PathVariable Long id){
        if(result.hasErrors()){
            return validar(result);
        }
        Optional<Curso> o = service.porId(id);
        if(o.isPresent()){
            Curso c = o.get();
            c.setNombre(curso.getNombre());
            service.guardar(curso);
        }
        return ResponseEntity.notFound().build();
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("{/id}")
    public ResponseEntity<Object> eliminar(@PathVariable Long id){
        Optional<Curso> o = service.porId(id);
        if(o.isPresent()){
            service.eliminar(id);
        }
        return ResponseEntity.notFound().build();

    }

    private static ResponseEntity<Object> validar(BindingResult result) {
        Map<String,String> errores = new HashMap<>();
        result.getFieldErrors().forEach(err -> {
            errores.put(err.getField(), "Error en el campo: " + err.getField() + " " + err.getDefaultMessage());
        });
        return ResponseEntity.badRequest().build();
    }
}
