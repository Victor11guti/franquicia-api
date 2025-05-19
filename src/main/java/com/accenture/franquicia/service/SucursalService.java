package com.accenture.franquicia.service;

import com.accenture.franquicia.model.Franquicia;
import com.accenture.franquicia.model.Sucursal;

import reactor.core.publisher.Mono;


public interface SucursalService {
    Mono<Franquicia> agregarSucursal(String idFranquicia, Sucursal sucursal);
    Mono<Franquicia> actualizarNombreSucursal(String idFranquicia, String idSucursal, String nuevoNombre);


}
