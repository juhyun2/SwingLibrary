package kr.java.swinglibrary.component;

import java.awt.GridLayout;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class InputCombo<T> extends JPanel {
	private JComboBox<T> comb;
	private JLabel lbl;

	public InputCombo() {
		setLayout(new GridLayout(1, 0, 10, 0));

		lbl = new JLabel("New label");
		lbl.setHorizontalAlignment(SwingConstants.RIGHT);
		add(lbl);

		comb = new JComboBox<>();
		add(comb);
	}
	
	public void setLblValue(String name){
		lbl.setText(name.trim());
	}
	
	public String getLblValue(){
		return lbl.getText().trim();
	}
	
	public void isEmptyCheck() throws Exception {
		if (comb.getSelectedItem().equals("")) {
			comb.requestFocus();
			throw new Exception("공백 존재");
		}
	}

	public Object getSelectedItem() {
		return comb.getSelectedItem();
	}

	public void setSelectedItem(Object obj) {
		if (obj==null) {
			comb.setSelectedIndex(-1);
		}else {
			comb.setSelectedItem(obj);
		}
	}

	public void setEnable(boolean isEnable) {
		comb.setEnabled(isEnable);
	}
	
	public boolean isEnable() {
		return comb.isEnabled();
	}
	
	public void setItems(List<T> items) {
		for(T obj : items) {
			comb.addItem(obj);
		}
		comb.setSelectedIndex(-1);
	}
}
