package com.tidsec.solicities_service.repositories;

import com.tidsec.solicities_service.entities.StockTakingMaterial;
import com.tidsec.solicities_service.entities.StockTakingMaterialPK;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface IStockTakingMaterialRepository extends IGenericRepository<StockTakingMaterial, StockTakingMaterialPK> {

    // 1️⃣ Verificar si el material ya existe en el inventario del proyect
    @Query("SELECT stm FROM StockTakingMaterial stm WHERE stm.stockTaking.idStockTaking = :idStockTaking AND stm.material.idMaterial = :idMaterial")
    StockTakingMaterial findByStockTakingAndMaterial(@Param("idStockTaking") Long idStockTaking, @Param("idMaterial") Long idMaterial);

    // 2️⃣ Actualizar el stock de un material existente
    @Transactional
    @Modifying
    @Query("UPDATE StockTakingMaterial stm SET stm.stock = stm.stock + :quantity WHERE stm.stockTaking.idStockTaking = :idStockTaking AND stm.material.idMaterial = :idMaterial")
    void updateStock(@Param("idStockTaking") Long idStockTaking, @Param("idMaterial") Long idMaterial, @Param("quantity") Double quantity);

    // 2️⃣ Actualizar el stock de un material restando lo utilizado
    @Transactional
    @Modifying
    @Query("UPDATE StockTakingMaterial stm SET stm.stock = stm.stock - :quantity WHERE stm.stockTaking.idStockTaking = :idStockTaking AND stm.material.idMaterial = :idMaterial")
    void updatedStock(@Param("idStockTaking") Long idStockTaking, @Param("idMaterial") Long idMaterial, @Param("quantity") Double quantity);


    // 3️⃣ Insertar un nuevo registro si el material no existe
    @Transactional
    @Modifying
    @Query(value = "INSERT INTO stock_taking_material (id_stock_taking, id_material, stock, status) VALUES (:idStockTaking, :idMaterial, :quantity, 1)", nativeQuery = true)
    void insertStock(@Param("idStockTaking") Long idStockTaking, @Param("idMaterial") Long idMaterial, @Param("quantity") Double quantity);
}