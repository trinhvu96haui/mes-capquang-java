package com.techie.microservices.menu.entities.base;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.Instant;

@MappedSuperclass
@Getter
@Setter
public abstract class EntityAuditBase{

    @Column(name = "created_by", length = 100, nullable = false)
    private String createdBy;

    @Column(name = "created", nullable = false)
    private Instant created = Instant.now();

    @Column(name = "updated_by", length = 100)
    private String updatedBy;

    @Column(name = "updated")
    private Instant updated;
}
