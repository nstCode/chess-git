/**
 * 
 * @author Noah Thompson
 * Represents a potential move
 *
 */


public class Move {
	private Square start;
	private Square end;
	
	/**
	 * Constructs a move 
	 * @param start Square where the move begins
	 * @param end Square where the move ends
	 */
	public Move(Square start, Square end) {
		this.start = start;
		this.end = end;
	}
	
	/**
	 * Returns the Square where the move begins
	 * @return start the Square where the move begins
	 */
	public Square getStart() {
		return this.start;
	}
	
	/**
	 * Returns the Square where the move ends
	 * @return end the Square where the move ends
	 */
	public Square getEnd() {
		return this.end;
	}
}
