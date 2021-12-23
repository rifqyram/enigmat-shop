package com.enigma.enigmat_shop.service.impl;

import com.enigma.enigmat_shop.entity.PurchaseDetail;
import com.enigma.enigmat_shop.repository.PurchaseDetailRepository;
import com.enigma.enigmat_shop.service.PurchaseDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class PurchaseDetailServiceImpl implements PurchaseDetailService {

    @Autowired
    private PurchaseDetailRepository purchaseDetailRepository;

    @Override
    public PurchaseDetail create(PurchaseDetail purchaseDetail) {
        return purchaseDetailRepository.save(purchaseDetail);
    }
}
