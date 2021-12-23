package com.enigma.enigmat_shop.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "trx_purchase_detail")
public class PurchaseDetail {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String id;

    @ManyToOne(targetEntity = Purchase.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "purchase_id")
    @JsonBackReference
    private Purchase purchase;

    @ManyToOne(targetEntity = Product.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(nullable = false)
    private Integer productPrice;

    @Column(nullable = false)
    private Integer totalPrice;

    @Column(nullable = false)
    private Integer quantity;

    public PurchaseDetail(String id, Purchase purchase, Product product, Integer productPrice, Integer totalPrice, Integer quantity) {
        this.id = id;
        this.purchase = purchase;
        this.product = product;
        this.productPrice = productPrice;
        this.totalPrice = totalPrice;
        this.quantity = quantity;
    }

    public PurchaseDetail() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Integer totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Purchase getPurchase() {
        return purchase;
    }

    public void setPurchase(Purchase purchase) {
        this.purchase = purchase;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Integer getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(Integer productPrice) {
        this.productPrice = productPrice;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
