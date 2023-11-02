/**
 * 
 * @author Noah Thompson
 * Represents a Pawn Piece
 *
 */


public class Pawn extends Piece{
	/**
	 * Constructs a Pawn of a specified color     
	 * @param isWhite color of the Pawn
	 * 				  true is white false is black
	 */
	public Pawn(boolean isWhite) {
		super(isWhite);
	}
	
	/**
	 * Represents the rules of movement for a Pawn
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
			//diagonal capture
			if (this.isWhite() && changeInY == 1 && Math.abs(changeInX) == 1 && !move.getEnd().getPiece().isWhite()) {
				setHasNotMoved(false);
				return true;
			}
			if (!this.isWhite() && changeInY == -1 && Math.abs(changeInX) == 1 && move.getEnd().getPiece().isWhite()) {
				setHasNotMoved(false);
				return true;
			}
			return false;
		}
		else {
			//move forward one Square
			if (this.isWhite() && (changeInY == 1 && changeInX == 0)) {
				setHasNotMoved(false);
				return true;
			}
			else if (!this.isWhite() && (changeInY == -1 && changeInX == 0)) {
				setHasNotMoved(false);
				return true;
			}
			//move forward two Squares
			else if (this.isWhite() && (changeInY == 2 && changeInX == 0) && hasNotMoved() && board.getSquare(move.getStart().getX(), move.getStart().getY() + 1).getPiece() == null) {
				setHasNotMoved(false);
				
				return true;
			}
			else if (!this.isWhite() && (changeInY == -2 && changeInX == 0) && hasNotMoved() && board.getSquare(move.getStart().getX(), move.getStart().getY() - 1).getPiece() == null) {
				setHasNotMoved(false);
				return true;
			} 
			//En Passent capture
			else if (this.isWhite() && changeInY == 1 && Math.abs(changeInX) == 1) {
				if(board.getSquare(move.getEnd().getX(), move.getStart().getY()).getPiece() != null){
					if(!board.getSquare(move.getEnd().getX(), move.getStart().getY()).getPiece().isWhite() 
							&& board.getSquare(move.getEnd().getX(), move.getStart().getY()).getPiece() instanceof Pawn) {
						if(Game.prevMove.getStart() == board.getSquare(move.getEnd().getX(), move.getEnd().getY() + 1) 
								&& Game.prevMove.getEnd() == board.getSquare(move.getEnd().getX(), move.getEnd().getY() - 1)) {
							return true;
						}
					}
				}
			}
			else if (!this.isWhite() && changeInY == -1 && Math.abs(changeInX) == 1) {
				if(board.getSquare(move.getEnd().getX(), move.getStart().getY()).getPiece() != null){
					if(board.getSquare(move.getEnd().getX(), move.getStart().getY()).getPiece().isWhite() 
							&& board.getSquare(move.getEnd().getX(), move.getStart().getY()).getPiece() instanceof Pawn) {
						if(Game.prevMove.getStart() == board.getSquare(move.getEnd().getX(), move.getEnd().getY() - 1) 
								&& Game.prevMove.getEnd() == board.getSquare(move.getEnd().getX(), move.getEnd().getY() + 1)) {
							return true;
						}
					}
				}
			}
			return false;
		}
	}
	
	/**
	 * Returns the unicode char of a Pawn
	 * @return String unicode char of a Pawn
	 */
	public String toString() {
		if (this.isWhite()) {
			return "" + (char)(0x2659); 
		}
		else {
			return "" + (char)(0x265F);
		}
	}
}
