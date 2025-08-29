package com.example.pruebaTecnicaSpringBoot.repository;

import com.example.pruebaTecnicaSpringBoot.model.Vuelo;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.*;


@Repository
public class VueloRepository {

    private final List<Vuelo> vuelos = new ArrayList<>();
    private int contadorId = 0;

    //Para crear la lista precargada de vuelos
    public VueloRepository() {
        crearListaPrecargadaDeVuelos();
    }

    public void crearListaPrecargadaDeVuelos() {
        save(new Vuelo(0, "HKJ-014V", "Ryanair", "Valencia", "Roma", LocalDate.of(2025, 10, 1), LocalDate.of(2025, 10, 1)));
        save(new Vuelo(0, "HJT-096M", "Iberia", "Valencia", "Aosta", LocalDate.of(2025, 11, 1), LocalDate.of(2025, 11, 1)));
        save(new Vuelo(0, "HTK-054S", "Ryanair", "Valencia", "Dublín", LocalDate.of(2025, 12, 1), LocalDate.of(2025, 12, 1)));
        save(new Vuelo(0, "RSM-072L", "Air Europa", "Alicante", "Amsterdam", LocalDate.of(2025, 10, 10), LocalDate.of(2025, 10, 11)));
        save(new Vuelo(0, "JLK-024P", "Lufthansa", "Valencia", "Eindhoven", LocalDate.of(2025, 11, 10), LocalDate.of(2025, 11, 10)));
        save(new Vuelo(0, "HLM-043E", "Turkish Airlines", "Madrid", "Munich", LocalDate.of(2025, 10, 9), LocalDate.of(2025, 10, 10)));
        save(new Vuelo(0, "VAL-214L", "Iberia", "Sevilla", "Mallorca", LocalDate.of(2025, 11, 15), LocalDate.of(2025, 11, 15)));
        save(new Vuelo(0, "ALL-321P", "Air Europa", "Alicante", "Liverpool", LocalDate.of(2025, 11, 14), LocalDate.of(2025, 11, 14)));
        save(new Vuelo(0, "BAR-056V", "Ryanair", "Barcelona", "Berlin", LocalDate.of(2025, 10, 11), LocalDate.of(2025, 10, 11)));
        save(new Vuelo(0, "MAD-044M", "Iberia", "Madrid", "Sydney", LocalDate.of(2025, 9, 12), LocalDate.of(2025, 9, 14)));
    }


    public Vuelo save(Vuelo vuelo) {
        vuelo.setId(++contadorId);
        vuelos.add(vuelo);
        return vuelo;
    }

    public List<Vuelo> findAll() {
        return vuelos;
    }

    public Vuelo findById(int id) {
        return vuelos.stream().filter(v -> v.getId() == id).findFirst()
                .orElseThrow(() -> new RuntimeException("Vuelo con id " + id + " no encontrado."));
    }

    public void delete(int id) {
        Vuelo vueloAEliminar = findById(id);
        vuelos.remove(vueloAEliminar);
    }

    public List<Vuelo> search(String empresa, String destino, LocalDate fechaSalida) {
        return vuelos.stream().filter(v -> empresa == null || v.getEmpresa().equalsIgnoreCase(empresa))
                .filter(v -> destino == null || v.getLugarLlegada().equalsIgnoreCase(destino))
                .filter(v -> fechaSalida == null || v.getFechaSalida().equals(fechaSalida))
                .toList();
    }


}
