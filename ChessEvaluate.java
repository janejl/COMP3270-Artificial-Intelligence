import java.util.HashMap;


public class ChessEvaluate {
	// General, Advisor, Elephant, Horse, Chariot, Cannon, Soldier, Big Soldier
	private final int[] pieceValue = new int[]{1000, 15, 18, 50, 80, 50, 5, 10};
	
	// Estimation of the current state of the game -> Search through moves and pick the “best” one.
	// given from[] and to[], evaluate the step's value and assign to Chessman.value
	public int giveEvaluation(char[][] chess_board, HashMap<Integer, Chessman> piece_list, ChessNode node){
		int value = 0;
		// machine wants largest, user gives least value.
		int[] to = node.getDest();
		char dest = chess_board[to[1]][to[0]];
		boolean eat = (dest == 'n')? false:true;
		
		// Intrinsic values
		for(Chessman piece:piece_list.values()){
			if(eat && (dest == piece.getType())){
				eat = false;
				continue;
			}
			switch(piece.getType()){
				case 'g':
					value += pieceValue[0];
					break;
				case 'G':
					value -= pieceValue[0];
					break;
				case 'a':
					value += pieceValue[1];
					break;
				case 'A':
					value -= pieceValue[1];
					break;
				case 'e':
					value += pieceValue[2];
					break;
				case 'E':
					value -= pieceValue[2];
					break;
				case 'h':
					value += pieceValue[3];
					break;
				case 'H':
					value -= pieceValue[3];
					break;
				case 'r':
					value += pieceValue[4];
					break;
				case 'R':
					value -= pieceValue[4];
					break;
				case 'c':
					value += pieceValue[5];
					break;
				case 'C':
					value -= pieceValue[5];
					break;
				case 's':
					value += pieceValue[6];
					break;
				case 'S':
					value -= pieceValue[6];
					break;
				case 'b':
					value += pieceValue[7];
					break;
				case 'B':
					value -= pieceValue[7];
			}	
		}
		return value;
	}
}
