import java.util.ArrayList;
import java.util.List;

public class BishopMoveFinder extends MoveFinder {
	@Override
	public List<int[]> findMoves(int[][] board, Piece piece) {
		List<int[]> moves = new ArrayList<>();
		addTopRightMoves(board, piece, moves);
		addTopLeftMoves(board, piece, moves);
		addBottomRightMoves(board, piece, moves);
		addBottomLeftMoves(board, piece, moves);
		return moves;
	}

	private void addTopRightMoves(int[][] board, Piece piece, List<int[]> moves) {
		int pieceValue = getPieceValue(piece);
		int row = piece.getPosition()[0] + 1;
		int col = piece.getPosition()[1] + 1;
		while (row < 8 && col < 8) {
			if (board[row][col] == 0) {
				moves.add(new int[] { row, col });
			} else if (board[row][col] == -pieceValue) {
				moves.add(new int[] { row, col });
				break;
			} else {
				break;
			}
			row++;
			col++;
		}
	}

	private void addTopLeftMoves(int[][] board, Piece piece, List<int[]> moves) {
		int pieceValue = getPieceValue(piece);
		int row = piece.getPosition()[0] + 1;
		int col = piece.getPosition()[1] - 1;
		while (row < 8 && col >= 0) {
			if (board[row][col] == 0) {
				moves.add(new int[] { row, col });
			} else if (board[row][col] == -pieceValue) {
				moves.add(new int[] { row, col });
				break;
			} else {
				break;
			}
			row++;
			col--;
		}
	}

	private void addBottomLeftMoves(int[][] board, Piece piece, List<int[]> moves) {
		int pieceValue = getPieceValue(piece);
		int row = piece.getPosition()[0] - 1;
		int col = piece.getPosition()[1] - 1;
		while (row >= 0 && col >= 0) {
			if (board[row][col] == 0) {
				moves.add(new int[] { row, col });
			} else if (board[row][col] == -pieceValue) {
				moves.add(new int[] { row, col });
				break;
			} else {
				break;
			}
			row--;
			col--;
		}
	}

	private void addBottomRightMoves(int[][] board, Piece piece, List<int[]> moves) {
		int pieceValue = getPieceValue(piece);
		int row = piece.getPosition()[0] - 1;
		int col = piece.getPosition()[1] + 1;
		while (row >= 0 && col < 8) {
			if (board[row][col] == 0) {
				moves.add(new int[] { row, col });
			} else if (board[row][col] == -pieceValue) {
				moves.add(new int[] { row, col });
				break;
			} else {
				break;
			}
			row--;
			col++;
		}
	}
}
