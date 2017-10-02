/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestiondefichier;

import java.io.*;

/**
 *
 * @author formation
 */
public class FichierTextLecture {

    public static void main(String[] args) {
        String nomDuFichier = "villes.txt";
        try {

            File f = new File(nomDuFichier);

            if (f.exists()) {

                // --- Ouverture du fichier
                FileReader lfrFichier = new FileReader(nomDuFichier);
                // --- Bufferisation
                BufferedReader lbrBuffer = new BufferedReader(lfrFichier);

                // --- Lecture des lignes-enregistrements
                StringBuilder lsbContenu = new StringBuilder();
                String lsLigne;

                while ((lsLigne = lbrBuffer.readLine()) != null) {
                    String[] t = lsLigne.split(";");
                    lsbContenu.append(t[0]);
                    lsbContenu.append("\n");
                    lsbContenu.append(t[1]);
                    lsbContenu.append("\n");                    
                }

                lbrBuffer.close();
                lfrFichier.close();

                System.out.println(lsbContenu.toString());
            } else {
                System.out.println("Le fichier " + nomDuFichier + " n'existe");
            }

        } catch (FileNotFoundException e) {
            System.err.println("Erreur de fichier : " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Erreur de lecture : " + e.getMessage());
        }

    } /// main

}
