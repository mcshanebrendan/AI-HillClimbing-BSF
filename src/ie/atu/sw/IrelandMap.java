package ie.atu.sw;

public class IrelandMap {
	private Node<String> start;
	
	public IrelandMap() {
		init();
	}
	
	private void init() {
		start = new Node<String>("Galway", 200);
		var limerick = new Node<String>("Limerick", 170);
		var belfast = new Node<String>("Belfast", 270);
		var cork = new Node<String>("Cork", 120);
		var dublin = new Node<String>("Dublin", 130);
		var nenagh = new Node<String>("Nenagh", 120);  // Same heuristic as Cork

		var waterford = new Node<String>("Waterford", 0);		
		waterford.setGoal(true);
		
		var cashel = new Node<String>("Cashel", 45);
		var kilkenny = new Node<String>("Kilkenny", 40);

		// Cashel connects to Limerick and Kilkenny
		cashel.addChild(limerick, 90);
		limerick.addChild(cashel, 90);

		cashel.addChild(kilkenny, 35);
		kilkenny.addChild(cashel, 35);

		// Kilkenny connects to Dublin
		kilkenny.addChild(dublin, 130);
		dublin.addChild(kilkenny, 130);

		
		var athlone = new Node<String>("Athlone", 170);
		athlone.addChild(start, 80);
		start.addChild(athlone, 80);
		athlone.addChild(belfast, 300);
		belfast.addChild(athlone, 300);

		// Plateau connections
		limerick.addChild(nenagh, 50);
		nenagh.addChild(limerick, 50);
				
		dublin.addChild(nenagh, 130);
		nenagh.addChild(dublin, 130);

		// Optional path from Nenagh to Waterford to make it a viable path
		// Uncomment if you want to let hill climbing possibly use this route
		// nenagh.addChild(waterford, 140);
		// waterford.addChild(nenagh, 140);
		
		start.addChild(limerick, 105);
		start.addChild(belfast, 306);
		
		limerick.addChild(start, 105);
		limerick.addChild(belfast, 323);
		limerick.addChild(cork, 121);
		
		cork.addChild(dublin, 220);
		cork.addChild(waterford, 126);
		cork.addChild(limerick, 121);	
		
		waterford.addChild(cork, 126);
		waterford.addChild(dublin, 158);	
		
		dublin.addChild(belfast, 167);
		dublin.addChild(waterford, 158);
		dublin.addChild(cork, 220);	
		
		belfast.addChild(dublin, 167);
		belfast.addChild(start, 306);
		belfast.addChild(limerick, 323);
	}
	
	public Node<String> getStartNode(){
		return start;
	}
}