package User_Interface;

import java.awt.BorderLayout;
import java.util.ArrayList;
import java.awt.EventQueue;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.io.File;

import javax.swing.BoxLayout;
import java.awt.Component;
import java.awt.Font;
import java.awt.Color;
import javax.swing.AbstractListModel;
import javax.swing.border.MatteBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import File_IO.CSV_In;
import File_IO.Categorized_In;
import File_IO.FileAccess;
import Objects.Categorized;

import java.awt.Cursor;
import javax.swing.Box;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.ScrollPane;

public class Window_Main extends JFrame {

	private JPanel contentPane;
	public static ArrayList<Categorized> catList = new ArrayList<Categorized>();

	/**
	 * Launch the application.
	 */
	public static void createMainWindow() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Window_Main frame = new Window_Main();
					frame.setTitle("Data Analyzer");
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		/*
		SwingUtilities.invokeLater(new Runnable() {
	        @Override
	        public void run() {
	            final Window_Main app = new Window_Main();
	            app.setTitle("Data Analyzer");
	            app.setVisible(true);
	        }
		});
		*/
        
	}

	/**
	 * Create the frame.
	 */
	public Window_Main() {
		//catList = new ArrayList<Categorized>();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 623, 643);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));

		JLabel lblTets = new JLabel("IT Case Data Analyzer");
		lblTets.setFont(new Font("Arial Black", Font.PLAIN, 17));
		lblTets.setAlignmentX(Component.CENTER_ALIGNMENT);
		contentPane.add(lblTets);
		//Load all of the .cat files into the catList

		File folder = new File(System.getProperty("user.dir") + "/cats"); //check this
		File[] listOfFiles = folder.listFiles();
		ArrayList<String> files = new ArrayList<String>();
		for (File file : listOfFiles) {
		    if (file.getName().contains(".cat")) {
		    	String name = file.getName();
		    	name = name.substring(0, name.length() - 4);
		    	files.add(name);
		    }
		}
		final String[] values = files.toArray(new String[files.size()]);
		JScrollPane scrollPane = new JScrollPane();
		JList list = new JList();
		revalidate();
		repaint();
		list.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
		        JList theList = (JList) e.getSource();
		        if (e.getClickCount() == 2) {
		          int index = theList.locationToIndex(e.getPoint());
		          if (index >= 0) {
		            String o = (String) theList.getModel().getElementAt(index);
		            //System.out.println("Double-clicked on: " + o.toString());
		            //TODO: have this open Window_Catergorized with the selected catagozired dataset
		            System.out.println(o);
		            Categorized cat = Categorized_In.readFromDatabase(o + ".cat");
		            Window_Categorized.createCategorizedWindow(cat);
		            dispose();
		          }
		        }
			}
		});
		list.setModel(new AbstractListModel() {
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		scrollPane.setViewportView(list);
		contentPane.add(scrollPane);

		Component verticalStrut = Box.createVerticalStrut(20);
		contentPane.add(verticalStrut);

		JButton btnNewButton = new JButton("Create new Categorization");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				Window_New.createNewWindow();
			    dispose();
			}
		});
		btnNewButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnNewButton.setAlignmentY(0.0f);
		contentPane.add(btnNewButton);
		
		Component verticalStrut_2 = Box.createVerticalStrut(5);
		contentPane.add(verticalStrut_2);
		
		JButton btnNewButton_1 = new JButton("Delete Categorization");
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				JFileChooser chooser = new JFileChooser();
				File currentDir = new File(System.getProperty("user.dir") + "/cats");
				chooser.setCurrentDirectory(currentDir);
				FileNameExtensionFilter filter = new FileNameExtensionFilter(
						"CAT Files", "CAT");
				chooser.setFileFilter(filter);
				int returnVal = chooser.showOpenDialog(contentPane);
				if(returnVal == JFileChooser.APPROVE_OPTION) {
					File file = chooser.getSelectedFile();
					if((file.getPath().contains("\\Data-Analyzer\\cats\\") || file.getPath().contains("Data-Analyzer/cats")) && file.getName().contains(".cat")){
						try {
							if(file.delete()) {
								dispose();
								JOptionPane.showMessageDialog(null, "File was deleted");
								createMainWindow();
							}
							else
								JOptionPane.showMessageDialog(null, "File was not deleted, an unexpected error occurred");
						} catch(Exception e){
							JOptionPane.showMessageDialog(null, "File was not deleted, an unexpected error occurred");
						}
					}
					else{
						JOptionPane.showMessageDialog(null, "File was not deleted, the flie must be a .cat file in the \\cats folder");
					}
				}	
			}
		});
		btnNewButton_1.setAlignmentX(Component.CENTER_ALIGNMENT);
		contentPane.add(btnNewButton_1);

		Component verticalStrut_1 = Box.createVerticalStrut(20);
		contentPane.add(verticalStrut_1);
	}
}