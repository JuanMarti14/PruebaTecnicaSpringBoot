package com.example.pruebaTecnicaSpringBoot.service;

import com.example.pruebaTecnicaSpringBoot.model.Vuelo;
import com.example.pruebaTecnicaSpringBoot.repository.VueloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class VueloService {

    @Autowired
    VueloRepository repository;

    //Crear vuelo con los campos obligatorios
    public Vuelo crearVuelo(Vuelo vuelo){
        if (vuelo.getNombreVuelo() == null || vuelo.getNombreVuelo().isBlank()) {
            throw new IllegalArgumentException("El nombre del vuelo es obligatorio");
        }
        if (vuelo.getLugarSalida()==null || vuelo.getLugarSalida().isBlank()){
            throw new IllegalArgumentException("El lugar de salida es un campo obligatorio.");
        }
        if (vuelo.getLugarLlegada()==null || vuelo.getLugarLlegada().isBlank()){
            throw new IllegalArgumentException("El lugar de llegada es un campo obligatorio.");
        }
        if (vuelo.getFechaSalida()==null || vuelo.getFechaSalida().isBefore(LocalDate.now())){
            throw new IllegalArgumentException("La fecha salida es obligatoria y tiene que ser superior a la fecha actual.");
        }
        if (vuelo.getFechaLlegada()==null || vuelo.getFechaLlegada().isBefore(vuelo.getFechaSalida())){
            throw new IllegalArgumentException("La fecha llegada es obligatoria y tiene que ser superior a la fecha salida.");
        }
        return repository.save(vuelo);
    }

    public List<Vuelo> listarVuelos(){
        return repository.findAll();
    }
}
