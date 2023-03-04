import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class QueenMoveFinderTest extends MoveFinderTest {

	@BeforeEach
	public void setUp() {
		this.moveFinder = new QueenMoveFinder();
	}

	@Test
	void whiteQueenBlockedOnAllSidesNoMoves() {
		int[][] board = getEmptyBoard();
		Piece piece = new Piece(Piece.PieceType.QUEEN, Piece.Color.WHITE, new int[] { 1, 1 });
		placePieces(board,
				new int[][] { { 2, 1 }, { 0, 1 }, { 1, 0 }, { 1, 2 }, { 2, 0 }, { 2, 2 }, { 0, 0 }, { 0, 2 } },
				Color.WHITE);
		List<int[]> moves = this.moveFinder.findMoves(board, piece);
		assertFoundMovesCorrect(new int[][] {}, moves);
	}

	@Test
	void whiteQueenA1BlockedTopRightAndDiagonalHasNoMoves() {
		int[][] board = getEmptyBoard();
		Piece piece = new Piece(Piece.PieceType.QUEEN, Piece.Color.WHITE, new int[] { 0, 0 });
		placePieces(board, new int[][] { { 1, 0 }, { 0, 1 }, { 1, 1 } }, Color.WHITE);
		List<int[]> moves = this.moveFinder.findMoves(board, piece);
		assertFoundMovesCorrect(new int[][] {}, moves);
	}

	@Test
	void whiteQueenH8BlockedBottomLeftAndDiagonalHasNoMoves() {
		int[][] board = getEmptyBoard();
		Piece piece = new Piece(Piece.PieceType.QUEEN, Piece.Color.WHITE, new int[] { 7, 7 });
		placePieces(board, new int[][] { { 7, 6 }, { 6, 7 }, { 6, 6 } }, Color.WHITE);
		List<int[]> moves = this.moveFinder.findMoves(board, piece);
		assertFoundMovesCorrect(new int[][] {}, moves);
	}

	@Test
	void whiteQueenA1EmptyBoard() {
		int[][] board = getEmptyBoard();
		Piece piece = new Piece(Piece.PieceType.QUEEN, Piece.Color.WHITE, new int[] { 0, 0 });
		int[][] expectedMoves = new int[][] { { 0, 1 }, { 0, 2 }, { 0, 3 }, { 0, 4 }, { 0, 5 }, { 0, 6 }, { 0, 7 },
				{ 1, 0 }, { 2, 0 }, { 3, 0 }, { 4, 0 }, { 5, 0 }, { 6, 0 }, { 7, 0 }, { 1, 1 }, { 2, 2 }, { 3, 3 },
				{ 4, 4 }, { 5, 5 }, { 6, 6 }, { 7, 7 } };
		List<int[]> moves = this.moveFinder.findMoves(board, piece);
		assertFoundMovesCorrect(expectedMoves, moves);
	}

	@Test
	void whiteQueenD4EmptyBoard() {
		int[][] board = getEmptyBoard();
		Piece piece = new Piece(Piece.PieceType.QUEEN, Piece.Color.WHITE, new int[] { 3, 3 });
		int[][] expectedMoves = new int[][] { { 3, 0 }, { 3, 1 }, { 3, 2 }, { 3, 4 }, { 3, 5 }, { 3, 6 }, { 3, 7 },
				{ 0, 3 }, { 1, 3 }, { 2, 3 }, { 4, 3 }, { 5, 3 }, { 6, 3 }, { 7, 3 }, { 0, 0 }, { 1, 1 }, { 2, 2 },
				{ 4, 4 }, { 5, 5 }, { 6, 6 }, { 7, 7 }, { 0, 6 }, { 1, 5 }, { 2, 4 }, { 4, 2 }, { 5, 1 }, { 6, 0 } };
		List<int[]> moves = this.moveFinder.findMoves(board, piece);
		assertFoundMovesCorrect(expectedMoves, moves);
	}

	@Test
	void whiteQueenD4SurroundedByBlackPieces() {
		int[][] board = getEmptyBoard();
		Piece piece = new Piece(Piece.PieceType.QUEEN, Piece.Color.WHITE, new int[] { 1, 1 });
		placePieces(board,
				new int[][] { { 2, 1 }, { 0, 1 }, { 1, 0 }, { 1, 2 }, { 2, 0 }, { 2, 2 }, { 0, 0 }, { 0, 2 } },
				Color.BLACK);
		int[][] expectedMoves = new int[][] { { 2, 1 }, { 0, 1 }, { 1, 0 }, { 1, 2 }, { 2, 0 }, { 2, 2 }, { 0, 0 },
				{ 0, 2 } };
		List<int[]> moves = this.moveFinder.findMoves(board, piece);
		assertFoundMovesCorrect(expectedMoves, moves);
	}

	@Test
	void blackQueenD4SurroundedByWhitePieces() {
		int[][] board = getEmptyBoard();
		Piece piece = new Piece(Piece.PieceType.QUEEN, Piece.Color.BLACK, new int[] { 1, 1 });
		placePieces(board,
				new int[][] { { 2, 1 }, { 0, 1 }, { 1, 0 }, { 1, 2 }, { 2, 0 }, { 2, 2 }, { 0, 0 }, { 0, 2 } },
				Color.WHITE);
		int[][] expectedMoves = new int[][] { { 2, 1 }, { 0, 1 }, { 1, 0 }, { 1, 2 }, { 2, 0 }, { 2, 2 }, { 0, 0 },
				{ 0, 2 } };
		List<int[]> moves = this.moveFinder.findMoves(board, piece);
		assertFoundMovesCorrect(expectedMoves, moves);
	}

	@Test
	void blackQueenD4SurroundedByWhitePiecesTwoSpacesAway() {
		int[][] board = getEmptyBoard();
		Piece piece = new Piece(Piece.PieceType.QUEEN, Piece.Color.BLACK, new int[] { 2, 2 });
		placePieces(board,
				new int[][] { { 2, 0 }, { 2, 4 }, { 0, 2 }, { 4, 2 }, { 0, 0 }, { 0, 4 }, { 4, 0 }, { 4, 4 } },
				Color.WHITE);
		int[][] expectedMoves = new int[][] { { 2, 0 }, { 2, 4 }, { 0, 2 }, { 4, 2 }, { 0, 0 }, { 0, 4 }, { 4, 0 },
				{ 4, 4 }, { 1, 1 }, { 1, 2 }, { 1, 3 }, { 2, 1 }, { 2, 3 }, { 3, 1 }, { 3, 2 }, { 3, 3 } };
		List<int[]> moves = this.moveFinder.findMoves(board, piece);
		assertFoundMovesCorrect(expectedMoves, moves);
	}

	@Test
	void whiteQueenE5MixedPiecesVaryingDistances() {
		int[][] board = getEmptyBoard();
		Piece piece = new Piece(Piece.PieceType.QUEEN, Piece.Color.WHITE, new int[] { 4, 4 });
		placePieces(board, new int[][] { { 5, 4 }, { 4, 6 }, { 2, 2 } }, Color.WHITE);
		placePieces(board, new int[][] { { 3, 4 }, { 7, 7 } }, Color.BLACK);
		int[][] expectedMoves = new int[][] { { 3, 4 }, { 4, 5 }, { 4, 0 }, { 4, 1 }, { 4, 2 }, { 4, 3 }, { 3, 3 },
				{ 5, 5 }, { 6, 6 }, { 7, 7 }, { 1, 7 }, { 2, 6 }, { 3, 5 }, { 5, 3 }, { 6, 2 }, { 7, 1 } };
		List<int[]> moves = this.moveFinder.findMoves(board, piece);
		assertFoundMovesCorrect(expectedMoves, moves);
	}

	@Test
	void blackQueenE5MixedPiecesVaryingDistances() {
		int[][] board = getEmptyBoard();
		Piece piece = new Piece(Piece.PieceType.QUEEN, Piece.Color.BLACK, new int[] { 4, 4 });
		placePieces(board, new int[][] { { 5, 4 }, { 4, 6 }, { 2, 2 }, { 1, 7 } }, Color.WHITE);
		placePieces(board, new int[][] { { 3, 4 }, { 7, 7 }, { 6, 2 }, { 4, 2 } }, Color.BLACK);
		int[][] expectedMoves = new int[][] { { 5, 4 }, { 4, 5 }, { 4, 6 }, { 4, 3 }, { 1, 7 }, { 2, 6 }, { 3, 5 },
				{ 5, 3 }, { 2, 2 }, { 3, 3 }, { 5, 5 }, { 6, 6 } };

		List<int[]> moves = this.moveFinder.findMoves(board, piece);
		assertFoundMovesCorrect(expectedMoves, moves);
	}
}
