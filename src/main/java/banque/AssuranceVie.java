package banque;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class AssuranceVie extends Compte{

	@Column(name="TAUX", nullable=false)
	private double taux;
	@Column(name="DATE_FIN", nullable =false)
	private LocalDate dateFin;
	
	public AssuranceVie(){
		
	}

	/**
	 * Constructeur
	 * @param numero
	 * @param solde
	 * @param taux
	 * @param dateFin
	 */
	public AssuranceVie(String numero, double solde, double taux, LocalDate dateFin) {
		super(numero, solde);
		this.taux = taux;
		this.dateFin = dateFin;
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

	/**
	 * @return the dateFin
	 */
	public LocalDate getDateFin() {
		return dateFin;
	}

	/**
	 * @param dateFin the dateFin to set
	 */
	public void setDateFin(LocalDate dateFin) {
		this.dateFin = dateFin;
	}
	
}
