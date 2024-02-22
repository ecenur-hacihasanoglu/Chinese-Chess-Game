import java.util.ArrayList;

public class Horse extends Item {
	
	private Game game;
	ArrayList<Integer> legal_move_positions = new ArrayList<Integer>();

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

	public Horse(String colour, Game game, Player player) {
		super(colour, game, player);
		this.setColour(colour);
		this.game = game;
		this.player = player;
		this.setPoint(4.0f);
		
	}

	public void update_legal_moves() {
		Item[] givenboard = this.game.getBoard().board;
		legal_move_positions.clear();

		if (!Game.outOfBoard(Game.calculate_index(getPosition()) + 18)
				&& (givenboard[Game.calculate_index(this.getPosition()) + 9] == null)) {

			if ((Game.calculate_index(this.getPosition())) % 9 != 0)
				legal_move_positions.add(Game.calculate_index(this.getPosition()) + 17);
			if ((Game.calculate_index(this.getPosition())) % 9 != 8)
				legal_move_positions.add(Game.calculate_index(this.getPosition()) + 19);
		}
		if (!Game.outOfBoard(Game.calculate_index(this.getPosition()) - 18)
				&& (givenboard[Game.calculate_index(this.getPosition()) - 9] == null)) {

			if ((Game.calculate_index(this.getPosition())) % 9 != 0)
				legal_move_positions.add(Game.calculate_index(this.getPosition()) - 17);
			if ((Game.calculate_index(this.getPosition())) % 9 != 8)
				legal_move_positions.add(Game.calculate_index(this.getPosition()) - 19);
		}
		if ((Game.calculate_index(this.getPosition())) % 9 != 8
				&& (givenboard[Game.calculate_index(this.getPosition()) + 1] == null)) {

			if (!Game.outOfBoard(Game.calculate_index(this.getPosition()) + 11))
				legal_move_positions.add(Game.calculate_index(this.getPosition()) + 11);

			if (!Game.outOfBoard(Game.calculate_index(this.getPosition()) - 18))
				legal_move_positions.add(Game.calculate_index(this.getPosition()) - 7);
		}
		if ((Game.calculate_index(this.getPosition())) % 9 != 1
				&& (givenboard[Game.calculate_index(this.getPosition()) - 1] == null)) {

			if (!Game.outOfBoard(Game.calculate_index(this.getPosition()) + 18))
				legal_move_positions.add(Game.calculate_index(this.getPosition()) + 7);
			if (!Game.outOfBoard(Game.calculate_index(this.getPosition()) - 18))
				legal_move_positions.add(Game.calculate_index(this.getPosition()) - 11);
		}

		for (int i = 0; i < legal_move_positions.size(); i++) {
			if (game.getBoard().board[legal_move_positions.get(i)] != null) {

				if (game.getBoard().board[legal_move_positions.get(i)].getColour().equals(getColour())) {

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

	public ArrayList<Integer> getLegal_move_positions() {
		return legal_move_positions;
	}



}
