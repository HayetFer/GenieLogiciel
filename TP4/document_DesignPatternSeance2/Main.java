package document;

public class Main {

	public static void main(String[] args) {
		Document doc = new DocumentConcret("Immortel Archimède, artiste ingénieur.\n");
		doc.ajouterAuDebut("Que j'aime à faire apprendre ce nombre utile aux sages.\n");
		doc.ajouterEnFin("Qui de ton jugement peut priser la valeur ?\n");
		doc.ajouterEnFin("Pour moi ton problème eu de pareils avantages.\n");
		// System.out.println(doc);

		doc = new EnTete(doc, "heula");
		/*
		 * System.out.println(doc);
		 */
		doc = new Cadre(doc);
		doc.remplacer("eu", "XXXX");
		// System.out.println(doc);
		doc = new EnTete(doc, "MEGA TITRE");
		// System.out.println(doc);
		doc.ajouterAuDebut("insertionAvant\n");
		doc.ajouterEnFin("insertionAprès\n");
		// System.out.println(doc);
		doc = new PiedDePage(doc, 5);
		System.out.println(doc);
		// doc = new Cadre(doc);
		// System.out.println(doc);

	}

}
