package com.tidsec.solicities_service.services.impl;

import com.tidsec.solicities_service.entities.Accrual;
import com.tidsec.solicities_service.entities.AccrualDetail;
import com.tidsec.solicities_service.entities.StockTaking;
import com.tidsec.solicities_service.entities.StockTakingMaterial;
import com.tidsec.solicities_service.repositories.*;
import com.tidsec.solicities_service.services.IAccrualService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AccrualServiceImpl extends GenericServiceImpl<Accrual, Long> implements IAccrualService {

    private final IAccrualRepository accrualRepository;
    private final IStockTakingRepository stockTakingRepository;
    private final IAccrualDetailRepository accrualDetailRepository;
    private final IAccrualDetailsRepository adRepo;
    private final IStockTakingMaterialRepository stockTakingMaterialRepository;

    @Override
    protected IGenericRepository<Accrual, Long> getRepo() {
        return accrualRepository;
    }

    @Override
    public Accrual saveTransactional(Accrual accrual, List<AccrualDetail> accrualDetails) throws Exception {
        if (accrual.getStockTaking() != null && accrual.getStockTaking().getIdStockTaking() != null) {
            StockTaking stockTaking = stockTakingRepository.findById(accrual.getStockTaking().getIdStockTaking())
                    .orElseThrow(() -> new RuntimeException("Inventario no encontrado con ID: " + accrual.getStockTaking().getIdStockTaking()));
            accrual.setStockTaking(stockTaking);
        }

        StockTaking stockTaking = stockTakingRepository.findById(accrual.getStockTaking().getIdStockTaking())
                .orElseThrow(() -> new RuntimeException("Inventario no encontrado con ID: " + accrual.getStockTaking().getIdStockTaking()));


        accrualRepository.save(accrual);

        for (AccrualDetail detail : accrualDetails) {
            AccrualDetail savedDetail = accrualDetailRepository.save(detail);
            adRepo.saveDetail(accrual.getIdAccrual(), savedDetail.getIdAccrualDetail());

            StockTakingMaterial existingStock = stockTakingMaterialRepository.findByStockTakingAndMaterial(stockTaking.getIdStockTaking(), detail.getIdMaterial());

            if ((existingStock != null && existingStock.getStock() > 0) && (existingStock.getStock() - detail.getQuantityUsed() >= 0)) {
                // 6️⃣ Si el material existe, actualizar el stock restando quantityUsed
                stockTakingMaterialRepository.updatedStock(accrual.getStockTaking().getIdStockTaking(), detail.getIdMaterial(), detail.getQuantityUsed());
            } else {
                // 7️⃣ Si el material no existe y no hay stock, se envia un mensaje de error
                throw new RuntimeException("Stock insuficiente o material no encontrado para el ID: " + detail.getIdMaterial());
            }
        }
        return accrual;
    }
}
