package banque;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Adresse{
	

	@Column(name="NUMERO", length=5, nullable=false)
	private int numero;
	@Column(name="RUE", length=100, nullable=false)
	private String rue;
	@Column(name="CODE_POSTAL", length=5, nullable=false)
	private int codePostal;
	@Column(name="VILLE", length=50, nullable=false)
	private String ville;
	
	public Adresse(){
		
	}
	
	/**
	 * Constructeur
	 * @param numero
	 * @param rue
	 * @param codePostal
	 * @param ville
	 */
	public Adresse(int numero, String rue, int codePostal, String ville) {
		this.numero = numero;
		this.rue = rue;
		this.codePostal = codePostal;
		this.ville = ville;
	}

	/**
	 * @return the numero
	 */
	public int getNumero() {
		return numero;
	}

	/**
	 * @param numero the numero to set
	 */
	public void setNumero(int numero) {
		this.numero = numero;
	}

	/**
	 * @return the rue
	 */
	public String getRue() {
		return rue;
	}

	/**
	 * @param rue the rue to set
	 */
	public void setRue(String rue) {
		this.rue = rue;
	}

	/**
	 * @return the codePostal
	 */
	public int getCodePostal() {
		return codePostal;
	}

	/**
	 * @param codePostal the codePostal to set
	 */
	public void setCodePostal(int codePostal) {
		this.codePostal = codePostal;
	}

	/**
	 * @return the ville
	 */
	public String getVille() {
		return ville;
	}

	/**
	 * @param ville the ville to set
	 */
	public void setVille(String ville) {
		this.ville = ville;
	}
}