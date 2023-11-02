/**
 * 
 * @author Noah Thompson
 * Represents a Rook Piece
 *
 */


public class Rook extends Piece{
	/**
	 * Constructs a Rook of a specified color     
	 * @param isWhite color of the Rook
	 * 				  true is white false is black
	 */
	public Rook(boolean isWhite) {
		super(isWhite);
	}
	
	/**
	 * Represents the rules of movement for a Rook
	 * @param board current board state
	 * @param move proposed move
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
		//can't move diagonally
		if (changeInX != 0 && changeInY != 0) {
			return false;
		}
		//checks that there are no Pieces on the way to the destination
		//right
		if (changeInX > 0) {
			for (int i = move.getStart().getX() + 1; i < move.getEnd().getX(); i++) {
				if (board.getSquare(i, move.getStart().getY()).getPiece() != null) {
					return false;
				}
			}
		}
		//left
		else if (changeInX < 0) {
			for (int i = move.getStart().getX() - 1; i > move.getEnd().getX(); i--) {
				if (board.getSquare(i, move.getStart().getY()).getPiece() != null) {
					return false;
				}
			}
		}
		//up
		else if (changeInY > 0) {
			for (int i = move.getStart().getY() + 1; i < move.getEnd().getY(); i++) {
				if (board.getSquare(move.getStart().getX(), i).getPiece() != null) {
					return false;
				}
			}
		}
		//down
		else if (changeInY < 0) {
			for (int i = move.getStart().getY() - 1; i > move.getEnd().getY(); i--) {
				if (board.getSquare(move.getStart().getX(), i).getPiece() != null) {
					return false;
				}
			}
		}
		setHasNotMoved(false);
		return true;
	}
	
	/**
	 * Returns the unicode char of a Rook
	 * @return String unicode char of a Rook
	 */
	public String toString() {
		if (this.isWhite()) {
			//white rook
			return "" + (char)(0x2656);
		}
		else {
			//black rook
			return "" + (char)(0x265C);
		}
	}
}
