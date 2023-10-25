package dessin.formes;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;

public class Cercle extends AbstractForme {
	private int abscisseCentre;
	private int ordonneeCentre;
	private int rayon;
	private Color couleur;
	
	private boolean selectionne;
	private boolean rempli;
	
	public Cercle(int abscisseCentre, int ordonneeCentre, int rayon, Color couleur) {
		super();
		this.abscisseCentre = abscisseCentre;
		this.ordonneeCentre = ordonneeCentre;
		this.rayon = rayon;
		this.couleur = couleur;
		this.selectionne = false;
		this.setBounds(0,0,600,300);

	}
	
	public Color getCouleur() {
		return couleur;
	}
	
	@Override
	public void setSelectionne(boolean b) {
		selectionne = b;
		repaint();
	}
	
	@Override
	public void setRempli(boolean b) {
		rempli = b;
		repaint();
	}

	@Override
	public boolean isRempli() {
		return rempli;
	}
	
	@Override
	public void agrandir() {
		rayon *= 1.1;
		repaint();
	}

	@Override
	public void diminuer() {
		rayon /= 1.1;
		repaint();
	}

	@Override
	public Dimension getPreferredSize() {
		return new Dimension(600,300);
	}
	
	@Override
	public int getWidth() {
		return 600;
	}
	@Override
	public int getHeight() {
		return 300;
	}
	

	@Override
	public void paint(Graphics g) {
		int abscisseDebut = abscisseCentre-rayon;
		int ordonneeDebut = ordonneeCentre-rayon;
		int largeur = 2*rayon;
		int hauteur = 2*rayon;
		Color couleurCourante = g.getColor();
		g.setColor(couleur);
		if (rempli) {
			g.fillOval(abscisseDebut, ordonneeDebut, largeur, hauteur);
		}
		else {
			g.drawOval(abscisseDebut, ordonneeDebut, largeur, hauteur);
		}
		if (selectionne) {
			g.setColor(Color.LIGHT_GRAY);
			g.drawRect(abscisseDebut, ordonneeDebut, largeur, hauteur);
		}
		g.setColor(couleurCourante);
	}
	
	@Override
	public boolean clique(MouseEvent me) {
		int abscisse = me.getX();
		int ordonnee = me.getY();
		double distanceCentre = Math.sqrt((abscisse-abscisseCentre)*(abscisse-abscisseCentre)
										+ (ordonnee-ordonneeCentre)*(ordonnee-ordonneeCentre));
		return distanceCentre <= rayon;
	}

	@Override
	public int getXmin() {
		return abscisseCentre-rayon;
	}

	@Override
	public int getXmax() {
		return abscisseCentre+rayon;
	}

	@Override
	public int getYmin() {
		return ordonneeCentre-rayon;
	}

	@Override
	public int getYmax() {
		return ordonneeCentre+rayon;
	}

	@Override
	public String toString() {
		return "Cercle [couleur=" + couleur + "]";
	}
	
}
