import montreSeconde.horloge.EcouteurHorloge;
import montreSeconde.horloge.Heure;
import montreSeconde.visus.VisuTexte;


public class EcouteurTexte implements EcouteurHorloge {
    private VisuTexte visu;

    public EcouteurTexte(VisuTexte visu) {
        this.visu = visu;
    }

    @Override
    public void heureModifiee(Heure horloge) {
        visu.setTexte(horloge.getHeure()+ ":" + horloge.getHeure()+ ":"  + horloge.getSeconde());
    }
}
