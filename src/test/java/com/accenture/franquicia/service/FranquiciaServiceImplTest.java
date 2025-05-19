package com.accenture.franquicia.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.accenture.franquicia.model.Franquicia;
import com.accenture.franquicia.repository.FranquiciaRepository;
import com.accenture.franquicia.service.impl.FranquiciaServiceImpl;

import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@ExtendWith(MockitoExtension.class)
class FranquiciaServiceImplTest {

    @Mock
    private FranquiciaRepository franquiciaRepository;

    @InjectMocks
    private FranquiciaServiceImpl franquiciaService;

    @Test
    void crearFranquicia_deberiaCrearYGuardar() {
        String nombre = "Burger King";
        Franquicia franquicia = Franquicia.builder()
                .nombre(nombre)
                .sucursales(new ArrayList<>())
                .build();

        when(franquiciaRepository.save(any())).thenReturn(Mono.just(franquicia));

        StepVerifier.create(franquiciaService.crearFranquicia(nombre))
            .expectNextMatches(f -> f.getNombre().equals("Burger King"))
            .verifyComplete();

        verify(franquiciaRepository).save(any());
    }
}

