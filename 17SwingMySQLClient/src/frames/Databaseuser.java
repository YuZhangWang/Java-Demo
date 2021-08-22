package frames;

import javax.swing.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

import java.awt.event.*;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Databaseuser extends JFrame implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5752385183447486351L;
	private static String USERNAME = "";
	private static String PASSWD = "";
	private JButton start, register,jBtn_editUser,btn_editeOk;
	private JTextField username,jTextField_rootUser,jText_dataTable;
	private JPasswordField password,Jpass_rootPass;
	private JLabel UI, pw,jLabel_rootUser,jLabel_rootPass;
	private int Fwidth = 600, Fheight = 350;
	private JButton okButton, cancelButton,jBtn_rootOK;
	private JList list;
	boolean login = false;
	Connection myCon;
	Statement execStat1;
	
	private DefaultListModel listModel;

	public Databaseuser() {
		//窗口名称为MYSQL Login
		super("MYSQL Login");
		//设置登录窗口为400宽，200高
		this.setBounds(400, 200, Fwidth, Fheight);
		//设置窗口可见
		setVisible(true);
		//设置窗口不可调整大小
		setResizable(false);

		//创建并且设置列表数据模型
		listModel = new DefaultListModel();
		//创建进入数据表按钮
		okButton = new JButton("进入数据表");
		//添加动作侦听器
		okButton.addActionListener(this);
		cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(this);
		UI = new JLabel("用户名:");
		pw = new JLabel("密码:");
		jLabel_rootUser = new JLabel("管理员用户:");
		jLabel_rootPass = new JLabel("管理员密码:");
		//hint = new JLabel("Choose a database!");
		username = new JTextField(10);
		username.setText("root");
		password = new JPasswordField(10);
		password.setText("123456");
		
		jTextField_rootUser = new JTextField(10);
		Jpass_rootPass = new JPasswordField(10);
		
		start = new JButton("登陆");
		start.addActionListener(this);
		register = new JButton("注册");
		register.addActionListener(this);
		register.setVisible(false);
		btn_editeOk = new JButton("确定");
		btn_editeOk.addActionListener(this);
		
		jBtn_rootOK = new JButton("确定");
		jBtn_rootOK.addActionListener(this);
		jBtn_rootOK.setVisible(false);
		
		jBtn_editUser = new JButton("修改用户信息");
		jBtn_editUser.addActionListener(this);
		
		
		
		jText_dataTable = new JTextField("数据表：");
		list = new JList();
		JPanel pNorth = new JPanel();

		pNorth.add(UI);
		pNorth.add(username);
		pNorth.add(pw);
		pNorth.add(password);
		pNorth.add(start);
		pNorth.add(jBtn_editUser);
		pNorth.add(jLabel_rootUser);
		pNorth.add(jTextField_rootUser);
		pNorth.add(jLabel_rootPass);
		pNorth.add(Jpass_rootPass);
		pNorth.add(btn_editeOk);
		pNorth.add(jBtn_rootOK);
		
		jLabel_rootUser.setVisible(false);
		jLabel_rootPass.setVisible(false);
		jTextField_rootUser.setVisible(false);
		Jpass_rootPass.setVisible(false);
		
		jBtn_editUser.setVisible(false);
		btn_editeOk.setVisible(false);
		pNorth.setBackground(Color.GREEN);
		pNorth.add(register);
		JScrollPane sp = new JScrollPane(list);
//		sp.add(jText_dataTable);

		JPanel pSouth = new JPanel();
		pSouth.add(okButton);
		

		Container con = this.getContentPane();
		con.add(pNorth, BorderLayout.NORTH);
		con.add(sp, BorderLayout.CENTER);
		con.add(pSouth, BorderLayout.SOUTH);
		// con.validate();
		// validate();
		// setContentPane(p);
		// setContentPane(sp);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}


	@Override
	public void actionPerformed(ActionEvent arg) {
		// TODO Auto-generated method stub
		if (arg.getSource() == start) {
			ResultSet rs;
			int i = 1;
			int rowCount;
			String user = username.getText();
			String pw = new String(password.getPassword());

			try {
				Class.forName("com.mysql.jdbc.Driver").newInstance();
//				myCon = DriverManager.getConnection(
//						"jdbc:mysql://localhost:3306/", user, pw);
				myCon = DriverManager.getConnection(
						"jdbc:mysql://localhost:3306/?useUnicode=true&characterEncoding=utf8", user, pw);
				
				login = true;
				JOptionPane.showMessageDialog(this, "登陆成功!");
				UI.setText("用户名：" + username.getText());
				this.username.setVisible(false);
//				username.setText("用户名: " + );
				this.pw.setVisible(false);
				this.password.setVisible(false);
				start.setVisible(false);
				register.setVisible(false);
				jBtn_editUser.setVisible(true);
				password.setText("");
				Statement execStat1 = myCon.createStatement();

				rowCount = execStat1.executeUpdate("show databases;");
				rs = execStat1.executeQuery("show databases;");
				// System.out.println(rowCount);
				String[] listData = new String[rowCount+1];
				listData[0] = "数据表：";
				listModel.addElement(listData[0]);
				while (rs.next()) {
					listData[i] = rs.getString("Database");
					
			        listModel.addElement(listData[i]);
			    
					i++;
				}
				list.setModel(listModel);
//				list.setListData(listData);
				
				USERNAME = user;
				PASSWD = pw;
			

			} catch (InstantiationException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IllegalAccessException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				JOptionPane.showMessageDialog(this,
						"账号或密码错误，请重试!");
			}

		}

		if (arg.getSource() == register) {
			Connection myConRes;
			String sql = "CREATE USER '" +username.getText() + "'@'localhost' IDENTIFIED BY '" + password.getPassword().toString()
					+ "';";
			String grant = "grant all privileges on udw.* to '" + username.getText() +  "'@'localhost'";
			try {
				myConRes = DriverManager.getConnection(
						"jdbc:mysql://localhost:3306/", "root", "123456");
				Statement execStatEditUser = myConRes.createStatement();
				execStatEditUser.execute(sql);
				execStatEditUser.execute(grant);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			
			
		}
		else if (arg.getSource() == jBtn_editUser) {
			
			jLabel_rootUser.setVisible(true);
			jLabel_rootPass.setVisible(true);
			jTextField_rootUser.setVisible(true);
			Jpass_rootPass.setVisible(true);
			this.jBtn_rootOK.setVisible(true);
			
			this.username.setVisible(false);
			this.UI.setVisible(false);
//			this.UI.setText("用户名：");
			this.pw.setVisible(false);
			this.password.setVisible(false);
			this.jBtn_editUser.setVisible(false);
			
			
//			this.username.setVisible(true);
//			this.UI.setText("用户名：");
//			this.pw.setVisible(true);
//			this.password.setVisible(true);
//			this.jBtn_editUser.setVisible(false);
			
			
//			password.setText("");
		}
		else if (arg.getSource() == jBtn_rootOK) {
			String rootName = jTextField_rootUser.getText();
			String rootPass = new String(Jpass_rootPass.getPassword());
			Process process;
			try {
				process = Runtime.getRuntime().exec(
						"cmd /c mysql -u" + rootName + " -p" + rootPass);
				OutputStream os = process.getOutputStream();
				OutputStreamWriter writer = new OutputStreamWriter(os);
				writer.write("exit");
				writer.flush();
				writer.close();
				int exitCode = process.waitFor();
				os.close();
				
				
				
				
				if (exitCode == 0) {
					System.out.println("登陆成功！");
					JOptionPane.showMessageDialog(this, "验证成功，请修改用户信息!");
					
					jLabel_rootUser.setVisible(false);
					jLabel_rootPass.setVisible(false);
					jTextField_rootUser.setVisible(false);
					Jpass_rootPass.setVisible(false);
					jBtn_rootOK.setVisible(false);
					
					
					this.username.setVisible(true);
					this.UI.setText("用户名：");
					this.UI.setVisible(true);
					this.pw.setVisible(true);
					this.password.setVisible(true);
					this.jBtn_editUser.setVisible(false);
					
					this.btn_editeOk.setVisible(true);
				}else {
					System.out.println("登陆失败，请检查管理员用户是否输入正确！");
					JOptionPane.showMessageDialog(this, "验证失败，请重新输入!");
				}
				
				
				
			} catch (IOException | InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			
			
//			password.setText("");
		}
		else if (arg.getSource() == btn_editeOk) {
			String editSql = "rename user '" + username.getText().toString() + "'@'127.0.0.1' to '" 
					+ password.getPassword().toString() + "'@'127.0.0.1'";
//			String sql = "CREATE USER 'myuser'@'%' IDENTIFIED BY '123456';";
			String rootName = jTextField_rootUser.getText();
			String rootPass = new String(Jpass_rootPass.getPassword());
			try {
				
//				Process process = Runtime.getRuntime().exec(
//						"cmd /c mysql -uroot" + " -p123456");
				Process process = Runtime.getRuntime().exec(
						"cmd /c mysql -u" + rootName + " -p" + rootPass);
				OutputStream os = process.getOutputStream();
				OutputStreamWriter writer = new OutputStreamWriter(os);
				writer.write("use mysql;");
				if(!USERNAME.equals(username.getText())) {
					writer.write("rename user '" + USERNAME + "'@'localhost' to '" + username.getText()+  "'@'localhost';");//向图形界面输出第二第三条命令。中间 \r\n  作用是用来换行的，
				}
				if(!PASSWD.equals(new String(password.getPassword()))){
					writer.write("update user set authentication_string='' where user='" + username.getText() + "';");
					writer.write("alter user '" + username.getText() +  "'@'localhost' identified by '" +  new String(password.getPassword()) 
							+   "';");
				}
				
				writer.write("flush privileges;");
				writer.flush();
				writer.close();
				
				int exitCode = process.waitFor();
				os.close();
				
				if (exitCode == 0) {
					System.out.println("修改成功！");
					JOptionPane.showMessageDialog(this, "修改成功，请重新登陆！");
					
					((DefaultListModel)list.getModel()).removeAllElements();
				
					jLabel_rootUser.setVisible(false);
					jLabel_rootPass.setVisible(false);
					jTextField_rootUser.setVisible(false);
					Jpass_rootPass.setVisible(false);
					jBtn_rootOK.setVisible(false);
					this.jBtn_editUser.setVisible(false);
					btn_editeOk.setVisible(false);
					
					this.username.setVisible(true);
					this.UI.setText("用户名：");
					this.UI.setVisible(true);
					this.pw.setVisible(true);
					this.password.setVisible(true);
					
					start.setVisible(true);
					
					myCon.close();
				}else {
					JOptionPane.showMessageDialog(this, "修改失败！");
					System.out.println("修改失败，请检查管理员用户是否输入正确！");
				}
//				Process process1 = Runtime.getRuntime().exec(
//						"cmd /c rename user '123'@'localhost' to '" + username.getText().toString() + "'@'localhost';");
				System.out.println("cmd process1: " + process.toString());
//				Statement execStatEditUser = myCon.createStatement();
//				execStatEditUser.execute(editSql);
			} catch (IOException | InterruptedException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		if (arg.getSource() == okButton) {
			if (login == false) {
				JOptionPane.showMessageDialog(this, "请先登陆!");
			} else {
				
				Object selected = list.getSelectedValue();
				int selectIndex = list.getSelectedIndex();
				
				if (selected != null && selectIndex != 0) {
					System.out.println("selected database: "+selected);
					Statement execStat1;
					try {
						execStat1 = myCon.createStatement();
						execStat1.executeUpdate("use " + selected + ";");

						DatabaseOP op = new DatabaseOP(myCon, selected.toString());
//						op.setSize(300,200);
						op.setVisible(true);

					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				} else {
					JOptionPane
							.showMessageDialog(this, "请选择要查看的表!");
				}
			}
		}
		if (arg.getSource() == cancelButton) {
			JOptionPane.showMessageDialog(this, "Welcome next time. Bye!");
			System.exit(0);
		}

	}

}

