package banque;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import bibliotheque.Emprunt;
import bibliotheque.Livre;

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
		compteClient2.add(compte21);
		compteClient2.add(compteDouble);
		
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
		
		/**
		 * Requete diverse TP7
		 */
		EntityManager em3 = entityManagerFactory.createEntityManager();
		EntityTransaction et3 = em3.getTransaction();
		et3.begin();
		TypedQuery<Client> queryClient = em3.createQuery("SELECT c FROM Client c WHERE c.id=:param1", Client.class);
		queryClient.setParameter("param1", 2);
		
		Client client = queryClient.getResultList().get(0);
		List<Compte> compteList2 = client.getComptes();
		System.out.println("Affichage des comptes d'un client donné\n");
		for (Compte c : compteList2)
			System.out.println(c.getNumero());
		
		System.out.println(
                "****************************************************************************************************\n");
		System.out.println("Affichage des comptes d'une banque\n");
        Compte compte = null;
        TypedQuery<Compte> compteQuery = em3.createQuery(
                "select c from Compte c INNER JOIN c.clients cli where cli.banque.id=:BanqueID", Compte.class);
        compteQuery.setParameter("BanqueID", 1);
        if (compteQuery.getMaxResults() > 1) {
            List<Compte> comptes = compteQuery.getResultList();
            for (Compte compte2 : comptes) {
                System.out.println(compte2.getNumero());
            }
        } else {
            compte = compteQuery.getSingleResult();
            System.out.println(compte.getNumero());
        }
        System.out.println(
                "****************************************************************************************************\n");
        System.out.println("Affichage des comptes avec une opération de plus de 1000€\n");
        TypedQuery<Compte> compteQuery2 = em3.createQuery(
                "select c from Compte c INNER JOIN c.operations op where op.montant>:montant", Compte.class);
        compteQuery2.setParameter("montant", 1000.00);
        
        if (compteQuery2.getMaxResults() > 1) {
            for (Compte compte3 : compteQuery2.getResultList()) {
                System.out.println(compte3.getNumero());
            }
        } else {
            System.out.println(compteQuery2.getSingleResult());
        }
        System.out.println(
                "****************************************************************************************************\n");
        System.out.println("Réaliser une requête qui recherche tous les comptes qui ont au moins une opération.\n");
        
        TypedQuery<Compte> compteQuery3 = em3.createQuery(
                "select DISTINCT c from Compte c INNER JOIN c.operations op where exists(Select o from Operation o where o.compte=c)", Compte.class);
        
        if (compteQuery3.getMaxResults() > 1) {
            for (Compte compte4 : compteQuery3.getResultList()) {
                System.out.println(compte4.getNumero());
            }
        } else {
            System.out.println(compteQuery3.getSingleResult());
        }
        
        
        
		et3.commit();
		em3.close();
		
		
		
		/**
		 * Suppression du compte 1
		 */
		/*EntityManager em2 = entityManagerFactory.createEntityManager();
		EntityTransaction et2 = em2.getTransaction();
		et2.begin();
		List<Virement> virementRemove = new ArrayList<Virement>();
		TypedQuery<Virement> query1 = em2.createQuery("Select v from Virement v where v.id=1", Virement.class);
		virementRemove = query1.getResultList();
		
		for(Virement v:virementRemove)
			if( v != null)
				em2.remove(v);
		operationCompte1 = null;
		
		compteClient1.remove(compte11);
		client1.setComptes(compteClient1);
		em2.merge(client1);
		Compte compteRemove  = em2.find(Compte.class,1);
		
		if( compteRemove != null)
			em2.remove(compteRemove);

		
		et2.commit();
		em2.close();*/
		
		
		entityManagerFactory.close();
	}
}
