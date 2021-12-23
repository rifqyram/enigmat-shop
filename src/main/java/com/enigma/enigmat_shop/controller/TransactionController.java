package com.enigma.enigmat_shop.controller;

import com.enigma.enigmat_shop.entity.Purchase;
import com.enigma.enigmat_shop.service.PurchaseService;
import com.enigma.enigmat_shop.util.WebResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transaction")
public class TransactionController {

    @Autowired
    private PurchaseService purchaseService;

    @PostMapping
    public ResponseEntity<WebResponse<Purchase>> createTransaction(@RequestBody Purchase purchase) {
        Purchase transaction = purchaseService.transaction(purchase);
        WebResponse<Purchase> response = new WebResponse<>(
                "Transaction success",
                transaction);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

}
