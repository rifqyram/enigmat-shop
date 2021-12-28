package com.enigma.enigmat_shop.controller;

import com.enigma.enigmat_shop.dto.WalletDTO;
import com.enigma.enigmat_shop.util.WebResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

@RestController
@RequestMapping("/wallets")
public class WalletRestTemplate {

    @Autowired
    private RestTemplate restTemplate;

    @PostMapping
    public ResponseEntity<WalletDTO> createNewWallet(@RequestBody WalletDTO wallet) {
        String url = "http://localhost:8090/wallets";

        WalletDTO walletDTO = new WalletDTO(wallet.getPhoneNumber(), wallet.getBalance());


        // Contoh kalo ada pake API KEY dari luar
//        HttpHeaders httpHeaders = new HttpHeaders();
//        httpHeaders.set("API-KEY", "");
//
//        HttpEntity<WalletDTO> walletDTOHttpEntity = new HttpEntity<>(walletDTO, httpHeaders);
//
//        ResponseEntity<WalletDTO> walletDTOResponseEntity = restTemplate.postForEntity(
//                URI.create(url),
//                walletDTOHttpEntity,
//                WalletDTO.class);

        return restTemplate.postForEntity(URI.create(url), walletDTO, WalletDTO.class);
    }

}
