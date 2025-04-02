package ie.atu.sw;

import static java.lang.System.out;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Comparator;

public class HillClimbing<E> {

	public void search(Node<E> node){
		var queue = new ArrayDeque<Node<E>>();
		queue.push(node);
		
		while (!queue.isEmpty()) {
			node = queue.pop();
			node.setVisited(true);

			out.println("Visiting..."+node.getData());
			
			if (node.isGoal()){
				getPath(node);
				break; //We're done. Bail out of loop.
			}else {
				var children = node.children();
				Arrays.sort(children, Comparator.comparing(Node<E>::heuristic).reversed());
				for (Node<E> child : children) {
					if (!child.isVisited()){
						child.setParent(node);
						queue.push(child);
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
		var climber = new HillClimbing<String>();
		climber.search(new IrelandMap().getStartNode());
	}
}