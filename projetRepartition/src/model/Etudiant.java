package model;

public class Etudiant {
	private String nom;
	private String prenom;
	
	public Etudiant(String pNom, String pPrenom) {
		super();
		this.nom = pNom;
		this.prenom = pPrenom;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	
	
}
