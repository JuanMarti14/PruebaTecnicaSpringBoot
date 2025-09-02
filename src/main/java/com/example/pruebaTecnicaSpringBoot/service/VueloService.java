package com.example.pruebaTecnicaSpringBoot.service;

import com.example.pruebaTecnicaSpringBoot.dto.VueloDto;
import com.example.pruebaTecnicaSpringBoot.model.Vuelo;
import com.example.pruebaTecnicaSpringBoot.repository.VueloRepository;
import com.example.pruebaTecnicaSpringBoot.utils.FechaUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class VueloService {

    public Vuelo listarVueloId(int id) {
        return repository.findById(id);
    }

    @Autowired
    private VueloRepository repository;

    //Validar vuelo con los campos obligatorios
    public void validarVuelos(Vuelo vuelo) {
        if (vuelo.getEmpresa() == null || vuelo.getEmpresa().isBlank()) {
            throw new IllegalArgumentException("El nombre de la compañia aerea es obligatorio");
        }
        if (vuelo.getNombreVuelo() == null || vuelo.getNombreVuelo().isBlank()) {
            throw new IllegalArgumentException("El nombre del vuelo es obligatorio");
        }
        if (vuelo.getLugarSalida() == null || vuelo.getLugarSalida().isBlank()) {
            throw new IllegalArgumentException("El lugar de salida es un campo obligatorio.");
        }
        if (vuelo.getLugarLlegada() == null || vuelo.getLugarLlegada().isBlank()) {
            throw new IllegalArgumentException("El lugar de llegada es un campo obligatorio.");
        }
        if (vuelo.getFechaSalida() == null || vuelo.getFechaSalida().isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("La fecha salida es obligatoria y tiene que ser superior a la fecha actual.");
        }
        if (vuelo.getFechaLlegada() == null || vuelo.getFechaLlegada().isBefore(vuelo.getFechaSalida())) {
            throw new IllegalArgumentException("La fecha llegada es obligatoria y tiene que ser superior a la fecha salida.");
        }
    }


    public Vuelo crearVuelo(Vuelo vuelo) {
        validarVuelos(vuelo);
        return repository.save(vuelo);
    }

    public List<VueloDto> listarVuelos() {
        return new ArrayList<>(repository.findAll().stream().sorted(Comparator.comparing(Vuelo::getFechaSalida)).map(this::convertirVueloDto)
                .collect(Collectors.toList()));
    }

    //Convertir los Vuelos en VuelosDto
    public VueloDto convertirVueloDto(Vuelo vuelo) {
        VueloDto dto = new VueloDto();
        dto.setId(vuelo.getId());
        dto.setNombreVuelo(vuelo.getNombreVuelo());
        dto.setEmpresa(vuelo.getEmpresa());
        dto.setLugarSalida(vuelo.getLugarSalida());
        dto.setLugarLlegada(vuelo.getLugarLlegada());
        dto.setFechaSalida(vuelo.getFechaSalida());
        dto.setFechaLlegada(vuelo.getFechaLlegada());
        dto.setDuracionVuelo(FechaUtils.calcularDuracioDias(vuelo.getFechaSalida(), vuelo.getFechaLlegada()));
        return dto;
    }

    //Listar los Vuelos con los filtros aplicados.
    public List<VueloDto> listarVuelosFiltrados(String empresa, String lugarLlegada, LocalDate fechaSalida, String ordenarPor) {
        return repository.findAll().stream()
                .filter(v -> empresa == null || v.getEmpresa().equalsIgnoreCase(empresa))
                .filter(v -> lugarLlegada == null || v.getLugarLlegada().equalsIgnoreCase(lugarLlegada))
                .filter(v -> fechaSalida == null || v.getFechaSalida().equals(fechaSalida))
                .sorted(getComparator(ordenarPor)).map(this::convertirVueloDto)
                .collect(Collectors.toList());
    }

    //Establecer el criterio de ordenación de la lista
    private Comparator<Vuelo> getComparator(String ordenarPor) {
        //Si recibe el valor lugarLlegada en el @RequestPAram ordena por lugarLlegada
        if (ordenarPor != null && ordenarPor.equalsIgnoreCase("lugarLlegada")) {
            return Comparator.comparing(Vuelo::getLugarLlegada);
        }
        // Si recibe cualquier otro valor ordena por
        return Comparator.comparing(Vuelo::getFechaSalida);
    }

    public void eliminarVuelo(int id) {
        repository.delete(id);
    }

    public Vuelo actualizarVuelo(int id, Vuelo vueloActualizado) {
        repository.findById(id);
        validarVuelos(vueloActualizado);
        return repository.update(id, vueloActualizado);
    }

}