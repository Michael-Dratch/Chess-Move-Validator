
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
	void integrationTest1() {
		String input = """
				WHITE: Rf1, Kg1, Pf2, Ph2, Pg3
				BLACK: kb8, Ne8, Pa7, Pb7, Pc7, Ra5
				PIECE TO MOVE: Rf1
				""";
		String expectedOutput = "LEGAL MOVES FOR Rf1: e1, d1, c1, b1, a1";
		main.main(new String[] { input });
		assertEquals(expectedOutput, outputStreamCaptor.toString().trim());
	}

}
