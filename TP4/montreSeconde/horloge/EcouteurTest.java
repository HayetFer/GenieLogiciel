package montreSeconde.horloge;

import java.text.DecimalFormat;

public class EcouteurTest implements EcouteurHorloge {
	@Override
	public void heureModifiee(Heure nouvelleHeure) {
		int heure = nouvelleHeure.getHeure();
		int minute = nouvelleHeure.getMinute();
		int seconde = nouvelleHeure.getSeconde();
		DecimalFormat format = new DecimalFormat("00");
		String affichage = format.format(heure) + ":"
				+ format.format(minute) + ":"
				+ format.format(seconde);
		System.out.println(affichage);
		
	}

}
