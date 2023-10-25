package dessin;
import javax.swing.SwingUtilities;

public class Main {

	public static void main(String[] args) {
		Fenetre f = new Fenetre();
		for (int i = 0; i < 10; i++) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//f.shuffle();
		}
	}

}
