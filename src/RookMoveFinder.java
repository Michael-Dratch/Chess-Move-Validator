import java.util.ArrayList;

public class RookMoveFinder extends MoveFinder {
	@Override
	ArrayList<int[]> findMoves(int[][] board, Piece piece) {

		ArrayList<int[]> moves = new ArrayList<>();

		addTopMoves(board, piece, moves);
		addBottomMoves(board, piece, moves);
		addRightMoves(board, piece, moves);
		addLeftMoves(board, piece, moves);
		return moves;
	}

	private void addTopMoves(int[][] board, Piece piece, ArrayList<int[]> moves) {
		int pieceValue = getPieceValue(piece);
		int col = piece.getPosition()[1];
		for (int row = piece.getPosition()[0] + 1; row < 8; row++) {
			if (board[row][col] == 0) {
				moves.add(new int[] { row, col });
			} else if (board[row][col] == -pieceValue) {
				moves.add(new int[] { row, col });
				break;
			} else {
				break;
			}
		}
	}

	private void addBottomMoves(int[][] board, Piece piece, ArrayList<int[]> moves) {
		int pieceValue = getPieceValue(piece);
		int col = piece.getPosition()[1];
		for (int row = piece.getPosition()[0] - 1; row >= 0; row--) {
			if (board[row][col] == 0) {
				moves.add(new int[] { row, col });
			} else if (board[row][col] == -pieceValue) {
				moves.add(new int[] { row, col });
				break;
			} else {
				break;
			}
		}
	}

	private void addRightMoves(int[][] board, Piece piece, ArrayList<int[]> moves) {
		int pieceValue = getPieceValue(piece);
		int row = piece.getPosition()[0];
		for (int col = piece.getPosition()[1] + 1; col < 8; col++) {
			if (board[row][col] == 0) {
				moves.add(new int[] { row, col });
			} else if (board[row][col] == -pieceValue) {
				moves.add(new int[] { row, col });
				break;
			} else {
				break;
			}
		}
	}

	private void addLeftMoves(int[][] board, Piece piece, ArrayList<int[]> moves) {
		int pieceValue = getPieceValue(piece);
		int row = piece.getPosition()[0];
		for (int col = piece.getPosition()[1] - 1; col >= 0; col--) {
			if (board[row][col] == 0) {
				moves.add(new int[] { row, col });
			} else if (board[row][col] == -pieceValue) {
				moves.add(new int[] { row, col });
				break;
			} else {
				break;
			}
		}
	}
}
