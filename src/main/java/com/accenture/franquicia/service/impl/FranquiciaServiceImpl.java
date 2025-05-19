package com.accenture.franquicia.service.impl;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.accenture.franquicia.model.Franquicia;
import com.accenture.franquicia.repository.FranquiciaRepository;
import com.accenture.franquicia.service.FranquiciaService;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class FranquiciaServiceImpl implements FranquiciaService {
	
    private final FranquiciaRepository franquiciaRepository;

	@Override
	public Mono<Franquicia> crearFranquicia(String nombre) {
		  Franquicia franquicia = Franquicia.builder()
	                .nombre(nombre)
	                .sucursales(new ArrayList<>())
	                .build();
	        return franquiciaRepository.save(franquicia);
	}
	
	@Override
	public Mono<Franquicia> actualizarNombre(String idFranquicia, String nuevoNombre) {
	    return franquiciaRepository.findById(idFranquicia)
	        .flatMap(f -> {
	            f.setNombre(nuevoNombre);
	            return franquiciaRepository.save(f);
	        });
	}


}
