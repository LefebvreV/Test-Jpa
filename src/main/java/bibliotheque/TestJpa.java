package bibliotheque;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

public class TestJpa {

	public static void main (String[] args){
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("testJpa");
		EntityManager em = entityManagerFactory.createEntityManager();
		
		/*Livre livre = em.find(Livre.class, 4);
		
		if(livre != null){
			System.out.println("Voici le nom du livre retourné " + livre.getTitre() + ", dont l'auteur est " + livre.getAuteur());
		}
		
		TypedQuery<Livre> query = em.createQuery("SELECT l FROM Livre l WHERE l.titre=:param1", Livre.class);
		query.setParameter("param1", "Vingt mille lieues sous les mers");
		
		Livre livre2;
		livre2 = query.getResultList().get(0);
		
		if(livre2 != null){
			System.out.println("Voici le nom du livre retourné " + livre2.getTitre() + ", dont l'auteur est " + livre2.getAuteur());

		}*/
		
		
		TypedQuery<Emprunt> queryEmprunt = em.createQuery("SELECT e FROM Emprunt e WHERE e.id=:param1", Emprunt.class);
		queryEmprunt.setParameter("param1", 2);
		
		Emprunt emprunt = queryEmprunt.getResultList().get(0);
		
		List<Livre> livreList = emprunt.getLivre();
		
		System.out.println("La liste de l'emprunt n° "+ emprunt.getId() 
		+ " du client " + emprunt.getClient().getNom() 
		+ " " + emprunt.getClient().getPrenom() + " :\n");
		for(Livre l : livreList){
			System.out.println(l.getTitre() + " de " + l.getAuteur());
		}
		
		TypedQuery<Client> queryClient = em.createQuery("SELECT c FROM Client c WHERE c.id=:param1", Client.class);
		queryClient.setParameter("param1", 3);
		
		Client client = queryClient.getResultList().get(0);
		
		List<Emprunt> empruntLIst = client.getEmprunt();
		
		System.out.println("\nVoici la liste des emprunts du client " + client.getNom() 
		+ " " + client.getPrenom() + " identifié " + client.getId() + " :\n" );
		for(Emprunt e : empruntLIst){
			System.out.println("L'emprunt n° " + e.getId());
		}
		
		
		em.close();
		entityManagerFactory.close();
	}
	
}
