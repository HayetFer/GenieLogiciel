package document;

/**
 * Un traitement permet d'obtenir un nouveau document à partir d'un document.
 * 
 * @author mermet
 *
 */
public abstract class Traitement implements Document {
	private Document docInclus;

	/**
	 * Création d'un traitement à partir d'un document.
	 * 
	 * @param doc le document auquel le traitement va s'appliquer
	 */
	public Traitement(Document doc) {
		docInclus = doc;
	}

	/**
	 * Renvoie le document cible du traitement.
	 * 
	 * @return le document cible
	 */
	protected Document getDocInclus() {
		return docInclus;
	}

}
