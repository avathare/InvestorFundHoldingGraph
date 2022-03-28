package com.publicis.sapient.InvestorHoldingGraph.VertxServices;

import com.publicis.sapient.InvestorHoldingGraph.Exception.BrokenPayloadException;
import com.publicis.sapient.InvestorHoldingGraph.Model.AssociateVertex;
import com.publicis.sapient.InvestorHoldingGraph.Model.AssociateVertexVO;
import com.publicis.sapient.InvestorHoldingGraph.Model.CreateVertex;
import com.publicis.sapient.InvestorHoldingGraph.VertxDAO.AssociateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class AssociateServices {

    @Autowired
    private AssociateRepository associateRepository;

    public String addAssociation(AssociateVertexVO associateVertexVO) {
        CreateVertex createVertex = new CreateVertex();
        AssociateVertex associateVertex = createVertex.generateAssociateVertex(associateVertexVO);
        associateRepository.save(associateVertex);
        return "Successful";
    }

    public String addBulkAssociation(List<AssociateVertexVO> associateVertexVO) {
        String result = "Successful";
        CreateVertex createVertex = new CreateVertex();
        List<AssociateVertex> associateVertexList = createVertex.generateAssociateVertex(associateVertexVO);
        boolean isPresent = ValidationAssociationInsertion(associateVertexList);
        if (!isPresent) {
            associateRepository.saveAll(associateVertexList);
        } else {
            throw new BrokenPayloadException("duplicate Association present");
        }

        return result;
    }

    public List<AssociateVertex> calculateHoldingOfFund(String fundid) {

        return associateRepository.findByFundid(fundid);
    }

    public List<AssociateVertex> calculateHoldingOfInv(String invid) {
        return associateRepository.findByInvidAndFundidIsNotNull(invid);
        //return associateRepository.findByInvid(invid);
    }

    public boolean isFundIdExist(String fundid) {
        return associateRepository.existsByFundid(fundid);
    }

    public boolean isInvIdExist(String invdid) {
        return associateRepository.existsByInvid(invdid);
    }

    public boolean ValidationAssociationInsertion(List<AssociateVertex> associateVertexList) {
        boolean result = false;
        for (AssociateVertex associateVertex : associateVertexList) {
            result = associateRepository.existsByInvidAndFundidAndHoldingid(associateVertex.getInvid(), associateVertex.getFundid(), associateVertex.getHoldingid());
            if (result) {
                break;
            }
        }
        return result;
    }


}
