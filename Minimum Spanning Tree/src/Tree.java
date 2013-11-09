import java.util.*;
import java.io.*;

public class Tree {
	public static void main(String[] args) throws IOException {
		Tree tree = new Tree("src/data.txt");
		tree.find();
	}

	public Tree(String fileName) throws IOException {
		vertexMap = new HashMap<Vertex, Vertex>();
		visitedVertices = new ArrayList<Vertex>();
		visitedEdges = new ArrayList<Edge>();
		readFile(fileName);
	}

	public void readFile(String fileName) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(fileName));
		String info = br.readLine();
		while (info != null) {
			addEdge(info);
			info = br.readLine();
		}
	}

	public void find() {
		// initialize the tree to some vertex of G;
		Vertex s = vertexMap.remove(new Vertex(1));
		visitedVertices.add(s);
		// initialize the set Q of vertices not yet in the tree to the vertices
		// of G;
		while (!vertexMap.isEmpty()) {
			Edge e = lightestEdge();
			visitedVertices.add(vertexMap.remove(e.getDest()));
			visitedEdges.add(e);
		}
		printCost();
	}

	private void printCost() {
		double totalCost = 0;
		for (Edge e : visitedEdges)
			totalCost += e.getCost();
		System.out.println(totalCost);
	}

	private Edge lightestEdge() {
		Edge minEdge = null;
		for (Vertex v : visitedVertices) {
			for (Edge e : v.getAdj()) {
				if (!visitedVertices.contains(e.getDest())) {
					if (minEdge == null)
						minEdge = e;
					else if (e.getCost() < minEdge.getCost())
						minEdge = e;
				}
			}
		}
		return minEdge;
	}

	public void addEdge(String info) {
		String[] attr = info.split(" ");
		int init = Integer.parseInt(attr[0]);
		int dest = Integer.parseInt(attr[1]);
		double cost = Double.parseDouble(attr[2]);
		Vertex v = getVertex(init);
		Vertex w = getVertex(dest);
		v.addEdge(w, cost);
		w.addEdge(v, cost);
	}

	private Vertex getVertex(int number) {
		Vertex v = vertexMap.get(new Vertex(number));
		if (v == null) {
			v = new Vertex(number);
			vertexMap.put(v, v);
		}
		return v;
	}

	private HashMap<Vertex, Vertex> vertexMap;
	private ArrayList<Vertex> visitedVertices;
	private ArrayList<Edge> visitedEdges;
}
