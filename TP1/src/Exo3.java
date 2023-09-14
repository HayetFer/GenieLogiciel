import java.util.*;
public class Exo3 {
public static void main(String... args) {
int a[] = {1, 2, 3, 4};
for (int x : a) {
System.out.println(x);
}
ArrayList<String> als = new ArrayList<String>();
als.add("Hello");
als.add("Good");
als.add("Bye");
for (String s: als) {
System.out.println(s);
}
CharSequence cs = "Hello";
/*or(char c: cs) {
System.out.println("c");
} //Implémente pas iterable */

}
}