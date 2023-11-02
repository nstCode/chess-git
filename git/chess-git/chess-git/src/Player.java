/**
 * 
 * @author Noah Thompson
 * Represents a player
 *
 */


public class Player {
	public boolean isWhite;
	
	/**
	 * Constructs a player of a determined color
	 * @param isWhite color of the player 
	 * 				  true is white false is black
	 */
	public Player(boolean isWhite) {
		this.isWhite = isWhite;
	}
	
	/**
	 * Return the color of the player
	 * @return isWhite color of the player 
	 * 				   true is white false is black
	 */
	public boolean isWhite() {
		return this.isWhite;
	}
	
	/**
	 * Executes the given move
	 * returns false if move cannot be executed
	 * @param move move to be performed
	 * @param chessBoard Board for the move to be enacted upon
	 * @param prevChessBoard Board to hold the previous board state
	 * @return True if successful, false if not
	 */
	public boolean makeMove(Move move, Board chessBoard, Board prevChessBoard) {
		boolean firstMove = true;
		prevChessBoard = (Board)chessBoard.clone();
		
		if(move.getStart().getPiece() != null) {
			if(this.isWhite == move.getStart().getPiece().isWhite() && 
				move.getStart().getPiece().isLegalMove(chessBoard, move)) {
				firstMove = move.getStart().getPiece().hasNotMoved();
				
				//Executes castle if move is applicable
				if (move.getStart().getPiece() instanceof King && move.getEnd().getPiece() instanceof Rook && 
						move.getStart().getPiece().isWhite() == move.getEnd().getPiece().isWhite()) {
					castle(move, chessBoard);
					return true;
				}
				else {
					//Removes Captured Piece if En Passent Capture
					if(move.getStart().getPiece() instanceof Pawn && move.getEnd().getPiece() == null && move.getStart().getX() != move.getEnd().getX()) {
						chessBoard.getSquare(move.getEnd().getX(), move.getStart().getY()).setPiece(null); //en pessent
					}
					
					move.getEnd().setPiece(move.getStart().getPiece());
					move.getStart().setPiece(null);
					
					//updates King's locations if they moved
					if (move.getEnd().getPiece().isWhite() && move.getEnd().getPiece() instanceof King) {
						chessBoard.setWhiteKing(move.getEnd()); 
					}
					else if (!move.getEnd().getPiece().isWhite() && move.getEnd().getPiece() instanceof King) {
						chessBoard.setBlackKing(move.getEnd()); 
					}
					//if the move results with the active player's king in check the move is undone and returned false
					if (move.getEnd().getPiece().isWhite() && Game.isCheck(chessBoard.getWhiteKing())) {
						undoMove(move, firstMove, chessBoard, prevChessBoard);
					}
					else if (!move.getEnd().getPiece().isWhite() && Game.isCheck(chessBoard.getBlackKing())) {
						undoMove(move, firstMove, chessBoard, prevChessBoard);
					}
					else {
						return true;
					}
				}
			}
		}
		return false;
	}
	
	/**
	 * Returns game to the previous board state
	 * @param move Move to be undone
	 * @param firstMove if this was the Pieces first move
	 * @param chessBoard Current board state
	 * @param prevChessBoard Previous board state
	 */
	public void undoMove(Move move, boolean firstMove, Board chessBoard, Board prevChessBoard) {
		//reset king locations
		chessBoard.setWhiteKing(chessBoard.getSquare(prevChessBoard.getWhiteKing().getX(), prevChessBoard.getWhiteKing().getY()));
		chessBoard.setBlackKing(chessBoard.getSquare(prevChessBoard.getBlackKing().getX(), prevChessBoard.getBlackKing().getY()));
		//return all Pieces to their original Squares
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                chessBoard.getSquare(j, i).setPiece(prevChessBoard.getSquare(j, i).getPiece());
            }
        }
		move.getStart().getPiece().setHasNotMoved(firstMove);
	}
	
	/**
	 * Executes a castle move
	 * @param move proposed castle
	 * @param chessBoard board for the move to be enacted upon
	 */
	private void castle(Move move, Board chessBoard) {
		//if castling right
		if (move.getEnd().getX() > move.getStart().getX()) {
			//perform castle
			chessBoard.getSquare(6, move.getStart().getY()).setPiece(move.getStart().getPiece());
			chessBoard.getSquare(5, move.getStart().getY()).setPiece(move.getEnd().getPiece());
			move.getStart().setPiece(null);
			move.getEnd().setPiece(null);
			//update king's position
			if(chessBoard.getSquare(6, move.getStart().getY()).getPiece().isWhite()) {
				chessBoard.setWhiteKing(chessBoard.getSquare(6, move.getStart().getY())); 
			}
			else {
				chessBoard.setBlackKing(chessBoard.getSquare(6, move.getStart().getY()));
			}
		}
		//if castling left
		else {
			chessBoard.getSquare(2, move.getStart().getY()).setPiece(move.getStart().getPiece());
			chessBoard.getSquare(3, move.getStart().getY()).setPiece(move.getEnd().getPiece());
			move.getStart().setPiece(null);
			move.getEnd().setPiece(null);
			if(chessBoard.getSquare(2, move.getStart().getY()).getPiece().isWhite()) {
				chessBoard.setWhiteKing(chessBoard.getSquare(2, move.getStart().getY()));
			}
			else {
				chessBoard.setBlackKing(chessBoard.getSquare(2, move.getStart().getY()));
			}
		}
	}
	
	/**
	 * Prints the color of the player
	 */
	@Override
	public String toString() {
		if (isWhite()) {
			return "White";
		}
		else {
			return "Black";
		}
	}
}
