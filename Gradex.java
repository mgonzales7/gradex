import java.awt.*;
import java.awt.event.*;
import java.text.*;
import javax.swing.*;
import javax.swing.border.*;

public class Gradex extends JFrame 
{
   private JPanel inputGradeJPanel;
   private JLabel studentName;
   private JTextField studentNameField;
   private JLabel test1JLabel;

   private JTextField test1JTextField;
   private JLabel test2JLabel;
   private JTextField test2JTextField;
   private JLabel test3JLabel;
   private JTextField test3JTextField;
   private JButton submitGradesJButton;
   private ButtonGroup displayButtonGroup;
   private JRadioButton numericJRadioButton;
   private JRadioButton letterJRadioButton;

   private JLabel displayJLabel;
   private JTextArea displayJTextArea;

   private JLabel classAverageJLabel;
   private JTextField classAverageJTextField;

   // Keep Track of Number of Students
   private int studentCount = 0;

   // constants
   private final int MAXIMUM_STUDENTS = 10;
   private final int FIRST_TEST = 0;
   private final int SECOND_TEST = 1;
   private final int THIRD_TEST = 2;

   //Assignements Array
   private String assignments[] = {"Test 1","HW 1","HW 2","Test2", "Test 3", "Test 4", "Test 5"};

   private JTextField assignmentFields[] = new JTextField[ assignments.length ];

   // Student Name Array
   private String studentNames[] = new String[ MAXIMUM_STUDENTS ];

   // Student Grades Array
   private int studentGrades[][] = 
      new int[ MAXIMUM_STUDENTS ][ assignments.length ];

   private DecimalFormat twoDigits = new DecimalFormat( "0.00" );

   public Gradex()
   {
      createUserInterface();
      displayNumericGrades();
   }

   // Creates the User Interface
   private void createUserInterface()
   {
      // get content pane for attaching GUI components
      Container contentPane = getContentPane();

      //Input Controls
      JPanel controlPane = new JPanel();
      controlPane.setLayout( new GridLayout(0,1));
      contentPane.add(controlPane, BorderLayout.LINE_START);
      //Display
      JPanel displayPane = new JPanel();
      displayPane.setLayout( new GridLayout(0,1));
      contentPane.add( displayPane, BorderLayout.CENTER);

      //Bottom Controls
      JPanel bottomPane = new JPanel();
      bottomPane.setLayout( new GridLayout(0,4));
      contentPane.add( bottomPane, BorderLayout.PAGE_END);

      // set up inputGradeJPanel
      inputGradeJPanel = new JPanel();
      inputGradeJPanel.setBorder(
         new TitledBorder( "Input Grade" ) );
      inputGradeJPanel.setLayout( new GridLayout(0,2));
      
      // set up studentNameJLabel
      studentName = new JLabel();
      studentName.setText( "Student Name:" );
      inputGradeJPanel.add( studentName);

      // set up studentNameJTextField
      studentNameField = new JTextField();
      studentNameField.setHorizontalAlignment(
         JTextField.RIGHT );
      inputGradeJPanel.add( studentNameField );
      
      for(int i = 0; i < assignments.length; i++){
      	JLabel assignment = new JLabel();
      	assignment.setText(assignments[i]);
      	inputGradeJPanel.add(assignment);
      	JTextField assignmentField = new JTextField();
      	assignmentFields[i] = assignmentField;
      	assignmentField.setHorizontalAlignment(JTextField.RIGHT);
      	inputGradeJPanel.add(assignmentField);
      }


      controlPane.add( inputGradeJPanel );

      // set up submitGradesJButton
      submitGradesJButton = new JButton();
      submitGradesJButton.setText( "Submit Grades" );
      inputGradeJPanel.add( submitGradesJButton );
      submitGradesJButton.addActionListener(

         new ActionListener() // anonymous inner class
         {
            // event handler called when submitGradesJButton 
            // is clicked
            public void actionPerformed( ActionEvent event )
            {
               submitGradesJButtonActionPerformed( event );
            }

         } // end anonymous inner class

      ); // end call to addActionListener

      // set up displayButtonGroup
      displayButtonGroup = new ButtonGroup();
      
      // set up numericJRadioButton
      numericJRadioButton = new JRadioButton(); 
      numericJRadioButton.setText( "Numeric" );
      numericJRadioButton.setSelected( true );
      displayButtonGroup.add( numericJRadioButton );
      bottomPane.add( numericJRadioButton );
      numericJRadioButton.addActionListener(

         new ActionListener() // anonymous inner class
         {
            // event handler called when numericJRadioButton 
            // is selected
            public void actionPerformed( ActionEvent event )
            {
               numericJRadioButtonActionPerformed( event );
            }

         } // end anonymous inner class

      ); // end call to addActionListener
      
      // set up letterJRadioButton
      letterJRadioButton = new JRadioButton();
      letterJRadioButton.setText( "Letter" );
      displayButtonGroup.add( letterJRadioButton );
      bottomPane.add( letterJRadioButton );
      letterJRadioButton.addActionListener(

         new ActionListener() // anonymous inner class
         {
            // event handler called when letterJRadioButton 
            // is selected
            public void actionPerformed( ActionEvent event )
            {
               letterJRadioButtonActionPerformed( event );
            }

         } // end anonymous inner class

      ); // end call to addActionListener
      
      // set up displayJTextArea
      displayJTextArea = new JTextArea();
      displayJTextArea.setEditable( false );
      displayPane.add( displayJTextArea );
      
      // set up classAverageJLabel
      classAverageJLabel = new JLabel();
      classAverageJLabel.setText( "Class average:" );
      bottomPane.add( classAverageJLabel);
      
      // set up classAverageJTextField
      classAverageJTextField = new JTextField();
      classAverageJTextField.setHorizontalAlignment(
         JTextField.CENTER );
      classAverageJTextField.setEditable( false );
      bottomPane.add( classAverageJTextField);
      
      setTitle( "Gradex" ); 
      setSize( 1000, 308 );
      setVisible( true );

   } 

   // convert a number to a letter grade
   private String convertToLetterGrade( double grade )
   {
      if ( grade >= 90 )
      {
         return "A";
      }
      else if ( grade >= 80 )
      {
         return "B";
      }
      else if ( grade >= 70 )
      {
         return "C";
      }
      else if ( grade >= 60 )
      {
         return "D";
      }
      else
      {
         return "F";
      }
   
   } // end method convertToLetterGrade

   // calculate and display the student and class average
   private void submitGradesJButtonActionPerformed(
      ActionEvent event )
   {
      // get user input
      String nameOfStudent = studentNameField.getText();
      studentNames[ studentCount ] = nameOfStudent;
      for(int i = 0; i < assignmentFields.length; i++){
      		studentGrades[studentCount][i] = Integer.parseInt( assignmentFields[i].getText() );
      }

      studentCount++; // increment studentCount

      if ( numericJRadioButton.isSelected() )
      {
         displayNumericGrades();
      }
      else
      {
         displayLetterGrades();
      }
      
      // clear other JTextFields for new data
      studentNameField.setText( "" );
      for(int i = 0; i < assignmentFields.length; i++){
      	assignmentFields[i].setText("");
      }
 
      // if ten student grades have been entered
      if ( studentCount == MAXIMUM_STUDENTS )
      {
         // disable submitGradesJButton
         submitGradesJButton.setEnabled( false );
      }

   } 

   // display student grades and averages as numbers
   private void displayNumericGrades()
   {
      // add a header to displayJTextArea
   		displayJTextArea.setText("");
   		displayJTextArea.append("Name\t");
   		for (int i = 0; i < assignments.length; i++) {
   			displayJTextArea.append(assignments[i] + "\t");
   		}

   		displayJTextArea.append("Average\n");
      int studentTotal = 0; // store the student's total grades
      int classTotal = 0;   // store the class's total grades

      for ( int student = 0; student < studentCount; student++ )
      {
         // display student names
         displayJTextArea.append( studentNames[ student ] + "\t" );

         studentTotal = 0; // initialize the student's total grades
         
         for ( int test = 0; test < assignments.length; test++ )
         {
            // append each test grade to displayJTextArea
            displayJTextArea.append(
               studentGrades[ student ][ test ] + "\t" );

            // add the test grade to the student's total
            studentTotal += studentGrades[ student ][ test ];

         } // end inner for

         // add the student's total grade to the class's total
         classTotal += studentTotal;

         // calculate the student average and display it
         double studentAverage = 
            ( double ) studentTotal / assignments.length;
         displayJTextArea.append( 
            twoDigits.format( studentAverage ) + "\n" );

      } // end outer for

      // calculate the class average and display it
      double classAverage = 
         ( double ) classTotal / studentCount / assignments.length;
      classAverageJTextField.setText( 
         twoDigits.format( classAverage ) );

   } // end method displayNumericGrades

   // display student grades and averages as letters
   private void displayLetterGrades()
   {
     // add a header to displayJTextArea
   		displayJTextArea.setText("");
   		displayJTextArea.append("Name\t");
   		for (int i = 0; i < assignments.length; i++) {
   			displayJTextArea.append(assignments[i] + "\t");
   		}

   		displayJTextArea.append("Average\n");

      int studentTotal = 0; // store the student's total grades
      int classTotal = 0;   // store the class's total grades

      for ( int student = 0; student < studentCount; student++ )
      {
         // display student names
         displayJTextArea.append( studentNames[ student ] + "\t" );

         studentTotal = 0; // initialize the student's total grades

         for ( int test = 0; test < assignments.length; test++ )
         {
            // append each test grade to displayJTextArea
            displayJTextArea.append( convertToLetterGrade( 
               studentGrades[ student ][ test ] ) + "\t" );

            // add the test grade to the student's total
            studentTotal += studentGrades[ student ][ test ];

         } // end inner for

         // add the student's total grade to the class's total
         classTotal += studentTotal;

         // calculate the student average and display it
         double studentAverage = 
            ( double ) studentTotal / assignments.length;
         displayJTextArea.append( 
            convertToLetterGrade( studentAverage ) + "\n" );

      } // end outer for

      // calculate the class average and display it
      double classAverage = 
         ( double ) classTotal / studentCount / assignments.length;
      classAverageJTextField.setText( 
         convertToLetterGrade( classAverage ) );

   } // end method displayLetterGrades

   // user selected numeric display
   private void numericJRadioButtonActionPerformed(
      ActionEvent event )
   {
      displayNumericGrades();

   } // end method numericJRadioButtonActionPerformed
   
   // user selected letter display
   private void letterJRadioButtonActionPerformed(
      ActionEvent event )
   {
      displayLetterGrades();
       
   } // end method letterJRadioButtonActionPerformed

   // main method
   public static void main( String[] args ) 
   {
      Gradex application = new Gradex();
      application.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
      
   } // end method main

} 
