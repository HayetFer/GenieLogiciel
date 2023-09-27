package edu.mermet.calendrier;

import java.util.logging.*;
import java.io.IOException;

public class Main {
  // TODO: stocker ici les loggers dans des variables d'instance
   static Logger LoggerC = Logger.getLogger("Connexions");
   static Logger LoggerA = Logger.getLogger("Actions");
   static void analyseParametres(String[] params) throws IOException {
    // Configurer ici les loggers pour que par défaut, seules les messages d'information ou plus importants soient loggués
    LoggerC.setLevel(Level.INFO);
    LoggerA.setLevel(Level.INFO);
    // Associer à chaque logger son handler ; le log doit être stocké dans des fichiers séparés suivant la fonctionnalité (log ou action) concernée ; les logs doivent être ajoutés dans les fichier d'une exécution sur l'autre.
    ConsoleHandler consHandler = new ConsoleHandler();
    

    FileHandler fileHandler2 = new FileHandler("actions.xml");
    LoggerA.addHandler(fileHandler2);

    FileHandler fileHandler1 = new FileHandler("connexions.txt");
    LoggerC.addHandler(fileHandler1);

    // Les logs concernant la connexion doivent être au format texte standard, ceux concernant les actions au format xml.
    for (String param: params) {
      if ("--verbose".equals(param)) {
        LoggerA.addHandler(consHandler);
        consHandler.setLevel(Level.INFO);
        fileHandler2.setLevel(Level.OFF);
      } else if ("--debug".equals(param)) {
        LoggerA.setLevel(Level.FINEST);
        LoggerC.setLevel(Level.FINEST);
      }
      else if ("--silent".equals(param)) {
        fileHandler1.setLevel(Level.OFF);
        fileHandler2.setLevel(Level.OFF);
        
      }
    }
  }
  public static void main(String... args) throws IOException {
    analyseParametres(args);
    Application app = new Application();
  }
}
