package com.techie.microservices.menu.entities;

import com.techie.microservices.menu.entities.base.EntityAuditBase;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "process")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Process extends EntityAuditBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private Integer processId;

    @Column(name = "value", nullable = false, length = 100, columnDefinition = "varchar(100)")
    private String value;

    @Column(name = "name", nullable = false, length = 200, columnDefinition = "varchar(200)")
    private String name;

    @Column(name = "active", length = 2, columnDefinition = "char(2)")
    private String active;

    @Column(name = "form_load_id")
    private Integer formLoadId;

    @Column(name = "process_vmms_id")
    private Integer processVmmsId;
}
