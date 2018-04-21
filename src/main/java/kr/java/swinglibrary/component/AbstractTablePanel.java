package kr.java.swinglibrary.component;

import java.awt.BorderLayout;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

@SuppressWarnings("serial")
public abstract class AbstractTablePanel extends JPanel {
	protected JTable table;
	protected NonEditableModel model;
	protected int selectRowIndex;
	protected Object[] colNames;
	
	public AbstractTablePanel(String title) {
		initComponents(title);
		setColumnNames();
	}

	private void initComponents(String title) {
		setBorder(new TitledBorder(null, title, TitledBorder.LEADING, TitledBorder.TOP, null, null));
		setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane, BorderLayout.CENTER);

		table = new JTable();
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setPreferredScrollableViewportSize(table.getPreferredSize());
		scrollPane.setViewportView(table);
	}

	protected abstract void setAlignWith();

	public void setPopupMenu(JPopupMenu popUpMenu) {
		table.setComponentPopupMenu(popUpMenu);
	}

	protected void tableCellAlignment(int align, int... idx) {
		DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();
		dtcr.setHorizontalAlignment(align);

		TableColumnModel model = table.getColumnModel();
		for (int i = 0; i < idx.length; i++) {
			model.getColumn(idx[i]).setCellRenderer(dtcr);
		}
	}

	protected void tableSetWidth(int... width) {
		TableColumnModel cModel = table.getColumnModel();

		for (int i = 0; i < width.length; i++) {
			cModel.getColumn(i).setPreferredWidth(width[i]);
		}
	}

	public Object getSelectedNo() {
		return table.getValueAt(table.getSelectedRow(), 0);
	}

	public void addRow(ToArray item) {
		model.addRow(item.toArray());
	}

	public void removeRow() {
		model.removeRow(table.getSelectedRow());
	}

	public void updateRow(ToArray item) {
		int row = table.getSelectedRow();
		Object[] data = item.toArray();
		int colSize = model.getColumnCount();
		
		for(int column = 0; column < colSize; column++) {
			model.setValueAt(data[column], row, column);
		}
	}

	public abstract void setColumnNames();
	
	public void loadData(List<? extends ToArray> items) {
		Object[][] datas = toArray(items);
		model = new NonEditableModel(datas, colNames);
		table.setModel(model);
		setAlignWith();
	}

	protected Object[][] toArray(List<? extends ToArray> items) {
		Object[][] results = new Object[items.size()][];
		for (int i = 0; i < items.size(); i++) {
			results[i] = items.get(i).toArray();
		}
		return results;
	}

	public JTable getTable() {
		return table;
	}

	class NonEditableModel extends DefaultTableModel {

		public NonEditableModel(Object[][] data, Object[] columnNames) {
			super(data, columnNames);
		}

		@Override
		public boolean isCellEditable(int row, int column) {
			return false;
		}
	}
}