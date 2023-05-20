package com.procesos.automovil.models;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Automovil {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "Cars")
    private String car;
    @Column(name = "MODEL")
    private String car_model;
    @Column(name = "COLOR")
    private String car_color;
    @Column(name = "YEAR")
    private int car_model_year;
    @Column(name = "VIN")
    private String car_vin;
    @Column(name = "PRICE")
    private String price;
    @Column(name = "AVAILABILITY")
    private boolean availability;
}
