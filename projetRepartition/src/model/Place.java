package model;

public class Place implements Comparable {
	private String nomPlace;	
	/*  L'origine du repère sera considéré être le coin le plus près
	 *  du tableau et le plus à droite. L'unité des coordonnées est en nombre de places.
	 */
	private int x;	// x>0
	private int y;	// y>0
	private int z;	// z>=0 facultatif
    private double tauxTricherie;

    public double getTauxTricherie() {
        return tauxTricherie;
    }

    public void setTauxTricherie(double tauxTricherie) {
        this.tauxTricherie = tauxTricherie;
    }

    public Place (String pname, int px, int py) {
		this.nomPlace = pname;
		try {
			this.setX(px);
			this.setY(py);
			this.setZ(0);
		} catch (CoordonneesException e) {
			e.printStackTrace();
		}
	}
	
	public Place (String pname, int px, int py, int pz) {
		this.nomPlace = pname;
		try {
			this.setX(px);
			this.setY(py);
			this.setZ(pz);
		} catch (CoordonneesException e) {
			e.printStackTrace();
		}
	}

	public String getNomPlace() {
		return nomPlace;
	}

	public void setNomPlace(String name) {
		this.nomPlace = name;
	}

	public int getX() {
		return x;
	}

	public void setX(int px) throws CoordonneesException {
		if ( px > 0 ) {
			this.x = px;
		} else {
			throw new CoordonneesException();
		}
	}

	public int getY() {
		return y;
	}

	public void setY(int py) throws CoordonneesException {
		if ( py > 0 ) {
			this.y = py;
		} else {
			throw new CoordonneesException();
		}
	}
	
	public int getZ() {
		return z;
	}

	public void setZ(int pz) throws CoordonneesException {
		if ( pz >= 0 ) {
			this.z = pz;
		} else {
			throw new CoordonneesException();
		}
	}


    @Override
    public int compareTo(Object o) {
        if (o instanceof Place) {
            double diff = tauxTricherie - ((Place) o).getTauxTricherie();
            if (diff < 0) {
                return -1;
            }
            if (diff == 0) {
                return 0;
            }
            if (diff > 0) {
                return 1;
            }
        }
        return -1;
    }
}
