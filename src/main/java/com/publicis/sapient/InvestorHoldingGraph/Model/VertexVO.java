package com.publicis.sapient.InvestorHoldingGraph.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class VertexVO {
    private String name;
    private double value;
    private  String type;
}
