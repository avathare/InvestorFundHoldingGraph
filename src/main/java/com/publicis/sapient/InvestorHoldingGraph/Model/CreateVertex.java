package com.publicis.sapient.InvestorHoldingGraph.Model;

import com.publicis.sapient.InvestorHoldingGraph.Constants.Constants;
import com.publicis.sapient.InvestorHoldingGraph.Exception.BrokenPayloadException;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CreateVertex {

    public Vertx generateVertex(VertexVO vertexVO) {
        Vertx vertx = new Vertx();

        if (!validatePayload(vertexVO)) {
            throw new BrokenPayloadException("Type should be present");
        }
        //----set Name---//
        vertx.setName(vertexVO.getName());

        //---set v_value---//
        if (!CreateVertex.checkNull(vertexVO)) {
            vertx.setV_value(vertexVO.getValue());
        } else {
            vertx.setV_value(0.0);
        }

        //---set Type and unique id----//
        if (vertexVO.getType().contains(Constants.INV)) {
            vertx.setType(Constants.INV);
            vertx.setUniqueid(Constants.INV + "_" + UUID.randomUUID());
        } else if (vertexVO.getType().contains(Constants.HOLD)) {
            vertx.setType(Constants.HOLD);
            vertx.setUniqueid(Constants.HOLD + "_" + UUID.randomUUID());
        } else if (vertexVO.getType().contains(Constants.FUND)) {
            vertx.setType(Constants.FUND);
            vertx.setUniqueid(Constants.FUND + "_" + UUID.randomUUID());
        }
        return vertx;
    }

    public List<Vertx> generateBulkVertex(List<VertexVO> vertexVOBulk) {
        List<Vertx> vertexs = new ArrayList<>();
        for (VertexVO vertexVO : vertexVOBulk) {

            if (!validatePayload(vertexVO)) {
                throw new BrokenPayloadException("Type should be present");
            }
            Vertx vertx = new Vertx();
            //----set Name---//
            vertx.setName(vertexVO.getName());

            //---set v_value---//
            if (!CreateVertex.checkNull(vertexVO)) {
                vertx.setV_value(vertexVO.getValue());
            } else {
                vertx.setV_value(0.0);
            }

            //---set Type and unique id----//
            if (vertexVO.getType().contains(Constants.INV)) {
                vertx.setType(Constants.INV);
                vertx.setUniqueid(Constants.INV + "_" + UUID.randomUUID());
            } else if (vertexVO.getType().contains(Constants.HOLD)) {
                vertx.setType(Constants.HOLD);
                vertx.setUniqueid(Constants.HOLD + "_" + UUID.randomUUID());
            } else if (vertexVO.getType().contains(Constants.FUND)) {
                vertx.setType(Constants.FUND);
                vertx.setUniqueid(Constants.FUND + "_" + UUID.randomUUID());
            }
            vertexs.add(vertx);
        }
        return vertexs;
    }

    public static boolean checkNull(VertexVO vertexVO) {
        boolean result;
        try {
            double v_value = vertexVO.getValue();
            result = false;
        } catch (NullPointerException e) {
            result = true;
        }
        return result;
    }

    public AssociateVertex generateAssociateVertex(AssociateVertexVO associateVertexVO) {
        AssociateVertex associateVertex = new AssociateVertex();
        if (validateParam(associateVertexVO, Constants.INV) && validateParam(associateVertexVO, Constants.FUND)) {
            associateVertex.setFundid(associateVertexVO.getFundid());
            associateVertex.setInvid(associateVertexVO.getInvid());
            associateVertex.setHoldingid(null);
        } else if (validateParam(associateVertexVO, Constants.HOLD) && validateParam(associateVertexVO, Constants.FUND)) {
            associateVertex.setFundid(associateVertexVO.getFundid());
            associateVertex.setInvid(null);
            associateVertex.setHoldingid(associateVertexVO.getHoldingid());
        }
        return associateVertex;
    }

    public List<AssociateVertex> generateAssociateVertex(List<AssociateVertexVO> associateVertexVOS) {
        List<AssociateVertex> associateVertexList = new ArrayList<>();
        for (AssociateVertexVO associateVertexVO : associateVertexVOS) {
            AssociateVertex associateVertex = new AssociateVertex();
            if (validateParam(associateVertexVO, Constants.INV) && validateParam(associateVertexVO, Constants.FUND)) {
                associateVertex.setFundid(associateVertexVO.getFundid());
                associateVertex.setInvid(associateVertexVO.getInvid());
                associateVertex.setHoldingid(null);
            } else if (validateParam(associateVertexVO, Constants.HOLD) && validateParam(associateVertexVO, Constants.FUND)) {
                associateVertex.setFundid(associateVertexVO.getFundid());
                associateVertex.setInvid(null);
                associateVertex.setHoldingid(associateVertexVO.getHoldingid());
            }
            associateVertexList.add(associateVertex);
        }
        return associateVertexList;
    }

    public boolean validateParam(AssociateVertexVO associateVertex, String label) {
        boolean result = false;
        System.out.println("validateParam -->AssociateVertexVO:" + associateVertex.toString());
        try {
            if (label.equalsIgnoreCase(Constants.INV)) {
                String value = associateVertex.getInvid();
                if (associateVertex.getInvid().contains(Constants.INV)) {
                    result = true;
                }

            } else if (label.equalsIgnoreCase(Constants.HOLD)) {
                String value = associateVertex.getHoldingid();
                if (associateVertex.getHoldingid().contains(Constants.HOLD)) {
                    result = true;
                }
            } else if (label.equalsIgnoreCase(Constants.FUND)) {
                String value = associateVertex.getFundid();
                if (associateVertex.getFundid().contains(Constants.FUND)) {
                    result = true;
                }
            }
        } catch (Exception e) {
            result = false;
        }
        return result;
    }

    public boolean validatePayload(VertexVO vertexVO) {


        return vertexVO.getType() == null ? false : true;

    }
}
