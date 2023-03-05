import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class KnightMoveFinderTest extends MoveFinderTest {

	@BeforeEach
	void setUp() {
		this.moveFinder = new KnightMoveFinder();
	}

	@Test
	void whiteKnightB1AllMovesBlocked() {
		int[][] board = getEmptyBoard();
		Piece piece = new Piece(Piece.PieceType.KNIGHT, Piece.Color.WHITE, new int[] { 0, 1 });
		placePieces(board, new int[][] { { 2, 0 }, { 2, 2 }, { 1, 3 } }, Color.WHITE);
		List<int[]> moves = this.moveFinder.findMoves(board, piece);
		assertFoundMovesCorrect(new int[][] {}, moves);
	}

	@Test
	void whiteKnightB1EmptyBoard() {
		int[][] board = getEmptyBoard();
		Piece piece = new Piece(Piece.PieceType.KNIGHT, Piece.Color.WHITE, new int[] { 0, 1 });
		List<int[]> moves = this.moveFinder.findMoves(board, piece);
		int[][] expectedMoves = new int[][] { { 2, 0 }, { 2, 2 }, { 1, 3 } };
		assertFoundMovesCorrect(expectedMoves, moves);
	}

	@Test
	void whiteKnightD4EmptyBoard() {
		int[][] board = getEmptyBoard();
		Piece piece = new Piece(Piece.PieceType.KNIGHT, Piece.Color.WHITE, new int[] { 3, 3 });
		List<int[]> moves = this.moveFinder.findMoves(board, piece);
		int[][] expectedMoves = new int[][] { { 1, 2 }, { 2, 1 }, { 1, 4 }, { 2, 5 }, { 4, 5 }, { 5, 4 }, { 1, 4 },
				{ 2, 5 } };
		assertFoundMovesCorrect(expectedMoves, moves);
	}

	@Test
	void whiteKnightH8EmptyBoard() {
		int[][] board = getEmptyBoard();
		Piece piece = new Piece(Piece.PieceType.KNIGHT, Piece.Color.WHITE, new int[] { 7, 7 });
		List<int[]> moves = this.moveFinder.findMoves(board, piece);
		int[][] expectedMoves = new int[][] { { 6, 5 }, { 5, 6 } };
		assertFoundMovesCorrect(expectedMoves, moves);
	}

	@Test
	void whiteKnightB1AllMovesBlackPieces() {
		int[][] board = getEmptyBoard();
		Piece piece = new Piece(Piece.PieceType.KNIGHT, Piece.Color.WHITE, new int[] { 0, 1 });
		placePieces(board, new int[][] { { 2, 0 }, { 2, 2 }, { 1, 3 } }, Color.BLACK);
		int[][] expectedMoves = new int[][] { { 2, 0 }, { 2, 2 }, { 1, 3 } };
		List<int[]> moves = this.moveFinder.findMoves(board, piece);
		assertFoundMovesCorrect(expectedMoves, moves);
	}

	@Test
	void whiteKnightD4AllMovesBlackPieces() {
		int[][] board = getEmptyBoard();
		Piece piece = new Piece(Piece.PieceType.KNIGHT, Piece.Color.WHITE, new int[] { 3, 3 });
		placePieces(board,
				new int[][] { { 1, 2 }, { 2, 1 }, { 1, 4 }, { 2, 5 }, { 4, 5 }, { 5, 4 }, { 4, 1 }, { 5, 2 } },
				Color.BLACK);
		List<int[]> moves = this.moveFinder.findMoves(board, piece);
		int[][] expectedMoves = new int[][] { { 1, 2 }, { 2, 1 }, { 1, 4 }, { 2, 5 }, { 4, 5 }, { 5, 4 }, { 4, 1 },
				{ 5, 2 } };
		assertFoundMovesCorrect(expectedMoves, moves);
	}

	@Test
	void BlackKnightD4AllMovesWhitePieces() {
		int[][] board = getEmptyBoard();
		Piece piece = new Piece(Piece.PieceType.KNIGHT, Piece.Color.BLACK, new int[] { 3, 3 });
		placePieces(board,
				new int[][] { { 1, 2 }, { 2, 1 }, { 1, 4 }, { 2, 5 }, { 4, 5 }, { 5, 4 }, { 4, 1 }, { 5, 2 } },
				Color.WHITE);
		List<int[]> moves = this.moveFinder.findMoves(board, piece);
		int[][] expectedMoves = new int[][] { { 1, 2 }, { 2, 1 }, { 1, 4 }, { 2, 5 }, { 4, 5 }, { 4, 1 }, { 5, 2 },
				{ 2, 5 } };
		assertFoundMovesCorrect(expectedMoves, moves);
	}

	@Test
	void BlackKnightD4HalfMovesWhitePieces() {
		int[][] board = getEmptyBoard();
		Piece piece = new Piece(Piece.PieceType.KNIGHT, Piece.Color.BLACK, new int[] { 3, 3 });
		placePieces(board, new int[][] { { 1, 2 }, { 2, 1 }, { 1, 4 }, { 2, 5 } }, Color.WHITE);
		placePieces(board, new int[][] { { 4, 5 }, { 5, 4 }, { 4, 1 }, { 5, 2 } }, Color.BLACK);
		List<int[]> moves = this.moveFinder.findMoves(board, piece);
		int[][] expectedMoves = new int[][] { { 1, 2 }, { 2, 1 }, { 1, 4 }, { 2, 5 } };
		assertFoundMovesCorrect(expectedMoves, moves);
	}

	@Test
	void BlackKnightD4PotentialSpacesHaveMixedColorPiecesAndEmpty() {
		int[][] board = getEmptyBoard();
		Piece piece = new Piece(Piece.PieceType.KNIGHT, Piece.Color.BLACK, new int[] { 3, 3 });
		placePieces(board, new int[][] { { 1, 2 }, { 2, 1 }, { 1, 4 } }, Color.WHITE);
		placePieces(board, new int[][] { { 4, 5 }, { 5, 4 }, { 4, 1 } }, Color.BLACK);
		List<int[]> moves = this.moveFinder.findMoves(board, piece);
		int[][] expectedMoves = new int[][] { { 1, 2 }, { 2, 1 }, { 1, 4 }, { 2, 5 }, { 5, 2 } };
		assertFoundMovesCorrect(expectedMoves, moves);
	}
}
