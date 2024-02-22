import java.util.ArrayList;

public class Soldier extends Item {

	private Game game;
	ArrayList<Integer> legal_move_positions= new ArrayList<Integer>();
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
	Player player;
	public Soldier(String colour, Game game,Player player) {
		super(colour, game, player);
		this.setColour(colour);
		this.game=game;
		this.player=player;
		this.setPoint(1.0f);
		// TODO Auto-generated constructor stub
	}

	public void update_legal_moves() {
		// TODO Auto-generated method stub
		legal_move_positions.clear();
		if(this.getColour().equals("red")) {
			if(Game.calculate_index(this.getPosition())>44) {
				if(Game.calculate_index(this.getPosition())%9!=8)
				legal_move_positions.add(Game.calculate_index(this.getPosition())+1);
				if(Game.calculate_index(this.getPosition())%9!=0)
				legal_move_positions.add(Game.calculate_index(this.getPosition())-1);
			}
			if(Game.calculate_index(this.getPosition())<81)
				legal_move_positions.add(Game.calculate_index(this.getPosition())+9);
		}else {
			if(Game.calculate_index(this.getPosition())<45) {
				if(Game.calculate_index(this.getPosition())%9!=8)
				legal_move_positions.add(Game.calculate_index(this.getPosition())+1);
				if(Game.calculate_index(this.getPosition())%9!=0)
				legal_move_positions.add(Game.calculate_index(this.getPosition())-1);
			}
			if(Game.calculate_index(this.getPosition())>8)
				legal_move_positions.add(Game.calculate_index(this.getPosition())-9);
		}
		for (int i = 0; i < legal_move_positions.size(); i++) {
			if (game.getBoard().board[legal_move_positions.get(i)] != null && !this.getPosition().equals("xx")) {

			
				if (game.getBoard().board[legal_move_positions.get(i)].getColour().equals(this.getColour())) {
					
					legal_move_positions.remove(i);
					
				}
			}

		}
	
	}
	public ArrayList<Integer> getLegal_move_positions() {
		return legal_move_positions;
	}

	

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


}
