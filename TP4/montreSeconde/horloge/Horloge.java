package montreSeconde.horloge;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Horloge extends Thread {
	private Heure heure;
	//TODO Encapsuler une liste d'écouteurs
	public List<EcouteurHorloge> ecouteurs = new ArrayList<EcouteurHorloge>();
	//TODO Ajouter des méthodes pour ajouter/supprimer des écouteurs

	public void run() {
		while (true) {
			LocalTime heureJava = LocalTime.now();
			adaptationHeure(heureJava);
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				System.err.println("pause interrompue");
			}
		}
	}
	private void adaptationHeure(LocalTime heureJava) {
		Heure heureActuelle = new Heure(heureJava);
		if (!heureActuelle.equals(heure)) {
			heure = heureActuelle;
			for (EcouteurHorloge ecouteurHorloge : ecouteurs) {
				ecouteurHorloge.heureModifiee(heure);
			}
			
		}	
	}
	
	public Heure getHeure() {
		return heure;
	}
	public void addEcouteurHorloge(EcouteurHorloge ecouteur){
		ecouteurs.add(ecouteur);
	}
	public void deleteEcouteurHorloge(EcouteurHorloge ecouteur){
		try{
			ecouteurs.remove(ecouteur);
		}
		catch(Exception e){
			System.out.print(e + "\n");
		}
	}
}
