package main;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Iterator;
import java.util.List;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class Course_Test {
	private ICourseService service =  new CourseServiceImp();
	private JFrame jf = new JFrame();
	Dimension faceSize = new Dimension(800,600);
	private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	//按照学号查询
	private JPanel pSelect = new JPanel();
	private JLabel lSelectGrade = new JLabel("年级");
	private JComboBox cSelectGrade = new JComboBox();
	private JLabel lSelectterm = new JLabel("学期");
	private JComboBox cSelectTerm = new JComboBox();
	private JLabel lSelectId = new JLabel("课程号");
	private TextField tSelectId = new TextField(15);
	private JButton bSelect = new JButton("查询");
	//查询结果放在一个JTable里面
	private MyJTable table;
	private DefaultTableModel tableModel;
	private JScrollPane tableScrollPane;
	private Object[] tableTitle = {"课程号","课程名","学分","学期","年级"};
	private Object[][] tableData = {new Object[]{"001","大学英语","4","1","18"}};
	//对学生信息做注释
	private JPanel buttonPanel = new JPanel();
	private JButton insert = new JButton("添加");
	private JButton delete = new JButton("删除");
	private JButton update = new JButton("修改");
	//单击修改对话框操作
	private JDialog dialog = new JDialog(jf,"课程管理");
	private Box box = Box.createVerticalBox();
	private JPanel pId = new JPanel(new FlowLayout(FlowLayout.LEFT));
	private JPanel pName = new JPanel(new FlowLayout(FlowLayout.LEFT));
	private JPanel pMark = new JPanel(new FlowLayout(FlowLayout.LEFT));
	private JPanel pGrade = new JPanel(new FlowLayout(FlowLayout.LEFT));
	private JPanel pTerm = new JPanel(new FlowLayout(FlowLayout.LEFT));
	private JLabel lId = new JLabel("课程号");
	private JLabel lName = new JLabel("课程名");
	private JLabel lMark = new JLabel("学 分");
	private JLabel lTerm = new JLabel("学 期");
	private JLabel lGrade = new JLabel("年 级");
	private JTextField tid = new JTextField(15);
	private JTextField tName = new JTextField(15);
	private JTextField tMark = new JTextField(15);
	private JComboBox cTerm = new JComboBox();
	private JComboBox cGrade = new JComboBox();
	private JPanel pButton = new JPanel();
	private JButton confirm = new JButton("确认");
	private JButton cancel = new JButton("取消");

	//用于标记修改
	private String id;
	public void init(){
		pSelect.add(lSelectGrade);
		pSelect.add(cSelectGrade);
		pSelect.add(lSelectterm);
		pSelect.add(cSelectTerm);
		pSelect.add(lSelectId);
		pSelect.add(tSelectId);
		pSelect.add(bSelect);
		cSelectGrade.addItem("无");
		cSelectGrade.addItem("大一");
		cSelectGrade.addItem("大二");
		cSelectGrade.addItem("大三");
		cSelectGrade.addItem("大四");
		cSelectTerm.addItem("无");
		cSelectTerm.addItem("第一学期");
		cSelectTerm.addItem("第二学期");
		//事件监听器
		cSelectTerm.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(cSelectTerm.getSelectedItem().equals("无")){
					insert.setVisible(false);
				}else{
					insert.setVisible(true);
				}
			}
		});
		//查询按钮
		bSelect.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String grade = String.valueOf(cSelectGrade.getSelectedItem());
				String term  = String.valueOf(cSelectTerm.getSelectedIndex());
				String id = tSelectId.getText().trim();
				Course course = new Course();
				course.setId(id);
				course.setTerm(term);
				course.setGrade(grade);
				List<Course> list =null;
				try{
					list = service.select(course);
				}catch (NumberFormatException ex){
					ex.printStackTrace();
				} catch (Exception ex) {
					ex.printStackTrace();
				}
				clearTable();
				Iterator<Course>it=list.iterator();
				while(it.hasNext()){
					course = (Course)it.next();
					insertTable(course);
				}
			}
		});
		//table
		tableModel = new DefaultTableModel(tableData,tableTitle);
		table = new MyJTable(tableModel);
		tableScrollPane = new JScrollPane(table,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		clearTable();
		//button
		buttonPanel.add(insert);
		buttonPanel.add(delete);
		buttonPanel.add(update);
		//添加监听器
		insert.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//如果添加 将id置为空
				id = null;
				tid.setText("");
				tid.setEditable(true);
				tName.setText("");
				tMark.setText("");
				dialog.setVisible(true);
			}
		});
		//解除监听器
		delete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//获得选择删除的行号数组
				int []selected = table.getSelectedRows();
				//如果长度是0，则没有选择删除的
				if(selected.length == 0){
					JOptionPane.showMessageDialog(jf,"请选择要删除的综合测试?","提示",JOptionPane.WARNING_MESSAGE);
				}else{
					int flag = JOptionPane.showConfirmDialog(jf,"确定删除吗?","提示",JOptionPane.OK_CANCEL_OPTION,JOptionPane.WARNING_MESSAGE);
					if(flag == JOptionPane.OK_OPTION){
						String id = null;
						for(int i  = selected.length -1; i >=  0; i--){
							id = String.valueOf(tableModel.getValueAt(selected[i],0));
							try{
								service.delete(id);
							} catch (Exception ex) {
								ex.printStackTrace();
							}
							tableModel.removeRow(selected[i]);
						}
					}
				}
			}
		});
		//修改按钮的监听器
		update.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int row = table.getSelectedRow();
				if(row == -1){
					JOptionPane.showMessageDialog(jf,"请选择要删除的列!","提示",JOptionPane.WARNING_MESSAGE);
				}else{
					//如果进行修改,将id改成要修改的学号
					id = String.valueOf(table.getValueAt(row,0));
					String term = String.valueOf(table.getValueAt(row,3));
					String grade = String.valueOf(table.getValueAt(row,4));
					//设置id的内容
					tid.setText(id);
					//不可修改
					tid.setEditable(false);
					tName.setText(String.valueOf(table.getValueAt(row,1)));
					tMark.setText(String.valueOf(table.getValueAt(row,2)));
					cTerm.setSelectedItem(term);
					cGrade.setSelectedItem(grade);
					//设置可见
					dialog.setVisible(true);
				}
			}
		});

		jf.setLayout(new BorderLayout());
		//设置jf里面的东西
		jf.add(pSelect,BorderLayout.NORTH);
		jf.add(tableScrollPane, BorderLayout.CENTER);
		jf.add(buttonPanel,BorderLayout.SOUTH);
		jf.pack();
		jf.setSize(faceSize);
		jf.setLocation((int)(screenSize.width - faceSize.getWidth())/2,(int)(screenSize.height - faceSize.getHeight())/2);
		jf.setResizable(false);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setVisible(true);
		cTerm.addItem("第一学期");
		cTerm.addItem("第二学期");
		cGrade.addItem("大一");
		cGrade.addItem("大二");
		cGrade.addItem("大三");
		cGrade.addItem("大四");
		pId.add(lId);
		pId.add(tid);
		pName.add(lName);
		pName.add(tName);
		pMark.add(lMark);
		pMark.add(tMark);
		pTerm.add(lTerm);
		pTerm.add(cTerm);
		pGrade.add(lGrade);
		pGrade.add(cGrade);
		pButton.add(confirm);
		pButton.add(cancel);

		//确定按钮的监听器
		confirm.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Course course = new Course();
				course.setId(tid.getText());
				course.setName(tName.getText());
				course.setMark(tMark.getText());
				int term = Integer.valueOf(cTerm.getSelectedIndex());
				if(term == 0){
					course.setTerm("第一学期");
				}else{
					course.setTerm("第二学期");
				}
				int grade = Integer.valueOf(cGrade.getSelectedIndex());
				switch (grade){
					case 0: course.setGrade("大一"); break;
					case 1: course.setGrade("大二"); break;
					case 2: course.setGrade("大三"); break;
					case 3: course.setGrade("大四"); break;
				}
				if(id != null){
					int flag = 0;
					try{
						flag = service.update(course);
					} catch (Exception ex) {
						ex.printStackTrace();
					}
					dialog.dispose();
					if(flag == 1){
						JOptionPane.showMessageDialog(jf,"修改成功!","提示",JOptionPane.WARNING_MESSAGE);
						int row = table.getSelectedRow();
						table.setValueAt(course.getName(),row,1);
						table.setValueAt(course.getMark(),row,2);
						table.setValueAt(course.getTerm(),row,3);
						table.setValueAt(course.getGrade(),row,4);
					}else{
						JOptionPane.showMessageDialog(jf,"修改失败!","提示",JOptionPane.WARNING_MESSAGE);
					}
				}else{
					try{
						service.insert(course);
					} catch (Exception ex) {
						ex.printStackTrace();
					}
					insertTable(course);
					dialog.dispose();
					JOptionPane.showMessageDialog(jf,"添加成功!","提示",JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		//取消按钮的事件监听器
		cancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dialog.dispose();
			}
		});
		box.add(pId);
		box.add(pName);
		box.add(pMark);
		box.add(pTerm);
		box.add(pGrade);
		box.add(pButton);
		dialog.add(box);
		dialog.setBounds((int)(screenSize.width - 280)/2,(int)(screenSize.height - 240)/2,280,240);
	}
	public void insertTable(Course course){
		String [] newCell = new String[5];
		newCell[0] = course.getId();
		newCell[1] = course.getName();
		newCell[2] = course.getMark();
		newCell[3] = course.getGrade();
		newCell[4] = course.getTerm();
		tableModel.addRow(newCell);
	}
	public void clearTable(){
		int rows = tableModel.getRowCount();
		for(int i = rows - 1; i >= 0; i--){
			tableModel.removeRow(i);
		}
	}

	public static void main(String[] args) {
		new Course_Test().init();
	}
}

