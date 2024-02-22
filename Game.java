import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Game extends AbstractGame {
	Board board = new Board();
	private int move_count = 1;
	private int redgeneralposition;
	public int redcheckcount = 0;
	public int blackcheckcount = 0;
	private int blackgeneralposition;
	private boolean isItOver = false;
	private boolean isThereACheck;
	ArrayList<Integer> checkingpieces = new ArrayList<Integer>();
	Player red;
	Player black;


	public int getRedgeneralposition() {
		return redgeneralposition;
	}

	public void setRedgeneralposition(int redgeneralposition) {
		this.redgeneralposition = redgeneralposition;
	}

	public int getBlackgeneralposition() {
		return blackgeneralposition;
	}

	public void setBlackgeneralposition(int blackgeneralposition) {
		this.blackgeneralposition = blackgeneralposition;
	}

	public boolean areGeneralsFaceToFace(int redgnerealpos, int blackgeneralpos) {
//		System.out.println(redgnerealpos+"----"+blackgeneralpos);
		boolean facetoface = true;
		if (redgnerealpos % 9 != redgnerealpos % 9) {
//			System.out.println("position is wrong");
			return false;
		} else {
			int row = redgeneralposition / 9;
			int col = redgnerealpos % 9;
			int i = 9;
			for (i = 9; i < 90; i += 9) {
//				System.out.println(row*9+i+col);
				if (getBoard().board[row * 9 + i + col] != null
						&& !(getBoard().board[row * 9 + i + col] instanceof General)) {
//					System.out.println("face to face ?  "+facetoface);
					facetoface = false;
					break;
				}

			}
			if (i > 80)
				return false;
		}

		return facetoface;

	}

	@Override
	void play(String from, String to) {

		if (getBoard().board[calculate_index(from)] != null && rightTurn(from)) {
//		System.out.println(redturn);
			try {
				if (!isItOver && !outOfBoard(from)) {
//				System.out.println(isThereACheck+"CHECK EXISTS?");
					String lastcolour = "";
					setThereACheck(false);
					update_all(getBoard().board);
					if (this.getBoard().board[calculate_index(from)] instanceof Soldier) {
						Soldier soldier = (Soldier) this.getBoard().board[calculate_index(from)];
						try {
							soldier.move(to);
							move_count++;
							lastcolour = soldier.getColour();
						} catch (IllegalMove e) {
							throw new IllegalMove();
						}
					} else if (this.getBoard().board[calculate_index(from)] instanceof Chariot) {
						Chariot chariot = (Chariot) this.getBoard().board[calculate_index(from)];
						try {
							chariot.move(to);
							move_count++;
							lastcolour = chariot.getColour();
						} catch (IllegalMove e) {
							throw new IllegalMove();
						}
					} else if (this.getBoard().board[calculate_index(from)] instanceof Horse) {
						Horse horse = (Horse) this.getBoard().board[calculate_index(from)];
						try {
							horse.move(to);
							move_count++;
							lastcolour = horse.getColour();
						} catch (IllegalMove e) {
							throw new IllegalMove();
						}
					} else if (this.getBoard().board[calculate_index(from)] instanceof Cannon) {
						Cannon cannon = (Cannon) this.getBoard().board[calculate_index(from)];

						try {
							cannon.move(to);
							move_count++;
							lastcolour = cannon.getColour();
						} catch (IllegalMove e) {
							throw new IllegalMove();
						}
//				System.out.println(cannon.legal_move_positions);
					} else if (this.getBoard().board[calculate_index(from)] instanceof General) {
						General general = (General) this.getBoard().board[calculate_index(from)];
						try {
							general.move(to);
							move_count++;
							lastcolour = general.getColour();

							if (general.getColour().equals("red"))
								setRedgeneralposition(calculate_index(to));
							else
								setBlackgeneralposition(calculate_index(to));
//						System.out.println(getBlackgeneralposition());
						} catch (IllegalMove e) {
							throw new IllegalMove();
						}
					} else if (this.getBoard().board[calculate_index(from)] instanceof Advisor) {
						Advisor advisor = (Advisor) this.getBoard().board[calculate_index(from)];
						try {
							advisor.move(to);
							move_count++;
							lastcolour = advisor.getColour();
						} catch (IllegalMove e) {
							throw new IllegalMove();
						}
					} else if (this.getBoard().board[calculate_index(from)] instanceof Elephant) {
						Elephant elephant = (Elephant) this.getBoard().board[calculate_index(from)];
						try {
							elephant.move(to);
							move_count++;
							lastcolour = elephant.getColour();
						} catch (IllegalMove e) {
							throw new IllegalMove();
						}
					}
					update_all(getBoard().board);
//				System.out.println(this.getRedgeneralposition());
					if (this.move_count % 2 == 0
							&& isThereACheckforBlack(getBoard().board, this.blackgeneralposition)) {
						this.redcheckcount++;
						setThereACheck(true);
//					System.out.println("check for black");
						isItMateforBlack();
					} else if (this.move_count % 2 == 1
							&& isThereACheckforRed(getBoard().board, getRedgeneralposition())) {
						this.blackcheckcount++;
						setThereACheck(true);
//					System.out.println("check for red");
						isItMateforRed();
					}

				}
			} catch (IllegalMove e) {
				System.out.println("hatali hareket");
			}

		} else
			System.out.println("hatali hareket");
	}

	public static String calculateStringIndex(int num) {
		int row = num / 9;
		int col = num % 9;
		char rowChar = (char) (row + 'a');
		int colNum = col + 1;
		return rowChar + Integer.toString(colNum);
	}

	private boolean isItMateforRed() {

		for (int i = 0; i < getBoard().board.length; i++) {
			if (getBoard().board[i] != null && getBoard().board[i].getColour().equals("red")) {
				for (int j = 0; j < getBoard().board[i].getLegal_move_positions().size(); j++) {
					try {
						int destination = getBoard().board[i].getLegal_move_positions().get(j);
						if (getBoard().board[destination] == null) {

							getBoard().board[i].move(Game.calculateStringIndex(destination));
							getBoard().board[i] = getBoard().board[destination];
							getBoard().board[i].setPosition(calculateStringIndex(i));
							getBoard().board[destination] = null;

							return false;

						} else {
							Item item = getBoard().board[destination];
							getBoard().board[i].move(Game.calculateStringIndex(destination));

							getBoard().board[i] = getBoard().board[destination];

							item.setPosition(Game.calculateStringIndex(destination));
							getBoard().board[destination] = item;
							return false;
						}

					} catch (IllegalMove e) {

					}
				}
			}

		}
//		if (((General) this.getBoard().board[getRedgeneralposition()]).getLegal_move_positions().isEmpty()) {
		this.isItOver = true;
		System.out.println("ŞAH MAT! " + black.name + " oyunu kazandı. " + black.name + "'in puanı: " + black.puan
				+ ", " + red.name + "'nin puanı: " + red.puan);
		return true;

	}

	private boolean isItMateforBlack() {

		for (int i = 0; i < getBoard().board.length; i++) {
			if (getBoard().board[i] != null && getBoard().board[i].getColour().equals("black")) {
				for (int j = 0; j < getBoard().board[i].getLegal_move_positions().size(); j++) {
					try {
						int destination = getBoard().board[i].getLegal_move_positions().get(j);
						if (getBoard().board[destination] == null) {

//								System.out.println(getBoard().board[i].getLegal_move_positions());
							getBoard().board[i].move(Game.calculateStringIndex(destination));
							getBoard().board[i] = getBoard().board[destination];
							getBoard().board[i].setPosition(calculateStringIndex(i));
							getBoard().board[destination] = null;

							return false;

						} else {
							Item item = getBoard().board[destination];
							getBoard().board[i].move(Game.calculateStringIndex(destination));
							getBoard().board[i] = getBoard().board[destination];

							item.setPosition(Game.calculateStringIndex(destination));
							getBoard().board[destination] = item;
							return false;
						}

					} catch (IllegalMove e) {

					}

				}

			}
		}
		if (getBoard().board[getBlackgeneralposition()].getLegal_move_positions().isEmpty()) {
			this.isItOver = true;
			System.out.println("ŞAH MAT! " + red.name + " oyunu kazandı. " + red.name + "'in puanı: " + red.puan + ", "
					+ black.name + "'nin puanı: " + black.puan);
			return true;
		}
		return false;

	}

	public Game(String player1, String player2) {
		this.red = new Player(player1);
		this.black = new Player(player2);
		this.board = new Board();

		for (int i = 0; i < board.board.length; i++) {
			board.board[i] = null;
		}
		Horse redhorse1 = new Horse("red", this, red);
		redhorse1.setPosition("a2");
		board.board[calculate_index("a2")] = redhorse1;
		this.getBoard().getItems()[0] = redhorse1;

		Horse redhorse2 = new Horse("red", this, red);
		redhorse2.setPosition("a8");
		board.board[calculate_index("a8")] = redhorse2;
		this.getBoard().getItems()[1] = redhorse2;

		Horse blackhorse1 = new Horse("black", this, black);
		blackhorse1.setPosition("j2");
		board.board[calculate_index("j2")] = blackhorse1;
		this.getBoard().getItems()[2] = blackhorse1;

		Horse blackhorse2 = new Horse("black", this, black);
		blackhorse2.setPosition("j8");
		board.board[calculate_index("j8")] = blackhorse2;
		this.getBoard().getItems()[3] = blackhorse2;

		Soldier redsoldier1 = new Soldier("red", this, red);
		redsoldier1.setPosition("d1");
		board.board[calculate_index("d1")] = redsoldier1;
		this.getBoard().getItems()[4] = redsoldier1;

		Soldier redsoldier2 = new Soldier("red", this, red);
		redsoldier2.setPosition("d3");
		board.board[calculate_index("d3")] = redsoldier2;
		this.getBoard().getItems()[5] = redsoldier2;

		Soldier redsoldier3 = new Soldier("red", this, red);
		redsoldier3.setPosition("d5");
		board.board[calculate_index("d5")] = redsoldier3;
		this.getBoard().getItems()[6] = redsoldier3;

		Soldier redsoldier4 = new Soldier("red", this, red);
		redsoldier4.setPosition("d7");
		board.board[calculate_index("d7")] = redsoldier4;
		this.getBoard().getItems()[7] = redsoldier4;

		Soldier redsoldier5 = new Soldier("red", this, red);
		redsoldier5.setPosition("d9");
		board.board[calculate_index("d9")] = redsoldier5;
		this.getBoard().getItems()[8] = redsoldier5;

		Soldier blacksoldier1 = new Soldier("black", this, black);
		blacksoldier1.setPosition("g1");
		board.board[calculate_index("g1")] = blacksoldier1;
		this.getBoard().getItems()[9] = blacksoldier1;

		Soldier blacksoldier2 = new Soldier("black", this, black);
		blacksoldier2.setPosition("g3");
		board.board[calculate_index("g3")] = blacksoldier2;
		this.getBoard().getItems()[10] = blacksoldier2;

		Soldier blacksoldier3 = new Soldier("black", this, black);
		blacksoldier3.setPosition("g5");
		board.board[calculate_index("g5")] = blacksoldier3;
		this.getBoard().getItems()[11] = blacksoldier3;

		Soldier blacksoldier4 = new Soldier("black", this, black);
		blacksoldier4.setPosition("g7");
		board.board[calculate_index("g7")] = blacksoldier4;
		this.getBoard().getItems()[12] = blacksoldier4;

		Soldier blacksoldier5 = new Soldier("black", this, black);
		blacksoldier5.setPosition("g9");
		board.board[calculate_index("g9")] = blacksoldier5;
		this.getBoard().getItems()[13] = blacksoldier5;

		General redgeneral = new General("red", this, red);
		redgeneral.setPosition("a5");
		setRedgeneralposition(calculate_index("a5"));
		board.board[calculate_index("a5")] = redgeneral;
		this.getBoard().getItems()[14] = redgeneral;

		General blackgeneral = new General("black", this, black);
		blackgeneral.setPosition("j5");
		setBlackgeneralposition(calculate_index("j5"));
		board.board[calculate_index("j5")] = blackgeneral;
		this.getBoard().getItems()[15] = blackgeneral;

		Cannon redcannon1 = new Cannon("red", this, red);
		redcannon1.setPosition("c2");
		board.board[calculate_index("c2")] = redcannon1;
		this.getBoard().getItems()[16] = redcannon1;

		Cannon redcannon2 = new Cannon("red", this, red);
		redcannon2.setPosition("c8");
		board.board[calculate_index("c8")] = redcannon2;
		this.getBoard().getItems()[17] = redcannon2;

		Cannon blackcannon1 = new Cannon("black", this, black);
		blackcannon1.setPosition("h2");
		board.board[calculate_index("h2")] = blackcannon1;
		this.getBoard().getItems()[18] = blackcannon1;

		Cannon blackcannon2 = new Cannon("black", this, black);
		blackcannon2.setPosition("h8");
		board.board[calculate_index("h8")] = blackcannon2;
		this.getBoard().getItems()[19] = blackcannon2;

		Chariot redchariot1 = new Chariot("red", this, red);
		board.board[calculate_index("a1")] = redchariot1;
		redchariot1.setPosition("a1");
		this.getBoard().getItems()[20] = redchariot1;

		Chariot redchariot2 = new Chariot("red", this, red);
		board.board[calculate_index("a9")] = redchariot2;
		redchariot2.setPosition("a9");
		this.getBoard().getItems()[21] = redchariot2;

		Chariot blackcheriot1 = new Chariot("black", this, black);
		board.board[calculate_index("j1")] = blackcheriot1;
		blackcheriot1.setPosition("j1");
		this.getBoard().getItems()[22] = blackcheriot1;

		Chariot blackcheriot2 = new Chariot("black", this, black);
		board.board[calculate_index("j9")] = blackcheriot2;
		blackcheriot2.setPosition("j9");
		this.getBoard().getItems()[23] = blackcheriot2;

		Advisor redadivsor1 = new Advisor("red", this, red);
		board.board[calculate_index("a4")] = redadivsor1;
		redadivsor1.setPosition("a4");
		this.getBoard().getItems()[24] = redadivsor1;

		Advisor redadivsor2 = new Advisor("red", this, red);
		board.board[calculate_index("a6")] = redadivsor2;
		redadivsor2.setPosition("a6");
		this.getBoard().getItems()[25] = redadivsor2;

		Advisor blackadivsor1 = new Advisor("black", this, black);
		board.board[calculate_index("j4")] = blackadivsor1;
		blackadivsor1.setPosition("j4");
		this.getBoard().getItems()[26] = blackadivsor1;

		Advisor blackadivsor2 = new Advisor("black", this, black);
		board.board[calculate_index("j6")] = blackadivsor2;
		blackadivsor2.setPosition("j6");
		this.getBoard().getItems()[27] = blackadivsor2;

		Elephant redelephant1 = new Elephant("red", this, red);
		board.board[calculate_index("a3")] = redelephant1;
		redelephant1.setPosition("a3");
		this.getBoard().getItems()[28] = redelephant1;

		Elephant redelephant2 = new Elephant("red", this, red);
		board.board[calculate_index("a7")] = redelephant2;
		redelephant2.setPosition("a7");
		this.getBoard().getItems()[29] = redelephant2;

		Elephant blackelephant1 = new Elephant("black", this, black);
		board.board[calculate_index("j3")] = blackelephant1;
		blackelephant1.setPosition("j3");
		this.getBoard().getItems()[30] = blackelephant1;

		Elephant blackelephant2 = new Elephant("black", this, black);
		board.board[calculate_index("j7")] = blackelephant2;
		blackelephant2.setPosition("j7");
		this.getBoard().getItems()[31] = blackelephant2;

		this.update_all(getBoard().board);

	}

	public static int calculate_index(String s_index) {
		int row;
		int col;
		try {
			row = s_index.charAt(0) - 'a';
			col = Integer.parseInt(s_index.charAt(1) + "") - 1;
		} catch (Exception e) {
			return -1;
		}

		return (row) * 9 + col;

	}

	public static boolean outOfBoard(String s_index) {
		if (s_index.length() > 2 || s_index.length() < 2)
			return true;
		else if (calculate_index(s_index) < 0 || calculate_index(s_index) > 89)
			return true;
		else if (s_index.charAt(0) < 97 || s_index.charAt(0) > 106)
			return false;
		else if (s_index.charAt(0) < 49 || s_index.charAt(0) > 57)
			return false;
		else
			return false;

	}

	public static boolean outOfBoard(int index) {

		if (index > 0 && index < 90)
			return false;
		else
			return true;

	}

	public boolean isThereACheckforBlack(Item[] board, int general_index) {
		ArrayList<Integer> possibleChecks = new ArrayList<Integer>();
		for (Item item : board) {
			if (item instanceof Soldier) {
				Soldier soldier = (Soldier) item;
				if (soldier.getColour().equals("red")) {
					if (soldier.getLegal_move_positions().contains(general_index)) {
//						System.out.println(" red soldier checked");
						checkingpieces.add(calculate_index(soldier.getPosition()));
						// System.out.println(general_index);
						// System.out.println(soldier.getLegal_move_positions());
						possibleChecks.addAll(soldier.legal_move_positions);
					}
				}
			} else if (item instanceof Horse) {
				Horse horse = (Horse) item;
				if (horse.getColour().equals("red")) {
					if (horse.getLegal_move_positions().contains(general_index)) {
						checkingpieces.add(calculate_index(horse.getPosition()));
//						System.out.println("red horse checked");
						possibleChecks.addAll(horse.legal_move_positions);
					}
				}
			} else if (item instanceof Chariot) {
				Chariot chariot = (Chariot) item;
				if (chariot.getColour().equals("red")) {
					if (chariot.getLegal_move_positions().contains(general_index)) {
						checkingpieces.add(calculate_index(chariot.getPosition()));
//						System.out.println("red chariot checked");
//						System.out.println(chariot.getLegal_move_positions());

						possibleChecks.addAll(chariot.getLegal_move_positions());
					}
				}
			} else if (item instanceof Cannon) {
				Cannon cannon = (Cannon) item;
				if (cannon.getColour().equals("red")) {
					if (!cannon.check_positions.isEmpty() && cannon.check_positions.contains(general_index)) {
//						System.out.println("red cannon checked");
						checkingpieces.add(calculate_index(cannon.getPosition()));
//						System.out.println("here---->"+cannon.getLegal_move_positions());
//						System.out.println(general_index);
						possibleChecks.addAll(cannon.check_positions);
					}
				}
			}
		}
		if (possibleChecks.contains(general_index)) {
//			System.out.println("black is checked");
			setThereACheck(true);
			return true;
		} else {
			setThereACheck(false);
			return false;
		}
	}

	public boolean isThereACheckforRed(Item[] board, int general_index) {
		// System.out.println(general_index+" general index");
		ArrayList<Integer> possibleChecks = new ArrayList<Integer>();
		for (Item item : board) {

			if (item instanceof Soldier) {
				Soldier soldier = (Soldier) item;
				if (soldier.getColour().equals("black")) {
					if (soldier.getLegal_move_positions().contains(general_index)) {
						checkingpieces.add(calculate_index(soldier.getPosition()));
//						System.out.println("soldier checked");
						possibleChecks.addAll(soldier.legal_move_positions);
					}
				}
			} else if (item instanceof Horse) {
				Horse horse = (Horse) item;
				if (horse.getColour().equals("black")) {
					if (horse.getLegal_move_positions().contains(general_index)) {
						checkingpieces.add(calculate_index(horse.getPosition()));
//						System.out.println("horse checked");
						possibleChecks.addAll(horse.legal_move_positions);
					}
				}
			} else if (item instanceof Chariot) {
				Chariot chariot = (Chariot) item;
//				System.out.println("these "+chariot.getPosition()+" "+chariot.getLegal_move_positions());
//				System.out.println("these "+general_index);
				if (chariot.getColour().equals("black")) {
					if (chariot.getLegal_move_positions().contains(general_index)) {
						checkingpieces.add(calculate_index(chariot.getPosition()));
//						System.out.println("chariot checked");
						possibleChecks.addAll(chariot.getLegal_move_positions());
					}
				}
			} else if (item instanceof Cannon) {
				Cannon cannon = (Cannon) item;
				if (cannon.getColour().equals("black")) {
					// System.out.println(cannon.legal_move_positions);
					if (cannon.check_positions.contains(general_index)) {
						checkingpieces.add(calculate_index(cannon.getPosition()));
//						System.out.println("cannon checked");
						possibleChecks.addAll(cannon.check_positions);
					}

				}
			}
		}
		if (possibleChecks.contains(general_index)) {
//			System.out.println("red is checked");

			return true;
		} else {
//		System.out.println("I dont think red is checked");

			return false;
		}
	}

	public void update_all(Item[] myboard) {
		for (Item item : myboard) {
			if (item instanceof Soldier) {
				Soldier soldier = (Soldier) item;
				soldier.update_legal_moves();

			} else if (item instanceof Horse) {
				Horse horse = (Horse) item;
				horse.update_legal_moves();
			} else if (item instanceof Chariot) {
				Chariot chariot = (Chariot) item;
				chariot.update_legal_moves();

			} else if (item instanceof Cannon) {
				Cannon cannon = (Cannon) item;
				cannon.update_legal_moves();
			} else if (item instanceof Elephant) {
				Elephant elephant = (Elephant) item;
				elephant.update_legal_moves();
			} else if (item instanceof General) {
				General chariot = (General) item;
				chariot.update_legal_moves();

			} else if (item instanceof Advisor) {
				Advisor advisor = (Advisor) item;
				advisor.update_legal_moves();
			}
		}

	}

	public boolean rightTurn(String from) {
		if ((getMove_count() % 2 == 0 && this.getBoard().board[calculate_index(from)].getColour().equals("black"))
				|| (getMove_count() % 2 == 1
						&& this.getBoard().board[calculate_index(from)].getColour().equals("red"))) {
//			System.out.println("rightturn");
			return true;
		} else {
//			System.out.println("wrong turn");
			return false;
		}
	}

	public int getMove_count() {
		return move_count;
	}

	public void setMove_count(int move_count) {
		this.move_count = move_count;
	}

	public boolean legal(String from, String to) throws IllegalMove {
		if (outOfBoard(to) || outOfBoard(from)) {

			return false;
		}
		if (this.getBoard().board[calculate_index(from)] == null) {

			return false;
		}

		if (this.getBoard().board[calculate_index(from)] instanceof Horse) {
			Horse horse = (Horse) this.getBoard().board[calculate_index(from)];

			if (horse.legal_move_positions.contains(calculate_index(to))) {

				if (!(this.getBoard().board[calculate_index(to)] == null)) {
					if (this.getBoard().board[calculate_index(to)].getColour()
							.equals(this.getBoard().board[calculate_index(from)].getColour())) {

						return false;
					}
				}
//				System.out.println("horse legal döndü");
				return true;
			}
		} else if (this.getBoard().board[calculate_index(from)] instanceof Soldier) {
			Soldier soldier = (Soldier) this.getBoard().board[calculate_index(from)];
			if (soldier.legal_move_positions.contains(calculate_index(to)) && this.rightTurn(from)) {
				if (!(this.getBoard().board[calculate_index(to)] == null)) {
					if (this.getBoard().board[calculate_index(to)].getColour()
							.equals(this.getBoard().board[calculate_index(from)].getColour())) {
						return false;
					}
				}
//				System.out.println("soldier legal döndü");
				return true;
			}
		} else if (this.getBoard().board[calculate_index(from)] instanceof Cannon) {
			Cannon cannon = (Cannon) this.getBoard().board[calculate_index(from)];

			if (cannon.legal_move_positions.contains(calculate_index(to))) {

				if (!(this.getBoard().board[calculate_index(to)] == null)) {
					if (this.getBoard().board[calculate_index(to)].getColour()
							.equals(this.getBoard().board[calculate_index(from)].getColour())) {
						return false;
					}
				}
//				System.out.println("cannon legal döndü");
				return true;
			}
		} else if (this.getBoard().board[calculate_index(from)] instanceof Chariot) {
			Chariot chariot = (Chariot) this.getBoard().board[calculate_index(from)];
			// System.out.println(chariot.legal_move_positions);
			if (chariot.legal_move_positions.contains(calculate_index(to))) {
//				System.out.println("it does contain");
				if (!(this.getBoard().board[calculate_index(to)] == null)) {
					if (this.getBoard().board[calculate_index(to)].getColour()
							.equals(this.getBoard().board[calculate_index(from)].getColour())) {
						return false;
					}
				}
//				System.out.println("chariot legal döndü");
				return true;
			}
		} else if (this.getBoard().board[calculate_index(from)] instanceof Elephant) {
			Elephant elephant = (Elephant) this.getBoard().board[calculate_index(from)];
			if (elephant.legal_move_positions.contains(calculate_index(to))) {
				if (!(this.getBoard().board[calculate_index(to)] == null)) {
					if (this.getBoard().board[calculate_index(to)].getColour()
							.equals(this.getBoard().board[calculate_index(from)].getColour())) {
						return false;
					}
				}
//				System.out.println("elephant legal döndü");
				return true;
			}
		} else if (this.getBoard().board[calculate_index(from)] instanceof Advisor) {

			Advisor advisor = (Advisor) this.getBoard().board[calculate_index(from)];
			if (advisor.legal_move_positions.contains(calculate_index(to))) {

				if (!(this.getBoard().board[calculate_index(to)] == null)) {
					if (this.getBoard().board[calculate_index(to)].getColour()
							.equals(this.getBoard().board[calculate_index(from)].getColour())) {

						return false;
					}
				}
//				System.out.println("advisor legal döndü");
				return true;
			}
		} else if (this.getBoard().board[calculate_index(from)] instanceof General) {
			General general = (General) this.getBoard().board[calculate_index(from)];

			if (general.legal_move_positions.contains(calculate_index(to))) {
				if (!(this.getBoard().board[calculate_index(to)] == null)) {

					if (this.getBoard().board[calculate_index(to)].getColour()
							.equals(this.getBoard().board[calculate_index(from)].getColour())) {
						return false;
					}
				}
//				System.out.println("general legal döndü");
				return true;
			}
		}

//		System.out.println("nothing happened in legal");
		return false;

	}

	@Override
	void load_binary(String address) {
		ObjectInputStream o = null;

		try {
			o = new ObjectInputStream(new FileInputStream(address));
			this.isItOver = o.readBoolean();
			this.isThereACheck = o.readBoolean();
			String redplayername = o.readUTF();
			String blackplayername = o.readUTF();
			this.move_count = o.readInt();
			this.red = new Player(redplayername);
			this.black = new Player(blackplayername);
			red.puan = o.readFloat();
			black.puan = o.readFloat();
			this.board = new Board();
//			System.out.println(isItOver+"\n"+isThereACheck+"\n"+move_count+"\n"+red.name+"\n"+black.name+"\n"+red.puan+"\n"+black.puan);
			for (int i = 0; i < board.board.length; i++) {
				board.board[i] = null;
			}
			Horse redhorse1 = new Horse("red", this, red);
			String pos1 = o.readUTF();
			redhorse1.setPosition(pos1);
			if (!pos1.equals("xx"))
				board.board[calculate_index(pos1)] = redhorse1;
			this.getBoard().getItems()[0] = redhorse1;

			Horse redhorse2 = new Horse("red", this, red);
			String pos2 = o.readUTF();
			redhorse2.setPosition(pos2);
			if (!pos1.equals("xx"))
				board.board[calculate_index(pos2)] = redhorse2;
			this.getBoard().getItems()[1] = redhorse2;

			Horse blackhorse1 = new Horse("black", this, black);
			String pos3 = o.readUTF();
			blackhorse1.setPosition(pos3);
			if (!pos3.equals("xx"))
				board.board[calculate_index("j2")] = blackhorse1;
			this.getBoard().getItems()[2] = blackhorse1;

			Horse blackhorse2 = new Horse("black", this, black);
			String pos4 = o.readUTF();
			blackhorse2.setPosition(pos4);
			if (!pos4.equals("xx"))
				board.board[calculate_index(pos4)] = blackhorse2;
			this.getBoard().getItems()[3] = blackhorse2;

			Soldier redsoldier1 = new Soldier("red", this, red);
			String pos5 = o.readUTF();
			redsoldier1.setPosition(pos5);
			if (!pos5.equals("xx"))
				board.board[calculate_index(pos5)] = redsoldier1;
			this.getBoard().getItems()[4] = redsoldier1;

			Soldier redsoldier2 = new Soldier("red", this, red);
			String pos6 = o.readUTF();
			redsoldier2.setPosition(pos6);
			if (!pos6.equals("xx"))
				board.board[calculate_index(pos6)] = redsoldier2;
			this.getBoard().getItems()[5] = redsoldier2;

			Soldier redsoldier3 = new Soldier("red", this, red);
			String pos7 = o.readUTF();
			redsoldier3.setPosition(pos7);
			if (!pos7.equals("xx"))
				board.board[calculate_index(pos7)] = redsoldier3;
			this.getBoard().getItems()[6] = redsoldier3;

			Soldier redsoldier4 = new Soldier("red", this, red);
			String pos8 = o.readUTF();
			redsoldier4.setPosition(pos8);
			if (!pos5.equals("xx"))
				board.board[calculate_index(pos8)] = redsoldier4;
			this.getBoard().getItems()[7] = redsoldier4;

			Soldier redsoldier5 = new Soldier("red", this, red);
			String pos9 = o.readUTF();
			redsoldier5.setPosition(pos9);
			if (!pos9.equals("xx"))
				board.board[calculate_index(pos9)] = redsoldier5;
			this.getBoard().getItems()[8] = redsoldier5;

			Soldier blacksoldier1 = new Soldier("black", this, black);
			String pos10 = o.readUTF();
			blacksoldier1.setPosition(pos10);
			if (!pos10.equals("xx"))
				board.board[calculate_index(pos10)] = blacksoldier1;
			this.getBoard().getItems()[9] = blacksoldier1;

			Soldier blacksoldier2 = new Soldier("black", this, black);
			String pos11 = o.readUTF();
			blacksoldier2.setPosition(pos11);
			if (!pos11.equals("xx"))
				board.board[calculate_index(pos11)] = blacksoldier2;
			this.getBoard().getItems()[10] = blacksoldier2;

			Soldier blacksoldier3 = new Soldier("black", this, black);
			String pos12 = o.readUTF();
			blacksoldier3.setPosition(pos12);
			if (!pos12.equals("xx"))
				board.board[calculate_index("g5")] = blacksoldier3;
			this.getBoard().getItems()[11] = blacksoldier3;

			Soldier blacksoldier4 = new Soldier("black", this, black);
			String pos13 = o.readUTF();
			blacksoldier4.setPosition(pos13);
			if (!pos13.equals("xx"))
				board.board[calculate_index(pos13)] = blacksoldier4;
			this.getBoard().getItems()[12] = blacksoldier4;

			Soldier blacksoldier5 = new Soldier("black", this, black);
			String pos14 = o.readUTF();
			blacksoldier5.setPosition(pos14);
			if (!pos14.equals("xx"))
				board.board[calculate_index(pos14)] = blacksoldier5;
			this.getBoard().getItems()[13] = blacksoldier5;

			General redgeneral = new General("red", this, red);
			String pos15 = o.readUTF();
			redgeneral.setPosition(pos15);
			setRedgeneralposition(calculate_index(pos15));
			board.board[calculate_index(pos15)] = redgeneral;
			this.getBoard().getItems()[14] = redgeneral;

			General blackgeneral = new General("black", this, black);
			String pos16 = o.readUTF();
			blackgeneral.setPosition(pos16);
			setBlackgeneralposition(calculate_index(pos16));
			board.board[calculate_index(pos16)] = blackgeneral;
			this.getBoard().getItems()[15] = blackgeneral;

			Cannon redcannon1 = new Cannon("red", this, red);
			String pos17 = o.readUTF();
			redcannon1.setPosition(pos17);
			if (!pos17.equals("xx"))
				board.board[calculate_index(pos17)] = redcannon1;
			this.getBoard().getItems()[16] = redcannon1;

			Cannon redcannon2 = new Cannon("red", this, red);
			String pos18 = o.readUTF();
			redcannon2.setPosition(pos18);
			if (!pos18.equals("xx"))
				board.board[calculate_index(pos18)] = redcannon2;
			this.getBoard().getItems()[17] = redcannon2;

			Cannon blackcannon1 = new Cannon("black", this, black);
			String pos19 = o.readUTF();
			blackcannon1.setPosition(pos19);
			if (!pos19.equals("xx"))
				board.board[calculate_index(pos19)] = blackcannon1;
			this.getBoard().getItems()[18] = blackcannon1;

			Cannon blackcannon2 = new Cannon("black", this, black);
			String pos20 = o.readUTF();
			blackcannon2.setPosition(pos20);
			if (!pos20.equals("xx"))
				board.board[calculate_index(pos20)] = blackcannon2;
			this.getBoard().getItems()[19] = blackcannon2;

			Chariot redchariot1 = new Chariot("red", this, red);
			String pos21 = o.readUTF();
			if (!pos21.equals("xx"))
				board.board[calculate_index(pos21)] = redchariot1;
			redchariot1.setPosition(pos21);
			this.getBoard().getItems()[20] = redchariot1;

			Chariot redchariot2 = new Chariot("red", this, red);
			String pos22 = o.readUTF();
			if (!pos22.equals("xx"))
				board.board[calculate_index(pos22)] = redchariot2;
			redchariot2.setPosition(pos22);
			this.getBoard().getItems()[21] = redchariot2;

			Chariot blackcheriot1 = new Chariot("black", this, black);
			String pos23 = o.readUTF();
			if (!pos23.equals("xx"))
				board.board[calculate_index(pos23)] = blackcheriot1;
			blackcheriot1.setPosition(pos23);
			this.getBoard().getItems()[22] = blackcheriot1;

			Chariot blackcheriot2 = new Chariot("black", this, black);
			String pos24 = o.readUTF();
			if (!pos24.equals("xx"))
				board.board[calculate_index(pos24)] = blackcheriot2;
			blackcheriot2.setPosition(pos24);
			this.getBoard().getItems()[23] = blackcheriot2;

			Advisor redadivsor1 = new Advisor("red", this, red);
			String pos25 = o.readUTF();
			if (!pos5.equals("xx"))
				board.board[calculate_index(pos25)] = redadivsor1;
			redadivsor1.setPosition(pos25);
			this.getBoard().getItems()[24] = redadivsor1;

			Advisor redadivsor2 = new Advisor("red", this, red);
			String pos26 = o.readUTF();
			if (!pos26.equals("xx"))
				board.board[calculate_index(pos26)] = redadivsor2;
			redadivsor2.setPosition(pos26);
			this.getBoard().getItems()[25] = redadivsor2;

			Advisor blackadivsor1 = new Advisor("black", this, black);
			String pos27 = o.readUTF();
			if (!pos27.equals("xx"))
				board.board[calculate_index(pos27)] = blackadivsor1;
			blackadivsor1.setPosition(pos27);
			this.getBoard().getItems()[26] = blackadivsor1;

			Advisor blackadivsor2 = new Advisor("black", this, black);
			String pos28 = o.readUTF();
			if (!pos28.equals("xx"))
				board.board[calculate_index(pos28)] = blackadivsor2;
			blackadivsor2.setPosition(pos28);
			this.getBoard().getItems()[27] = blackadivsor2;

			Elephant redelephant1 = new Elephant("red", this, red);
			String pos29 = o.readUTF();
			if (!pos29.equals("xx"))
				board.board[calculate_index(pos29)] = redelephant1;
			redelephant1.setPosition(pos29);
			this.getBoard().getItems()[28] = redelephant1;

			Elephant redelephant2 = new Elephant("red", this, red);
			String pos30 = o.readUTF();
			if (!pos30.equals("xx"))
				board.board[calculate_index(pos30)] = redelephant2;
			redelephant2.setPosition(pos30);
			this.getBoard().getItems()[29] = redelephant2;

			Elephant blackelephant1 = new Elephant("black", this, black);
			String pos31 = o.readUTF();
			if (!pos31.equals("xx"))
				board.board[calculate_index(pos31)] = blackelephant1;
			blackelephant1.setPosition(pos31);
			this.getBoard().getItems()[30] = blackelephant1;

			Elephant blackelephant2 = new Elephant("black", this, black);
			String pos32 = o.readUTF();
			if (!pos32.equals("xx"))
				board.board[calculate_index(pos32)] = blackelephant2;
			blackelephant2.setPosition(pos32);
			this.getBoard().getItems()[31] = blackelephant2;

			this.update_all(getBoard().board);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	void save_binary(String address) {
		ObjectOutputStream o = null;
		try {
			o = new ObjectOutputStream(new FileOutputStream(address));
			o.writeBoolean(isItOver);
			o.writeBoolean(isThereACheck);
			o.writeUTF(this.red.name);
			o.writeUTF(this.black.name);
			o.writeInt(this.move_count);
			o.writeFloat(this.red.puan);
			o.writeFloat(this.black.puan);

			for (int i = 0; i < getBoard().getItems().length; i++) {
				o.writeUTF(getBoard().getItems()[i].getPosition());
			}
			o.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	void save_text(String address) {
		PrintWriter out = null;
		try {
			out = new PrintWriter(new FileOutputStream(address));
			out.println(this.isItOver);
			out.println(this.isThereACheck);
			out.println(this.red.name);
			out.println(this.black.name);
			out.println(this.move_count);
			out.println(red.puan);
			out.println(black.puan);
			for (int i = 0; i < getBoard().getItems().length; i++) {
				out.println(getBoard().getItems()[i].getPosition());
			}
			out.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	void load_text(String address) {
		Scanner s = null;

		try {
			s = new Scanner(new FileInputStream(address));
			this.isItOver = s.nextBoolean();
			s.nextLine();

			this.isThereACheck = s.nextBoolean();
			s.nextLine();

			String redplayername = s.nextLine();
			String blackplayername = s.nextLine();
	
			this.red = new Player(redplayername);
			this.black = new Player(blackplayername);
			this.move_count = s.nextInt();

			s.nextLine();

			red.puan = Float.parseFloat(s.nextLine());
		
			black.puan = Float.parseFloat(s.nextLine());
		
			this.board = new Board();

			for (int i = 0; i < board.board.length; i++) {
				board.board[i] = null;
			}
			Horse redhorse1 = new Horse("red", this, red);
			String pos1 = s.nextLine();
			redhorse1.setPosition(pos1);
			if (!pos1.equals("xx"))
				board.board[calculate_index(pos1)] = redhorse1;
			this.getBoard().getItems()[0] = redhorse1;

			Horse redhorse2 = new Horse("red", this, red);
			String pos2 = s.nextLine();
			redhorse2.setPosition(pos2);
			if (!pos1.equals("xx"))
				board.board[calculate_index(pos2)] = redhorse2;
			this.getBoard().getItems()[1] = redhorse2;

			Horse blackhorse1 = new Horse("black", this, black);
			String pos3 = s.nextLine();
			blackhorse1.setPosition(pos3);
			if (!pos3.equals("xx"))
				board.board[calculate_index("j2")] = blackhorse1;
			this.getBoard().getItems()[2] = blackhorse1;

			Horse blackhorse2 = new Horse("black", this, black);
			String pos4 = s.nextLine();
			blackhorse2.setPosition(pos4);
			if (!pos4.equals("xx"))
				board.board[calculate_index(pos4)] = blackhorse2;
			this.getBoard().getItems()[3] = blackhorse2;

			Soldier redsoldier1 = new Soldier("red", this, red);
			String pos5 = s.nextLine();
			redsoldier1.setPosition(pos5);
			if (!pos5.equals("xx"))
				board.board[calculate_index(pos5)] = redsoldier1;
			this.getBoard().getItems()[4] = redsoldier1;

			Soldier redsoldier2 = new Soldier("red", this, red);
			String pos6 = s.nextLine();
			redsoldier2.setPosition(pos6);
			if (!pos6.equals("xx"))
				board.board[calculate_index(pos6)] = redsoldier2;
			this.getBoard().getItems()[5] = redsoldier2;

			Soldier redsoldier3 = new Soldier("red", this, red);
			String pos7 = s.nextLine();
			redsoldier3.setPosition(pos7);
			if (!pos7.equals("xx"))
				board.board[calculate_index(pos7)] = redsoldier3;
			this.getBoard().getItems()[6] = redsoldier3;

			Soldier redsoldier4 = new Soldier("red", this, red);
			String pos8 = s.nextLine();
			redsoldier4.setPosition(pos8);
			if (!pos5.equals("xx"))
				board.board[calculate_index(pos8)] = redsoldier4;
			this.getBoard().getItems()[7] = redsoldier4;

			Soldier redsoldier5 = new Soldier("red", this, red);
			String pos9 = s.nextLine();
			redsoldier5.setPosition(pos9);
			if (!pos9.equals("xx"))
				board.board[calculate_index(pos9)] = redsoldier5;
			this.getBoard().getItems()[8] = redsoldier5;

			Soldier blacksoldier1 = new Soldier("black", this, black);
			String pos10 = s.nextLine();
			blacksoldier1.setPosition(pos10);
			if (!pos10.equals("xx"))
				board.board[calculate_index(pos10)] = blacksoldier1;
			this.getBoard().getItems()[9] = blacksoldier1;

			Soldier blacksoldier2 = new Soldier("black", this, black);
			String pos11 = s.nextLine();
			blacksoldier2.setPosition(pos11);
			if (!pos11.equals("xx"))
				board.board[calculate_index(pos11)] = blacksoldier2;
			this.getBoard().getItems()[10] = blacksoldier2;

			Soldier blacksoldier3 = new Soldier("black", this, black);
			String pos12 = s.nextLine();
			blacksoldier3.setPosition(pos12);
			if (!pos12.equals("xx"))
				board.board[calculate_index("g5")] = blacksoldier3;
			this.getBoard().getItems()[11] = blacksoldier3;

			Soldier blacksoldier4 = new Soldier("black", this, black);
			String pos13 = s.nextLine();
			blacksoldier4.setPosition(pos13);
			if (!pos13.equals("xx"))
				board.board[calculate_index(pos13)] = blacksoldier4;
			this.getBoard().getItems()[12] = blacksoldier4;

			Soldier blacksoldier5 = new Soldier("black", this, black);
			String pos14 = s.nextLine();
			blacksoldier5.setPosition(pos14);
			if (!pos14.equals("xx"))
				board.board[calculate_index(pos14)] = blacksoldier5;
			this.getBoard().getItems()[13] = blacksoldier5;

			General redgeneral = new General("red", this, red);
			String pos15 = s.nextLine();
			redgeneral.setPosition(pos15);
			setRedgeneralposition(calculate_index(pos15));
			board.board[calculate_index(pos15)] = redgeneral;
			this.getBoard().getItems()[14] = redgeneral;

			General blackgeneral = new General("black", this, black);
			String pos16 = s.nextLine();
			blackgeneral.setPosition(pos16);
			setBlackgeneralposition(calculate_index(pos16));
			board.board[calculate_index(pos16)] = blackgeneral;
			this.getBoard().getItems()[15] = blackgeneral;

			Cannon redcannon1 = new Cannon("red", this, red);
			String pos17 = s.nextLine();
			redcannon1.setPosition(pos17);
			if (!pos17.equals("xx"))
				board.board[calculate_index(pos17)] = redcannon1;
			this.getBoard().getItems()[16] = redcannon1;

			Cannon redcannon2 = new Cannon("red", this, red);
			String pos18 = s.nextLine();
			redcannon2.setPosition(pos18);
			if (!pos18.equals("xx"))
				board.board[calculate_index(pos18)] = redcannon2;
			this.getBoard().getItems()[17] = redcannon2;

			Cannon blackcannon1 = new Cannon("black", this, black);
			String pos19 = s.nextLine();
			blackcannon1.setPosition(pos19);
			if (!pos19.equals("xx"))
				board.board[calculate_index(pos19)] = blackcannon1;
			this.getBoard().getItems()[18] = blackcannon1;

			Cannon blackcannon2 = new Cannon("black", this, black);
			String pos20 = s.nextLine();
			blackcannon2.setPosition(pos20);
			if (!pos20.equals("xx"))
				board.board[calculate_index(pos20)] = blackcannon2;
			this.getBoard().getItems()[19] = blackcannon2;

			Chariot redchariot1 = new Chariot("red", this, red);
			String pos21 = s.nextLine();
			if (!pos21.equals("xx"))
				board.board[calculate_index(pos21)] = redchariot1;
			redchariot1.setPosition(pos21);
			this.getBoard().getItems()[20] = redchariot1;

			Chariot redchariot2 = new Chariot("red", this, red);
			String pos22 = s.nextLine();
			if (!pos22.equals("xx"))
				board.board[calculate_index(pos22)] = redchariot2;
			redchariot2.setPosition(pos22);
			this.getBoard().getItems()[21] = redchariot2;

			Chariot blackcheriot1 = new Chariot("black", this, black);
			String pos23 = s.nextLine();
			if (!pos23.equals("xx"))
				board.board[calculate_index(pos23)] = blackcheriot1;
			blackcheriot1.setPosition(pos23);
			this.getBoard().getItems()[22] = blackcheriot1;

			Chariot blackcheriot2 = new Chariot("black", this, black);
			String pos24 = s.nextLine();
			if (!pos24.equals("xx"))
				board.board[calculate_index(pos24)] = blackcheriot2;
			blackcheriot2.setPosition(pos24);
			this.getBoard().getItems()[23] = blackcheriot2;

			Advisor redadivsor1 = new Advisor("red", this, red);
			String pos25 = s.nextLine();
			if (!pos5.equals("xx"))
				board.board[calculate_index(pos25)] = redadivsor1;
			redadivsor1.setPosition(pos25);
			this.getBoard().getItems()[24] = redadivsor1;

			Advisor redadivsor2 = new Advisor("red", this, red);
			String pos26 = s.nextLine();
			if (!pos26.equals("xx"))
				board.board[calculate_index(pos26)] = redadivsor2;
			redadivsor2.setPosition(pos26);
			this.getBoard().getItems()[25] = redadivsor2;

			Advisor blackadivsor1 = new Advisor("black", this, black);
			String pos27 = s.nextLine();
			if (!pos27.equals("xx"))
				board.board[calculate_index(pos27)] = blackadivsor1;
			blackadivsor1.setPosition(pos27);
			this.getBoard().getItems()[26] = blackadivsor1;

			Advisor blackadivsor2 = new Advisor("black", this, black);
			String pos28 = s.nextLine();
			if (!pos28.equals("xx"))
				board.board[calculate_index(pos28)] = blackadivsor2;
			blackadivsor2.setPosition(pos28);
			this.getBoard().getItems()[27] = blackadivsor2;

			Elephant redelephant1 = new Elephant("red", this, red);
			String pos29 = s.nextLine();
			if (!pos29.equals("xx"))
				board.board[calculate_index(pos29)] = redelephant1;
			redelephant1.setPosition(pos29);
			this.getBoard().getItems()[28] = redelephant1;

			Elephant redelephant2 = new Elephant("red", this, red);
			String pos30 = s.nextLine();
			if (!pos30.equals("xx"))
				board.board[calculate_index(pos30)] = redelephant2;
			redelephant2.setPosition(pos30);
			this.getBoard().getItems()[29] = redelephant2;

			Elephant blackelephant1 = new Elephant("black", this, black);
			String pos31 = s.nextLine();
			if (!pos31.equals("xx"))
				board.board[calculate_index(pos31)] = blackelephant1;
			blackelephant1.setPosition(pos31);
			this.getBoard().getItems()[30] = blackelephant1;

			Elephant blackelephant2 = new Elephant("black", this, black);
			String pos32 = s.nextLine();
			if (!pos32.equals("xx"))
				board.board[calculate_index(pos32)] = blackelephant2;
			blackelephant2.setPosition(pos32);
			this.getBoard().getItems()[31] = blackelephant2;

			this.update_all(getBoard().board);

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public Board getBoard() {
		// TODO Auto-generated method stub
		return this.board;
	}

	public boolean isThereACheck() {
		return isThereACheck;
	}

	public void setThereACheck(boolean isThereACheck) {
		this.isThereACheck = isThereACheck;
	}

}
