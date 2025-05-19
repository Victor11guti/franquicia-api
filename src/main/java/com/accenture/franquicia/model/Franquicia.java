package com.accenture.franquicia.model;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document("franquicias")
public class Franquicia {
	
	 @Id
	    private String id;

	    private String nombre;

	    private List<Sucursal> sucursales = new ArrayList<>();

}
