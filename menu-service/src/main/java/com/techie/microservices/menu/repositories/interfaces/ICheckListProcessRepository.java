package com.techie.microservices.menu.repositories.interfaces;

import com.techie.microservices.menu.entities.CheckList;
import com.techie.microservices.menu.entities.CheckListProcess;
import com.techie.microservices.menu.infrastructure.common.IRepositoryBase;

import java.util.List;
import java.util.Optional;

public interface ICheckListProcessRepository extends IRepositoryBase<CheckListProcess, Long> {

    List<CheckListProcess> findByProcessId(Long processId);

    List<CheckListProcess> createMany(List<CheckListProcess> entities);

    void deleteMany(List<CheckListProcess> entities);
}
