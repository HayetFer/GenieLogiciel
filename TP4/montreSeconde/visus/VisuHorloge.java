package montreSeconde.visus;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JComponent;
import javax.swing.JPanel;

import montreSeconde.horloge.Heure;

public class VisuHorloge {
	private JPanel panneau;
	private int heure;
	private int minute;
	private int seconde;
	private static int POURCENTAGE_LONGUEUR_HEURE=60;
	private static int POURCENTAGE_LONGUEUR_MINUTE=80;
	private static int POURCENTAGE_LONGUEUR_SECONDE=80;
	
	
	public VisuHorloge() {
		panneau = new monPanneau();
	}

	public JComponent getVue() {
		return panneau;
	}	
	
	private class monPanneau extends JPanel {
		public Dimension getPreferredSize() {
			return new Dimension(60,60);
		}
		public void paint(Graphics g) {
			g.clearRect(0, 0, getWidth(), getHeight());
			int diametre = Math.min(getWidth(), getHeight())-10;
			g.drawOval(5, 5, diametre, diametre);
			int centreX = (diametre+10)/2;
			int centreY = centreX;
			double longueurHeure = diametre/2.0*POURCENTAGE_LONGUEUR_HEURE/100;
			double longueurMinute = diametre/2.0*POURCENTAGE_LONGUEUR_MINUTE/100;
			double longueurSeconde = diametre/2.0*POURCENTAGE_LONGUEUR_SECONDE/100;
			double angleSeconde = Math.PI/2-(seconde)/60.*2*Math.PI;
			double angleMinute = Math.PI/2-(minute)/60.*2*Math.PI;
			double angleHeure = Math.PI/2-(heure%12+minute/60.)/12.*2*Math.PI;
			int longueurHeureX = (int)(longueurHeure*Math.cos(angleHeure));
			int longueurHeureY = (int)(longueurHeure*Math.sin(angleHeure));
			int longueurMinuteX = (int)(longueurMinute*Math.cos(angleMinute));
			int longueurMinuteY = (int)(longueurMinute*Math.sin(angleMinute));
			int longueurSecondeX = (int)(longueurSeconde*Math.cos(angleSeconde));
			int longueurSecondeY = (int)(longueurSeconde*Math.sin(angleSeconde));
			g.setColor(Color.BLACK);
			g.drawLine(centreX, centreY, centreX+longueurSecondeX, centreY-longueurSecondeY);
			g.setColor(Color.BLUE);
			g.drawLine(centreX, centreY, centreX+longueurMinuteX, centreY-longueurMinuteY);
			g.setColor(Color.RED);
			g.drawLine(centreX, centreY, centreX+longueurHeureX, centreY-longueurHeureY);
		}
	}
	public void setHeure(Heure h) {
		heure = h.getHeure();
		minute = h.getMinute();
		seconde = h.getSeconde();
		panneau.repaint();
	}
}
