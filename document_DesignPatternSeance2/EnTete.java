package document;

public class EnTete extends Traitement {

	/**
	 * Constructeur
	 * 
	 * @param doc le document auquel le cadre est appliqué.
	 */
	private String TexteEnTete;

	public EnTete(Document doc, String s) {
		super(doc);
		TexteEnTete = s;
	}

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
		TexteEnTete = ajout + "\n" + TexteEnTete;
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
		String[] lines = TexteEnTete.split("\n");
		for (String line : lines) {
			resultat.append("| ");
			resultat.append(line);
			for (int i = 0; i < largeur() - line.length() - 4; i++) {
				resultat.append(' ');
			}
			resultat.append(" |\n");
		}

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
