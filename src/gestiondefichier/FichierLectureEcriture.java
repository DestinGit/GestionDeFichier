/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestiondefichier;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author formation
 */
public class FichierLectureEcriture {

    public static void main(String[] args) {
        String nomDuFichier = "accessibilite-des-gares-et-stations-metro-et-rer-ratp.csv";
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
                
                StringBuilder dataLine = new StringBuilder();
//                List<String> liste = new ArrayList();
                
                while ((lsLigne = lbrBuffer.readLine()) != null) {
                    if (!lsLigne.isEmpty()) {

                        String[] t = lsLigne.split(";");
                        if (t.length > 12 && t[11].substring(0,2).equals("75")) {
                            dataLine.append(t[10]);
                            dataLine.append(";");
                            dataLine.append(t[8].split(",")[0].trim());
                            dataLine.append(";");
                            dataLine.append(t[8].split(",")[1].trim());
                            dataLine.append("\n");
                        }
                    }
                }

                lbrBuffer.close();
                lfrFichier.close();

//                String[] tEns = liste.toArray(new String[liste.size()]);
//                Arrays.sort(tEns);
//                
//                liste = new ArrayList();
//                String stationAvant = "";
//                String stationCourante;
//                
//                for(int i = 0; i < tEns.length - 1; i++) {
//                    String enr = tEns[i];
//                    String[] tChamps = enr.split(enr);
//                }
                
                ecriture(dataLine.toString());

                System.out.println("Fin transfert");
            } else {
                System.out.println("Le fichier " + nomDuFichier + " n'existe");
            }

        } catch (FileNotFoundException e) {
            System.err.println("Erreur de fichier : " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Erreur de lecture : " + e.getMessage());
        }

    } /// main

    public static void ecriture(String dataLine) {
        try {
            FileWriter lfwFichier = new FileWriter("metros.csv", true);
            BufferedWriter lbwBuffer = new BufferedWriter(lfwFichier);

            lbwBuffer.write(dataLine);
//            lbwBuffer.newLine();

            lbwBuffer.close();
            lfwFichier.close();

        } catch (IOException e) {
            System.err.println(e);
        }
    }

    public String lecture() {
        return "fd";
    }
}
