import java.util.ArrayList;

public class ChessNode {
	private char[][] chess_board; // state
	private boolean isMyTurn; // Machine's turn
	private ArrayList<Chessman> chessman_list;
	private ChessNode parent_node;
	private int depth;
	
	// root constructor
	public ChessNode(char[][] chess_board, boolean machine_turn, ArrayList<Chessman> chessman_list){
		this.chess_board = chess_board;
		isMyTurn = machine_turn;
		this.chessman_list = chessman_list;
		parent_node = null;
		depth = 0;
	}
	
	// normal constructor
	public ChessNode(char[][] chess_board, boolean machine_turn, ArrayList<Chessman> chessman_list, ChessNode parent){
		this.chess_board = chess_board;
		isMyTurn = machine_turn;
		this.chessman_list = chessman_list;
		if(parent != null){
			parent_node = parent;
			depth = 1 + parent.getDepth();
		}else{
			parent_node = null;
			depth = 0;
		}
	}
	
	public char[][] getChessBoard(){
		return chess_board;
	}
	public boolean isMachineTurn(){
		return isMyTurn;
	}
	public ArrayList<Chessman> getChessmanList(){
		return chessman_list;
	}
	public ChessNode getParent(){
		return parent_node;
	}
	public int getDepth(){
		return depth;
	}

	// check game status: p - play, w - win, l - lose, t - tie
	public char getGameStatus(){
		char st = 'p';
		
		return st;
	}
	
	// Estimation of the current state of the game -> Search through moves and pick the “best” one.
	public int getEvaluation(){
		
	}
}
