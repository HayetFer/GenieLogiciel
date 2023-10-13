package document;

import java.util.Arrays;

public interface Document {

	/**
	 * Calcul de la largeur d'un texte à partir de la ligne la plus longue.
	 * 
	 * @param texte le texte dont on veut connaître la largeur
	 * @return la largeur du texte
	 */
	static int largeur(StringBuilder texte) {
		return largeur(texte.toString());
	}

	/**
	 * Calcul de la largeur d'un texte à partir de la ligne la plus longue.
	 * 
	 * @param texte le texte dont on veut connaître la largeur
	 * @return la largeur du texte
	 */
	static int largeur(String texte) {
		return Arrays.stream(texte.split("\n")).mapToInt(String::length).max().orElse(0);
	}

	/**
	 * Méthode permettant d'ajouter un texte à la fin d'un document.
	 * 
	 * @param ajout le texte à ajouter
	 */
	void ajouterEnFin(String ajout);

	/**
	 * Méthode permettant d'ajouter un texte au début d'un document.
	 * 
	 * @param ajout le texte à ajouter
	 */
	void ajouterAuDebut(String ajout);

	/**
	 * Méthode permettant de remplacer dans le document toutes les occurrences d'un
	 * texte
	 * par un autre.
	 * 
	 * @param avant le texte à remplacer
	 * @param apres le texte de substitution
	 */
	void remplacer(String avant, String apres);

	/**
	 * Méthode renvoyant la largeur d'un document.
	 * 
	 * @return La largeur du document
	 */
	default int largeur() {
		return largeur(toString());
	}

	String toString();

}
