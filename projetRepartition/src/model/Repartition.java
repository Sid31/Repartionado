package model;

import model.Place;

import java.util.*;

public class Repartition extends ArrayList<Affectation> {
	private Promotion p1;
	private Salle s1;
	
	public Repartition(Promotion p_p1, Salle p_s1, int xMax_svg, int yMax_svg) {
		this.p1 = p_p1;
		this.s1 = p_s1;
		if (this.p1.size() <= this.s1.size()) {
			this.repartir();
		}
		this.s1.toSvgFormat(xMax_svg, yMax_svg);
	}
	
	public Promotion getPromotion() {
		return p1;
	}

	public Salle getSalle() {
		return s1;
	}


    public void repartiRecuit(){
        int nbEleves = p1.size();
        List<Place> res= recuit(s1, nbEleves,100,Double.MAX_VALUE,1000);

        for (int i = 0; i < nbEleves; ++i) {
            add(new Affectation(p1.get(i), res.get(i)));
        }
    }


    /**
     *
     * @param salle de la bdd
     * @param nbEleves  nombre d'eleves à placer
     * @param nbIter met 100 par exemple
     * @param eMax met Double.MAX_VALUE
     * @param temp met 1000 par exemple
     * @return
     */


    public List<Place> recuit(Salle salle, int nbEleves, int nbIter,double eMax, double temp) {
        List<Place> s; //liste des places occupées (donc on ne considère pas les places vides)
        double e = 0;
        s= genererSolutionAleatoire(salle, nbEleves);

        List<Place> sn = null;
        double en = 0;
        for (int i = 0; i < nbIter && e < eMax;  ++i) {
            sn = genererVoisin(salle, s);
            en = calculerEnergie(sn);
            if (en < e || metropolis(e, en, temp)) {
                s = sn;
                e = en;
                temp *= 0.9;
            }
        }
        return sn;
    }

    private List<Place> genererSolutionAleatoire(Salle salle, int nbEleves) {
        Random random = new Random();
        List<Place> res = new ArrayList<Place>();
        for (int i = 0; i < nbEleves; ++i) {
            res.add(salle.remove(random.nextInt(salle.size())));
        }
        return res;
    }

    private double calculerEnergie(List<Place> places) {
        affecterTauxTricheries(places);
        return Collections.max(places).getTauxTricherie();
    }

    private void affecterTauxTricheries(List<Place> places) {
        for (Place p: places) {
            double sn = 0;
            for (Place q : places) {
                if (!p.equals(q)) { //vérifier si référence suffit
                    double px = p.getX();
                    double py = p.getY();
                    double qx = q.getX();
                    double qy = q.getY();
                    double diffPx = px - qx;
                    double diffPy = py - qy;
                    sn += diffPx*diffPx - diffPy-diffPy;// XXXXXXXXXDDDDDDDD
                }
            }
            p.setTauxTricherie(sn);
        }
    }

    private List<Place> genererVoisin(Salle salle, List<Place> s) {
        List sn = s; //référence ou copie ?
        Random random = new Random();
        List<Place> placesNonOccupees = new ArrayList<Place>();
        for (Place p: salle) {
            if (!s.contains(p)) {
                placesNonOccupees.add(p);
            }
        }

        Place placeMaxTauxTricheries = Collections.max(s);
        sn.remove(placeMaxTauxTricheries);
        Place placeNonOccupeeRandom = placesNonOccupees.get(random.nextInt(placesNonOccupees.size()));
        sn.add(placeNonOccupeeRandom);

        return sn;
    }

    private boolean metropolis(double e, double en, double temp) {
        double random = new Random().nextDouble();
        double diff = en - e;
        double val = Math.exp(-(diff / temp));
        return (diff < val);
    }

    public void repartir() { //Poids : 0.8 diagonale, 0.4 latérale et 0.1 frontale
		ArrayList<Place> placesDispo = s1.toArrayList();
		Place p_optimale;		//La place optimale pour l'étudiant à placer
		int delta_x, delta_y, delta_z;	//Les distances latérale, frontale et la hauteur séparant 2 places
		double delta_diag;		//La distance diagonale séparant 2 places
		double dist_totale;		//La distance totale à maximiser pour une place candidate
		double dist_max;
		int cpt;
		
		p_optimale = placeInitiale();
		
		for (Etudiant e : p1) {
			System.out.println(e.getNom());
			dist_max = 0;
			cpt = 0;
			for (Place p : placesDispo) {
				dist_totale = 0;
				delta_x = p.getX();
				delta_y = p.getY();
				delta_z = p.getZ();
				for (Affectation a : this) {
					Place p_occupee = a.getPlace();
					delta_x = Math.abs(delta_x - p_occupee.getX());
					delta_y = Math.abs(delta_y - p_occupee.getY());
					delta_z = Math.abs(delta_z - p_occupee.getZ());
					if ((delta_x != 0) && (delta_y != 0)) {
						delta_diag = Math.sqrt(Math.pow(delta_x, 2) + Math.pow(delta_y, 2) + Math.pow(delta_z, 2));
					} else {
						delta_diag = 0;
					}
					dist_totale += (0.1 * delta_x) + (0.1 * delta_y) + (0.1 * delta_diag);
					cpt++;
				}
				System.out.println(p.getNomPlace() + "dist_totale = " + dist_totale);
				if (dist_totale > dist_max) {
					dist_max = dist_totale;
					p_optimale = p;
				}
			}
			System.out.println("dist_max = " + dist_max);
			this.add(new Affectation(e, p_optimale));
			System.out.println(p_optimale.getNomPlace());
			placesDispo.remove(p_optimale);
			System.out.println(cpt);
		}
	}


        public Place placeInitiale () {
            int somme_coord;
            int min = 1000000;
            Place placeRes = s1.get(0);

            for (Place p : s1) {
                somme_coord = p.getX() + p.getY();
                if (somme_coord < min) {
                    min = somme_coord;
                    placeRes = p;
                }
		}

		return placeRes;
	}
	
}
