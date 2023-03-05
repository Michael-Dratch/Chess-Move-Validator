import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class OutputTest {
	Output output;

	@BeforeEach
	void setUp() {
		this.output = new Output();
	}

	@Test
	void pawnA2NoMoves() {
		Piece piece = new Piece(Piece.PieceType.PAWN, Piece.Color.WHITE, new int[] { 1, 0 });
		List<int[]> moves = new ArrayList<>();
		String output = this.output.createOutput(piece, moves);
		String expectedOutput = "LEGAL MOVES FOR Pa2:";
		assertEquals(expectedOutput, output);
	}

	@Test
	void kingH8NoMoves() {
		Piece piece = new Piece(Piece.PieceType.KING, Piece.Color.BLACK, new int[] { 7, 7 });
		List<int[]> moves = new ArrayList<>();
		String output = this.output.createOutput(piece, moves);
		String expectedOutput = "LEGAL MOVES FOR Kh8:";
		assertEquals(expectedOutput, output);
	}

	@Test
	void bishopD4NoMoves() {
		Piece piece = new Piece(Piece.PieceType.BISHOP, Piece.Color.BLACK, new int[] { 3, 3 });
		List<int[]> moves = new ArrayList<>();
		String output = this.output.createOutput(piece, moves);
		String expectedOutput = "LEGAL MOVES FOR Bd4:";
		assertEquals(expectedOutput, output);
	}

	@Test
	void queenD4NoMoves() {
		Piece piece = new Piece(Piece.PieceType.QUEEN, Piece.Color.BLACK, new int[] { 3, 3 });
		List<int[]> moves = new ArrayList<>();
		String output = this.output.createOutput(piece, moves);
		String expectedOutput = "LEGAL MOVES FOR Qd4:";
		assertEquals(expectedOutput, output);
	}

	@Test
	void rookD4NoMoves() {
		Piece piece = new Piece(Piece.PieceType.ROOK, Piece.Color.BLACK, new int[] { 3, 3 });
		List<int[]> moves = new ArrayList<>();
		String output = this.output.createOutput(piece, moves);
		String expectedOutput = "LEGAL MOVES FOR Rd4:";
		assertEquals(expectedOutput, output);
	}

	@Test
	void knightD4NoMoves() {
		Piece piece = new Piece(Piece.PieceType.KNIGHT, Piece.Color.BLACK, new int[] { 3, 3 });
		List<int[]> moves = new ArrayList<>();
		String output = this.output.createOutput(piece, moves);
		String expectedOutput = "LEGAL MOVES FOR Nd4:";
		assertEquals(expectedOutput, output);
	}

	@Test
	void kingD4MovesD5() {
		Piece piece = new Piece(Piece.PieceType.KING, Piece.Color.BLACK, new int[] { 3, 3 });
		List<int[]> moves = new ArrayList<int[]>();
		moves.add(new int[] { 4, 3 });
		String output = this.output.createOutput(piece, moves);
		String expectedOutput = "LEGAL MOVES FOR Kd4: d5";
		assertEquals(expectedOutput, output);
	}

	@Test
	void queenD4MovesD5D6D7() {
		Piece piece = new Piece(Piece.PieceType.KING, Piece.Color.BLACK, new int[] { 3, 3 });
		List<int[]> moves = new ArrayList<>();
		moves.add(new int[] { 4, 3 });
		moves.add(new int[] { 5, 3 });
		moves.add(new int[] { 6, 3 });
		String output = this.output.createOutput(piece, moves);
		String expectedOutput = "LEGAL MOVES FOR Kd4: d5, d6, d7";
		assertEquals(expectedOutput, output);
	}

	@Test
	void bishopD4MovesA1b2c3e5f6g7h8() {
		Piece piece = new Piece(Piece.PieceType.BISHOP, Piece.Color.BLACK, new int[] { 3, 3 });
		List<int[]> moves = new ArrayList<>();
		moves.add(new int[] { 0, 0 });
		moves.add(new int[] { 1, 1 });
		moves.add(new int[] { 2, 2 });
		moves.add(new int[] { 4, 4 });
		moves.add(new int[] { 5, 5 });
		moves.add(new int[] { 6, 6 });
		moves.add(new int[] { 7, 7 });
		String output = this.output.createOutput(piece, moves);
		String expectedOutput = "LEGAL MOVES FOR Bd4: a1, b2, c3, e5, f6, g7, h8";
		assertEquals(expectedOutput, output);
	}

	@Test
	void pawnC2MovesB3c3d3() {
		Piece piece = new Piece(Piece.PieceType.PAWN, Piece.Color.BLACK, new int[] { 1, 2 });
		List<int[]> moves = new ArrayList<>();
		moves.add(new int[] { 2, 1 });
		moves.add(new int[] { 2, 2 });
		moves.add(new int[] { 2, 3 });
		String output = this.output.createOutput(piece, moves);
		String expectedOutput = "LEGAL MOVES FOR Pc2: b3, c3, d3";
		assertEquals(expectedOutput, output);
	}
}
