public class Chessman {
	// Machine use small letter, human use capital letter.
	private char type;
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
	
	public void move(int new_x, int new_y){
		
	}
	
	// Chessman's rule:
	private void moveGeneral(){
		
	}
	
	
/*	
	void moveComplete(Node*, void*);
    //走棋规则 
    bool canMove(int moveid, int killid, int x, int y);
    //将的走棋规则 
    bool canMoveJiang(int moveid, int killid, int x, int y);
    //士的走棋规则 
    bool canMoveShi(int moveid, int x, int y);
    //相的走棋规则 
    bool canMoveXiang(int moveid, int x, int y);
    //车的走棋规则 
    bool canMoveChe(int moveid, int x, int y);
    //马的走棋规则 
    bool canMoveMa(int moveid, int x, int y);
    //炮的走棋规则 
    bool canMovePao(int moveid, int killid, int x, int y);
    //兵的走棋规则 
    bool canMoveBing(int moveid, int x, int y);
	
  //将的走棋规则 
    bool SceneGame::canMoveJiang(int moveid, int killid, int x, int y)
    {
        Stone* skill = _s[killid];
        //将的走棋规则：
        //1、一次走一格 
        //2、不能出九宫格 

        //将的对杀 
        if(skill->getType() == Stone::JIANG)
        {
            return canMoveChe(moveid, x, y);
        }
        //通过棋子的ID得到棋子 
        Stone* s = _s[moveid];
        //获得将当前的位置 
        int xo = s->getX();
        int yo = s->getY();
        //获得将走的格数 
        //(x,y)表示将走到的位置 
        int xoff = abs(xo - x);
        int yoff = abs(yo - y);
        int d = xoff*10 + yoff;
        //走将的时候有两种情况 
        //xoff=1, yoff=0：将向左或向右 
        //xoff=0, yoff=1：将向前或向后 
        if(d != 1 && d != 10)
        {
            return false;
        }
        //判断将是否出了九宫 
        //红色的将和黑色的将的x坐标的范围都是3<=x<=5 
        if(x<3 || x>5)
        {
            return false;
        }
        //如果玩家的棋子是红棋 
        if(_redSide == s->getRed())
        {
            //判断将是否出了九宫 
            if(y<0 || y>2)
            {
                return false;
            }
        }
        else//判断黑色的将的范围 
        {
            //判断将是否出了九宫 
            if(y>9 || y<7)
            {
                return false;
            }
        }

        return true;
    }


    //士的走棋规则 
    bool SceneGame::canMoveShi(int moveid, int x, int y)
    {
        //士的走棋规则：
        //1、一次走一格 
        //2、不能出九宫格 
        //3、斜着走 
        // 
        //通过棋子的ID得到棋子 
        Stone* s = _s[moveid];
        //获得相走棋前的位置 
        int xo = s->getX();
        int yo = s->getY();
        //获得相走的格数 
        //(x,y)表示将走到的位置 
        int xoff = abs(xo - x);
        int yoff = abs(yo - y);
        int d = xoff*10 + yoff;
        //士每走一步x方向走1格,y方向走1格 
        //当走的格数大于1格时 
        //返回false 
        if(d != 11)
        {
            return false;
        }
        //判断士是否出了九宫 
        //红色的士和黑色的士的x坐标的范围都是3<=x<=5 
        if(x<3 || x>5)
        {
            return false;
        }
        //如果玩家的棋子是红棋 
        if(_redSide == s->getRed())
        {
            //判断士是否出了九宫 
            if(y<0 || y>2)
            {
                return false;
            }
        }
        else//判断黑色的士的范围 
        {
            //判断士是否出了九宫 
            if(y>9 || y<7)
            {
                return false;
            }
        }

        return true;
    }

    //相的走棋规则 
    bool SceneGame::canMoveXiang(int moveid, int x, int y)
    {
        //相的走棋规则： 
        //每走一次x移动2格,y移动2格 
        //不能过河 
        // 
        //通过棋子的ID得到棋子 
        Stone* s = _s[moveid];
        //获得相走棋前的位置 
        int xo = s->getX();
        int yo = s->getY();
        //获得相走的格数 
        //(x,y)表示将走到的位置 
        int xoff = abs(xo - x);
        int yoff = abs(yo - y);
        int d = xoff*10 + yoff;
        //相每一次x方向走2格子,y方向走2格 
        //当走的格数大于2格时 
        //返回false 
        if(d != 22)
        {
            return false;
        }
        //计算两个坐标的中点坐标 
        int xm = (xo + x) / 2;
        int ym = (yo + y) / 2;
        //得到(xm,ym)上的棋子 
        int id = getStone(xm, ym);
        //当(xm,ym)上有棋子的时候 
        if(id != -1)
        {
            //不能走相 
            return false;
        }
        //限制相不能过河 
        //如果玩家的棋子是红棋 
        if(_redSide == s->getRed())
        {
            //判断相是否过了河 
            if(y > 4)
            {
                return false;
            }
        }
        else//判断黑色的相的范围 
        {
            //判断相是否过了河 
            if(y < 5)
            {
                return false;
            }
        }
        return true;
    }

    //车的走棋规则 
    bool SceneGame::canMoveChe(int moveid, int x, int y)
    {
        //通过棋子的ID得到棋子 
        Stone* s = _s[moveid];
        //获得车走棋前的位置 
        int xo = s->getX();
        int yo = s->getY();
        //当两点之间有棋子的时候车不能走 
        if(getStoneCount(xo,yo,x,y) != 0)
        {
            return false;
        }
        return true;
    }

    //马的走棋规则 
    bool SceneGame::canMoveMa(int moveid, int x, int y)
    {
        //通过棋子的ID得到棋子 
        Stone* s = _s[moveid];
        //获得马走棋前的位置 
        int xo = s->getX();
        int yo = s->getY();
        //CCLog("xo=%d", xo);
        //CCLog("yo=%d", yo);
        //获得马走的格数 
        //(x,y)表示马走到的位置 
        //马有两种情况：
        //第一种情况：马先向前或向后走1步，再向左或向右走2步 
        //第二种情况：马先向左或向右走1不，再向前或向后走2步 
        int xoff = abs(xo-x);
        int yoff = abs(yo-y);
        //CCLog("x=%d", x);
        //CCLog("y=%d", y);
        int d = xoff*10 + yoff;
        //CCLog("d=%d", d);
        if(d != 12 && d != 21)     
        {
            return false;
        }
        int xm, ym;//记录绑脚点坐标 
        if(d == 12)//当马走的是第一种情况 
        {
            xm = xo;//绑脚点的x坐标为走棋前马的x坐标 
            ym = (yo + y) / 2;//绑脚点的y坐标为走棋前马的y坐标和走棋后马的y坐标的中点坐标 
        }
        else//当马走的是第二种情况 
        {
            xm = (xo + x) / 2;//绑脚点的x坐标为走棋前马的x坐标和走棋后马的x坐标的中点坐标 
            ym = yo;//绑脚点的y坐标为走棋前马的y坐标 
        }
        //CCLog("xm=%d", xm); 
        //CCLog("ym=%d", ym); 
        //当绑脚点有棋子时,不能走 
        if(getStone(xm, ym) != -1) 
        {
            return false;
        }
        return true;
    }

    //炮的走棋规则 
    bool SceneGame::canMovePao(int moveid, int killid, int x, int y)
    {
        //通过棋子的ID得到棋子 
        Stone* s = _s[moveid];
        //获得炮走棋前的位置 
        int xo = s->getX();
        int yo = s->getY();
        //当触摸点上有一个棋子 
        //而且两点之间只有一个棋子的时候 
        //炮吃掉触摸点上的棋子 
        if(killid != -1 && this->getStoneCount(xo,yo,x,y) == 1)
        {
            return true;
        }
        if(killid == -1 && this->getStoneCount(xo, yo, x, y) == 0) 
        {
            return true;
        }
        return false;
    }

    //兵的走棋规则 
    bool SceneGame::canMoveBing(int moveid, int x, int y)
    {
        //兵的走棋规则： 
        //1、一次走一格 
        //2、前进一格后不能后退 
        //3、过河后才可以左右移动 
        //通过棋子的ID得到棋子 
        Stone* s = _s[moveid];
        //获得将当前的位置 
        int xo = s->getX();
        int yo = s->getY();
        //获得兵走的格数 
        //(x,y)表示将走到的位置 
        int xoff = abs(xo - x);
        int yoff = abs(yo - y);
        int d = xoff*10 + yoff;
        //走将的时候有两种情况 
        //xoff=1, yoff=0：将向左或向右 
        //xoff=0, yoff=1：将向前或向后 
        if(d != 1 && d != 10)
        {
            return false;
        }
        //如果玩家的棋子是红棋 
        if(_redSide == s->getRed())
        {
            //限制红色的兵不能后退 
            if(y < yo)
            {
                return false;
            }
            //红色的兵没有过河不能左右移动 
            if(yo <= 4 && y == yo)
            {
                return false;
            }
        }
        else//判断黑色的兵 
        {
            //限制黑色的兵不能后退 
            if(y > yo)
            {
                return false;
            }
            //黑色的兵没有过河不能左右移动 
            if(yo >= 5 && y == yo)
            {
                return false;
            }
        }
        return true;
    }
 */   
	
}
