package com.selflearning.englishcourses.service.impl;

import com.selflearning.englishcourses.domain.Module;
import com.selflearning.englishcourses.repository.jpa.ModuleJpaRepository;
import com.selflearning.englishcourses.service.ModuleService;
import com.selflearning.englishcourses.service.dto.ModuleDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ModuleServiceImpl implements ModuleService {

    private ModuleJpaRepository moduleJpaRepository;

    private ModelMapper modelMapper;

    @Autowired
    public void setModuleJpaRepository(ModuleJpaRepository moduleJpaRepository) {
        this.moduleJpaRepository = moduleJpaRepository;
    }

    @Autowired
    public void setModelMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public Module get(UUID id) {
        return moduleJpaRepository.findById(id).orElse(null);
    }

    @Override
    public void save(Module module) {
        moduleJpaRepository.save(module);
    }

    @Override
    public void delete(Module module) {
        moduleJpaRepository.delete(module);
    }

    @Override
    public Page<Module> findAll(Pageable pageable) {
        return moduleJpaRepository.findAll(pageable);
    }

    @Override
    public Module convertDtoToEntity(ModuleDto moduleDto) {
        return modelMapper.map(moduleDto, Module.class);
    }

    @Override
    public List<Module> convertDtoToEntity(List<ModuleDto> moduleDtos) {
        return moduleDtos.stream().map(this::convertDtoToEntity).collect(Collectors.toList());
    }

    @Override
    public ModuleDto convertEntityToDto(Module module) {
        return modelMapper.map(module, ModuleDto.class);
    }

    @Override
    public List<ModuleDto> convertEntityToDto(List<Module> modules) {
        return modules.stream().map(this::convertEntityToDto).collect(Collectors.toList());
    }

    @Override
    public Page<ModuleDto> convertEntityPageToDtoPage(Page<Module> page) {
        return new PageImpl<>(convertEntityToDto(page.getContent()), page.getPageable(), page.getTotalElements());
    }

    @Override
    public List<Module> findAllModulesNotIn(List<UUID> ids) {
        return moduleJpaRepository.findAllModulesNotIn(ids);
    }
}
