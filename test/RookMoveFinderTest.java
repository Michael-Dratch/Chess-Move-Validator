import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class RookMoveFinderTest extends MoveFinderTest {

	private enum Color {
		WHITE, BLACK
	}

	public void placePieces(int[][] board, int[][] positions, Color color) {
		int value = 1;
		if (color == Color.BLACK) {
			value = -1;
		}
		for (int[] position : positions) {
			board[position[0]][position[1]] = value;
		}
	}

	@BeforeEach
	void setUp() {
		this.moveFinder = new RookMoveFinder();
	}

	@Test
	void WhiteRookBlockedOnAllSidesNoMoves() {
		int[][] board = getEmptyBoard();
		Piece piece = new Piece(Piece.PieceType.ROOK, Piece.Color.WHITE, new int[] { 1, 1 });
		placePieces(board, new int[][] { { 2, 1 }, { 0, 1 }, { 1, 0 }, { 1, 2 } }, Color.WHITE);
		ArrayList<int[]> moves = this.moveFinder.findMoves(board, piece);
		assertFoundMovesCorrect(new int[][] {}, moves);
	}

	@Test
	void WhiteRookA1BlockedTopAndRightNoMoves() {
		int[][] board = getEmptyBoard();
		Piece piece = new Piece(Piece.PieceType.ROOK, Piece.Color.WHITE, new int[] { 0, 0 });
		placePieces(board, new int[][] { { 1, 0 }, { 0, 1 } }, Color.WHITE);
		ArrayList<int[]> moves = this.moveFinder.findMoves(board, piece);
		assertFoundMovesCorrect(new int[][] {}, moves);
	}

	@Test
	void WhiteRookA1EmptyBoard() {
		int[][] board = getEmptyBoard();
		Piece piece = new Piece(Piece.PieceType.ROOK, Piece.Color.WHITE, new int[] { 0, 0 });
		ArrayList<int[]> moves = this.moveFinder.findMoves(board, piece);
		int[][] expectedMoves = new int[][] { { 0, 1 }, { 0, 2 }, { 0, 3 }, { 0, 4 }, { 0, 5 }, { 0, 6 }, { 0, 7 },
				{ 1, 0 }, { 2, 0 }, { 3, 0 }, { 4, 0 }, { 5, 0 }, { 6, 0 }, { 7, 0 } };
		assertFoundMovesCorrect(expectedMoves, moves);
	}

	@Test
	void WhiteRookH8EmptyBoard() {
		int[][] board = getEmptyBoard();
		Piece piece = new Piece(Piece.PieceType.ROOK, Piece.Color.WHITE, new int[] { 7, 7 });
		ArrayList<int[]> moves = this.moveFinder.findMoves(board, piece);
		int[][] expectedMoves = new int[][] { { 7, 0 }, { 7, 1 }, { 7, 2 }, { 7, 3 }, { 7, 4 }, { 7, 5 }, { 7, 6 },
				{ 1, 7 }, { 2, 7 }, { 3, 7 }, { 4, 7 }, { 5, 7 }, { 6, 7 }, { 0, 7 } };
		assertFoundMovesCorrect(expectedMoves, moves);
	}

	@Test
	void WhiteRookD4EmptyBoard() {
		int[][] board = getEmptyBoard();
		Piece piece = new Piece(Piece.PieceType.ROOK, Piece.Color.WHITE, new int[] { 3, 3 });
		ArrayList<int[]> moves = this.moveFinder.findMoves(board, piece);
		int[][] expectedMoves = new int[][] { { 3, 0 }, { 3, 1 }, { 3, 2 }, { 3, 4 }, { 3, 5 }, { 3, 6 }, { 3, 7 },
				{ 0, 3 }, { 1, 3 }, { 2, 3 }, { 4, 3 }, { 5, 3 }, { 6, 3 }, { 7, 3 } };
		assertFoundMovesCorrect(expectedMoves, moves);
	}

	@Test
	void WhiteRookD4BlackPieceOnTop() {
		int[][] board = getEmptyBoard();
		board[4][3] = -1;
		Piece piece = new Piece(Piece.PieceType.ROOK, Piece.Color.WHITE, new int[] { 3, 3 });
		ArrayList<int[]> moves = this.moveFinder.findMoves(board, piece);
		int[][] expectedMoves = new int[][] { { 3, 0 }, { 3, 1 }, { 3, 2 }, { 3, 4 }, { 3, 5 }, { 3, 6 }, { 3, 7 },
				{ 0, 3 }, { 1, 3 }, { 2, 3 }, { 4, 3 } };
		assertFoundMovesCorrect(expectedMoves, moves);
	}

	@Test
	void WhiteRookD4WhitePieceOnTop() {
		int[][] board = getEmptyBoard();
		board[4][3] = 1;
		Piece piece = new Piece(Piece.PieceType.ROOK, Piece.Color.WHITE, new int[] { 3, 3 });
		ArrayList<int[]> moves = this.moveFinder.findMoves(board, piece);
		int[][] expectedMoves = new int[][] { { 3, 0 }, { 3, 1 }, { 3, 2 }, { 3, 4 }, { 3, 5 }, { 3, 6 }, { 3, 7 },
				{ 0, 3 }, { 1, 3 }, { 2, 3 } };
		assertFoundMovesCorrect(expectedMoves, moves);
	}

	@Test
	void BlackRookD4BlackPieceOnTop() {
		int[][] board = getEmptyBoard();
		board[4][3] = -1;
		Piece piece = new Piece(Piece.PieceType.ROOK, Piece.Color.BLACK, new int[] { 3, 3 });
		ArrayList<int[]> moves = this.moveFinder.findMoves(board, piece);
		int[][] expectedMoves = new int[][] { { 3, 0 }, { 3, 1 }, { 3, 2 }, { 3, 4 }, { 3, 5 }, { 3, 6 }, { 3, 7 },
				{ 0, 3 }, { 1, 3 }, { 2, 3 } };
		assertFoundMovesCorrect(expectedMoves, moves);
	}

	@Test
	void BlackRookD4WhitePieceOnTop() {
		int[][] board = getEmptyBoard();
		board[4][3] = 1;
		Piece piece = new Piece(Piece.PieceType.ROOK, Piece.Color.BLACK, new int[] { 3, 3 });
		ArrayList<int[]> moves = this.moveFinder.findMoves(board, piece);
		int[][] expectedMoves = new int[][] { { 3, 0 }, { 3, 1 }, { 3, 2 }, { 3, 4 }, { 3, 5 }, { 3, 6 }, { 3, 7 },
				{ 0, 3 }, { 1, 3 }, { 2, 3 }, { 4, 3 } };
		assertFoundMovesCorrect(expectedMoves, moves);
	}

	@Test
	void BlackRookF5MixedPiecesOnAllSidesDifferentDistances() {
		int[][] board = getEmptyBoard();
		Piece piece = new Piece(Piece.PieceType.ROOK, Piece.Color.BLACK, new int[] { 4, 5 });
		placePieces(board, new int[][] { { 6, 5 }, { 4, 0 } }, Color.WHITE);
		placePieces(board, new int[][] { { 1, 5 }, { 4, 7 } }, Color.BLACK);
		ArrayList<int[]> moves = this.moveFinder.findMoves(board, piece);
		int[][] expectedMoves = new int[][] { { 5, 5 }, { 6, 5 }, { 2, 5 }, { 3, 5 }, { 4, 0 }, { 4, 1 }, { 4, 2 },
				{ 4, 3 }, { 4, 4 }, { 4, 6 } };
		assertFoundMovesCorrect(expectedMoves, moves);
	}

}
