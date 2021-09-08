package main;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;


public class StudentManageView {

    Dimension faceSize = new Dimension(800, 600);
    private JFrame jf = new JFrame();
    private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

    //按学号查询
    private JPanel pSelect = new JPanel();
    private JLabel lSelect = new JLabel("学号");
    private JTextField tSelect = new JTextField(15);
    private JButton bSelect = new JButton("查询");

    //查询结果放在一个JTable
    private MyJTable table;
    private DefaultTableModel tableModel;
    private JScrollPane tableScrollPane;
    private Object[] tableTitle = {"学号", "姓名", "性别", "年龄", "手机号", "qq",
            "email"};
    private Object[][] tableData = {new Object[]{""}};

    //对学生信息进行管理的添加、删除、修改按钮
    private JPanel buttonPanel = new JPanel();
    private JButton insert = new JButton("添加");
    private JButton delete = new JButton("删除");
    private JButton update = new JButton("修改");

    //点击添加、修改时弹出的对话框
    private JDialog dialog = new JDialog(jf, "学生管理");

    private Box box = Box.createVerticalBox();

    private JPanel pId = new JPanel(new FlowLayout(FlowLayout.LEFT));
    private JPanel pName = new JPanel(new FlowLayout(FlowLayout.LEFT));
    private JPanel pSex = new JPanel(new FlowLayout(FlowLayout.LEFT));
    private JPanel pAge = new JPanel(new FlowLayout(FlowLayout.LEFT));
    private JPanel pPhone = new JPanel(new FlowLayout(FlowLayout.LEFT));
    private JPanel pQq = new JPanel(new FlowLayout(FlowLayout.LEFT));
    private JPanel pEmail = new JPanel(new FlowLayout(FlowLayout.LEFT));

    private JLabel lId = new JLabel("学       号");
    private JLabel lName = new JLabel("姓       名");
    private JLabel lSex = new JLabel("性       别");
    private JLabel lAge = new JLabel("年       龄");
    private JLabel lPhone = new JLabel("手       机");
    private JLabel lQq = new JLabel("      QQ    ");
    private JLabel lEmail = new JLabel("   Email   ");

    private JTextField tId = new JTextField(15);
    private JTextField tName = new JTextField(15);
    private ButtonGroup bSex = new ButtonGroup();
    private JRadioButton boy = new JRadioButton("男");
    private JRadioButton girl = new JRadioButton("女");
    private JTextField tAge = new JTextField(15);
    private JTextField tPhone = new JTextField(15);
    private JTextField tQq = new JTextField(15);
    private JTextField tEmail = new JTextField(15);

    private JPanel pButton = new JPanel();
    private JButton confirm = new JButton("确认");
    private JButton cancel = new JButton("取消");

    private StudentService service = new StudentService();

    //用于标记是添加还是修改
    private String id;

    public static void main(String[] args) {
        new StudentManageView().init();
    }

    public void init() {

        pSelect.add(lSelect);
        pSelect.add(tSelect);
        pSelect.add(bSelect);

        //查询按钮的监听器
        bSelect.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String userId = tSelect.getText().trim();
                if (userId.equals("")) {
                    Student[] student = service.selectAll();

                    clearTable();
                    for (Student s : student) {
                        insertTable(s);
                    }

                } else {
                    Student s = service.selectById(userId);
                    if (s != null) {
                        clearTable();
                        insertTable(s);
                    } else {
                        selectFailure();
                    }

                }
            }
        });

        //table
        tableModel = new DefaultTableModel(tableData, tableTitle);
        table = new MyJTable(tableModel);
        tableScrollPane = new JScrollPane(table,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        //button
        buttonPanel.add(insert);
        buttonPanel.add(delete);
        buttonPanel.add(update);

        //添加按钮的监听器
        insert.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                //如果是添加,则将id = null;
                id = null;
                tId.setText("");
                tId.setEditable(true);
                tName.setText("");
                bSex.clearSelection();
                tAge.setText("");
                tPhone.setText("");
                tQq.setText("");
                tEmail.setText("");

                dialog.setVisible(true);
            }
        });

        //删除按钮的监听器
        delete.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //获得选择删除的行号数组
                int[] selected = table.getSelectedRows();
                //如果selected的长度为0，说明没有选择要删除的
                if (selected.length == 0) {
                    JOptionPane.showMessageDialog(jf, "请选择要删除的综测！", "提示",
                            JOptionPane.WARNING_MESSAGE);
                } else {
                    //提示是否要进行删除
                    int flag = JOptionPane.showConfirmDialog(jf, "确定删除吗？",
                            "提示", JOptionPane.OK_CANCEL_OPTION,
                            JOptionPane.WARNING_MESSAGE);
                    //如果选择是，则进行删除
                    if (flag == JOptionPane.OK_OPTION) {
                        for (int i = selected.length - 1; i >= 0; i--) {
                            service.delete((String) tableModel.getValueAt(selected[i], 0));
                            tableModel.removeRow(selected[i]);
                        }
                    }
                }
            }
        });

        //修改按钮的监听器
        update.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int row = table.getSelectedRow();
                //如果进行修改，就将id = 要修改的学号
                id = String.valueOf(table.getValueAt(row, 0));
                //设置tId的内容
                tId.setText(id);
                //设置tId不可修改
                tId.setEditable(false);
                tName.setText(String.valueOf(table.getValueAt(row, 1)));
                String sex = (String) table.getValueAt(row, 2);
                //如果性别是"男"，则将单选框中的男选中，否则选中"女"
                if (sex.equals("男")) {
                    bSex.setSelected(boy.getModel(), true);
                } else {
                    bSex.setSelected(girl.getModel(), true);
                }
                tAge.setText(String.valueOf(table.getValueAt(row, 3)));
                tPhone.setText(String.valueOf(table.getValueAt(row, 4)));
                tQq.setText(String.valueOf(table.getValueAt(row, 5)));
                tEmail.setText(String.valueOf(table.getValueAt(row, 6)));

                //设置dialog可见
                dialog.setVisible(true);
            }
        });

        jf.setLayout(new BorderLayout());

        //设置pSelect在jf的北面
        jf.add(pSelect, BorderLayout.NORTH);

        //设置pSelect在jf的中心
        jf.add(tableScrollPane, BorderLayout.CENTER);

        //设置pSelect在jf的南面
        jf.add(buttonPanel, BorderLayout.SOUTH);

        jf.pack();

        jf.setSize(faceSize);
        jf.setLocation((int) (screenSize.width - faceSize.getWidth()) / 2,
                (int) (screenSize.height - faceSize.getHeight()) / 2);
        jf.setResizable(false);
        jf.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        jf.setVisible(true);

        pId.add(lId);
        pId.add(tId);
        pName.add(lName);
        pName.add(tName);
        pSex.add(lSex);
        bSex.add(boy);
        bSex.add(girl);
        pSex.add(boy);
        pSex.add(girl);
        pAge.add(lAge);
        pAge.add(tAge);
        pPhone.add(lPhone);
        pPhone.add(tPhone);
        pQq.add(lQq);
        pQq.add(tQq);
        pEmail.add(lEmail);
        pEmail.add(tEmail);
        pButton.add(confirm);
        pButton.add(cancel);

        //确定按钮的监听器
        confirm.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Student student = new Student();
                student.setUserId(tId.getText());
                student.setPassword(tId.getText());
                student.setName(tName.getText());
                String sex = null;
                if (boy.isSelected()) {
                    sex = "男";
                }
                if (girl.isSelected()) {
                    sex = "女";
                }
                student.setSex(sex);
                student.setAge(tAge.getText());
                student.setPhone(tPhone.getText());
                student.setQq(tQq.getText());
                student.setEmail(tEmail.getText());

                if (id != null) {
                    service.update(student);
                } else {
                    service.insert(student);
                }

                dialog.dispose();
            }
        });

        //取消按钮的监听器
        cancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dialog.dispose();
            }
        });

        box.add(pId);
        box.add(pName);
        box.add(pSex);
        box.add(pAge);
        box.add(pPhone);
        box.add(pQq);
        box.add(pEmail);
        box.add(pButton);

        dialog.add(box);
        dialog.setBounds((int) (screenSize.width - 280) / 2,
                (int) (screenSize.height - 300) / 2, 280, 300);

    }

    public void insertTable(Student student) {
        if (student != null) {
            String[] newCell = new String[7];
            newCell[0] = student.getUserId();
            newCell[1] = student.getName();
            newCell[2] = student.getSex();
            newCell[3] = student.getAge();
            newCell[4] = student.getPhone();
            newCell[5] = student.getQq();
            newCell[6] = student.getEmail();

            tableModel.addRow(newCell);
        }
    }

    public void clearTable() {
        int rows = tableModel.getRowCount();
        for (int i = rows - 1; i >= 0; i--) {
            tableModel.removeRow(i);
        }
    }

    public void selectFailure() {
        JOptionPane.showMessageDialog(jf, "不存在该学号的学生！", "提示",
                JOptionPane.WARNING_MESSAGE);
    }

}