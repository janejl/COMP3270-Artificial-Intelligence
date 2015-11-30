import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class ChineseChess {

	private static char[][] chess_board;
	private static int[][] chess_board_key; // correspond to chess_board
	
	private static ArrayList<ChessNode> history;
	private static boolean isMyTurn; // Machine's turn
	private static HashMap<Integer, Chessman> piece_list;
	
	private final static int DEPTH = 4;
	
	/* 
	 * Human will take the first turn in default.
	 */
	public ChineseChess(){
		initChessBoard();
		isMyTurn = false;
		history = new ArrayList<ChessNode>();
		initChessBoard();
	}
	
	public static void main(String[] args) {
        System.out.println("=========================== Game Start ===========================");
        Scanner scanner = new Scanner(System.in);
        
        ChineseChess game = new ChineseChess();
                
        int x, y, nx, ny;      
        String[] digit;
        char result;
        ChessNode my_node = null;
        ChessNode ur_node = null;
        
        while(true){
        	printChessBoard();
        	System.out.println("You are red. Please enter (from.x from.y dest.x dest.y) (e.g.: 4 9 4 8):");
	        digit = scanner.nextLine().split(" ");
			x = Integer.parseInt(digit[0]);
		    y = Integer.parseInt(digit[1]);
		    nx = Integer.parseInt(digit[2]);
		    ny = Integer.parseInt(digit[3]);
		    
		    int[] from = {x,y};
			int[] dest = {nx,ny};
			
			// 1. check if current move is legal or not
			ChessRule rule = new ChessRule();
			if(rule.moveRule(x, y, nx, ny, chess_board) == 'f'){
				System.out.println("Fail the move.");
		    	isMyTurn = false;
		    	continue; // illegal -> require user to re-enter
			}
			
			// 2. check if current legal move will win/lose/play.
			ur_node = new ChessNode(chess_board_key[y][x], from, dest, isMyTurn, my_node);
	    	result = ur_node.checkStatus(piece_list);
	    	
	    	if(result == 'w'){
	    		System.out.println("You win!");
	    		break;
	    	}else if(result == 'l'){
	    		System.out.println("You lose!");
	    		break;
	    	}else{
	    		// Continue playing -> Real Move
		    	ur_node.move(chess_board, chess_board_key, piece_list);
		    	history.add(ur_node);
		    	//System.out.println("Your movement is saved to history: ur_node: "+ur_node);
		    	
		    	// 3. response to user's action
		    	isMyTurn = true;
		    	System.out.println("Machine is thinking ... ");
		    	
		    	my_node = searchBestMove(ur_node);
				result = my_node.checkStatus(piece_list);
				
				if(result == 'w'){
					System.out.println("You lose!");
					break;
				}else if(result == 'l'){
					System.out.println("You win!");
					break;
				}else{
					// Continue playing -> Real Move
					my_node.move(chess_board, chess_board_key, piece_list);
					history.add(my_node);
				}
			    isMyTurn = false;
	    	}
        }
        System.out.println("=========================== Game End ===========================");
	}
	
	// 1. check for state - done.
	// 2. generate next step for selected piece (iterate through all available pieces) with evaluation value
	// 3. push into openset, alpha beta pruning
	// 3. choose best one and go, put into closedset (? may not useful, no need to print path)
	// 4. clean all value in tree and wait for user's step to trigger new decision tree. 

	// return: f - illegal move, w - win game, l - lose game, p - continue playing
	public static ChessNode searchBestMove(ChessNode current){
		double time_start = System.nanoTime();
		ArrayList<ChessNode> successors;
		ChessNode best_node = null;
		
		ChessSuccessor childGenerator = new ChessSuccessor();
		successors = childGenerator.getSuccessor(chess_board, chess_board_key, piece_list, current);
		
		
		
		for(ChessNode node : successors){
			// try the move of this node
			node.move(chess_board, chess_board_key, piece_list);
			//System.out.println("searchBestMove: try the move: " + node);
			//printChessBoard();

			node.setValue(alphabeta(node, DEPTH, Integer.MIN_VALUE, Integer.MAX_VALUE, false));
			
			//System.out.println("Finish for node: "+ node + "... best_node: "+best_node);
			if(best_node == null || node.getValue() >= best_node.getValue()){
				best_node = node;
			}
			// roll back for next successor
			node.revert(chess_board, chess_board_key, piece_list);
		}
		
		double time_spent = (System.nanoTime() - time_start)/1000000000.0;
		System.out.println("Time: " + time_spent);
		return best_node;
	}

	private static int alphabeta(ChessNode node, int depth, int alpha, int beta, boolean isMachine){
		char node_state = node.checkStatus(piece_list);
		if(node_state != 'p' || depth == 0){
			ChessEvaluate eva = new ChessEvaluate();
			int value = eva.giveEvaluation(chess_board, piece_list, node);
			//System.out.println(">>> return αβ value: " + value + " for node: " + node);
			return value;
		}
		
		// Machine is max-player, user is min-player
		int value;
		ChessSuccessor childGenerator = new ChessSuccessor();
		ArrayList<ChessNode> sub_successors;
		
		if(isMachine){
			//System.out.println("alphabeta: maxplayer...");
			value = Integer.MIN_VALUE;
			sub_successors = childGenerator.getSuccessor(chess_board, chess_board_key, piece_list, node);
			for(ChessNode sub_node : sub_successors){
				sub_node.move(chess_board, chess_board_key, piece_list);
				value = Math.max(value, alphabeta(sub_node, depth-1, alpha, beta, false));
				sub_node.revert(chess_board, chess_board_key, piece_list);
				alpha = Math.max(alpha, value);
				if(beta <= alpha){
					System.out.println("β cut-off");
					break; // β cut-off
				}
			}
		}else{
			//System.out.println("alphabeta: minplayer...");
			value = Integer.MAX_VALUE;
			sub_successors = childGenerator.getSuccessor(chess_board, chess_board_key, piece_list, node);
			for(ChessNode sub_node : sub_successors){
				sub_node.move(chess_board, chess_board_key, piece_list);
				value = Math.min(value, alphabeta(sub_node, depth-1, alpha, beta, false));
				sub_node.revert(chess_board, chess_board_key, piece_list);
				alpha = Math.min(alpha, value);
				if(beta <= alpha){
					System.out.println("α cut-off");
					break; // α cut-off
				}
			}
		}		
		return value;
	}
	
	// Set up a chess board
	private void initChessBoard(){
		piece_list = new HashMap<Integer, Chessman>();
		piece_list = new HashMap<Integer, Chessman>();
		Chessman piece;
		
		chess_board = new char[][]{{'r','h','e','a','g','a','e','h','r'},
								   {'n','n','n','n','n','n','n','n','n'},
								   {'n','c','n','n','n','n','n','c','n'},
								   {'s','n','s','n','s','n','s','n','s'},
								   {'n','n','n','n','n','n','n','n','n'},
								   {'n','n','n','n','n','n','n','n','n'},
								   {'S','n','S','n','S','n','S','n','S'},
								   {'n','C','n','n','n','n','n','C','n'},
								   {'n','n','n','n','n','n','n','n','n'},
								   {'R','H','E','A','G','A','E','H','R'}};

		chess_board_key = new int[][]{	{8,6,4,2,1,3,5,7,9},
										{0,0,0,0,0,0,0,0,0},
										{0,10,0,0,0,0,0,11,0},
										{12,0,13,0,14,0,15,0,16},
										{0,0,0,0,0,0,0,0,0},
										{0,0,0,0,0,0,0,0,0},
										{32,0,33,0,34,0,35,0,36},
										{0,30,0,0,0,0,0,31,0},
										{0,0,0,0,0,0,0,0,0},
										{28,26,24,22,21,23,25,27,29}};
		
		// set General:
		piece = new Chessman('g', 4, 0);
		piece_list.put(1,piece);
		piece = new Chessman('G', 4, 9);
		piece_list.put(21,piece);
		
		// set Advisor:
		piece = new Chessman('a', 3, 0);
		piece_list.put(2,piece);
		piece = new Chessman('a', 5, 0);
		piece_list.put(3,piece);
		piece = new Chessman('A', 3, 9);
		piece_list.put(22,piece);
		piece = new Chessman('A', 5, 9);
		piece_list.put(23,piece);
		
		// set Elephant:
		piece = new Chessman('e', 2, 0);
		piece_list.put(4,piece);
		piece = new Chessman('e', 6, 0);
		piece_list.put(5,piece);
		piece = new Chessman('E', 2, 9);
		piece_list.put(24,piece);
		piece = new Chessman('E', 6, 9);
		piece_list.put(25,piece);
		
		// set Horse:
		piece = new Chessman('h', 1, 0);
		piece_list.put(6,piece);
		piece = new Chessman('h', 7, 0);
		piece_list.put(7,piece);
		piece = new Chessman('H', 1, 9);
		piece_list.put(26,piece);
		piece = new Chessman('H', 7, 9);
		piece_list.put(27,piece);
		
		// set Chariot:
		piece = new Chessman('r', 0, 0);
		piece_list.put(8,piece);
		piece = new Chessman('r', 8, 0);
		piece_list.put(9,piece);
		piece = new Chessman('R', 0, 9);
		piece_list.put(28,piece);
		piece = new Chessman('R', 8, 9);
		piece_list.put(29,piece);
		
		// set Cannon:
		piece = new Chessman('c', 1, 2);
		piece_list.put(10,piece);
		piece = new Chessman('c', 7, 2);
		piece_list.put(11,piece);
		piece = new Chessman('C', 1, 7);
		piece_list.put(30,piece);
		piece = new Chessman('C', 7, 7);
		piece_list.put(31,piece);
		
		// set Soldier:
		piece = new Chessman('s', 0, 3);
		piece_list.put(12,piece);
		piece = new Chessman('s', 2, 3);
		piece_list.put(13,piece);
		piece = new Chessman('s', 4, 3);
		piece_list.put(14,piece);
		piece = new Chessman('s', 6, 3);
		piece_list.put(15,piece);
		piece = new Chessman('s', 8, 3);
		piece_list.put(16,piece);
		piece = new Chessman('S', 0, 6);
		piece_list.put(32,piece);
		piece = new Chessman('S', 2, 6);
		piece_list.put(33,piece);
		piece = new Chessman('S', 4, 6);
		piece_list.put(34,piece);
		piece = new Chessman('S', 6, 6);
		piece_list.put(35,piece);
		piece = new Chessman('S', 8, 6);
		piece_list.put(36,piece);
	}

	public static void printChessBoard(){
		System.out.println(" 0 1 2 3 4 5 6 7 8");
		for(int j=0; j<10; ++j){
			System.out.print("\033[0m"+j);
			for(int i=0; i<9; ++i){
				switch(chess_board[j][i]){
					case 'n':
						System.out.print("\033[34m口");
						break;
					case 'g':
						System.out.print("\033[32m將");
						break;
					case 'G':
						System.out.print("\033[31m帥");
						break;
					case 'a':
						System.out.print("\033[32m士");
						break;
					case 'A':
						System.out.print("\033[31m仕");
						break;
					case 'e':
						System.out.print("\033[32m象");
						break;
					case 'E':
						System.out.print("\033[31m相");
						break;
					case 'h':
						System.out.print("\033[32m馬");
						break;
					case 'H':
						System.out.print("\033[31m傌");
						break;
					case 'r':
						System.out.print("\033[32m車");
						break;
					case 'R':
						System.out.print("\033[31m俥");
						break;
					case 'c':
						System.out.print("\033[32m砲");
						break;
					case 'C':
						System.out.print("\033[31m炮");
						break;
					case 's':
						System.out.print("\033[32m卒");
						break;
					case 'S':
						System.out.print("\033[31m兵");
						break;
					case 'b':
						System.out.print("\033[32m卒");
						break;
					case 'B':
						System.out.print("\033[31m兵");
				}
			}
			System.out.println("\033[0m");
		}
	}
}
