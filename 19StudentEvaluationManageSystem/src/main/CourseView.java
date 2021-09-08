package main;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

public class CourseView {

    Dimension faceSize = new Dimension(800, 600);
    private ICourseService service = new CourseServiceImp();
    private JFrame jf = new JFrame();
    private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

    // 按学号查询
    private JPanel pSelect = new JPanel();
    private JLabel lSelectGrade = new JLabel("年级");
    private JComboBox cSelectGrade = new JComboBox();
    private JLabel lSelectTerm = new JLabel("学期");
    private JComboBox cSelectTerm = new JComboBox();
    private JLabel lSelectId = new JLabel("课程号");
    private JTextField tSelectId = new JTextField(15);
    private JButton bSelect = new JButton("查询");

    // 查询结果放在一个JTable
    private MyJTable table;
    private DefaultTableModel tableModel;
    private JScrollPane tableScrollPane;
    private Object[] tableTitle = {"课程号", "课程名", "学分", "年级", "学期"};
    private Object[][] tableData = {new Object[]{""}};

    // 对学生信息进行管理的添加、删除、修改按钮
    private JPanel buttonPanel = new JPanel();
    private JButton insert = new JButton("添加");
    private JButton delete = new JButton("删除");
    private JButton update = new JButton("修改");

    // 点击添加、修改时弹出的对话框
    private JDialog dialog = new JDialog(jf, "课程管理");

    private Box box = Box.createVerticalBox();

    private JPanel pId = new JPanel(new FlowLayout(FlowLayout.LEFT));
    private JPanel pName = new JPanel(new FlowLayout(FlowLayout.LEFT));
    private JPanel pMark = new JPanel(new FlowLayout(FlowLayout.LEFT));
    private JPanel pGrade = new JPanel(new FlowLayout(FlowLayout.LEFT));
    private JPanel pTerm = new JPanel(new FlowLayout(FlowLayout.LEFT));

    private JLabel lId = new JLabel("课程号");
    private JLabel lName = new JLabel("课程名");
    private JLabel lMark = new JLabel("学分");
    //private JLabel lTerm = new JLabel("学期");
    //private JLabel lGrade = new JLabel("年级");

    private JTextField tId = new JTextField(15);
    private JTextField tName = new JTextField(15);
    private JTextField tMark = new JTextField(15);
    //学期，年级
    //private JComboBox cTerm;
    //private JComboBox cGrade;

    private JPanel pButton = new JPanel();
    private JButton confirm = new JButton("确认");
    private JButton cancel = new JButton("取消");

    // 用于标记是添加还是修改
    private String id;
    private String grade;
    private String term;

    public static void main(String[] args) {
        new CourseView().init();
    }

    public void init() {

        pSelect.add(lSelectGrade);
        pSelect.add(cSelectGrade);
        pSelect.add(lSelectTerm);
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

        cSelectTerm.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (cSelectTerm.getSelectedItem().equals("无")) {
                    insert.setVisible(false);
                } else {
                    insert.setVisible(true);
                }
            }
        });

        // 查询按钮的监听器
        bSelect.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String grade = String.valueOf(cSelectGrade.getSelectedItem());
                String term = String.valueOf(cSelectTerm.getSelectedIndex());
                String id = tSelectId.getText();

                Course course = new Course();
                if (id.equals("")) {
                    List list = null;
                    try {
                        list = service.select(course);
                    } catch (Exception exception) {
                        exception.printStackTrace();
                    }

                    clearTable();

                    Iterator it = list.iterator();
                    while (it.hasNext()) {
                        insertTable((Course) it.next());
                    }
                } else {

                    course.setId(id);
                    course.setGrade(grade);
                    course.setTerm(term);

                    List list = null;
                    try {
                        list = service.select(course);
                    } catch (Exception exception) {
                        exception.printStackTrace();
                    }

                    Iterator it = list.iterator();
                    if (list != null) {
                        clearTable();
                        while (it.hasNext()) {
                            insertTable((Course) it.next());
                        }

                    } else {
                        JOptionPane.showMessageDialog(jf, "查找失败！", "提示",
                                JOptionPane.WARNING_MESSAGE);
                    }
                }
            }
        });

        // table
        tableModel = new DefaultTableModel(tableData, tableTitle);
        table = new MyJTable(tableModel);
        tableScrollPane = new JScrollPane(table,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        clearTable();

        // button
        buttonPanel.add(insert);
        buttonPanel.add(delete);
        buttonPanel.add(update);

        // 添加按钮的监听器
        insert.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                // 如果是添加,则将id = null;
                id = null;

                tId.setText("");
                tId.setEditable(true);
                tName.setText("");
                tMark.setText("");

                dialog.setVisible(true);
            }
        });

        // 删除按钮的监听器
        delete.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // 获得选择删除的行号数组
                int[] selected = table.getSelectedRows();
                // 如果selected的长度为0，说明没有选择要删除的
                if (selected.length == 0) {
                    JOptionPane.showMessageDialog(jf, "请选择要删除的综测！", "提示",
                            JOptionPane.WARNING_MESSAGE);
                } else {
                    // 提示是否要进行删除
                    int flag = JOptionPane.showConfirmDialog(jf, "确定删除吗？",
                            "提示", JOptionPane.OK_CANCEL_OPTION,
                            JOptionPane.WARNING_MESSAGE);
                    // 如果选择是，则进行删除
                    if (flag == JOptionPane.OK_OPTION) {

                        Course course = new Course();

                        for (int i = selected.length - 1; i >= 0; i--) {

                            course.setId((String) tableModel.getValueAt(selected[i], 0));
                            course.setGrade((String) tableModel.getValueAt(selected[i], 3));
                            course.setTerm((String) tableModel.getValueAt(selected[i], 4));
                            try {
                                service.delete(id);
                            } catch (Exception e1) {
                                e1.printStackTrace();
                            }
                            tableModel.removeRow(selected[i]);
                        }
                    }
                }
            }
        });

        // 修改按钮的监听器
        update.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int row = table.getSelectedRow();

                if (row == -1) {
                    JOptionPane.showMessageDialog(jf, "请选择要修改的列！", "提示",
                            JOptionPane.WARNING_MESSAGE);
                } else {
                    // 如果进行修改，就将id = 要修改的学号,年级和学期一样
                    id = String.valueOf(table.getValueAt(row, 0));
                    grade = String.valueOf(table.getValueAt(row, 3));
                    term = String.valueOf(table.getValueAt(row, 4));
                    //int term = Integer.valueOf((String) table.getValueAt(row, 4));
                    //int grade = Integer.valueOf((String) table.getValueAt(row, 3));
                    // 设置tId的内容
                    tId.setText(id);
                    // 设置tId不可修改
                    tId.setEditable(false);

                    tName.setText(String.valueOf(table.getValueAt(row, 1)));
                    tMark.setText(String.valueOf(table.getValueAt(row, 2)));


                    // 设置dialog可见
                    dialog.setVisible(true);
                }
            }
        });

        jf.setLayout(new BorderLayout());

        // 设置pSelect在jf的北面
        jf.add(pSelect, BorderLayout.NORTH);

        // 设置pSelect在jf的中心
        jf.add(tableScrollPane, BorderLayout.CENTER);

        // 设置pSelect在jf的南面
        jf.add(buttonPanel, BorderLayout.SOUTH);

        jf.pack();

        jf.setSize(faceSize);
        jf.setLocation((int) (screenSize.width - faceSize.getWidth()) / 2,
                (int) (screenSize.height - faceSize.getHeight()) / 2);
        jf.setResizable(false);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setVisible(true);

        pId.add(lId);
        pId.add(tId);
        pName.add(lName);
        pName.add(tName);
        pMark.add(lMark);
        pMark.add(tMark);
        //学期，年级
        String[] ter = {"第一学期", "第二学期"};
        String[] gra = {"大一", "大二", "大三", "大四"};
        //cTerm = new JComboBox(ter);
        //cGrade = new JComboBox(gra);
        //pTerm.add(lTerm);
        //pTerm.add(cTerm);
        //pGrade.add(lGrade);
        //pGrade.add(cGrade);
        pButton.add(confirm);
        pButton.add(cancel);

        // 确定按钮的监听器
        confirm.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Course course = new Course();
                course.setId(tId.getText());
                course.setName(tId.getText());
                course.setMark(tMark.getText());
                course.setGrade((String) cSelectGrade.getSelectedItem());
                course.setTerm((String) cSelectTerm.getSelectedItem());

				/*
				int term = Integer.valueOf(cTerm.getSelectedIndex());
				if(term == 0) {
					course.setTerm("第一学期");
				}else {
					course.setTerm("第二学期");
				}
				int grade = Integer.valueOf(cGrade.getSelectedIndex());
				switch (grade) {
					case 0: course.setGrade("大一"); break;
					case 1: course.setGrade("大二"); break;
					case 2: course.setGrade("大三"); break;
					case 3: course.setGrade("大四"); break;
				}*/
                //course.setTerm(String.valueOf(cTerm.getSelectedIndex()));
                //course.setGrade(String.valueOf(cGrade.getSelectedIndex()));

                if (id != null) {
                    int flag = 0;
                    try {
                        flag = service.update(course);
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }

                    dialog.dispose();

                    if (flag == 1) {
                        JOptionPane.showMessageDialog(jf, "修改成功！", "提示",
                                JOptionPane.WARNING_MESSAGE);

                        int row = table.getSelectedRow();

                        //获取修改后的课程号，课程名，学分。年级和学期默认不许修改
                        table.setValueAt(course.getId(), row, 0);
                        table.setValueAt(course.getName(), row, 1);
                        table.setValueAt(course.getMark(), row, 2);

                    } else {
                        JOptionPane.showMessageDialog(jf, "修改失败！", "提示",
                                JOptionPane.WARNING_MESSAGE);
                    }

                } else {

                    try {
                        service.insert(course);
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }

                    insertTable(course);

                    dialog.dispose();

                    JOptionPane.showMessageDialog(jf, "添加成功！", "提示",
                            JOptionPane.WARNING_MESSAGE);

                }
            }
        });

        // 取消按钮的监听器
        cancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dialog.dispose();
            }
        });

        box.add(pId);
        box.add(pName);
        box.add(pMark);
        box.add(pButton);

        dialog.add(box);
        dialog.setBounds((int) (screenSize.width - 280) / 2,
                (int) (screenSize.height - 240) / 2, 280, 240);
    }

    public void insertTable(Course course) {

        String[] newCell = new String[7];
        newCell[0] = course.getId();
        newCell[1] = course.getName();
        newCell[2] = course.getMark();
        newCell[3] = course.getGrade();
        newCell[4] = course.getTerm();

        tableModel.addRow(newCell);

    }

    public void clearTable() {
        int rows = tableModel.getRowCount();
        for (int i = rows - 1; i >= 0; i--) {
            tableModel.removeRow(i);
        }
    }
}
