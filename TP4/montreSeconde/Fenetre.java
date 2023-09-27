package montreSeconde;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import montreSeconde.horloge.EcouteurHorloge;
import montreSeconde.horloge.EcouteurTest;
import montreSeconde.horloge.Horloge;
import montreSeconde.visus.VisuGraphes;
import montreSeconde.visus.VisuHorloge;
import montreSeconde.visus.VisuTexte;

public class Fenetre extends JFrame {
	private Horloge horloge;
	public Fenetre(Horloge monHorloge) {
		horloge = monHorloge;
		setUp();
		creerMenu();
		setVisible(true);
	}

	private void setUp() {
		setSize(600,300);
		setLocationRelativeTo(null);
		setContentPane(new JDesktopPane());
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	private void creerMenu() {
		JMenuBar barre = new JMenuBar();
		// menu Fichier
		JMenu fichier = new JMenu("Fichier");
		JMenuItem quitter = new JMenuItem("Quitter");
		quitter.addActionListener(ae -> System.exit(0));
		fichier.add(quitter);
		barre.add(fichier);
		// menu Visu
		JMenu visu = new JMenu("Visualisation");
		JMenuItem vTexte = new JMenuItem(new ActionCreerVisuTexte());
		visu.add(vTexte);
		JMenuItem vGraphes = new JMenuItem(new ActionCreerVisuGraphes());
		visu.add(vGraphes);
		JMenuItem vHorloge = new JMenuItem(new ActionCreerVisuHorloge());
		visu.add(vHorloge);
		barre.add(visu);
		//finalisation
		setJMenuBar(barre);
		
	}
	class ActionCreerVisuTexte extends AbstractAction {

		public ActionCreerVisuTexte() {
			super("Vue Texte");
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			VisuTexte visu = new VisuTexte();
			JInternalFrame fenetre = new JInternalFrame("texte",true,true);
			fenetre.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			fenetre.add(visu.getVue());
			getContentPane().add(fenetre);
			fenetre.pack();
			fenetre.setVisible(true);
			visu.setTexte(String.format("%02d:%02d:%02d",horloge.getHeure().getHeure(),
						    horloge.getHeure().getMinute(), horloge.getHeure().getSeconde()));
			// enregistrer un écouteur auprès de l'horloge pour prévenir la visu texte
			EcouteurTexte text = new EcouteurTexte(visu);
			horloge.addEcouteurHorloge(text);
		}		
	}
	class ActionCreerVisuGraphes extends AbstractAction {

		public ActionCreerVisuGraphes() {
			super("Vue Graphes");
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			VisuGraphes visu = new VisuGraphes();
			JInternalFrame fenetre = new JInternalFrame("barres",true,true);
			fenetre.add(visu.getVue());
			getContentPane().add(fenetre);
			fenetre.setSize(200, 90);
			fenetre.setVisible(true);
			visu.setHeure(horloge.getHeure().getHeure(),horloge.getHeure().getMinute(),
				      horloge.getHeure().getSeconde());
			// enregistrer un écouteur auprès de l'horloge pour prévenir la visu graphe
			EcouteurVisu ect = new EcouteurVisu(visu);
			horloge.addEcouteurHorloge(ect);
		}
		
	}
	class ActionCreerVisuHorloge extends AbstractAction {

		public ActionCreerVisuHorloge() {
			super("Vue Horloge");
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			VisuHorloge visu = new VisuHorloge();
			JInternalFrame fenetre = new JInternalFrame("horloge",true,true);
			fenetre.add(visu.getVue());
			getContentPane().add(fenetre);
			fenetre.setVisible(true);
			fenetre.pack();
			visu.setHeure(horloge.getHeure());
			// enregistrer un écouteur auprès de l'horloge pour prévenir la visu horloge
			EcouteurDessin ect = new EcouteurDessin(visu);
			horloge.addEcouteurHorloge(ect);
		}		
	}
}
