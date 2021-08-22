package frames;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;


/**
 * 登录页面
 */
public class LoginFrame extends JFrame implements ActionListener, ItemListener {

	private JComboBox dbDriver;// DB数据库方言下拉列表
	private JComboBox dbURL;// DB数据库连接字符串下拉列表
	private JTextField ipTF;//IP地址
	private JTextField duankouTF;//IP地址
	private JTextField dbnam;//数据库名称
	private JTextField dbnamTF;//数据库账号
	private JPasswordField passwordTF;//数据库密码
	private JButton loginButton = null, resetButton = null;

	private String dbnames = null, passwordname = null, urlname = null,drivername = null ,duankouname = null
			,dbusernames = null ,dbipname = null;

	private static String udrivername="com.microsoft.sqlserver.jdbc.SQLServerDriver";

	public LoginFrame(){
		super();
		Image img=getToolkit().createImage("image/tols.jpg");
		//设置窗体的图标
		setIconImage(img);
		getContentPane().setLayout(null);
		jFrameValidate();
		setTitle("MYSQL数据库");
		JLabel backgroundLabel = new JLabel();

		backgroundLabel.setBounds(0, 0, 768, 540);
		backgroundLabel.setText("<html><img width=776 height=574 src='"
				+ this.getClass().getResource("login1.jpg") + "'></html>");
//  backgroundLabel.setText("<html><img width=776 height=574 src='"
//    + System.getProperty("user.dir")+"/image/login1.jpg" + "'></html>");
		backgroundLabel.setLayout(null);
		
		final JLabel dbDriverLable = new JLabel();
		dbDriverLable.setText("DB数据库方言：");
		dbDriverLable.setBounds(180, 153, 100, 18);
		backgroundLabel.add(dbDriverLable);

		final JLabel dbURLLabel = new JLabel();
		dbURLLabel.setText("DB连接字符串：");
		dbURLLabel.setBounds(180, 193, 100, 18);
		backgroundLabel.add(dbURLLabel);

		final JLabel dbLabel = new JLabel();
		dbLabel.setText("DB数据库名称：");
		dbLabel.setBounds(180, 233, 100, 18);
		backgroundLabel.add(dbLabel);
		
		final JLabel dbnameLabel = new JLabel();
		dbnameLabel.setText("DB数据库账号：");
		dbnameLabel.setBounds(180, 273, 100, 18);
		backgroundLabel.add(dbnameLabel);

		final JLabel dbpasswordLable = new JLabel();
		dbpasswordLable.setText("DB数据库密码：");
		dbpasswordLable.setBounds(180, 313, 100, 18);
		backgroundLabel.add(dbpasswordLable);

		// DB数据库方言列表
		String[] dbDriverAdd = { "oracle.jdbc.driver.OracleDriver", 
					"com.ibm.db2.jdbc.app.DB2Driver", 
					"(sql 2000)com.microsoft.sqlserver.jdbc.SQLServerDriver",
					"(sql 2005or2008)com.microsoft.sqlserver.jdbc.SQLServerDriver",
					"com.sybase.jdbc.SybDriver", 
					"com.informix.jdbc.IfxDriver", "com.mysql.jdbc.Driver",
				"org.postgresql.Driver"};
		dbDriver = new JComboBox(dbDriverAdd);
		dbDriver.setSelectedIndex(0);
		dbDriver.setEditable(false);
		dbDriver.addItemListener(this);
		//335, 203 坐标位置   280, 22 长宽
		dbDriver.setBounds(270, 153, 300, 22);
		backgroundLabel.add(dbDriver);

// DB数据库连接字符串列表
/*
   * "jdbc:oracle:thin:@localhost:1521:orcl", 
         "jdbc:db2://localhost:5000/sample", 
                  "jdbc:microsoft:sqlserver://localhost:1433;DatabaseName=db2",
                  "jdbc:sqlserver://localhost:1433;DatabaseName=db2", 
                  "jdbc:sybase:Tds:localhost:5007/myDB", 
                  "jdbc:informix-sqli://123.45.67.89:1533/myDB", 
                  "jdbc:mysql://localhost:3306/myDB",
                  "jdbc:postgresql://localhost/myDB"
   */
		String[] dbURLAdd = { "jdbc:oracle:thin:@",
				"jdbc:db2://", 
	"jdbc:microsoft:sqlserver://",
	"jdbc:sqlserver://", 
	"jdbc:sybase:Tds:", 
	"jdbc:informix-sqli://", 
	"jdbc:mysql://",
		"jdbc:postgresql://"};
		dbURL = new JComboBox(dbURLAdd);
		dbURL.setSelectedIndex(0);
		dbURL.addItemListener(this);
		//禁止操作
		dbURL.setEditable(false);
		dbURL.setEnabled(false);
		dbURL.setBounds(270, 193, 150, 22);
		backgroundLabel.add(dbURL);

		ipTF = new JTextField();
		ipTF.setBounds(422, 193, 80, 22);
		backgroundLabel.add(ipTF);

		final JLabel nameLabel = new JLabel();
		nameLabel.setText(":");
		//设置字体 “dialog”代表字体，1代表样式(1是粗体，0是平常的）15是字号
		nameLabel.setFont(new java.awt.Font("Dialog",1,15));
		nameLabel.setBounds(505, 193, 100, 22);
		backgroundLabel.add(nameLabel);

		duankouTF = new JTextField();
		duankouTF.setBounds(510, 193, 50, 22);
		backgroundLabel.add(duankouTF);

		dbnam = new JTextField();
		dbnam.setBounds(270, 233, 300, 22);
		backgroundLabel.add(dbnam);

		dbnamTF = new JTextField();
		dbnamTF.setBounds(270, 273, 300, 22);
		backgroundLabel.add(dbnamTF);

		passwordTF = new JPasswordField();
		passwordTF.setBounds(270, 313, 300, 22);
		backgroundLabel.add(passwordTF);

		loginButton = new JButton("登录");
		resetButton = new JButton("重置");
		backgroundLabel.add(loginButton);
		backgroundLabel.add(resetButton);
		loginButton.setBounds(280, 360, 80, 30);
		resetButton.setBounds(400, 360, 80, 30);
		loginButton.addActionListener(this);
		resetButton.addActionListener(this);

		getContentPane().add(backgroundLabel);
	
	}
	
	public void jFrameValidate() {
		Toolkit tk = getToolkit();// 获得屏幕的宽和高
		Dimension dim = tk.getScreenSize();
		this.setResizable(false);
		this.setBounds(dim.width / 2 - 380, dim.height / 2 - 270, 776, 574);
		validate();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
// 下拉列表改变时的事件处理
	public void itemStateChanged(ItemEvent e) {
		if (e.getSource() == dbDriver) {
			if (e.getStateChange() == ItemEvent.SELECTED
					&& dbDriver.getSelectedIndex() != -1)
				dbURL.setSelectedIndex(dbDriver.getSelectedIndex());
		} else if (e.getSource() == dbURL) {
			if (e.getStateChange() == ItemEvent.SELECTED
					&& dbURL.getSelectedIndex() != -1)
				dbURL.setSelectedIndex(dbURL.getSelectedIndex());
	}
		
		return;

	}
// 登录 和重置事件的处理
	public void actionPerformed(ActionEvent e) {	
		if (e.getSource() == loginButton) {// 登录
			getValues();// 得到界面中的所有项的值
			checkUser();// 登录验证
		}else if (e.getSource() == resetButton) {
//			reset();// 重新设置各项的值
		}
	}
	
	private void checkUser() {
		if(dbipname==null||dbipname.equals("")){
			JOptionPane.showMessageDialog(this, "<html><h4>"+ "请输入连接数据库服务器IP地址、IP不容许为空值！" + "<html><h4>", "警告",JOptionPane.WARNING_MESSAGE);
		}else if(duankouname==":"||duankouname.equals(":")){
			JOptionPane.showMessageDialog(this, "<html><h4>"+ "请输入数据库端口、端口不容许为空！" + "<html><h4>", "警告",JOptionPane.WARNING_MESSAGE);
		}else if(dbnames==":"||dbnames.equals(":")||dbnames=="/"||dbnames.equals("/")||dbnames==";DatabaseName="||dbnames.equals(";DatabaseName=")){
			JOptionPane.showMessageDialog(this, "<html><h4>"+ "请输入数据库名称、数据库名称不容许为空！" + "<html><h4>", "警告",JOptionPane.WARNING_MESSAGE);
		}else if(dbusernames==null||dbusernames.equals("")){
			JOptionPane.showMessageDialog(this, "<html><h4>"+ "请输入登录账号、账号不能为空！" + "<html><h4>", "警告",JOptionPane.WARNING_MESSAGE);
		}else{

			System.err.println(drivername);
			System.err.println(getURL());
		}
	}
	
	
	// 得到界面中的所有项的值
	private void getValues() {
		//此处由于sql 2000 与 2005 方言一样 但是连接字符串不一样  所有要做区别
		if(dbDriver.getSelectedItem().equals("(sql 2000)com.microsoft.sqlserver.jdbc.SQLServerDriver")||dbDriver.getSelectedItem().equals("(sql 2005or2008)com.microsoft.sqlserver.jdbc.SQLServerDriver")){
			drivername = udrivername;
			}else {
				drivername = (String) dbDriver.getSelectedItem();
			}
		urlname = (String) dbURL.getSelectedItem();
		dbipname = ipTF.getText().trim();
		//在端口前面追加：
		StringBuffer sbBuf = new StringBuffer(duankouTF.getText().trim());
		sbBuf.insert(0,":");
		duankouname = sbBuf.toString();
		StringBuffer sbBuffer = new StringBuffer(dbnam.getText().trim());
		//dbnames = dbnam.getText().trim();
		//判断数据库追加字符
		if(dbDriver.getSelectedItem().equals("(sql 2000)com.microsoft.sqlserver.jdbc.SQLServerDriver")||dbDriver.getSelectedItem().equals("(sql 2005or2008)com.microsoft.sqlserver.jdbc.SQLServerDriver")){
			sbBuffer.insert(0,";DatabaseName=");
			dbnames=sbBuffer.toString();
			}else if (dbDriver.getSelectedItem().equals("oracle.jdbc.driver.OracleDriver")){
				sbBuffer.insert(0,":");
				dbnames=sbBuffer.toString();
			}else if (dbDriver.getSelectedItem().equals("com.ibm.db2.jdbc.app.DB2Driver")||dbDriver.getSelectedItem().equals("com.sybase.jdbc.SybDriver")||dbDriver.getSelectedItem().equals("com.informix.jdbc.IfxDriver")){
				sbBuffer.insert(0,"/");
				dbnames=sbBuffer.toString();
			}else if (dbDriver.getSelectedItem().equals("com.mysql.jdbc.Driver")){
				sbBuffer.insert(0,"/");
				dbnames=sbBuffer.toString();
			}else if (dbDriver.getSelectedItem().equals("org.postgresql.Driver")){
				sbBuffer.insert(0,"/");
				dbnames=sbBuffer.toString();
			}
		dbusernames = dbnamTF.getText().trim();
		passwordname = passwordTF.getText().trim();
	}
	
	public String getURL() {
		StringBuffer sb = new StringBuffer();
		sb.append(urlname);
		sb.append(dbipname);
		sb.append(duankouname);
		sb.append(dbnames);
		return sb.toString();	
	}
	// 重新设置各项的值
	private void reset() {
		dbDriver.setSelectedIndex(0);
		dbURL.setSelectedIndex(0);
		ipTF.setText("");
		duankouTF.setText("");
		dbnam.setText("");
		dbnamTF.setText("");
		passwordTF.setText("");
	}
}

