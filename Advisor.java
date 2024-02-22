import java.util.ArrayList;


public class Advisor extends Item {
	
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
	Player player;


	public Advisor(String colour, Game game, Player player) {
		super(colour, game, player);
		this.setColour(colour);;
		this.game = game;
		this.player = player;
		this.setPoint(2.0f);
	}

	
	public void update_legal_moves() {
		// TODO Auto-generated method stub
	
		legal_move_positions.clear();
	
		if (this.getColour().equals("red")) {
			if (Game.calculate_index(this.getPosition()) == 13) {
				legal_move_positions.add(23);
				legal_move_positions.add(21);
				legal_move_positions.add(3);
				legal_move_positions.add(5);

			} else {
				legal_move_positions.add(13);	
			} 
		}else {
			
			if (Game.calculate_index(this.getPosition()) == 76) {
					legal_move_positions.add(84);
					legal_move_positions.add(86);
					legal_move_positions.add(68);
					legal_move_positions.add(66);

			} else {
				legal_move_positions.add(76);	
			} 
			
			
		
		}
		for (int i = 0; i < legal_move_positions.size(); i++) {
			if (game.getBoard().board[legal_move_positions.get(i)] != null) {

			
				if (game.getBoard().board[legal_move_positions.get(i)].getColour().equals(this.getColour())) {
					
					legal_move_positions.remove(i);
					i--;
				}
			}

		}

		
	}
	
	

	public ArrayList<Integer> getLegal_move_positions() {
		return legal_move_positions;
	}

}
