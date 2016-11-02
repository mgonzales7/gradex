import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Gradex extends JFrame {

	JTextField jtf = new JTextField();
	JTextField jtl = new JTextField();
	String[] course = {"Data Structures",
                        "Software Engineering",
                        "Computer Science 1"
    					};
	String[] columnNames = {"First Name",
                        "Last Name",
                        "Grade"
    					};
    String[] list = {"data",
                        "data2",
                        "data"
    					};
    Object[][] data = {
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
	public Gradex() {
		//create the first panel
		JPanel container = new JPanel();
		container.setLayout(new GridLayout(0,1));
		JPanel ui = new JPanel();
		ui.setLayout( new GridLayout(0,4) );

		JTabbedPane tabbedPane = new JTabbedPane();
		for( int i = 0; i<3; i ++) {
			JTable table = new JTable(data, columnNames);
			JScrollPane scrollPane = new JScrollPane(table);
			table.setFillsViewportHeight(true);
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
