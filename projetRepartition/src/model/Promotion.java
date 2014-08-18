package model;

import java.util.ArrayList;

public class Promotion extends ArrayList<Etudiant> {
	private String nom;
	
	public Promotion (String pNom) {
		this.nom = pNom;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

}
