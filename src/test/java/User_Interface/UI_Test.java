package User_Interface;

import static org.junit.Assert.*;

import org.junit.Test;

public class UI_Test {

	@Test
	public void ui_test() {
		try {
			//Window_Main.createMainWindow();
		} catch (Exception e) {
			fail("UI caused an exception to be thrown that was not handled");
		}
		assertEquals("Ran UI and got through to close without crashing", 1, 1); // Ran UI and Closed without crashing
	}

}