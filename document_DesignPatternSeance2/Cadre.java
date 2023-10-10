package document;

/**
 * Classe permettant d'ajouter un cadre autour d'un document. Le cadre est fait
 * de "--" et de "|"
 * et est décollé de 1 espace du document à gauche et à droite.
 * 
 * @author mermet
 *
 */
public class Cadre extends Traitement {

	/**
	 * Constructeur
	 * 
	 * @param doc le document auquel le cadre est appliqué.
	 */

	public Cadre(Document doc) {
		super(doc);
	}

	/**
	 * Ajout d'un texte en fin de document. Le texte est ajouté à la fin du document
	 * encadré. Il sera
	 * donc dans le cadre.
	 */
	@Override
	public void ajouterEnFin(String ajout) {
		getDocInclus().ajouterEnFin(ajout);

	}

	/**
	 * Ajout d'un texte en fin de document. Le texte est ajouté au début du document
	 * encadré. Il sera
	 * donc dans le cadre.
	 */
	@Override
	public void ajouterAuDebut(String ajout) {
		getDocInclus().ajouterAuDebut(ajout);
	}

	/**
	 * Remplacement de toutes les occurrences d'un texte par un autre texte. Le
	 * remplacement
	 * est effectué sur le document encadre.
	 * 
	 * @param avant : texte recherché qui sera remplacé
	 * @param apres : texte de remplacement
	 */
	@Override
	public void remplacer(String avant, String apres) {
		getDocInclus().remplacer(avant, apres);
	}

	/**
	 * calcule la largeur du document, en tenant compte du cadre.
	 * 
	 * @return la largeur calculé pour le document avec son cadre.
	 */
	@Override
	public int largeur() {
		return getDocInclus().largeur() + 4;
	}

	@Override
	public String toString() {
		StringBuilder resultat = new StringBuilder();
		String texteDocument = getDocInclus().toString();
		String[] lignes = texteDocument.split("\n");
		StringBuilder traitHorizontal = new StringBuilder();
		StringBuilder blancs = new StringBuilder();
		for (int i = 0; i < largeur(); i++) {
			traitHorizontal.append("-");
			blancs.append(' ');
		}
		resultat.append(traitHorizontal)
				.append('\n');
		for (String ligne : lignes) {
			String blancsARajouter = "";
			try {
				blancsARajouter = blancs.substring(0, getDocInclus().largeur() - ligne.length());
			} catch (Exception e) {
				System.err.println("docInclus.largeur : " + getDocInclus().largeur());
				System.err.println("ligne : **" + ligne + "**");

			}
			resultat.append("| ")
					.append(ligne)
					.append(blancsARajouter)
					.append(" |\n");
		}
		resultat.append(traitHorizontal)
				.append('\n');
		return resultat.toString();
	}
}
