package com.publicis.sapient.InvestorHoldingGraph.VertxServices;

import com.publicis.sapient.InvestorHoldingGraph.Exception.BrokenPayloadException;
import com.publicis.sapient.InvestorHoldingGraph.Model.CreateVertex;
import com.publicis.sapient.InvestorHoldingGraph.Model.VertexVO;
import com.publicis.sapient.InvestorHoldingGraph.Model.Vertx;
import com.publicis.sapient.InvestorHoldingGraph.VertxDAO.VertxRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class VertexServices {

    @Autowired
    private VertxRepository vertxRepository;

//    @Transactional
    public ResponseEntity<Object> saveVertexWithDetails(VertexVO vertexVO) {

        CreateVertex createVertex = new CreateVertex();

        Vertx vertx = createVertex.generateVertex(vertexVO);

        vertx = vertxRepository.save(vertx);
        if (vertx.getUniqueid() == null) {
            throw new BrokenPayloadException("payload doen't contain all required data");
        }
        return new ResponseEntity<Object>("Successful", HttpStatus.OK);
    }

    public String saveBulkVertexWithDetails(List<VertexVO> vertexVO) {
        CreateVertex createVertex = new CreateVertex();
        List<Vertx> vertx = createVertex.generateBulkVertex(vertexVO);
        vertxRepository.saveAll(vertx);
        return "Successfully save";
    }

    public List<Vertx> getAllVertex() {
        return vertxRepository.findAll();
    }

    public List<Vertx> getVertxByHoldingId(String id) {
        return vertxRepository.findByUniqueid(id);
    }


}
