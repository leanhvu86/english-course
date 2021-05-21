package com.selflearning.englishcourses.service;

import com.selflearning.englishcourses.domain.Module;
import com.selflearning.englishcourses.service.dto.ModuleDto;

import java.util.List;
import java.util.UUID;

public interface ModuleService extends BaseCurdService<Module, UUID>, ModelMapperService<Module, ModuleDto> {

    List<Module> findAllModulesNotIn(List<UUID> ids);

}
