
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
		Piece pawn = new Piece(Piece.PieceType.PAWN, new int[] { 2, 0 });
		int[][] expectedMoves = { { 3, 0 } };

		ArrayList<int[]> resultMoves = this.moveFinder.findMoves(board, pawn);

		assertFoundMovesCorrect(expectedMoves, resultMoves);

//		assertEquals(1, moves.size());
//		assertEquals(3, moves.get(0)[0]);
//		assertEquals(0, moves.get(0)[1]);
	}

	@Test
	void WhitePawnInStartingPositionNoBlocksHasTwoMoves() {
		int[][] board = getEmptyBoard();
		Piece pawn = new Piece(Piece.PieceType.PAWN, new int[] { 1, 0 });
		int[][] expectedMoves = { { 2, 0 }, { 3, 0 } };

		ArrayList<int[]> moves = this.moveFinder.findMoves(board, pawn);

//		assertEquals(2, moves.size());
//		assertEquals(3, moves.get(0)[0]);
//		assertEquals(0, moves.get(0)[1]);
//		assertEquals(3, moves.get(1)[0]);
//		assertEquals(0, moves.get(1)[1]);

	}

	@Test
	void LastRowWhitePawnHasNoValidMoves() {
		int[][] board = getEmptyBoard();
		int[][] expectedMoves = {};
		Piece pawn = new Piece(Piece.PieceType.PAWN, new int[] { 7, 0 });
		ArrayList<int[]> moves = this.moveFinder.findMoves(board, pawn);

		assertFoundMovesCorrect(expectedMoves, moves);
	}

}
