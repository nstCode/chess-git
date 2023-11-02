/**
 * 
 * @author Noah Thompson
 * Represents a Knight Piece
 *
 */


public class Knight extends Piece{
	/**
	 * Constructs a Knight of a specified color     
	 * @param isWhite color of the Knight
	 * 				  true is white false is black
	 */
	public Knight(boolean isWhite) {
		super(isWhite);
	}
	
	/**
	 * Represents the rules of movement for a Knight
	 * @param board current board state
	 * @param move proposed Move
	 * @return true if Move follows the rules false if not
	 */
	public boolean isLegalMove(Board board, Move move) {
		//can't take the space of a Piece of the same color
		if (move.getEnd().getPiece() != null) {
			if (move.getEnd().getPiece().isWhite() == this.isWhite()) {
				return false;
			}
		}
		//L shape
		int changeInX = Math.abs(move.getStart().getX() - move.getEnd().getX());
		int changeInY = Math.abs(move.getStart().getY() - move.getEnd().getY());
		return changeInX * changeInY == 2;
	}
	
	/**
	 * Returns the unicode char of a Knight
	 * @return String unicode char of a Knight
	 */
	public String toString() {
		if (this.isWhite()) {
			return "" + (char)(0x2658);
		}
		else {
			return "" + (char)(0x265E);
		}
	}
}
