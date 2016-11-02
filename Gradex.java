import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;
import javax.swing.border.MatteBorder;

public class Gradex extends JFrame {


	JTextField jtf = new JTextField();
	JTextField jtl = new JTextField();

	//Course Data
	String[] course = {"Data Structures",
                        "Software Engineering",
                        "Computer Science 1"
    					};
	String[] columnNames = {"First Name",
                        "Last Name",
                        "Grade"
    					};
    String[] list = {"data",
                        "data1",
                        "data2"
    					};
     // buttons
    private JButton calculateGradeB;


    //Student Data
    Object[][] data = {
    {"Kathy", "Smith", "65"},
    {"John", "Doe", "30"},
    {"Sue", "Black", "10"},
    {"Jane", "White", "85"},
    {"Joe", "Brown", "45"}
	};

	Object[][] data1 = {
    {"Kathy", "Smith", "8/20"},
    {"John", "Doe", "10/20"},
    {"Sue", "Black", "Knitting"},
    {"Jane", "White", "Speed reading"},
    {"Joe", "Brown", "Pool"}
	};

	Object[][] data2 = {
    {"Joe", "ok", "8/20"},
    {"John", "Doe", "10/20"},
    {"Sue", "Black", "Knitting"},
    {"Jane", "White", "Speed reading"},
    {"Joe", "Brown", "Pool"}
	};

	public static void main ( String[] args ) {
		Gradex frame = new Gradex();
		frame.setTitle ( "Gradex" );
		frame.setSize ( 512, 128 );
		frame.setLocationRelativeTo ( null );
		frame.setDefaultCloseOperation ( JFrame.EXIT_ON_CLOSE );
		frame.setVisible( true );
	}

	private final TableModel dataModel = new AbstractTableModel() {

        public int getColumnCount() { 
            return 5; 
        }

        public int getRowCount() { 
            return 10;
        }

        public Object getValueAt(int row, int col) { 
            return new Integer(row*col); 
        }
        public boolean isCellEditable(int row, int col)
      		{ return true; }
 		public void setValueAt(Object value, int row, int col) {
	    	data[row][col] = value;
	     	fireTableCellUpdated(row, col);
 	    }
	};

	public Gradex() {
		//create the first panel
		JPanel container = new JPanel();
		container.setLayout(new GridLayout(0,1));
		JPanel ui = new JPanel();
		ui.setLayout( new GridLayout(0,4) );

		JTabbedPane tabbedPane = new JTabbedPane();
		for( int i = 0; i<3; i ++) {
			JTable table = new JTable(dataModel);
			table.setFillsViewportHeight(true);
			Color color = UIManager.getColor("Table.gridColor");
			MatteBorder border = new MatteBorder(1, 1, 0, 0, color);
			table.setBorder(border);
			JScrollPane scrollPane = new JScrollPane(table);
			tabbedPane.addTab(course[i], scrollPane);
		}

		container.add(tabbedPane);
		add(container);
	}

	private class TextFieldListenerFNAME implements ActionListener {
		public void actionPerformed ( ActionEvent e ) {
			String fname = jtf.getText().trim();
			System.out.println( "First Name entered is " + fname );
		}
	}
	private class TextFieldListenerLNAME implements ActionListener {
                public void actionPerformed ( ActionEvent e ) {
                        String lname = jtl.getText().trim();
                        System.out.println( "Last Name entered is " + lname );
                }
        }
}
