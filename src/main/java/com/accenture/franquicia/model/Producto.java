package com.accenture.franquicia.model;


import lombok.*;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class Producto {
	
	private String id = UUID.randomUUID().toString();

	private String nombre;

	private int stock;
}
