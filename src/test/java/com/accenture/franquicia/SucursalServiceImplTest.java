package com.accenture.franquicia;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.accenture.franquicia.model.Franquicia;
import com.accenture.franquicia.model.Sucursal;
import com.accenture.franquicia.repository.FranquiciaRepository;
import com.accenture.franquicia.service.impl.SucursalServiceImpl;

import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@ExtendWith(MockitoExtension.class)
class SucursalServiceImplTest {

    @Mock
    private FranquiciaRepository franquiciaRepository;

    @InjectMocks
    private SucursalServiceImpl sucursalService;

    @Test
    void agregarSucursal_deberiaAgregarASucursalYGuardar() {
        String idFranquicia = "abc123";
        Sucursal nueva = new Sucursal("s1", "Sucursal Norte", new ArrayList<>());

        Franquicia franquicia = Franquicia.builder()
                .id(idFranquicia)
                .nombre("Franquicia 1")
                .sucursales(new ArrayList<>())
                .build();

        when(franquiciaRepository.findById(idFranquicia)).thenReturn(Mono.just(franquicia));
        when(franquiciaRepository.save(any())).thenReturn(Mono.just(franquicia));

        StepVerifier.create(sucursalService.agregarSucursal(idFranquicia, nueva))
            .expectNextMatches(f -> f.getSucursales().stream()
                .anyMatch(s -> s.getNombre().equals("Sucursal Norte")))
            .verifyComplete();
    }
}

