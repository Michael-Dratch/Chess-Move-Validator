
public class Piece {

	public enum PieceType {
		KING, QUEEN, ROOK, BISHOP, KNIGHT, PAWN
	}

	public enum Color {
		WHITE, BLACK
	}

	private final PieceType type;

	private final Color color;

	private final int[] position;

	public Piece(PieceType type, Color color, int[] position) {
		this.type = type;
		this.color = color;
		this.position = position;
	}

	public PieceType getType() {
		return type;
	}

	public Color getColor() {
		return color;
	}

	public boolean isBlack() {
		return this.color == Color.BLACK;
	}

	public int[] getPosition() {
		return position;
	}
}
