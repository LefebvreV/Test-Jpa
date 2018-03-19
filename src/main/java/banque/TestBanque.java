package banque;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class TestBanque {

	public static void main(String[] args) {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("banque");
		EntityManager em = entityManagerFactory.createEntityManager();

		EntityTransaction et = em.getTransaction();

		// Ajout de banque
		Banque banque1 = new Banque("La meilleure banque du monde !");
		Banque banque2 = new Banque("La seconde meilleure banque du monde !");

		//Adresse du client
		Adresse adresse1 = new Adresse(12,"Chemin de Traverse",44000,"Londre");
		
		// Ajout du client1
		Client client1 = new Client("Neymar","Jean",LocalDate.now(),adresse1);
		client1.setBanque(banque1);

		// Ajout du client2
		Client client2 = new Client("Naipas", "Jean", LocalDate.now(), adresse1);
		client2.setBanque(banque2);

		//Ajout de Compte
		LivretA compte11 = new LivretA("11211211111231", 256.30, 1.5);
		LivretA compte21 = new LivretA("112358132134",789.6,1.25);
		AssuranceVie compteDouble = new AssuranceVie("13579111315171921",1564.30,0.6,LocalDate.of(2020, 2, 16));
		
		//Ajout de la liste de clients au compte correspondant
		List<Client> clientList1 = new ArrayList<Client>();
		clientList1.add(client1);
		compte11.setClients(clientList1);
		
		List<Client> clientList2 = new ArrayList<Client>();
		clientList2.add(client2);
		compte21.setClients(clientList2);
		
		List<Client> clientList3 = new ArrayList<Client>();
		clientList3.add(client1);
		clientList3.add(client2);
		compteDouble.setClients(clientList3);
		
		//Assignation des comptes aux clients
		List<Compte> compteClient1 = new ArrayList<Compte>();
		compteClient1.add(compte11);
		compteClient1.add(compteDouble);
		
		List<Compte> compteClient2 = new ArrayList<Compte>();
		compteClient1.add(compte21);
		compteClient1.add(compteDouble);
		
		client1.setComptes(compteClient1);
		client2.setComptes(compteClient2);
		
		//Ajout d'opérations
		Virement operation1 = new Virement(LocalDateTime.of(2019, 9, 20, 16, 15), 36.30 ,null, "La boulangerie des deux Moulins");
		operation1.setCompte(compte11);
		
		Virement operation2 = new Virement(LocalDateTime.of(2019, 9, 22, 16, 15),7.36,"J'avais faim","La boulangerie du Moulin");
		operation2.setCompte(compte11);
		
		//Ajout de la liste d'opération au compte correspondant
		List<Virement> operationCompte1 = new ArrayList<Virement>();
		operationCompte1.add(operation1);
		operationCompte1.add(operation2);
		
		
		et.begin();

		em.persist(banque1);
		em.persist(banque2);
		
		em.persist(client1);
		em.persist(client2);
		
		em.persist(compte11);
		em.persist(compte21);
		em.persist(compteDouble);
		
		em.persist(operation1);
		em.persist(operation2);

		et.commit();

		em.close();
		entityManagerFactory.close();
	}
}
