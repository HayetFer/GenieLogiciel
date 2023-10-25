package dessin;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import dessin.formes.AbstractForme;
import dessin.formes.Cercle;
import dessin.formes.Forme;
import dessin.formes.GroupeFormes;

public class Fenetre extends JFrame {
	private JLayeredPane contenu;
	private List<AbstractForme> formes;
	private Color[] couleurs = {Color.BLACK, Color.BLUE, Color.RED, Color.GREEN, Color.YELLOW};
	private Optional<AbstractForme> formeSelectionnee;
	
	private JCheckBoxMenuItem rempli;
	private JMenuItem agrandir;
	private JMenuItem diminuer;
	private JMenuItem ajouter;
	private JMenuItem detruire;
	
	private GroupeFormes groupe;
	
	public Fenetre() {
		super("dessin");
		initMenus();
		formes = new ArrayList<>();
		groupe = new GroupeFormes();
		formeSelectionnee = Optional.empty();
		contenu = new JLayeredPane();
		contenu.setPreferredSize(new Dimension(600,300));
		setContentPane(contenu);
		pack();
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		creerCercles();
		contenu.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent me) {
				formeCliquee(me);
			}
		});
		setVisible(true);
	}
	
	public void initMenus() {
		JMenuBar barre = new JMenuBar();
		JMenu fichier = new JMenu("Fichier");
		JMenuItem quitter = new JMenuItem("Quitter");
		quitter.addActionListener(ev -> System.exit(0));
		fichier.add(quitter);
		JMenu style = new JMenu("Style");
		barre.add(fichier);
		barre.add(style);
		rempli = new JCheckBoxMenuItem("Rempli");
		rempli.setEnabled(false);
		rempli.addActionListener(new EcouteurRempli());
		style.add(rempli);
		agrandir = new JMenuItem("Agrandir");
		agrandir.addActionListener(new EcouteurAgrandir());
		style.add(agrandir);
		diminuer = new JMenuItem("Diminuer");
		diminuer.addActionListener(new EcouteurDiminuer());
		style.add(diminuer);
		JMenu groupe = new JMenu("Groupe");
		ajouter = new JMenuItem("Ajouter");
		ajouter.setEnabled(false);
		ajouter.addActionListener(new EcouteurAjouter());
		groupe.add(ajouter);
		detruire = new JMenuItem("Détruire");
		detruire.setEnabled(false);
		detruire.addActionListener(new EcouteurDetruire());
		groupe.add(detruire);
		barre.add(groupe);
		setJMenuBar(barre);		
	}
	
	class EcouteurAgrandir implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			formeSelectionnee.ifPresent(AbstractForme::agrandir);
		}
	}
	
	class EcouteurDiminuer implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			formeSelectionnee.ifPresent(AbstractForme::diminuer);
		}
	}

	class EcouteurRempli implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			formeSelectionnee.ifPresent(p->p.setRempli(rempli.isSelected()));
		}		
	}
	
	class EcouteurAjouter implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			groupe.ajouterForme(formeSelectionnee.get());
			formes.remove(formeSelectionnee.get());
			formeSelectionnee = Optional.empty();
			detruire.setEnabled(true);
			ajouter.setEnabled(false);
			agrandir.setEnabled(false);
			diminuer.setEnabled(false);
			rempli.setSelected(false);
			rempli.setEnabled(false);
			if (!formes.contains(groupe)) {
				System.err.println("Le groupe n'est pas encore dans la liste");
				formes.add(0, groupe);
			}
			System.err.println("On revoit les couches");
			positionnerCouches();
			
		}
	}
	
	class EcouteurDetruire implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent ae) {
			Set<AbstractForme> contenu = groupe.vider();
			formes.remove(groupe);
			formes.addAll(contenu);
			positionnerCouches();
			detruire.setEnabled(false);
		}
	}
	
	public void positionnerCouches() {
		contenu.removeAll();
		for(int i = 0; i < formes.size(); i++) {
			AbstractForme formeCourante = formes.get(i);
			System.err.println("on ajoute dans le LayeredPane " + formeCourante);
			contenu.add(formeCourante, i+1, 0);
			formeCourante.repaint();
		}
		System.out.println("**comp** : " + Arrays.toString(contenu.getComponents()));
	}
	public Cercle addCercle(int abscisse, int ordonnee, int rayon, Color couleur, int prof) {
		Cercle cercle = new Cercle(abscisse, ordonnee, rayon, couleur);
		return cercle;
	}
	
	public void creerCercles() {
		for (int i = 0; i < 5; i++) {
			Cercle c = addCercle(200+10*i, 100+10*i, 50+5*i, couleurs[i], new Integer(i));
			formes.add(c);
		}
		positionnerCouches();
	}
		
	public void shuffle() {
		Collections.shuffle(formes);
		for (int i = 0; i < formes.size(); i++) {
			contenu.setLayer(formes.get(i), i);
		}
	}
	
	public void formeCliquee(MouseEvent me) {
		boolean trouve = false;
		formeSelectionnee.ifPresent(p->p.setSelectionne(false));
		for (int i = formes.size()-1; i >= 0 && !trouve; i--) {
			AbstractForme cercleCourant = formes.get(i);
			trouve = cercleCourant.clique(me);
			if (trouve) {
				System.err.println("trouvé!");
				formeSelectionnee = Optional.of(cercleCourant);
				rempli.setSelected(formeSelectionnee.get().isRempli());
				rempli.setEnabled(true);
				agrandir.setEnabled(true);
				diminuer.setEnabled(true);
				formeSelectionnee.get().setSelectionne(true);
				if (formeSelectionnee.get() == groupe) {
					ajouter.setEnabled(false);					
				}
				else {
					ajouter.setEnabled(true);
				}
			}
		}
		if (!trouve) {
				rempli.setEnabled(false);
				agrandir.setEnabled(false);
				diminuer.setEnabled(false);
				ajouter.setEnabled(false);
		}
	}
}
