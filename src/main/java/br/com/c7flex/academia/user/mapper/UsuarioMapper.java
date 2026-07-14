package br.com.c7flex.academia.user.mapper;

import br.com.c7flex.academia.user.dto.UsuarioResponse;
import br.com.c7flex.academia.user.entity.Usuario;
import org.springframework.stereotype.Component;

@Component
public class UsuarioMapper {

    public UsuarioResponse toResponse(Usuario usuario) {

        return new UsuarioResponse(

                usuario.getId(),

                usuario.getNome(),

                usuario.getEmail(),

                usuario.getFoto(),

                usuario.getRole().name()

        );

    }

}