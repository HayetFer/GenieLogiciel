package com.tp3;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import org.graphstream.algorithm.Dijkstra;
import org.graphstream.algorithm.generator.RandomGenerator;
import org.graphstream.graph.Edge;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.SingleGraph;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void testRandomGenerator()
    {
        System.setProperty("org.graphstream.ui", "swing");
        Graph graph = new SingleGraph("RandomGraph");

        graph.setAttribute("ui.stylesheet", "edge { text-alignment: above; text-size: 14; text-color: black; }");
        graph.setAttribute("ui.quality");
        graph.setAttribute("ui.antialias");

        // Générateur aléatoire avec un degré moyen de 3
        RandomGenerator generator = new RandomGenerator(3);
        generator.addSink(graph);

        // Initialisation du générateur
        generator.begin();

        // Générer 10 nœuds
        for (int i = 0; i < 10; i++) {
            generator.nextEvents();
        }

        // Terminer le générateur
        generator.end();

        // Ajouter des poids aléatoires positifs aux arêtes
        Random rand = new Random();
        for (Edge edge : graph.edges().toArray(Edge[]::new)) {
            double weight = 1 + rand.nextDouble() * 9; // Poids entre 1.0 et 10.0
            edge.setAttribute("weight", weight);
            edge.setAttribute("ui.label", String.format("%.2f", weight));
        }

        // Afficher le graphe
        graph.display();
    }

        public static void DijkstraNaif(Graph graph, String sourceId) 
    {
        long startTime = System.nanoTime();

        // Initialisation des distances et du nœud source
        for (Node node : graph) {
            node.setAttribute("dist", Double.POSITIVE_INFINITY); // Distance infinie par défaut
        }
        Node source = graph.getNode(sourceId);
        source.setAttribute("dist", 0.0); // Distance de la source = 0

        Set<Node> visited = new HashSet<>(); // Ensemble des nœuds visités

        while (visited.size() < graph.getNodeCount()) {
            // Trouver le nœud u avec la distance minimale non visité
            Node u = null;
            double minDist = Double.POSITIVE_INFINITY;

            for (Node node : graph) {
                if (!visited.contains(node)) {
                    double dist = (double) node.getAttribute("dist");
                    if (dist < minDist) {
                        minDist = dist;
                        u = node;
                    }
                }
            }

            if (u == null) break; // Aucun nœud accessible restant
            visited.add(u); // Marquer u comme visité

            // Relaxation des voisins de u
            for (Edge edge : u.edges().toArray(Edge[]::new)) {
                Node v = edge.getTargetNode();
                if (visited.contains(v)) continue;

                double weight = (double) edge.getAttribute("weight");
                double newDist = (double) u.getAttribute("dist") + weight;

                if (newDist < (double) v.getAttribute("dist")) {
                    v.setAttribute("dist", newDist);
                }
            }
        }

        long endTime = System.nanoTime();

        // Afficher les distances finales
        System.out.println("Résultats du Dijkstra naïf (temps : " + (endTime - startTime) / 1e6 + " ms):");
    }

    // Utilisation de l'algorithme Dijkstra intégré de GraphStream
    public static void DijkstraGraphStream(Graph graph, String sourceId) 
    {
        long startTime = System.nanoTime();

        Dijkstra dijkstraGS = new Dijkstra(Dijkstra.Element.EDGE, null, "weight");
        dijkstraGS.init(graph);
        dijkstraGS.setSource(graph.getNode(sourceId));
        dijkstraGS.compute();

        long endTime = System.nanoTime();

        // Afficher les distances finales
        System.out.println("Résultats du Dijkstra (GraphStream) (temps : " + (endTime - startTime) / 1e6 + " ms):");
        for (Node node : graph) {
            double dist = dijkstraGS.getPathLength(node);
            //System.out.println("Nœud " + node.getId() + ": " + (dist == Double.POSITIVE_INFINITY ? "Inaccessible" : dist));
        }

        dijkstraGS.clear();
    }
    public static Graph generateRandomGraph(int numNodes, int avgDegree) {
        Graph graph = new SingleGraph("RandomGraph");

        graph.setAttribute("ui.stylesheet", "edge { text-alignment: above; text-size: 14; text-color: black; }");
        graph.setAttribute("ui.quality");
        graph.setAttribute("ui.antialias");

        RandomGenerator generator = new RandomGenerator(avgDegree);
        generator.addSink(graph);

        generator.begin();
        for (int i = 0; i < numNodes; i++) {
            generator.nextEvents();
        }
        generator.end();

        // Ajouter des poids aléatoires positifs aux arêtes
        Random rand = new Random();
        for (Edge edge : graph.edges().toArray(Edge[]::new)) {
            double weight = 1 + rand.nextDouble() * 9; // Poids entre 1.0 et 10.0
            edge.setAttribute("weight", weight);
            edge.setAttribute("ui.label", String.format("%.2f", weight));
        }

        return graph;
    }

    // Fonction pour effectuer les tests sur plusieurs graphes
    public static void runTests() {
        int[] graphSizes = {100, 200, 500, 1000, 2000, 3000, 4000, 5000, 10000, 30000, 50000}; // Tailles des graphes à tester
        int avgDegree = 3;

        for (int size : graphSizes) {
            System.out.println("=== Test sur un graphe de " + size + " nœuds ===");

            // Générer un graphe aléatoire
            Graph graph = generateRandomGraph(size, avgDegree);

            // Affichage du graphe
            //graph.display(); // Vous pouvez activer l'affichage du graphe si nécessaire

            // Appliquer les deux algorithmes de Dijkstra
            System.out.println("\n=== Dijkstra Naïf ===");
            DijkstraNaif(graph, "0");

            System.out.println("\n=== Dijkstra (GraphStream) ===");
            DijkstraGraphStream(graph, "0");

            System.out.println("=====================================\n");
        }
    }

    public static void main(String[] args) 
    {
       runTests();
    }
   
}
