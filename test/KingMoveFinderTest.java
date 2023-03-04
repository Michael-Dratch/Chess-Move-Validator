import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class KingMoveFinderTest extends MoveFinderTest {

	@BeforeEach
	void setUp() {
		this.moveFinder = new KingMoveFinder();
	}

	@Test
	void whiteKingA1BlockedAllSidesNoMoves() {
		int[][] board = getEmptyBoard();
		Piece piece = new Piece(Piece.PieceType.KING, Piece.Color.WHITE, new int[] { 0, 0 });
		placePieces(board, new int[][] { { 0, 1 }, { 1, 1 }, { 1, 0 } }, Color.WHITE);
		List<int[]> moves = this.moveFinder.findMoves(board, piece);
		assertFoundMovesCorrect(new int[][] {}, moves);
	}

	@Test
	void whiteKingE1BlockedAllSidesNoMoves() {
		int[][] board = getEmptyBoard();
		Piece piece = new Piece(Piece.PieceType.KING, Piece.Color.WHITE, new int[] { 0, 5 });
		placePieces(board, new int[][] { { 0, 4 }, { 0, 6 }, { 1, 4 }, { 1, 5 }, { 1, 6 } }, Color.WHITE);
		List<int[]> moves = this.moveFinder.findMoves(board, piece);
		assertFoundMovesCorrect(new int[][] {}, moves);
	}

	@Test
	void whiteKingD4BlockedAllSidesNoMoves() {
		int[][] board = getEmptyBoard();
		Piece piece = new Piece(Piece.PieceType.KING, Piece.Color.WHITE, new int[] { 3, 3 });
		placePieces(board,
				new int[][] { { 2, 2 }, { 2, 3 }, { 2, 4 }, { 3, 2 }, { 3, 4 }, { 4, 2 }, { 4, 3 }, { 4, 4 } },
				Color.WHITE);
		List<int[]> moves = this.moveFinder.findMoves(board, piece);
		assertFoundMovesCorrect(new int[][] {}, moves);
	}

	@Test
	void whiteKingD4BlockedAllSidesExceptTop() {
		int[][] board = getEmptyBoard();
		Piece piece = new Piece(Piece.PieceType.KING, Piece.Color.WHITE, new int[] { 3, 3 });
		placePieces(board, new int[][] { { 2, 2 }, { 2, 3 }, { 2, 4 }, { 3, 2 }, { 3, 4 }, { 4, 2 }, { 4, 4 } },
				Color.WHITE);
		int[][] expectedMoves = new int[][] { { 4, 3 } };
		List<int[]> moves = this.moveFinder.findMoves(board, piece);
		assertFoundMovesCorrect(expectedMoves, moves);
	}

	@Test
	void whiteKingD4BlockedAllSidesExceptBottom() {
		int[][] board = getEmptyBoard();
		Piece piece = new Piece(Piece.PieceType.KING, Piece.Color.WHITE, new int[] { 3, 3 });
		placePieces(board, new int[][] { { 2, 2 }, { 2, 4 }, { 3, 2 }, { 3, 4 }, { 4, 2 }, { 4, 3 }, { 4, 4 } },
				Color.WHITE);
		int[][] expectedMoves = new int[][] { { 2, 3 } };
		List<int[]> moves = this.moveFinder.findMoves(board, piece);
		assertFoundMovesCorrect(expectedMoves, moves);
	}

	@Test
	void whiteKingD4BlockedAllSidesExceptRight() {
		int[][] board = getEmptyBoard();
		Piece piece = new Piece(Piece.PieceType.KING, Piece.Color.WHITE, new int[] { 3, 3 });
		placePieces(board, new int[][] { { 2, 2 }, { 2, 3 }, { 2, 4 }, { 3, 2 }, { 4, 2 }, { 4, 3 }, { 4, 4 } },
				Color.WHITE);
		int[][] expectedMoves = new int[][] { { 3, 4 } };
		List<int[]> moves = this.moveFinder.findMoves(board, piece);
		assertFoundMovesCorrect(expectedMoves, moves);
	}

	@Test
	void whiteKingD4BlockedAllSidesExceptLeft() {
		{
			int[][] board = getEmptyBoard();
			Piece piece = new Piece(Piece.PieceType.KING, Piece.Color.WHITE, new int[] { 3, 3 });
			placePieces(board, new int[][] { { 2, 2 }, { 2, 3 }, { 2, 4 }, { 3, 4 }, { 4, 2 }, { 4, 3 }, { 4, 4 } },
					Color.WHITE);
			int[][] expectedMoves = new int[][] { { 3, 2 } };
			List<int[]> moves = this.moveFinder.findMoves(board, piece);
			assertFoundMovesCorrect(expectedMoves, moves);
		}
	}

	@Test
	void whiteKingD4BlockedAllSidesExceptTopRightCorner() {
		{
			int[][] board = getEmptyBoard();
			Piece piece = new Piece(Piece.PieceType.KING, Piece.Color.WHITE, new int[] { 3, 3 });
			placePieces(board, new int[][] { { 2, 2 }, { 2, 3 }, { 2, 4 }, { 3, 2 }, { 3, 4 }, { 4, 2 }, { 4, 3 } },
					Color.WHITE);
			int[][] expectedMoves = new int[][] { { 4, 4 } };
			List<int[]> moves = this.moveFinder.findMoves(board, piece);
			assertFoundMovesCorrect(expectedMoves, moves);
		}
	}

	@Test
	void whiteKingD4BlockedAllSidesExceptTopLeftCorner() {
		{
			int[][] board = getEmptyBoard();
			Piece piece = new Piece(Piece.PieceType.KING, Piece.Color.WHITE, new int[] { 3, 3 });
			placePieces(board, new int[][] { { 2, 2 }, { 2, 3 }, { 2, 4 }, { 3, 2 }, { 3, 4 }, { 4, 3 }, { 4, 4 } },
					Color.WHITE);
			int[][] expectedMoves = new int[][] { { 4, 2 } };
			List<int[]> moves = this.moveFinder.findMoves(board, piece);
			assertFoundMovesCorrect(expectedMoves, moves);
		}
	}

	@Test
	void whiteKingD4BlockedAllSidesExceptBottomLeftCorner() {
		{
			int[][] board = getEmptyBoard();
			Piece piece = new Piece(Piece.PieceType.KING, Piece.Color.WHITE, new int[] { 3, 3 });
			placePieces(board, new int[][] { { 2, 3 }, { 2, 4 }, { 3, 2 }, { 3, 4 }, { 4, 2 }, { 4, 3 }, { 4, 4 } },
					Color.WHITE);
			int[][] expectedMoves = new int[][] { { 2, 2 } };
			List<int[]> moves = this.moveFinder.findMoves(board, piece);
			assertFoundMovesCorrect(expectedMoves, moves);
		}
	}

	@Test
	void whiteKingD4BlockedAllSidesExceptBottomRightCorner() {
		{
			int[][] board = getEmptyBoard();
			Piece piece = new Piece(Piece.PieceType.KING, Piece.Color.WHITE, new int[] { 3, 3 });
			placePieces(board, new int[][] { { 2, 2 }, { 2, 3 }, { 3, 2 }, { 3, 4 }, { 4, 2 }, { 4, 3 }, { 4, 4 } },
					Color.WHITE);
			int[][] expectedMoves = new int[][] { { 2, 4 } };
			List<int[]> moves = this.moveFinder.findMoves(board, piece);
			assertFoundMovesCorrect(expectedMoves, moves);
		}
	}

	@Test
	void whiteKingD4BlackPiecesAllSides() {
		int[][] board = getEmptyBoard();
		Piece piece = new Piece(Piece.PieceType.KING, Piece.Color.WHITE, new int[] { 3, 3 });
		placePieces(board,
				new int[][] { { 2, 2 }, { 2, 3 }, { 2, 4 }, { 3, 2 }, { 3, 4 }, { 4, 2 }, { 4, 3 }, { 4, 4 } },
				Color.BLACK);
		int[][] expectedMoves = new int[][] { { 2, 2 }, { 2, 3 }, { 2, 4 }, { 3, 2 }, { 3, 4 }, { 4, 2 }, { 4, 3 },
				{ 4, 4 } };
		List<int[]> moves = this.moveFinder.findMoves(board, piece);
		assertFoundMovesCorrect(expectedMoves, moves);
	}

	@Test
	void blackKingH5MixedSidesColors() {
		int[][] board = getEmptyBoard();
		Piece piece = new Piece(Piece.PieceType.KING, Piece.Color.BLACK, new int[] { 4, 7 });
		placePieces(board, new int[][] { { 5, 7 }, { 5, 6 } }, Color.WHITE);

		placePieces(board, new int[][] { { 4, 6 }, { 3, 6 } }, Color.BLACK);

		int[][] expectedMoves = new int[][] { { 5, 7 }, { 5, 6 }, { 3, 7 } };
		List<int[]> moves = this.moveFinder.findMoves(board, piece);
		assertFoundMovesCorrect(expectedMoves, moves);
	}

	@Test
	void blackKingA7MixedSidesColors() {
		int[][] board = getEmptyBoard();
		Piece piece = new Piece(Piece.PieceType.KING, Piece.Color.BLACK, new int[] { 7, 0 });
		placePieces(board, new int[][] { { 7, 1 } }, Color.WHITE);

		placePieces(board, new int[][] { { 6, 0 } }, Color.BLACK);

		int[][] expectedMoves = new int[][] { { 7, 1 }, { 6, 1 } };
		List<int[]> moves = this.moveFinder.findMoves(board, piece);
		assertFoundMovesCorrect(expectedMoves, moves);
	}
}
