package camelCase;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Camel {

    public static void main(String[] args) {
        // Lecture du fichier texte d'entrée
        String inputFilename = "camel.txt";
        String outputFilename = "camel2.txt";
        
        // Lire les lignes du fichier d'entrée
        String[] lines = readInputFile(inputFilename);

        // Traiter les lignes pour les convertir en camelCase
        String[] camelCaseLines = convertToCamelCase(lines);

        // Ecrire les lignes converties dans le fichier de sortie
        writeOutputFile(outputFilename, camelCaseLines);

        System.out.println("Conversion terminée. Le résultat est dans le fichier " + outputFilename);
    }

    /**
     * Lit le contenu d'un fichier texte et retourne un tableau de chaînes de caractères contenant chaque ligne du fichier.
     */
    public static String[] readInputFile(String filename) {
        try {
            // Ouvrir le fichier d'entrée
            File inputFile = new File(filename);
            Scanner scanner = new Scanner(inputFile);
            
            // Lire toutes les lignes du fichier et les stocker dans un tableau
            String[] lines = scanner.useDelimiter("\\A").next().split("\\r?\\n");
            scanner.close();
            
            return lines;
        } catch (FileNotFoundException e) {
            System.out.println("Erreur: le fichier " + filename + " n'a pas été trouvé.");
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Convertit chaque ligne d'un tableau de chaînes de caractères en camelCase.
     */
    public static String[] convertToCamelCase(String[] lines) {
        String[] camelCaseLines = new String[lines.length];
        
        // Parcourir chaque ligne et la convertir en camelCase
        for (int i = 0; i < lines.length; i++) {
            camelCaseLines[i] = toCamelCase(lines[i]);
        }
        
        return camelCaseLines;
    }

    /**
     * Ecrit chaque ligne d'un tableau de chaînes de caractères dans un fichier texte.
     */
    public static void writeOutputFile(String filename, String[] lines) {
        try {
            // Créer un fichier de sortie
            File outputFile = new File(filename);
            PrintWriter writer = new PrintWriter(outputFile);
            
            // Ecrire chaque ligne du tableau dans le fichier de sortie
            for (String line : lines) {
                writer.println(line);
            }
            
            writer.close();
        } catch (FileNotFoundException e) {
            System.out.println("Erreur: impossible d'écrire dans le fichier " + filename);
            e.printStackTrace();
        }
    }

    /**
     * Convertit une chaîne de caractères en camelCase.
     */
    public static String toCamelCase(String input) {
        // Découper la chaîne de caractères en mots
        String[] words = input.split(" ");
        StringBuilder camelCase = new StringBuilder(words[0].toLowerCase());
        
        // Parcourir chaque mot et le convertir en camelCase
        for (int i = 1; i < words.length; i++) {
            String word = words[i];
            String capitalized = word.substring(0, 1).toUpperCase() + word.substring(1).toLowerCase();
            camelCase.append(capitalized);
        }
        
        return camelCase.toString();
    }
}
