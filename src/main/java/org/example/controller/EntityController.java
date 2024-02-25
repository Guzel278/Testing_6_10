package org.example.controller;

import org.example.entity.Entity;
import org.example.repository.EntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api")
public class EntityController {

    // Внедряем зависимость репозитория
    @Autowired
    private EntityRepository entityRepository;

    // Создание сущности
    @PostMapping("/entities")
    public ResponseEntity<Entity> createEntity(@RequestBody Entity entity) {
        Entity savedEntity = entityRepository.save(entity);
        return ResponseEntity.ok(savedEntity);
    }

    // Получение всех сущностей
    @GetMapping("/entities")
    public List<Entity> getAllEntities() {
        return entityRepository.findAll();
    }

    // Получение сущности по ID
    @GetMapping("/entities/{id}")
    public ResponseEntity<Entity> getEntityById(@PathVariable(value = "id") Long entityId) {
        Entity entity = entityRepository.findById(entityId)
                .orElseThrow(() -> new ResourceNotFoundException("Entity not found with id: " + entityId));
        return ResponseEntity.ok().body(entity);
    }

    // Обновление сущности
    @PutMapping("/entities/{id}")
    public ResponseEntity<Entity> updateEntity(@PathVariable(value = "id") Long entityId,
                                               @RequestBody Entity entityDetails) {
        Entity entity = entityRepository.findById(entityId)
                .orElseThrow(() -> new ResourceNotFoundException("Entity not found with id: " + entityId));
        entity.setName(entityDetails.getName());
        Entity updatedEntity = entityRepository.save(entity);
        return ResponseEntity.ok(updatedEntity);
    }

    // Удаление сущности
    @DeleteMapping("/entities/{id}")
    public ResponseEntity<?> deleteEntity(@PathVariable(value = "id") Long entityId) {
        Entity entity = entityRepository.findById(entityId)
                .orElseThrow(() -> new ResourceNotFoundException("Entity not found with id: " + entityId));
        entityRepository.delete(entity);
        return ResponseEntity.ok().build();
    }
}
