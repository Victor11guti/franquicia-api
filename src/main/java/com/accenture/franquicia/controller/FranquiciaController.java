package com.accenture.franquicia.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

import java.util.Map;

import org.springframework.web.bind.annotation.*;

import com.accenture.franquicia.model.Franquicia;
import com.accenture.franquicia.model.Producto;
import com.accenture.franquicia.model.Sucursal;
import com.accenture.franquicia.service.FranquiciaService;
import com.accenture.franquicia.service.ProductoService;
import com.accenture.franquicia.service.SucursalService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/franquicias")
@RequiredArgsConstructor
@Tag(name = "Franquicias", description = "Operaciones CRUD sobre franquicias")
public class FranquiciaController {
	
	private final FranquiciaService franquiciaService;
	private final SucursalService sucursalService;
	private final ProductoService productoService;


	 @Operation(summary = "Crear una nueva franquicia")
	    @PostMapping
	    public Mono<Franquicia> crearFranquicia(@RequestBody Map<String, String> request) {
	        return franquiciaService.crearFranquicia(request.get("nombre"));
	    }
	 
	 @Operation(summary = "Agregar una sucursal a una franquicia")
	 @PostMapping("/{id}/sucursales")
	 public Mono<Franquicia> agregarSucursal(
	     @PathVariable("id") String id, 
	     @RequestBody Sucursal sucursal
	 ) {
	     return sucursalService.agregarSucursal(id, sucursal);
	 }
	 
	 @Operation(summary = "Agregar un producto a una sucursal")
	 @PostMapping("/{idFranquicia}/sucursales/{idSucursal}/productos")
	 public Mono<Franquicia> agregarProducto(
	     @PathVariable("idFranquicia") String idFranquicia,
	     @PathVariable("idSucursal") String idSucursal,
	     @RequestBody Producto producto
	 ) {
	     return productoService.agregarProducto(idFranquicia, idSucursal, producto);
	 }
	 
	 @Operation(summary = "Eliminar un producto de una sucursal")
	 @DeleteMapping("/{idFranquicia}/sucursales/{idSucursal}/productos/{idProducto}")
	 public Mono<Franquicia> eliminarProducto(
	     @PathVariable("idFranquicia") String idFranquicia,
	     @PathVariable("idSucursal") String idSucursal,
	     @PathVariable("idProducto") String idProducto
	 ) {
	     return productoService.eliminarProducto(idFranquicia, idSucursal, idProducto);
	 }
	 
	 @Operation(summary = "Actualizar el stock de un producto")
	 @PutMapping("/{idFranquicia}/sucursales/{idSucursal}/productos/{idProducto}/stock")
	 public Mono<Franquicia> actualizarStock(
	     @PathVariable("idFranquicia") String idFranquicia,
	     @PathVariable("idSucursal") String idSucursal,
	     @PathVariable("idProducto") String idProducto,
	     @RequestBody Map<String, Integer> body
	 ) {
	     int nuevoStock = body.get("stock");
	     return productoService.actualizarStock(idFranquicia, idSucursal, idProducto, nuevoStock);
	 }
	 
	 @Operation(summary = "Obtener el producto con más stock por sucursal")
	 @GetMapping("/{idFranquicia}/productos/top-stock")
	 public Flux<Producto> obtenerTopStockPorSucursal(
	     @PathVariable("idFranquicia") String idFranquicia
	 ) {
	     return productoService.obtenerTopStockPorSucursal(idFranquicia);
	 }
	 
	 @Operation(summary = "Actualizar nombre de la franquicia")
	 @PutMapping("/{id}/nombre")
	 public Mono<Franquicia> actualizarNombreFranquicia(
	     @PathVariable("id") String id,
	     @RequestBody Map<String, String> body
	 ) {
	     return franquiciaService.actualizarNombre(id, body.get("nombre"));
	 }
	 
	 
	 @Operation(summary = "Actualizar nombre de una sucursal")
	 @PutMapping("/{idFranquicia}/sucursales/{idSucursal}/nombre")
	 public Mono<Franquicia> actualizarNombreSucursal(
	     @PathVariable String idFranquicia,
	     @PathVariable String idSucursal,
	     @RequestBody Map<String, String> body
	 ) {
	     return sucursalService.actualizarNombreSucursal(idFranquicia, idSucursal, body.get("nombre"));
	 }

	 @Operation(summary = "Actualizar nombre de un producto")
	 @PutMapping("/{idFranquicia}/sucursales/{idSucursal}/productos/{idProducto}/nombre")
	 public Mono<Franquicia> actualizarNombreProducto(
	     @PathVariable String idFranquicia,
	     @PathVariable String idSucursal,
	     @PathVariable String idProducto,
	     @RequestBody Map<String, String> body
	 ) {
	     return productoService.actualizarNombreProducto(idFranquicia, idSucursal, idProducto, body.get("nombre"));
	 }




}
