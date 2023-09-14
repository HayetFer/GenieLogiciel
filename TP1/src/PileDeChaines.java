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
        return pile.pop();
    }

    public void afficher() {
        System.out.println(pile);
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
