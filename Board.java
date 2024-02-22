
public class Board extends AbstractBoard {
	Item[] board=new Item[90];
	Item[] items=new Item[32];


	
	@Override
	public void print() {
		for(int i=0;i<10;i++) {
			System.out.print((char)('j'-i)+"\t");
			for(int j=0;j<9;j++) {
				if(board[(9-i)*9+j]==null) {
					System.out.print("-");
				}
				else if(board[(9-i)*9+j] instanceof Soldier) {
					if(board[(9-i)*9+j].getColour().equals("black"))
						System.out.print("P");
					else
						System.out.print("p");
					
				}else if(board[(9-i)*9+j] instanceof Horse) {
					if(board[(9-i)*9+j].getColour().equals("black"))
						System.out.print("A");
					else
						System.out.print("a");
				}else if(board[(9-i)*9+j] instanceof Cannon) {
					if(board[(9-i)*9+j].getColour().equals("black"))
						System.out.print("T");
					else
						System.out.print("t");
				}else if(board[(9-i)*9+j] instanceof Chariot) {
					if(board[(9-i)*9+j].getColour().equals("black"))
						System.out.print("K");
					else
							System.out.print("k");
				}else if(board[(9-i)*9+j] instanceof General) {
					if(board[(9-i)*9+j].getColour().equals("black"))
						System.out.print("Ş");
					else
						System.out.print("ş");
				}else if(board[(9-i)*9+j] instanceof Advisor) {
					if(board[(9-i)*9+j].getColour().equals("black"))
						System.out.print("V");
					else
						System.out.print("v");
				}else if(board[(9-i)*9+j] instanceof Elephant) {
					if(board[(9-i)*9+j].getColour().equals("black"))
						System.out.print("F");
					else
						System.out.print("f");
				}
				if(j!=8)
				System.out.print("--");
				
			}
			
			System.out.println();
			if(i==0||i==7) {
				System.out.println(" \t|  |  |  |\\ | /|  |  |  |");
			}else if(i==1||i==8) {
				System.out.println(" \t|  |  |  |/ | \\|  |  |  |");
			}
			else if(i!=9&& i!=4) {
				System.out.println(" \t|  |  |  |  |  |  |  |  |");
			}else if(i==4)
				System.out.println(" \t|                       |");
		}
		System.out.println();
		System.out.println(" \t1--2--3--4--5--6--7--8--9");
	}
	

	public Board() {
		super();
	}


	public Item[] getItems() {
		// TODO Auto-generated method stub
		return items;
	}



}
