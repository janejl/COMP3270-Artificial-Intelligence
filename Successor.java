import java.util.ArrayList;
import java.util.Arrays;

/*
 * return arraylist of nodes as successors
 */
public class Successor{
	private ArrayList<ChessNode> successors;
	
	public Successor(){
		successors = new ArrayList<ChessNode>();
	}
	
	public ArrayList<ChessNode> getSuccessor(ChessNode current){
		ChessNode current_node = current;
		char[][] current_state = current_node.getChessBoard();
		
		int check_result = checkState(current_state);
		ChessNode child_node;
		int[] origin_state = current_state;
		int[] temp_state;
		
		// check_result represents the empty block in puzzle or game over.
		switch(check_result){
			// Game over
			case -1:
				break;
				
			// Empty block is on center:
			case 4: // 1, 3, 5, 7 can move
				temp_state = swap(origin_state, 4, 1);
				child_node = new NodeHeuristic(temp_state, current_node, calculateHeuristic(temp_state));
				successors.add(child_node);
				temp_state = swap(origin_state, 4, 3);
				child_node = new NodeHeuristic(temp_state, current_node, calculateHeuristic(temp_state));
				successors.add(child_node);
				temp_state = swap(origin_state, 4, 5);
				child_node = new NodeHeuristic(temp_state, current_node, calculateHeuristic(temp_state));
				successors.add(child_node);
				temp_state = swap(origin_state, 4, 7);
				child_node = new NodeHeuristic(temp_state, current_node, calculateHeuristic(temp_state));
				successors.add(child_node);
				break;
		}
		
		return successors;
	}
	

	
/*
	// Return: the position of blank cell OR game over
	public int checkState([] current_state){
		if(Arrays.equals(current_state, goal_state)){
			System.out.println("Game Over");
			return -1; // game over
		}
		
		int position = 0;
		while(position<9){
			if(current_state[position] == 0) break;
			++position;
		}
		
		return position;
	}
	
	public int calculateHeuristic(int[] state){
		int h = 0;
		if(isManhattan){
			// # of tiles from desired location of each tile, horizontally & vertically, not diagonally
			// map the state with a 2D array
			int[][] newArray = new int[3][3];
			for(int y=0; y<3; ++y){
				for(int x=0; x<3; ++x){
					newArray[y][x] = state[3*y + x];
				}
			}
			
			int x1, x2, y1, y2, index, item;
			index = 0;
			for(y1=0; y1<3; ++y1){
				for(x1=0; x1<3; ++x1){
					item = newArray[y1][x1];
					if(item != 0){
						for(int i=0; i<9; ++i){
							if(item == goal_state[i]){
								index = i;
								break;
							}
						}
						x2 = index % 3;
						y2 = index / 3;
						h = h + Math.abs(x1 - x2) + Math.abs(y1 - y2);
					}
				}
			}
		}else{
			// # of misplaced tiles: the empty tile is not included!
			for(int i=0; i<9; ++i){
				if(state[i] != 0 && state[i] != goal_state[i]){
					++h;
				}
			}
		}
		return h;
	}

	public int[] swap(int[] array, int empty_pos, int target_pos){
		int[] temp_array = new int[9];
		System.arraycopy(array, 0, temp_array, 0, 9); // copy array
		temp_array[empty_pos]  = temp_array[target_pos];
		temp_array[target_pos] = 0;
		
		return temp_array;
	}

	public void printSuccessors(){
		for(int i=0; i<successors.size(); ++i){
			System.out.println("child: " + Arrays.toString(successors.get(i).getState()) + " H = " + successors.get(i).getH() + " F = " + successors.get(i).getF());
		}
	}
*/	

}
