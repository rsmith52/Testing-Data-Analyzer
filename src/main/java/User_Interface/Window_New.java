package User_Interface;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.TextField;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import File_IO.CSV_In;
import File_IO.Categorized_Out;
import Neural_Network.Neural;
import Neural_Network.Run_Neural;
import Neural_Network.Train_Neural;
import Objects.*;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
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
		
		//Text field to enter the categorized data set name
		final JTextField textField = new JTextField();
		textField.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent a){
				text = textField.getText();
			}
		});
		contentPane.add(textField, BorderLayout.CENTER);
		
		JButton btnOpencsv = new JButton("Open .CSV");
		btnOpencsv.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				text = textField.getText();
				if(!text.equals("")){
					JFileChooser chooser = new JFileChooser();
					chooser.setCurrentDirectory(new File(System.getProperty("user.dir")));
				    FileNameExtensionFilter filter = new FileNameExtensionFilter(
				        "CSV Files", "csv");
				    chooser.setFileFilter(filter);
				    int returnVal = chooser.showOpenDialog(contentPane);
				    if(returnVal == JFileChooser.APPROVE_OPTION) {
				        try {
				        	File tempFile = new File(System.getProperty("user.dir") + "/cats/" + text + ".cat");
				        	if(tempFile.exists()) {
				        		throw new Exception("File already exists");
				        	}
				        	boolean exists = tempFile.exists();
					    	ArrayList<Case> casesAL = CSV_In.csvRead(chooser.getSelectedFile());
					    	Case[] cases = new Case[casesAL.size()];
					    	cases = casesAL.toArray(cases);
					    	//set up neural network
					    	Neural network = new Neural();
					    	Train_Neural.readWeightsFromFile(network);
					    	Run_Neural.assignCategories(network, cases);
					    	//TODO get the date from some date library
					    	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yyyy");
					    	LocalDate dateCreated = LocalDate.now();
					    	String dateString = dateCreated.format(dtf);
					    	Categorized cat = new Categorized(text, dateString, cases);
					    	Window_Main.catList.add(cat);
					    	Window_Main.createMainWindow();
					    	//EDIT HERE
					    	Categorized_Out.writeToDatabase(cat.getName() + ".cat", cat);
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
