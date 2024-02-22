import java.util.ArrayList;
import java.util.Iterator;

public class General extends Item {

	private Game game;
	ArrayList<Integer> legal_move_positions = new ArrayList<Integer>();

	Player player;

	public General(String colour, Game game, Player player) {
		super(colour, game, player);
		this.game = game;
		this.player = player;
	}

	public ArrayList<Integer> getLegal_move_positions() {
		return legal_move_positions;
	}

	public void setLegal_move_positions(ArrayList<Integer> legal_move_positions) {
		this.legal_move_positions = legal_move_positions;
	}

	public void update_legal_moves() {
//		System.out.println("general index is"+this.getPosition());
		boolean emptyred = true;
		legal_move_positions.clear();
		if (getColour().equals("red")) {
			if (Game.calculate_index(getPosition()) == 3) {
				legal_move_positions.add(4);
				legal_move_positions.add(12);
			} else if (Game.calculate_index(getPosition()) == 4) {
				legal_move_positions.add(13);
				legal_move_positions.add(3);
				legal_move_positions.add(5);
			} else if (Game.calculate_index(getPosition()) == 5) {
				legal_move_positions.add(4);
				legal_move_positions.add(14);
			} else if (Game.calculate_index(getPosition()) == 12) {
				legal_move_positions.add(3);
				legal_move_positions.add(13);
				legal_move_positions.add(21);
			} else if (Game.calculate_index(getPosition()) == 13) {
				legal_move_positions.add(12);
				legal_move_positions.add(14);
				legal_move_positions.add(4);
				legal_move_positions.add(22);
			} else if (Game.calculate_index(getPosition()) == 14) {
				legal_move_positions.add(5);
				legal_move_positions.add(13);
				legal_move_positions.add(23);

			} else if (Game.calculate_index(getPosition()) == 21) {
				legal_move_positions.add(22);
				legal_move_positions.add(12);

			} else if (Game.calculate_index(getPosition()) == 22) {
				legal_move_positions.add(13);
				legal_move_positions.add(21);
				legal_move_positions.add(23);

			} else if (Game.calculate_index(getPosition()) == 23) {
				legal_move_positions.add(22);
				legal_move_positions.add(14);
			}

			for (int i = 0; i < legal_move_positions.size(); i++) {
				if (game.getBoard().board[legal_move_positions.get(i)] != null) {

					if (game.getBoard().board[legal_move_positions.get(i)].getColour().equals(getColour())
							) {
					
						legal_move_positions.remove(legal_move_positions.get(i));
						i--;
					}
				}

			}
			for (int i = 9; Game.calculate_index(this.getPosition()) - 1 + i < 90; i += 9) {
				if (game.getBoard().board[Game.calculate_index(this.getPosition()) - 1 + i] != null
						&& !(game.getBoard().board[Game.calculate_index(this.getPosition()) + i
								- 1] instanceof General)) {

					emptyred = false;
					break;
				}else if(game.getBoard().board[Game.calculate_index(this.getPosition()) - 1 + i] != null
						&& (game.getBoard().board[Game.calculate_index(this.getPosition()) + i
													- 1] instanceof General)) {
					break;
				}
			}
			if (emptyred) {
				legal_move_positions.remove((Integer) (Game.calculate_index(this.getPosition()) - 1));
			}
			emptyred = true;
			for (int i = 9; Game.calculate_index(this.getPosition()) + 1 + i < 90; i += 9) {
				if (game.getBoard().board[Game.calculate_index(this.getPosition()) + 1 + i] != null
						&& !(game.getBoard().board[Game.calculate_index(this.getPosition()) + i
								+ 1] instanceof General)) {

					emptyred = false;
					break;
				}else if(game.getBoard().board[Game.calculate_index(this.getPosition()) + 1 + i] != null
						&& (game.getBoard().board[Game.calculate_index(this.getPosition()) + i
													+ 1] instanceof General)) {
					break;
				}
			}
			if (emptyred) {
				legal_move_positions.remove((Integer) (Game.calculate_index(this.getPosition()) + 1));
				// System.out.println("+1 removed for red");
			}

//			for (int i = 0; i < legal_move_positions.size(); i++) {
//				if (game.getBoard().board[(int) legal_move_positions.get(i)] != null) {
//					Item[] newBoard = game.getBoard().board.clone();
//					newBoard[legal_move_positions.get(i)] = null;
//					if (game.isThereACheckforRed(newBoard, legal_move_positions.get(i))) {
//						legal_move_positions.remove(legal_move_positions.get(i));
//					}
//
//				} else {
//					if (game.isThereACheckforRed(game.getBoard().board, legal_move_positions.get(i))) {
//						legal_move_positions.remove(legal_move_positions.get(i));
//					}
//				}
//
//			}
			for (int i = 0; i < game.getBoard().board.length; i++) {
				if (game.getBoard().board[i] != null && (game.getBoard().board[i] instanceof Cannon)
						&& !game.getBoard().board[i].getColour().equals(getColour())) {
					Cannon cannon = (Cannon) game.getBoard().board[i];
					for (int j = 0; j < cannon.check_positions.size(); j++) {
						if (legal_move_positions.contains(cannon.check_positions.get(j))) {
							legal_move_positions.remove((Integer) cannon.check_positions.get(j));
						}
					}
				}
			}
//			for (int j = 0; j < getLegal_move_positions().size(); j++) {
//				int destination = getLegal_move_positions().get(j);
//				game.getBoard().board[Game.calculate_index(this.getPosition())] = null;
//
//				if (game.isThereACheckforRed(game.getBoard().board, getLegal_move_positions().get(j))) {
////						System.out.println(getBoard().board[i].getLegal_move_positions());
//					getLegal_move_positions().remove(destination);
//					if(j!=0)
//					j--;
//
//				}
//
//		
//			}
//			for (int i = getLegal_move_positions().size() - 1; i >= 0; --i) {
//				int destination = getLegal_move_positions().get(i);
//				game.getBoard().board[Game.calculate_index(this.getPosition())] = null;
//
//				if (game.isThereACheckforRed(game.getBoard().board, getLegal_move_positions().get(i))) {
////						System.out.println(getBoard().board[i].getLegal_move_positions());
//					getLegal_move_positions().remove((Integer) destination);
//
//				}
//			}
//			game.getBoard().board[Game.calculate_index(this.getPosition())] = this;
			

		} else if (getColour().equals("black")) {
			boolean emptyblack = true;
			if (Game.calculate_index(getPosition()) == 66) {
				legal_move_positions.add(75);
				legal_move_positions.add(67);
			} else if (Game.calculate_index(getPosition()) == 67) {
				legal_move_positions.add(66);
				legal_move_positions.add(76);
				legal_move_positions.add(68);
			} else if (Game.calculate_index(getPosition()) == 68) {
				legal_move_positions.add(67);
				legal_move_positions.add(77);
			} else if (Game.calculate_index(getPosition()) == 75) {
				legal_move_positions.add(84);
				legal_move_positions.add(76);
				legal_move_positions.add(66);
			} else if (Game.calculate_index(getPosition()) == 76) {
				legal_move_positions.add(75);
				legal_move_positions.add(85);
				legal_move_positions.add(77);
				legal_move_positions.add(67);
			} else if (Game.calculate_index(getPosition()) == 77) {
				legal_move_positions.add(76);
				legal_move_positions.add(86);
				legal_move_positions.add(68);

			} else if (Game.calculate_index(getPosition()) == 84) {
				legal_move_positions.add(75);
				legal_move_positions.add(85);

			} else if (Game.calculate_index(getPosition()) == 85) {
				legal_move_positions.add(84);
				legal_move_positions.add(76);
				legal_move_positions.add(86);

			} else if (Game.calculate_index(getPosition()) == 86) {
				legal_move_positions.add(77);
				legal_move_positions.add(85);
			}

			for (int i = 0; i < legal_move_positions.size(); i++) {
				if (game.getBoard().board[legal_move_positions.get(i)] != null) {

					if (game.getBoard().board[legal_move_positions.get(i)].getColour().equals(getColour())) {
//						System.out.println("removed"+legal_move_positions.get(i));
						legal_move_positions.remove(legal_move_positions.get(i));
						i--;
					}
				}

			}
//			System.out.println(this.getPosition()+"--------");
			for (int i = 9; Game.calculate_index(this.getPosition()) - 1 - i > 0; i += 9) {
				if (game.getBoard().board[Game.calculate_index(this.getPosition()) - 1 - i] != null
						&& !(game.getBoard().board[Game.calculate_index(this.getPosition()) - i
								- 1] instanceof General)) {
//					 System.out.println(Game.calculate_index(this.getPosition()) - 1 - i);
					emptyblack = false;
					break;
				}else if(game.getBoard().board[Game.calculate_index(this.getPosition()) - 1 - i] != null
						&& (game.getBoard().board[Game.calculate_index(this.getPosition()) - i
													- 1] instanceof General)) {
					break;
				}
			}
			if (emptyblack) {
//				System.out.println("-1 removed");
				legal_move_positions.remove((Integer) (Game.calculate_index(this.getPosition()) - 1));
			}
			emptyblack = true;
			for (int i = 9; Game.calculate_index(this.getPosition()) + 1 - i > 0; i += 9) {
				if (game.getBoard().board[Game.calculate_index(this.getPosition()) + 1 - i] != null
						&& !(game.getBoard().board[Game.calculate_index(this.getPosition()) - i
								+ 1] instanceof General)) {

					emptyblack = false;
					break;
				}else if(game.getBoard().board[Game.calculate_index(this.getPosition()) + 1 - i] != null
						&& (game.getBoard().board[Game.calculate_index(this.getPosition()) - i
													+ 1] instanceof General)) {
					break;
				}
			}
			if (emptyblack) {
				legal_move_positions.remove((Integer) (Game.calculate_index(this.getPosition()) + 1));
//				System.out.println("+1 black removed");
			}

			for (int i = 0; i < game.getBoard().board.length; i++) {
				if (game.getBoard().board[i] != null && (game.getBoard().board[i] instanceof Cannon)
						&& !game.getBoard().board[i].getColour().equals(getColour())) {
					Cannon cannon = (Cannon) game.getBoard().board[i];
					for (int j = 0; j < cannon.check_positions.size(); j++) {
						if (legal_move_positions.contains(cannon.check_positions.get(j))) {
	//						System.out.println("removed"+legal_move_positions.get(i));
							legal_move_positions.remove((Integer) cannon.check_positions.get(j));
						}
					}
				}
			}


		}
	}


	@Override
	public void move(String destination) throws IllegalMove {

		try {

			if (game.legal(this.getPosition(), destination) && game.rightTurn(getPosition())) {

				Item item = game.getBoard().board[Game.calculate_index(destination)];
				if (game.getBoard().board[Game.calculate_index(destination)] != null) {
//					System.out.println(this.getPosition());
					game.getBoard().board[Game.calculate_index(this.getPosition())] = null;

					game.getBoard().board[Game.calculate_index(destination)] = this;
					game.update_all(game.getBoard().board);
					if (this instanceof General) {
						if ((getColour().equals("red")
								&& game.isThereACheckforRed(game.getBoard().board, Game.calculate_index(destination)))
								|| (getColour().equals("black") && game.isThereACheckforBlack(game.getBoard().board,
										Game.calculate_index(destination)))
								|| (game.isThereACheckforRed(game.getBoard().board, game.getRedgeneralposition())
										&& game.redcheckcount == 2)
								|| (game.isThereACheckforBlack(game.getBoard().board, game.getBlackgeneralposition())
										&& game.blackcheckcount == 2)
								|| game.areGeneralsFaceToFace(game.getRedgeneralposition(),
										game.getBlackgeneralposition()))

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
//							System.out.println("CAPTURE!");
//							System.out.println("Succesfully moved and the move count is" + game.getMove_count());
						}

					}

				} else {
					// if it is pinned

					game.getBoard().board[Game.calculate_index(this.getPosition())] = null;
					game.getBoard().board[Game.calculate_index(destination)] = this;
//					game.getBoard().print();
//					this.setPosition(destination);
					
//					game.getBoard().print();
					game.update_all(game.getBoard().board);
//					System.out.println("---------------------------------");
//					game.getBoard().print();
					
						if ((this.getColour().equals("red")
								&& game.isThereACheckforRed(game.getBoard().board, Game.calculate_index(destination)))
								|| (getColour().equals("black") && game.isThereACheckforBlack(game.getBoard().board,
										Game.calculate_index(destination)))){
							
							game.getBoard().board[Game.calculate_index(this.getPosition())] = this;
							game.getBoard().board[Game.calculate_index(destination)] = null;
//							System.out.println("pinned  " + this.getPosition() + this.getColour());
							game.update_all(game.getBoard().board);
							throw new IllegalMove();

						}
	
					if (game.redcheckcount == 2 && this.getColour().equals("red"))
						game.redcheckcount = 0;
					if (game.blackcheckcount == 2 && this.getColour().equals("black"))
						game.blackcheckcount = 0;
					this.setPosition(destination);
//					System.out.println("Succesfully moved and the move count is" + game.getMove_count());

				}
//				game.getBoard().print();
				
			} else
				throw new IllegalMove();

		} catch (IllegalMove e) {
			
			throw new IllegalMove();

		}
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

	@Override
	public Game getGame() {
		// TODO Auto-generated method stub
		return super.getGame();
	}

	@Override
	public void setGame(Game game) {
		// TODO Auto-generated method stub
		super.setGame(game);
	}

}
