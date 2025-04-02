package ie.atu.sw;

import java.util.HashMap;
import java.util.Map;

public class Node<E>{
	private E data;
	private Node<E> parent;
	private boolean visited = false;
	private boolean goal = false;
	private double heuristic; 
	private Map<Node<E>, Double> children = new HashMap<>();
	
	public Node(E data, double distance) {
		this.data = data;
		this.heuristic = distance;
	}
	
	public double heuristic() {
		return this.heuristic;
	}
	
	public double getDistance(Node<E> child) {
		return children.get(child);
	}
	
	public void setParent(Node<E> parent) {
		this.parent = parent;
	}
	
	public Node<E> getParent(){
		return this.parent;
	}
	
	public boolean isVisited() {
		return visited;
	}
	
	public void setVisited(boolean visited) {
		this.visited = visited;
	}
	
	public void setGoal(boolean goal) {
		this.goal = goal;
	}
	
	public boolean isGoal() {
		return goal;
	}
	
	public E getData() {
		return data;
	}
	
	public void setData(E data) {
		this.data = data;
	}
	
	public void addChild(Node<E> child, double distance){
		children.put(child, distance);
	}
	
	@SuppressWarnings("unchecked")
	public Node<E>[] children(){
		return (Node<E>[]) children.keySet().toArray(new Node[children.size()]);
	}

	@Override
	public String toString() {
		return getData().toString();
	}
}