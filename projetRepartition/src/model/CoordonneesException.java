package model;

public class CoordonneesException extends Exception {

	@Override
	public void printStackTrace() {
		System.out.println("Levée d'exception concernant les coordonnées : ");
		System.out.println("\tL'unité des coordonnées est un nombre de places strictement positif.");
		System.out.println("Cas particulier de 'z' : peut être nul si la salle est plate.");
	}
	
}
