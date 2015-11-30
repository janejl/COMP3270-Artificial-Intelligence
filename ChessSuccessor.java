import java.util.ArrayList;
import java.util.HashMap;

public class ChessSuccessor{
	private ArrayList<ChessNode> successors;
	
	public ChessSuccessor(){
		successors = new ArrayList<ChessNode>();
	}
	
	public ArrayList<ChessNode> getSuccessor(char[][] chess_board, int[][] chess_board_key, HashMap<Integer, Chessman> piece_list, ChessNode current){
		// from current node, get the list of available pieces. 
		// feed piece to moveXXX one by one and get legal successors.
		// return successor to alpha beta pruning.
		successors = new ArrayList<ChessNode>();
		//System.out.println("getSuccessor: current: "+current);
		
		char check_state = current.checkStatus(piece_list);
		//System.out.println("getSuccessor: check_state: " + check_state);
		if(check_state != 'p'){
			return successors;
		}
		
		for(Chessman piece : piece_list.values()){
			generateSuccessors(current, piece, chess_board, chess_board_key, successors);
		}

		return successors;
	}
	
	
	// ===== Rules =====
	// Generate valid moves (will pass to giveEvaluation() later on)	
	public void generateSuccessors(ChessNode parent, Chessman piece, char[][] chess_board, int[][] chess_board_key, ArrayList<ChessNode> successors){
	 	int x = piece.getX();
	 	int y = piece.getY();
	 	int nx, ny;
	 	int from[] = {x,y};
	 	ChessNode next_node;
	 	ChessRule rule = new ChessRule();
	 	char result = 'f';
	 	
		switch(piece.getType()){
			case 'g': // General
			 	// Possible next steps: 4 around + 3 fly and eat
			 	int[][] g_XY = {{x-1,y}, {x,y-1}, {x+1,y}, {x,y+1}, {x,y+7}, {x,y+8}, {x,y+9}};
			 	
			 	for(int[] nxny : g_XY){
			 		nx = nxny[0];
			 		ny = nxny[1];
			 		result = rule.moveMyGeneral(x, y, nx, ny, chess_board);
			 		if(result == 'f'){
			 			continue;
			 		}else{
 			 			next_node = new ChessNode(chess_board_key[ny][nx], from, nxny, true, parent);
 			 			successors.add(next_node); 
			 		}
			 	}
				break;
			case 'G':
				// Possible next steps: 4 around + 3 fly and eat
				int[][] G_XY = {{x-1,y}, {x,y-1}, {x+1,y}, {x,y+1}, {x,y-7}, {x,y-8}, {x,y-9}};
			 	
			 	for(int[] nxny : G_XY){
			 		nx = nxny[0];
			 		ny = nxny[1];
			 		result = rule.moveUrGeneral(x, y, nx, ny, chess_board);
			 		if(result == 'f'){
			 			continue;
			 		}else{
 			 			next_node = new ChessNode(chess_board_key[ny][nx], from, nxny, false, parent);
 			 			successors.add(next_node);
			 		}
			 	}
				break;
			case 'a': // Advisor
				// Possible next steps: 4 around
				int[][] a_XY = {{x-1,y-1}, {x+1,y+1}, {x+1,y-1}, {x-1,y+1}};
			 	
			 	for(int[] nxny : a_XY){
			 		nx = nxny[0];
			 		ny = nxny[1];
			 		result = rule.moveMyAdvisor(x, y, nx, ny, chess_board);
			 		if(result == 'f'){
			 			continue;
			 		}else{
 			 			next_node = new ChessNode(chess_board_key[ny][nx], from, nxny, true, parent);
 			 			successors.add(next_node);
			 		}
			 	}
				break;
			case 'A':
				// Possible next steps: 4 around
				int[][] A_XY = {{x-1,y-1}, {x+1,y+1}, {x+1,y-1}, {x-1,y+1}};
			 	
			 	for(int[] nxny : A_XY){
			 		nx = nxny[0];
			 		ny = nxny[1];
			 		result = rule.moveUrAdvisor(x, y, nx, ny, chess_board);
			 		if(result == 'f'){
			 			continue;
			 		}else{
 			 			next_node = new ChessNode(chess_board_key[ny][nx], from, nxny, false, parent);
 			 			successors.add(next_node);
			 		}
			 	}
				break;
			case 'e': // Elephant
				// Possible next steps: 4 around
				int[][] e_XY = {{x-2,y-2}, {x+2,y+2}, {x+2,y-2}, {x-2,y+2}};
			 	
			 	for(int[] nxny : e_XY){
			 		nx = nxny[0];
			 		ny = nxny[1];
			 		result = rule.moveMyElephant(x, y, nx, ny, chess_board);
			 		if(result == 'f'){
			 			continue;
			 		}else{
 			 			next_node = new ChessNode(chess_board_key[ny][nx], from, nxny, true, parent);
 			 			successors.add(next_node);
			 		}
			 	}
				break;
			case 'E':
				// Possible next steps: 4 around
				int[][] E_XY = {{x-2,y-2}, {x+2,y+2}, {x+2,y-2}, {x-2,y+2}};
			 	
			 	for(int[] nxny : E_XY){
			 		nx = nxny[0];
			 		ny = nxny[1];
			 		result = rule.moveUrElephant(x, y, nx, ny, chess_board);
			 		if(result == 'f'){
			 			continue;
			 		}else{
 			 			next_node = new ChessNode(chess_board_key[ny][nx], from, nxny, false, parent);
 			 			successors.add(next_node);
			 		}
			 	}
				break;
			case 'h': // Horse
				// Possible next steps: 8 around
				int[][] h_XY = {{x-1,y-2}, {x+1,y-2}, {x-2,y-1}, {x+2,y-1}, {x-1,y+2}, {x+1,y+2}, {x-2,y+1}, {x+2,y+1}};
			 	
			 	for(int[] nxny : h_XY){
			 		nx = nxny[0];
			 		ny = nxny[1];
			 		result = rule.moveMyHorse(x, y, nx, ny, chess_board);
			 		if(result == 'f'){
			 			continue;
			 		}else{
 			 			next_node = new ChessNode(chess_board_key[ny][nx], from, nxny, true, parent);
 			 			successors.add(next_node);
			 		}
			 	}
				break;
			case 'H':
				// Possible next steps: 8 around
				int[][] H_XY = {{x-1,y-2}, {x+1,y-2}, {x-2,y-1}, {x+2,y-1}, {x-1,y+2}, {x+1,y+2}, {x-2,y+1}, {x+2,y+1}};
			 	
			 	for(int[] nxny : H_XY){
			 		nx = nxny[0];
			 		ny = nxny[1];
			 		result = rule.moveUrHorse(x, y, nx, ny, chess_board);
			 		if(result == 'f'){
			 			continue;
			 		}else{
 			 			next_node = new ChessNode(chess_board_key[ny][nx], from, nxny, false, parent);
 			 			successors.add(next_node);
			 		}
			 	}
				break;
			case 'r': // Chariot
			 	// Possible next steps: 4 directions
			 	int[][] r_XY = {{x+1,y}, {x+2,y}, {x+3,y}, {x+4,y}, {x+5,y}, {x+6,y}, {x+7,y}, {x+8,y}, 
			 				  {x-1,y}, {x-2,y}, {x-3,y}, {x-4,y}, {x-5,y}, {x-6,y}, {x-7,y}, {x-8,y}, 
			 				  {x,y+1}, {x,y+2}, {x,y+3}, {x,y+4}, {x,y+5}, {x,y+6}, {x,y+7}, {x,y+8}, {x,y+9},
			 				  {x,y-1}, {x,y-2}, {x,y-3}, {x,y-4}, {x,y-5}, {x,y-6}, {x,y-7}, {x,y-8}, {x,y-9}};
			 	for(int[] nxny : r_XY){
			 		nx = nxny[0];
			 		ny = nxny[1];
			 		result = rule.moveMyChariot(x, y, nx, ny, chess_board);
			 		if(result == 'f'){
			 			continue;
			 		}else{
 			 			next_node = new ChessNode(chess_board_key[ny][nx], from, nxny, true, parent);
 			 			successors.add(next_node);
			 		}
			 	}
				break;
			case 'R':
				// Possible next steps: 4 directions
			 	int[][] R_XY = {{x+1,y}, {x+2,y}, {x+3,y}, {x+4,y}, {x+5,y}, {x+6,y}, {x+7,y}, {x+8,y}, 
			 				  {x-1,y}, {x-2,y}, {x-3,y}, {x-4,y}, {x-5,y}, {x-6,y}, {x-7,y}, {x-8,y}, 
			 				  {x,y+1}, {x,y+2}, {x,y+3}, {x,y+4}, {x,y+5}, {x,y+6}, {x,y+7}, {x,y+8}, {x,y+9},
			 				  {x,y-1}, {x,y-2}, {x,y-3}, {x,y-4}, {x,y-5}, {x,y-6}, {x,y-7}, {x,y-8}, {x,y-9}};
			 	for(int[] nxny : R_XY){
			 		nx = nxny[0];
			 		ny = nxny[1];
			 		result = rule.moveUrChariot(x, y, nx, ny, chess_board);
			 		if(result == 'f'){
			 			continue;
			 		}else{
 			 			next_node = new ChessNode(chess_board_key[ny][nx], from, nxny, false, parent);
 			 			successors.add(next_node);
			 		}
			 	}
				break;
			case 'c': // Cannon
			 	// Possible next steps: 4 directions
			 	int[][] c_XY = {{x+1,y}, {x+2,y}, {x+3,y}, {x+4,y}, {x+5,y}, {x+6,y}, {x+7,y}, {x+8,y}, 
			 				  {x-1,y}, {x-2,y}, {x-3,y}, {x-4,y}, {x-5,y}, {x-6,y}, {x-7,y}, {x-8,y}, 
			 				  {x,y+1}, {x,y+2}, {x,y+3}, {x,y+4}, {x,y+5}, {x,y+6}, {x,y+7}, {x,y+8}, {x,y+9},
			 				  {x,y-1}, {x,y-2}, {x,y-3}, {x,y-4}, {x,y-5}, {x,y-6}, {x,y-7}, {x,y-8}, {x,y-9}};
			 	for(int[] nxny : c_XY){
			 		nx = nxny[0];
			 		ny = nxny[1];
			 		result = rule.moveMyCannon(x, y, nx, ny, chess_board);
			 		if(result == 'f'){
			 			continue;
			 		}else{
 			 			next_node = new ChessNode(chess_board_key[ny][nx], from, nxny, true, parent);
 			 			successors.add(next_node);
			 		}
			 	}
			 	break;
			case 'C':
				// Possible next steps: 4 directions
			 	int[][] C_XY = {{x+1,y}, {x+2,y}, {x+3,y}, {x+4,y}, {x+5,y}, {x+6,y}, {x+7,y}, {x+8,y}, 
			 				  {x-1,y}, {x-2,y}, {x-3,y}, {x-4,y}, {x-5,y}, {x-6,y}, {x-7,y}, {x-8,y}, 
			 				  {x,y+1}, {x,y+2}, {x,y+3}, {x,y+4}, {x,y+5}, {x,y+6}, {x,y+7}, {x,y+8}, {x,y+9},
			 				  {x,y-1}, {x,y-2}, {x,y-3}, {x,y-4}, {x,y-5}, {x,y-6}, {x,y-7}, {x,y-8}, {x,y-9}};
			 	for(int[] nxny : C_XY){
			 		nx = nxny[0];
			 		ny = nxny[1];
			 		result = rule.moveUrCannon(x, y, nx, ny, chess_board);
			 		if(result == 'f'){
			 			continue;
			 		}else{
 			 			next_node = new ChessNode(chess_board_key[ny][nx], from, nxny, false, parent);
 			 			successors.add(next_node);
			 		}
			 	}
			 	break;
			case 's': // Soldier
			 	// Possible next steps: 1 forward
			 	int[][] s_XY = {{x,y+1}};
			 	for(int[] nxny : s_XY){
			 		nx = nxny[0];
			 		ny = nxny[1];
			 		result = rule.moveMySoldier(x, y, nx, ny, chess_board);
			 		if(result == 'f'){
			 			continue;
			 		}else{
 			 			next_node = new ChessNode(chess_board_key[ny][nx], from, nxny, true, parent);
 			 			successors.add(next_node);
			 		}
			 	}
				break;
			case 'S':
				// Possible next steps: 1 forward
			 	int[][] S_XY = {{x,y-1}};
			 	for(int[] nxny : S_XY){
			 		nx = nxny[0];
			 		ny = nxny[1];
			 		result = rule.moveUrSoldier(x, y, nx, ny, chess_board);
			 		if(result == 'f'){
			 			continue;
			 		}else{
 			 			next_node = new ChessNode(chess_board_key[ny][nx], from, nxny, false, parent);
 			 			successors.add(next_node);
			 		}
			 	}
				break;
			case 'b': // Soldier across the river
			 	// Possible next steps: 3 around
			 	int[][] b_XY = {{x,y+1}, {x-1,y}, {x+1,y}};
			 	for(int[] nxny : b_XY){
			 		nx = nxny[0];
			 		ny = nxny[1];
			 		result = rule.moveMyBigSoldier(x, y, nx, ny, chess_board);
			 		if(result == 'f'){
			 			continue;
			 		}else{
 			 			next_node = new ChessNode(chess_board_key[ny][nx], from, nxny, true, parent);
 			 			successors.add(next_node);
			 		}
			 	}
				break;
			case 'B':
				// Possible next steps: 3 around
			 	int[][] B_XY = {{x,y-1}, {x-1,y}, {x+1,y}};
			 	for(int[] nxny : B_XY){
			 		nx = nxny[0];
			 		ny = nxny[1];
			 		result = rule.moveUrBigSoldier(x, y, nx, ny, chess_board);
			 		if(result == 'f'){
			 			continue;
			 		}else{
 			 			next_node = new ChessNode(chess_board_key[ny][nx], from, nxny, false, parent);
 			 			successors.add(next_node);
			 		}
			 	}
		}
	}

}
