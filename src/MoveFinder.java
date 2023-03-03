import java.util.ArrayList;

public abstract class MoveFinder {

	abstract ArrayList<int[]> findMoves(int[][] bord, Piece piece);

	protected boolean isInLastRow(Piece piece) {
		if (piece.isBlack()) {
			return piece.getPosition()[0] == 0;
		} else {
			return piece.getPosition()[0] == 7;
		}
	}

	protected boolean isInRightCol(Piece piece) {
		return piece.getPosition()[1] == 7;
	}

	protected boolean isInLeftCol(Piece piece) {
		return piece.getPosition()[1] == 0;
	}

}
