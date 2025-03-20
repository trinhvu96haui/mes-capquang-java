package com.techie.microservices.menu.entities;

import jakarta.persistence.*;
import java.util.List;
import com.techie.microservices.menu.entities.base.EntityAuditBase;

@Entity
@Table(name = "model")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Model extends EntityAuditBase<Long> {

    @Column(name = "value", length = 100, nullable = false)
    private String value;

    @Column(name = "name", length = 200, nullable = false)
    private String name;

    @Column(name = "category_id")
    private int categoryId;

    @Column(name = "active", length = 2, nullable = false)
    private String active;

    @OneToMany(mappedBy = "model", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Product> products;

    // Getters & Setters
}
