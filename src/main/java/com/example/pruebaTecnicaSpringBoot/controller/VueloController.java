package com.example.pruebaTecnicaSpringBoot.controller;

import com.example.pruebaTecnicaSpringBoot.dto.VueloDto;
import com.example.pruebaTecnicaSpringBoot.model.Vuelo;
import com.example.pruebaTecnicaSpringBoot.service.VueloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/vuelos")
public class VueloController {

    @Autowired
    private VueloService service;

   /* Anotamos los @RequestPar(required = false) para que no sean campos obligatorios
     y si no los ponemos no se tengan en cuenta para el filtro.*/
    @GetMapping
    public ResponseEntity<List<VueloDto>> listarVuelos(@RequestParam(required = false) String empresa,
                                                      @RequestParam(required = false) String lugarLlegada,
                                                      @RequestParam(required = false) LocalDate fechaDeSalida,
                                                      @RequestParam(required = false) String ordenarPor){
        return ResponseEntity.ok()
                .body(service.listarVuelosFiltrados(empresa,lugarLlegada,fechaDeSalida,ordenarPor));
    }

    @PostMapping
    public ResponseEntity<Vuelo> crearVuelo(@RequestBody Vuelo vuelo){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.crearVuelo(vuelo));
    }


    @GetMapping("{id}")
    public ResponseEntity<Vuelo> listarVueloId(@PathVariable int id){
        return ResponseEntity.ok().body(service.listarVueloId(id));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> eliminarVuelo(@PathVariable int id){
        service.eliminarVuelo(id);
        return ResponseEntity.ok("Vuelo eliminado correctamente.");
    }

    @PutMapping("{id}")
    public ResponseEntity<Vuelo> actualizarVuelo(@PathVariable int id, @RequestBody Vuelo vueloActualizado){
        return ResponseEntity.ok().body(service.actualizarVuelo(id, vueloActualizado));
    }

}
