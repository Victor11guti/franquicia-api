package com.accenture.franquicia.service;
import com.accenture.franquicia.model.Franquicia;

import reactor.core.publisher.Mono;


public interface FranquiciaService {
	  Mono<Franquicia> crearFranquicia(String nombre);
	  Mono<Franquicia> actualizarNombre(String idFranquicia, String nuevoNombre);

}
