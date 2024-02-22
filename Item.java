import java.util.ArrayList;

public class Item extends AbstractItem {

	private float point;
	private Game game;
	ArrayList<Integer> legal_move_positions;
	Player player;
	private String position;

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	@Override
	public void move(String destination) throws IllegalMove {
	
		try {
			 //System.out.println(this.getLegal_move_positions());
			// if it is pinned
if (game.legal(this.position, destination)&& game.rightTurn(getPosition())) {
	
			Item item = game.getBoard().board[Game.calculate_index(destination)];
			if (game.getBoard().board[Game.calculate_index(destination)] != null) {
//					System.out.println(this.getPosition());
				game.getBoard().board[Game.calculate_index(this.getPosition())] = null;
//					System.out.println("not what you think it is");
				game.getBoard().board[Game.calculate_index(destination)] = this;
				game.update_all(game.getBoard().board);
				if ((getColour().equals("red")
						&& game.isThereACheckforRed(game.getBoard().board, game.getRedgeneralposition()))
						|| (getColour().equals("black")
								&& game.isThereACheckforBlack(game.getBoard().board, game.getBlackgeneralposition()))
						|| (game.isThereACheckforRed(game.getBoard().board, game.getRedgeneralposition())
								&& game.redcheckcount == 2)
						|| (game.isThereACheckforBlack(game.getBoard().board, game.getBlackgeneralposition())
								&& game.blackcheckcount == 2)|| game.areGeneralsFaceToFace(game.getRedgeneralposition(), game.getBlackgeneralposition()))

				{
					game.setThereACheck(false);
					game.getBoard().board[Game.calculate_index(this.getPosition())] = this;
					game.getBoard().board[Game.calculate_index(destination)] = item;
					item.setPosition(destination);
					this.setPosition(getPosition());

					throw new IllegalMove();

				} else {
					player.puan += item.getPoint();
					item.setPosition("xx");
					this.setPosition(destination);
//					System.out.println("CAPTURE!");
//					System.out.println("Succesfully moved and the move count is" + game.getMove_count());
				}

			} else {
				// if it is pinned
				game.getBoard().board[Game.calculate_index(this.getPosition())] = null;
				game.getBoard().board[Game.calculate_index(destination)] = this;
				game.update_all(game.getBoard().board);
				String olddestin=this.getPosition();
				this.setPosition(destination);
				if ((getColour().equals("red")
						&& game.isThereACheckforRed(game.getBoard().board, game.getRedgeneralposition()))
						|| (getColour().equals("black")
								&& game.isThereACheckforBlack(game.getBoard().board, game.getBlackgeneralposition()))
						|| (game.isThereACheckforRed(game.getBoard().board, game.getRedgeneralposition())
								&& game.blackcheckcount == 2)
						|| (game.isThereACheckforBlack(game.getBoard().board, game.getBlackgeneralposition())
								&& game.redcheckcount == 2)|| game.areGeneralsFaceToFace(game.getRedgeneralposition(), game.getBlackgeneralposition())
						
						)

				{
					this.setPosition(olddestin);
					game.getBoard().board[Game.calculate_index(this.getPosition())] = this;
					game.getBoard().board[Game.calculate_index(destination)] = null;
//					System.out.println("pinned  " + this.getPosition() + this.getColour());
					game.update_all(game.getBoard().board);
					throw new IllegalMove();

				}

				this.setPosition(destination);
				game.update_all(game.getBoard().board);

			}
}else
	throw new IllegalMove();

		} catch (IllegalMove e) {
			// System.out.println("Hatali hamle");
			throw new IllegalMove();

		}
	}

	public float getPoint() {
		return point;
	}

	public void setPoint(float point) {
		this.point = point;
	}

	public ArrayList<Integer> getLegal_move_positions() {
		return legal_move_positions;
	}

	public void setLegal_move_positions(ArrayList<Integer> legal_move_positions) {
		this.legal_move_positions = legal_move_positions;
	}

	public Item(String colour, Game game, Player player) {
		super();
		this.setColour(colour);
		this.game = game;
		this.player = player;
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

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

}
