public class Statistics {
	public int xWin;
	public int oWin;
	public int draw;
	
	public Statistics() {
	}
	
	public Statistics(int xWin, int oWin, int draw) {
		this.xWin = xWin;
		this.oWin = oWin;
		this.draw = draw;

	}

	public void addStats(Node node) {
		if (node.children == null) {
			xWin += node.xWin;
			oWin += node.oWin;
			draw += node.draw;

		} else {
			for (int i = 0; i < node.children.size(); i++) {
				xWin += node.children.get(i).xWin;
				oWin += node.children.get(i).oWin;
				draw += node.children.get(i).draw;

				addStats(node.children.get(i));
			}
		}
	}
	
	/*public int sumStatsX(Node node) {
		if(node.children == null) {
			return node.xWin;
		}
		for (int i = 0; i < node.children.size(); i++) {
			node.xWin += sumStatsX(node.children.get(i));
		}
		return node.xWin;
	}*/
	
	public int[] returnStats() {
		int[] stats = { xWin, oWin, draw };
		return stats;
	}
	
	public String toString() {
		return "X wins: " + xWin + "\nO Wins: " + oWin + "\nDraws: " + draw;
	}
}