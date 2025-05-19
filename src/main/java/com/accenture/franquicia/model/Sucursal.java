package com.accenture.franquicia.model;

import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Sucursal {
	 private String id = UUID.randomUUID().toString();

	    private String nombre;

	    private List<Producto> productos = new ArrayList<>();

}
