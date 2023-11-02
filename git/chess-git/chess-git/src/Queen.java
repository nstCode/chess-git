/**
 * 
 * @author Noah Thompson
 * Represents a Queen Piece
 *
 */


public class Queen extends Piece{
	/**
	 * Constructs a Queen of a specified color     
	 * @param isWhite color of the Queen
	 * 				  true is white false is black
	 */
	public Queen(boolean isWhite) {
		super(isWhite);
	}
	
	/**
	 * Represents the rules of movement for a Queen
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
		//checks that there are no Pieces on the way to the destination
		//Moving horizontally or vertically
		if ((changeInX != 0 && changeInY == 0) || (changeInX == 0 && changeInY != 0)) {
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
			return true;
		}
		//Moving diagonally
		else if (Math.abs(changeInX) == Math.abs(changeInY)) {
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
		return false;
	}
	
	/**
	 * Returns the unicode char of a Queen
	 * @return String unicode char of a Queen
	 */
	public String toString() {
		if (this.isWhite()) {
			return "" + (char)(0x2655);
		}
		else {
			return "" + (char)(0x265B);
		}
	}
}
