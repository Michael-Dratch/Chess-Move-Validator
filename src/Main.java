import java.util.List;

public class Main {
	public static void main(String args[]) {
		Input input = new Input();
		Output output = new Output();

		String inputString = args[0];

		int[][] board = input.getBoard(inputString);
		Piece piece = input.getPieceToMove(inputString);

		List<int[]> moves = getMoves(board, piece);

		String outputString = output.createOutput(piece, moves);
		System.out.println(outputString);
	}

	private static List<int[]> getMoves(int[][] board, Piece piece) {
		Piece.PieceType pieceType = piece.getType();
		MoveFinder moveFinder = getMoveFinder(pieceType);
		return moveFinder.findMoves(board, piece);
	}

	private static MoveFinder getMoveFinder(Piece.PieceType pieceType) {
		MoveFinder moveFinder;
		switch (pieceType) {
		case KING:
			moveFinder = new KingMoveFinder();
			break;
		case QUEEN:
			moveFinder = new QueenMoveFinder();
			break;
		case ROOK:
			moveFinder = new RookMoveFinder();
			break;
		case BISHOP:
			moveFinder = new BishopMoveFinder();
			break;
		case KNIGHT:
			moveFinder = new KnightMoveFinder();
			break;
		default:
			moveFinder = new PawnMoveFinder();
		}
		return moveFinder;
	}
}
