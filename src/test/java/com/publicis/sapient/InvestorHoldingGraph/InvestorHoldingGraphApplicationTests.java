package com.publicis.sapient.InvestorHoldingGraph;

import com.publicis.sapient.InvestorHoldingGraph.VertxDAO.AssociateRepository;
import com.publicis.sapient.InvestorHoldingGraph.VertxDAO.VertxRepository;
import com.publicis.sapient.InvestorHoldingGraph.VertxServices.AssociateServices;
import com.publicis.sapient.InvestorHoldingGraph.VertxServices.VertexServices;
import com.publicis.sapient.InvestorHoldingGraph.Model.*;
import org.apache.logging.log4j.core.util.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;


@SpringBootTest
class InvestorHoldingGraphApplicationTests {

    @Autowired
    private VertexServices vertexServices;

    @MockBean
    private VertxRepository vertxRepository;

    @Autowired
    private AssociateServices associateServices;

    @MockBean
    private AssociateRepository associateRepository;

    @Test
    public void saveBulkVertxUserTest() {
        List<VertexVO> listVO = Arrays.asList(new VertexVO("abhi", 30.0, "HOLD"), new VertexVO("pratik", 0.0, "INV"), new VertexVO("Santosh", 0.0, "FUND"));
        CreateVertex createVertex = new CreateVertex();
        List<Vertx> vertx = createVertex.generateBulkVertex(listVO);
        when(vertxRepository.saveAll(vertx)).thenReturn(vertx);
        assertEquals("Successfully save", vertexServices.saveBulkVertexWithDetails(listVO));
    }

    @Test
    public void getAllVertex() {
        List<VertexVO> listVO = Arrays.asList(new VertexVO("abhi", 30.0, "HOLD"), new VertexVO("pratik", 0.0, "INV"), new VertexVO("Santosh", 0.0, "FUND"));
        CreateVertex createVertex = new CreateVertex();
        List<Vertx> vertx = createVertex.generateBulkVertex(listVO);
        when(vertxRepository.saveAll(vertx)).thenReturn(vertx);
        Assert.isNonEmpty(vertexServices.getAllVertex());
    }

    @Test
    public void addBulkAssociation() {
        List<AssociateVertexVO> associateVertexVO = Arrays.asList(new AssociateVertexVO("INV_948f8cf7-db4a-490f-afc8-a42eb2456308", "FUND_3b82ca5b-408f-4b25-882d-32142158f258", ""), new AssociateVertexVO("", "HOLD_948f8567-db4a-490f-afc8-a42eb2456308", "FUND_3b82ca5b-408f-4b25-882d-32142158f258"));
        CreateVertex createVertex = new CreateVertex();
        List<AssociateVertex> associateVertexList = createVertex.generateAssociateVertex(associateVertexVO);
        when(associateRepository.saveAll(associateVertexList)).thenReturn(associateVertexList);
        assertEquals("Successful", associateServices.addBulkAssociation(associateVertexVO));
    }

    @Test
    public void addAssociation() {
        AssociateVertexVO associateVertexVO = new AssociateVertexVO("INV_948f8cf7-db4a-490f-afc8-a42eb2456308", "FUND_3b82ca5b-408f-4b25-882d-32142158f258", "");
        CreateVertex createVertex = new CreateVertex();
        AssociateVertex associateVertex = createVertex.generateAssociateVertex(associateVertexVO);
        when(associateRepository.save(associateVertex)).thenReturn(associateVertex);
        assertEquals("Successful", associateServices.addAssociation(associateVertexVO));
    }

    @Test
    public void fetchAssociationListByFundId() {
        List<AssociateVertexVO> associateVertexVO = Arrays.asList(new AssociateVertexVO("INV_948f8cf7-db4a-490f-afc8-a42eb2456308", "FUND_3b82ca5b-408f-4b25-882d-32142158f258", ""), new AssociateVertexVO("", "HOLD_948f8567-db4a-490f-afc8-a42eb2456308", "FUND_3b82ca5b-408f-4b25-882d-32142158f258"));
        CreateVertex createVertex = new CreateVertex();
        List<AssociateVertex> associateVertexList = createVertex.generateAssociateVertex(associateVertexVO);
        when(associateRepository.saveAll(associateVertexList)).thenReturn(associateVertexList);
        Assert.isNonEmpty(associateServices.calculateHoldingOfFund("FUND_3b82ca5b-408f-4b25-882d-32142158f258"));
    }

    @Test
    public void isFundIdExists() {
        List<AssociateVertexVO> associateVertexVO = Arrays.asList(new AssociateVertexVO("INV_948f8cf7-db4a-490f-afc8-a42eb2456308", "FUND_3b82ca5b-408f-4b25-882d-32142158f258", ""), new AssociateVertexVO("", "HOLD_948f8567-db4a-490f-afc8-a42eb2456308", "FUND_3b82ca5b-408f-4b25-882d-32142158f258"));
        CreateVertex createVertex = new CreateVertex();
        List<AssociateVertex> associateVertexList = createVertex.generateAssociateVertex(associateVertexVO);
        when(associateRepository.saveAll(associateVertexList)).thenReturn(associateVertexList);
        assertFalse(associateServices.isFundIdExist("FUND_3b82ca5b-408f-4b25-882d-32142158f258"));
    }

    @Test
    public void ValidateAssociation() {
        List<AssociateVertexVO> associateVertexVO = Arrays.asList(new AssociateVertexVO("INV_948f8cf7-db4a-490f-afc8-a42eb2456308", "FUND_3b82ca5b-408f-4b25-882d-32142158f258", ""), new AssociateVertexVO("", "HOLD_948f8567-db4a-490f-afc8-a42eb2456308", "FUND_3b82ca5b-408f-4b25-882d-32142158f258"));
        CreateVertex createVertex = new CreateVertex();
        List<AssociateVertex> associateVertexList = createVertex.generateAssociateVertex(associateVertexVO);
        when(associateRepository.saveAll(associateVertexList)).thenReturn(associateVertexList);
        assertFalse(associateServices.ValidationAssociationInsertion(associateVertexList));
    }


}
