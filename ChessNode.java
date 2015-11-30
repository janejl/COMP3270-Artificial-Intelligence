import java.util.HashMap;


public class ChessNode {
	private ChessNode parent_node;
	
	private int p_key;
	private int[] from;
	private int[] dest;
	private boolean isMyTurn; // Machine's move
	
	private int value; // will get assigned during evaluation process
	private int depth;
	private Chessman eaten_value;
	private int eaten_key;
	// root constructor
	public ChessNode(int piece_key, int[] from, int[] dest, boolean machine_turn){
		p_key = piece_key;
		this.from = from;
		this.dest = dest;
		parent_node = null;
		value = 0;
		depth = 0;
		isMyTurn = machine_turn;
		eaten_value = null;
		eaten_key = -1;
	}
	
	// normal constructor
	public ChessNode(int piece_key, int[] from, int[] dest, boolean machine_turn, ChessNode parent){
		p_key = piece_key;
		this.from = from;
		this.dest = dest;
		value = 0;
		isMyTurn = machine_turn;
		eaten_value = null;
		eaten_key = -1;
		if(parent != null){
			parent_node = parent;
			depth = 1 + parent.getDepth();
		}else{
			parent_node = null;
			depth = 0;
		}
	}
	// check game status: p - play, w - win, l - lose
	public char checkStatus(HashMap<Integer, Chessman> piece_list){
		if(piece_list.containsKey(1)){
			if(piece_list.containsKey(21)){
				return 'p';
			}else{
				return 'l';
			}
		}
		return 'w';
	}
	public void move(char[][] chess_board, int[][] chess_board_key, HashMap<Integer, Chessman> piece_list){
		if(chess_board_key[dest[1]][dest[0]] != 0){
			eaten_key = chess_board_key[dest[1]][dest[0]];
			eaten_value = piece_list.get(eaten_key);
			piece_list.remove(eaten_key);
		}
		
		if((isMyTurn && from[1] == 4) || (!isMyTurn && from[1] == 5)){
			// upgrade soldier to big soldier
			chess_board[dest[1]][dest[0]] = isMyTurn? 'b':'B';
		}else{
			chess_board[dest[1]][dest[0]] = chess_board[from[1]][from[0]];
		}
		chess_board_key[dest[1]][dest[0]] = chess_board_key[from[1]][from[0]];
		chess_board[from[1]][from[0]] = 'n';
		chess_board_key[from[1]][from[0]] = 0;
	}
	public void revert(char[][] chess_board, int[][] chess_board_key, HashMap<Integer, Chessman> piece_list){
		if((isMyTurn && from[1] == 4) || (!isMyTurn && from[1] == 5)){
			// degrade big soldier to soldier
			chess_board[from[1]][from[0]] = isMyTurn? 's':'S';
		}else{
			chess_board[from[1]][from[0]] = chess_board[dest[1]][dest[0]];
		}
		chess_board_key[from[1]][from[0]] = chess_board_key[dest[1]][dest[0]];
		
		if(eaten_key != -1){
			piece_list.put(eaten_key, eaten_value);
			chess_board[dest[1]][dest[0]] = eaten_value.getType();
			chess_board_key[dest[1]][dest[0]] = eaten_key;
		}else{
			chess_board[dest[1]][dest[0]] = 'n';
			chess_board_key[dest[1]][dest[0]] = 0;
		}
	}
	
	public void setValue(int v){
		value = v;
	}
	public int getPieceKey(){
		return p_key;
	}
	public int getValue(){
		return value;
	}
	public ChessNode getParent(){
		return parent_node;
	}
	public boolean isMachineTurn(){
		return isMyTurn;
	}
	public int getDepth(){
		return depth;
	}
	public int[] getFrom(){
		return from;
	}
	public int[] getDest(){
		return dest;
	}
	@Override
	public String toString(){
		String line = "key: "+p_key+", from("+from[0]+","+from[1]+"), to("+dest[0]+","+dest[1]+"), value: "+value+", depth: "+depth+", eate key: "+eaten_key;
		return line;
	}

}
