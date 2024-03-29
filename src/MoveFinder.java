import java.util.List;

public abstract class MoveFinder {

	abstract List<int[]> findMoves(int[][] board, Piece piece);

	protected boolean isInLastRow(Piece piece) {
		if (piece.isBlack()) {
			return piece.getPosition()[0] == 0;
		} else {
			return piece.getPosition()[0] == 7;
		}
	}

	protected static int getPieceValue(Piece piece) {
		if (piece.getColor() == Piece.Color.WHITE) {
			return 1;
		}
		return -1;
	}

	protected boolean isInRightCol(Piece piece) {
		return piece.getPosition()[1] == 7;
	}

	protected boolean isInLeftCol(Piece piece) {
		return piece.getPosition()[1] == 0;
	}

}
