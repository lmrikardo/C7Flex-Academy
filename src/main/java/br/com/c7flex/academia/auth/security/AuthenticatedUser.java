package br.com.c7flex.academia.auth.security;

import br.com.c7flex.academia.user.entity.Role;

public record AuthenticatedUser(

        Long id,

        String nome,

        String email,

        Role role

) {
}