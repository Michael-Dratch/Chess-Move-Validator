
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class MainTest {

	private final PrintStream standardOut = System.out;
	private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

	Main main;

	@BeforeEach
	public void setUp() {
		System.setOut(new PrintStream(outputStreamCaptor));
		main = new Main();
	}

	@AfterEach
	public void tearDown() {
		System.setOut(standardOut);
	}

	@Test
	void rookIntegrationTest1() {
		String input = """
				WHITE: Rf1, Kg1, Pf2, Ph2, Pg3
				BLACK: kb8, Ne8, Pa7, Pb7, Pc7, Ra5
				PIECE TO MOVE: Rf1
				""";
		String expectedOutput = "LEGAL MOVES FOR Rf1: e1, d1, c1, b1, a1";
		main.main(new String[] { input });
		assertEquals(expectedOutput, outputStreamCaptor.toString().trim());
	}

	@Test
	void pawnIntegrationTest2() {
		String input = """
				WHITE: Pc3, Pc2, Pd2
				BLACK:
				PIECE TO MOVE: Pc2
				""";
		String expectedOutput = "LEGAL MOVES FOR Pc2:";
		main.main(new String[] { input });
		assertEquals(expectedOutput, outputStreamCaptor.toString().trim());
	}

	@Test
	void knightIntegrationTest3() {
		String input = """
				WHITE: Nf3, Rf1, Pf2, Ph2, Kg1
				BLACK: Pa7, Pc7, Be8
				PIECE TO MOVE: Nf3
				""";
		String expectedOutput = "LEGAL MOVES FOR Nf3: g5, h4, e5, d4, e1, d2";
		main.main(new String[] { input });
		assertEquals(expectedOutput, outputStreamCaptor.toString().trim());
	}

	@Test
	void bishopIntegrationTest4() {
		String input = """
				WHITE: Ph2, Rc2
				BLACK: Bf5, Ph7, Pg7, Kc8
				PIECE TO MOVE: Bf5
				""";
		String expectedOutput = "LEGAL MOVES FOR Bf5: g6, e6, d7, g4, h3, e4, d3, c2";
		main.main(new String[] { input });
		assertEquals(expectedOutput, outputStreamCaptor.toString().trim());
	}

	@Test
	void kingIntegrationTest5() {
		String input = """
				WHITE: Na3, Pg5, Qf1, Rb2
				BLACK: kc3, Rc2, Pd2
				PIECE TO MOVE: Kc3
				""";
		String expectedOutput = "LEGAL MOVES FOR Kc3: c4, d3, b3, d4, b4, b2";
		main.main(new String[] { input });
		assertEquals(expectedOutput, outputStreamCaptor.toString().trim());
	}

	@Test
	void queenIntegrationTest6() {
		String input = """
				WHITE: Qd2, Ke1, Pc2, Pf2
				BLACK: Rd6, Na5, Pa7, Pb7, Pc7
				PIECE TO MOVE: Qd2
				""";
		String expectedOutput = "LEGAL MOVES FOR Qd2: d3, d4, d5, d6, d1, e2, e3, f4, g5, h6, c3, b4, a5, c1";
		main.main(new String[] { input });
		assertEquals(expectedOutput, outputStreamCaptor.toString().trim());
	}

}
