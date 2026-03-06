package tn.esprit.spring.crudetudiant.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.spring.crudetudiant.entities.Etudiant;
import tn.esprit.spring.crudetudiant.entities.Option;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest  // ADD THIS - loads Spring context
class EtudiantServiceImplTest {

    @Autowired  // ADD THIS - injects the service
    private EtudiantServiceImpl etudiantService;

    private Etudiant etudiant;

    @BeforeEach
    void setUp() {
        etudiant = new Etudiant();
        etudiant.setNomEtudiant("Houssem");
        etudiant.setPrenomEtudiant("Askri");
        etudiant.setOpt(Option.DS);
    }

    @Test
    void afficherEtudiants() {
        etudiantService.ajouterEtudiant(etudiant);

        List<Etudiant> list = etudiantService.afficherEtudiants();

        assertNotNull(list);
        assertFalse(list.isEmpty());
    }

    @Test
    void ajouterEtudiant() {
        Etudiant savedEtudiant = etudiantService.ajouterEtudiant(etudiant);

        assertNotNull(savedEtudiant);
        assertEquals("Houssem", savedEtudiant.getNomEtudiant());
        assertEquals("Askri", savedEtudiant.getPrenomEtudiant());
        assertEquals(Option.DS, savedEtudiant.getOpt());
    }

    @Test
    void modifierEtudiant() {
        Etudiant savedEtudiant = etudiantService.ajouterEtudiant(etudiant);

        savedEtudiant.setNomEtudiant("UpdatedName");
        Etudiant updatedEtudiant = etudiantService.modifierEtudiant(savedEtudiant);

        assertNotNull(updatedEtudiant);
        assertEquals("UpdatedName", updatedEtudiant.getNomEtudiant());
    }

    @Test
    void supprimerEtudiant() {
        Etudiant savedEtudiant = etudiantService.ajouterEtudiant(etudiant);
        Long id = savedEtudiant.getIdEtudiant();

        etudiantService.supprimerEtudiant(id);

        Etudiant deletedEtudiant = etudiantService.afficherEtudiantById(id);
        assertNull(deletedEtudiant);
    }

    @Test
    void afficherEtudiantById() {
        Etudiant savedEtudiant = etudiantService.ajouterEtudiant(etudiant);
        Long id = savedEtudiant.getIdEtudiant();

        Etudiant fetchedEtudiant = etudiantService.afficherEtudiantById(id);

        assertNotNull(fetchedEtudiant);
        assertEquals("Houssem", fetchedEtudiant.getNomEtudiant());
        assertEquals("Askri", fetchedEtudiant.getPrenomEtudiant());
        assertEquals(Option.DS, fetchedEtudiant.getOpt());
    }
}