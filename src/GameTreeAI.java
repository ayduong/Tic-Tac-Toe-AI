import java.util.*;

public class GameTreeAI extends Player {

	private Map<String, Node> treeNodes = new HashMap<String, Node>();
	private Node root;

	private GameTreeAI(GameUserInterface gui, int num) {
		super(gui, num);
	}

	public static Player getPlayer(GameUserInterface gui, int num) {
		return new GameTreeAI(gui, num);
	}

	public String toString() {
		return "Game Tree AI";
	}

	public void learn() {
		root = new Node();
		createGameTree(root);
		System.out.println("done w tree");
		// Statistics stat = new Statistics(root.xWin, root.oWin, root.draw);
		// stat.addStats(root);
		// int[] wins = stat.returnStats();
		// System.out.println(wins[0] + ", " + wins[1] + ", " + wins[2]);
		System.out.println(root.stats.toString());
		// root.stats.sumStatsX(root);
		// System.out.println("xWins: " + root.xWin);
	}

	public Move getMove(Board board) {

		Node current = findNode(board, root);
		System.out.println("Board: " + board.toString());
		//find(board, root);
		System.out.println("Node Board: " + current.board.toString());
		Move move = getBestMoveX(current);
		// look at its children, and find the best one
		// return the move that maps to the best child.

		// once we get our move from our Game Tree
		// we must show the move and return it
		getGui().showAIMove(move);

		return move;

	}

	private Move getBestMoveX(Node node) {
		int max = -1;
		Node maxNode = new Node();
		for (int i = 0; i < node.children.size(); i++) {
			Node n = node.children.get(i);
			if(n.xWin == 1 && n.oWin == 0 && n.draw == 0) {
				return n.move;
			}
			if (n.stats.xWin > max) {
				max = n.stats.xWin;
				maxNode = n;
			}
		}

		//Srestem.out.println(node.board.toString());
		//System.out.println(maxNode.board.toString());

		return maxNode.move;
	}

	private Node findNode(Board b, Node node) {
		if (node.board.toString().equals(b.toString())) {
			return node;
		} else if (node.children == null) {
			return null;
		}
		Node n;

		for (int i = 0; i < node.children.size(); i++) {
			n = findNode(b, node.children.get(i));
			if (n != null) {
				return n;
			}
		}
		return null;
	}


	private void createGameTree(Node node) {
		Move[] availableMoves = node.board.getAllMoves();
		for (int x = 0; x < availableMoves.length; x++) {
			Move m = availableMoves[x];
			Node child = new Node(node.board);
			child.move = m;
			node.children.add(child);
			child.board.doMove(m);
			int winCheck = child.board.findWinner();
			if (winCheck == 1 || winCheck == 2 || child.board.isBoardFull()) {
				if (winCheck == 1) {
					child.xWin++;
				} else if (winCheck == 2) {
					child.oWin++;
				} else {
					child.draw++;
				}
			} else {
				createGameTree(child);
			}

			node.stats.addStats(child);
			// statClass stat = new statClass(node.xWin, node.oWin, node.draw);
			// int[] wins = stat.returnStats();
			//node.board.undoMove(m);

		}

		// print out board & node.statistics
	}

}