package ie.atu.sw;

import static java.lang.System.out;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Comparator;

public class BeamSearch<E> {

	public void search(Node<E> node){
		var beam = 2;
		var fifo = new ArrayDeque<Node<E>>();
		
		fifo.offer(node);
		while(!fifo.isEmpty()){
			node = fifo.poll(); //Remove from the front
			node.setVisited(true);

			if (node.isGoal()){
				out.println("Reached " + node.getData());
				break; //We're done. Bail out of loop.
			}else{
				var children = node.children();
				Arrays.sort(children, Comparator.comparing(Node<E>::heuristic));
				
				int bound = children.length < beam ? children.length : beam;
				for (int i = 0; i < bound; i++) {
					if (!children[i].isVisited()){
						fifo.offer(children[i]); //Add to the end
					}
				}
				out.println(fifo);
			}
		}
	}
	
	public static void main(String[] args) {
		var beam = new BeamSearch<String>();
		beam.search(new IrelandMap().getStartNode());
	}
}