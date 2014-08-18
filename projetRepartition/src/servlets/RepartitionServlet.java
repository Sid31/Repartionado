package servlets;

import model.Promotion;
import model.Etudiant;
import model.Salle;
import model.Place;
import model.Repartition;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RepartitionServlet extends HttpServlet {
	private boolean test = false;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String promo = request.getParameter("promo").toUpperCase();
		String nomSalle = request.getParameter("salle");
		
		/*	Ici on va récupérer les données issues de la BDD correspondant aux choix de l'utilisateur.
		 * 	Les tables en BDD sont : Etudiant(id, nom, prenom, promotion) 
		 * 							 Place(id, nomPlace, coord_x, coord_y, coord_z, nomSalle)
		 */
		
		Promotion p1 = new Promotion(promo);
		Salle s1 = new Salle(nomSalle);
		
		if (this.test == false) {
			/* Chargement du driver JDBC pour MySQL */
			try {
			    Class.forName( "oracle.jdbc.OracleDriver" );
			} catch ( ClassNotFoundException e ) {
			    /* Gérer les éventuelles erreurs ici. */
			}
			this.test = true;
		}
		
		/* Connexion à la base de données */
		String url = "jdbc:oracle:thin:@localhost:1521:XE";
		String user = "projetGSI";
		String mdp = "password";
		
		Connection connexion = null;
		Statement statement = null;
		ResultSet resultat = null;
		try {
			/* Création de la connexion */
		    connexion = DriverManager.getConnection( url, user, mdp );

		    /* Création de l'objet gérant les requêtes */
		    statement = connexion.createStatement();

		    /* Exécution d'une requête de lecture */
		    resultat = statement.executeQuery("SELECT nom, prenom FROM Etudiant WHERE promotion = '" + promo + "'");

		    /* Récupération des données du résultat de la requête de lecture */
		    while ( resultat.next() ) {
		    	Etudiant e_tmp = new Etudiant(resultat.getString("nom"), resultat.getString("prenom"));
		    	p1.add(e_tmp);
		    }    
		    
		    /* Vider resultat et exécuter une nouvelle requête */
		    if (resultat != null) {
		    	resultat = null;
		    }
		    resultat = statement.executeQuery( "SELECT nomPlace, coord_x, coord_y, coord_z FROM Place WHERE nomSalle = '" + nomSalle + "'");

		    /* Récupération des données du résultat de la requête de lecture */
		    Place p_tmp;
		    while ( resultat.next() ) {
		    	p_tmp = new Place(resultat.getString("nomPlace"), resultat.getInt("coord_x"), resultat.getInt("coord_y"), resultat.getInt("coord_z"));
		    	s1.add(p_tmp);
		    }
		    
		} catch ( SQLException e ) {
		    e.printStackTrace();
		} finally {
			if ( resultat != null ) {
		        try {
		            /* Fermeture de la connexion */
		            resultat.close();
		        } catch ( SQLException ignore ) {}
		    }
			if ( statement != null ) {
		        try {
		            /* Fermeture de la connexion */
		            statement.close();
		        } catch ( SQLException ignore ) {}
		    }
		    if ( connexion != null ) {
		        try {
		            /* Fermeture de la connexion */
		            connexion.close();
		        } catch ( SQLException ignore ) {}
		    }
		}

		/*	A partir d'ici, on a logiquement chargé en p1 et s1 une promotion complète 
		 * 	ainsi qu'une salle complète. On procède alors au traitement de répartition.
		 */
		
		Repartition r1 =new Repartition(p1, s1, 1400, 650);	//Répartit et convertit les coordonnées en cm en pixels
		request.setAttribute("repartition", r1);
		
		request.getServletContext().getRequestDispatcher("/jsp/AffichageResultat.jsp").forward(request, response);
	}

}
