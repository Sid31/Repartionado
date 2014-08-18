package model;

import java.util.ArrayList;

public class Salle extends ArrayList<Place> {
	private String nom;
	
	public Salle(String nom) {
		this.nom = nom;
	}

	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	
	public ArrayList<Place> toArrayList() {
		ArrayList<Place> resList = new ArrayList<Place>();
		for (Place p : this) {
			resList.add(p);
		}
		return resList;
	}
	
	public void toSvgFormat(int xMax_svg, int yMax_svg) {
		int max_x = max_X() + 130;
		int max_y = max_Y() + 130;
		for (Place p : this) {
			try {
				p.setX((p.getX()*xMax_svg/max_x));
				p.setY((p.getY()*yMax_svg/max_y));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	private int max_X() {
		int max_x = 1;	// Cette initialisation permet de s'assurer que la max ne pourra pas être 0
		for (Place p : this) {
			if (p.getX() > max_x) {
				max_x = p.getX();
			}
		}
		return max_x;
	}
	
	private int max_Y() {
		int max_y = 1;	// Cette initialisation permet de s'assurer que la max ne pourra pas être 0
		for (Place p : this) {
			if (p.getY() > max_y) {
				max_y = p.getY();
			}
		}
		return max_y;
	}
}
