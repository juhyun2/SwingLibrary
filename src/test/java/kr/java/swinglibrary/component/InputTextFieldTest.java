package kr.java.swinglibrary.component;

import org.apache.logging.log4j.LogManager;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class InputTextFieldTest {
	private static InputTextField ic;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		ic = new InputTextField();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		ic = null;
	}

	@Test
	public void testInputTextField() {
		ic.setTfValue("테스트");

		String res = ic.getTfValue();
		Assert.assertEquals("테스트", res);
		LogManager.getLogger().debug("testInputTextField() - " + res);
	}

	@Test
	public void testInputTextFieldLabel() {
		ic.setLblValue("타이틀");

		String res = ic.getLblValue();
		Assert.assertEquals("타이틀", res);
		LogManager.getLogger().debug("testInputTextFieldLabel() - " + res);
	}

	@Test
	public void testInputTextFieldEmpty() {
		ic.setTfValue("aa");

		try {
			ic.isEmptyCheck();
			LogManager.getLogger().debug("isEmptyCheck() - Not Empty");
		} catch (Exception e) {
			Assert.fail();
			LogManager.getLogger().debug("isEmptyCheck() - Empty");
		}
	}

	@Test
	public void testInputTextFieldIsValidCheck() {
		try {
			ic.setTfValue("A000");
			ic.isValidCheck("[A-Z][0-9]{3}", "첫글자는 A-Z 숫자 3자리만 가능");
			ic.setTfValue("000");
			ic.isValidCheck("[0-9]{3,8}", "정수 3자리 이상 ~ 8자리만 가능");
			LogManager.getLogger().debug("isValidCheck() - Valid");
		} catch (Exception e) {
			Assert.fail();
			LogManager.getLogger().debug("isValidCheck() - Invalid");
		}
	}

	@Test
	public void testInputTextTfEnable() {
		ic.setEnabled(true);

		boolean res = ic.isEditableTf();
		Assert.assertEquals(true, res);
		LogManager.getLogger().debug("testInputTextTfEnable() - " + res);
	}
}
