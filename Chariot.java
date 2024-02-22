import java.util.ArrayList;

public class Chariot extends Item {
	private String colour;
	private Game game;
	ArrayList<Integer> legal_move_positions= new ArrayList<Integer>();

	Player player;
	
	public Chariot(String colour, Game game,Player player) {
		super(colour, game,player);
		this.colour=colour;
		this.game=game;
		this.player=player;
		this.setPoint(9.0f);
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
	
	public ArrayList<Integer> getLegal_move_positions() {
		return legal_move_positions;
	}

	public void update_legal_moves() {
		
		legal_move_positions.clear();
		ArrayList<Integer> uplist=new ArrayList<Integer>();
		ArrayList<Integer> downlist=new ArrayList<Integer>();
		ArrayList<Integer> rightlist=new ArrayList<Integer>();
		ArrayList<Integer> leftlist=new ArrayList<Integer>();
		int rowmain=Game.calculate_index(this.getPosition())/9;
		int colmain=Game.calculate_index(this.getPosition())%9;
		int row=rowmain;
		int col=colmain;
		while(row>0) {
			row--;
			if(game.getBoard().board[row*9+colmain]==null) {
				downlist.add(row*9+colmain);
			
				
			}else {
				downlist.add(row*9+colmain);
				break;

			
			}
		}
		row=rowmain;
		while(row<9) {
			row++;
			if(game.getBoard().board[row*9+colmain]==null) {
				uplist.add(row*9+colmain);
				
			}else {
				uplist.add(row*9+colmain);
				break;
				}
			
			}
			
		while(col>0) {
			col--;
			if(game.getBoard().board[rowmain*9+col]==null) {
				leftlist.add(rowmain*9+col);
			}else {
				leftlist.add(rowmain*9+col);
				break;

			}
			
		}		
		col=colmain;
		while(col<8) {
			col++;
			if(game.getBoard().board[rowmain*9+col]==null) {
				rightlist.add(rowmain*9+col);
				
			}else {
				rightlist.add(rowmain*9+col);
				break;

			}
			
		}
//		System.out.println(leftlist);
//		System.out.println(rightlist);
//		System.out.println(downlist);
//		System.out.println(uplist);
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

			
				if (game.getBoard().board[legal_move_positions.get(i)].getColour().equals(colour)) {
					
					legal_move_positions.remove(i);
					i--;
				}
			}

		}
//		for (int i=0;i<legal_move_positions.size();i++) {
//			Item[] newBoard=game.getBoard().board.clone();
//			newBoard[legal_move_positions.get(i)]=null;
//			newBoard[legal_move_positions.get(i)]=this;
//			if((getColour().equals("red")&& game.isThereACheckforRed(newBoard,game.getRedgeneralposition()))||(getColour().equals("black")&& game.isThereACheckforBlack(newBoard,game.getBlackgeneralposition()))) {
//				legal_move_positions.remove(legal_move_positions.get(i));
//			}
//	}
//		System.out.println(legal_move_positions);
		
	}
	
	@Override
	public String getColour() {
		// TODO Auto-generated method stub
		return this.colour;
	}
	@Override
	public void setColour(String colour) {
		// TODO Auto-generated method stub
		this.colour=colour;
	}
	@Override
	public Game getGame() {
		// TODO Auto-generated method stub
		return super.getGame();
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
