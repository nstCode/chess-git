/**
 * 
 * @author Noah Thompson
 * Represents a Bishop Piece
 *
 */


public class Bishop extends Piece{
	/**
	 * Constructs a Bishop of a specified color     
	 * @param isWhite color of the Bishop
	 * 				  true is white false is black
	 */
	public Bishop(boolean isWhite) {
		super(isWhite);
	}
	
	/**
	 * Represents the rules of movement for a Bishop
	 * @param board current board state
	 * @param move proposed Move
	 * @return true if Move follows the rules false if not
	 */
	public boolean isLegalMove(Board board, Move move) {
		int changeInX = move.getEnd().getX() - move.getStart().getX();
		int changeInY = move.getEnd().getY() - move.getStart().getY();
		//can't take the space of a Piece of the same color
		if (move.getEnd().getPiece() != null) {
			if (move.getEnd().getPiece().isWhite() == this.isWhite()) {
				return false;
			}
		}
		//must move diagonally
		if (Math.abs(changeInX) != Math.abs(changeInY)) {
			return false;
		}
		//checks that there are no Pieces on the way to the destination
		//up right
		if (changeInX > 0 && changeInY > 0) {
			int j = move.getStart().getY() + 1;
			for (int i = move.getStart().getX() + 1; i < move.getEnd().getX(); i++) {
				if (board.getSquare(i, j).getPiece() != null) {
					return false;
				}
				j++;
			}
		}
		//down right
		else if (changeInX > 0 && changeInY < 0) {
			int j = move.getStart().getY() - 1;
			for (int i = move.getStart().getX() + 1; i < move.getEnd().getX(); i++) {
				if (board.getSquare(i, j).getPiece() != null) {
					return false;
				}
				j--;
			}
		}
		//up left
		else if (changeInX < 0 && changeInY > 0) {
			int j = move.getStart().getY() + 1;
			for (int i = move.getStart().getX() - 1; i > move.getEnd().getX(); i--) {
				if (board.getSquare(i, j).getPiece() != null) {
					return false;
				}
				j++;
			}
		}
		//down left
		else if (changeInX < 0 && changeInY < 0) {
			int j = move.getStart().getY() - 1;
			for (int i = move.getStart().getX() - 1; i > move.getEnd().getX(); i--) {
				if (board.getSquare(i, j).getPiece() != null) {
					return false;
				}
				j--;
			}
		}
		return true;
	}
	
	/**
	 * Returns the unicode char of a Bishop
	 * @return String unicode char of a Bishop
	 */
	public String toString() {
		if (this.isWhite()) {
			//white bishop
			return "" + (char)(0x2657);
		}
		else {
			//black bishop
			return "" + (char)(0x265D);
		}
	}
}
