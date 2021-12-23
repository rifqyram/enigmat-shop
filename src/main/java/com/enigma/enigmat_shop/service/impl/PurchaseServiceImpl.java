package com.enigma.enigmat_shop.service.impl;

import com.enigma.enigmat_shop.entity.Product;
import com.enigma.enigmat_shop.entity.Purchase;
import com.enigma.enigmat_shop.entity.PurchaseDetail;
import com.enigma.enigmat_shop.repository.PurchaseRepository;
import com.enigma.enigmat_shop.service.ProductService;
import com.enigma.enigmat_shop.service.PurchaseDetailService;
import com.enigma.enigmat_shop.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class PurchaseServiceImpl implements PurchaseService {

    @Autowired
    private PurchaseRepository purchaseRepository;

    @Autowired
    private ProductService productService;

    @Autowired
    private PurchaseDetailService purchaseDetailService;

    @Override
    public Purchase transaction(Purchase purchase) {
        Purchase save = purchaseRepository.save(purchase);

        for (PurchaseDetail purchaseDetail : save.getPurchaseDetails()) {
            Product product = productService.getById(purchaseDetail.getProduct().getId());
            product.setStock(product.getStock() - purchaseDetail.getQuantity());
            productService.update(product);

            purchaseDetail.setPurchase(save);
            purchaseDetail.setProduct(product);
            purchaseDetail.setProductPrice(product.getProductPrice());
            purchaseDetail.setTotalPrice(product.getProductPrice() * purchaseDetail.getQuantity());

            purchaseDetailService.create(purchaseDetail);
        }

        return save;
    }
}
