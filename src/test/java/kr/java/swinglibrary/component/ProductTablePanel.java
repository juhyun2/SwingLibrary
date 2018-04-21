package kr.java.swinglibrary.component;

import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class ProductTablePanel extends AbstractTablePanel {
	
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
