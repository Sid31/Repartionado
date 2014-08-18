package model;

public class Affectation {
	private Etudiant e1;
	private Place p1;
	
	public Affectation(Etudiant p_e1, Place p_p1) {
		this.e1 = p_e1;
		this.p1 = p_p1;
	}
	
	public Etudiant getEtudiant() {
		return e1;
	}

	public void setEtudiant(Etudiant p_e1) {
		this.e1 = p_e1;
	}

	public Place getPlace() {
		return p1;
	}

	public void setPlace(Place p_p1) {
		this.p1 = p_p1;
	}

	public boolean isPlaceOccupated() {
		if (this.e1 == null) {
			return false;
		} else {
			return true;
		}
	}
}
