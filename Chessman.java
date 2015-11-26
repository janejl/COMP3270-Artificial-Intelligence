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
				result = moveMyChariot(new_x, new_y);
				break;
			case 'R':
				result = moveUrChariot(new_x, new_y);
				break;
			case 'c': // Cannon
				result = moveMyCannon(new_x, new_y);
				break;
			case 'C':
				result = moveUrCannon(new_x, new_y);
				break;
			case 's': // Soldier
				result = moveMySoldier(new_x, new_y);
				break;
			case 'S':
				result = moveUrSoldier(new_x, new_y);
				break;
			case 'b': // Soldier across the river
				result = moveMyBigSoldier(new_x, new_y);
				break;
			case 'B':
				result = moveUrBigSoldier(new_x, new_y);
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
			}else if(Character.toUpperCase(temp) != temp && noObstacle){
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
			}else if(Character.toUpperCase(temp) != temp && noObstacle){
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
	private char moveMyChariot(int nx, int ny){
		// Rule: 1. no obstacles between source and destination.
		if(nx <0 || nx > 8 || ny < 0 || ny > 9){
			return 'f';
		}
		
		char dest = chess_board[ny][nx];
		
		if(Math.abs(nx-x) > 0 && ny == y){
			if(nx > x){
				for(int i=x+1; i<nx; ++i){
					if(chess_board[y][i] != 'n'){
						return 'f'; // obstacle
					}
				}
			}else if(x > nx){
				for(int i=nx+1; i<x; ++i){
					if(chess_board[y][i] != 'n'){
						return 'f'; // obstacle
					}
				}
			}
			if(dest == 'n'){
				chess_board[ny][nx] = 'r';
				chess_board[y][x] = 'n';
				return 's';
			}else if(Character.toLowerCase(dest) != dest){
				chess_board[ny][nx] = 'r';
				chess_board[y][x] = 'n';
				return 'e';
			}else{
				return 'f';
			}
		}else if(Math.abs(ny-y) > 0 && nx == x){
			if(ny > y){
				for(int i=y+1; i<ny; ++i){
					if(chess_board[i][x] != 'n'){
						return 'f'; // obstacle
					}
				}
			}else if(y > ny){
				for(int i=ny+1; i<y; ++i){
					if(chess_board[i][x] != 'n'){
						return 'f'; // obstacle
					}
				}
			}
			if(dest == 'n'){
				chess_board[ny][nx] = 'r';
				chess_board[y][x] = 'n';
				return 's';
			}else if(Character.toLowerCase(dest) != dest){
				chess_board[ny][nx] = 'r';
				chess_board[y][x] = 'n';
				return 'e';
			}else{
				return 'f';
			}
		}
		
		return 'f';
	}
	private char moveUrChariot(int nx, int ny){
		// Rule: 1. no obstacles between source and destination.
		if(nx <0 || nx > 8 || ny < 0 || ny > 9){
			return 'f';
		}
		
		char dest = chess_board[ny][nx];
		
		if(Math.abs(nx-x) > 0 && ny == y){
			if(nx > x){
				for(int i=x+1; i<nx; ++i){
					if(chess_board[y][i] != 'n'){
						return 'f'; // obstacle
					}
				}
			}else if(x > nx){
				for(int i=nx+1; i<x; ++i){
					if(chess_board[y][i] != 'n'){
						return 'f'; // obstacle
					}
				}
			}
			if(dest == 'n'){
				chess_board[ny][nx] = 'R';
				chess_board[y][x] = 'n';
				return 's';
			}else if(Character.toUpperCase(dest) != dest){
				chess_board[ny][nx] = 'R';
				chess_board[y][x] = 'n';
				return 'e';
			}else{
				return 'f';
			}
		}else if(Math.abs(ny-y) > 0 && nx == x){
			if(ny > y){
				for(int i=y+1; i<ny; ++i){
					if(chess_board[i][x] != 'n'){
						return 'f'; // obstacle
					}
				}
			}else if(y > ny){
				for(int i=ny+1; i<y; ++i){
					if(chess_board[i][x] != 'n'){
						return 'f'; // obstacle
					}
				}
			}
			if(dest == 'n'){
				chess_board[ny][nx] = 'R';
				chess_board[y][x] = 'n';
				return 's';
			}else if(Character.toUpperCase(dest) != dest){
				chess_board[ny][nx] = 'R';
				chess_board[y][x] = 'n';
				return 'e';
			}else{
				return 'f';
			}
		}
		return 'f';
	}
	private char moveMyCannon(int nx, int ny){
		// Rule: 1. if no obstacle, can move but not eat; 2. if 1 obstacle, can fly and eat.
		if(nx <0 || nx > 8 || ny < 0 || ny > 9){
			return 'f';
		}
		
		char dest = chess_board[ny][nx];
		int ob = 0;
		if(Math.abs(nx-x) > 0 && ny == y){
			if(nx > x){
				for(int i=x+1; i<nx; ++i){
					if(chess_board[y][i] != 'n'){
						++ob;
					}
				}
			}else if(x > nx){
				for(int i=nx+1; i<x; ++i){
					if(chess_board[y][i] != 'n'){
						++ob;
					}
				}
			}
		}else if(Math.abs(ny-y) > 0 && nx == x){
			if(ny > y){
				for(int i=y+1; i<ny; ++i){
					if(chess_board[i][x] != 'n'){
						++ob;
					}
				}
			}else if(y > ny){
				for(int i=ny+1; i<y; ++i){
					if(chess_board[i][x] != 'n'){
						++ob;
					}
				}
			}
		}else{
			return 'f';
		}
		
		if(ob == 0 && dest == 'n'){
			// can only move
			chess_board[ny][nx] = 'c';
			chess_board[y][x] = 'n';
			return 's';
		}else if(ob == 1 && Character.toLowerCase(dest) != dest){
			// can only fly and eat
			chess_board[ny][nx] = 'c';
			chess_board[y][x] = 'n';
			return 'e';
		}else{
			return 'f';
		}
	}
	private char moveUrCannon(int nx, int ny){
		// Rule: 1. if no obstacle, can move but not eat; 2. if 1 obstacle, can fly and eat.
		if(nx <0 || nx > 8 || ny < 0 || ny > 9){
			return 'f';
		}
		
		char dest = chess_board[ny][nx];
		int ob = 0;
		if(Math.abs(nx-x) > 0 && ny == y){
			if(nx > x){
				for(int i=x+1; i<nx; ++i){
					if(chess_board[y][i] != 'n'){
						++ob;
					}
				}
			}else if(x > nx){
				for(int i=nx+1; i<x; ++i){
					if(chess_board[y][i] != 'n'){
						++ob;
					}
				}
			}
		}else if(Math.abs(ny-y) > 0 && nx == x){
			if(ny > y){
				for(int i=y+1; i<ny; ++i){
					if(chess_board[i][x] != 'n'){
						++ob;
					}
				}
			}else if(y > ny){
				for(int i=ny+1; i<y; ++i){
					if(chess_board[i][x] != 'n'){
						++ob;
					}
				}
			}
		}else{
			return 'f';
		}
		
		if(ob == 0 && dest == 'n'){
			// can only move
			chess_board[ny][nx] = 'C';
			chess_board[y][x] = 'n';
			return 's';
		}else if(ob == 1 && Character.toUpperCase(dest) != dest){
			// can only fly and eat
			chess_board[ny][nx] = 'C';
			chess_board[y][x] = 'n';
			return 'e';
		}else{
			return 'f';
		}
	}
	private char moveMySoldier(int nx, int ny){
		// Rule: 1. only move forward; 2. upgrade after cross the river
		if(nx < 0 || nx > 8 || ny < 0 || ny > 5){
			return 'f';
		}
		if(ny-y == 1 && nx == x){
			char dest = chess_board[ny][nx];
			
			if(y == 4){
				// upgrade soldier
				if(dest == 'n'){
					chess_board[ny][nx] = 'b';
					chess_board[y][x] = 'n';
					return 's';
				}else if(Character.toLowerCase(dest) != dest){
					chess_board[ny][nx] = 'b';
					chess_board[y][x] = 'n';
					return 'e';
				}else{
					return 'f';
				}
			}else{
				if(dest == 'n'){
					chess_board[ny][nx] = 's';
					chess_board[y][x] = 'n';
					return 's';
				}else if(Character.toLowerCase(dest) != dest){
					chess_board[ny][nx] = 's';
					chess_board[y][x] = 'n';
					return 'e';
				}else{
					return 'f';
				}
			}
		}else{
			return 'f';
		}
	}
	private char moveUrSoldier(int nx, int ny){
		if(nx < 0 || nx > 8 || ny < 4 || ny > 9){
			return 'f';
		}
		if(y-ny == 1 && nx == x){
			char dest = chess_board[ny][nx];
			
			if(y == 4){
				// upgrade soldier
				if(dest == 'n'){
					chess_board[ny][nx] = 'B';
					chess_board[y][x] = 'n';
					return 's';
				}else if(Character.toUpperCase(dest) != dest){
					chess_board[ny][nx] = 'B';
					chess_board[y][x] = 'n';
					return 'e';
				}else{
					return 'f';
				}
			}else{
				if(dest == 'n'){
					chess_board[ny][nx] = 'S';
					chess_board[y][x] = 'n';
					return 's';
				}else if(Character.toUpperCase(dest) != dest){
					chess_board[ny][nx] = 'S';
					chess_board[y][x] = 'n';
					return 'e';
				}else{
					return 'f';
				}
			}
		}else{
			return 'f';
		}
	}
	private char moveMyBigSoldier(int nx, int ny){
		// Rule: 1. move left, right, forward
		if(nx < 0 || nx > 8 || ny < 5 || ny > 9){
			return 'f';
		}
		
		if((ny-y == 1 && nx == x) || (Math.abs(nx-x) == 1 && ny == y)){
			char dest = chess_board[ny][nx];
			if(dest == 'n'){
				chess_board[ny][nx] = 'b';
				chess_board[y][x] = 'n';
				return 's';
			}else if(Character.toLowerCase(dest) != dest){
				chess_board[ny][nx] = 'b';
				chess_board[y][x] = 'n';
				return 'e';
			}else{
				return 'f';
			}
		}
		return 'f';
	}
	private char moveUrBigSoldier(int nx, int ny){
		if(nx < 0 || nx > 8 || ny < 0 || ny > 4){
			return 'f';
		}
		
		if((y-ny == 1 && nx == x) || (Math.abs(nx-x) == 1 && ny == y)){
			char dest = chess_board[ny][nx];
			if(dest == 'n'){
				chess_board[ny][nx] = 'B';
				chess_board[y][x] = 'n';
				return 's';
			}else if(Character.toUpperCase(dest) != dest){
				chess_board[ny][nx] = 'B';
				chess_board[y][x] = 'n';
				return 'e';
			}else{
				return 'f';
			}
		}
		return 'f';
	}
	



}




