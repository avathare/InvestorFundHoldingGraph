package com.publicis.sapient.InvestorHoldingGraph.Controller;

import com.publicis.sapient.InvestorHoldingGraph.Exception.FundIdNotPresentException;
import com.publicis.sapient.InvestorHoldingGraph.Model.AssociateVertex;
import com.publicis.sapient.InvestorHoldingGraph.Model.AssociateVertexVO;
import com.publicis.sapient.InvestorHoldingGraph.Model.VertexVO;
import com.publicis.sapient.InvestorHoldingGraph.Model.Vertx;
import com.publicis.sapient.InvestorHoldingGraph.VertxServices.AssociateServices;
import com.publicis.sapient.InvestorHoldingGraph.VertxServices.VertexServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController("/")
public class MainController {

    @Autowired
    private VertexServices vertexServices;

    @Autowired
    private AssociateServices associateServices;

    @GetMapping("/getAllVertex")
    public List<Vertx> getAllVertex() {
        return vertexServices.getAllVertex();
    }

    @PostMapping("/addVertex")
    public ResponseEntity<Object> addVertex(@RequestBody VertexVO vertexVO) {
        return vertexServices.saveVertexWithDetails(vertexVO);

    }

    @PostMapping("/addBulkVertex")
    @RequestMapping(value = "/", method = RequestMethod.POST, produces = {"application/json"},consumes = {"application/json"})
    public String addBulkVertex(@RequestBody List<VertexVO> vertexVO) {
        vertexServices.saveBulkVertexWithDetails(vertexVO);
        return "saved Successfully";
    }

    @PostMapping("/addAssociation")
    public String saveAssociation(@RequestBody AssociateVertexVO associateVertexVO) {
        return associateServices.addAssociation(associateVertexVO);
    }

    @PostMapping("/addBulkAssociation")
    public String saveBulkAssociation(@RequestBody List<AssociateVertexVO> associateVertexVO) {
        return associateServices.addBulkAssociation(associateVertexVO);
    }

    @GetMapping("/healthz")
    public String healthz() {
        return "{\"Message\":\"Application Working\"}";
    }

    @PostMapping("/getHolding/fund/{fundId}")
    public ResponseEntity<Object> calculateFundHolding(@PathVariable String fundId, @RequestBody List<String> excludeHoldings) {
        double result = 0.0;
        if (associateServices.isFundIdExist(fundId)) {
            List<AssociateVertex> associateVertexList = associateServices.calculateHoldingOfFund(fundId);
            for (AssociateVertex asvt : associateVertexList) {
                if (asvt.getHoldingid() != null) {
                    String holdingId = asvt.getHoldingid();
                    List<Vertx> vertes = vertexServices.getVertxByHoldingId(holdingId);
                    if (!excludeHoldings.isEmpty()) {
                        vertes = excludeHolding(vertes, excludeHoldings);
                    }
                    result = result + (100 * calculateResult(vertes));
                }
            }
            return new ResponseEntity<Object>(result, HttpStatus.OK);
        } else {
            throw new FundIdNotPresentException();
        }

    }

    @GetMapping("/getHolding/investor/{invId}")
    public ResponseEntity<Object> calculateInvestorHolding(@PathVariable String invId) {
        double result = 0.0;
        if (associateServices.isInvIdExist(invId)) {
            List<AssociateVertex> associateVertexListOfInv = associateServices.calculateHoldingOfInv(invId);
            for (AssociateVertex associatevertex : associateVertexListOfInv) {
                List<AssociateVertex> associateVertexList = associateServices.calculateHoldingOfFund(associatevertex.getFundid());
                for (AssociateVertex asvt : associateVertexList) {
                    if (asvt.getHoldingid() != null) {
                        String holdingId = asvt.getHoldingid();
                        List<Vertx> vertes = vertexServices.getVertxByHoldingId(holdingId);
                        result = result + (100 * calculateResult(vertes));
                    }
                }
            }
            return new ResponseEntity<Object>(result, HttpStatus.OK);
        } else {
            throw new FundIdNotPresentException();
        }
    }

    private double calculateResult(List<Vertx> vertes) {
        return vertes.stream()
                .map(vertx -> vertx.getV_value())
                .collect(Collectors.summingDouble(Double::doubleValue));
    }

    private List<Vertx> excludeHolding(List<Vertx> vertes, List<String> excludeHolding) {
        List<Vertx> vertxes = new ArrayList<>();
        for (Vertx vertex : vertes) {
            for (String h_id : excludeHolding) {
                if (!vertex.getUniqueid().equals(h_id)) {
                    vertxes.add(vertex);
                }
            }
        }
        return vertxes;
    }
}
