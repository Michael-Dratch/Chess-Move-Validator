import java.util.ArrayList;

public class PawnMoveFinder implements MoveFinder {

	@Override
	public ArrayList<int[]> findMoves(int[][] bord, Piece piece) {

		ArrayList<int[]> moves = new ArrayList<>();
		int[] position = piece.getPosition();

		if (position[0] != 7) {
			moves.add(new int[] { position[0] + 1, position[1] });
		}

		return moves;
	}
}
