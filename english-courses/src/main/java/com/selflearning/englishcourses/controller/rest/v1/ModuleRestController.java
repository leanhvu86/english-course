package com.selflearning.englishcourses.controller.rest.v1;

import com.selflearning.englishcourses.domain.Module;
import com.selflearning.englishcourses.service.ModuleService;
import com.selflearning.englishcourses.service.dto.ModuleDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1")
public class ModuleRestController {

    private ModuleService moduleService;

    @Autowired
    public void setModuleService(ModuleService moduleService) {
        this.moduleService = moduleService;
    }

    @GetMapping("/modules")
    public ResponseEntity<Page<ModuleDto>> getModulePage(Pageable pageable) {
        Page<Module> modulePage = moduleService.findAll(pageable);
        return new ResponseEntity<>(moduleService.convertEntityPageToDtoPage(modulePage), HttpStatus.OK);
    }

    @GetMapping(value = "/modules/filter")
    public ResponseEntity<List<ModuleDto>> getAllModulesNotIn(@RequestBody List<UUID> ids) {
        return new ResponseEntity<>(moduleService.convertEntityToDto(moduleService.findAllModulesNotIn(ids)), HttpStatus.OK);
    }

    @GetMapping("/modules/{id}")
    public ResponseEntity<ModuleDto> createModule(@PathVariable("id") UUID id) {
        Module module = moduleService.get(id);
        return new ResponseEntity<>(moduleService.convertEntityToDto(module), HttpStatus.OK);
    }

    @PostMapping("/modules")
    public ResponseEntity<ModuleDto> createModule(@Valid @RequestBody ModuleDto moduleDto) {
        Module module = moduleService.convertDtoToEntity(moduleDto);
        moduleService.save(module);
        return new ResponseEntity<>(moduleService.convertEntityToDto(module), HttpStatus.CREATED);
    }

    @PutMapping("/modules/{id}")
    public ResponseEntity<ModuleDto> updateModule(@PathVariable("id") UUID id, @Valid @RequestBody ModuleDto moduleDto) {
        Module module = moduleService.get(id);
        module.setName(moduleDto.getName());
        moduleService.save(module);
        return new ResponseEntity<>(moduleService.convertEntityToDto(module), HttpStatus.OK);
    }

    @DeleteMapping("/modules/{id}")
    public ResponseEntity<ModuleDto> deleteModule(@PathVariable("id") UUID id) {
        Module module = moduleService.get(id);
        moduleService.delete(module);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
