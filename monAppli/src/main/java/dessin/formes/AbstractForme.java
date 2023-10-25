package dessin.formes;
import javax.swing.JComponent;

public abstract class AbstractForme extends JComponent implements Forme {
	protected boolean selectionne;
	
	public AbstractForme() {
		selectionne = false;
	}

	@Override
	public void setSelectionne(boolean b) {
		selectionne = b;

	}
}
