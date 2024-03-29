import java.util.ArrayList;
import java.util.List;

public class PawnMoveFinder extends MoveFinder {

	@Override
	public List<int[]> findMoves(int[][] board, Piece piece) {

		List<int[]> moves = new ArrayList<>();
		int[] position = piece.getPosition();

		if (isInLastRow(piece)) {
			return moves;
		}

		if (isFirstSpaceAheadOpen(board, piece)) {
			addForwardMove(piece, moves, 1);
		}

		if (isFirstSpaceAheadOpen(board, piece) && isSecondSpaceAheadOpen(board, piece)
				&& isInStartingPosition(piece)) {
			addForwardMove(piece, moves, 2);
		}

		if (isForwardRightSpaceAnOppositeColorPiece(board, piece)) {
			addForwardRightMove(piece, moves);
		}

		if (isForwardLeftSpaceAnOppositeColorPiece(board, piece)) {
			addForwardLeftMove(piece, moves);
		}
		return moves;
	}

	private boolean isFirstSpaceAheadOpen(int[][] board, Piece piece) {
		return getValueAtBoardPosition(board, getOneAheadPosition(piece)) == 0;
	}

	private static int getValueAtBoardPosition(int[][] board, int[] position) {
		return board[position[0]][position[1]];
	}

	private int[] getOneAheadPosition(Piece piece) {
		int[] position = piece.getPosition();
		int oneAhead = 1;
		if (piece.isBlack()) {
			oneAhead = -1;
		}
		return new int[] { position[0] + oneAhead, position[1] };
	}

	private boolean isSecondSpaceAheadOpen(int[][] board, Piece piece) {
		return getValueAtBoardPosition(board, getTwoAheadPosition(piece)) == 0;
	}

	private int[] getTwoAheadPosition(Piece piece) {
		int[] position = piece.getPosition();
		int oneAhead = 2;
		if (piece.isBlack()) {
			oneAhead = -2;
		}
		return new int[] { position[0] + oneAhead, position[1] };
	}

	private boolean isInStartingPosition(Piece piece) {
		return piece.getPosition()[0] == getStartRow(piece);
	}

	private static int getStartRow(Piece piece) {
		if (piece.isBlack()) {
			return 6;
		}
		return 1;
	}

	private static void addForwardMove(Piece piece, List<int[]> moves, int numSpaces) {
		int[] position = piece.getPosition();
		int spacesAhead = numSpaces;
		if (piece.isBlack()) {
			spacesAhead *= -1;
		}
		moves.add(new int[] { position[0] + spacesAhead, position[1] });
	}

	private boolean isForwardRightSpaceAnOppositeColorPiece(int[][] board, Piece piece) {
		if (isInRightCol(piece)) {
			return false;
		}
		int[] position = piece.getPosition();

		if (piece.isBlack()) {
			int spaceValue = getValueAtBoardPosition(board, new int[] { position[0] - 1, position[1] + 1 });
			return spaceValue == 1;
		} else {
			int spaceValue = getValueAtBoardPosition(board, new int[] { position[0] + 1, position[1] + 1 });
			return spaceValue == -1;
		}
	}

	private boolean isForwardLeftSpaceAnOppositeColorPiece(int[][] board, Piece piece) {
		if (isInLeftCol(piece)) {
			return false;
		}
		int[] position = piece.getPosition();

		if (piece.isBlack()) {
			int spaceValue = getValueAtBoardPosition(board, new int[] { position[0] - 1, position[1] - 1 });
			return spaceValue == 1;
		} else {
			int spaceValue = getValueAtBoardPosition(board, new int[] { position[0] + 1, position[1] - 1 });
			return spaceValue == -1;
		}
	}

	private static void addForwardRightMove(Piece piece, List<int[]> moves) {
		int[] position = piece.getPosition();
		int forward = 1;
		if (piece.isBlack()) {
			forward = -1;
		}
		moves.add(new int[] { position[0] + forward, position[1] + 1 });
	}

	private static void addForwardLeftMove(Piece piece, List<int[]> moves) {
		int[] position = piece.getPosition();
		int forward = 1;
		if (piece.isBlack()) {
			forward = -1;
		}
		moves.add(new int[] { position[0] + forward, position[1] - 1 });
	}
}
