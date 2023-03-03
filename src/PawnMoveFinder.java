import java.util.ArrayList;

public class PawnMoveFinder extends MoveFinder {

	@Override
	public ArrayList<int[]> findMoves(int[][] board, Piece piece) {

		ArrayList<int[]> moves = new ArrayList<>();
		int[] position = piece.getPosition();

		if (isInLastRow(piece)) {
			return moves;
		}

		if (isFirstSpaceAheadBlocked(board, piece)) {
			return moves;
		}

		if (isSecondSpaceAheadBlocked(board, piece) && isInStartingPosition(piece)) {
			addTwoInFrontMoves(piece, moves);
		} else {
			addOneInFrontMove(piece, moves);
		}
		return moves;
	}

	private boolean isFirstSpaceAheadBlocked(int[][] board, Piece piece) {
		return getValueAtBoardPosition(board, getOneAheadPosition(piece)) != 0;
	}

	private static int getValueAtBoardPosition(int[][] board, int[] position) {
		return board[position[0]][position[1]];
	}

	private int[] getOneAheadPosition(Piece piece) {
		int[] position = piece.getPosition();
		int oneAhead = 1;
		if (piece.getColor() == Piece.Color.BLACK) {
			oneAhead = -1;
		}
		return new int[] { position[0] + oneAhead, position[1] };
	}

	private boolean isSecondSpaceAheadBlocked(int[][] board, Piece piece) {
		return getValueAtBoardPosition(board, getTwoAheadPosition(piece)) == 0;
	}

	private int[] getTwoAheadPosition(Piece piece) {
		int[] position = piece.getPosition();
		int oneAhead = 2;
		if (piece.getColor() == Piece.Color.BLACK) {
			oneAhead = -2;
		}
		return new int[] { position[0] + oneAhead, position[1] };
	}

	private boolean isInStartingPosition(Piece piece) {
		if (piece.getPosition()[0] == getStartRow(piece)) {
			return true;
		}
		return false;
	}

	private static int getStartRow(Piece piece) {
		if (piece.getColor() == Piece.Color.BLACK) {
			return 6;
		}
		return 1;
	}

	private static void addOneInFrontMove(Piece piece, ArrayList<int[]> moves) {
		int[] position = piece.getPosition();
		if (piece.getColor() == Piece.Color.WHITE) {
			moves.add(new int[] { position[0] + 1, position[1] });
		} else {
			moves.add(new int[] { position[0] - 1, position[1] });
		}
	}

	private static void addTwoInFrontMoves(Piece piece, ArrayList<int[]> moves) {
		int[] position = piece.getPosition();
		int oneAhead = 1, twoAhead = 2;
		if (piece.getColor() == Piece.Color.BLACK) {
			oneAhead = -1;
			twoAhead = -2;
		}

		moves.add(new int[] { position[0] + oneAhead, position[1] });
		moves.add(new int[] { position[0] + twoAhead, position[1] });
	}
}
