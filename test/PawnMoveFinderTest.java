
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

public class PawnMoveFinderTest extends MoveFinderTest {

	MoveFinder moveFinder;

	@BeforeEach
	void setUp() {
		this.moveFinder = new PawnMoveFinder();
	}

	@Test
	void WhitePawnNotInStartingPositionAndNoBlocksHasOneMove() {
		int[][] board = getEmptyBoard();
		Piece pawn = new Piece(Piece.PieceType.PAWN, Piece.Color.WHITE, new int[] { 2, 0 });
		int[][] expectedMoves = { { 3, 0 } };

		List<int[]> resultMoves = this.moveFinder.findMoves(board, pawn);
		assertEquals(1, resultMoves.size());
		assertFoundMovesCorrect(expectedMoves, resultMoves);
	}

	@Test
	void BlackPawnNotInStartingPositionAndNoBlocksHasOneMove() {
		int[][] board = getEmptyBoard();
		Piece pawn = new Piece(Piece.PieceType.PAWN, Piece.Color.BLACK, new int[] { 5, 0 });
		int[][] expectedMoves = { { 4, 0 } };

		List<int[]> resultMoves = this.moveFinder.findMoves(board, pawn);
		System.out.println("testing");
		assertFoundMovesCorrect(expectedMoves, resultMoves);
		System.out.println("done testing");
	}

	@Test
	void WhitePawnInStartingPositionNoBlocksHasTwoMoves() {
		int[][] board = getEmptyBoard();
		Piece pawn = new Piece(Piece.PieceType.PAWN, Piece.Color.WHITE, new int[] { 1, 0 });
		int[][] expectedMoves = { { 2, 0 }, { 3, 0 } };

		List<int[]> moves = this.moveFinder.findMoves(board, pawn);

		assertFoundMovesCorrect(expectedMoves, moves);
	}

	@Test
	void BlackPawnInStartingPositionNoBlocksHasTwoMoves() {
		int[][] board = getEmptyBoard();
		Piece pawn = new Piece(Piece.PieceType.PAWN, Piece.Color.BLACK, new int[] { 6, 0 });
		int[][] expectedMoves = { { 5, 0 }, { 4, 0 } };

		List<int[]> moves = this.moveFinder.findMoves(board, pawn);

		assertFoundMovesCorrect(expectedMoves, moves);
	}

	@Test
	void LastRowWhitePawnHasNoValidMoves() {
		int[][] board = getEmptyBoard();
		int[][] expectedMoves = {};
		Piece pawn = new Piece(Piece.PieceType.PAWN, Piece.Color.WHITE, new int[] { 7, 0 });
		List<int[]> moves = this.moveFinder.findMoves(board, pawn);

		assertFoundMovesCorrect(expectedMoves, moves);
	}

	@Test
	void LastRowBlackPawnHasNoValidMoves() {
		int[][] board = getEmptyBoard();
		int[][] expectedMoves = {};
		Piece pawn = new Piece(Piece.PieceType.PAWN, Piece.Color.BLACK, new int[] { 0, 0 });
		List<int[]> moves = this.moveFinder.findMoves(board, pawn);

		assertFoundMovesCorrect(expectedMoves, moves);
	}

	@Test
	void WhitePawnA2BlackPieceA3HasNoMoves() {
		int[][] board = getEmptyBoard();
		board[2][0] = -1;
		Piece pawn = new Piece(Piece.PieceType.PAWN, Piece.Color.WHITE, new int[] { 1, 0 });
		int[][] expectedMoves = {};

		List<int[]> moves = this.moveFinder.findMoves(board, pawn);

		assertFoundMovesCorrect(expectedMoves, moves);
	}

	@Test
	void WhitePawnA2BlackPieceA4OnlyHasA3() {
		int[][] board = getEmptyBoard();
		board[3][0] = -1;
		Piece pawn = new Piece(Piece.PieceType.PAWN, Piece.Color.WHITE, new int[] { 1, 0 });
		int[][] expectedMoves = { { 2, 0 } };

		List<int[]> moves = this.moveFinder.findMoves(board, pawn);

		assertFoundMovesCorrect(expectedMoves, moves);
	}

	@Test
	void BlackPawnA6BlackPieceA5HasNoMoves() {
		int[][] board = getEmptyBoard();
		board[5][0] = -1;
		Piece pawn = new Piece(Piece.PieceType.PAWN, Piece.Color.BLACK, new int[] { 6, 0 });
		int[][] expectedMoves = {};

		List<int[]> moves = this.moveFinder.findMoves(board, pawn);

		assertFoundMovesCorrect(expectedMoves, moves);
	}

	@Test
	void BlackPawnA6BlackPieceA4OnlyHasA5() {
		int[][] board = getEmptyBoard();
		board[4][0] = -1;
		Piece pawn = new Piece(Piece.PieceType.PAWN, Piece.Color.BLACK, new int[] { 6, 0 });
		int[][] expectedMoves = { { 5, 0 } };

		List<int[]> moves = this.moveFinder.findMoves(board, pawn);

		assertFoundMovesCorrect(expectedMoves, moves);
	}

	@Test
	void WhitePawnA2WhitePieceA4OnlyHasA3() {
		int[][] board = getEmptyBoard();
		board[3][0] = 1;
		Piece pawn = new Piece(Piece.PieceType.PAWN, Piece.Color.WHITE, new int[] { 1, 0 });
		int[][] expectedMoves = { { 2, 0 } };

		List<int[]> moves = this.moveFinder.findMoves(board, pawn);

		assertFoundMovesCorrect(expectedMoves, moves);
	}

	@Test
	void WhitePawnA2WhitePieceB3NoDiagonalMoves() {
		int[][] board = getEmptyBoard();
		board[3][1] = 1;
		Piece pawn = new Piece(Piece.PieceType.PAWN, Piece.Color.WHITE, new int[] { 1, 0 });
		int[][] expectedMoves = { { 2, 0 }, { 3, 0 } };

		List<int[]> moves = this.moveFinder.findMoves(board, pawn);
		assertFoundMovesCorrect(expectedMoves, moves);

	}

	@Test
	void WhitePawnA2BlackPieceB3FindsDiagonalMove() {
		int[][] board = getEmptyBoard();
		board[2][1] = -1;
		Piece pawn = new Piece(Piece.PieceType.PAWN, Piece.Color.WHITE, new int[] { 1, 0 });
		int[][] expectedMoves = { { 2, 0 }, { 3, 0 }, { 2, 1 } };

		List<int[]> moves = this.moveFinder.findMoves(board, pawn);
		assertFoundMovesCorrect(expectedMoves, moves);

	}

	@Test
	void BlackPawnB6WhitePieceC5FindsDiagonalMove() {
		int[][] board = getEmptyBoard();
		board[5][2] = 1;
		Piece pawn = new Piece(Piece.PieceType.PAWN, Piece.Color.BLACK, new int[] { 6, 1 });
		int[][] expectedMoves = { { 5, 1 }, { 4, 1 }, { 5, 2 } };

		List<int[]> moves = this.moveFinder.findMoves(board, pawn);
		assertFoundMovesCorrect(expectedMoves, moves);

	}

	@Test
	void BlackPawnOnRightColFindsNoDiagonalMove() {
		int[][] board = getEmptyBoard();
		Piece pawn = new Piece(Piece.PieceType.PAWN, Piece.Color.BLACK, new int[] { 6, 7 });
		int[][] expectedMoves = { { 5, 7 }, { 4, 7 } };

		List<int[]> moves = this.moveFinder.findMoves(board, pawn);
		assertFoundMovesCorrect(expectedMoves, moves);

	}

	@Test
	void WhitePawnD2WhitePieceC3NoDiagonalMoves() {
		int[][] board = getEmptyBoard();
		board[2][2] = 1;
		Piece pawn = new Piece(Piece.PieceType.PAWN, Piece.Color.WHITE, new int[] { 1, 3 });
		int[][] expectedMoves = { { 2, 3 }, { 3, 3 } };

		List<int[]> moves = this.moveFinder.findMoves(board, pawn);
		assertFoundMovesCorrect(expectedMoves, moves);

	}

	@Test
	void WhitePawnD2BlackPieceC3FindsDiagonalMove() {
		int[][] board = getEmptyBoard();
		board[2][2] = -1;
		Piece pawn = new Piece(Piece.PieceType.PAWN, Piece.Color.WHITE, new int[] { 1, 3 });
		int[][] expectedMoves = { { 2, 3 }, { 3, 3 }, { 2, 2 } };

		List<int[]> moves = this.moveFinder.findMoves(board, pawn);
		assertFoundMovesCorrect(expectedMoves, moves);
	}

	@Test
	void BlackPawnD6WhitePieceC5FindsDiagonalMove() {
		int[][] board = getEmptyBoard();
		board[5][2] = 1;
		Piece pawn = new Piece(Piece.PieceType.PAWN, Piece.Color.BLACK, new int[] { 6, 3 });
		int[][] expectedMoves = { { 5, 3 }, { 4, 3 }, { 5, 2 } };

		List<int[]> moves = this.moveFinder.findMoves(board, pawn);
		assertFoundMovesCorrect(expectedMoves, moves);

	}

	@Test
	void WhitePawnWithTwoDiagonalMoves() {
		int[][] board = getEmptyBoard();
		board[2][0] = -1;
		board[2][2] = -1;
		Piece pawn = new Piece(Piece.PieceType.PAWN, Piece.Color.WHITE, new int[] { 1, 1 });
		int[][] expectedMoves = { { 2, 1 }, { 3, 1 }, { 2, 0 }, { 2, 2 } };

		List<int[]> moves = this.moveFinder.findMoves(board, pawn);
		assertFoundMovesCorrect(expectedMoves, moves);

	}
}
