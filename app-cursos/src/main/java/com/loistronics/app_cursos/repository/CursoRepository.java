package com.loistronics.app_cursos.repository;

import com.loistronics.app_cursos.entity.Curso;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CursoRepository extends CrudRepository<Curso,Long> {
}
