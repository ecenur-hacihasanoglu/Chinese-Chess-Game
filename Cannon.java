import java.util.ArrayList;

public class Cannon extends Item{

	private Game game;
	@Override
	public String getColour() {
		// TODO Auto-generated method stub
		return super.getColour();
	}


	@Override
	public void setColour(String colour) {
		// TODO Auto-generated method stub
		super.setColour(colour);
	}


	ArrayList<Integer> legal_move_positions= new ArrayList<Integer>();
	ArrayList<Integer> check_positions=new ArrayList<Integer>();
	Player player;

	public Cannon(String colour, Game game,Player player) {
		super(colour, game,player);
		this.setColour(colour);
		this.game=game;
		this.player=player;
		this.setPoint(4.5f);
	}
	@Override
	public float getPoint() {
		// TODO Auto-generated method stub
		return super.getPoint();
	}

	@Override
	public void setPoint(float point) {
		// TODO Auto-generated method stub
		super.setPoint(point);
	}


	public void update_legal_moves() {
		check_positions.clear();
		legal_move_positions.clear();
		ArrayList<Integer> uplist=new ArrayList<Integer>();
		ArrayList<Integer> downlist=new ArrayList<Integer>();
		ArrayList<Integer> rightlist=new ArrayList<Integer>();
		ArrayList<Integer> leftlist=new ArrayList<Integer>();
		ArrayList<Integer> upcheck=new ArrayList<Integer>();
		ArrayList<Integer> downcheck=new ArrayList<Integer>();
		ArrayList<Integer> righcheck=new ArrayList<Integer>();
		ArrayList<Integer> leftcheck=new ArrayList<Integer>();
		int rowmain=Game.calculate_index(this.getPosition())/9;
		int colmain=Game.calculate_index(this.getPosition())%9;
		int row=rowmain;
		int col=colmain;
		while(row>0) {
			row--;
			if(game.getBoard().board[row*9+colmain]==null) {
				downlist.add(row*9+colmain);
				
				
			}else {
				row--;
				while(row>=0) {
					
					downcheck.add(row*9+colmain);
					if(game.getBoard().board[row*9+colmain]!=null){
						downlist.add(row*9+colmain);
						
						break;
					}
					row--;
				}
				if(row==-1)
					downcheck.clear();
				break;
			}
			
		}
		row=rowmain;
		while(row<9) {
			row++;
			if(game.getBoard().board[row*9+colmain]==null) {
				uplist.add(row*9+colmain);
				
			}else {
				row++;
				while(row<=9) {
					
					upcheck.add(row*9+colmain);
					if(game.getBoard().board[row*9+colmain]!=null){
						uplist.add(row*9+colmain);
						break;
					}
					row++;
				}
				if(row==10)
					upcheck.clear();
			break;
			}
			
		}
		while(col>0) {
			col--;
			if(game.getBoard().board[rowmain*9+col]==null) {
				leftlist.add(rowmain*9+col);
			}else {
				col--;
				while(col>=0) {
					
					leftcheck.add(rowmain*9+col);
					if(game.getBoard().board[rowmain*9+col]!=null){
						leftlist.add(rowmain*9+col);
						break;
					}
					col--;
				}
				if(col==0)
					leftcheck.clear();
				break;
			}
			
		}		
		col=colmain;
		col++;
		while(col<=8) {
			if(game.getBoard().board[rowmain*9+col]==null) {
				rightlist.add(rowmain*9+col);
				col++;
			}else {
				col++;
				while(col<=8) {
					
					righcheck.add(rowmain*9+col);
					if(game.getBoard().board[rowmain*9+col]!=null){
						rightlist.add(rowmain*9+col);
						break;
					}
					col++;
					
				}
				if(col==8)
					righcheck.clear();
				break;
			
			}
			
		}
//		System.out.println(leftlist);
//		System.out.println(rightlist);
//		System.out.println(downlist);
//		System.out.println(uplist);
		if(!leftcheck.isEmpty())
			check_positions.addAll(leftcheck);
		if(!righcheck.isEmpty())
			check_positions.addAll(righcheck);
		if(!downcheck.isEmpty())
			check_positions.addAll(downcheck);
		if(!upcheck.isEmpty())
			check_positions.addAll(upcheck);
		if(!leftlist.isEmpty())
			legal_move_positions.addAll(leftlist);
		if(!rightlist.isEmpty())
			legal_move_positions.addAll(rightlist);
		if(!downlist.isEmpty())
			legal_move_positions.addAll(downlist);
		if(!uplist.isEmpty())
			legal_move_positions.addAll(uplist);
		
		for (int i = 0; i < legal_move_positions.size(); i++) {
			if (game.getBoard().board[legal_move_positions.get(i)] != null) {
			
				if (game.getBoard().board[legal_move_positions.get(i)].getColour().equals(this.getColour())) {

					legal_move_positions.remove(i);
					i--;
				}
			}

		}
//		for (int i=0;i<legal_move_positions.size();i++) {
//				Item[] newBoard=game.getBoard().board.clone();
//				newBoard[legal_move_positions.get(i)]=null;
//				newBoard[legal_move_positions.get(i)]=this;
//				if((getColour().equals("red")&& game.isThereACheckforRed(newBoard,game.getRedgeneralposition()))||(getColour().equals("black")&& game.isThereACheckforBlack(newBoard,game.getBlackgeneralposition()))) {
//					legal_move_positions.remove(legal_move_positions.get(i));
//				}
//		}
//		if(this.getColour().equals("black"))
//			System.out.println(check_positions);
//		
		
	}


	
	@Override
	public void move(String destination) throws IllegalMove {
		// TODO Auto-generated method stub
//		System.out.println(check_positions);
		super.move(destination);
	}


	public ArrayList<Integer> getLegal_move_positions() {
		return legal_move_positions;
	}



	@Override
	public String getPosition() {
		// TODO Auto-generated method stub
		return super.getPosition();
	}


	@Override
	public void setPosition(String position) {
		// TODO Auto-generated method stub
		super.setPosition(position);
	}
	


	
}
