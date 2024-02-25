package org.example.controller;

import org.example.entity.Entity;
import org.example.repository.EntityRepository;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.springframework.boot.test.mock.mockito.MockBean;
public class EntityControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EntityRepository entityRepository;

    @Test
    public void whenGetAllEntities_thenReturnJsonArray() throws Exception {
        // Arrange
        Entity entity = new Entity();
        entity.setName("Test Entity");
        List<Entity> entities = Collections.singletonList(entity);

        given(entityRepository.findAll()).willReturn(entities);

        // Act & Assert
        mockMvc.perform(get("/api/entities")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].name", equalTo(entity.getName())));
    }
}
