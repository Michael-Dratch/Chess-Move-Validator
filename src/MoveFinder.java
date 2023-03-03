import java.util.ArrayList;

public abstract class MoveFinder {

	abstract ArrayList<int[]> findMoves(int[][] bord, Piece piece);

	protected boolean isInLastRow(Piece piece) {
		if (piece.getColor() == Piece.Color.WHITE) {
			if (piece.getPosition()[0] == 7) {
				return true;
			}
		} else {
			if (piece.getPosition()[0] == 0) {
				return true;
			}
		}

		return false;
	}

}
