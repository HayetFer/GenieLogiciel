public class Exo5 {
public static void affiche(String titre, int... nombres) {
System.out.println(titre);
System.out.println("-----------------");
for (int n: nombres) {
System.out.println(n + "\t");
}
System.out.println();
}
public static void main(String... args) {
int[] pairs = {0, 2, 4, 6, 8, 10} ;
affiche("entiers", 1, 2, 3, 4) ; // (1)
affiche("pairs", pairs) ; // (2)
affiche("rien") ; // (3)
//affiche() ; // (4) Marche pas car pas d'arguments 
//affiche(2,3,4) ; // (5) Marche pas car manque un string avant
}
}