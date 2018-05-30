package kr.java.swinglibrary.component;

import java.util.Arrays;
import java.util.List;

import javax.swing.SwingConstants;
import javax.swing.table.TableModel;

import org.apache.logging.log4j.LogManager;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

@SuppressWarnings("serial")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AbstractTablePanelTest {
	private static List<Product> productLists;
	private static AbstractTablePanel panel;
	
	static class Product implements ToArray {

		private String code;
		private String name;
		private int price;
		
		public Product(String code, String name, int price) {
			this.code = code;
			this.name = name;
			this.price = price;
		}

		@Override
		public Object[] toArray() {
			return new Object[] {code, name, String.format("%,d", price)};
		}

	}
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		productLists = Arrays.asList(
				new Product("A001", "Test1", 1000), 
				new Product("A002", "Test2", 15000));
		
		panel= new AbstractTablePanel("Test") {
			@Override
			protected void setAlignWith() {
				tableCellAlignment(SwingConstants.CENTER, 0, 1, 2);
				tableSetWidth(100, 200, 100);	
			}

			@Override
			public void setColumnNames() {
				colNames = new String[] { "제품번호", "제품명", "제품 단가" };			
			}
		};
		
		panel.loadData(productLists);
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		Thread.sleep(3000);
		productLists = null;
	}

	@Before
	public void setUp() throws Exception {
		panel.getTable().setRowSelectionInterval(0, 1);
	}
	
	@After
	public void tearDown() throws Exception {
		panel.getTable().clearSelection();
	}

	@Test
	public void testASelectedNo() {
		String no = (String) panel.getSelectedNo();
		Assert.assertEquals("A002", no);
		LogManager.getLogger().debug("SelectedNo() - " + no);
		getTablePrn();
	}
	
	@Test
	public void testBAddRow() {
		int beforeCnt = panel.getTable().getRowCount();
		panel.addRow(new Product("A003", "test3", 3000));
		int afterCnt = panel.getTable().getRowCount();
		
		Assert.assertEquals(beforeCnt+1, afterCnt);
		LogManager.getLogger().debug("AddRow() - " + beforeCnt + " -> "+ afterCnt);
		getTablePrn();
	}
	
	@Test
	public void testCUpdateRow() {
		panel.updateRow(new Product("A004", "Test4", 4000));
		String no = (String) panel.getSelectedNo();
		LogManager.getLogger().debug("UpdateRow() - " + no);
		getTablePrn();
	}

	@Test
	public void testDDelRow() {
		int beforeCnt = panel.getTable().getSelectedRow();
		panel.removeRow();
		int afterCnt = panel.getTable().getRowCount();
		
		Assert.assertEquals(beforeCnt+1, afterCnt);
		LogManager.getLogger().debug("DelRow() - " + beforeCnt + " -> "+ afterCnt);
		getTablePrn();
	}
	
	private void getTablePrn() {
		TableModel model = panel.getTable().getModel();
		int rowSize = model.getRowCount();
		int colSize = model.getColumnCount();
		
		StringBuilder sb = new StringBuilder();
		LogManager.getLogger().info("\t------------------------");
		for(int row=0; row<rowSize; row++) {
			for(int column = 0; column <colSize; column++) {
				sb.append("\t" + model.getValueAt(row, column) + "  ");
			}
			LogManager.getLogger().info(sb);
			sb.setLength(0);
		}
		LogManager.getLogger().info("\t------------------------");
	}
}
