package com.publicis.sapient.InvestorHoldingGraph.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class AssociateVertex {
    @Id
    @GeneratedValue
    private int id;
    private String invid;
    private String fundid;
    private String holdingid;
}
