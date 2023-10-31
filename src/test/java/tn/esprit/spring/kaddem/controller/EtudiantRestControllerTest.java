package tn.esprit.spring.kaddem.controller;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import tn.esprit.spring.kaddem.controllers.EtudiantRestController;
import tn.esprit.spring.kaddem.entities.Etudiant;
import tn.esprit.spring.kaddem.services.IEtudiantService;

import java.util.Arrays;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.Mockito.when;





public class EtudiantRestControllerTest {
    @InjectMocks
    private EtudiantRestController etudiantRestController;

    @Mock
    private IEtudiantService etudiantService;

    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(etudiantRestController).build();
    }

    @Test
    public void testGetEtudiants() throws Exception {
        // Créer une liste d'étudiants de test
        Etudiant etudiant1 = new Etudiant();
        etudiant1.setIdEtudiant(1);
        etudiant1.setNomE("John");
        etudiant1.setPrenomE("Doe");

        Etudiant etudiant2 = new Etudiant();
        etudiant2.setIdEtudiant(2);
        etudiant2.setNomE("Jane");
        etudiant2.setPrenomE("Smith");

        // Configurer le comportement simulé du service
        when(etudiantService.retrieveAllEtudiants()).thenReturn(Arrays.asList(etudiant1, etudiant2));


        // Effectuer la requête GET
        mockMvc.perform(get("/etudiant/retrieve-all-etudiants")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nomE").value("John"))
                .andExpect(jsonPath("$[1].nomE").value("Jane"));
    }
}
