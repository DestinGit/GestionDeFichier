/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestiondefichier;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Administrateur
 */
public class GetStationsRATP75 {

    public static void main(String[] args) {
        // stop_id;stop_name;stop_desc;coord;stop_lat;stop_lon;code_INSEE;departement
        //String nomDuFichier = "c:/TEMPO/positions-geographiques-des-stations-du-reseau-ratp.csv";

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

                List<String> liste = new ArrayList();

                while ((lsLigne = lbrBuffer.readLine()) != null) {
                    if (!lsLigne.isEmpty()) {
                        // stop_id;stop_name;stop_desc;coord;stop_lat;stop_lon;code_INSEE;departement
                        // idptar;Departement;X;Y;Annonce Sonore Prochain Passage;Annonce Visuelle Prochain Passage;Annonce Sonore Situations Perturbees;Annonce Visuelle Situations Perturbees;coord;IDAMIVIF;nomptar;CodeINSEE;UFR;PAQT;Tel;Date de mise en accessibilite;Accessibilite Quai Train;STIF
                        // coords : champ indice 8
                        // station : champ indice 10
                        String[] t = lsLigne.split(";");
                        //System.out.println(t.length);
                        lsbContenu.setLength(0);
                        if (t.length == 18 && t[11].substring(0, 2).equals("75")) {
                            lsbContenu.append(t[10]);
                            lsbContenu.append(";");
                            lsbContenu.append(t[8].split(",")[0].trim());
                            lsbContenu.append(";");
                            lsbContenu.append(t[8].split(",")[1].trim());
                            liste.add(lsbContenu.toString());
                        }
                    }
                }

                lbrBuffer.close();
                lfrFichier.close();

                String[] tEnrs = liste.toArray(new String[liste.size()]);
                Arrays.sort(tEnrs);

                liste = new ArrayList();
                
                String stationAvant = "";
                String stationCourante;

                for (int i = 0; i < tEnrs.length; i++) {
                    String enr = tEnrs[i];
                    String[] tChamps = enr.split(";");
                    stationCourante = tChamps[0];
                    if (!stationCourante.equals(stationAvant)) {
                        liste.add(enr);
                        stationAvant = stationCourante;
                    }
                }

                for (int i = 0; i < liste.size(); i++) {
                    String item = liste.get(i);
                    lsbContenu.append(item);
                    lsbContenu.append("\n");
                }

//                System.out.println(lsbContenu.toString());
                FileWriter lfwFichier = new FileWriter("metro_paris.txt");
                lfwFichier.write(lsbContenu.toString());
                lfwFichier.close();

                System.out.println("Transfert réussi !!!");

            } else {
                System.out.println("Le " + nomDuFichier + " fichier n'existe pas !!!");
            }

        } catch (FileNotFoundException e) {
            System.err.println("Erreur de fichier : " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Erreur de lecture : " + e.getMessage());
        }
    }
}
