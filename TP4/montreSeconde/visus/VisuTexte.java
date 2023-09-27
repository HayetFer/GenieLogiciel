package montreSeconde.visus;

import javax.swing.JComponent;
import javax.swing.JLabel;

public class VisuTexte {
	private JLabel visu;
	
	public VisuTexte() {
		visu = new JLabel();
		visu.setText("HH:MM:SS");
	}
	public void setTexte(String texte) {
		visu.setText(texte);
	}
	public JComponent getVue() {
		return visu;
	}
}
