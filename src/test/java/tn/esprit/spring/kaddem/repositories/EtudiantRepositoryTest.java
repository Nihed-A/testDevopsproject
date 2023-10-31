package tn.esprit.spring.kaddem.repositories;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import tn.esprit.spring.kaddem.entities.Etudiant;
import tn.esprit.spring.kaddem.entities.Option;

import static org.junit.jupiter.api.Assertions.assertEquals;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
 class EtudiantRepositoryTest {
    @Autowired
    private EtudiantRepository etudiantRepository;

    @Autowired
    private TestEntityManager entityManager;


    @BeforeEach
    void setup() {


        Etudiant etudiant = new Etudiant(3, "Houssem", "Chaaben", Option.GAMIX);
        Etudiant mergedEtudiant = entityManager.merge(etudiant);

    }

    @Test
    public void findbyEtudiantId() {
        Etudiant etudiant = etudiantRepository.findById(3).get();
        assertEquals("Houssem", etudiant.getNomE());
    }



}
