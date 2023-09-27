package montreSeconde;

import javax.swing.SwingUtilities;

import montreSeconde.horloge.EcouteurHorloge;
import montreSeconde.horloge.EcouteurTest;
import montreSeconde.horloge.Horloge;

public class Main {
	public static void main(String... args) {
		Horloge horloge = new Horloge();
		EcouteurTest test = new EcouteurTest();
		horloge.addEcouteurHorloge(test);
		horloge.start();
		SwingUtilities.invokeLater(() -> {new Fenetre(horloge);});
	}
}
