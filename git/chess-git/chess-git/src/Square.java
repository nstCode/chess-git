/**
 * 
 * @author Noah Thompson
 * Represents a square on the board
 * 
 */


public class Square {
	private Piece piece;
	private int x, y;
	
	/**
	 * Constructs a square referencing the piece that is on it 
	 * and its x/y coordinates on the board
	 * @param x the horizontal coordinate of the square
	 * @param y the vertical coordinate of the square
	 * @param piece Piece on the Square
	 */
	public Square(int x, int y, Piece piece) {
		this.piece = piece;
		this.x = x;
		this.y = y;
	}
	
	/**
	 * Returns the Piece object on the square
	 * @return piece Piece on the Square
	 */
	public Piece getPiece() {
		return this.piece;
	}
	
	/**
	 * Returns the x coordinate of the Square
	 * @return x the horizontal coordinate of the square
	 */
	public int getX() {
		return this.x;
	}
	
	/**
	 * Returns the y coordinate of the Square
	 * @return y the vertical coordinate of the square
	 */
	public int getY() {
		return this.y;
	}
	
	/**
	 * Sets a new Piece on the square, overriding the previous Piece
	 * @param piece new Piece placed on the square
	 */
	public void setPiece(Piece piece) {
		this.piece = piece;
	}
}
