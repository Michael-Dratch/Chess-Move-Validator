import java.util.ArrayList;
import java.util.List;

public class KnightMoveFinder extends MoveFinder {
	@Override
	public List<int[]> findMoves(int[][] board, Piece piece) {
		List<int[]> moves = new ArrayList<>();

		int[] position = piece.getPosition();

		addTopRightMoves(board, piece, moves, position);
		addTopLeftMoves(board, piece, moves, position);
		addBottomLeftMoves(board, piece, moves, position);
		addBottomRightMoves(board, piece, moves, position);

		return moves;
	}

	private static void addTopRightMoves(int[][] board, Piece piece, List<int[]> moves, int[] position) {
		int row = position[0] + 2;
		int col = position[1] + 1;
		if (row < 8 && col < 8) {
			addMove(board, moves, piece, row, col);
		}

		row = position[0] + 1;
		col = position[1] + 2;
		if (row < 8 && col < 8) {
			addMove(board, moves, piece, row, col);
		}
	}

	private static void addTopLeftMoves(int[][] board, Piece piece, List<int[]> moves, int[] position) {
		int row = position[0] + 2;
		int col = position[1] - 1;
		if (row < 8 && col >= 0) {
			addMove(board, moves, piece, row, col);
		}

		row = position[0] + 1;
		col = position[1] - 2;
		if (row < 8 && col >= 0) {
			addMove(board, moves, piece, row, col);
		}
	}

	private static void addBottomLeftMoves(int[][] board, Piece piece, List<int[]> moves, int[] position) {
		int row = position[0] - 2;
		int col = position[1] - 1;
		if (row >= 0 && col >= 0) {
			addMove(board, moves, piece, row, col);
		}

		row = position[0] - 1;
		col = position[1] - 2;
		if (row >= 0 && col >= 0) {
			addMove(board, moves, piece, row, col);
		}
	}

	private static void addBottomRightMoves(int[][] board, Piece piece, List<int[]> moves, int[] position) {
		int row = position[0] - 2;
		int col = position[1] + 1;
		if (row >= 0 && col < 8) {
			addMove(board, moves, piece, row, col);
		}

		row = position[0] - 1;
		col = position[1] + 2;
		if (row >= 0 && col < 8) {
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
