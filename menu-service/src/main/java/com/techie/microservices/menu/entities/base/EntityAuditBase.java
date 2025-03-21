package com.techie.microservices.menu.entities.base;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;

import java.io.Serializable;
import java.time.Instant;

@MappedSuperclass
public abstract class EntityAuditBase{

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
