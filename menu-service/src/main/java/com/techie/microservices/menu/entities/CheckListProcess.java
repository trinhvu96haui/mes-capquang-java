package com.techie.microservices.menu.entities;

import com.techie.microservices.menu.entities.base.EntityAuditBase;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "check_list_process")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CheckListProcess extends EntityAuditBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "check_list_id", nullable = false)
    private Long checkListId;

    @Column(name = "process_id", nullable = false)
    private Long processId;

}
