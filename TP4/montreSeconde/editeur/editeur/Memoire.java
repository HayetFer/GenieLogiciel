package editeur;

import java.util.ArrayList;
import java.util.List;

import editeur.Fenetre.Memento;

public class Memoire {
	private List<Memento> pileEtats; // la pile des états mémorisés
	private int indiceEtatCourant;   // l'indice dans la pile de l'état actuel

	public Memoire(Memento etatInitial) {
		pileEtats = new ArrayList<>();
		pileEtats.add(etatInitial);
		indiceEtatCourant=0;
	}
	
	public void ajouter(Memento etat) {
		if(this.aLaFin()){
			pileEtats.add(++indiceEtatCourant, etat);
		}
		else{
			int toRemove = pileEtats.size()-1-indiceEtatCourant;
			int temp = 0;
			while(temp<toRemove){
				pileEtats.remove(indiceEtatCourant--);
			}
			pileEtats.add(indiceEtatCourant, etat);
		}
	}
	
	public Memento annuler() {
		if(!auDebut()){
			indiceEtatCourant--;
		}
		return pileEtats.get(indiceEtatCourant);
	}


	public Memento refaire() {
		// TODO
		// fait progresser l'indice de l'état courant et renvoie l'état atteint
		if(!aLaFin()){
			indiceEtatCourant++;
		}
		return pileEtats.get(indiceEtatCourant);
	}
	
	public boolean auDebut() {
		return indiceEtatCourant==0;
	}
	
	public boolean aLaFin() {
		// TODO
		// renvoie vrai si on est sur le dernier état
		return indiceEtatCourant==pileEtats.size()-1;
	}
	
}
