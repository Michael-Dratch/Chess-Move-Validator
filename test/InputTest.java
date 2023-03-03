
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class InputTest {

	private int[][] getEmptyBoard() {
		return new int[][] { { 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0 } };
	}

	private void assertCorrectBoardOutput(int[][] expectedBoard, String input) {
		int[][] actualBoard = this.input.getBoard(input);

		for (int row = 0; row < 8; row++) {
			for (int col = 0; col < 8; col++) {
				assertEquals(expectedBoard[row][col], actualBoard[row][col]);
			}
		}
	}

	private void assertThrowsValidationErrorForBoard(String input) {
		IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
			this.input.getBoard(input);
		});

		String expectedMessage = """
				Invalid Input
				Input Criteria:
				- The format of a piece should be the first Letter of the piece name followed by the column letter and row number of its position.
				- Put white and black pieces on separate lines beginning with labels 'WHITE:' and 'BLACK:'.
				-Pieces should be seperated by commas and there cannot be any duplicate positions.
				- Lastly, put a third label 'PIECE TO MOVE:' followed by the piece information for the piece to be analyzed.

				Example input:
				WHITE: Rf1, Kg1, Pf2, Ph2, Pg3
				BLACK: Kb8, Ne8, Pa7, Pc7, Ra5
				PIECE TO MOVE: Rf1
				""";
		String actualMessage = exception.getMessage();

		assertTrue(actualMessage.equals(expectedMessage));
	}

	private void assertThrowsValidationErrorForPiece(String input) {
		IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
			this.input.getPieceToMove(input);
		});

		String expectedMessage = """
				Invalid Input
				Input Criteria:
				- The format of a piece should be the first Letter of the piece name followed by the column letter and row number of its position.
				- Put white and black pieces on separate lines beginning with labels 'WHITE:' and 'BLACK:'.
				-Pieces should be seperated by commas and there cannot be any duplicate positions.
				- Lastly, put a third label 'PIECE TO MOVE:' followed by the piece information for the piece to be analyzed.

				Example input:
				WHITE: Rf1, Kg1, Pf2, Ph2, Pg3
				BLACK: Kb8, Ne8, Pa7, Pc7, Ra5
				PIECE TO MOVE: Rf1
				""";
		String actualMessage = exception.getMessage();

		assertTrue(actualMessage.equals(expectedMessage));
	}

	private void assertCorrectPiece(String input, Piece.PieceType expectedType, Piece.Color expectedColor,
			int[] expectedPosition) {
		Piece piece = this.input.getPieceToMove(input);
		assertEquals(expectedType, piece.getType());
		assertEquals(expectedColor, piece.getColor());
		assertEquals(expectedPosition[0], piece.getPosition()[0]);
		assertEquals(expectedPosition[1], piece.getPosition()[1]);
	}

	private Input input;

	@BeforeEach
	void setUp() {
		this.input = new Input();
	}

	@Test
	void emptyInputReturnsException() {
		String input = "";
		assertThrowsValidationErrorForBoard(input);
	}

	@Test
	void missingLabelsThrowsException() {
		String input = "Rf1, Kg1, Pf2, Ph2, Pg3";
		assertThrowsValidationErrorForBoard(input);
	}

	@Test
	void inputPiecesBeforeLabelThrowsException() {
		String input = "Rf1, Kg1, Pf2, Ph2, Pg3 WHITE: BLACK: PIECE TO MOVE:";
		assertThrowsValidationErrorForBoard(input);
	}

	@Test
	void inputLabelOrderIncorrectThrowsException() {
		String input = "BLACK: Rf1, Kg1, Pf2 WHITE: Ph2 PIECE TO MOVE: Pg3";
		assertThrowsValidationErrorForBoard(input);
	}

	@Test
	void incorrectPieceFormatNoColThrowsException() {
		String input = "White: Rf,   Pa2, Pa2, Pa2 BLACK: Pa2 PIECE TO MOVE: Pa3";
		assertThrowsValidationErrorForBoard(input);
	}

	@Test
	void incorrectPieceFormatNoRowThrowsException() {
		String input = "White: BLACK: R1 PIECE TO MOVE:";
		assertThrowsValidationErrorForBoard(input);
	}

	@Test
	void incorrectPieceFormatAllLettersThrowsException() {
		String input = "White: BLACK: PIECE TO MOVE: RAB";
		assertThrowsValidationErrorForPiece(input);
	}

	@Test
	void multipleValuesForPieceToMoveThrowsError() {
		String input = """
				WHITE:
				BLACK:
				PIECE TO MOVE: Ka1 Qa2""";

		assertThrowsValidationErrorForPiece(input);
	}

	@Test
	void createEmptyBoard() {
		String input = """
				WHITE:
				BLACK:
				PIECE TO MOVE: Ka1""";

		assertCorrectBoardOutput(getEmptyBoard(), input);
	}

	@Test
	void oneWhitePawnOnA1() {
		String input = """
				WHITE: Pa1
				BLACK:
				PIECE TO MOVE: Pa1""";

		int[][] expectedBoard = getEmptyBoard();
		expectedBoard[0][0] = 1;
		assertCorrectBoardOutput(expectedBoard, input);
	}

	@Test
	void oneBlackPawnOnA1() {
		String input = """
				WHITE:
				BLACK: Pa1
				PIECE TO MOVE: Pa1""";

		int[][] expectedBoard = getEmptyBoard();
		expectedBoard[0][0] = -1;
		assertCorrectBoardOutput(expectedBoard, input);
	}

	@Test
	void SingleBlackAndWhitePieces() {
		String input = """
				WHITE: Kc4
				BLACK: Pa1
				PIECE TO MOVE: Pa1""";

		int[][] expectedBoard = getEmptyBoard();
		expectedBoard[0][0] = -1;
		expectedBoard[3][2] = 1;
		assertCorrectBoardOutput(expectedBoard, input);
	}

	@Test
	void ManyPieces() {
		String input = """
				WHITE: Rf1, Kg1, Pf2, Ph2, Pg3
				BLACK: Kb8, Ne8, Pa7, Pc7, Ra5
				PIECE TO MOVE: Rf1""";

		int[][] expectedBoard = getEmptyBoard();
		expectedBoard[0][5] = 1;
		expectedBoard[0][6] = 1;
		expectedBoard[1][5] = 1;
		expectedBoard[1][7] = 1;
		expectedBoard[2][6] = 1;
		expectedBoard[7][1] = -1;
		expectedBoard[7][4] = -1;
		expectedBoard[6][0] = -1;
		expectedBoard[6][2] = -1;
		expectedBoard[4][0] = -1;
		assertCorrectBoardOutput(expectedBoard, input);
	}

	@Test
	void DuplicatePositionsAllWhiteThrowsError() {
		String input = """
				WHITE: Kc4, Kc4
				BLACK: Pa1
				PIECE TO MOVE: Pa1""";

		assertThrowsValidationErrorForBoard(input);
	}

	@Test
	void DuplicatePositionsAllBlackThrowsError() {
		String input = """
				WHITE:
				BLACK: Kc4, Kc4
				PIECE TO MOVE: Pa1""";

		assertThrowsValidationErrorForBoard(input);
	}

	@Test
	void DuplicatePositionsWhiteAndBlackThrowsError() {
		String input = """
				WHITE: Kc4
				BLACK: Kc4
				PIECE TO MOVE: Pa1""";

		assertThrowsValidationErrorForBoard(input);
	}

	@Test
	void getPieceToMoveKa1() {
		String input = """
				WHITE: Ka1
				BLACK:
				PIECE TO MOVE: Ka1""";

		assertCorrectPiece(input, Piece.PieceType.KING, Piece.Color.WHITE, new int[] { 0, 0 });
	}

	@Test
	void getPieceToMovePh8() {
		String input = """
				WHITE:
				BLACK: Ph8
				PIECE TO MOVE: Ph8""";

		assertCorrectPiece(input, Piece.PieceType.PAWN, Piece.Color.BLACK, new int[] { 7, 7 });
	}

	@Test
	void getPieceToMoveBd5() {
		String input = """
				WHITE: Bd5
				BLACK: Qa2
				PIECE TO MOVE: Bd5""";

		assertCorrectPiece(input, Piece.PieceType.BISHOP, Piece.Color.WHITE, new int[] { 4, 3 });
	}

}