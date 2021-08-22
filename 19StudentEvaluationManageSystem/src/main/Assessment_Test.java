package main;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
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

public class Assessment_Test {

    private JFrame jf = new JFrame();

    Dimension faceSize = new Dimension(800, 600);
    private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

    // 查询
    private JPanel pSelect = new JPanel();
    private JLabel lSelectYear = new JLabel("年份");
    private JComboBox cSelectYear = new JComboBox();
    private JLabel lSelectTerm = new JLabel("学期");
    private JComboBox cSelectTerm = new JComboBox();
    private JLabel lSelectId = new JLabel("学号");
    private JTextField tSelectId = new JTextField(15);
    private JButton bSelect = new JButton("查询");

    // 查询结果放在一个JTable
    private MyJTable table;
    private DefaultTableModel tableModel;
    private JScrollPane tableScrollPane;
    private Object[] tableTitle = {"学号", "姓名", "不及格门数", "总成绩", "学期", "年份"};
    private Object[][] tableData = {new Object[]{""}};

    // 对学生信息进行管理的添加、删除、修改按钮
    private JPanel buttonPanel = new JPanel();
    private JButton insert = new JButton("添加");
    private JButton delete = new JButton("删除");
    private JButton update = new JButton("修改");

    // 点击添加、修改时弹出的对话框
    private JDialog dialog = new JDialog(jf, "综测管理");

    private Box box = Box.createVerticalBox();

    private JPanel pId = new JPanel(new FlowLayout(FlowLayout.LEFT));
    private JPanel pName = new JPanel(new FlowLayout(FlowLayout.LEFT));
    private JPanel pUnpass = new JPanel(new FlowLayout(FlowLayout.LEFT));
    private JPanel pScore = new JPanel(new FlowLayout(FlowLayout.LEFT));

    private JLabel lId = new JLabel("学             号");
    private JLabel lName = new JLabel("姓             名");
    private JLabel lUnpass = new JLabel("不及格门数");
    private JLabel lScore = new JLabel("总    成    绩 ");

    private JTextField tId = new JTextField(15);
    private JTextField tName = new JTextField(15);
    private JTextField tUnpass = new JTextField(15);
    private JTextField tScore = new JTextField(15);

    private JPanel pButton = new JPanel();
    private JButton confirm = new JButton("确认");
    private JButton cancel = new JButton("取消");

    private AssessmentService service = new AssessmentService();

    // 用于标记是添加还是修改
    private String id;
    private String year;
    private String term;

    public void init() {

        pSelect.add(lSelectYear);
        pSelect.add(cSelectYear);
        pSelect.add(lSelectTerm);
        pSelect.add(cSelectTerm);
        pSelect.add(lSelectId);
        pSelect.add(tSelectId);
        pSelect.add(bSelect);

        int currentYear = Integer.valueOf(getTime().substring(0, 4));
        for (int i = 0; i < 4; i++, currentYear--) {
            cSelectYear.addItem(currentYear);
        }

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
                String year = String.valueOf(cSelectYear.getSelectedItem());
                String term = String.valueOf(cSelectTerm.getSelectedIndex());
                String id = tSelectId.getText().trim();

                Assessment assessment = new Assessment();
                assessment.setId(id);
                assessment.setYear(year);
                assessment.setTerm(term);

                List list = service.select(assessment);

                clearTable();

                Iterator it = list.iterator();
                while (it.hasNext()) {
                    assessment = (Assessment) it.next();
                    insertTable(assessment);
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
                term = String.valueOf(cSelectTerm.getSelectedIndex());
                year = String.valueOf(cSelectYear.getSelectedItem());

                tId.setText("");
                tId.setEditable(true);
                tName.setText("");
                tUnpass.setText("");
                tScore.setText("");

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

                        Assessment assessment = new Assessment();

                        for (int i = selected.length - 1; i >= 0; i--) {

                            assessment.setId((String) tableModel.getValueAt(
                                    selected[i], 0));
                            assessment.setTerm((String) tableModel.getValueAt(
                                    selected[i], 4));
                            assessment.setYear((String) tableModel.getValueAt(
                                    selected[i], 5));
                            service.delete(assessment);

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
                    // 如果进行修改，就将id = 要修改的学号
                    id = String.valueOf(table.getValueAt(row, 0));
                    term = String.valueOf(table.getValueAt(row, 4));
                    year = String.valueOf(table.getValueAt(row, 5));
                    // 设置tId的内容
                    tId.setText(id);
                    // 设置tId不可修改
                    tId.setEditable(false);

                    tName.setText(String.valueOf(table.getValueAt(row, 1)));
                    tName.setEditable(false);

                    tUnpass.setText(String.valueOf(table.getValueAt(row, 2)));
                    tScore.setText(String.valueOf(table.getValueAt(row, 3)));

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
        pUnpass.add(lUnpass);
        pUnpass.add(tUnpass);
        pScore.add(lScore);
        pScore.add(tScore);
        pButton.add(confirm);
        pButton.add(cancel);

        // 确定按钮的监听器
        confirm.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Assessment assessment = new Assessment();
                assessment.setId(tId.getText());
                assessment.setName(tId.getText());
                assessment.setUnPass(tUnpass.getText());
                assessment.setScore(tScore.getText());
                assessment.setTerm(term);
                assessment.setYear(year);

                if (id != null) {
                    int flag = service.update(assessment);

                    dialog.dispose();

                    if (flag == 1) {
                        JOptionPane.showMessageDialog(jf, "修改成功！", "提示",
                                JOptionPane.WARNING_MESSAGE);

                        int row = table.getSelectedRow();

                        table.setValueAt(assessment.getUnPass(), row, 2);
                        table.setValueAt(assessment.getScore(), row, 3);

                    } else {
                        JOptionPane.showMessageDialog(jf, "修改失败！", "提示",
                                JOptionPane.WARNING_MESSAGE);
                    }

                } else {
                    service.insert(assessment);

                    insertTable(assessment);

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
        box.add(pUnpass);
        box.add(pScore);
        box.add(pButton);

        dialog.add(box);
        dialog.setBounds((int) (screenSize.width - 280) / 2,
                (int) (screenSize.height - 240) / 2, 280, 240);
        jf.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
    }

    public void insertTable(Assessment assessment) {

        String[] newCell = new String[7];
        newCell[0] = assessment.getId();
        newCell[1] = assessment.getName();
        newCell[2] = assessment.getUnPass();
        newCell[3] = assessment.getScore();
        newCell[4] = assessment.getTerm();
        newCell[5] = assessment.getYear();

        tableModel.addRow(newCell);

    }

    public void clearTable() {
        int rows = tableModel.getRowCount();
        for (int i = rows - 1; i >= 0; i--) {
            tableModel.removeRow(i);
        }
    }

    public String getTime() {
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(new Date().getTime());
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss");
        return dateFormat.format(c.getTime());
    }

    public static void main(String[] args) {
        new Assessment_Test().init();
    }
}

