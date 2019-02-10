import java.io.IOException;
public class QueenBoard{
	private int[][] board;

	public QueenBoard(int size){
		board = new int[size][size];
	}

	private boolean addQueen(int r, int c){
		board[r][c] = 1;
		return true;
	}

	private boolean removeQueen(int r, int c){
		board[r][c] = 0;
		return true;
	}

	public String toString(){
		String result = "";
		for(int i = 0; i < board.length; i++){
			for(int j = 0; j < board.length; j++){
				if(board[i][j] == 0) result += "_";
				else{
					result += "Q";
				}
				result += " ";
			}
			result += "\n";
		}
		return result;
	}

	public boolean solve(){
		for(int i = 0; i < board.length; i++){
			for(int j = 0; j < board.length; j++){
				if(board[i][j] != 0){
					throw new IllegalStateException("Board started with a nonzero value.");
				}
			}
		}
		return this.oneSolution(0);
	}

	public boolean oneSolution(int r){
		for(int i = 0; i < board.length; i++){
			if(!isConflict(board, r, i)){//only place if there's no conflict
				this.addQueen(r, i);
				if(r == board.length - 1) return true; //you're done
				if(oneSolution(r + 1)) return true; //if going forward yeilds a solution, return true
				this.removeQueen(r, i);//if you didn't get a solution, remove the queen and keep going
			}
		}
		return false;
	}

	public boolean isConflict(int[][] board, int r, int c){
		for(int i = 0; i < board.length; i++){
			for(int j = 0; j < board.length; j++){
				if(board[i][j] == 1){
					if(Math.abs(r - i) == Math.abs(c - j)) return true;
					if(j == c) return true;
				}
			}
		}
		return false;
	}

	public int countSolutions(){
		for(int i = 0; i < board.length; i++){
			for(int j = 0; j < board.length; j++){
				if(board[i][j] != 0){
					throw new IllegalStateException("Board started with a nonzero value.");
				}
			}
		}
		return this.allSolutions(0, 0);
	}

	public int allSolutions(int r, int total){
		for(int i = 0; i < board.length; i++){
			if(!isConflict(board, r, i)){//only place if there's no conflict
				this.addQueen(r, i);
				if(r == board.length - 1) total += 1; //you've got one
				else{
					total += allSolutions(r + 1, 0); //increase the depth of the search and add the number of solutions found
				}
				this.removeQueen(r, i);//remove the queen, keep searching
			}
		}
		return total;
	}
}
