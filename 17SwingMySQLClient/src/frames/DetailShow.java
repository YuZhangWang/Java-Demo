package frames;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.table.*;

public class DetailShow extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6518293763899472453L;

	public DetailShow(int selectedID, String selectedTable, Connection myCon,
			Vector<String> selectedColumn, int selectedRow) {
		super("Details about...");
		this.setLocation(300, 200);
		setSize(600, 300);
		show tb = new show(selectedID, selectedTable, myCon, selectedColumn,
				selectedRow);
		setContentPane(tb);
		this.setVisible(true);
	}
}

class show extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3699430987029368323L;

	show(int selectedID, String selectedTable, Connection myCon,
			Vector<String> selectedColumn, int selectedRow) {
		setLayout(new BorderLayout());
		// Create data model
		DetailDataModel eModel = new DetailDataModel(selectedID, selectedTable,
				myCon, selectedColumn, selectedRow);
		// Create/setup table
		JTable table = new JTable(eModel);
		table.setEnabled(false);
		// Place table in JScrollPane
		JScrollPane scrollPane = new JScrollPane(table);
		// Add to Screen
		add(scrollPane, BorderLayout.CENTER);
	}

}

class DetailDataModel extends AbstractTableModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8355228342165088075L;
	// By extending AbstractTableModel, instead of
	// implementing TableModel yourself,
	// AbstractTableModel takes care of
	// TableModelListener list management
	String columns[] = { "" };
	String rows[][] = { { "" } };
	private int numColumns = columns.length;
	private int numRows = rows.length;
	int sID = 0;
	String sTable = "";
	Connection myConnect = null;

	DetailDataModel(int selectedID, String selectedTable, Connection myCon,
			Vector<String> selectedColumn, int selectedRow) {
		sID = selectedID;
		sTable = selectedTable;
		myConnect = myCon;
		String query1 = "";
		ResultSet rs;
		Statement execStat1;

		try {
			execStat1 = myConnect.createStatement();
			if (sTable.equalsIgnoreCase("movies")) {
				String moviecolumns[] = { "MovieID", "Title", "Year", "Length",
						"Genres", "People", "People_role" };

				query1 = "select Genres.Film_type from Genres,Type_of_movies,movies where movies.id="
						+ sID
						+ " and movies.id=Type_of_movies.movie_id and Type_of_movies.type_id=Genres.id;";
				rs = execStat1.executeQuery(query1);
				String[] strArr4 = this.getResultSetByAttri(rs, "Film_Type");
				int rowNum = strArr4.length;

				query1 = "select movies_length.Film_length from movies_length,movies where movies.id="
						+ sID + " and movies.id=movies_length.movie_id;";

				rs = execStat1.executeQuery(query1);
				String[] strArr3 = this.getResultSetByAttri(rs, "Film_length");
				if (rowNum < strArr3.length) {
					rowNum = strArr3.length;
				}

				query1 = "select people.name as peoplename,people_in_movies.people_role from people_in_movies,people,movies where movies.id="
						+ sID
						+ " and movies.id=people_in_movies.movie_id and people_in_movies.people_id=people.id;";

				rs = execStat1.executeQuery(query1);
				String[] strArr5 = this.getResultSetByAttri(rs, "peoplename");
				if (rowNum < strArr5.length) {
					rowNum = strArr5.length;
				}
				String[] strArr6 = this.getResultSetByAttri(rs, "people_role");
				if (rowNum < strArr6.length) {
					rowNum = strArr6.length;
				}

				query1 = "select movies.id as movieid,title,year from movies where movies.id="
						+ sID + ";";

				rs = execStat1.executeQuery(query1);
				String[] strArr0 = this.getResultSetByAttri(rs, "movieid");
				if (rowNum < strArr0.length) {
					rowNum = strArr0.length;
				}
				String[] strArr2 = this.getResultSetByAttri(rs, "year");
				String[] strArr1 = this.getResultSetByAttri(rs, "title");
				String[][] newrows = new String[rowNum + 1][moviecolumns.length];
				newrows = this.copyArr(newrows, 0, strArr0);
				newrows = this.copyArr(newrows, 1, strArr1);
				newrows = this.copyArr(newrows, 2, strArr2);
				newrows = this.copyArr(newrows, 3, strArr3);
				newrows = this.copyArr(newrows, 4, strArr4);
				newrows = this.copyArr(newrows, 5, strArr5);
				newrows = this.copyArr(newrows, 6, strArr6);
				rows = newrows;
				columns = moviecolumns;
			}

			if (sTable.equalsIgnoreCase("people")) {
				String peoplecolumns[] = { "PeopleID", "Name", "Film_title",
						"People_role" };

				query1 = "select people.id as PeopleID,people.name from people where people.id="
						+ sID + ";";
				rs = execStat1.executeQuery(query1);
				String[] strArr0 = this.getResultSetByAttri(rs, "PeopleID");
				int rowNum = strArr0.length;
				String[] strArr1 = this.getResultSetByAttri(rs, "name");
				rowNum = strArr1.length;

				query1 = "select movies.Title as Film_title,people_in_movies.people_role from people_in_movies,movies where people_in_movies.people_id="
						+ sID + " and people_in_movies.movie_id=movies.id;";

				rs = execStat1.executeQuery(query1);
				String[] strArr2 = this.getResultSetByAttri(rs, "Film_title");
				if (rowNum < strArr2.length) {
					rowNum = strArr2.length;
				}

				String[] strArr3 = this.getResultSetByAttri(rs, "people_role");
				if (rowNum < strArr3.length) {
					rowNum = strArr3.length;
				}

				String[][] newrows = new String[rowNum + 1][peoplecolumns.length];
				newrows = this.copyArr(newrows, 0, strArr0);
				newrows = this.copyArr(newrows, 1, strArr1);
				newrows = this.copyArr(newrows, 2, strArr2);
				newrows = this.copyArr(newrows, 3, strArr3);
				rows = newrows;
				columns = peoplecolumns;
			}

			if (sTable.equalsIgnoreCase("movies_length")) {
				String tempcolumns[] = { "Movie_Title", "Film_length" };

				System.out.println(selectedColumn);
				query1 = "select movies.title as Movie_Title,movies_length.Film_length from movies,movies_length where movies_length.movie_id=movies.id and movies_length.movie_id="
						+ selectedColumn.elementAt(0) + ";";
				rs = execStat1.executeQuery(query1);
				String[] strArr0 = this.getResultSetByAttri(rs, "Movie_Title");
				int rowNum = strArr0.length;
				String[] strArr1 = this.getResultSetByAttri(rs, "Film_length");
				if (rowNum < strArr1.length) {
					rowNum = strArr1.length;
				}
				String[][] newrows = new String[rowNum + 1][tempcolumns.length];
				newrows = this.copyArr(newrows, 0, strArr0);
				newrows = this.copyArr(newrows, 1, strArr1);

				rows = newrows;
				columns = tempcolumns;
			}

			if (sTable.equalsIgnoreCase("type_of_movies")) {
				String tempcolumns[] = { "Movie_Title", "Genres" };

				System.out.println(selectedColumn);
				query1 = "select DISTINCT movies.title as Movie_Title from movies,type_of_movies where type_of_movies.movie_id=movies.id and type_of_movies.movie_id="
						+ selectedColumn.elementAt(0)
						+ ";";
				rs = execStat1.executeQuery(query1);
				String[] strArr0 = this.getResultSetByAttri(rs, "Movie_Title");
				int rowNum = strArr0.length;
				query1 = "select DISTINCT genres.film_type as film_type from type_of_movies,genres where type_of_movies.type_id=genres.id and type_of_movies.type_id="
					+ selectedColumn.elementAt(1) + ";";
				rs = execStat1.executeQuery(query1);
				String[] strArr1 = this.getResultSetByAttri(rs, "film_type");
				if (rowNum < strArr1.length) {
					rowNum = strArr1.length;
				}
				String[][] newrows = new String[rowNum + 1][tempcolumns.length];
				newrows = this.copyArr(newrows, 0, strArr0);
				newrows = this.copyArr(newrows, 1, strArr1);

				rows = newrows;
				columns = tempcolumns;
			}

			if (sTable.equalsIgnoreCase("people_in_movies")) {
				String tempcolumns[] = { "People_name", "Movie_title",
						"People_role" };

				System.out.println(selectedColumn);
				query1 = "select people.name as PeopleName, movies.title as Movie_Title,people_in_movies.people_role as people_role from movies,people,people_in_movies "
						+ "where people_in_movies.movie_id=movies.id and people_in_movies.movie_id="
						+ selectedColumn.elementAt(1)
						+ " and people_in_movies.people_id=people.id and people_in_movies.people_id="
						+ selectedColumn.elementAt(0) 
						/*
						+ " and people_in_movies.people_role='"
						+ selectedColumn.elementAt(2)+"'"
						*/
						+";";
				rs = execStat1.executeQuery(query1);
				String[] strArr0 = this.getResultSetByAttri(rs, "PeopleName");
				int rowNum = strArr0.length;
				String[] strArr1 = this.getResultSetByAttri(rs, "Movie_Title");
				if (rowNum < strArr1.length) {
					rowNum = strArr1.length;
				}
				String[] strArr2 = this.getResultSetByAttri(rs, "people_role");
				if (rowNum < strArr2.length) {
					rowNum = strArr2.length;
				}
				String[][] newrows = new String[rowNum + 1][tempcolumns.length];
				newrows = this.copyArr(newrows, 0, strArr0);
				newrows = this.copyArr(newrows, 1, strArr1);
				newrows = this.copyArr(newrows, 2, strArr2);

				rows = newrows;
				columns = tempcolumns;
			}

			if (sTable.equalsIgnoreCase("genres")) {
				String genrescolumns[] = { "GenreID", "GenreName", "Film_title" };

				query1 = "select genres.id as GenreID,Genres.Film_type as GenreName,movies.title as Film_title from Genres,Type_of_movies,movies where genres.id="
						+ sID
						+ " and genres.id=Type_of_movies.type_id and Type_of_movies.movie_id=movies.id;";
				rs = execStat1.executeQuery(query1);
				String[] strArr0 = this.getResultSetByAttri(rs, "GenreID");
				int rowNum = strArr0.length;
				String[] strArr1 = this.getResultSetByAttri(rs, "GenreName");
				rowNum = strArr1.length;
				String[] strArr2 = this.getResultSetByAttri(rs, "Film_title");
				rowNum = strArr2.length;

				String[][] newrows = new String[rowNum + 1][genrescolumns.length];
				newrows = this.copyArr(newrows, 0, strArr0);
				newrows = this.copyArr(newrows, 1, strArr1);
				newrows = this.copyArr(newrows, 2, strArr2);
				rows = newrows;
				columns = genrescolumns;
			}

			numColumns = columns.length;
			numRows = rows.length;
			this.setValueAt("Total Items: " + (numRows - 1), numRows - 1,
					numColumns - 1);

		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	private String[][] copyArr(String[][] row, int column, String[] originalArr) {
		int length = originalArr.length;
		for (int i = 0; i < length; i++) {
			row[i][column] = originalArr[i];
		}
		return row;
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

	public int getColumnCount() {
		return numColumns;
	}

	public int getRowCount() {
		return numRows;
	}

	public Object getValueAt(int row, int column) {
		return rows[row][column];
	}

	public String getColumnName(int columnIndex) {
		return columns[columnIndex];
	}

	public void setValueAt(Object aValue, int row, int column) {
		String cellValue;
		if (aValue instanceof String)
			cellValue = (String) aValue;
		else
			cellValue = aValue.toString();
		rows[row][column] = cellValue;
		fireTableCellUpdated(row, column);
	}

	public boolean isCellEditable(int row, int column) {
		// first column is read-only
		return (column != 0);
	}
}

