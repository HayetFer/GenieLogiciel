package edu.mermet.calendrier.commandes;

import java.time.LocalDateTime;
import java.time.format.FormatStyle;
import java.time.format.DateTimeFormatter;

public class CommandeDateHeure implements Commande {
    public void execute() {
      LocalDateTime dateHeure = LocalDateTime.now();
      DateTimeFormatter formateur = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM);
	    String dateHeureFormatee = dateHeure.format(formateur);
      System.out.println(dateHeureFormatee);
    }
}
