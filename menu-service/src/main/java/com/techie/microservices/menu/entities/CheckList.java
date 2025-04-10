package com.techie.microservices.menu.entities;

import com.techie.microservices.menu.Enums.CheckListType;
import com.techie.microservices.menu.entities.base.EntityAuditBase;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "check_list")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CheckList extends EntityAuditBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "check_list_id", nullable = false, unique = true)
    private Integer checkListId;

    @Column(name = "value", nullable = false, length = 100, columnDefinition = "varchar(100)")
    private String value;

    @Column(name = "name", nullable = false, length = 200, columnDefinition = "varchar(200)")
    private String name;

    @Column(name = "min_value")
    private BigDecimal minValue;

    @Column(name = "max_value")
    private BigDecimal maxValue;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private CheckListType type;

}
