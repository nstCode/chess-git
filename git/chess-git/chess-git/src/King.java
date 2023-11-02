/**
 * 
 * @author Noah Thompson
 * Represents a King Piece
 *
 */


public class King extends Piece{
	/**
	 * Constructs a King of a specified color     
	 * @param isWhite color of the King
	 * 				  true is white false is black
	 */
	public King(boolean isWhite) {
		super(isWhite);
	}
	
	/**
	 * Represents the rules of movement for a King
	 * @param board current board state
	 * @param move proposed Move
	 * @return true if Move follows the rules false if not
	 */
	public boolean isLegalMove(Board board, Move move) {
		int changeInX = move.getEnd().getX() - move.getStart().getX();
		int changeInY = move.getEnd().getY() - move.getStart().getY();
		//can't take the space of a Piece of the same color
		if (move.getEnd().getPiece() != null) {
			if (move.getEnd().getPiece().isWhite() == this.isWhite() && !(move.getEnd().getPiece() instanceof Rook)) {
				return false;
			}
			//Check for castle
			else if (move.getEnd().getPiece().isWhite() == this.isWhite() && (move.getEnd().getPiece() instanceof Rook)) {
				if (hasNotMoved() && move.getEnd().getPiece().hasNotMoved()) {
					//castling to the right
					if (move.getEnd().getX() > move.getStart().getX()) {
						//check that the squares between the King and Rook are empty
						for (int i = 5; i < 7; i++) {
							if (board.getSquare(i, move.getStart().getY()).getPiece() != null) {
								return false;
							}
						}
						//checks if any of the Squares the King passes over are in check
						board.getSquare(5, move.getStart().getY()).setPiece(new King(move.getStart().getPiece().isWhite()));
						board.getSquare(6, move.getStart().getY()).setPiece(new King(move.getStart().getPiece().isWhite()));
						for (int i = 4; i < 7; i++) {
							if (Game.isCheck(board.getSquare(i, move.getStart().getY()))){
								board.getSquare(5, move.getStart().getY()).setPiece(null);
								board.getSquare(6, move.getStart().getY()).setPiece(null);
								return false;
							}
						}
						board.getSquare(5, move.getStart().getY()).setPiece(null);
						board.getSquare(6, move.getStart().getY()).setPiece(null);
						setHasNotMoved(false);
						return true;
					}
					//castling to the left
					else {
						//check that the squares between the King and Rook are empty
						for (int i = 3; i > 0; i--) {
							if (board.getSquare(i, move.getStart().getY()).getPiece() != null) {
								return false;
							}
						}
						//checks if any of the Squares the King passes over are in check
						board.getSquare(3, move.getStart().getY()).setPiece(new King(move.getStart().getPiece().isWhite()));
						board.getSquare(2, move.getStart().getY()).setPiece(new King(move.getStart().getPiece().isWhite()));
						board.getSquare(1, move.getStart().getY()).setPiece(new King(move.getStart().getPiece().isWhite()));
						for (int i = 4; i > 0; i--) {
							if (Game.isCheck(board.getSquare(i, move.getStart().getY()))){
								board.getSquare(3, move.getStart().getY()).setPiece(null);
								board.getSquare(2, move.getStart().getY()).setPiece(null);
								board.getSquare(1, move.getStart().getY()).setPiece(null);
								return false;
							}
						}
						board.getSquare(3, move.getStart().getY()).setPiece(null);
						board.getSquare(2, move.getStart().getY()).setPiece(null);
						board.getSquare(1, move.getStart().getY()).setPiece(null);
						setHasNotMoved(false);
						return true;
					}
				}
			}
		}
		if(Math.abs(changeInX) > 1 || Math.abs(changeInY) > 1) {
			return false;
		}
		setHasNotMoved(false);
		return true;
	}
	
	/**
	 * Returns the unicode char of a King
	 * @return String unicode char of a King
	 */
	public String toString() {
		if (this.isWhite()) {
			return "" + (char)(0x2654);
		}
		else {
			return "" + (char)(0x265A);
		}
	}
}
