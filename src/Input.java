import java.util.ArrayList;

public class Input {

	private final InputValidator validator;

	private final String whiteStartToken = "WHITE:";
	private final String blackStartToken = "BLACK:";
	private final String pieceStartToken = "PIECE TO MOVE:";

	private enum Color {
		WHITE, BLACK
	}

	public Input() {
		this.validator = new InputValidator();
	}

	public int[][] getBoard(String inputText) {
		String input = inputText.toUpperCase();
		this.validator.validateInput(input);
		String whiteLine = input.substring(input.indexOf(whiteStartToken), input.indexOf(blackStartToken));
		String blackLine = input.substring(input.indexOf(blackStartToken), input.indexOf(pieceStartToken));

		String[] whitePieces = parsePieces(whiteLine);
		String[] blackPieces = parsePieces(blackLine);

		this.validator.validatePieces(whitePieces);
		this.validator.validatePieces(blackPieces);
		ArrayList<int[]> whitePositions = extractPositions(whitePieces);
		ArrayList<int[]> blackPositions = extractPositions(blackPieces);

		this.validator.validateNoRepeatingPositions(whitePositions, blackPositions);

		int[][] board = getEmptyBoard();
		insertPieces(board, whitePositions, Color.WHITE);
		insertPieces(board, blackPositions, Color.BLACK);
		return board;
	}

	public Piece getPieceToMove(String inputText) {
		String input = inputText.toUpperCase();
		this.validator.validateInput(input);
		String pieceLine = input.substring(input.indexOf(pieceStartToken));
		String[] pieceToMove = parsePieces(pieceLine);
		this.validator.validatePieceToMoveList(pieceToMove);
		char pieceTypeLetter = pieceToMove[0].charAt(0);

		Piece.PieceType type = getPieceType(pieceTypeLetter);
		int[] piecePosition = extractPositions(pieceToMove).get(0);
		Piece.Color color = getColor(input, pieceToMove[0]);

		return new Piece(type, color, piecePosition);
	}

	private Piece.Color getColor(String input, String pieceToMoveString) {
		String whiteLine = input.substring(input.indexOf(whiteStartToken), input.indexOf(blackStartToken));
		String[] whitePieces = parsePieces(whiteLine);

		Piece.Color color = Piece.Color.BLACK;
		for (String piece : whitePieces) {
			if (pieceToMoveString.equals(piece)) {
				color = Piece.Color.WHITE;
				break;
			}
		}
		return color;
	}

	private void insertPieces(int[][] board, ArrayList<int[]> positions, Color color) {
		int piece = 1;
		if (color == Color.BLACK) {
			piece = -1;
		}
		for (int[] p : positions) {
			board[p[0]][p[1]] = piece;
		}
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

	private Piece.PieceType getPieceType(char pieceTypeLetter) {
		Piece.PieceType piece;
		switch (pieceTypeLetter) {
		case 'K':
			piece = Piece.PieceType.KING;
			break;
		case 'Q':
			piece = Piece.PieceType.QUEEN;
			break;
		case 'R':
			piece = Piece.PieceType.ROOK;
			break;
		case 'B':
			piece = Piece.PieceType.BISHOP;
			break;
		case 'N':
			piece = Piece.PieceType.KNIGHT;
			break;
		default:
			piece = Piece.PieceType.PAWN;
		}
		return piece;
	}

	private class InputValidator {

		final String errorMessage = """
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

		private void validatePieceToMoveList(String[] pieces) {
			if (pieces.length != 1) {
				throw new IllegalArgumentException(this.errorMessage);
			}
			validatePieceInfo(pieces[0]);
		}

		private void validateNoRepeatingPositions(ArrayList<int[]> whitePositions, ArrayList<int[]> blackPositions) {
			ArrayList<int[]> allPositions = new ArrayList<>(whitePositions);
			allPositions.addAll(blackPositions);
			for (int[] currentP : allPositions) {
				ArrayList<int[]> tempPositions = (ArrayList<int[]>) allPositions.clone();
				tempPositions.remove(currentP);
				for (int[] p : tempPositions) {
					if (currentP[0] == p[0] && currentP[1] == p[1]) {
						throw new IllegalArgumentException(this.errorMessage);
					}
				}
			}
		}
	}

}
