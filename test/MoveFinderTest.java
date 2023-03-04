import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public abstract class MoveFinderTest {

	MoveFinder moveFinder;

	protected enum Color {
		WHITE, BLACK
	}

	public void placePieces(int[][] board, int[][] positions, MoveFinderTest.Color color) {
		int value = 1;
		if (color == MoveFinderTest.Color.BLACK) {
			value = -1;
		}
		for (int[] position : positions) {
			board[position[0]][position[1]] = value;
		}
	}

	protected int[][] getEmptyBoard() {
		return new int[][] { { 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0 } };
	}

	protected void assertFoundMovesCorrect(int[][] expectedMoves, List<int[]> resultMoves) {
		for (int[] expectedMove : expectedMoves) {
			boolean found = false;
			for (int[] move : resultMoves) {
				if (expectedMove[0] == move[0] && expectedMove[1] == move[1]) {
					found = true;
					break;
				}
			}
			assertEquals(true, found);
		}

		assertEquals(expectedMoves.length, resultMoves.size());
	}
}
