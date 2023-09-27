package edu.mermet.calendrier;
import edu.mermet.calendrier.commandes.*;
import java.util.Map;
import java.util.Optional;
import java.util.logging.*;
import java.io.Console;

public class Application {
    private static final int NB_OPTIONS = 4;
    // TODO: stocker ici les loggers dans des variables d'instance
    static Logger LoggerC = Logger.getLogger("Connexions");
    static Logger LoggerA = Logger.getLogger("Actions");
    Console console = System.console();
    // la ligne suivante n'est valide qu'à partir de Java 9
    Map<String,String> utilisateurs = Map.of("Mermet", "Bruno", "Pigne", "Yoann");
    private String utilisateurConnecte = "";

    public Application() {
      boolean connecte = connexion();
      if (connecte) {
        menu();
        deconnexion();
      }
      else {
        System.out.println("Connection avortée");
        System.out.println("Au revoir");
      }
    }

    private static void clearScreen() {
      System.out.print("\033[H\033[2J");
      System.out.flush();
    }

    private void afficherMenu() {
      clearScreen();
      System.out.println("1. Afficher la date");
      System.out.println("2. Afficher l'heure");
      System.out.println("3. Afficher la date et l'heure");
      System.out.println("4. Quitter");
    }

    private void menu() {
      boolean termine = false;
      while (!termine) {
        afficherMenu();
        String texteSaisi = console.readLine();
        int choix = 0;
        try {
          choix = Integer.parseInt(texteSaisi);
        } catch(NumberFormatException nfe) {
          System.out.println("Veuillez saisi un nombre s'il vous plait");
	        // TODO: logger ici, l'erreur de saisie au niveau le plus fin
          LoggerA.finest("Nombre non saisi");

          continue;
        }
        if (choix < 1 || choix > NB_OPTIONS) {
	  // TODO: logger ici, l'erreur de saisie au niveau le plus fin
          System.out.println("Veuillez saisi un nombre entre 1 et " + NB_OPTIONS);
          LoggerA.finest("Erreur de saisie : " +  choix + " n'est pas compris dans le menu \n ");
          continue;
        }
        LoggerA.info("nombre saisi = " + choix + " \n");
        Optional<Commandes> cmd = Optional.empty();
        switch(choix) {
          case 1:
            cmd = Optional.of(Commandes.DATE);
            break;
          case 2:
            cmd = Optional.of(Commandes.HEURE);
            break;
          case 3:
            cmd = Optional.of(Commandes.DATEHEURE);
            break;
          case 4:
            termine = true;
            break;
          default:
	    // TODO: logger ici, au niveau le plus sérieux, l'erreur
            LoggerA.severe("Erreur dans le programme");
            termine = true;
        }
        cmd.ifPresent(x -> {x.getCommande().execute(); console.readLine();});
      }
    }
    private boolean connexion() {
      boolean correct = false;
      int nbEssais = 0;
      while (!correct && nbEssais < 3) {
        System.out.print("Login : ");
        String login = console.readLine();
        System.out.println(utilisateurs.get("Mermet"));
        System.out.print("Mot de passe : ");
        String mdpSaisi = new String(console.readPassword());
        String mdpStocke = utilisateurs.get(login);
        if (mdpSaisi.equals(mdpStocke)) {
          LoggerC.info("Connexion réussie avec " + login + " \n");
          correct = true;
          utilisateurConnecte = login;
        }
        else {
                    nbEssais++;
                    LoggerC.warning(" " + nbEssais +  " connexions erronées successives  \n");

        }
      }
      if (!correct) {
	  // TODO: logger ici au niveau le plus sérieux que 3 erreurs successives ont eu lieu
        LoggerC.severe("3 connexions erronées successives");
      }
      return correct;
    }

    private void deconnexion() {
      System.out.println("Vous êtes maintenant déconnecté");
      // TODO: logger ici comme information qu'une déconnexion a eu lieu pour l'utilisateur courant
      LoggerC.info("Utilisateur déconnecté");
      utilisateurConnecte = "";
    }
}
