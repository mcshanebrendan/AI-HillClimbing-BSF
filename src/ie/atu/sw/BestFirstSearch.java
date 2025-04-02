package ie.atu.sw;

import static java.lang.System.out;

import java.util.*;

public class BestFirstSearch<E> {

	public void search(Node<E> node){
		var queue = new PriorityQueue<Node<E>>(Comparator.comparing(Node<E>::heuristic));
		queue.offer(node);
		
		while (!queue.isEmpty()) {
			node = queue.poll();
			node.setVisited(true);

			if (node.isGoal()){
				getPath(node);
				break; //We're done. Bail out of loop.
			}else {
				var children = node.children();
				for (Node<E> child : children) {
					if (!child.isVisited()){
						child.setParent(node);
						queue.offer(child);
					}
				}
				out.println(queue);
			}
		}
	}
	
	private void getPath(Node<E> node) {
		var total = 0.0d;
		var path = new ArrayDeque<E>();
		while (node != null) {
			path.push(node.getData());
			var next = node.getParent();
			if (next != null) {
				total += next.getDistance(node);
			}
			node = next;
		}
		out.println(path);
		out.println("Distance: " + total);
	}
	
	public static void main(String[] args) {
		var greedy = new BestFirstSearch<String>();
		greedy.search(new IrelandMap().getStartNode());
	}
}
