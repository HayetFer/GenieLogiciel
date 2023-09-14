package edu.mermet.calendrier.commandes;

import java.time.LocalDate;
import java.time.format.FormatStyle;
import java.time.format.DateTimeFormatter;

public class CommandeDate implements Commande {
    public void execute() {
      LocalDate date = LocalDate.now();
      DateTimeFormatter formateur = DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM);
	    String dateFormatee = date.format(formateur);
      System.out.println(dateFormatee);
    }
}
