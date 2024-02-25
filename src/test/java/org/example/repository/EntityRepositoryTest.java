package org.example.repository;

import org.example.entity.Entity;
import org.example.repository.EntityRepository;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;

@DataJpaTest
public class EntityRepositoryTest {

    @Autowired
    private EntityRepository entityRepository;

    @Test
    public void whenFindById_thenReturnEntity() {
        // Arrange
        Entity entity = new Entity();
        entity.setName("Test Entity");
        entityRepository.save(entity);

        // Act
        Optional<Entity> found = entityRepository.findById(entity.getId());

        // Assert
        assertThat(found.isPresent()).isTrue();
        assertThat(found.get().getName()).isEqualTo(entity.getName());
    }
}