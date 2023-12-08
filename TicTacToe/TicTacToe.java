import java.util.Scanner;
public class TicTacToe{
	private char turn='x';
	private char board[][] = new char[3][3];
	private boolean finished = false;
	private char winner;
	private boolean tied;
	TicTacToe(){
		for(int i=0; i<3; i++){
			for(int j=0; j<3; j++){
				this.board[i][j] = '.';
			}
		}
	
	}	

	private boolean checkVictoryCondition(char c){
		boolean horizontal=true,
			vertical=true,
			pDiagonal=true,
			sDiagonal=true,
			notTie=false;
		for(int i=0;i<3;i++){
			for(int j=0; j<3; j++){
				notTie = this.board[i][j] == '.' || notTie;
				horizontal = this.board[i][j]==c && horizontal;
				vertical = this.board[j][i]==c && vertical;
			
			}
			if(horizontal || vertical){
				break;
			}
			pDiagonal= this.board[i][i] ==c && pDiagonal;
			sDiagonal= this.board[2-i][i] ==c && sDiagonal;
		}
		this.tied = ! notTie;
		return horizontal || vertical || pDiagonal || sDiagonal || !notTie;
	}
	public char winner(){
		if(!tied)
			return this.winner;
		else
			return '.';
	}
	public char turnOf(){
		return this.turn;
	}
	public boolean hasGameFinished(){
		return finished;
	}
	public void printBoard(){
		for(int i=0; i<3; i++){
			for(int j=0; j<3; j++){
				System.out.printf("%c ",this.board[i][j]);
				
			}
			System.out.println("");
		}
	}	
	public boolean play(int i,int j){
		if(this.board[i][j] != '.'){
			return false;	
		}

		if(this.turn == 'x'){
			this.board[i][j] = 'x';	
			this.turn='o';
			this.winner='x';
			this.finished = this.checkVictoryCondition('x');
		}else{
			this.board[i][j]= 'o';
			this.turn='x';
			this.winner='o';
			this.finished =	this.checkVictoryCondition('o');
		}
		return true;
	}
	public static void clearConsole() {
		System.out.print("\033[H\033[2J");  
	    System.out.flush();  
	}
	public static void main(String args[]){
		System.out.println("Welcome To my TicTacToe Game!");
		Scanner sc = new Scanner(System.in);
		TicTacToe t = new TicTacToe();
		int row,column;
		while(!t.hasGameFinished()){
			System.out.printf("Player %c, it is your turn\nInsert the poision of the line and the column:\t",t.turnOf());
			row = sc.nextInt();
			column = sc.nextInt();
			clearConsole();
			if(row<3 && column<3){
				if( !t.play(row,column)){
					System.out.println("Hey, you can't play in this position");
				}	
			}else{
				System.out.println("Column or Rown can't be greater than 2");
			}
		
			t.printBoard();
		}
		if(t.winner()!='.')
			System.out.printf("Player %c.... WON!!!\n",t.winner());
		else
			System.out.printf("TIE\n");
	}
}
