package com.tidsec.solicities_service.repositories;

import com.tidsec.solicities_service.entities.StockTaking;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IStockTakingRepository extends IGenericRepository<StockTaking, Long> {

    @Query("SELECT st FROM StockTaking st WHERE st.project.idProject = :idProject")
    Optional<StockTaking> findByProject(@Param("idProject") Long idProject);
}
