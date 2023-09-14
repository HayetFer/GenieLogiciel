package edu.mermet.calendrier.commandes;

public enum Commandes {
  DATE {
    public Commande getCommande() {
      return new CommandeDate();
    }
  },
  HEURE {
    public Commande getCommande() {
      return new CommandeHeure();
    }
  },
  DATEHEURE {
    public Commande getCommande() {
      return new CommandeDateHeure();
    }
  };
  public abstract Commande getCommande();
}
