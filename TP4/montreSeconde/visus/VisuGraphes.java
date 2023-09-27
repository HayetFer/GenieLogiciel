package montreSeconde.visus;

import java.awt.GridLayout;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.SwingConstants;

public class VisuGraphes {
	private JComponent panneau;
	private JProgressBar barreHeure;
	private JProgressBar barreMinute;
	private JProgressBar barreSeconde;
    
	public VisuGraphes() {
	    GridLayout layout = new GridLayout(3,2);
		panneau = new JLabel();
		panneau.setLayout(layout);
		JLabel titreHeure = new JLabel("Heure :");
		JLabel titreMinute = new JLabel("Minute :");
		JLabel titreSeconde = new JLabel("Seconde :");
		barreHeure = new JProgressBar(SwingConstants.HORIZONTAL, 0, 23);
		barreMinute = new JProgressBar(SwingConstants.HORIZONTAL, 0, 59);
		barreSeconde = new JProgressBar(SwingConstants.HORIZONTAL, 0, 59);
		panneau.add(titreHeure);
		panneau.add(barreHeure);
		panneau.add(titreMinute);
		panneau.add(barreMinute);
		panneau.add(titreSeconde);
		panneau.add(barreSeconde);
	}
	public JComponent getVue() {
		return panneau;
	}
    public void setHeure(int heures, int minutes, int secondes) {
		barreHeure.setValue(heures);
		barreMinute.setValue(minutes);
		barreSeconde.setValue(secondes);
	}
}
