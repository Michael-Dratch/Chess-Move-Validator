import java.util.List;

public class Output {

	public String createOutput(Piece piece, List<int[]> moves) {
		String output = "LEGAL MOVES FOR ";
		output = output.concat(getPieceInfo(piece)).concat(": ");

		for (int[] move : moves) {
			output = output.concat(getPositionString(move)).concat(", ");
		}
		output = trimResult(output);
		return output;
	}

	private String getPieceInfo(Piece piece) {
		String result = "";
		result = result.concat(getPieceLetter(piece.getType()));
		result = result.concat(getPositionString(piece.getPosition()));
		return result;
	}

	private String getPieceLetter(Piece.PieceType pieceType) {
		return switch (pieceType) {
		case KING -> "K";
		case QUEEN -> "Q";
		case ROOK -> "R";
		case BISHOP -> "B";
		case KNIGHT -> "N";
		default -> "P";
		};
	}

	private String getPositionString(int[] position) {
		String cols = "abcdefgh";
		String result = "";
		result = result.concat(String.valueOf(cols.charAt(position[1])));
		result = result.concat(String.valueOf(position[0] + 1));
		return result;
	}

	private static String trimResult(String output) {
		String trimmedOutput = output.trim();
		if (trimmedOutput.charAt(trimmedOutput.length() - 1) == ',') {
			trimmedOutput = trimmedOutput.substring(0, trimmedOutput.length() - 1);
		}
		return trimmedOutput;
	}
}
