package com.techie.microservices.menu.entities.base;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import java.time.Instant;

@MappedSuperclass
public abstract class EntityAuditBase<T extends Serializable> extends EntityBase<T> {

    @Column(name = "created_by", length = 100, nullable = false)
    private String createdBy;

    @Column(name = "created", nullable = false)
    private Instant created = Instant.now();

    @Column(name = "updated_by", length = 100)
    private String updatedBy;

    @Column(name = "updated")
    private Instant updated;

    // Getters & Setters
}
