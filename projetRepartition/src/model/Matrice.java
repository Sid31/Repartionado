package model;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.Vector;

public class Matrice {

    /* Attributs */
    private int m;             // nombre de ligne
    private int n;             // nombre colonne
    private Affectation[][] mat;           // déclaration de la matrice


    /* Constructeur: crée une matrice de taille m n  */
    public Matrice(int m, int n) {
        this.m = m;
        this.n = n;
        mat = new Affectation[m][n];
    }

    /* Accesseurs */
    public int getM(){
        return this.m;
    }

    public int getN(){
        return this.n;
    }

    public Affectation [][] getMatrice() {
        return this.mat;
    }

    public void setM(int m) {
        this.m = m;
    }

    public void setN(int n) {
        this.n = n;
    }

    public void setMat(Affectation[][] mat) {
        this.mat = mat;
    }

    /**
     * @author BERRAF Sid
     * @version 1.0
     * @brief: Fonction correspondant à l'Algo du Coin Nord Ouest vu pendant le cours de ROP
     */



    /**
     * @author BERRAF Sid
     * @version 1.0
     * @brief: Procédure qui trie la matrice sur les lignes.
     */

    public Matrice triageMatrice(){
        for(int i=0;i<this.getMatrice().length;++i){
            java.util.Arrays.sort(this.getMatrice()[i]);
        }
        return this;

    }



    /**
     * @author BERRAF Sid
     * @version 1.0
     * @brief: redéfinition de la méthode toString(), permet d'afficher la matrice de son application linéaire.
     */
   /* @Override
    public String toString(){
        int i, j;
        String separateur = "   ";
        StringBuffer res = new StringBuffer();
        //res.append("Matrice de votre Espace Vectoriel : \n");
        for(i=0; i<this.m; i++) {
            for(j=0; j<this.n; j++) {
                res.append(this.mat[i][j]);
                res.append(separateur);
            }
            res.append("\n");
        }
        return  res.toString();
    }
*/
    public static void displayTableau(int[][] tab) {
        for (int i = 0; i < tab.length; i++) {
            for (int j = 0; j < tab[i].length; j++) {
                System.out.print(tab[i][j]);
                System.out.print(' ');
            }
            System.out.println();
        }
        System.out.println();
    }

    public Matrice lireMatrice(int indiceMatrice) {
        int[][] matriceRes = new int[this.getMatrice().length][this.getMatrice().length];
        BufferedInputStream bufferedInput = null;
        String res = "";
        byte[] buffer = new byte[1024];

        try {
            bufferedInput = new BufferedInputStream(new FileInputStream("./docs/Transport" + indiceMatrice + ".txt"));
            int bytesRead = 0;
            while ((bytesRead = bufferedInput.read(buffer)) != -1) {
                String chunk = new String(buffer, 0, bytesRead);
                res += chunk;
            }
            String[] tabChunk = res.split(" ");
            int cpt = 0;
            for (int i = 0; i < this.getMatrice().length; i++) {
                for (int j = 0; j < this.getMatrice().length; j++) {
                    matriceRes[i][j] = Integer.parseInt(tabChunk[cpt]);
                    cpt++;
                }
            }
        }
        catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
        finally {
            try {
                if (bufferedInput != null) {
                    bufferedInput.close();
                }
            }
            catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return this;
    }



}