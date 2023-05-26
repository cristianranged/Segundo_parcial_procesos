package com.procesos.automovil.models;

import lombok.Data;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


@Data
@Entity

public class Automovil {
    @Id
    private Long id;
    private String car;
    private String car_model;
    private String car_color;
    private int car_model_year;
    private String car_vin;
    private String price;
    private boolean availability;
    @ManyToOne
    @JoinColumn(name="user_id", nullable=false)
    private User user;

}
