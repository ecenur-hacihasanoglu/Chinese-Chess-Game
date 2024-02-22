import java.util.ArrayList;

public class Elephant extends Item {
	
	@Override
	public String getColour() {
		// TODO Auto-generated method stub
		return super.getColour();
	}

	private Game game;
	ArrayList<Integer> legal_move_positions= new ArrayList<Integer>();
	@Override
	public String getPosition() {
		// TODO Auto-generated method stub
		return super.getPosition();
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


	@Override
	public void setPosition(String position) {
		// TODO Auto-generated method stub
		super.setPosition(position);
	}

	int itemposition;
	Player player;

	public Elephant(String colour, Game game, Player player) {
		super(colour, game, player);
		this.setColour(colour);
		this.game = game;
		this.player = player;
		this.setPoint(2.0f);
	}


	
	public void update_legal_moves() {
		// TODO Auto-generated method stub
		legal_move_positions.clear();
		if (getColour().equals("red")) {
			if (Game.calculate_index(this.getPosition()) / 9<=2) {
				//sol yukarı
				if (Game.calculate_index(this.getPosition()) % 9 >= 2
						&& game.getBoard().board[Game.calculate_index(this.getPosition()) + 8] == null) {
					legal_move_positions.add(Game.calculate_index(this.getPosition()) + 16);
				}
				//sağ yukarı
				if (Game.calculate_index(this.getPosition()) % 9 <= 6
						&& game.getBoard().board[Game.calculate_index(this.getPosition()) + 10] == null) {
					legal_move_positions.add(Game.calculate_index(this.getPosition()) + 20);
				}
			}
			//sağ aşağı 
			if (Game.calculate_index(this.getPosition()) % 9 <= 6 && !Game.outOfBoard(Game.calculate_index(this.getPosition())-18)
					&& game.getBoard().board[Game.calculate_index(this.getPosition()) - 8] == null) {
				legal_move_positions.add(Game.calculate_index(this.getPosition()) - 16);
			}
			//sol aşağı
			if (Game.calculate_index(this.getPosition()) % 9 >= 2 && !Game.outOfBoard(Game.calculate_index(this.getPosition())-18)
					&& game.getBoard().board[Game.calculate_index(this.getPosition()) - 10] == null) {
				legal_move_positions.add(Game.calculate_index(this.getPosition()) - 20);
			}
		} else {
			if (Game.calculate_index(this.getPosition()) / 9>=6) {
				//sağ aşağı 
				if (Game.calculate_index(this.getPosition()) % 9 <= 6
						&& game.getBoard().board[Game.calculate_index(this.getPosition()) - 8] == null) {
					legal_move_positions.add(Game.calculate_index(this.getPosition()) - 16);
				}
				//sol aşağı
				if (Game.calculate_index(this.getPosition()) % 9 >= 2 && !Game.outOfBoard(Game.calculate_index(this.getPosition())-18)
						&& game.getBoard().board[Game.calculate_index(this.getPosition()) - 10] == null) {
					legal_move_positions.add(Game.calculate_index(this.getPosition()) - 20);
				}
			}
		
			//sol yukarı
			if (Game.calculate_index(this.getPosition()) % 9 >= 2 && !Game.outOfBoard(Game.calculate_index(this.getPosition())+18)
					&& game.getBoard().board[Game.calculate_index(this.getPosition()) + 8] == null ) {
				legal_move_positions.add(Game.calculate_index(this.getPosition()) + 16);
			}
			//sağ yukarı
			if (Game.calculate_index(this.getPosition()) % 9 <= 6  && !Game.outOfBoard(Game.calculate_index(this.getPosition())+18)
					&& game.getBoard().board[Game.calculate_index(this.getPosition()) + 10] == null) {
				legal_move_positions.add(Game.calculate_index(this.getPosition()) + 20);
			}


		}
		for (int i = 0; i < legal_move_positions.size(); i++) {
			if (game.getBoard().board[legal_move_positions.get(i)] != null) {

			
				if (game.getBoard().board[legal_move_positions.get(i)].getColour().equals(getColour())) {
					
					legal_move_positions.remove(i);
					i--;
				}
			}

		}


	}

	
	public ArrayList<Integer> getLegal_move_positions() {
		return legal_move_positions;
	}



	@Override
	public void setColour(String colour) {
		// TODO Auto-generated method stub
		super.setColour(colour);
	}

}
