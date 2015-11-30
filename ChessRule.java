
public class ChessRule {

	public char moveRule(int x, int y, int nx, int ny, char[][] chess_board){
		switch(chess_board[y][x]){
			case 'g':
				return moveMyGeneral(x, y, nx, ny, chess_board);
			case 'G':
				return moveUrGeneral(x, y, nx, ny, chess_board);
			case 'a':
				return moveMyAdvisor(x, y, nx, ny, chess_board);
			case 'A':
				return moveUrAdvisor(x, y, nx, ny, chess_board);
			case 'e':
				return moveMyElephant(x, y, nx, ny, chess_board);
			case 'E':
				return moveUrElephant(x, y, nx, ny, chess_board);
			case 'h':
				return moveMyHorse(x, y, nx, ny, chess_board);
			case 'H':
				return moveUrHorse(x, y, nx, ny, chess_board);
			case 'r':
				return moveMyChariot(x, y, nx, ny, chess_board);
			case 'R':
				return moveUrChariot(x, y, nx, ny, chess_board);
			case 'c':
				return moveMyCannon(x, y, nx, ny, chess_board);
			case 'C':
				return moveUrCannon(x, y, nx, ny, chess_board);
			case 's':
				return moveMySoldier(x, y, nx, ny, chess_board);
			case 'S':
				return moveUrSoldier(x, y, nx, ny, chess_board);
			case 'b':
				return moveMyBigSoldier(x, y, nx, ny, chess_board);
			case 'B':
				return moveUrBigSoldier(x, y, nx, ny, chess_board);
		}
		return 'f';
	}
	
	public char moveMyGeneral(int x, int y, int nx, int ny, char[][] chess_board){
		// Rule: 1. within palace; 2. one square every step; 3. fly and eat when meet enemy's general.
		if(nx <0 || nx > 8 || ny < 0 || ny > 9){
			// System.out.println("Fail: out of scope");
			return 'f';
		}
		
		char temp = chess_board[ny][nx];
		if(temp == 'G' && nx == x && ny > 6 && ny < 10){
			for(int i=y+1; i<ny; ++i){
				if(chess_board[i][x] != 'n'){
					// System.out.println("Fail: cannot fly and eat, obstacle in ("+ x + ", "+i+")");
					return 'f'; // obstacle
				}
			}
			return 'e';
		}
		
		// General won't fly and eat, check normal case:
		if(nx < 3 || nx > 5 || ny < 0 || ny > 2){
			// System.out.println("Fail: out of palace");
			return 'f';
		}
		
		if((Math.abs(nx-x) == 1 && (ny-y) == 0) || (Math.abs(ny-y) == 1 && (nx-x) == 0)){
			if(temp == 'n'){
				return 's';
			}else if(Character.toLowerCase(temp) != temp){
				return 'e';
			}else{
				// System.out.println("Fail: space is taken by other soldier");
				return 'f';
			}
		}else{
			// System.out.println("Fail: wrong number of steps");
			return 'f';
		}
	}
	public char moveUrGeneral(int x, int y, int nx, int ny, char[][] chess_board){
		if(nx <0 || nx > 8 || ny < 0 || ny > 9){
			// System.out.println("Fail: out of scope");
			return 'f';
		}
		
		char temp = chess_board[ny][nx];
		if(temp == 'g' && nx == x && ny >= 0 && ny < 3){
			for(int i=ny+1; i<y; ++i){
				if(chess_board[i][x] != 'n'){
					// System.out.println("Fail: cannot fly and eat, obstacle in ("+ x + ", "+i+")");
					return 'f'; // obstacle
				}
			}
			return 'e';
		}
		
		// General won't fly and eat, check normal case:
		if(nx < 3 || nx > 5 || ny < 7 || ny > 9){
			// System.out.println("Fail: out of palace");
			return 'f';
		}
		if((Math.abs(nx-x) == 1 && (ny-y) == 0) || (Math.abs(ny-y) == 1 && (nx-x) == 0)){
			if(temp == 'n'){
				return 's';
			}else if(Character.toUpperCase(temp) != temp){
				return 'e';
			}else{
				// System.out.println("Fail: space is taken by other soldier");
				return 'f';
			}
		}else{
			// System.out.println("Fail: wrong number of steps");
			return 'f';
		}
	}
	public char moveMyAdvisor(int x, int y, int nx, int ny, char[][] chess_board){
		// Rule: 1. within palace; 2. one diagonal one step.
		if(nx < 3 || nx > 5 || ny < 0 || ny > 2){
			// System.out.println("Fail 1");
			return 'f';
		}
		if(Math.abs(nx-x) == 1 && Math.abs(ny-y) == 1){
			char temp = chess_board[ny][nx];
			if(temp == 'n'){
				return 's';
			}else if(Character.toLowerCase(temp) != temp){
				return 'e';
			}else{
				// System.out.println("Fail 2");
				return 'f';
			}
		}else{
			// System.out.println("Fail 3");
			return 'f';
		}
	}
	public char moveUrAdvisor(int x, int y, int nx, int ny, char[][] chess_board){
		if(nx < 3 || nx > 5 || ny < 7 || ny > 9){
			// System.out.println("Fail 1");
			return 'f';
		}
		if(Math.abs(nx-x) == 1 && Math.abs(ny-y) == 1){
			char temp = chess_board[ny][nx];
			if(temp == 'n'){
				return 's';
			}else if(Character.toUpperCase(temp) != temp){
				return 'e';
			}else{
				// System.out.println("Fail 2");
				return 'f';
			}
		}else{
			// System.out.println("Fail 3");
			return 'f';
		}
	}
	public char moveMyElephant(int x, int y, int nx, int ny, char[][] chess_board){
		// Rule: 1. cannot across river; 2. two diagonals one step; 3. no obstacle in center of 田.
		if(nx < 0 || nx > 8 || ny < 0 || ny > 4){
			return 'f';
		}
		char check_center = chess_board[(ny+y)/2][(nx+x)/2];
		if(Math.abs(nx-x) == 2 && Math.abs(ny-y) == 2 && check_center == 'n'){
			char temp = chess_board[ny][nx];
			if(temp == 'n'){
				return 's';
			}else if(Character.toLowerCase(temp) != temp){
				return 'e';
			}else{
				return 'f';
			}
		}else{
			return 'f';
		}
	}
	public char moveUrElephant(int x, int y, int nx, int ny, char[][] chess_board){
		// Rule: 1. cannot across river; 2. two diagonals one step; 3. no obstacle in center of 田.
		if(nx < 0 || nx > 8 || ny < 5 || ny > 9){
			// System.out.println("Fail 1");
			return 'f';
		}
		char check_center = chess_board[(ny+y)/2][(nx+x)/2];
		if(Math.abs(nx-x) == 2 && Math.abs(ny-y) == 2 && check_center == 'n'){
			char temp = chess_board[ny][nx];
			if(temp == 'n'){
				return 's';
			}else if(Character.toUpperCase(temp) != temp){
				return 'e';
			}else{
				// System.out.println("Fail 2");
				return 'f';
			}
		}else{
			// System.out.println("Fail 3");
			return 'f';
		}
	}
	public char moveMyHorse(int x, int y, int nx, int ny, char[][] chess_board){
		// Rule: 1. two skew diagonal one step; 2. no obstacle.
		if(nx <0 || nx > 8 || ny < 0 || ny > 9){
			// System.out.println("Fail 1");
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
					// System.out.println("Fail 2");
					return 'f'; // obstacle
				}else{
					noObstacle = true;
				}
			}else{
				ob_check = chess_board[y][x-1];
				if(ob_check != 'n'){
					// System.out.println("Fail 3");
					return 'f'; // obstacle
				}else{
					noObstacle = true;
				}
			}
			
			if(temp == 'n' && noObstacle){
				return 's';
			}else if(Character.toLowerCase(temp) != temp && noObstacle){
				return 'e';
			}else{
				// System.out.println("Fail 4");
				return 'f';
			}
		}else if((Math.abs(ny-y) == 2 && Math.abs(nx-x) == 1)){
			// vertical jump
			if(ny > y){
				ob_check = chess_board[y+1][x];
				if(ob_check != 'n'){
					// System.out.println("Fail 5");
					return 'f'; // obstacle
				}else{
					noObstacle = true;
				}
			}else{
				ob_check = chess_board[y-1][x];
				if(ob_check != 'n'){
					// System.out.println("Fail 6");
					return 'f'; // obstacle
				}else{
					noObstacle = true;
				}
			}
			
			if(temp == 'n' && noObstacle){
				return 's';
			}else if(Character.toLowerCase(temp) != temp && noObstacle){
				return 'e';
			}else{
				// System.out.println("Fail 7");
				return 'f';
			}
		}else{
			// System.out.println("Fail 8");
			return 'f';
		}
	}
	public char moveUrHorse(int x, int y, int nx, int ny, char[][] chess_board){
		// Rule: 1. two skew diagonal one step; 2. no obstacle.
		if(nx <0 || nx > 8 || ny < 0 || ny > 9){
			// System.out.println("Fail 1");
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
					// System.out.println("Fail 2");
					return 'f'; // obstacle
				}else{
					noObstacle = true;
				}
			}else{
				ob_check = chess_board[y][x-1];
				if(ob_check != 'n'){
					// System.out.println("Fail 3");
					return 'f'; // obstacle
				}else{
					noObstacle = true;
				}
			}
			
			if(temp == 'n' && noObstacle){
				return 's';
			}else if(Character.toUpperCase(temp) != temp && noObstacle){
				return 'e';
			}else{
				// System.out.println("Fail 4");
				return 'f';
			}
		}else if((Math.abs(ny-y) == 2 && Math.abs(nx-x) == 1)){
			// vertical jump
			if(ny > y){
				ob_check = chess_board[y+1][x];
				if(ob_check != 'n'){
					// System.out.println("Fail 5");
					return 'f'; // obstacle
				}else{
					noObstacle = true;
				}
			}else{
				ob_check = chess_board[y-1][x];
				if(ob_check != 'n'){
					// System.out.println("Fail 6");
					return 'f'; // obstacle
				}else{
					noObstacle = true;
				}
			}
			
			if(temp == 'n' && noObstacle){
				return 's';
			}else if(Character.toUpperCase(temp) != temp && noObstacle){
				return 'e';
			}else{
				// System.out.println("Fail 7");
				return 'f';
			}
		}else{
			// System.out.println("Fail 8");
			return 'f';
		}
	}
	public char moveMyChariot(int x, int y, int nx, int ny, char[][] chess_board){
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
				return 's';
			}else if(Character.toLowerCase(dest) != dest){
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
				return 's';
			}else if(Character.toLowerCase(dest) != dest){
				return 'e';
			}else{
				return 'f';
			}
		}
		
		return 'f';
	}
	public char moveUrChariot(int x, int y, int nx, int ny, char[][] chess_board){
		// Rule: 1. no obstacles between source and destination.
		if(nx <0 || nx > 8 || ny < 0 || ny > 9){
			// System.out.println("Fail 1");
			return 'f';
		}
		
		char dest = chess_board[ny][nx];
		
		if(Math.abs(nx-x) > 0 && ny == y){
			if(nx > x){
				for(int i=x+1; i<nx; ++i){
					if(chess_board[y][i] != 'n'){
						// System.out.println("Fail 2");
						return 'f'; // obstacle
					}
				}
			}else if(x > nx){
				for(int i=nx+1; i<x; ++i){
					if(chess_board[y][i] != 'n'){
						// System.out.println("Fail 3");
						return 'f'; // obstacle
					}
				}
			}
			if(dest == 'n'){
				return 's';
			}else if(Character.toUpperCase(dest) != dest){
				return 'e';
			}else{
				// System.out.println("Fail 4");
				return 'f';
			}
		}else if(Math.abs(ny-y) > 0 && nx == x){
			if(ny > y){
				for(int i=y+1; i<ny; ++i){
					if(chess_board[i][x] != 'n'){
						// System.out.println("Fail 5");
						return 'f'; // obstacle
					}
				}
			}else if(y > ny){
				for(int i=ny+1; i<y; ++i){
					if(chess_board[i][x] != 'n'){
						// System.out.println("Fail 6");
						return 'f'; // obstacle
					}
				}
			}
			if(dest == 'n'){
				return 's';
			}else if(Character.toUpperCase(dest) != dest){
				return 'e';
			}else{
				// System.out.println("Fail 7");
				return 'f';
			}
		}
		// System.out.println("Fail 8");
		return 'f';
	}
	public char moveMyCannon(int x, int y, int nx, int ny, char[][] chess_board){
		// Rule: 1. if no obstacle, can move but not eat; 2. if 1 obstacle, can fly and eat.
		if(nx <0 || nx > 8 || ny < 0 || ny > 9){
			// System.out.println("Fail 1");
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
			// System.out.println("Fail 2");
			return 'f';
		}
		
		if(ob == 0 && dest == 'n'){
			// can only move
			return 's';
		}else if(ob == 1 && Character.toLowerCase(dest) != dest){
			// can only fly and eat
			return 'e';
		}else{
			// System.out.println("Fail 3");
			return 'f';
		}
	}
	public char moveUrCannon(int x, int y, int nx, int ny, char[][] chess_board){
		// Rule: 1. if no obstacle, can move but not eat; 2. if 1 obstacle, can fly and eat.
		if(nx <0 || nx > 8 || ny < 0 || ny > 9){
			// System.out.println("Fail 1");
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
			// System.out.println("Fail 2");
			return 'f';
		}
		
		if(ob == 0 && dest == 'n'){
			// can only move
			return 's';
		}else if(ob == 1 && Character.toUpperCase(dest) != dest){
			// can only fly and eat
			return 'e';
		}else{
			// System.out.println("Fail 3");
			return 'f';
		}
	}
	public char moveMySoldier(int x, int y, int nx, int ny, char[][] chess_board){
		// Rule: 1. only move forward; 2. upgrade after cross the river
		if(nx < 0 || nx > 8 || ny < 0 || ny > 5){
			// System.out.println("Fail 1");
			return 'f';
		}
		if(ny-y == 1 && nx == x){
			char dest = chess_board[ny][nx];
			if(dest == 'n'){
				return 's';
			}else if(Character.toLowerCase(dest) != dest){
				return 'e';
			}else{
				// System.out.println("Fail 3");
				return 'f';
			}
		}else{
			// System.out.println("Fail 4");
			return 'f';
		}
	}
	public char moveUrSoldier(int x, int y, int nx, int ny, char[][] chess_board){
		if(nx < 0 || nx > 8 || ny < 4 || ny > 9){
			// System.out.println("Fail 1");
			return 'f';
		}
		if(y-ny == 1 && nx == x){
			char dest = chess_board[ny][nx];
			if(dest == 'n'){
				return 's';
			}else if(Character.toUpperCase(dest) != dest){
				return 'e';
			}else{
				// System.out.println("Fail 3");
				return 'f';
			}
		}else{
			// System.out.println("Fail 4");
			return 'f';
		}
	}
	public char moveMyBigSoldier(int x, int y, int nx, int ny, char[][] chess_board){
		// Rule: 1. move left, right, forward
		if(nx < 0 || nx > 8 || ny < 5 || ny > 9){
			// System.out.println("Fail 1");
			return 'f';
		}
		
		if((ny-y == 1 && nx == x) || (Math.abs(nx-x) == 1 && ny == y)){
			char dest = chess_board[ny][nx];
			if(dest == 'n'){
				return 's';
			}else if(Character.toLowerCase(dest) != dest){
				return 'e';
			}else{
				// System.out.println("Fail 2");
				return 'f';
			}
		}
		// System.out.println("Fail 3");
		return 'f';
	}
	public char moveUrBigSoldier(int x, int y, int nx, int ny, char[][] chess_board){
		if(nx < 0 || nx > 8 || ny < 0 || ny > 4){
			// System.out.println("Fail 1");
			return 'f';
		}
		
		if((y-ny == 1 && nx == x) || (Math.abs(nx-x) == 1 && ny == y)){
			char dest = chess_board[ny][nx];
			if(dest == 'n'){
				return 's';
			}else if(Character.toUpperCase(dest) != dest){
				return 'e';
			}else{
				// System.out.println("Fail 2");
				return 'f';
			}
		}
		// System.out.println("Fail 3");
		return 'f';
	}
}
