package dessin.formes;
import java.awt.event.MouseEvent;

public interface Forme {

	void setSelectionne(boolean b);

	void setRempli(boolean b);

	boolean isRempli();

	void agrandir();

	void diminuer();

	boolean clique(MouseEvent me);

	int getXmin();
	int getXmax();
	int getYmin();
	int getYmax();
}