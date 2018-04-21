package kr.java.swinglibrary.component;

import java.util.Arrays;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import org.apache.logging.log4j.LogManager;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;


class ProductTablePanel extends AbstractTablePanel {
	private static final long serialVersionUID = 1L;

	public ProductTablePanel() {
		super("제품");
		colNames = new String[] { "제품번호", "제품명", "제품 단가" };
	}

	@Override
	protected void setAlignWith() {
		tableCellAlignment(SwingConstants.CENTER, 0, 1, 2);
		tableSetWidth(100, 200, 100);			
	}
	
}

class Product implements ToArray {
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

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AbstractTablePanelTest {
	private static List<Product> productLists;
	private static ProductTablePanel panel;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		productLists = Arrays.asList(
				new Product("A001", "Test1", 1000), 
				new Product("A002", "Test2", 15000));
		panel= new ProductTablePanel();
		panel.loadData(productLists);
		
		JFrame jf = new JFrame("test");
		jf.setBounds(0, 0, 500, 300);
		jf.add(panel);
		jf.setVisible(true);
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		productLists = null;
	}

	@Before
	public void setUp() throws Exception {
		panel.getTable().setRowSelectionInterval(0, 1);
		int rowCnt = panel.getTable().getRowCount();
		LogManager.getLogger().debug("======= setUp() selectedRow : " + panel.getTable().getSelectedRow() + " rowCnt :" + rowCnt);
		getTablePrn();
	}
	
	@After
	public void tearDown() throws Exception {
		panel.getTable().clearSelection();
		int rowCnt = panel.getTable().getRowCount();
		LogManager.getLogger().debug("======= tearDown() selectedRow : " + panel.getTable().getSelectedRow()+ " rowCnt :" + rowCnt);
		getTablePrn();
	}

	@Test
	public void testASelectedNo() {
		String no = (String) panel.getSelectedNo();
		Assert.assertEquals("A002", no);
		LogManager.getLogger().debug("SelectedNo() - " + no);
	}
	
	@Test
	public void testBAddRow() {
		int beforeCnt = panel.getTable().getRowCount();
		panel.addRow(new Product("A003", "test3", 3000));
		int afterCnt = panel.getTable().getRowCount();
		
		Assert.assertEquals(beforeCnt+1, afterCnt);
		LogManager.getLogger().debug("AddRow() - " + beforeCnt + " -> "+ afterCnt);
	}
	
	@Test
	public void testCUpdateRow() {
		panel.updateRow(new Product("A004", "Test4", 4000));
		String no = (String) panel.getSelectedNo();
		LogManager.getLogger().debug("UpdateRow() - " + no);
	}

	@Test
	public void testDDelRow() {
		int beforeCnt = panel.getTable().getSelectedRow();
		panel.removeRow();
		int afterCnt = panel.getTable().getRowCount();
		
		Assert.assertEquals(beforeCnt+1, afterCnt);
		LogManager.getLogger().debug("DelRow() - " + beforeCnt + " -> "+ afterCnt);
	}
	
	private void getTablePrn() {
		DefaultTableModel model = (DefaultTableModel) panel.getTable().getModel();
		for(int i=0; i<model.getRowCount(); i++) {
			for(int c = 0; c <model.getColumnCount(); c++) {
				System.out.print(model.getValueAt(i, c) + "  ");
			}
			System.out.println();
		}
	}
}
