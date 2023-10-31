package tn.esprit.spring.kaddem.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import lombok.extern.slf4j.Slf4j;

import tn.esprit.spring.kaddem.entities.Contrat;
import tn.esprit.spring.kaddem.entities.Departement;
import tn.esprit.spring.kaddem.entities.Equipe;
import tn.esprit.spring.kaddem.entities.Etudiant;
import tn.esprit.spring.kaddem.repositories.ContratRepository;
import tn.esprit.spring.kaddem.repositories.DepartementRepository;
import tn.esprit.spring.kaddem.repositories.EquipeRepository;
import tn.esprit.spring.kaddem.repositories.EtudiantRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;

@Service
@Slf4j
public class EtudiantServiceImpl implements IEtudiantService{

	private static final Logger logger = LoggerFactory.getLogger(EtudiantServiceImpl.class);
	@Autowired
	EtudiantRepository etudiantRepository ;
	@Autowired
	ContratRepository contratRepository;
	@Autowired
	EquipeRepository equipeRepository;
    @Autowired
    DepartementRepository departementRepository;
	public List<Etudiant> retrieveAllEtudiants(){
		logger.info("Retrieving all Etudiants");
		return (List<Etudiant>) etudiantRepository.findAll();
	}

	public Etudiant addEtudiant (Etudiant e){
		logger.info("Adding Etudiant: {}", e);
		return etudiantRepository.save(e);
	}

	public Etudiant updateEtudiant (Etudiant e){
		logger.info("Updating Etudiant: {}", e);
		return etudiantRepository.save(e);
	}

	public Etudiant retrieveEtudiant(Integer  idEtudiant){

		logger.info("Updating Etudiant: {}",idEtudiant);
		return etudiantRepository.findById(idEtudiant).get();
	}

	public void removeEtudiant(Integer idEtudiant){

	logger.info("removing Etudiant:{} ",idEtudiant );
	Etudiant e=retrieveEtudiant(idEtudiant);
	etudiantRepository.delete(e);
	}

	public void assignEtudiantToDepartement (Integer etudiantId, Integer departementId){

		logger.info("Assigning Etudiant (ID={}) to Departement (ID={})", etudiantId, departementId);

		Etudiant etudiant = etudiantRepository.findById(etudiantId).orElse(null);
		Departement departement = departementRepository.findById(departementId).orElse(null);

		if (etudiant != null && departement != null) {
			etudiant.setDepartement(departement);
			etudiantRepository.save(etudiant);
			logger.info("Etudiant (ID={}) assigned to Departement (ID={}) successfully", etudiantId, departementId);
		} else {
			logger.warn("Assignment failed. Etudiant (ID={}) or Departement (ID={}) not found.", etudiantId, departementId);
		}

	}
	@Transactional
	public Etudiant addAndAssignEtudiantToEquipeAndContract(Etudiant e, Integer idContrat, Integer idEquipe){
		logger.info("Adding and assigning Etudiant to Equipe and Contract: {}", e);
		Contrat c=contratRepository.findById(idContrat).orElse(null);
		Equipe eq=equipeRepository.findById(idEquipe).orElse(null);
		c.setEtudiant(e);
		eq.getEtudiants().add(e);
		logger.info("Etudiant added and assigned successfully");
return e;
	}

	public 	List<Etudiant> getEtudiantsByDepartement (Integer idDepartement){

		logger.info("Fetching Etudiants by Departement: ID={}", idDepartement);

		List<Etudiant> etudiants = etudiantRepository.findEtudiantsByDepartement_IdDepart(idDepartement);

		logger.info("Fetched {} Etudiants by Departement", etudiants.size());
		return etudiants;
	}
}
