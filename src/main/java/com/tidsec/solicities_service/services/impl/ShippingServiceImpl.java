package com.tidsec.solicities_service.services.impl;

import com.tidsec.solicities_service.entities.*;
import com.tidsec.solicities_service.repositories.*;
import com.tidsec.solicities_service.services.IShippingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ShippingServiceImpl extends GenericServiceImpl<Shipping, Long> implements IShippingService {

    private final IShippingRepository shippingRepository;
    private final IDetailsShippingRepository sdRepo;
    private final IMaterialsRequestRepository materialsRequestRepository;
    private final IShippingDetailRepository shippingDetailRepository;
    private final IStockTakingRepository stockTakingRepository;
    private final IStockTakingMaterialRepository stockTakingMaterialRepository;

    @Override
    protected IGenericRepository<Shipping, Long> getRepo() {
        return shippingRepository;
    }


    @Override
    public Shipping saveTransactional(Shipping shipping, List<ShippingDetail> shippingDetails) throws Exception {

        if (shipping.getMaterialsRequest() != null && shipping.getMaterialsRequest().getIdMaterialsRequest() != null) {
            MaterialsRequest materialsRequest = materialsRequestRepository.findById(shipping.getMaterialsRequest().getIdMaterialsRequest())
                    .orElseThrow(() -> new RuntimeException("Solicitud no encontrada con ID: " + shipping.getMaterialsRequest().getIdMaterialsRequest()));

            shipping.setMaterialsRequest(materialsRequest);
        }

        StockTaking stockTaking = stockTakingRepository.findByProject(shipping.getMaterialsRequest().getIdProject())
                .orElseThrow(() -> new RuntimeException("Inventario del proyecto no encontrado para el proyecto ID: " + shipping.getMaterialsRequest().getIdProject()));

        shippingRepository.save(shipping);

        for (ShippingDetail detail : shippingDetails) {
            ShippingDetail savedDetail = shippingDetailRepository.save(detail);
            sdRepo.saveDetail(shipping.getIdShipping(), savedDetail.getIdShippingDetail());

            StockTakingMaterial existingStock = stockTakingMaterialRepository.findByStockTakingAndMaterial(stockTaking.getIdStockTaking(), detail.getIdMaterial());

            if (existingStock != null) {
                // 6️⃣ Si el material existe, actualizar el stock sumando quantityShipped
                stockTakingMaterialRepository.updateStock(stockTaking.getIdStockTaking(), detail.getIdMaterial(), detail.getQuantityShipped());
            } else {
                // 7️⃣ Si el material no existe, agregarlo con stock inicial igual a quantityShipped
                stockTakingMaterialRepository.insertStock(stockTaking.getIdStockTaking(), detail.getIdMaterial(), detail.getQuantityShipped());
            }
        }



        return shipping;
    }
}
