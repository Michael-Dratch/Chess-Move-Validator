import java.util.ArrayList;

public class Input {

	private final InputValidator validator;

	private final String whiteStartToken = "WHITE:";
	private final String blackStartToken = "BLACK:";
	private final String pieceStartToken = "PIECE TO MOVE:";

	public Input() {
		this.validator = new InputValidator();
	}

	public int[][] createBoard(String input) {
		input = input.toUpperCase();
		this.validator.validateInput(input);
		String whiteLine = input.substring(input.indexOf(whiteStartToken), input.indexOf(blackStartToken));
		String blackLine = input.substring(input.indexOf(blackStartToken), input.indexOf(pieceStartToken));
		String pieceLine = input.substring(input.indexOf(pieceStartToken));

		String[] whitePieces = parsePieces(whiteLine);
		String[] blackPieces = parsePieces(blackLine);
		String[] pieceToMove = parsePieces(pieceLine);

		this.validator.validatePieces(whitePieces);
		this.validator.validatePieces(blackPieces);
		this.validator.validatePieces(pieceToMove);

//		int[][] whitePositions = extractPositions(whitePieces);
		int[][] board = getEmptyBoard();
		return board;
	}

	private String[] parsePieces(String input) {
		String[] parsedPieces = input.substring(input.lastIndexOf(":") + 1).replaceAll("\s+", "").replaceAll("\n+", "")
				.split(",");
		if (isParsedListEmpty(parsedPieces)) {
			parsedPieces = new String[] {};
		}
		return parsedPieces;
	}

	private static boolean isParsedListEmpty(String[] result) {
		return result.length == 1 && result[0].equals("");
	}

	private static int[][] getEmptyBoard() {
		return new int[][] { { 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0 } };
	}

	private ArrayList<int[]> extractPositions(String[] pieces) {
		ArrayList<int[]> positions = new ArrayList<>();
		String rows = "12345678";
		String cols = "ABCDEFGH";

		for (String piece : pieces) {
			char row = piece.charAt(2);
			char col = piece.charAt(1);
			int rowIndex = rows.indexOf(row);
			int colIndex = cols.indexOf(col);
			positions.add(new int[] { rowIndex, colIndex });
		}

		return positions;
	}

	private class InputValidator {

		final String errorMessage = """
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

		private void validateInput(String input) {
			boolean containsLabels = containsLabels(input);
			boolean startWithWhiteLabel = input.startsWith(whiteStartToken);
			boolean labelOrderCorrect = isLabelOrderCorrect(input);

			if (!containsLabels || !startWithWhiteLabel || !labelOrderCorrect) {
				throw new IllegalArgumentException(this.errorMessage);
			}

		}

		private boolean containsLabels(String input) {
			return input.contains(whiteStartToken) && input.contains(blackStartToken)
					&& input.contains(pieceStartToken);
		}

		private boolean isLabelOrderCorrect(String input) {
			int whiteIndex = input.indexOf(whiteStartToken);
			int blackIndex = input.indexOf(blackStartToken);
			int pieceIndex = input.indexOf(pieceStartToken);
			return (whiteIndex < blackIndex && blackIndex < pieceIndex);
		}

		private void validatePieces(String[] pieces) {
			for (String piece : pieces) {
				validatePieceInfo(piece);
			}
		}

		private void validatePieceInfo(String piece) {
			boolean formatIsCorrect = piece.matches("[KQRBNP][A-H][1-8]");
			if (!formatIsCorrect) {
				throw new IllegalArgumentException(this.errorMessage);
			}
		}
	}

}
