package User_Interface;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import Objects.Categorized;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.BoxLayout;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import java.awt.CardLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Window_Catigorized extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void createCatigorizedWindow(final Categorized dataSet) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Window_Catigorized frame = new Window_Catigorized("Viewing Dataset");
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
	public Window_Catigorized() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblYouAreViewing = new JLabel("You are viewing Dataset: ");
		lblYouAreViewing.setBounds(76, 13, 147, 16);
		contentPane.add(lblYouAreViewing);
		
		JButton btnNewButton = new JButton("Output .CSV");
		btnNewButton.setBounds(12, 109, 116, 25);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Output .PDF");
		btnNewButton_1.setBounds(152, 109, 116, 25);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Merge Datasets");
		btnNewButton_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
			}
		});
		btnNewButton_2.setBounds(294, 109, 126, 25);
		contentPane.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("Back");
		btnNewButton_3.setBounds(160, 183, 97, 25);
		contentPane.add(btnNewButton_3);
	}
}
