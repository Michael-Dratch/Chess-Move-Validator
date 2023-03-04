import java.util.ArrayList;
import java.util.List;

public class KingMoveFinder extends MoveFinder {
	@Override
	public List<int[]> findMoves(int[][] board, Piece piece) {
		List<int[]> moves = new ArrayList<>();

		addTopMoves(board, piece, moves);
		addBottomMoves(board, piece, moves);
		addRightMoves(board, piece, moves);
		addLeftMoves(board, piece, moves);
		addTopRightMoves(board, piece, moves);
		addTopLeftMoves(board, piece, moves);
		addBottomRightMoves(board, piece, moves);
		addBottomLeftMoves(board, piece, moves);
		return moves;
	}

	private void addTopMoves(int[][] board, Piece piece, List<int[]> moves) {
		int row = piece.getPosition()[0] + 1;
		int col = piece.getPosition()[1];
		if (row < 8) {
			addMove(board, moves, piece, row, col);
		}
	}

	private void addBottomMoves(int[][] board, Piece piece, List<int[]> moves) {
		int row = piece.getPosition()[0] - 1;
		int col = piece.getPosition()[1];
		if (row >= 0) {
			addMove(board, moves, piece, row, col);
		}
	}

	private void addRightMoves(int[][] board, Piece piece, List<int[]> moves) {
		int row = piece.getPosition()[0];
		int col = piece.getPosition()[1] + 1;
		if (col < 8) {
			addMove(board, moves, piece, row, col);
		}
	}

	private void addLeftMoves(int[][] board, Piece piece, List<int[]> moves) {
		int row = piece.getPosition()[0];
		int col = piece.getPosition()[1] - 1;
		if (col >= 0) {
			addMove(board, moves, piece, row, col);
		}
	}

	private void addTopRightMoves(int[][] board, Piece piece, List<int[]> moves) {
		int row = piece.getPosition()[0] + 1;
		int col = piece.getPosition()[1] + 1;
		if (row < 8 && col < 8) {
			addMove(board, moves, piece, row, col);
		}
	}

	private void addTopLeftMoves(int[][] board, Piece piece, List<int[]> moves) {
		int row = piece.getPosition()[0] + 1;
		int col = piece.getPosition()[1] - 1;
		if (row < 8 && col >= 0) {
			addMove(board, moves, piece, row, col);
		}
	}

	private void addBottomRightMoves(int[][] board, Piece piece, List<int[]> moves) {
		int row = piece.getPosition()[0] - 1;
		int col = piece.getPosition()[1] + 1;
		if (row >= 0 && col < 8) {
			addMove(board, moves, piece, row, col);
		}
	}

	private void addBottomLeftMoves(int[][] board, Piece piece, List<int[]> moves) {
		int row = piece.getPosition()[0] - 1;
		int col = piece.getPosition()[1] - 1;
		if (row >= 0 && col >= 0) {
			addMove(board, moves, piece, row, col);
		}
	}

	private static void addMove(int[][] board, List<int[]> moves, Piece piece, int row, int col) {
		int pieceValue = getPieceValue(piece);
		if (board[row][col] != pieceValue) {
			moves.add(new int[] { row, col });
		}
	}
}
