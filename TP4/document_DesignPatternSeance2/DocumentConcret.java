package document;

import java.util.Arrays;

public class DocumentConcret implements Document {
	private StringBuilder texte;
	public DocumentConcret() {
		texte = new StringBuilder();
	}
	public DocumentConcret(String monTexte) {
		texte = new StringBuilder(monTexte);
	}
	/* (non-Javadoc)
	 * @see dpdecorateur.IDocument#ajouterEnFin(java.lang.String)
	 */
	@Override
	public void ajouterEnFin(String ajout) {
		texte.append(ajout);
	}
	/* (non-Javadoc)
	 * @see dpdecorateur.IDocument#ajouterEnTete(java.lang.String)
	 */
	@Override
	public void ajouterAuDebut(String ajout) {
		texte.insert(0,ajout);
	}
	/* (non-Javadoc)
	 * @see dpdecorateur.IDocument#remplacer(java.lang.String, java.lang.String)
	 */
	@Override
	public void remplacer(String avant, String apres) {
		int indiceAvant;
		while ((indiceAvant=texte.indexOf(avant)) >=0 ) {
			texte.replace(indiceAvant,indiceAvant + avant.length(), apres);
		}
	}
	/* (non-Javadoc)
	 * @see dpdecorateur.IDocument#toString()
	 */
	@Override
	public String toString() {
		return texte.toString();
	}	
}
