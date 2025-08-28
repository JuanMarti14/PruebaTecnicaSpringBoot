package com.example.pruebaTecnicaSpringBoot.utils;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class FechaUtils {
    public  static Long calcularDuracioDias(LocalDate inicio, LocalDate fin){
        return ChronoUnit.DAYS.between(inicio, fin);
    }
}
