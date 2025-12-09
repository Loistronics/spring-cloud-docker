package com.loistronics.app_usuarios.services;

import com.loistronics.app_usuarios.entity.Usuario;

import java.util.List;
import java.util.Optional;

public interface IUsusarioService {
    List<Usuario> listar();
    Optional<Usuario> porId(Long id);
    Usuario guardar(Usuario usuario);
    void eliminar(Long id);

    Optional<Usuario> porEmail(String email);
}
