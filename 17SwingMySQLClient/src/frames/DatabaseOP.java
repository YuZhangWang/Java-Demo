package frames;

import javax.swing.*;
import javax.swing.event.CellEditorListener;
import javax.swing.event.ChangeEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import utils.MyFileUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.awt.event.*;
import java.io.FileWriter;

import java.io.Writer;
import java.awt.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.Vector;

public class DatabaseOP extends JFrame implements ActionListener,
        CellEditorListener {
    /**
     *
     */
    private static final long serialVersionUID = 628305557944421951L;
    private JLabel input, sqlText;
    private JTextField content, jField_colName;
    private JButton search, detail, reset, btn_exportOne, btn_exportAll, btn_importSql;
    private JButton deleteTuple, addNew, modify, save;
    private JButton addColumn, deleteColumn;
    private JButton renameTable;
    private Container con;
    private JPanel pNorth;
    private JPanel pSouth;
    private JPanel pEast;
    private JScrollPane sp;
    private JTable table;
    private JComboBox d, dataType;
    private String flag = "";
    private int Fwidth = 2000, Fheight = 1600;
    private Connection connect;
    private Vector<String> vColumn = new Vector<String>();
    private Vector<Vector> vRow = new Vector<Vector>();
    private MyTableModel atm;
    private CellEditor ce;
    private int currentSelectedRow = 0;
    private PreparedStatement prestate;
    private boolean alreadyAddnew = false, alreadyClickModify = false;
    private int currentRowbeModified = -1;
    private String currentDatabase = "";
    private int oldID = -1, newID = -1;
    private ArrayList<String> arrayList_allSql = new ArrayList<>();
    private String strSQL = " ";

    public DatabaseOP(Connection cont, String selectedDB) {
        super("数据库： " + selectedDB + ".db");
        connect = cont;
        ResultSet rs;
        Statement execStat1;
        currentDatabase = selectedDB;

        JFrame parentFrame = new JFrame();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Insets screenInsets = Toolkit.getDefaultToolkit().getScreenInsets(parentFrame.getGraphicsConfiguration());
        final Rectangle frameBounds = new Rectangle(
                screenInsets.left, screenInsets.top,
                screenSize.width,
                screenSize.height);

        //sets the bounds of the main frame
//		parentFrame.setBounds(frameBounds);

        this.setBounds(frameBounds);
//		this.setSize(1500,800);
        input = new JLabel("请输入：");
        input.setForeground(Color.red);
        content = new JTextField(10);
        search = new JButton("搜索");
        search.addActionListener(this);

        input.setVisible(false);
        content.setVisible(false);
        search.setVisible(false);

        detail = new JButton("Show Detail");
        detail.addActionListener(this);

        deleteTuple = new JButton("删除一行数据");
        deleteTuple.addActionListener(this);
        addNew = new JButton("添加一行数据");
        addNew.addActionListener(this);

        addColumn = new JButton("增加列");
        addColumn.addActionListener(this);
        jField_colName = new JTextField("输入列名...");
        jField_colName.setSize(200, 80);
        dataType = new JComboBox();
        dataType.setBackground(Color.WHITE);
        dataType.setForeground(Color.BLACK);
        dataType.addItem("选择数据类型");
        dataType.addItem("int");
        dataType.addItem("float");
        dataType.addItem("double");
        dataType.addItem("varchar(255)");

        deleteColumn = new JButton("删除列");
        deleteColumn.addActionListener(this);

        save = new JButton("提交保存");
        save.addActionListener(this);
        modify = new JButton("Edit");
        modify.setVisible(false);
        modify.addActionListener(this);
        renameTable = new JButton("修改表结构");
        renameTable.addActionListener(this);
        reset = new JButton("Restore Defaults");
        reset.addActionListener(this);

        btn_exportOne = new JButton("导出当前操作sql");
        btn_exportOne.addActionListener(this);
        btn_exportAll = new JButton("导出所有sql");
        btn_exportAll.addActionListener(this);

        btn_importSql = new JButton("导入SQL并执行");
        btn_importSql.addActionListener(this);

        FlowLayout ddd = new FlowLayout(FlowLayout.LEFT);
        d = new JComboBox();
        d.setBackground(Color.WHITE);
        d.setForeground(Color.BLACK);
        d.addItem("选择数据表");
        try {
            execStat1 = connect.createStatement();
            rs = execStat1.executeQuery("show tables");
            while (rs.next()) {
                d.addItem(rs.getString("Tables_in_" + selectedDB));
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        d.addActionListener(this);
        pNorth = new JPanel();
        pNorth.setLayout(ddd);
        pNorth.add(input);
        pNorth.add(content);
        pNorth.add(d);
        pNorth.add(search);

        pSouth = new JPanel();
        sqlText = new JLabel(" ");
        pSouth.add(sqlText);
//		pSouth.add(renameTable);
//		pSouth.add(reset);

        GridLayout glo = new GridLayout(12, 1);
        pEast = new JPanel();
        pEast.setLayout(glo);
        pEast.add(deleteTuple);
        pEast.add(addNew);
        pEast.add(dataType);
        pEast.add(jField_colName);
        pEast.add(addColumn);
        pEast.add(renameTable);

        pEast.add(deleteColumn);
        pEast.add(save);

        pEast.add(btn_exportOne);
        pEast.add(btn_exportAll);
        pEast.add(btn_importSql);

        atm = new MyTableModel();
        table = new JTable(atm);
        table.setAutoscrolls(true);

        sp = new JScrollPane(table);
        sp.getViewport().setView(table);
        con = this.getContentPane();
        con.add(pNorth, BorderLayout.NORTH);
        con.add(pSouth, BorderLayout.SOUTH);
        con.add(pEast, BorderLayout.EAST);
        con.add(sp, BorderLayout.CENTER);
        con.validate();
    }

    private static void writeInFile(File file, ArrayList<String> content) {
        Writer writer = null;
        StringBuilder outputString = new StringBuilder();
        try {
            for (int i = 0; i < content.size(); i++) {
                outputString.append(content.get(i) + "\r\n");
            }

            writer = new FileWriter(file, true); // true表示追加
            writer.write(outputString.toString());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                writer.close();

            } catch (IOException e2) {
                e2.printStackTrace();
            }
        }
    }

    /**
     * 读取数据，存入集合中
     */
    public static ArrayList<String> readtFile(File file) throws IOException, ParseException {
        InputStreamReader read = null;// 考虑到编码格式
        ArrayList<String> arrayList_InSql = new ArrayList<>();
        try {
            read = new InputStreamReader(new FileInputStream(file), "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        BufferedReader bufferedReader = new BufferedReader(read);
        String lineTxt = null;
        while ((lineTxt = bufferedReader.readLine()) != null) {
            arrayList_InSql.add(lineTxt);
            System.out.println(lineTxt);
        }
        read.close();

        return arrayList_InSql;
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == reset) {
            System.out.println("Reset button is clicked!");
            Statement execStat = null, execStat1 = null;
            ResultSet rs;
            try {
                boolean databaseExist = false;
                String database = "movies_info";
                execStat1 = connect.createStatement();
                rs = execStat1.executeQuery("show databases;");
                //System.out.println(rowCount);
                while (rs.next()) {
                    if (rs.getString("Database").equalsIgnoreCase(database)) {
                        databaseExist = true;
                        break;
                    }
                }
                execStat = connect.createStatement();
                String path = "";

                String query = "drop database " + database + ";";
                if (databaseExist) {
                    execStat.executeUpdate(query);
                }
                String query0 = "create database " + database + ";",
                        query1 = "use " + database + ";",
                        query2 = "create table movies(ID int(10) not null AUTO_INCREMENT primary key, Title char(100) not null, year int not null);",
                        query3 = "create table movies_length(movie_ID int(10) not null, Film_length char(10) not null);",
                        query4 = "create table People(ID int(10) not null AUTO_INCREMENT primary key ,name char(50) not null);",
                        query5 = "create table People_in_movies(people_id int(10) not null, movie_id int(10) not null,people_role char(10) not null );",
                        query6 = "create table Genres(ID int(10) not null AUTO_INCREMENT primary key, Film_Type char(50) not null);",
                        query7 = "create table Type_of_movies(movie_id int(10) not null, Type_ID int(10) not null);",
                        query8 = "LOAD DATA LOCAL INFILE " + "'" + path + "Types.txt' INTO TABLE genres LINES TERMINATED BY '\r\n';",
                        query9 = "LOAD DATA LOCAL INFILE " + "'" + path + "movies_info.txt' INTO TABLE movies LINES TERMINATED BY '\r\n';",
                        query10 = "LOAD DATA LOCAL INFILE " + "'" + path + "movies_length.txt' INTO TABLE movies_length LINES TERMINATED BY '\r\n';",
                        query11 = "LOAD DATA LOCAL INFILE " + "'" + path + "people.txt' INTO TABLE people LINES TERMINATED BY '\r\n';",
                        query12 = "LOAD DATA LOCAL INFILE " + "'" + path + "People_in_movie.txt' INTO TABLE People_in_movies LINES TERMINATED BY '\r\n';",
                        query13 = "LOAD DATA LOCAL INFILE " + "'" + path + "TypesOfMovie.txt' INTO TABLE Type_of_movies LINES TERMINATED BY '\r\n';";
                execStat.executeUpdate(query0);
                execStat.executeQuery(query1);
                execStat.executeUpdate(query2);
                execStat.executeUpdate(query3);
                execStat.executeUpdate(query4);
                execStat.executeUpdate(query5);
                execStat.executeUpdate(query6);
                execStat.executeUpdate(query7);
                execStat.executeQuery(query8);
                execStat.executeQuery(query9);
                execStat.executeQuery(query10);
                execStat.executeQuery(query11);
                execStat.executeQuery(query12);
                execStat.executeQuery(query13);
                System.out.println("Movie database is reset!");
                JOptionPane.showMessageDialog(this, "Restore completed!");
                d.setSelectedIndex(0);
                query1 = "use " + currentDatabase + ";";
                execStat.executeQuery(query1);
            } catch (SQLException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        }
        if (e.getSource() == renameTable) {
            System.out.println("editTable Table button is clicked!");
            ResultSet attributesRS;
            Statement execStat1;
            String query = "";
            if (d.getSelectedIndex() != 0) {
                String selectedtable = (String) d.getSelectedItem();
                String[] fieldName = null;
                String[] types = null;
                String[] newnames = null;
                try {
                    execStat1 = connect.createStatement();
                    query = "describe " + selectedtable + ";";
                    attributesRS = execStat1.executeQuery(query);
                    fieldName = getResultSetByAttri(attributesRS, "Field");
                    types = getResultSetByAttri(attributesRS, "Type");

                    RenameTable rt = new RenameTable(connect, selectedtable, fieldName, types);
                    rt.setVisible(true);

                } catch (SQLException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }

            } else {
                JOptionPane.showMessageDialog(this, "Please select a Table!");
            }
        }
        if (e.getSource() == modify) {
            System.out.println("edit button is clicked!");
            if (d.getSelectedIndex() != 0) {
                if (table.getSelectedRow() != -1) {
                    oldID = Integer.parseInt(atm.getValueAt(table.getSelectedRow(), 0).toString());

                    alreadyClickModify = true;
                    String selectedTab = (String) d.getSelectedItem();
                    int i = table.getSelectedRow();
                    int j = table.getSelectedColumn();
                    currentRowbeModified = table.getSelectedRow();
                    prestate = createModifyPreStat(selectedTab, 1, 1, " ");
                    table.updateUI();

                    table.editCellAt(i, j);
                    System.out.println("row " + i + " to be edited");
                    System.out.println("Column " + j + " to be edited");
                    ce = table.getCellEditor();
                    ce.addCellEditorListener(this);
                } else {
                    JOptionPane.showMessageDialog(this,
                            "Please select a tuple to modify!");
                }

            } else {
                JOptionPane.showMessageDialog(this, "Please select a Table!");
            }
        }
        if (e.getSource() == save) {
            System.out.println("save button is clicked!");
            Statement execStat1;
            Statement execStatadd;
            String queryAllData = "";
            int k = 0;
            ResultSet Rs_allData;

            if (d.getSelectedIndex() != 0) {
                String selectedTab = (String) d.getSelectedItem();

                queryAllData = "select * from " + selectedTab + ";";

                try {
                    execStat1 = connect.createStatement();
                    Rs_allData = execStat1.executeQuery(queryAllData);

//					oldID=Integer.parseInt(atm.getValueAt(table.getSelectedRow(), 0).toString());

                    alreadyClickModify = true;

                    int i = table.getSelectedRow();
                    int j = table.getSelectedColumn();
                    currentRowbeModified = table.getSelectedRow();
                    while (Rs_allData.next()) {
                        prestate = createModifyPreStat(selectedTab, k, 1, Rs_allData.getString(1));
                        prestate.executeUpdate();
                        k++;
                    }

                    table.updateUI();

                    table.editCellAt(i, j);
                    System.out.println("row " + i + " to be edited");
//					System.out.println("Column " + j+" to be edited" );
//					ce = table.getCellEditor();
//					ce.addCellEditorListener(this);
                } catch (SQLException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }


            } else {
                JOptionPane.showMessageDialog(this, "Please select a Table!");
            }


            if (alreadyAddnew) {

                String currentTable = (String) d.getSelectedItem();
                ResultSet attributesRS;

                String query = "";
                String[] types = null;
                String tempQ1 = "";
                String hint = "";
                int cRow = table.getSelectedRow();
                try {

                    execStatadd = connect.createStatement();
                    query = "describe " + currentTable + ";";
                    attributesRS = execStatadd.executeQuery(query);
                    types = getResultSetByAttri(attributesRS, "Type");

                    if (table.getRowCount() > k) {
                        System.out.println("k = " + k);
                        for (int j = k; j < table.getRowCount(); j++) {
                            prestate = this.createAddPreStat(currentTable);
                            for (int c = 0; c < types.length; c++) {
                                System.out.println("Add data new: " + types[c] + "selcet row: " + j);
                                if (types[c].startsWith("int")) {
                                    System.out.println("Add data new: int: " + c);
                                    prestate
                                            .setInt(c + 1, Integer
                                                    .parseInt((String) atm.getValueAt(
                                                            j, c)));
                                }
                                if (types[c].startsWith("varchar")) {
                                    System.out.println("Add data new: char: " + c);
                                    prestate.setString(c + 1, (String) atm.getValueAt(
                                            j, c));
//									prestate.setString(c + 1, "测试");

                                }
                            }

                            prestate.executeUpdate();
//							break;
                        }
                    }


                    for (int g = 0; g < types.length; g++) {
                        tempQ1 = tempQ1 + (String) atm.getValueAt(cRow, g);

                        if (g != (types.length - 1)) {
                            tempQ1 = tempQ1 + ",";

                        }
                    }
                    if (alreadyAddnew == true) {
                        hint = "Value(" + tempQ1 + ") has been added to the TABLE "
                                + currentTable + "!";
                        alreadyAddnew = false;
                        currentRowbeModified = -1;
                    } else if (alreadyClickModify == true) {
                        hint = "Value(" + tempQ1 + ") in TABLE " + currentTable + " has been modified!";
                        alreadyClickModify = false;
                        currentRowbeModified = -1;
                    }
                    System.out.println(hint);
                    JOptionPane.showMessageDialog(this, hint);
                    System.out.println("oldID:" + oldID);
                    System.out.println("newID:" + newID);
                    if (currentTable.equalsIgnoreCase("movies")) {
                        execStat1 = connect.createStatement();
                        query = "update movies_length set movie_id=" + newID + " where movie_id=" + oldID + ";";
                        execStat1.executeUpdate(query);
                        query = "update people_in_movies set movie_id=" + newID + " where movie_id=" + oldID + ";";
                        execStat1.executeUpdate(query);
                        query = "update type_of_movies set movie_id=" + newID + " where movie_id=" + oldID + ";";
                        execStat1.executeUpdate(query);
                    }

                    if (currentTable.equalsIgnoreCase("genres")) {
                        execStat1 = connect.createStatement();
                        query = "update type_of_movies set type_id=" + newID + " where type_id=" + oldID + ";";
                        execStat1.executeUpdate(query);
                    }

                    if (currentTable.equalsIgnoreCase("people")) {
                        execStat1 = connect.createStatement();
                        query = "update people_in_movies set people_id=" + newID + " where people_id=" + oldID + ";";
                        execStat1.executeUpdate(query);
                    }
                    oldID = -1;
                    newID = -1;

                } catch (com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException e0) {
                    JOptionPane.showMessageDialog(this,
                            "Duplicate entry for key!");
                    e0.printStackTrace();
                } catch (SQLException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                } catch (NumberFormatException e2) {
                    e2.printStackTrace();
                    JOptionPane.showMessageDialog(this,
                            "Please pay attention to Number Format!");
                }
            }
        }
        if (e.getSource() == deleteTuple) {
            System.out.println("delete button is clicked!");
            int rowselectedIndex = table.getSelectedRow();

            if (rowselectedIndex != -1) {

                try {
                    String selectedTable = (String) d.getSelectedItem();
                    prestate = createDeletePreStat(selectedTable, rowselectedIndex);
                    prestate.executeUpdate();

                    atm.removeRow(rowselectedIndex);
                    table.updateUI();
                    JOptionPane.showMessageDialog(this, "Delete successfully!");
                } catch (SQLException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }

            } else {
                JOptionPane.showMessageDialog(this,
                        "Which one do you want to delete?");
            }
        }
        if (e.getSource() == addNew) {
            System.out.println("Addnew button is clicked!");
            if (d.getSelectedIndex() != 0) {
                alreadyAddnew = true;
                String selectedTab = (String) d.getSelectedItem();

                prestate = this.createAddPreStat(selectedTab);

                int ColNum = table.getColumnCount();
                Vector vTmp = new Vector();
                for (int h = 0; h < ColNum; h++) {
                    vTmp.add("");
                }
                vRow.add(vTmp);
                table.updateUI();
//				table.changeSelection(table.getRowCount() - 1, 0, false, false);
                currentRowbeModified = table.getSelectedRow();
                int i = table.getSelectedRow();
                int j = table.getSelectedColumn();
//				table.editCellAt(i, j);
                System.out.println("row to be edited:" + i);
                System.out.println("Column to be edited:" + j);
                ce = table.getCellEditor();
//				ce.addCellEditorListener(this);

            } else {
                JOptionPane.showMessageDialog(this, "Please select a Table!");
            }

        } else if (e.getSource() == addColumn) {
            System.out.println("AddColumn button is clicked!");
            if (d.getSelectedIndex() != 0) {
                alreadyAddnew = true;
                String selectedTab = (String) d.getSelectedItem();

                prestate = this.createAddColumnStat(selectedTab);

                DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
                tableModel.addColumn(jField_colName.getText().toString());

                table.updateUI();
//				table.changeSelection(table.getRowCount() - 1, 0, false, false);
                currentRowbeModified = table.getSelectedRow();
                int i = table.getSelectedRow();
                int j = table.getSelectedColumn();
//				table.editCellAt(i, j);
                System.out.println("row to be edited:" + i);
                System.out.println("Column to be edited:" + j);
                ce = table.getCellEditor();
//				ce.addCellEditorListener(this);
                try {
                    prestate.execute();
                } catch (SQLException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }

            } else {
                JOptionPane.showMessageDialog(this, "Please select a Table!");
            }

        } else if (e.getSource() == deleteColumn) {
            System.out.println("deleteColumn button is clicked!");
            Statement execStat1;
            String selectColumnName;
            String selectedtable = d.getSelectedItem().toString();
            ResultSet attributesRS;
            String[] fieldName;

            int selectColumnIndex = table.getColumnCount();

            if (table.getSelectedColumn() < 0) {
                selectColumnIndex = table.getColumnCount() - 1;

            } else {
                selectColumnIndex = table.getSelectedColumn();

                System.out.println("deleteColumn selectColumnIndex: " + selectColumnIndex + "name: " + table.getColumnName(selectColumnIndex));
            }

            selectColumnName = table.getColumnName(selectColumnIndex);

            if (d.getSelectedIndex() == 0) {
                JOptionPane.showMessageDialog(this,
                        "请选择一张表！");
            } else {
                if (table.getColumnCount() > 0) {
                    TableColumnModel columnModel = table.getColumnModel();
                    columnModel.removeColumn(columnModel.getColumn(selectColumnIndex));
                    table.updateUI();
                }
                prestate = this.createDeleteColumnStat(selectedtable, selectColumnName);

                try {
                    prestate.execute();
                } catch (SQLException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            }
        } else if (e.getSource() == btn_exportOne) {
            ArrayList<String> tempSql = new ArrayList<>();
            tempSql.add(strSQL);
            File file = new File("C:\\Users\\Administrator\\Desktop\\sqlout.txt");
            if (!file.exists()) {
                try {
                    file.createNewFile(); //如果文件不存在则创建文件
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
            writeInFile(file, tempSql);   //写入文件
            JOptionPane.showMessageDialog(this, "导出完成!");
        } else if (e.getSource() == btn_exportAll) {
            File file = new File("C:\\Users\\Administrator\\Desktop\\sqlout.txt");
            if (!file.exists()) {
                try {
                    file.createNewFile(); //如果文件不存在则创建文件
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
            writeInFile(file, arrayList_allSql);   //写入文件
            JOptionPane.showMessageDialog(this, "导出完成!");
        } else if (e.getSource() == btn_importSql) {
//			 File file = new File("C:\\Users\\Administrator\\Desktop\\sqlin.txt");
            MyFileUtils fileUtils = new MyFileUtils();

            File file = fileUtils.openFile(this);

            Statement excuteInSQL = null;
            String inSQL = "";
            ArrayList<String> arrayList_inSQL = new ArrayList<>();
            if (!file.exists()) {
                System.out.println("文件不存在!");
                JOptionPane.showMessageDialog(this, "文件不存在!");
                return;
            }
            try {
                excuteInSQL = connect.createStatement();
                arrayList_inSQL = readtFile(file);
                for (int i = 0; i < arrayList_inSQL.size(); i++) {
                    inSQL += arrayList_inSQL.get(i) + "\n";
                    excuteInSQL.execute(arrayList_inSQL.get(i));
                    table.updateUI();
                }
                JOptionPane.showMessageDialog(this, "导入执行完毕！" + inSQL);
            } catch (IOException e1) {
                e1.printStackTrace();
            } catch (ParseException e2) {
                e2.printStackTrace();
            } catch (SQLException e1) {
                // TODO Auto-generated catch block
                JOptionPane.showMessageDialog(this, "SQL语句错误!");
                e1.printStackTrace();
            }


        } else if (e.getSource() == detail) {
            System.out.println("show detail button is clicked!");
            Statement execStat1;
            String query;
            String selectedtable = d.getSelectedItem().toString();
            ResultSet attributesRS;
            String[] fieldName;
            if (table.getSelectedRow() == -1) {
                JOptionPane.showMessageDialog(this,
                        "Please select a tuple first!");
            } else {
                System.out.println("the row selected: " + table.getSelectedRow());

                try {
                    execStat1 = connect.createStatement();
                    query = "describe " + selectedtable + ";";
                    attributesRS = execStat1.executeQuery(query);
                    fieldName = getResultSetByAttri(attributesRS, "Field");
                    int SelectedRow = table.getSelectedRow();

                    int selectedID = Integer.parseInt(((Vector) atm.getDataVector().elementAt(SelectedRow)).elementAt(0).toString());
                    Vector selectedColumn = (Vector) atm.getDataVector().elementAt(SelectedRow);
                    DetailShow nt = new DetailShow(selectedID, d
                            .getSelectedItem().toString(), connect, selectedColumn, SelectedRow);
                    nt.setVisible(true);
                } catch (SQLException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }

            }
        }
        if ((e.getSource() == d) || (e.getSource() == search)) {
            if (d.getSelectedIndex() != 0) {
                boolean needRefresh = false;
                String selcted = "";
                if (!flag.equals(d.getSelectedItem())) {
                    selcted = (String) d.getSelectedItem();
                    needRefresh = true;
                    flag = selcted;
                }
                selcted = flag;
                searchItem(selcted, content.getText(), needRefresh, e
                        .getSource());
            } else if (e.getSource() == search) {
                System.out.println(d.getSelectedItem());
                JOptionPane.showMessageDialog(this, "Please select a Table!");
            } else if (d.getSelectedIndex() == 0) {
                flag = (String) d.getSelectedItem();
                table.clearSelection();
                table = new JTable();
                con.remove(sp);
                sp = new JScrollPane(table);
                con.add(sp, BorderLayout.CENTER);
                validate();
            }

        }
    }

    private PreparedStatement createModifyPreStat(String currentTable, int index, int id, String oldEditWhere) {

        PreparedStatement addStat = null;

        String query = "";
        String prequery1 = "";
        String prequery2 = "";
        String prequery = "";
        String tempQ1 = "";
        String tempQ2 = "";
        String[] types = null;
        ResultSet attributesRS;

        Statement execStat1;
        try {
            execStat1 = connect.createStatement();
            query = "describe " + currentTable + ";";

            attributesRS = execStat1.executeQuery(query);
            String[] field = getResultSetByAttri(attributesRS, "Field");
            types = getResultSetByAttri(attributesRS, "Type");
            prequery1 = "UPDATE " + currentTable + " SET ";
            if (types[0].startsWith("int")) {
                if (oldEditWhere == null) {
                    oldEditWhere = "0";
                }
                tempQ1 = tempQ1 + field[0] + "=" + Integer.valueOf(oldEditWhere);
            }
            if (types[0].startsWith("varchar")) {
                if (oldEditWhere == null) {
                    oldEditWhere = " ";
                }
                tempQ1 = tempQ1 + field[0] + "= '" + oldEditWhere + "'";
            }

            for (int g = 0; g < field.length; g++) {
                int intData = 0;
                if (types[g].startsWith("int")) {
                    if (atm.getValueAt(index, g) != null) {
                        intData = Integer.valueOf(atm.getValueAt(index, g).toString());
                    }
                    tempQ2 = tempQ2 + field[g] + "= " + intData;
                }
                if (types[g].startsWith("varchar")) {
                    String strData = " ";
                    if (oldEditWhere != null) {
                        strData = atm.getValueAt(index, g).toString();
                    }
                    tempQ2 = tempQ2 + field[g] + "= '" + strData + "'";
                }

                if (g != (field.length - 1)) {
//						tempQ1 = tempQ1 + ",";
                    tempQ2 = tempQ2 + " , ";
                }
            }
            prequery2 = " WHERE ";
            prequery = prequery1 + tempQ2 + prequery2 + tempQ1 + ";";
            System.out.println(prequery);
            strSQL = prequery;
            arrayList_allSql.add(prequery);
            addStat = connect.prepareStatement(prequery);

        } catch (SQLException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        return addStat;

    }

    private PreparedStatement createAddPreStat(String currentTable) {
        PreparedStatement addStat = null;
        String query = "";
        String prequery1 = "";
        String prequery2 = "";
        String prequery = "";
        String tempQ1 = "";
        String tempQ2 = "";
        ResultSet attributesRS;
        Statement execStat1;
        try {
            execStat1 = connect.createStatement();
            query = "describe " + currentTable + ";";
            attributesRS = execStat1.executeQuery(query);
            String[] field = getResultSetByAttri(attributesRS, "Field");
            prequery1 = "INSERT INTO " + currentTable + " (";
            for (int g = 0; g < field.length; g++) {
                tempQ1 = tempQ1 + field[g];
                tempQ2 = tempQ2 + "?";
                if (g != (field.length - 1)) {
                    tempQ1 = tempQ1 + ",";
                    tempQ2 = tempQ2 + ",";
                }
            }
            prequery2 = ") VALUES(";
            prequery = prequery1 + tempQ1 + prequery2 + tempQ2 + ")";
            System.out.println(prequery);
            sqlText.setText("sql语句：" + prequery);
            strSQL = prequery;
            arrayList_allSql.add(prequery);
            addStat = connect.prepareStatement(prequery);
        } catch (SQLException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        return addStat;
    }

    private PreparedStatement createAddColumnStat(String currentTable) {
        PreparedStatement addStat = null;

        String query = "";
        String prequery1 = "";
        String prequery2 = "";
        String prequery = "";
        String tempQ1 = "";
        String tempQ2 = "";
        ResultSet attributesRS;
        Statement execStat1;
        try {

            prequery = "ALTER TABLE " + currentTable + " ADD " + jField_colName.getText().toString() + " " + (String) dataType.getSelectedItem()
                    + ";";
            System.out.println(prequery);

            addStat = connect.prepareStatement(prequery);
            sqlText.setText("sql语句：" + prequery);
            strSQL = prequery;
            arrayList_allSql.add(prequery);
        } catch (SQLException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        return addStat;
    }

    private PreparedStatement createDeleteColumnStat(String currentTable, String selectColumn) {
        PreparedStatement addStat = null;

        String prequery2 = "";
        String prequery = "";

        try {

            prequery = "alter table " + currentTable + " drop column " + selectColumn
                    + ";";
            System.out.println(prequery);
            addStat = connect.prepareStatement(prequery);
            sqlText.setText("sql语句：" + prequery);
            strSQL = prequery;
            arrayList_allSql.add(prequery);
        } catch (SQLException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        return addStat;
    }

    private PreparedStatement createDeletePreStat(String currentTable, int rowTobedel) {

        PreparedStatement addStat = null;
        String query = "";
        String prequery1 = "";
        String prequery = "";
        int currentMID = 0, currentPID = 0;
        String querys = "";
        String tempQ2 = "";
        ResultSet attributesRS;
        Statement execStat1;
        try {
            execStat1 = connect.createStatement();
            query = "describe " + currentTable + ";";
            attributesRS = execStat1.executeQuery(query);
            String[] field = getResultSetByAttri(attributesRS, "Field");
            prequery1 = "DELETE FROM " + currentTable + " where ";
            for (int g = 0; g < field.length; g++) {

                tempQ2 = tempQ2 + field[g] + "= '" + atm.getValueAt(rowTobedel, g) + "'";
                if (g != (field.length - 1)) {
                    tempQ2 = tempQ2 + " and ";
                }
            }
            prequery = prequery1 + tempQ2 + ";";
            System.out.println(prequery);
            addStat = connect.prepareStatement(prequery);
            sqlText.setText("sql语句：" + prequery);
            strSQL = prequery;
            arrayList_allSql.add(prequery);
            if (currentTable.equalsIgnoreCase("movies")) {
                currentMID = Integer.parseInt(atm.getValueAt(rowTobedel, 0).toString());
                querys = "delete from people_in_movies where people_in_movies.movie_id=" + currentMID + ";";
                execStat1 = connect.createStatement();
                execStat1.executeUpdate(querys);
                querys = "delete from movies_length where movies_length.movie_id=" + currentMID + ";";
                execStat1.executeUpdate(querys);
                querys = "delete from type_of_movies where type_of_movies.movie_id=" + currentMID + ";";
                execStat1.executeUpdate(querys);
            }
            if (currentTable.equalsIgnoreCase("people")) {
                currentPID = Integer.parseInt(atm.getValueAt(rowTobedel, 0).toString());
                querys = "delete from people_in_movies where people_in_movies.people_id=" + currentPID + ";";
                execStat1 = connect.createStatement();
                execStat1.executeUpdate(querys);

            }


        } catch (SQLException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        return addStat;
    }

    private void searchItem(String selectedtable, String input,
                            boolean needrefresh, Object o) {
        ResultSet rs, attributesRS;
        int i = 0;
        if (needrefresh == true) {
            int count = 0;
            String query = "";
            Statement execStat1;
            try {
                execStat1 = connect.createStatement();
                query = "describe " + selectedtable + ";";
                attributesRS = execStat1.executeQuery(query);
                String[] field = getResultSetByAttri(attributesRS, "Field");
                execStat1 = connect.createStatement();
                query = "select * from " + selectedtable + ";";
                rs = execStat1.executeQuery(query);
                count = this.getRowNumOfResultSet(rs);
                System.out.println("rowCount: " + count);
                //listData = new String[count + 1];
                //column = new Object[field.length];
                String temp = "";
                vColumn = new Vector<String>();
                for (int n = 0; n < field.length; n++) {
                    //column[n] = field[n];
                    temp = field[n] + "||" + temp;
                    vColumn.add(field[n]);
                }
                //listData[i++] = temp;
                int j = 0;
                //row = new Object[count][field.length];
                vRow = new Vector<Vector>();
                while (rs.next()) {
                    temp = "";
                    Vector<String> vTmp = new Vector<String>();
                    for (int n = 0; n < field.length; n++) {
                        temp = rs.getString(field[n]) + "||" + temp;
                        //row[j][n] = rs.getString(field[n]);
                        vTmp.add(rs.getString(field[n]));

                    }
                    vRow.add(vTmp);
                    j++;
                    //listData[i++] = temp;
                }
                atm = new MyTableModel(); // new
                atm.setDataVector(vRow, vColumn);
                table = new JTable(atm);
                con.remove(sp);
                table.setAutoscrolls(true);
                table.setShowHorizontalLines(true);
                //table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
                sp = new JScrollPane(table);
                con.add(sp, BorderLayout.CENTER);
                validate();

            } catch (SQLException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
            System.out.println(selectedtable);
        }

        if (!input.trim().equals("") && o == search) {
			/*
			In other words, to get to the cell at row 1, column 5:
((Vector)getDataVector().elementAt(1)).elementAt(5);
			*/
            Vector tableData = atm.getDataVector();
            int row = 0, r = 0;
            boolean find = false, atEnd = false;
            if (table.getSelectedRow() != -1) {
                r = table.getSelectedRow() + 1;
                if (r == table.getRowCount()) {
                    JOptionPane.showMessageDialog(this, "Reach the end of the table!Start over!");
                    atEnd = true;
                    table.changeSelection(0, 0, false, false);
                    table.clearSelection();
                    r = 0;
                }
            }
            for (row = r; row < atm.getRowCount(); row++) {
                for (int column = 0; column < atm.getColumnCount(); column++) {
                    String cell = (String) ((Vector) tableData.elementAt(row)).elementAt(column);

                    if (cell.trim().equalsIgnoreCase(input.trim())) {
                        table.changeSelection(row, 0, false, false);
                        find = true;
                        break;
                    }
                }
                if (find)
                    break;

            }
            if (atEnd) {
                table.changeSelection(0, 0, false, false);
                table.clearSelection();
            }
            if (!find) {
                JOptionPane.showMessageDialog(this, "Sorry!No Match!!!");
                table.changeSelection(0, 0, false, false);
                table.clearSelection();
            }


        }
    }

    private int getRowNumOfResultSet(ResultSet rs) {
        int countRow = 0;
        try {
            rs.last();
            countRow = rs.getRow();
            rs.beforeFirst();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return countRow;
    }

    private String[] getResultSetByAttri(ResultSet rs, String attribute) {
        int j = 0;
        int i = this.getRowNumOfResultSet(rs);
        String[] StrArr = new String[i];
        try {
            while (rs.next()) {
                StrArr[j] = rs.getString(attribute);
                j++;
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return StrArr;
        // String
    }

    @Override
    public void editingCanceled(ChangeEvent arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void editingStopped(ChangeEvent arg0) {
        // TODO Auto-generated method stub
//		Object value = ce.getCellEditorValue();
//		int irow = table.getSelectedRow();
//
//		currentSelectedRow = table.getSelectedRow();
//		int iColumn = table.getSelectedColumn();
//		atm.setValueAt(value, irow, iColumn);
//		table.setModel(atm);
//		newID=Integer.parseInt(atm.getValueAt(irow, 0).toString());
//
//		System.out.println("row edited:" + irow);
//		System.out.println("Column edited:" + iColumn);
//		System.out.println("New value:" + value);

    }

    private class MyTableModel extends DefaultTableModel {
        public MyTableModel() {
            super();
        }

        public boolean isCellEditable(int rowIndex, int columnIndex) {

            if (rowIndex == currentRowbeModified) {
                return true;
            }
//			  return false;//all the table can not be edited
            //表格可编辑
            return true;
        }
    }

    private class RenameTable extends JFrame implements ActionListener {

        private JLabel label[];
        private JTextField[] text;
        private JLabel oldtablename;
        private JTextField newtablename;
        private JPanel p, pSouth1, pNorth1;
        private JButton confirm;
        private int count = 0;
        private Connection connect;
        private String[] oldfieldName = null;
        private String oldTableName = "";
        private PreparedStatement prestate1, prestate2;
        private String[] oldTypes = null;

        public RenameTable(Connection mycon, String selectedTable, String[] fieldName, String[] Types) {
            super("修改表信息 " + selectedTable);
            oldfieldName = fieldName;
            oldTableName = selectedTable;
            connect = mycon;
            oldTypes = Types;
            count = fieldName.length;
            this.setLocation(200, 300);
            this.setSize(400, 200);
            GridLayout glo = new GridLayout(count + 1, 2);
            label = new JLabel[count];
            text = new JTextField[count];
            p = new JPanel();
            p.setLayout(glo);
            oldtablename = new JLabel("表名: " + selectedTable);
            oldtablename.setForeground(Color.red);
            newtablename = new JTextField(selectedTable);
            newtablename.selectAll();
            newtablename.setSelectedTextColor(Color.BLUE);
            p.add(oldtablename);
            p.add(newtablename);
            for (int i = 0; i < count; i++) {
                label[i] = new JLabel("列 " + (i + 1) + ": " + fieldName[i] + " " + Types[i] + "");
                label[i].setForeground(Color.red);
                text[i] = new JTextField(fieldName[i] + " " + Types[i] + "");
                text[i].setName(fieldName[i]);
                p.add(label[i]);
                p.add(text[i]);
            }

            confirm = new JButton("确定");
            confirm.addActionListener(this);
            pSouth1 = new JPanel();
            pSouth1.add(confirm);
            pSouth1.setBackground(Color.yellow);
            pNorth1 = new JPanel();
            GridLayout glo1 = new GridLayout(1, 2);
            pNorth1.setLayout(glo1);
            pNorth1.add(new JLabel("旧："));
            pNorth1.add(new JLabel("新："));
            pNorth1.setBackground(Color.yellow);
            Container con = this.getContentPane();
            con.add(p, BorderLayout.CENTER);
            con.add(pSouth1, BorderLayout.SOUTH);
            con.add(pNorth1, BorderLayout.NORTH);
            con.validate();
            this.setVisible(true);

        }

        private String[] getInputField() {
            String[] input = new String[count];

            for (int i = 0; i < count; i++) {
                input[i] = text[i].getText();
            }

            return input;
        }

        @Override
        public void actionPerformed(ActionEvent arg0) {
            if (arg0.getSource() == confirm) {
                //ALTER TABLE table_name CHANGE old_field_name new_field_name field_type;
                //alter table t1 rename t2;
                //System.out.println("sssss"+newtablename.getText()+"tttt");
                boolean canHide = true;
                prestate2 = this.renameFieldPreStat(oldTableName);
                String[] newFieldName = null;
                try {

                    newFieldName = this.getInputField();
                    prestate2.executeUpdate();
                    Vector newColumn = new Vector();
                    for (int j = 0; j < newFieldName.length; j++) {
                        if (!newFieldName[j].equals("")) {
                            String[] temp = newFieldName[j].trim().split(" ");
                            newColumn.add(temp[0]);
                        } else
                            newColumn.add(oldfieldName[j]);
                    }
                    atm.setDataVector(vRow, newColumn);
                } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    //e.printStackTrace();
                    JOptionPane.showMessageDialog(this,
                            "出错啦，请重试!");
                    canHide = false;


                }

                //rename table
                String newTabName = newtablename.getText().trim();
                if (!newTabName.equals("")) {

                    prestate1 = this.renameTablePreStat(oldTableName);
                    try {

                        prestate1.executeUpdate();
                        System.out.println("Oldname of Table: " + oldTableName);
                        System.out.println("New name of Table: " + newTabName);
                        d.setSelectedIndex(0);
                        d.removeItem(oldTableName);
                        d.addItem(newTabName);
                        d.setSelectedItem(newTabName);
                    } catch (SQLException e) {
                        // TODO Auto-generated catch block
                        JOptionPane.showMessageDialog(this,
                                "Something wrong with the new table name!");
                        newtablename.selectAll();
                        canHide = false;
                    }

                }
                d.updateUI();
                table.updateUI();
                //d.validate();
                validate();
                if (canHide)
                    this.setVisible(false);
            }

        }

        private PreparedStatement renameTablePreStat(String currentTable) {
            PreparedStatement addStat = null;
            String prequery = "";
            String newTabName = newtablename.getText().trim();
            try {
                if (!newTabName.equals(""))
                    prequery = "ALTER TABLE " + currentTable + " rename " + newTabName + ";";
                else
                    prequery = "ALTER TABLE " + currentTable + " rename " + currentTable + ";";

                System.out.println(prequery);
                strSQL = prequery;
                arrayList_allSql.add(prequery);
                addStat = connect.prepareStatement(prequery);
            } catch (SQLException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
            return addStat;

        }

        private PreparedStatement renameFieldPreStat(String currentTable) { //»¹Ã»¸Ä
            //ALTER TABLE table_name CHANGE old_field_name new_field_name field_type;
            PreparedStatement addStat = null;
            String prequery1 = "";
            String prequery = "";
            String tempQ1 = "";
            String[] newFieldName = null;

            try {
                String[] field = oldfieldName;
                newFieldName = this.getInputField();

                prequery1 = "ALTER TABLE " + currentTable;
                for (int g = 0; g < field.length; g++) {
                    newFieldName[g] = newFieldName[g].trim();
                    if (newFieldName[g].equals(""))
                        tempQ1 = tempQ1 + " CHANGE " + field[g] + " " + field[g] + " " + oldTypes[g];
                    else if (newFieldName[g].contains(" ")) {

                        tempQ1 = tempQ1 + " CHANGE " + field[g] + " " + newFieldName[g];
                    } else {
                        tempQ1 = tempQ1 + " CHANGE " + field[g] + " " + newFieldName[g] + " " + oldTypes[g];
                    }
                    if (g != (field.length - 1)) {
                        tempQ1 = tempQ1 + ",";
                    }
                }

                prequery = prequery1 + tempQ1 + ";";
                System.out.println(prequery);
                strSQL = prequery;
                arrayList_allSql.add(prequery);
                addStat = connect.prepareStatement(prequery);
            } catch (SQLException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
            return addStat;

        }
    }
}

 

