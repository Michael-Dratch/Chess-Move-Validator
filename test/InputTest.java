
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.shadow.com.univocity.parsers.common.DataValidationException;
import org.junit.jupiter.params.shadow.com.univocity.parsers.common.TextParsingException;

import static org.junit.jupiter.api.Assertions.*;

public class InputTest {

	private int[][] getEmptyBoard() {
		int[][] board = { { 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0 } };
		return board;
	}

	private void assertCorrectBoardOutput(int[][] expectedBoard, String input) {
		int[][] actualBoard = this.input.createBoard(input);

		for (int row = 0; row < 8; row++) {
			for (int col = 0; col < 8; col++) {
				assertEquals(expectedBoard[row][col], actualBoard[row][col]);
			}
		}
	}

	private void assertThrowsValidationError(String input) {
		IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
			this.input.createBoard(input);
		});

		String expectedMessage = """
				Invalid Input.
				Input Criteria:
				- The format of a piece should be the first Letter of the piece name followed by the column letter and row number of its position.
				- Put white and black pieces on separate lines beginning with labels 'WHITE:' and 'BLACK:'.
				-Pieces should be seperated by commas.
				- Lastly, put a third label 'PIECE TO MOVE:' followed by the piece information for the piece to be analyzed.

				Example input:
				WHITE: Rf1, Kg1, Pf2, Ph2, Pg3
				BLACK: Kb8, Ne8, Pa7, Pc7, Ra5
				PIECE TO MOVE: Rf1

				""";
		String actualMessage = exception.getMessage();

		assertTrue(actualMessage.contains(expectedMessage));
	}

	private Input input;

	@BeforeEach
	void setUp() {
		this.input = new Input();
	}

	@Test
	void emptyInputReturnsException() {
		String input = "";
		assertThrowsValidationError(input);
	}

	@Test
	void missingLabelsThrowsException() {
		String input = "Rf1, Kg1, Pf2, Ph2, Pg3";
		assertThrowsValidationError(input);
	}

	@Test
	void inputPiecesBeforeLabelThrowsException() {
		String input = "Rf1, Kg1, Pf2, Ph2, Pg3 WHITE: BLACK: PIECE TO MOVE:";
		assertThrowsValidationError(input);
	}

	@Test
	void inputLabelOrderIncorrectThrowsException() {
		String input = "BLACK: Rf1, Kg1, Pf2 WHITE: Ph2 PIECE TO MOVE: Pg3";
		assertThrowsValidationError(input);
	}

	@Test
	void incorrectPieceFormatNoColThrowsException() {
		String input = "White: Rf,   Pa2, Pa2, Pa2 BLACK: Pa2 PIECE TO MOVE: Pa3";
		assertThrowsValidationError(input);
	}

	@Test
	void incorrectPieceFormatNoRowThrowsException() {
		String input = "White: BLACK: R1 PIECE TO MOVE:";
		assertThrowsValidationError(input);
	}

	@Test
	void incorrectPieceFormatAllLettersThrowsException() {
		String input = "White: BLACK: PIECE TO MOVE: RAB";
		assertThrowsValidationError(input);
	}

	@Test
	void createEmptyBoard() {
		String input = """
				WHITE:
				BLACK:
				PIECE TO MOVE:""";

		assertCorrectBoardOutput(getEmptyBoard(), input);
	}

//	@Test
//	void onePawnOnA1() {
//		String input = """
//				WHITE: Pa1
//				BLACK:
//				PIECE TO MOVE:""";
//
//		int[][] expectedBoard = getEmptyBoard();
//		expectedBoard[0][0] = 1;
//		assertCorrectBoardOutput(expectedBoard, input);
//	}
}