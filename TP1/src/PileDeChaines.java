import java.util.ArrayDeque;

public class PileDeChaines {
    private ArrayDeque<String> pile;

    public PileDeChaines() {
        pile = new ArrayDeque<>();
    }

    public void empiler(String element) {
        pile.push(element);
    }

    public String depiler() {
        if(!estVide()){
            return pile.pop();
        }
        else {
            return "Pile vile";
        }
    }
    public boolean estVide(){
        return pile.isEmpty();
    }
    public void afficher() {
        System.out.println(pile);
    }
    public String Consulter() {
        if(!estVide()){
            return pile.getFirst();
        }
        else {
            return "Pile vile";
        }
    }

    public static void main(String[] args) {
        PileDeChaines pileDeChaines = new PileDeChaines();

        pileDeChaines.empiler("Que");
        pileDeChaines.empiler("J");
        pileDeChaines.empiler("Aime");
        pileDeChaines.afficher();

        pileDeChaines.empiler("À");
        pileDeChaines.empiler("Faire");
        pileDeChaines.empiler("Oublier");
        pileDeChaines.depiler();
        pileDeChaines.afficher();

        pileDeChaines.empiler("Apprendre");
        pileDeChaines.afficher();

        pileDeChaines.depiler();
        pileDeChaines.depiler();
        pileDeChaines.depiler();
        pileDeChaines.depiler();
        pileDeChaines.depiler();
        pileDeChaines.depiler();
        pileDeChaines.afficher();

        pileDeChaines.depiler();
        pileDeChaines.afficher();
    }
}
