package com.techie.microservices.menu.entities;

import lombok.*;


import com.techie.microservices.menu.entities.base.EntityAuditBase;
import jakarta.persistence.*;

@Entity
@Table(name = "product")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Product extends EntityAuditBase{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private Integer  productId;

    @Column(nullable = false, length = 100)
    private String value;

    @Column(nullable = false, length = 200)
    private String name;

    @ManyToOne
    @JoinColumn(name = "model_id", nullable = false)
    private Model model;

    @Column(nullable = false)
    private Integer  uomId;

    private Integer  cartonQty;

    @Column(length = 50)
    private String cartonName;

    private Integer cartonCharQty;
    private Integer cartonLabelQty;

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
