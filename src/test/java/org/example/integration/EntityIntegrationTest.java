package org.example.integration;

import org.example.repository.EntityRepository;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.Assert.assertThat;
import static org.springframework.http.RequestEntity.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
public class EntityIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private EntityRepository entityRepository;

    @Test
    public void whenCreateEntity_thenEntityIsCreated() throws Exception {
        // Arrange
        String entityJson = "{\"name\":\"Test Entity\"}";

        // Act & Assert
        mockMvc.perform(post("/api/entities")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(entityJson))
                .andExpect(status().isOk());

        assertThat(entityRepository.count()).isEqualTo(1);
    }
}
