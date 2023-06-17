package com.procesos.automovil.models;

import lombok.Data;

import javax.persistence.*;


@Data
@Entity

public class Automovil {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "car",length = 50, nullable = false )
    private String car;
    @Column(name = "car_model",length = 50, nullable = false )
    private String car_model;
    @Column(name = "car_color",length = 20, nullable = false )
    private String car_color;
    @Column(name = "car_model_year",length = 4, nullable = false )
    private int car_model_year;
    @Column(name = "car_vin",length = 30, nullable = false )
    private String car_vin;
    @Column(name = "price", nullable = false )
    private String price;
    private boolean availability;
    @ManyToOne
    @JoinColumn(name="user_id", nullable=false)
    private User user;

}
