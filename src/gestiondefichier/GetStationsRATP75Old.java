/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Le package dans lequel se trouve la classe
 */
package gestiondefichier;

// Importation de la biblio entrées/sorties
import java.io.*;

/**
 *
 * @author Administrateur
 */
public class GetStationsRATP75Old {

    public static void main(String[] args) {
        // Déclaration et affectation
        // stop_id;stop_name;stop_desc;coord;stop_lat;stop_lon;code_INSEE;departement
        String nomDuFichier = "positions-geographiques-des-stations-du-reseau-ratp.csv";

        //String nomDuFichier = "/TEMPO/accessibilite-des-gares-et-stations-metro-et-rer-ratp.csv";
        // Essai
        try {
            // Déclaration, affectation, instiation d'un objet FILE
            File f = new File(nomDuFichier);
            // test de l'existence du fichier
            if (f.exists()) {
                // --- Ouverture du fichier
                FileReader lfrFichier = new FileReader(nomDuFichier);
                // --- Bufferisation
                BufferedReader lbrBuffer = new BufferedReader(lfrFichier);

                // --- Lecture des lignes-enregistrements
                StringBuilder lsbContenu = new StringBuilder();
                String lsLigne;
                // On boucle et on lis le fichier
                while ((lsLigne = lbrBuffer.readLine()) != null) {
                    // Test si la ligne est vide
                    if (!lsLigne.isEmpty()) {
                        // stop_id;stop_name;stop_desc;coord;stop_lat;stop_lon;code_INSEE;departement
                        // Déclaration tableau de chaines et on explose la ligne
                        String[] t = lsLigne.split(";");
                        //System.out.println(t.length);
                        if (t.length == 8 && t[7].equals("75")) {
                            // On concatène ...
                            lsbContenu.append(t[1]);
                            lsbContenu.append(";");
                            lsbContenu.append(t[4]);
                            lsbContenu.append(";");
                            lsbContenu.append(t[5]);
                            lsbContenu.append("\n");
                        }
                    }
                }
                // On ferme 
                lbrBuffer.close();
                lfrFichier.close();
                
                // On écrit la chaine dans un fichier
                FileWriter lfwFichier = new FileWriter("metro_paris_old.txt");
                lfwFichier.write(lsbContenu.toString());
                lfwFichier.close();

                System.out.println("Transfert réussi !!!");

            } else {
                System.out.println("Le " + nomDuFichier + " fichier n'existe pas !!!");
            }
         // Si une exception est levée
        } catch (FileNotFoundException e) {
            System.err.println("Erreur de fichier : " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Erreur de lecture : " + e.getMessage());
        }
    }
}
