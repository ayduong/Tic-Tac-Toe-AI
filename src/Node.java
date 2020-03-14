import java.util.*;

public class Node {
	public ArrayList<Node> children;
	public int xWin;
	public int oWin;
	public int draw;
	public Board board;
	public Move move;
	
	public Statistics stats = new Statistics();

	public Node(Board board) {
		this.board = board.copyBoard();
		this.children = new ArrayList<Node>();

	}
	public Node() {
		board = new Board();
		this.children = new ArrayList<Node>();
	}
	
	public Move getMove() {
		
		return move;
	}
}