package com.techie.microservices.menu.entities;

import com.techie.microservices.menu.entities.base.EntityAuditBase;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "form_load")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FormLoad extends EntityAuditBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, name = "form_load_id")
    private Integer formLoadId;

    @Column(nullable = false, name = "name", length = 250)
    private String name;

    @Column(name = "path", length = 250)
    private String path;

    @Column(name = "status", length = 2)
    private String status;

    @Column(name = "note")
    private String note;
}
