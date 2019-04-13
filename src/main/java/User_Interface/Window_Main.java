package User_Interface;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JList;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import javax.swing.BoxLayout;
import java.awt.Component;
import java.awt.Font;
import java.awt.Color;
import javax.swing.AbstractListModel;
import javax.swing.border.MatteBorder;

import Objects.Categorized;

import java.awt.Cursor;
import javax.swing.Box;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.ScrollPane;

public class Window_Main extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void createMainWindow() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Window_Main frame = new Window_Main();
					frame.setTitle("Data Analyer");
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Window_Main() {
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
		
		JScrollPane scrollPane = new JScrollPane();
		JList list = new JList();
		list.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
		        JList<String> theList = (JList) e.getSource();
		        if (e.getClickCount() == 2) {
		          int index = theList.locationToIndex(e.getPoint());
		          if (index >= 0) {
		            Object o = theList.getModel().getElementAt(index);
		            //System.out.println("Double-clicked on: " + o.toString());
		            //TODO: have this open Window_Catergorized with the selected catagozired dataset
		            Categorized cat = new Categorized(o.toString(), o.toString());
		            Window_Categorized.createCategorizedWindow(cat);
		          }
		        }
			}
		});
		list.setModel(new AbstractListModel() {
			String[] values = new String[] {"2018-04-12", "2018-04-10", "2018-04-9", "2018-04-8", "2018-04-7"};
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
		
		Component verticalStrut_1 = Box.createVerticalStrut(20);
		contentPane.add(verticalStrut_1);
	}

}
