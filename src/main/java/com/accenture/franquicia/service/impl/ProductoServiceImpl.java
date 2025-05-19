package com.accenture.franquicia.service.impl;

import java.util.Comparator;
import java.util.Objects;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.accenture.franquicia.model.Franquicia;
import com.accenture.franquicia.model.Producto;
import com.accenture.franquicia.repository.FranquiciaRepository;
import com.accenture.franquicia.service.ProductoService;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;



@Service
@RequiredArgsConstructor
public class ProductoServiceImpl implements ProductoService {

    private final FranquiciaRepository franquiciaRepository;

	@Override
	public Mono<Franquicia> agregarProducto(String idFranquicia, String idSucursal, Producto producto) {
		return franquiciaRepository.findById(idFranquicia)
		            .flatMap(franquicia -> {
		                franquicia.getSucursales().stream()
		                    .filter(s -> s.getId().equals(idSucursal))
		                    .findFirst()
		                    .ifPresent(sucursal -> sucursal.getProductos().add(producto));
		                return franquiciaRepository.save(franquicia);
		            });
	}

	@Override
	public Mono<Franquicia> eliminarProducto(String idFranquicia, String idSucursal, String idProducto) {
		return franquiciaRepository.findById(idFranquicia)
		        .flatMap(franquicia -> {
		            franquicia.getSucursales().stream()
		                .filter(sucursal -> sucursal.getId().equals(idSucursal))
		                .findFirst()
		                .ifPresent(sucursal -> sucursal.getProductos()
		                    .removeIf(producto -> producto.getId().equals(idProducto)));
		            return franquiciaRepository.save(franquicia);
		        });
	}
	
	@Override
	public Mono<Franquicia> actualizarStock(String idFranquicia, String idSucursal, String idProducto, int nuevoStock) {
	    return franquiciaRepository.findById(idFranquicia)
	        .flatMap(franquicia -> {
	            franquicia.getSucursales().stream()
	                .filter(sucursal -> sucursal.getId().equals(idSucursal))
	                .findFirst()
	                .ifPresent(sucursal -> sucursal.getProductos().stream()
	                    .filter(producto -> producto.getId().equals(idProducto))
	                    .findFirst()
	                    .ifPresent(producto -> producto.setStock(nuevoStock)));
	            return franquiciaRepository.save(franquicia);
	        });
	}

	@Override
	public Flux<Producto> obtenerTopStockPorSucursal(String idFranquicia) {
	    return franquiciaRepository.findById(idFranquicia)
	        .flatMapMany(franquicia -> Flux.fromIterable(franquicia.getSucursales()))
	        .flatMap(sucursal -> {
	            return Mono.justOrEmpty(
	                sucursal.getProductos().stream()
	                    .max(Comparator.comparingInt(Producto::getStock))
	                    .map(p -> {
	                        p.setNombre(p.getNombre() + " (Sucursal: " + sucursal.getNombre() + ")");
	                        return p;
	                    })
	            );
	        });
	}

	@Override
	public Mono<Franquicia> actualizarNombreProducto(String idFranquicia, String idSucursal, String idProducto, String nuevoNombre) {
	    return franquiciaRepository.findById(idFranquicia)
	        .flatMap(franquicia -> {
	            franquicia.getSucursales().stream()
	                .filter(s -> s.getId().equals(idSucursal))
	                .findFirst()
	                .ifPresent(sucursal -> sucursal.getProductos().stream()
	                    .filter(p -> p.getId().equals(idProducto))
	                    .findFirst()
	                    .ifPresent(p -> p.setNombre(nuevoNombre)));
	            return franquiciaRepository.save(franquicia);
	        });
	}

	


}
