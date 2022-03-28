package com.publicis.sapient.InvestorHoldingGraph.VertxDAO;

import com.publicis.sapient.InvestorHoldingGraph.Model.Vertx;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VertxRepository extends JpaRepository<Vertx,Integer> {
    List<Vertx> findByUniqueid(String id);
}
