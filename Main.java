import java.util.Scanner;
public class Main {
//    
    public static void main(String[] args) {
        Game mygam = new Game("A","B");
        mygam.update_all(mygam.getBoard().board);
        mygam.getBoard().print();
        String a;
        Scanner scan = new Scanner(System.in);
        a = scan.next();
        while(!a.equals("e")){
      //  	System.out.println(mygam.getMove_count());
            mygam.play(a.substring(0,2).toLowerCase(),a.substring(2).toLowerCase());
            System.out.println(a.substring(0,2).toLowerCase() + " " +a.substring(2).toLowerCase() );
            mygam.getBoard().print();
            System.out.println(mygam.getMove_count());
            a=scan.next();
        }
//        scan.close();
        for (int i = 0; i < mygam.getBoard().board.length; i++) {
			if(mygam.getBoard().board[i]!=null) {
				System.out.println(mygam.getBoard().board[i].getColour()+"-->  "+mygam.getBoard().board[i].getClass());
				System.out.println(mygam.getBoard().board[i].getLegal_move_positions());
			}
		}
        
    }
}
