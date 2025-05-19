package com.accenture.franquicia.service;

import com.accenture.franquicia.model.Franquicia;
import com.accenture.franquicia.model.Producto;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ProductoService {
    Mono<Franquicia> agregarProducto(String idFranquicia, String idSucursal, Producto producto);
    Mono<Franquicia> eliminarProducto(String idFranquicia, String idSucursal, String idProducto);
    Mono<Franquicia> actualizarStock(String idFranquicia, String idSucursal, String idProducto, int nuevoStock);
    Flux<Producto> obtenerTopStockPorSucursal(String idFranquicia);
    Mono<Franquicia> actualizarNombreProducto(String idFranquicia, String idSucursal, String idProducto, String nuevoNombre);

}
