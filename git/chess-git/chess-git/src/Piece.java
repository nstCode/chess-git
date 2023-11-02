/**
 * 
 * @author Noah Thompson
 * Represents an abstract chess piece
 *
 */


public abstract class Piece {
	private boolean isWhite;
	private boolean hasNotMoved;
	
	/**
	 * Constructs a Piece and determines its color
	 * @param isWhite the color of the Piece
	 */
	public Piece(boolean isWhite) {
		this.isWhite = isWhite;
		hasNotMoved = true;
	}
	
	/**
	 * Returns the color of the Piece
	 * @return isWhite boolean for white and black
	 * 				   true is white false is black
	 */
	public boolean isWhite() {
		return this.isWhite;
	}
	
	/**
	 * Returns true if the Piece has not moved
	 * false if it has
	 * @return hasNotMoved Is this the first move of the Piece
	 */
	public boolean hasNotMoved() {
		return hasNotMoved;
	}
	
	/**
	 * Sets the status of the Pieces first move
	 * @param hasNotMoved Is this the first move of the Piece
	 */
	public void setHasNotMoved(boolean hasNotMoved) {
		this.hasNotMoved = hasNotMoved;
	}
	
	/**
	 * Returns true if the proposed move is legal for the specific Piece false if not
	 * @param board current Board and its state
	 * @param move the move being proposed
	 * @return isLegalMove True if the move follows the rules 
	 */
	public abstract boolean isLegalMove(Board board, Move move);
	
	/**
	 * Returns unicode char representing the piece
	 * @return String contains the single unicode char
	 */
	@Override
	public abstract String toString();
}
