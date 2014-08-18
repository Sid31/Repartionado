package model;

import java.util.HashSet;

public class Etablissement extends HashSet<Salle> {
	private String nom;
	
	public Etablissement (String pNom) {
		this.nom = pNom;
	}

	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
}
