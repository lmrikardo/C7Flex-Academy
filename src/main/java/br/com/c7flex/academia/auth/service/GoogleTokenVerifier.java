package br.com.c7flex.academia.auth.service;

import br.com.c7flex.academia.auth.dto.GoogleUser;

public interface GoogleTokenVerifier {

    GoogleUser verify(String idToken);

}