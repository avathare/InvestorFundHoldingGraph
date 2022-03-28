package com.publicis.sapient.InvestorHoldingGraph.VertxDAO;

import com.publicis.sapient.InvestorHoldingGraph.Model.AssociateVertex;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AssociateRepository extends JpaRepository<AssociateVertex, Integer> {
    List<AssociateVertex> findByFundid(String fundid);

    boolean existsByFundid(String id);

    List<AssociateVertex> findByInvidAndFundidIsNotNull(String invid);

//    @Query(value = "SELECT * FROM AssociateVertex WHERE invid = ?1 and fundid = ?2", nativeQuery = true)
//    User findBy(String invid,String fundid);

    Optional<AssociateVertex> findByInvidAndFundidAndHoldingid(String invid, String fundid, String holdingid);

    boolean existsByInvidAndFundidAndHoldingid(String invid, String fundid, String holdingid);

    boolean existsByInvid(String invdid);

    List<AssociateVertex> findByInvid(String invid);
}
