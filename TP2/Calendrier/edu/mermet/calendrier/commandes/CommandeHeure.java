package edu.mermet.calendrier.commandes;

import java.time.LocalTime;
import java.time.format.FormatStyle;
import java.time.format.DateTimeFormatter;

public class CommandeHeure implements Commande {
    public void execute() {
      LocalTime heure = LocalTime.now();
      DateTimeFormatter formateur = DateTimeFormatter.ofLocalizedTime(FormatStyle.MEDIUM);
	    String heureFormatee = heure.format(formateur);
      System.out.println(heureFormatee);
    }
}
