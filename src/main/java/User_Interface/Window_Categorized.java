package User_Interface;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.EventQueue;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.io.FileNotFoundException;

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
	private static Categorized cat;

	/**
	 * Launch the application.
	 */
	public static void createCategorizedWindow(final Categorized inputCat) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Window_Categorized frame = new Window_Categorized(inputCat);
					frame.setTitle("Categorized Dataset");
					frame.setVisible(true);
					frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public static Categorized getCategorized() {
		return cat;
	}

	public Window_Categorized(final Categorized inputCat) {
		cat = inputCat;

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
					PDF_Out pdfMaker = new PDF_Out();
					pdfMaker.outputPDF();
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
				File currentDir = new File(System.getProperty("user.dir") + "/cats");
				chooser.setCurrentDirectory(currentDir);
				FileNameExtensionFilter filter = new FileNameExtensionFilter(
						"CAT Files", "CAT");
				chooser.setFileFilter(filter);
				int returnVal = chooser.showOpenDialog(contentPane);
				if(returnVal == JFileChooser.APPROVE_OPTION) {
					try {
						File file = chooser.getSelectedFile();
						if(file.getName().equals(inputCat.getName() + ".cat")) {
							lblOutput.setText("Merge failed, identical datasets detected");
						} else {
							Categorized cat2 = Categorized_In.readFromDatabase(file);
							Categorized mergedCat = Categorized.combineLists(getCategorized(), cat2);
							Window_Main.catList.add(mergedCat);
							Categorized_Out.writeToDatabase(mergedCat.getName() + ".cat", mergedCat);
							lblOutput.setText("Dataset merge successful");
						}
					} catch(Exception e){
						lblOutput.setText("Dataset merge failed; an exception occurred");
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