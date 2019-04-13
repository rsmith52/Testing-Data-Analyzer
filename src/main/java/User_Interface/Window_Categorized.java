package User_Interface;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.EventQueue;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import File_IO.*;
import Objects.*;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JList;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.ArrayList;

public class Window_Categorized extends JFrame {
	
	private Container contentPane;
	private Categorized cat;

	/**
	 * Launch the application.
	 */
	public static void createCategorizedWindow(Categorized cat) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Window_Categorized frame = new Window_Categorized(cat);
					frame.setTitle("Categorized Dataset");
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public Categorized getCategorized() {
		return this.cat;
	}
	
	public Window_Categorized(Categorized cat) {
		this.cat = cat;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 500);
		contentPane = getContentPane();
		
		//"Data Set: _____"
		JPanel titlePane = new JPanel();
		titlePane.setLayout(new BoxLayout(titlePane, BoxLayout.Y_AXIS));
		//TODO center and make it look good
		//EDIT HERE
		JLabel lblDataSet = new JLabel("Data Set: " + cat.name);
		//TODO add name of categorized data
		
		titlePane.add(lblDataSet);
		
		//TODO add a label to tell whether a button click is successful or not
		
		//buttons
		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new BoxLayout(buttonPane, BoxLayout.LINE_AXIS));
		
		JButton btnOutputCsv = new JButton("Output .csv");
		btnOutputCsv.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent m) {
				try {
					CSV_Out.writeCSV(getCategorized().caseList);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
		
		JButton btnOutputMetricsPdf = new JButton("Output Metrics .pdf");
		btnOutputMetricsPdf.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent m) {
				//TODO call PDF_Out
			}
		});
		
		JButton btnMergeDatasets = new JButton ("Merge Datasets");
		btnMergeDatasets.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent m) {
				//get other Categorized or case list somehow
				JFileChooser chooser = new JFileChooser();
				FileNameExtensionFilter filter = new FileNameExtensionFilter(
						"CSV Files", "csv");
				chooser.setFileFilter(filter);
				int returnVal = chooser.showOpenDialog(contentPane);
				if(returnVal == JFileChooser.APPROVE_OPTION) {
					try {
						ArrayList<Case> cases = CSV_In.csvRead(chooser.getSelectedFile());
						//getCategorized().combineLists(cases);
					} catch(IOException e){
						e.printStackTrace();
					}
				}
				
			}
		});
		
		JButton btnBack = new JButton ("Back");
		btnBack.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent m) {
				Window_Main.createMainWindow();
				dispose();
			}
		});
		
		
		buttonPane.add(btnOutputCsv);
		buttonPane.add(Box.createHorizontalStrut(10));
		buttonPane.add(btnOutputMetricsPdf);
		buttonPane.add(Box.createHorizontalStrut(10));
		buttonPane.add(btnMergeDatasets);
		buttonPane.add(Box.createHorizontalStrut(10));
		buttonPane.add(btnBack);
		
		JPanel topPane = new JPanel();
		topPane.add(titlePane, BorderLayout.CENTER);
		
		JPanel bottomPane = new JPanel();
		bottomPane.add(buttonPane, BorderLayout.CENTER);
		
		contentPane.add(topPane, BorderLayout.NORTH);
		contentPane.add(bottomPane, BorderLayout.SOUTH);
		
		
	}
}