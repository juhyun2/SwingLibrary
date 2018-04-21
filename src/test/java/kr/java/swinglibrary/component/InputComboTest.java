package kr.java.swinglibrary.component;

import java.util.Arrays;

import org.apache.logging.log4j.LogManager;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class InputComboTest {
	private static InputCombo<Obj> combo;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		combo = new InputCombo<>();
		combo.setItems(Arrays.asList(new Obj(1,"aa"), new Obj(2, "bb")));
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		combo = null;
	}
	
	@Test
	public void testInputComboLabel() {
		combo.setLblValue("타이틀");

		String res = combo.getLblValue();
		Assert.assertEquals("타이틀", res);
		LogManager.getLogger().debug("testInputComboLabel() - " + res);
	}
	
	@Test
	public void testGetSelectionItem() {
		combo.setSelectedItem(new Obj(2));
		Obj obj = (Obj) combo.getSelectedItem();
		Assert.assertEquals("bb", obj.test);
		LogManager.getLogger().debug("testGetSelectionItem() - " + obj);
	}
	
	@Test
	public void testComboBoxEmptyCheck() {
		combo.setSelectedItem(new Obj(2));
		try {
			combo.isEmptyCheck();
			LogManager.getLogger().debug("testComboBoxEmptyCheck() - Not Empty");
		} catch (Exception e) {
			Assert.fail();
			LogManager.getLogger().debug("testComboBoxEmptyCheck() - Empty");
		}
	}
	@Test
	public void testEnableCombo() {
		combo.setEnable(true);
		boolean enable = combo.isEnable();
		Assert.assertEquals(true, enable);
		LogManager.getLogger().debug("testEnableCombo() - " + enable);
	}
	
}

class Obj{
	int i;
	String test;
	
	public Obj(int i) {
		this.i = i;
	}
	public Obj(int i, String test) {
		this.i = i;
		this.test = test;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + i;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Obj other = (Obj) obj;
		if (i != other.i)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return String.format("Obj [%s, %s]", i, test);
	}
	
}