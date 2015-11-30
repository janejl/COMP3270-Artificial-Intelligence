public class Chessman {
	private char type; // Machine use small letter, human use capital letter.
	// current position
	private int x;
	private int y;
	
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
	public char getType(){
		return type;
	}
	// should only be used after checking rules.
	public void updatePos(int newx, int newy){
		x = newx;
		y = newy;
	}
	
	@Override
    public String toString() {
		String name = "n";
        switch(type){
        case 'g':
        	name = "將";
			break;
		case 'G':
			name = "帥";
			break;
		case 'a':
			name = "士";
			break;
		case 'A':
			name = "仕";
			break;
		case 'e':
			name = "象";
			break;
		case 'E':
			name = "相";
			break;
		case 'h':
			name = "馬";
			break;
		case 'H':
			name = "傌";
			break;
		case 'r':
			name = "車";
			break;
		case 'R':
			name = "俥";
			break;
		case 'c':
			name = "砲";
			break;
		case 'C':
			name = "炮";
			break;
		case 's':
			name = "卒";
			break;
		case 'S':
			name = "兵";
			break;
		case 'b':
			name = "卒";
			break;
		case 'B':
			name = "兵";
        }
        name += "("+Integer.toString(x)+", "+Integer.toString(y)+") ";
        return name;
    }

}




