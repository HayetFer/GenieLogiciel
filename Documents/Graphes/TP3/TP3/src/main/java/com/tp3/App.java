package com.tp3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Random;
import java.util.Set;

import javax.swing.JFrame;

import org.graphstream.algorithm.Dijkstra;
import org.graphstream.algorithm.generator.RandomGenerator;
import org.graphstream.graph.Edge;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.SingleGraph;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

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

    public static void DijkstraNaif(Graph graph, String sourceId) {
        Node sourceNode = graph.getNode(sourceId);
        PriorityQueue<Node> f = new PriorityQueue<>((a, b) -> 
            Double.compare(
                a.getAttribute("dist") != null ? (Double)a.getAttribute("dist") : Double.POSITIVE_INFINITY, 
                b.getAttribute("dist") != null ? (Double)b.getAttribute("dist") : Double.POSITIVE_INFINITY
            )
        );
        for (Node node : graph.nodes().toArray(Node[]::new)) {
            node.setAttribute("dist", Double.POSITIVE_INFINITY);
            node.setAttribute("parent", null);
        }
        sourceNode.setAttribute("dist", 0.0);
        f.add(sourceNode);
        Set<Node> processedNodes = new HashSet<>();
    
        long startTime = System.nanoTime();
        while (!f.isEmpty()) {
            Node u = f.poll();
    
            if (processedNodes.contains(u)) continue;
            processedNodes.add(u);
    
            for (Edge edge : u.edges().toArray(Edge[]::new)) {
                Node v = edge.getOpposite(u);
                
                Double edgeWeight = (double)edge.getAttribute("weight");
    
                Double uDist = (double)u.getAttribute("dist");    
                double newDist = uDist + edgeWeight;
    
                Double vDist = (double)v.getAttribute("dist");
                if (newDist < vDist) {
                    v.setAttribute("dist", newDist);
                    v.setAttribute("parent", u);
                    f.add(v);
                }
            }
        }
    
        long endTime = System.nanoTime();
        System.out.println("Résultats du Dijkstra Naïf (temps : " + (endTime - startTime) / 1e6 + " ms)");
    }
    // Utilisation de l'algorithme Dijkstra intégré de GraphStream
    public static void DijkstraGraphStream(Graph graph, String sourceId) 
    {

        Dijkstra dijkstraGS = new Dijkstra(Dijkstra.Element.EDGE, null, "weight");
        dijkstraGS.init(graph);
        dijkstraGS.setSource(graph.getNode(sourceId));
        long startTime = System.nanoTime();
        dijkstraGS.compute();
        long endTime = System.nanoTime();

        // Afficher les distances finales
        System.out.println("Résultats du Dijkstra (GraphStream) (temps : " + (endTime - startTime) / 1e6 + " ms):");
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
        int avgDegree = 10;

        for (int size : graphSizes) {
            System.out.println("=== Test sur un graphe de " + size + " nœuds ===");

            // Générer un graphe aléatoire
            Graph graph = generateRandomGraph(size, avgDegree);

            // Affichage du graphe
            //graph.display();

            // Appliquer les deux algorithmes de Dijkstra
            System.out.println("\n=== Dijkstra Naïf ===");
            DijkstraNaif(graph, "0");

            System.out.println("\n=== Dijkstra (GraphStream) ===");
            DijkstraGraphStream(graph, "0");

            System.out.println("=====================================\n");
        }
    }

    public static void showCSVFile()
        {
            String csvFile = "TP3/src/resources/Dijkstra.csv";
            DefaultCategoryDataset dataset = new DefaultCategoryDataset();
    
            try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
                String line;
                boolean header = true;
    
                while ((line = br.readLine()) != null) {
                    if (header) {
                        header = false; 
                        continue;
                    }
                    String[] values = line.split(",");
                    int graphSize = Integer.parseInt(values[0].trim());
                    double naiveTime = Double.parseDouble(values[1].trim());
                    double graphStreamTime = Double.parseDouble(values[2].trim());
    
                    dataset.addValue(naiveTime, "Dijkstra Naïf", String.valueOf(graphSize));
                    dataset.addValue(graphStreamTime, "Dijkstra GraphStream", String.valueOf(graphSize));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
    
            // Création du graphique
            JFreeChart lineChart = ChartFactory.createLineChart(
                    "Comparaison des performances de Dijkstra",
                    "Taille du graphe (nœuds)",
                    "Temps d'exécution (ms)",
                    dataset,
                    PlotOrientation.VERTICAL,
                    true, true, false);
    
            // Affichage dans une fenêtre
            JFrame frame = new JFrame("Dijkstra Performance Plot");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.add(new ChartPanel(lineChart));
            frame.pack();
            frame.setVisible(true);
        }
        public static void main(String[] args) 
        {
            showCSVFile();
       //runTests();
    }
   
}
