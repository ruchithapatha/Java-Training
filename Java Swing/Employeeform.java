
import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Enumeration;
import java.awt.*;
import javax.swing.table.DefaultTableModel;

public class Employeeform {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JFrame f=new JFrame("Registration Form");
		f.setBounds(50,50,1000,2000);
		f.setLayout(null);
		f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//f.getContentPane().setBackground(Color.LIGHT_GRAY);

		
		JLabel l1=new JLabel("Registration Form");
		l1.setBounds(250,50,150,40);
		f.add(l1);
		
		JLabel l2=new JLabel("First name :");
		l2.setBounds(100,150,100,30);
		f.add(l2);
		
		JTextField tf1=new JTextField();
		tf1.setBounds(200,150,200,30);
		f.add(tf1);
		
		JLabel l3=new JLabel("Last name :");
		l3.setBounds(100,200,100,30);
		f.add(l3);
		
		JTextField tf2=new JTextField();
		tf2.setBounds(200,200,200,30);
		f.add(tf2);
		
		JLabel l4=new JLabel("Email Address :");
		l4.setBounds(100,250,100,30);
		f.add(l4);
		
		JTextField tf3=new JTextField();
		tf3.setBounds(200,250,200,30);
		f.add(tf3);
		
		JLabel l5=new JLabel("Phone Number :");
		l5.setBounds(100,300,100,30);
		f.add(l5);
		
		JTextField tf4=new JTextField();
		tf4.setBounds(200,300,200,30);
		f.add(tf4);
		
		JLabel l6=new JLabel("Home Address :");
		l6.setBounds(100,350,100,30);
		f.add(l6);
		
		JTextArea tf5=new JTextArea();
		tf5.setBounds(200,350,200,100);
		f.add(tf5);
		
		JLabel l7=new JLabel("Gender :");
		l7.setBounds(100,470,100,30);
		f.add(l7);
		
		JRadioButton r1=new JRadioButton("Male");    
		JRadioButton r2=new JRadioButton("Female");    
		JRadioButton r3=new JRadioButton("Other"); 
		 r1.setBounds(200,470,100,30);
		 r2.setBounds(300,470,100,30);
		 r3.setBounds(400,470,100,30);
		 ButtonGroup bg=new ButtonGroup();  
		 bg.add(r1);
		 bg.add(r2); 
		 bg.add(r3);
		 f.add(r1);
		 f.add(r2);
		 f.add(r3);
		 
		 JLabel l8=new JLabel("Interested Subject :");
		 l8.setBounds(100,520,200,30);
		 f.add(l8);
		 
		 JCheckBox box1=new JCheckBox("Java");
		 box1.setBounds(250,520,100,30);
		 f.add(box1);
		 JCheckBox box2=new JCheckBox("C++");
		 box2.setBounds(350,520,100,30);
		 f.add(box2);
		 JCheckBox box3=new JCheckBox("Python");
		 box3.setBounds(450,520,100,30);
		 f.add(box3);
		 
		 JLabel l9=new JLabel("Degree :");
		 l9.setBounds(100,570,200,30);
		 f.add(l9);
		 
		 
		 String branch[]={"B.Tech","M.tech","Degree"};        
		 JComboBox cb=new JComboBox(branch);    
		 cb.setBounds(250,570,200,30);    
		 f.add(cb);  
		 
		 
		 JButton btn=new JButton("SUBMIT");
		 btn.setBounds(150,650,100,30);
		 f.add(btn);
		 
		 JButton btn1=new JButton("RESET");
		 btn1.setBounds(300,650,100,30);
		 f.add(btn1);
		 
		 JLabel lab=new JLabel();
		 f.add(lab);
		 lab.setBounds(200,700,200,30);
		 
		 btn1.addActionListener(new ActionListener(){  
			 public void actionPerformed(ActionEvent e){ 
				  tf1.setText("");
				  tf2.setText("");
				  tf3.setText("");
				  tf4.setText("");
				  tf5.setText("");
				  bg.clearSelection();
				  
				JCheckBox[] checkBoxes = {box1,box2,box3};
				for (JCheckBox checkBox : checkBoxes) {
				    checkBox.setSelected(false);
				}
				cb.setSelectedIndex(-1); 
	         }  
	     }); 
		 
		 String[] columnNames = {"Name", "Email","Phone Number","Gender","Subjects","Degree"};
		 DefaultTableModel tableModel = new DefaultTableModel(columnNames,0);
		 tableModel.addColumn(columnNames);
		 JTable table = new JTable(tableModel);
		 table.setBounds(700,100,600,400);
		 f.add(table);
		 
		 
		 btn.addActionListener(new ActionListener(){  
			 public void actionPerformed(ActionEvent e){ 
				 
				 JLabel lab=new JLabel();
				 f.add(lab);
				 lab.setBounds(200,700,500,30);
				 
				 int result = JOptionPane.showConfirmDialog(f,"Sure? You want to submit?", "Submit Confiramtion",
			               JOptionPane.YES_NO_OPTION);
			        if(result == JOptionPane.YES_OPTION){
			        	if(tf1.getText().isEmpty() || tf2.getText().isEmpty() || tf3.getText().isEmpty() || tf4.getText().isEmpty() || tf5.getText().isEmpty() || (!r1.isSelected() && !r2.isSelected() && !r3.isSelected())|| (!box1.isSelected() && !box2.isSelected() && !box3.isSelected()) || (cb.getSelectedIndex() == -1) ) {
			            	JOptionPane.showMessageDialog(f, "Please fill in all fields.");
			            }
			            lab.setText("You Form is Submited.");
			            
			            //else {
			             //  lab.setText("Your form is not sbmitted");
			            //}
			           String name = tf1.getText()+" "+tf2.getText();
			           String email=tf3.getText();
			           String phno=tf4.getText();
			           JRadioButton selectedButton = null;
			            for (Enumeration<AbstractButton> buttons = bg.getElements(); buttons.hasMoreElements();) {
			                AbstractButton button = buttons.nextElement();
			                if (button.isSelected()) {
			                    selectedButton = (JRadioButton) button;
			                    break;
			                }
			            }
			            String gender = selectedButton.getText();
			            
			            ArrayList<String> subjects = new ArrayList<>();

			            // Check which checkboxes are selected and add their names to the ArrayList
			            if (box1.isSelected()) {
			                subjects.add(box1.getText());
			            }
			            if (box2.isSelected()) {
			                subjects.add(box2.getText());
			            }
			            if (box3.isSelected()) {
			                subjects.add(box3.getText());
			            }
			            
			            String degree = (String) cb.getSelectedItem();
		
			         
			            Object[] rowData = {name, email,phno,gender,subjects,degree};
			            tableModel.addRow(rowData);
			            
			              tf1.setText("");
						  tf2.setText("");
						  tf3.setText("");
						  tf4.setText("");
						  tf5.setText("");
						  bg.clearSelection();
						  
						JCheckBox[] checkBoxes = {box1,box2,box3};
						for (JCheckBox checkBox : checkBoxes) {
						    checkBox.setSelected(false);
						}
						cb.setSelectedIndex(-1);
			        }
			        else
			        	lab.setText("Your form is not submitted");
			            
			         }  
			     });  
		
		 
	}
}