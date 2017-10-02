package gestiondefichier;

import java.io.*;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author formation
 */
public class FichierTextEcriture {

    public static void main(String[] args) {

        try {
            FileWriter lfwFichier = new FileWriter("villes.txt");
            BufferedWriter lbwBuffer = new BufferedWriter(lfwFichier);

            lbwBuffer.write("75011;Paris");
            lbwBuffer.newLine();

            lbwBuffer.write("75012;Paris\r\n"); // --- Alternative

            lbwBuffer.write("59000;Lille\r\n");

            //lbwBuffer.flush(); // --- Facultatif
            lbwBuffer.close(); // --- Facultatif, non, non, non!!!
            lfwFichier.close();

            System.out.println("Fin de l'écriture d'un fichier texte");
        } catch (IOException e) {
            System.err.println(e);
        }
    } /// main

}
