package dessin.formes;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.util.HashSet;
import java.util.Set;

public class GroupeFormes extends AbstractForme {
	private Set<AbstractForme> contenu;
	private boolean selectionne;
	
	public GroupeFormes() {
		super();
		contenu = new HashSet<>();
		selectionne = false;
		this.setBounds(0, 0, 600, 300);
	}
	
	public Set<AbstractForme> vider() {
		Set<AbstractForme> retour = contenu;
		contenu = new HashSet<>();
		selectionne = false;
		return retour;
	}
	
	public void ajouterForme(AbstractForme f) {
		contenu.add(f);
		f.setSelectionne(false);
	}

	@Override
	public void setSelectionne(boolean b) {
		selectionne = b;
		repaint();
	}

	@Override
	public void setRempli(boolean b) {
		contenu.stream().forEach(x -> x.setRempli(b));
		repaint();
	}

	@Override
	public boolean isRempli() {
		return contenu.stream().allMatch(Forme::isRempli);
	}

	@Override
	public void agrandir() {
		contenu.stream().forEach(Forme::agrandir);	
		repaint();
	}

	@Override
	public void diminuer() {
		contenu.stream().forEach(Forme::diminuer);
		repaint();
	}

	@Override
	public Dimension getPreferredSize() {
		return new Dimension(600,300);
	}

	@Override
	public void paint(Graphics g) {
		contenu.stream().forEach(f -> f.paint(g));
		if (selectionne) {
			System.err.println(String.format("cadre : %d,%d,%d,%d", getXmin(), getXmax(), getYmin(), getYmax()));

			g.setColor(Color.LIGHT_GRAY);
			g.drawRect(getXmin(), getYmin(), getXmax()-getXmin(), getYmax()-getYmin());
		}
	}

	@Override
	public boolean clique(MouseEvent me) {
		System.err.println("clic en "+ me.getX() + "," +  me.getY());
		System.err.println(String.format("%d,%d,%d,%d", getXmin(), getXmax(), getYmin(), getYmax()));
		return me.getX() >= getXmin() &&
				me.getX() <= getXmax() &&
				me.getY() >= getYmin() &&
				me.getY() <= getYmax();
		
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
	public int getXmin() {
		return contenu.stream().mapToInt(Forme::getXmin).min().getAsInt();
	}

	@Override
	public int getXmax() {
		return contenu.stream().mapToInt(Forme::getXmax).max().getAsInt();
	}

	@Override
	public int getYmin() {
		return contenu.stream().mapToInt(Forme::getYmin).min().getAsInt();
	}

	@Override
	public int getYmax() {
		return contenu.stream().mapToInt(Forme::getYmax).max().getAsInt();
	}

	@Override
	public String toString() {
		return "GroupeCercle [contenu=" + contenu + "]";
	}
	
	
}
