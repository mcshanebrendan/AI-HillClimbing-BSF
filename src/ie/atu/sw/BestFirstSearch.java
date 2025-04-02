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
			out.println("Expanding node: " + node.getData() + " (h=" + node.heuristic() + ")");

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
				//out.println(queue);
				printQueue(queue);
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

		out.println("Step-by-step path:");
		for (E step : path) {
			out.println(" -> " + step);
		}

		out.println("Start city: " + path.peekFirst());
		out.println("Goal reached: " + path.peekLast());
		out.println("Total distance: " + total);
	}


	private void printQueue(PriorityQueue<Node<E>> queue) {
		out.print("Queue now contains: ");
		for (Node<E> n : queue) {
			out.print(n.getData() + "(h=" + n.heuristic() + ") ");
		}
		out.println();
	}

	public static void main(String[] args) {
		var greedy = new BestFirstSearch<String>();
		greedy.search(new IrelandMap().getStartNode());
	}
}
