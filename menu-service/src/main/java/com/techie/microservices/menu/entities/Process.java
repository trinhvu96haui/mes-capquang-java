package com.techie.microservices.menu.entities;

import com.techie.microservices.menu.Enums.InputMethod;
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

    @Column(name = "status")
    private Boolean status;

    @Enumerated(EnumType.STRING)
    @Column(name = "input_method",nullable = false)
    private InputMethod inputMethod;


}
