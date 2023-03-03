
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

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

		ArrayList<int[]> resultMoves = this.moveFinder.findMoves(board, pawn);
		assertEquals(1, resultMoves.size());
		assertFoundMovesCorrect(expectedMoves, resultMoves);
	}

	@Test
	void BlackPawnNotInStartingPositionAndNoBlocksHasOneMove() {
		int[][] board = getEmptyBoard();
		Piece pawn = new Piece(Piece.PieceType.PAWN, Piece.Color.BLACK, new int[] { 5, 0 });
		int[][] expectedMoves = { { 4, 0 } };

		ArrayList<int[]> resultMoves = this.moveFinder.findMoves(board, pawn);
		System.out.println("testing");
		assertFoundMovesCorrect(expectedMoves, resultMoves);
		System.out.println("done testing");
	}

	@Test
	void WhitePawnInStartingPositionNoBlocksHasTwoMoves() {
		int[][] board = getEmptyBoard();
		Piece pawn = new Piece(Piece.PieceType.PAWN, Piece.Color.WHITE, new int[] { 1, 0 });
		int[][] expectedMoves = { { 2, 0 }, { 3, 0 } };

		ArrayList<int[]> moves = this.moveFinder.findMoves(board, pawn);

		assertFoundMovesCorrect(expectedMoves, moves);
	}

	@Test
	void BlackPawnInStartingPositionNoBlocksHasTwoMoves() {
		int[][] board = getEmptyBoard();
		Piece pawn = new Piece(Piece.PieceType.PAWN, Piece.Color.BLACK, new int[] { 6, 0 });
		int[][] expectedMoves = { { 5, 0 }, { 4, 0 } };

		ArrayList<int[]> moves = this.moveFinder.findMoves(board, pawn);

		assertFoundMovesCorrect(expectedMoves, moves);
	}

	@Test
	void LastRowWhitePawnHasNoValidMoves() {
		int[][] board = getEmptyBoard();
		int[][] expectedMoves = {};
		Piece pawn = new Piece(Piece.PieceType.PAWN, Piece.Color.WHITE, new int[] { 7, 0 });
		ArrayList<int[]> moves = this.moveFinder.findMoves(board, pawn);

		assertFoundMovesCorrect(expectedMoves, moves);
	}

	@Test
	void LastRowBlackPawnHasNoValidMoves() {
		int[][] board = getEmptyBoard();
		int[][] expectedMoves = {};
		Piece pawn = new Piece(Piece.PieceType.PAWN, Piece.Color.BLACK, new int[] { 0, 0 });
		ArrayList<int[]> moves = this.moveFinder.findMoves(board, pawn);

		assertFoundMovesCorrect(expectedMoves, moves);
	}

	@Test
	void WhitePawnA2BlackPieceA3HasNoMoves() {
		int[][] board = getEmptyBoard();
		board[2][0] = -1;
		Piece pawn = new Piece(Piece.PieceType.PAWN, Piece.Color.WHITE, new int[] { 1, 0 });
		int[][] expectedMoves = {};

		ArrayList<int[]> moves = this.moveFinder.findMoves(board, pawn);

		assertFoundMovesCorrect(expectedMoves, moves);
	}

	@Test
	void WhitePawnA2BlackPieceA4OnlyHasA3() {
		int[][] board = getEmptyBoard();
		board[3][0] = -1;
		Piece pawn = new Piece(Piece.PieceType.PAWN, Piece.Color.WHITE, new int[] { 1, 0 });
		int[][] expectedMoves = { { 2, 0 } };

		ArrayList<int[]> moves = this.moveFinder.findMoves(board, pawn);

		assertFoundMovesCorrect(expectedMoves, moves);
	}

	@Test
	void WhitePawnA2WhitePieceA4OnlyHasA3() {
		int[][] board = getEmptyBoard();
		board[3][0] = 1;
		Piece pawn = new Piece(Piece.PieceType.PAWN, Piece.Color.WHITE, new int[] { 1, 0 });
		int[][] expectedMoves = { { 2, 0 } };

		ArrayList<int[]> moves = this.moveFinder.findMoves(board, pawn);

		assertFoundMovesCorrect(expectedMoves, moves);
	}
}
