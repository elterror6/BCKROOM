package es.uclm.bckroom;


import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import es.uclm.bckroom.business.domain.Comodidad;
import es.uclm.bckroom.persistence.ComodidadDAO;

@Configuration
public class DataInitializer {
	@Bean
    CommandLineRunner cargarComodidades(ComodidadDAO repo) {
        return args -> {

            if (repo.count() == 0) {
                repo.saveAll(List.of(
                    new Comodidad(null, "WiFi"),
                    new Comodidad(null, "Piscina"),
                    new Comodidad(null, "Parking"),
                    new Comodidad(null, "Aire Acondicionado"),
                    new Comodidad(null, "Calecfacción"),
                    new Comodidad(null, "Admite Mascotas")
                ));
            }
        };
    }
}