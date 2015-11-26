import java.util.ArrayList;

public class ChineseChess {

	private char[][] chess_board;
	private ArrayList<ChessNode> history;
	private boolean isMyTurn; // Machine's turn
	private ArrayList<Chessman> myChessmen;
	private ArrayList<Chessman> userChessmen;
	
	/* 
	 * Human will take the first turn in default.
	 */
	public ChineseChess(){
		initChessBoard();
		isMyTurn = false;
		history = new ArrayList<ChessNode>();
	}
	
	public static void main(String[] args) {
        System.out.println("=========================== Game Start ===========================");
        
        ChineseChess game = new ChineseChess();
        game.printChessBoard();
        
        System.out.println("=========================== Game End ===========================");
	}
	
	// Set up a chess board
	private void initChessBoard(){
		chess_board = new char[10][9];
		myChessmen = new ArrayList<Chessman>();
		userChessmen = new ArrayList<Chessman>();
		Chessman piece;
		
		for(int j=0; j<10; ++j){
			for(int i=0; i<9; ++i){
				chess_board[j][i] = 'n';
			}
		}
		
		// set General:
		chess_board[0][4] = 'g';
		piece = new Chessman('g', 4, 0);
		myChessmen.add(piece);
		chess_board[9][4] = 'G';
		piece = new Chessman('G', 4, 9);
		userChessmen.add(piece);
		
		// set Advisor:
		chess_board[0][3] = 'a';
		piece = new Chessman('a', 3, 0);
		myChessmen.add(piece);
		chess_board[0][5] = 'a';
		piece = new Chessman('a', 5, 0);
		myChessmen.add(piece);
		chess_board[9][3] = 'A';
		piece = new Chessman('A', 3, 9);
		userChessmen.add(piece);
		chess_board[9][5] = 'A';
		piece = new Chessman('A', 5, 9);
		userChessmen.add(piece);
		
		// set Elephant:
		chess_board[0][2] = 'e';
		piece = new Chessman('e', 2, 0);
		myChessmen.add(piece);
		chess_board[0][6] = 'e';
		piece = new Chessman('e', 6, 0);
		myChessmen.add(piece);
		chess_board[9][2] = 'E';
		piece = new Chessman('E', 2, 9);
		userChessmen.add(piece);
		chess_board[9][6] = 'E';
		piece = new Chessman('E', 6, 9);
		userChessmen.add(piece);
		
		// set Horse:
		chess_board[0][1] = 'h';
		piece = new Chessman('h', 1, 0);
		myChessmen.add(piece);
		chess_board[0][7] = 'h';
		piece = new Chessman('h', 7, 0);
		myChessmen.add(piece);
		chess_board[9][1] = 'H';
		piece = new Chessman('H', 1, 9);
		userChessmen.add(piece);
		chess_board[9][7] = 'H';
		piece = new Chessman('H', 7, 9);
		userChessmen.add(piece);
		
		// set Chariot:
		chess_board[0][0] = 'r';
		piece = new Chessman('r', 0, 0);
		myChessmen.add(piece);
		chess_board[0][8] = 'r';
		piece = new Chessman('r', 8, 0);
		myChessmen.add(piece);
		chess_board[9][0] = 'R';
		piece = new Chessman('R', 0, 9);
		userChessmen.add(piece);
		chess_board[9][8] = 'R';
		piece = new Chessman('R', 8, 9);
		userChessmen.add(piece);
		
		// set Cannon:
		chess_board[2][1] = 'c';
		piece = new Chessman('c', 1, 2);
		myChessmen.add(piece);
		chess_board[2][7] = 'c';
		piece = new Chessman('c', 7, 2);
		myChessmen.add(piece);
		chess_board[7][1] = 'C';
		piece = new Chessman('C', 1, 7);
		userChessmen.add(piece);
		chess_board[7][7] = 'C';
		piece = new Chessman('C', 7, 7);
		userChessmen.add(piece);
		
		// set Soldier:
		chess_board[3][0] = 's';
		piece = new Chessman('s', 0, 3);
		myChessmen.add(piece);
		chess_board[3][2] = 's';
		piece = new Chessman('s', 2, 3);
		userChessmen.add(piece);
		chess_board[3][4] = 's';
		piece = new Chessman('s', 4, 3);
		myChessmen.add(piece);
		chess_board[3][6] = 's';
		piece = new Chessman('s', 6, 3);
		userChessmen.add(piece);
		chess_board[3][8] = 's';
		piece = new Chessman('s', 8, 3);
		myChessmen.add(piece);
		chess_board[6][0] = 'S';
		piece = new Chessman('S', 0, 6);
		userChessmen.add(piece);
		chess_board[6][2] = 'S';
		piece = new Chessman('S', 2, 6);
		myChessmen.add(piece);
		chess_board[6][4] = 'S';
		piece = new Chessman('S', 4, 6);
		userChessmen.add(piece);
		chess_board[6][6] = 'S';
		piece = new Chessman('S', 6, 6);
		myChessmen.add(piece);
		chess_board[6][8] = 'S';
		piece = new Chessman('S', 8, 6);
		userChessmen.add(piece);
	}
	
	public void printChessBoard(){
		for(int j=0; j<10; ++j){
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
			System.out.println("\033[32m");
		}
	}
}
