package kr.java.swinglibrary.component;

import java.awt.GridLayout;
import java.util.regex.Pattern;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class InputTextField extends JPanel {
	private JTextField tf;
	private JLabel lbl;

	public InputTextField() {
		setLayout(new GridLayout(1, 0, 10, 0));
		
		lbl = new JLabel("New label");
		lbl.setHorizontalAlignment(SwingConstants.RIGHT);
		add(lbl);
		
		tf = new JTextField();
		add(tf);
		tf.setColumns(10);
	}

	public void setTfValue(String value){
		tf.setText(value.trim());
	}
	
	public String getTfValue(){
		return tf.getText().trim();
	}
	
	public void setLblValue(String name){
		lbl.setText(name.trim());
	}
	
	public String getLblValue(){
		return lbl.getText().trim();
	}

	public void setEditableTf(boolean isEnable) {
		tf.setEditable(isEnable);
	}
	
	public boolean isEditableTf() {
		return tf.isEditable();
	}
	
	public void isEmptyCheck() throws Exception{
		if(getTfValue().equals("")){
			throw new Exception("공백 존재");
		}
	}
	
	public void isValidCheck(String pattern, String msg) throws Exception{
		if(!Pattern.matches(pattern, getTfValue())){
			throw new Exception(msg);
		}
	}
}
