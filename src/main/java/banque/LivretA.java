package banque;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class LivretA extends Compte{

	@Column(name="TAUX", nullable=false)
	private double taux;
	
	public LivretA(){
		
	}
	
	/**
	 * Constructeur
	 * @param numero
	 * @param solde
	 * @param taux
	 */
	public LivretA(String numero, double solde, double taux) {
		super(numero, solde);
		this.taux = taux;
	}

	/**
	 * @return the taux
	 */
	public double getTaux() {
		return taux;
	}

	/**
	 * @param taux the taux to set
	 */
	public void setTaux(double taux) {
		this.taux = taux;
	}
	
}
