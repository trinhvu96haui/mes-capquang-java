package com.techie.microservices.menu.entities;

import com.techie.microservices.menu.Enums.GroupProcessType;
import com.techie.microservices.menu.entities.base.EntityAuditBase;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "group_process")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GroupProcess extends EntityAuditBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String code;

    @Column(nullable = false)
    private String name;

    private String description;
    private String version;
    private String linkDocument;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private GroupProcessType type;
}
