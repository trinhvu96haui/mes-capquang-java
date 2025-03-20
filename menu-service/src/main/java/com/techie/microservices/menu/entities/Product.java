package com.techie.microservices.menu.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import com.techie.microservices.menu.entities.base.EntityAuditBase;
import jakarta.persistence.*;

@Entity
@Table(name = "product")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Product extends EntityAuditBase<Long> {

    @Column(nullable = false, unique = true)
    private int productId;

    @Column(nullable = false, length = 100)
    private String value;

    @Column(nullable = false, length = 200)
    private String name;

    @ManyToOne
    @JoinColumn(name = "model_id", nullable = false)
    private Model model;

    @Column(nullable = false)
    private int uomId;

    private int cartonQty;

    @Column(length = 50)
    private String cartonName;

    private int cartonCharQty;
    private int cartonLabelQty;

    @Column(length = 2)
    private String active;

    private boolean isAuto;
    private boolean isYear;
    private boolean isMonth;
    private boolean isDay;
    private boolean isRepair;
    private boolean isReject;

    @Column(length = 50)
    private String type;
}
