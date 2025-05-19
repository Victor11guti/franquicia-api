package com.accenture.franquicia.service.impl;

import org.springframework.stereotype.Service;

import com.accenture.franquicia.model.Franquicia;
import com.accenture.franquicia.model.Sucursal;
import com.accenture.franquicia.repository.FranquiciaRepository;
import com.accenture.franquicia.service.SucursalService;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;



@Service
@RequiredArgsConstructor
public class SucursalServiceImpl implements SucursalService {
	
	 private final FranquiciaRepository franquiciaRepository;

	@Override
	public Mono<Franquicia> agregarSucursal(String idFranquicia, Sucursal sucursal) {
		return franquiciaRepository.findById(idFranquicia)
	            .flatMap(franquicia -> {
	                franquicia.getSucursales().add(sucursal);
	                return franquiciaRepository.save(franquicia);
	            });
	}
	
	@Override
	public Mono<Franquicia> actualizarNombreSucursal(String idFranquicia, String idSucursal, String nuevoNombre) {
	    return franquiciaRepository.findById(idFranquicia)
	        .flatMap(franquicia -> {
	            franquicia.getSucursales().stream()
	                .filter(s -> s.getId().equals(idSucursal))
	                .findFirst()
	                .ifPresent(s -> s.setNombre(nuevoNombre));
	            return franquiciaRepository.save(franquicia);
	        });
	}


}
