

//import java.util.ArrayList;

public class Savings {
//	public boolean isThereACheckforBlack(Item[] board, int general_index) {
//		ArrayList<Integer> possibleChecks = new ArrayList<Integer>();
//		for (Item item : board) {
//			if (item instanceof Soldier) {
//				Soldier soldier = (Soldier) item;
//				if (soldier.getColour().equals("red")) {
//					if (soldier.getLegal_move_positions().contains(general_index)) {
//						System.out.println(" red soldier checked");
//						checkingpieces.add(calculate_index(soldier.getPosition()));
//	//					System.out.println(general_index);
//	//					System.out.println(soldier.getLegal_move_positions());
//					possibleChecks.addAll(soldier.legal_move_positions);}
//				}
//			} else if (item instanceof Horse) {
//				Horse horse = (Horse) item;
//				if (horse.getColour().equals("red")) {
//					if (horse.getLegal_move_positions().contains(general_index)) {
//						checkingpieces.add(calculate_index(horse.getPosition()));
//						System.out.println("red horse checked");
//					possibleChecks.addAll(horse.legal_move_positions);}
//				}
//			} else if (item instanceof Chariot) {
//				Chariot chariot = (Chariot) item;
//				if (chariot.getColour().equals("red")) {
//					if (chariot.getLegal_move_positions().contains(general_index)) {
//						checkingpieces.add(calculate_index(chariot.getPosition()));
//						System.out.println("red chariot checked");
////						System.out.println(chariot.getLegal_move_positions());
//					
//					possibleChecks.addAll(chariot.legal_move_positions);}
//				}
//			} else if (item instanceof Cannon) {
//				Cannon cannon = (Cannon) item;
//				if (cannon.getColour().equals("red")) {
//					if (!cannon.check_positions.isEmpty() && cannon.check_positions.contains(general_index)) {
//						System.out.println("red cannon checked");
//						checkingpieces.add(calculate_index(cannon.getPosition()));
////						System.out.println("here---->"+cannon.getLegal_move_positions());
////						System.out.println(general_index);
//					possibleChecks.addAll(cannon.check_positions);}
//				}
//			}
//		}
//		if (possibleChecks.contains(general_index)) {
//			System.out.println("black is checked");
//			setThereACheck(true);
//			return true;
//		} else {
//			setThereACheck(false);
//			return false;
//		}
//	}
//
//	public boolean isThereACheckforRed(Item[] board, int general_index) {
//	//	 System.out.println(general_index+" general index");
//		ArrayList<Integer> possibleChecks = new ArrayList<Integer>();
//		for (Item item : board) {
//			
//			if (item instanceof Soldier) {
//				Soldier soldier = (Soldier) item;
//				if (soldier.getColour().equals("black")) {
//					if (soldier.getLegal_move_positions().contains(general_index)) {
//						checkingpieces.add(calculate_index(soldier.getPosition()));
//						System.out.println("soldier checked");
//					possibleChecks.addAll(soldier.legal_move_positions);}
//				}
//			} else if (item instanceof Horse) {
//				Horse horse = (Horse) item;
//				if (horse.getColour().equals("black")) {
//					if (horse.getLegal_move_positions().contains(general_index)) {
//						checkingpieces.add(calculate_index(horse.getPosition()));
//						System.out.println("horse checked");
//					possibleChecks.addAll(horse.legal_move_positions);}
//				}
//			} else if (item instanceof Chariot) {
//				Chariot chariot = (Chariot) item;
////				System.out.println("these "+chariot.getPosition()+" "+chariot.getLegal_move_positions());
////				System.out.println("these "+general_index);
//				if (chariot.getColour().equals("black")) {
//					if (chariot.getLegal_move_positions().contains(general_index)) {
//						checkingpieces.add(calculate_index(chariot.getPosition()));
//						System.out.println("chariot checked");
//					possibleChecks.addAll(chariot.getLegal_move_positions());}
//				}
//			} else if (item instanceof Cannon) {
//				Cannon cannon = (Cannon) item;
//				if (cannon.getColour().equals("black")) {
//					//System.out.println(cannon.legal_move_positions);
//					if (cannon.check_positions.contains(general_index)) {
//						checkingpieces.add(calculate_index(cannon.getPosition()));
//						System.out.println("cannon checked");
//					possibleChecks.addAll(cannon.check_positions);}
//
//				}
//			}
//		}
//		if (possibleChecks.contains(general_index)) {
//			System.out.println("red is checked");
//			setThereACheck(true);
//			return true;
//		} else {
////		System.out.println("I dont think red is checked");
//			setThereACheck(false);
//			return false;
//		}
//	}

//	private void isItMateforRed(){
//		ArrayList<Integer> chariot_checkvectors = new ArrayList<Integer>();
//		ArrayList<Integer> horse_checkvectors = new ArrayList<Integer>();
//		ArrayList<Integer> cannon_checkvectors = new ArrayList<Integer>();
//		ArrayList<Integer> soldier_checkvectors = new ArrayList<Integer>();
//		for (int i = 0; i < checkingpieces.size(); i++) {
//			if(getBoard().board[checkingpieces.get(i)] instanceof Chariot) {
//				//if same column
//				if(checkingpieces.get(i)%9== getRedgeneralposition()%9) {
//					//if chariot is upper side of the general
//					if(checkingpieces.get(i)>getRedgeneralposition()) {
//						for (int j = checkingpieces.get(i); j >getRedgeneralposition(); j-=9) {
//							chariot_checkvectors.add(j);
//						}
//					}else {//if the chariot is under the general
//						for (int j = getRedgeneralposition()-9; j >=checkingpieces.get(i); j-=9) {
//							chariot_checkvectors.add(j);
//						}
//					}
//					
//				}else {
//					//if it is on the right side of the general
//					if(checkingpieces.get(i)>getRedgeneralposition()) {
//						for (int j = checkingpieces.get(i); j >getRedgeneralposition(); j--) {
//							chariot_checkvectors.add(j);
//						}
//					}else {//if the general is on the right side of the chariot
//						for (int j = getRedgeneralposition()-1; j >=checkingpieces.get(i); j--) {
//							chariot_checkvectors.add(j);
//						}
//					}
//				}
//				
//			}else if(getBoard().board[checkingpieces.get(i)] instanceof Cannon) {
////		 		same column
//				if(checkingpieces.get(i)%9== getRedgeneralposition()%9) {
//					//if cannon is upper side of the general
//					if(checkingpieces.get(i)>getRedgeneralposition()) {
//						for (int j = checkingpieces.get(i); j >getRedgeneralposition(); j-=9) {
//							//if there is a piece with the same color as general
//							if(getBoard().board[j] ==null) {
//								cannon_checkvectors.add(j);
//							}
//							else if(getBoard().board[j] !=null && getBoard().board[j].getColour().equals("red")) {
//								cannon_checkvectors.addAll(getBoard().board[j].getLegal_move_positions());
//							}
//						}
//					}else {//if the cannon is under the general
//						for (int j = checkingpieces.get(i); j <getRedgeneralposition(); j+=9) {
//							//if there is a piece with the same color as general
//							if(getBoard().board[j] ==null) {
//								cannon_checkvectors.add(j);
//							}
//							else if(getBoard().board[j] !=null && getBoard().board[j].getColour().equals("red")) {
//								cannon_checkvectors.addAll(getBoard().board[j].getLegal_move_positions());
//							}
//						}
//					}
//					
//				}else {
//					//if it is on the right side of the general
//					if(checkingpieces.get(i)>getRedgeneralposition()) {
//						for (int j = checkingpieces.get(i); j >getRedgeneralposition(); j--) {
//							//if there is a piece with the same color as general
//							if(getBoard().board[j] ==null) {
//								cannon_checkvectors.add(j);
//							}
//							else if(getBoard().board[j] !=null && getBoard().board[j].getColour().equals("red")) {
//								cannon_checkvectors.addAll(getBoard().board[j].getLegal_move_positions());
//							}
//						}
//					}else {//if the cannon is under the general
//						for (int j = checkingpieces.get(i); j <getRedgeneralposition(); j++) {
//							//if there is a piece with the same color as general
//							if(getBoard().board[j] ==null) {
//								cannon_checkvectors.add(j);
//							}
//							else if(getBoard().board[j] !=null && getBoard().board[j].getColour().equals("red")) {
//								cannon_checkvectors.addAll(getBoard().board[j].getLegal_move_positions());
//							}
//						}
//					}
//					
//				}
//			}else if(getBoard().board[checkingpieces.get(i)] instanceof Horse) {
//				int col=checkingpieces.get(i)%9;
//				int row=checkingpieces.get(i)/9;
//				int gencol=getRedgeneralposition()%9;
//				int genrow=getRedgeneralposition()/9;
//				horse_checkvectors.add(checkingpieces.get(i));
//				if(genrow<row&& gencol<col) {
//					horse_checkvectors.add(getRedgeneralposition()+10);
//				}else if(genrow<row&& gencol>col) {
//					horse_checkvectors.add(getRedgeneralposition()+8);
//				}else if(genrow>row&& gencol>col) {
//					horse_checkvectors.add(getRedgeneralposition()-10);
//				}else if(genrow>row&& gencol<col) {
//					horse_checkvectors.add(getRedgeneralposition()-8);
//				}			
//					
//			}else if(getBoard().board[checkingpieces.get(i)] instanceof Soldier) {
//				soldier_checkvectors.add(checkingpieces.get(i));
//			}
//		}
//	}
}
