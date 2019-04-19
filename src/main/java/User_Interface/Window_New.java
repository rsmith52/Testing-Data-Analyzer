package User_Interface;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import File_IO.CSV_In;
import Objects.*;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import javax.swing.SwingConstants;

public class Window_New extends JFrame {

	private JPanel contentPane;
	String text;

	/**
	 * Launch the application.
	 */
	public static void createNewWindow() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Window_New frame = new Window_New();
					frame.setTitle("New Dataset");
					frame.setVisible(true);
					frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Window_New() {
		text = "";
		setResizable(false);
		setAlwaysOnTop(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 540, 106);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JLabel lblPleaseEnterIn = new JLabel("Please click the Open .CSV button and choose the .CSV file with the data to be categorized");
		contentPane.add(lblPleaseEnterIn, BorderLayout.NORTH);
		
		JButton btnOpencsv = new JButton("Open .CSV");
		btnOpencsv.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(!text.equals("")){
					JFileChooser chooser = new JFileChooser();
				    FileNameExtensionFilter filter = new FileNameExtensionFilter(
				        "CSV Files", "csv");
				    chooser.setFileFilter(filter);
				    int returnVal = chooser.showOpenDialog(contentPane);
				    if(returnVal == JFileChooser.APPROVE_OPTION) {
				        try {
					    	ArrayList<Case> casesAL = CSV_In.csvRead(chooser.getSelectedFile());
					    	Case[] cases = new Case[casesAL.size()];
					    	cases = casesAL.toArray(cases);
					    	//TODO get the date from some date library
					    	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yyyy");
					    	LocalDate dateCreated = LocalDate.now();
					    	String dateString = dateCreated.format(dtf);
					    	Categorized cat = new Categorized(text, dateString, cases);
					    	Window_Main.catList.add(cat);
					    	Window_Main.createMainWindow();
					    	dispose();
				        }
				        catch(Exception err) {
				        	JOptionPane.showMessageDialog(contentPane, err.getMessage(), "Error",
				        	        JOptionPane.WARNING_MESSAGE);
				        }
				    }
				} else {
					//TODO print "must add a name" warning
				}
			}
		});
		contentPane.add(btnOpencsv, BorderLayout.EAST);
		
		//Text field to enter the categorized data set name
		final JTextField textField = new JTextField();
		textField.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent a){
				text = textField.getText();
			}
		});
		contentPane.add(textField, BorderLayout.CENTER);
		
		JButton btnNewButton = new JButton("Go Back");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Window_Main.createMainWindow();
				dispose();
			}
		});
		contentPane.add(btnNewButton, BorderLayout.WEST);
		//Watch for close of application


	}

}
