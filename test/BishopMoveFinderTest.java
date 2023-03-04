import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class BishopMoveFinderTest extends MoveFinderTest {

	@BeforeEach
	void setUp() {
		this.moveFinder = new BishopMoveFinder();
	}

	@Test
	void whiteBishopA1BlockedAllCornersNoMoves() {
		int[][] board = getEmptyBoard();
		Piece piece = new Piece(Piece.PieceType.KING, Piece.Color.WHITE, new int[] { 0, 0 });
		placePieces(board, new int[][] { { 1, 1 } }, Color.WHITE);
		List<int[]> moves = this.moveFinder.findMoves(board, piece);
		assertFoundMovesCorrect(new int[][] {}, moves);
	}

	@Test
	void whiteBishopE1BlockedAllCornersNoMoves() {
		int[][] board = getEmptyBoard();
		Piece piece = new Piece(Piece.PieceType.KING, Piece.Color.WHITE, new int[] { 0, 5 });
		placePieces(board, new int[][] { { 1, 4 }, { 1, 6 } }, Color.WHITE);
		List<int[]> moves = this.moveFinder.findMoves(board, piece);
		assertFoundMovesCorrect(new int[][] {}, moves);
	}

	@Test
	void whiteBishopD4BlockedAllCornersNoMoves() {
		int[][] board = getEmptyBoard();
		Piece piece = new Piece(Piece.PieceType.KING, Piece.Color.WHITE, new int[] { 3, 3 });
		placePieces(board, new int[][] { { 2, 2 }, { 2, 4 }, { 4, 2 }, { 4, 4 } }, Color.WHITE);
		List<int[]> moves = this.moveFinder.findMoves(board, piece);
		assertFoundMovesCorrect(new int[][] {}, moves);
	}

	@Test
	void whiteBishopD4BlockedAllCornersExceptTopRight() {
		int[][] board = getEmptyBoard();
		Piece piece = new Piece(Piece.PieceType.KING, Piece.Color.WHITE, new int[] { 3, 3 });
		placePieces(board, new int[][] { { 2, 2 }, { 2, 4 }, { 4, 2 } }, Color.WHITE);
		int[][] expectedMoves = new int[][] { { 4, 4 }, { 5, 5 }, { 6, 6 }, { 7, 7 } };
		List<int[]> moves = this.moveFinder.findMoves(board, piece);
		assertFoundMovesCorrect(expectedMoves, moves);
	}

	@Test
	void whiteBishopD4BlockedAllCornersExceptBottomRight() {
		int[][] board = getEmptyBoard();
		Piece piece = new Piece(Piece.PieceType.KING, Piece.Color.WHITE, new int[] { 3, 3 });
		placePieces(board, new int[][] { { 2, 2 }, { 4, 2 }, { 4, 4 } }, Color.WHITE);
		int[][] expectedMoves = new int[][] { { 2, 4 }, { 1, 5 }, { 0, 6 } };
		List<int[]> moves = this.moveFinder.findMoves(board, piece);
		assertFoundMovesCorrect(expectedMoves, moves);
	}

	@Test
	void whiteBishopD4BlockedAllCornersExceptTopLeft() {
		int[][] board = getEmptyBoard();
		Piece piece = new Piece(Piece.PieceType.KING, Piece.Color.WHITE, new int[] { 3, 3 });
		placePieces(board, new int[][] { { 2, 2 }, { 2, 4 }, { 4, 4 } }, Color.WHITE);
		int[][] expectedMoves = new int[][] { { 4, 2 }, { 5, 1 }, { 6, 0 } };
		List<int[]> moves = this.moveFinder.findMoves(board, piece);
		assertFoundMovesCorrect(expectedMoves, moves);
	}

	@Test
	void whiteBishopD4BlockedAllCornersExceptBottomLeft() {
		{
			int[][] board = getEmptyBoard();
			Piece piece = new Piece(Piece.PieceType.KING, Piece.Color.WHITE, new int[] { 3, 3 });
			placePieces(board, new int[][] { { 2, 4 }, { 4, 2 }, { 4, 4 } }, Color.WHITE);
			int[][] expectedMoves = new int[][] { { 2, 2 }, { 1, 1 }, { 0, 0 } };
			List<int[]> moves = this.moveFinder.findMoves(board, piece);
			assertFoundMovesCorrect(expectedMoves, moves);
		}
	}

	@Test
	void whiteBishopD4BlackPiecesAllCorners() {
		int[][] board = getEmptyBoard();
		Piece piece = new Piece(Piece.PieceType.KING, Piece.Color.WHITE, new int[] { 3, 3 });
		placePieces(board, new int[][] { { 2, 2 }, { 2, 4 }, { 4, 2 }, { 4, 4 } }, Color.BLACK);
		int[][] expectedMoves = new int[][] { { 2, 2 }, { 2, 4 }, { 4, 2 }, { 4, 4 } };
		List<int[]> moves = this.moveFinder.findMoves(board, piece);
		assertFoundMovesCorrect(expectedMoves, moves);
	}

	@Test
	void blackBishopD5MixedCornerColorsAndDistances() {
		int[][] board = getEmptyBoard();
		Piece piece = new Piece(Piece.PieceType.KING, Piece.Color.BLACK, new int[] { 4, 4 });
		placePieces(board, new int[][] { { 3, 3 }, { 1, 7 } }, Color.WHITE);

		placePieces(board, new int[][] { { 7, 7 } }, Color.BLACK);

		int[][] expectedMoves = new int[][] { { 3, 3 }, { 1, 7 }, { 2, 6 }, { 3, 5 }, { 5, 5 }, { 6, 6 }, { 5, 3 },
				{ 6, 2 }, { 7, 1 } };
		List<int[]> moves = this.moveFinder.findMoves(board, piece);
		assertFoundMovesCorrect(expectedMoves, moves);
	}

	@Test
	void blackBishopE7MixedCornerColorsAndDistances() {
		int[][] board = getEmptyBoard();
		Piece piece = new Piece(Piece.PieceType.KING, Piece.Color.BLACK, new int[] { 7, 4 });
		placePieces(board, new int[][] { { 5, 6 } }, Color.WHITE);

		placePieces(board, new int[][] { { 4, 1 } }, Color.BLACK);

		int[][] expectedMoves = new int[][] { { 5, 6 }, { 6, 5 }, { 5, 2 }, { 6, 3 } };
		List<int[]> moves = this.moveFinder.findMoves(board, piece);
		assertFoundMovesCorrect(expectedMoves, moves);
	}
}
