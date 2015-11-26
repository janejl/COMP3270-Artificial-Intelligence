public class Chessman {
	// Machine use small letter, human use capital letter.
	private char type;
	// current position
	private int x;
	private int y;
	private char[][] chess_board;
	
	public Chessman(char t, int x, int y){
		type = t;
		this.x = x;
		this.y = y;
	}
	
	// upgrade to big soldier after crossing the river
	public void upgradeSoldier(){
		if(type == 's')
			type = 'b';
		else if(type == 'S')
			type = 'B';
	}
	public int getX(){
		return x;
	}
	public int getY(){
		return y;
	}
	
	// return: s - succeed, f - fail, e - eat
	public char move(char[][] board, int new_x, int new_y){
		chess_board = board;
		char result = 'f';
		switch(type){
			case 'g': // General
				result = moveMyGeneral(new_x, new_y);
				break;
			case 'G':
				result = moveUrGeneral(new_x, new_y);
				break;
			case 'a': // Advisor
				result = moveMyAdvisor(new_x, new_y);
				break;
			case 'A':
				result = moveUrAdvisor(new_x, new_y);
				break;
			case 'e': // Elephant
				result = moveMyElephant(new_x, new_y);
				break;
			case 'E':
				result = moveUrElephant(new_x, new_y);
				break;
			case 'h': // Horse
				result = moveMyHorse(new_x, new_y);
				break;
			case 'H':
				result = moveUrHorse(new_x, new_y);
				break;
			case 'r': // Chariot
				break;
			case 'R':
				break;
			case 'c': // Cannon
				break;
			case 'C':
				break;
			case 's': // Soldier
				break;
			case 'S':
				break;
			case 'b': // Soldier across the river
				break;
			case 'B':
		}
		return result;
	}

	private char moveMyGeneral(int nx, int ny){
		// Rule: 1. within palace; 2. one square every step.
		if(nx < 3 || nx > 5 || ny < 0 || ny > 2){
			return 'f';
		}
		if((Math.abs(nx-x) == 1 && (ny-y) == 0) || (Math.abs(ny-y) == 1 && (nx-x) == 0)){
			char temp = chess_board[ny][nx];
			if(temp == 'n'){
				chess_board[ny][nx] = 'g';
				chess_board[y][x] = 'n';
				return 's';
			}else if(Character.toLowerCase(temp) != temp){
				chess_board[ny][nx] = 'g';
				chess_board[y][x] = 'n';
				return 'e';
			}else{
				return 'f';
			}
		}else{
			return 'f';
		}
	}
	private char moveUrGeneral(int nx, int ny){
		if(nx < 3 || nx > 5 || ny < 7 || ny > 9){
			return 'f';
		}
		if((Math.abs(nx-x) == 1 && (ny-y) == 0) || (Math.abs(ny-y) == 1 && (nx-x) == 0)){
			char temp = chess_board[ny][nx];
			if(temp == 'n'){
				chess_board[ny][nx] = 'G';
				chess_board[y][x] = 'n';
				return 's';
			}else if(Character.toUpperCase(temp) != temp){
				chess_board[ny][nx] = 'G';
				chess_board[y][x] = 'n';
				return 'e';
			}else{
				return 'f';
			}
		}else{
			return 'f';
		}
	}
	private char moveMyAdvisor(int nx, int ny){
		// Rule: 1. within palace; 2. one diagonal one step.
		if(nx < 3 || nx > 5 || ny < 0 || ny > 2){
			return 'f';
		}
		if(Math.abs(nx-x) == 1 && Math.abs(ny-y) == 1){
			char temp = chess_board[ny][nx];
			if(temp == 'n'){
				chess_board[ny][nx] = 'a';
				chess_board[y][x] = 'n';
				return 's';
			}else if(Character.toLowerCase(temp) != temp){
				chess_board[ny][nx] = 'a';
				chess_board[y][x] = 'n';
				return 'e';
			}else{
				return 'f';
			}
		}else{
			return 'f';
		}
	}
	private char moveUrAdvisor(int nx, int ny){
		if(nx < 3 || nx > 5 || ny < 7 || ny > 9){
			return 'f';
		}
		if(Math.abs(nx-x) == 1 && Math.abs(ny-y) == 1){
			char temp = chess_board[ny][nx];
			if(temp == 'n'){
				chess_board[ny][nx] = 'A';
				chess_board[y][x] = 'n';
				return 's';
			}else if(Character.toUpperCase(temp) != temp){
				chess_board[ny][nx] = 'A';
				chess_board[y][x] = 'n';
				return 'e';
			}else{
				return 'f';
			}
		}else{
			return 'f';
		}
	}
	private char moveMyElephant(int nx, int ny){
		// Rule: 1. cannot across river; 2. two diagonals one step.
		if(nx < 0 || nx > 8 || ny < 0 || ny > 4){
			return 'f';
		}
		if(Math.abs(nx-x) == 2 && Math.abs(ny-y) == 2){
			char temp = chess_board[ny][nx];
			if(temp == 'n'){
				chess_board[ny][nx] = 'e';
				chess_board[y][x] = 'n';
				return 's';
			}else if(Character.toLowerCase(temp) != temp){
				chess_board[ny][nx] = 'e';
				chess_board[y][x] = 'n';
				return 'e';
			}else{
				return 'f';
			}
		}else{
			return 'f';
		}
	}
	private char moveUrElephant(int nx, int ny){
		if(nx < 0 || nx > 8 || ny < 5 || ny > 9){
			return 'f';
		}
		if(Math.abs(nx-x) == 2 && Math.abs(ny-y) == 2){
			char temp = chess_board[ny][nx];
			if(temp == 'n'){
				chess_board[ny][nx] = 'E';
				chess_board[y][x] = 'n';
				return 's';
			}else if(Character.toUpperCase(temp) != temp){
				chess_board[ny][nx] = 'E';
				chess_board[y][x] = 'n';
				return 'e';
			}else{
				return 'f';
			}
		}else{
			return 'f';
		}
	}
	private char moveMyHorse(int nx, int ny){
		// Rule: 1. two skew diagonal one step; 2. no obstacle.
		if(nx <0 || nx > 8 || ny < 0 || ny > 9){
			return 'f';
		}
		
		char temp = chess_board[ny][nx];
		char ob_check;
		boolean noObstacle = false;
		
		if(Math.abs(nx-x) == 2 && Math.abs(ny-y) == 1){
			// horizontal jump
			if(nx > x){
				ob_check = chess_board[y][x+1];
				if(ob_check != 'n'){
					return 'f'; // obstacle
				}else{
					noObstacle = true;
				}
			}else{
				ob_check = chess_board[y][x-1];
				if(ob_check != 'n'){
					return 'f'; // obstacle
				}else{
					noObstacle = true;
				}
			}
			
			if(temp == 'n' && noObstacle){
				chess_board[ny][nx] = 'h';
				chess_board[y][x] = 'n';
				return 's';
			}else if(Character.toLowerCase(temp) != temp && noObstacle){
				chess_board[ny][nx] = 'h';
				chess_board[y][x] = 'n';
				return 'e';
			}else{
				return 'f';
			}
		}else if((Math.abs(ny-y) == 2 && Math.abs(nx-x) == 1)){
			// vertical jump
			if(ny > y){
				ob_check = chess_board[y+1][x];
				if(ob_check != 'n'){
					return 'f'; // obstacle
				}else{
					noObstacle = true;
				}
			}else{
				ob_check = chess_board[y-1][x];
				if(ob_check != 'n'){
					return 'f'; // obstacle
				}else{
					noObstacle = true;
				}
			}
			
			if(temp == 'n' && noObstacle){
				chess_board[ny][nx] = 'h';
				chess_board[y][x] = 'n';
				return 's';
			}else if(Character.toLowerCase(temp) != temp && noObstacle){
				chess_board[ny][nx] = 'h';
				chess_board[y][x] = 'n';
				return 'e';
			}else{
				return 'f';
			}
		}else{
			return 'f';
		}
	}
	private char moveUrHorse(int nx, int ny){
		// Rule: 1. two skew diagonal one step; 2. no obstacle.
		if(nx <0 || nx > 8 || ny < 0 || ny > 9){
			return 'f';
		}
		
		char temp = chess_board[ny][nx];
		char ob_check;
		boolean noObstacle = false;
		
		if(Math.abs(nx-x) == 2 && Math.abs(ny-y) == 1){
			// horizontal jump
			if(nx > x){
				ob_check = chess_board[y][x+1];
				if(ob_check != 'n'){
					return 'f'; // obstacle
				}else{
					noObstacle = true;
				}
			}else{
				ob_check = chess_board[y][x-1];
				if(ob_check != 'n'){
					return 'f'; // obstacle
				}else{
					noObstacle = true;
				}
			}
			
			if(temp == 'n' && noObstacle){
				chess_board[ny][nx] = 'H';
				chess_board[y][x] = 'n';
				return 's';
			}else if(Character.toLowerCase(temp) != temp && noObstacle){
				chess_board[ny][nx] = 'H';
				chess_board[y][x] = 'n';
				return 'e';
			}else{
				return 'f';
			}
		}else if((Math.abs(ny-y) == 2 && Math.abs(nx-x) == 1)){
			// vertical jump
			if(ny > y){
				ob_check = chess_board[y+1][x];
				if(ob_check != 'n'){
					return 'f'; // obstacle
				}else{
					noObstacle = true;
				}
			}else{
				ob_check = chess_board[y-1][x];
				if(ob_check != 'n'){
					return 'f'; // obstacle
				}else{
					noObstacle = true;
				}
			}
			
			if(temp == 'n' && noObstacle){
				chess_board[ny][nx] = 'H';
				chess_board[y][x] = 'n';
				return 's';
			}else if(Character.toLowerCase(temp) != temp && noObstacle){
				chess_board[ny][nx] = 'H';
				chess_board[y][x] = 'n';
				return 'e';
			}else{
				return 'f';
			}
		}else{
			return 'f';
		}
	}


}
