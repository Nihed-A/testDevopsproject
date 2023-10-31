package tn.esprit.spring.kaddem.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.spring.kaddem.entities.Etudiant;
import tn.esprit.spring.kaddem.repositories.EtudiantRepository;
import static org.junit.jupiter.api.Assertions.assertEquals;
@SpringBootTest
public class EtudiantServiceTest {
    @InjectMocks
    private   EtudiantServiceImpl etudiantService;

    @Mock
    private EtudiantRepository etudiantRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testAddEtudiant() {
        // Créer un étudiant de test
        Etudiant etudiant = new Etudiant();
        etudiant.setNomE("John");
        etudiant.setPrenomE("Doe");

        // Configurer le comportement simulé du repository
        Mockito.when(etudiantRepository.save(Mockito.any(Etudiant.class))).thenReturn(etudiant);

        // Appeler la méthode que vous testez
        Etudiant result = etudiantService.addEtudiant(etudiant);

        // Vérifier que la méthode a été appelée avec le bon argument
        Mockito.verify(etudiantRepository).save(Mockito.any(Etudiant.class));

        // Assertions
        assertEquals("John", result.getNomE());
        assertEquals("Doe", result.getPrenomE());
    }
}
