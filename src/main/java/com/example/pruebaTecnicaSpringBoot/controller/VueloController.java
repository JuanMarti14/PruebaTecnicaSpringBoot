package com.example.pruebaTecnicaSpringBoot.controller;

import com.example.pruebaTecnicaSpringBoot.model.Vuelo;
import com.example.pruebaTecnicaSpringBoot.service.VueloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vuelos")
public class VueloController {

    @Autowired
    VueloService service;

    @GetMapping
    public List<Vuelo> listarVuelos(){
        return service.listarVuelos();
    }

    @PostMapping
    public Vuelo crearVuelo(@RequestBody Vuelo vuelo){
        return service.crearVuelo(vuelo);
    }
}
