/**
 * 
 * @author Noah Thompson
 * Represents the chess board 
 *
 */


public class Board implements Cloneable{
	private Square[][] board = new Square[8][8];
	private Square whiteKing = this.getSquare(4, 0);
	private Square blackKing = this.getSquare(4, 7);
	
	/**
	 * Constructor that sets the board to its pregame state
	 */
	public Board() {
		this.reset();
	}
	
	/**
	 * Returns the Square at the specified coordinate
	 * @param x the horizontal coordinate in board
	 * @param y the vertical coordinate in board
	 * @return board[x][y] 
	 */
	public Square getSquare(int x, int y) {
		return board[x][y];
	}
	
	/**
	 * Returns the Square that the white king is currently on
	 * @return whiteKing white king's square
	 */
	public Square getWhiteKing() {
		return this.whiteKing;
	}
	
	/**
	 * Returns the Square that the black king is currently on
	 * @return blackKing black king's square
	 */
	public Square getBlackKing() {
		return this.blackKing;
	}
	
	/**
	 * Set the location of the white king
	 * @param square new square the white king is on
	 */
	public void setWhiteKing(Square square) {
		whiteKing = square;
	}
	
	/**
	 * Set the location of the black king
	 * @param square new square the black king is on
	 */
	public void setBlackKing(Square square) {
		blackKing = square;
	}
	
	/**
	 * Populates the board with Square objects
	 * and creates pieces at their starting positions
	 * 
	 * A square without a piece references null instead
	 */
	private void reset() {
        board[0][0] = new Square(0, 0, new Rook(true));
        board[1][0] = new Square(1, 0, new Knight(true));
        board[2][0] = new Square(2, 0, new Bishop(true));
        board[3][0] = new Square(3, 0, new Queen(true));
        board[4][0] = new Square(4, 0, new King(true));
        board[5][0] = new Square(5, 0, new Bishop(true));
        board[6][0] = new Square(6, 0, new Knight(true));
        board[7][0] = new Square(7, 0, new Rook(true));
        
        board[0][7] = new Square(0, 7, new Rook(false));
        board[1][7] = new Square(1, 7, new Knight(false));
        board[2][7] = new Square(2, 7, new Bishop(false));
        board[3][7] = new Square(3, 7, new Queen(false));
        board[4][7] = new Square(4, 7, new King(false));
        board[5][7] = new Square(5, 7, new Bishop(false));
        board[6][7] = new Square(6, 7, new Knight(false));
        board[7][7] = new Square(7, 7, new Rook(false));

        board[0][1] = new Square(0, 1, new Pawn(true));
        board[1][1] = new Square(1, 1, new Pawn(true));
        board[2][1] = new Square(2, 1, new Pawn(true));
        board[3][1] = new Square(3, 1, new Pawn(true));
        board[4][1] = new Square(4, 1, new Pawn(true));
        board[5][1] = new Square(5, 1, new Pawn(true));
        board[6][1] = new Square(6, 1, new Pawn(true));
        board[7][1] = new Square(7, 1, new Pawn(true));

        board[0][6] = new Square(0, 6, new Pawn(false));
        board[1][6] = new Square(1, 6, new Pawn(false));
        board[2][6] = new Square(2, 6, new Pawn(false));
        board[3][6] = new Square(3, 6, new Pawn(false));
        board[4][6] = new Square(4, 6, new Pawn(false));
        board[5][6] = new Square(5, 6, new Pawn(false));
        board[6][6] = new Square(6, 6, new Pawn(false));
        board[7][6] = new Square(7, 6, new Pawn(false));
        
        for (int i = 2; i < 6; i++) {
            for (int j = 0; j < 8; j++) {
                board[j][i] = new Square(j, i, null);
            }
        }
	}
	
	/**
	 * toString now returns a string visually representing the chess board
	 * 
	 * NOTE: The spacing is based on MS Gothic font with its unique unicode
	 * chess pieces
	 * @return returnVal
	 */
	@Override
	public String toString() {
		String returnVal = "";
        for (int i = 0; i < 8; i++) {
        	returnVal += "|----|----|----|----|----|----|----|----|\n";
        	returnVal += "|";
            for (int j = 0; j < 8; j++) {
            	if (board[j][i].getPiece() == null) {
            		returnVal += "    ";
            	}
            	else {
            		returnVal += (" " + board[j][i].getPiece() + " ");
            	}
                returnVal += "|";
            }
            returnVal += " " + (char)(i + 65);
            returnVal += "\n";
        }
        returnVal += "|----|----|----|----|----|----|----|----|\n";
        returnVal += "  1    2    3    4    5    6    7    8";
        return returnVal;
	}
	
	/**
	 * creates a semi deep copy of the original board 
	 * the squares each board references are different but the pieces are the same
	 * @return clone
	 */
	@Override
	public Object clone() {
		Board clone = new Board();
		clone.setWhiteKing(clone.getSquare(this.getWhiteKing().getX(), this.getWhiteKing().getY()));
		clone.setBlackKing(clone.getSquare(this.getBlackKing().getX(), this.getBlackKing().getY()));
		
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                clone.getSquare(j, i).setPiece(this.getSquare(j, i).getPiece());
            }
        }
		
		return clone;
	}
}
