import java.util.*;
public class Vertex {
	private int number;
	private ArrayList<Edge> adj;
	
	public Vertex(int number){
		this.number = number;
		adj = new ArrayList<Edge>();
	}
	
	public String toString(){
		return "" + number;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Vertex other = (Vertex) obj;
		if (number != other.number)
			return false;
		return true;
	}

	public void addEdge(Vertex v, double cost){
		adj.add(new Edge(v, cost));
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public ArrayList<Edge> getAdj() {
		return adj;
	}

	public void setAdj(ArrayList<Edge> adj) {
		this.adj = adj;
	}
}
