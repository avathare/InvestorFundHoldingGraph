package com.publicis.sapient.InvestorHoldingGraph.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class AssociateVertexVO {
    private String invid;
    private String fundid;
    private String holdingid;
}
