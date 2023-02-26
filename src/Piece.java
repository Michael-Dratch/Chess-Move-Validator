
public class Piece {

    public enum PieceType {
        KING, QUEEN, ROOK, BISHOP, KNIGHT, PAWN
    }

    private final PieceType type;
    private final int[] position;

    public Piece(PieceType type, int[] position){
        this.type = type;
        this.position = position;
    }

    public PieceType getType(){
        return type;
    }

    public int[] getPosition(){
        return position;
    }
}
