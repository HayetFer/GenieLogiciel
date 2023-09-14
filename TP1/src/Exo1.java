import java.util.*;
public class Exo1 {
public static void main(String... args) { //(1)
ArrayList<String> als = new ArrayList<String>() ; //(2)
als.add("La") ; //(3)
als.add("vie") ; //(4)
als.add("de") ;//.......................................................(5)
als.add("L'avis") ; //(6)
als.add("de") ; //(7)
als.add("Bryan") ; //(8)
System.out.println(als) ; //(9)
//Set<CharSequence> scs = als ;//.........................................(10)
ArrayList<String> scs2 = new ArrayList<String>(als) ; //(11)
//ArrayList<String> als2 = scs2 ; //(12)
ArrayList<String> als3 = new ArrayList<String>(scs2) ; //(13)
}
}