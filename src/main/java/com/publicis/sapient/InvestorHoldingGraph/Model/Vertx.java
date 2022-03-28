package com.publicis.sapient.InvestorHoldingGraph.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Vertx {
    @Id
    @GeneratedValue
    private int id;
    private String name;
    private double v_value;
    private String type;
    private String uniqueid;

}
