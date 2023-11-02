/**
 * 
 * @author Noah Thompson
 * Operates the game from main
 * Takes user input
 * Performs automatic checks of the game state
 * 
 */


import java.util.Scanner;

public class Game {
	static private Board chessBoard = new Board();
	static private Board prevChessBoard = new Board();
	static public Move prevMove;
	static private int startYParse, startXParse, endYParse, endXParse;
	
	/**
	 * Determines weather the specified square is in check
	 * @param king Square that is being tested
	 * @return True if the Square is in check false if not
	 */
	public static boolean isCheck(Square king) {
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (chessBoard.getSquare(i, j).getPiece() != null) {
					if (!(chessBoard.getSquare(i, j).getPiece() instanceof King) && 
						chessBoard.getSquare(i, j).getPiece().isLegalMove(chessBoard, new Move(chessBoard.getSquare(i, j), king))) {
						return true;
					}
				}
			}
		}
		return false;
	}   
	
	/**
	 * Determines weather the specified player is in check mate
	 * @param defendingPlayer player to be tested
	 * @return True if the player is in check mate false if not
	 */
	private static boolean isCheckmate(Player defendingPlayer) {
		boolean firstMove = true;
		Move simMove;
		//outer two loops represent the coordinates of the start of a move
		for (int y = 0; y < 8; y++) {
			for (int x = 0; x < 8; x++) {
				//inner two loops represent the coordinates of the end of a move
				for (int y2 = 0; y2 < 8; y2++) {
					for (int x2 = 0; x2 < 8; x2++) {
						simMove = new Move(chessBoard.getSquare(x, y), chessBoard.getSquare(x2, y2));
						if (simMove.getStart().getPiece() != null) {
							firstMove = simMove.getStart().getPiece().hasNotMoved();
						}
						//simulates possible move, if successful the move is undone and returns false
						if (defendingPlayer.makeMove(simMove, chessBoard, prevChessBoard)) {
							defendingPlayer.undoMove(simMove, firstMove, chessBoard, prevChessBoard);
							return false;
						}
					}
				}
			}
		}
		return true;
	}
	
	/**
	 * Checks if the piece on the square is promotable
	 * @param square Square with the possibly promotable Piece
	 * @return True if the piece is promotable false if not
	 */
	private static boolean isPromotable(Square square) {
		if (square.getPiece() instanceof Pawn) {
			if (square.getPiece().isWhite() && square.getY() == 7) {
				return true;
			}
			else if (!square.getPiece().isWhite() && square.getY() == 0){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Prompts Player to promote the Piece and then does so
	 * @param square Square with the Piece to be promoted
	 * @param scan Scanner to take input
	 */
	private static void promote(Square square, Scanner scan) {
		String piece;
		System.out.println("What piece would you like to promote to?");
		//waits until valid response received
		while(true) {
			piece = scan.next();
			if (piece.equals("Queen") || piece.equals("Bishop") || piece.equals("Knight") || piece.equals("Rook")) {
				break;
			}
			System.out.println("Piece not allowed");
		}
		//replaces Piece based on response
		switch(piece) {
			case "Queen":
				square.setPiece(new Queen(square.getPiece().isWhite()));
				break;
			case "Bishop":
				square.setPiece(new Bishop(square.getPiece().isWhite()));
				break;
			case "Knight":
				square.setPiece(new Knight(square.getPiece().isWhite()));
				break;
			case "Rook":
				square.setPiece(new Rook(square.getPiece().isWhite()));
				break;
		}
	}
	
	/**
	 * Takes user input of letter-number letter-number and converts 
	 * to two x/y coordinate pairs
	 * @param moveInputStart Letter-number representing the starting Square of a Move
	 * @param moveInputEnd Letter-number representing the ending Square of a Move
	 */
	private static void parseInput(String moveInputStart, String moveInputEnd) {
		startYParse = (int)(moveInputStart.charAt(0)) - 65;
		startXParse = (int)(moveInputStart.charAt(1)) - 49;
		endYParse = (int)(moveInputEnd.charAt(0)) - 65;
		endXParse = (int)(moveInputEnd.charAt(1)) - 49;
	}
	
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		Player whitePlayer = new Player(true);
		Player blackPlayer = new Player(false);
		Player activePlayer = whitePlayer;
		Move move = null;
		boolean validMove;
		boolean checkmate = false;
		
		System.out.println(chessBoard);
		
		//main game loop
		while(!checkmate) {
			System.out.println(activePlayer + "'s Turn.");
			//waits for user to input a valid move and then performs the valid move
			do {
				parseInput(scan.next(), scan.next());
				try {
					move = new Move(chessBoard.getSquare(startXParse, startYParse), 
									chessBoard.getSquare(endXParse, endYParse));
					validMove = activePlayer.makeMove(move, chessBoard, prevChessBoard);
				} catch (IndexOutOfBoundsException e) {
					validMove = false;
				}
				if(!validMove) {
					System.out.println("Illegal Move");
				}
			} while(!validMove);
			//stores the move as the previous move exclusively for En Passent functionality
			prevMove = move;
			//promotes pawns that reach the end of the board
			if (move.getEnd() != null) {
				if (isPromotable(move.getEnd())) {
					promote(move.getEnd(), scan);
				}
			}
			//swaps active player
			if (activePlayer.isWhite()) {
				activePlayer = blackPlayer;
			}
			else {
				activePlayer = whitePlayer;
			}
			//determines if the now activePlayer is in check mate
			if (isCheck(chessBoard.getBlackKing()) || isCheck(chessBoard.getWhiteKing())) {
				if (isCheckmate(activePlayer)) {
					checkmate = true;
				}
			}
			System.out.println(chessBoard);
		}
		//Swaps active player back to the winning player
		if (activePlayer.isWhite()) {
			activePlayer = blackPlayer;
		}
		else {
			activePlayer = whitePlayer;
		}
		System.out.println(activePlayer + " Wins!");
		scan.close();
	}
}
