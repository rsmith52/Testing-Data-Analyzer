package User_Interface;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import File_IO.CSV_In;
import Objects.Case;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.SwingConstants;

public class Window_New extends JFrame {

	private JPanel contentPane;

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
		setResizable(false);
		setAlwaysOnTop(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 540, 106);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JLabel lblPleaseEnterIn = new JLabel("Please click the Open .CSV button and choose the .CSV file with the data to be catigorized");
		contentPane.add(lblPleaseEnterIn, BorderLayout.NORTH);
		
		JButton btnOpencsv = new JButton("Open .CSV");
		btnOpencsv.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JFileChooser chooser = new JFileChooser();
			    FileNameExtensionFilter filter = new FileNameExtensionFilter(
			        "CSV Files", "csv");
			    chooser.setFileFilter(filter);
			    int returnVal = chooser.showOpenDialog(contentPane);
			    if(returnVal == JFileChooser.APPROVE_OPTION) {
			        try {
			    	ArrayList<Case> cases = CSV_In.csvRead(chooser.getSelectedFile());
			    	//TODO: what needs to be done here? We need to categorize the data set with the AI and then save it.
			    	Window_Main.createMainWindow();
			    	dispose();
			        }
			        catch(Exception err) {
			        	JOptionPane.showMessageDialog(contentPane, err.getMessage(), "Error",
			        	        JOptionPane.WARNING_MESSAGE);
			        }
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
		//Watch for close of appication


	}

}
