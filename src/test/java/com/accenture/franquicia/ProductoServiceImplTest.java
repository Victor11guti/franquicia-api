package com.accenture.franquicia;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.accenture.franquicia.model.Franquicia;
import com.accenture.franquicia.model.Producto;
import com.accenture.franquicia.model.Sucursal;
import com.accenture.franquicia.repository.FranquiciaRepository;
import com.accenture.franquicia.service.impl.ProductoServiceImpl;

import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@ExtendWith(MockitoExtension.class)
class ProductoServiceImplTest {

    @Mock
    private FranquiciaRepository franquiciaRepository;

    @InjectMocks
    private ProductoServiceImpl productoService;

    @Test
    void agregarProducto_deberiaAgregarProductoASucursal() {
        String idF = "f1", idS = "s1";
        Producto p = new Producto("p1", "Refresco", 100);

        Sucursal sucursal = new Sucursal(idS, "Sucursal Central", new ArrayList<>());
        Franquicia franquicia = Franquicia.builder().id(idF).sucursales(List.of(sucursal)).build();

        when(franquiciaRepository.findById(idF)).thenReturn(Mono.just(franquicia));
        when(franquiciaRepository.save(any())).thenReturn(Mono.just(franquicia));

        StepVerifier.create(productoService.agregarProducto(idF, idS, p))
            .expectNextMatches(f -> f.getSucursales().get(0).getProductos().size() == 1)
            .verifyComplete();
    }

    @Test
    void actualizarStock_deberiaActualizarStockDelProducto() {
        String idF = "f1", idS = "s1", idP = "p1";

        Producto producto = new Producto(idP, "Papas", 20);
        Sucursal sucursal = new Sucursal(idS, "Norte", List.of(producto));
        Franquicia franquicia = Franquicia.builder().id(idF).sucursales(List.of(sucursal)).build();

        when(franquiciaRepository.findById(idF)).thenReturn(Mono.just(franquicia));
        when(franquiciaRepository.save(any())).thenReturn(Mono.just(franquicia));

        StepVerifier.create(productoService.actualizarStock(idF, idS, idP, 99))
            .expectNextMatches(f -> f.getSucursales().get(0).getProductos().get(0).getStock() == 99)
            .verifyComplete();
    }

    @Test
    void obtenerTopStockPorSucursal_deberiaRetornarProductoConMasStock() {
        String idF = "f1";

        Producto p1 = new Producto("p1", "Pizza", 30);
        Producto p2 = new Producto("p2", "Burger", 80);
        Sucursal s1 = new Sucursal("s1", "Norte", List.of(p1, p2));

        Producto p3 = new Producto("p3", "Taco", 45);
        Sucursal s2 = new Sucursal("s2", "Sur", List.of(p3));

        Franquicia franquicia = Franquicia.builder().id(idF).sucursales(List.of(s1, s2)).build();

        when(franquiciaRepository.findById(idF)).thenReturn(Mono.just(franquicia));

        StepVerifier.create(productoService.obtenerTopStockPorSucursal(idF))
            .expectNextMatches(p -> p.getNombre().contains("Sucursal:"))
            .expectNextCount(1)
            .verifyComplete();
    }
}

