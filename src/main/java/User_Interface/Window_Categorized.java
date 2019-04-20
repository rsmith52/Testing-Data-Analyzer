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
import java.io.File;
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
					frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
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
		JLabel lblDataSet = new JLabel("Data Set: " + getCategorized().name);
		titlePane.add(lblDataSet);

		final JLabel lblOutput = new JLabel("");

		//buttons
		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new BoxLayout(buttonPane, BoxLayout.LINE_AXIS));

		JButton btnOutputCsv = new JButton("Output .csv");
		btnOutputCsv.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent m) {
				try {
					CSV_Out.writeCSV(getCategorized().caseList);
					lblOutput.setText("CSV output successful");
				} catch (IOException e) {
					lblOutput.setText("CSV output failed");
					e.printStackTrace();
				}
			}
		});

		JButton btnOutputMetricsPdf = new JButton("Output Metrics .pdf");
		btnOutputMetricsPdf.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent m) {
				//TODO call PDF_Out
				try {
//					outputPDF(getCategorized().caseList, getCategorized().name,
//							getCategorized.dateCreated);
					lblOutput.setText("Output metrics successful");
				} catch(Exception e) {
					lblOutput.setText("Output metrics failed");
					e.printStackTrace();
				}

			}
		});

		JButton btnMergeDatasets = new JButton ("Merge Datasets");
		btnMergeDatasets.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent m) {
				//get other Categorized or case list somehow
				JFileChooser chooser = new JFileChooser();
				chooser.setCurrentDirectory(new File(System.getProperty("user.dir") + "/cats"));
				FileNameExtensionFilter filter = new FileNameExtensionFilter(
						"TXT Files", "TXT");
				chooser.setFileFilter(filter);
				int returnVal = chooser.showOpenDialog(contentPane);
				if(returnVal == JFileChooser.APPROVE_OPTION) {
					try {
						Categorized cat2 = Categorized_In.readFromDatabase(chooser.getSelectedFile());
						Categorized.combineLists(getCategorized(), cat2);
						lblOutput.setText("Dataset merge successful");
					} catch(Exception e){
						lblOutput.setText("Dataset merge failed");
						e.printStackTrace();
					}
				} else {
					lblOutput.setText("Dataset merge failed");
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

		JPanel centerPane = new JPanel();
		centerPane.add(lblOutput, BorderLayout.CENTER);

		JPanel bottomPane = new JPanel();
		bottomPane.add(buttonPane, BorderLayout.CENTER);

		contentPane.add(topPane, BorderLayout.NORTH);
		contentPane.add(centerPane, BorderLayout.CENTER);
		contentPane.add(bottomPane, BorderLayout.SOUTH);


	}
}
