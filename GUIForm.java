package AWTXJDBC;
import AWTXJDBC.FormDB;
import java.awt.Button;
import java.awt.Checkbox;
import java.awt.CheckboxGroup;
import java.awt.Choice;
import java.awt.Color;
import java.awt.Frame;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class GUIForm extends Frame implements ActionListener {
	private final String USER="root";
	private final String PASSWORD="1220";
	private final String DBURL="jdbc:mysql://localhost:3306/Form?autoReconnect=true&useSSL=false";
	Frame f ;
	Label intro, fname,mname,lname,age,gender,College,perId,Mnumber,Courses,re,su;
	TextField tf,tm,tl,ta,tg,tc,te,tno;
	Button Reset,Submit;
	Windows wl;
	//Checkbox 
	Choice c,m,fm;
	CheckboxGroup cgf;
	
	GUIForm(){
		f= new Frame("Registration Form");
		f.setVisible(true);
		f.setSize(670,800);
		f.setLayout(null);
		f.setBackground(Color.lightGray);
		
		////For Closing
		wl = new Windows();
		
		/////////Labels///////
		intro = new Label("   REGISTRATION FORM");
		intro.setBounds(270,50,150,30);
		intro.setBackground(Color.cyan);
		
		fname = new Label("  First Name");
		fname.setBounds(140,110,80,30);
		fname.setBackground(Color.WHITE);
		
		mname = new Label("  Middle Name");
		mname.setBounds(140,170,80,30);
		mname.setBackground(Color.WHITE);
		
		lname = new Label("  Last Name");
		lname.setBounds(140,230,80,30);
		lname.setBackground(Color.WHITE);
		
		age = new Label("   AGE");
		age.setBounds(140,290,80,30);
		age.setBackground(Color.WHITE);
		
		gender = new Label("  Gender");
		gender.setBounds(140,350,80,30);
		gender.setBackground(Color.WHITE);
		
		College = new Label("  College Name");
		College.setBounds(140,410,90,30);
		College.setBackground(Color.WHITE);
		
		perId = new Label("  Personal Email ID");
		perId.setBounds(140,470,120,30);
		perId.setBackground(Color.WHITE);
		
		Mnumber = new Label(" Mobile Number");
		Mnumber.setBounds(140,530,90,30);
		Mnumber.setBackground(Color.WHITE);
		
		Courses = new Label("  Courses");
		Courses.setBounds(140,590,90,30);
		Courses.setBackground(Color.WHITE);
		
		re = new Label();
		re.setBounds(580,50,80,50);
		
		su = new Label();
		su.setBounds(160,720,180,30);
		
		///////TextFields//////
		tf = new TextField();
		tf.setBounds(260,110,200,30);
		
		tm = new TextField();
		tm.setBounds(260,170,200,30);
		
		tl= new TextField();
		tl.setBounds(260,230,200,30);
		
		ta = new TextField();
		ta.setBounds(260,290,50,30);
		
		tc = new TextField();
		tc.setBounds(260,410,250,30);
		
		te = new TextField();
		te.setBounds(275,470,250,30);
		
		tno = new TextField();
		tno.setBounds(260,530,200,30);
		
		
//		/////CheckBox//////
//		
//		cgf = new CheckboxGroup();
//		m = new Checkbox("Male",cgf,false);
//		m.setBounds(260,350,80,30);
//		fm= new Checkbox("Female",cgf,false);
//		fm.setBounds(340,350,80,30);
		
		m= new Choice();
		m.setBounds(260,350,200,50);
		m.add("Select Gender");
		m.add("male");
		m.add("Female");
		
		////////Choice////////		
		c = new Choice();
		c.setBounds(260,590,200,50);
		c.add("Select Courses");
		c.add("C");
		c.add("JAVA");
		c.add("PYTHON");
		c.add("R");
		c.add("RUBY");
		c.add("WEB DEVELOPMENT");
		c.add("KOTLIN");
		c.add("MYSQL");
		c.add("MONGO DB");
		c.add("C#");
		c.add("ORACLE DB");
		c.add("SWIFT");
		c.add("RUST");
		
		//////Button////////
		Reset = new Button("Reset");
		Reset.setBounds(200,670,60,30);
		Reset.setBackground(Color.yellow);
		
		
		Submit = new Button("Submit Form");
		Submit.setBounds(310,670,90,30);
		Submit.setBackground(Color.yellow);
		
		//add Listener to Button ///////
        Submit.addActionListener(this);	
        Reset.addActionListener(this);

		
		///ADD Components in  Frame/////
		
		f.addWindowListener(wl);
		f.add(intro);
		f.add(fname);
		f.add(tf);
		f.add(mname);
		f.add(tm);
		f.add(lname);
		f.add(tl);
		f.add(age);
		f.add(ta);
		f.add(gender);
		f.add(m);
		//f.add(fm);
		f.add(College);
		f.add(tc);
		f.add(perId);
		f.add(te);
		f.add(Mnumber);
		f.add(tno);
		f.add(Courses);
		f.add(c);
		f.add(Reset);
		f.add(Submit);
		f.add(re);
		f.add(su);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == Submit ) {
			 
			try {
				Connection conn = DriverManager.getConnection(DBURL,USER,PASSWORD);
				PreparedStatement ps = conn.prepareStatement("insert into FormData values(?,?,?,?,?,?,?,?,?)");
			    ps.setString(1, tf.getText());
			    ps.setString(2, tm.getText());
			    ps.setString(3, tl.getText());
			    ps.setString(4, ta.getText().toString());
			    ps.setString(5, m.getSelectedItem().toString());
			    ps.setString(6, tc.getText());
			    ps.setString(7, te.getText());
			    ps.setString(8, tno.getText());
			    ps.setString(9, c.getSelectedItem().toString());
			    
			    ps.executeUpdate();
			    
			    su.setText("Form Successfully Submitted");
			    System.out.println("Form Submitted");
			} 
			catch (SQLException e1) {
				e1.printStackTrace();
			}
			
		}
		
		else if(e.getSource() == Reset) {
			tf.setText(null);
			tm.setText(null);
			tl.setText(null);
			ta.setText(null);
			tc.setText(null);
			te.setText(null);
			tno.setText(null);
			m.select("Select Gender");
			c.select("Select Courses");
		}
		
	}
  }










class Windows implements WindowListener{

	@Override
	public void windowOpened(WindowEvent e) {
		
		
	}

	@Override
	public void windowClosing(WindowEvent e) {
		System.out.println("Windows Closing");
		System.exit(0);
	}

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
