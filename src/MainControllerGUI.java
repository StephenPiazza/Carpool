
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import org.jdesktop.swingx;

public class MainControllerGUI extends JFrame {
	/**
		 * 
		 */
	private static final long serialVersionUID = 1L;
	private JPanel p1;
	private JPanel p3;
	private JPanel p4;
	private JPanel p5;
	public static JTextField t2;
	JXDatePicker picker;
	private JButton createRide;
	private JButton createMember;
	private JLabel cheer;
	private JLabel carpool;
	private JButton search;
	private JButton update;
	private JButton delete;
	Font font;
	Font font1;

	public MainControllerGUI() {
		font = new Font("Arial", Font.PLAIN, 22);
		p1 = new JPanel();
		p3 = new JPanel();
		p4 = new JPanel();
		p5 = new JPanel();
		p1.setLayout(null);
		p3.setLayout(null);
		p4.setLayout(null);
		p5.setLayout(null);
		p1.setBackground(new Color(215, 215, 215));
		p3.setBackground(new Color(185, 185, 185));
		p4.setBackground(new Color(120, 135, 135));
		p5.setBackground(new Color(210, 210, 210));
		cheer = new JLabel("We are group 9 !!!");
		cheer.setFont(new Font("dialog", Font.ITALIC, 35));
		cheer.setForeground(Color.BLUE);
		search = new JButton();
		search.setText("Driver");
		search.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new DriverPickPassengerGUI().setVisible(true);
			}
		});

//		String path = "D:/Spring 2016/Res_management/carpool.png";
//		File file = new File(path);
//		BufferedImage image = null;
//		try {
//			image = ImageIO.read(file);
//		} catch (IOException e1) {
//			e1.printStackTrace();
//		}
		//carpool = new JLabel(new ImageIcon(image));
		update = new JButton("Update");
		update.setFont(font);
		update.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});

		createRide = new JButton();
		createRide.setText("Rider");
		createRide.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new RiderScheduleGUI().setVisible(true);
			}
		});

		createMember = new JButton();
		createMember.setText("Member");
		createMember.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new MemberAccountGUI().setVisible(true);
			}
		});

		delete = new JButton("Delete");
		delete.setFont(font);
		delete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new DeleteMemberGUI().setVisible(true);
			}
		});
		t2 = new JTextField("");
		t2.setFont(new Font("Arial", Font.BOLD, 75));
		t2.setForeground(Color.BLUE);
		t2.setHorizontalAlignment(JTextField.CENTER);
		t2.setBackground(new Color(210, 210, 210));
		Timer timer = new Timer(1000, new Listener());
		timer.start();
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		picker = new JXDatePicker();
		picker.setDate(Calendar.getInstance().getTime());
		picker.setFormats(new SimpleDateFormat("MM/dd/yyyy"));
		picker.setFont(new Font("Arial", Font.BOLD, 35));
		picker.setLinkPanel(null);
		JXMonthView monthview = picker.getMonthView();
		monthview.setFont(font);
		monthview.setBackground(new Color(239, 239, 239));
		JButton change = (JButton) picker.getComponent(1);
		//Image image1 = toolkit.getImage("D:/Spring 2016/Res_management/calendar.png");
		//ImageIcon icon = new ImageIcon(image1);
		ImageIcon icon = new ImageIcon();
		change.setIcon(icon);
		p1.add(cheer);
		p3.add(createMember);
		p3.add(createRide);
		p3.add(search);
		p3.add(update);
		p3.add(delete);
		//p4.add(carpool);
		p5.add(picker);
		p5.add(t2);
		p1.setBorder(new TitledBorder(new EtchedBorder(), ""));
		((javax.swing.border.TitledBorder) p1.getBorder()).setTitleFont(font);
		((javax.swing.border.TitledBorder) p1.getBorder()).setTitleColor(Color.BLUE);
		p3.setBorder(new TitledBorder(new EtchedBorder(), "Menu"));
		((javax.swing.border.TitledBorder) p3.getBorder()).setTitleFont(font);
		((javax.swing.border.TitledBorder) p3.getBorder()).setTitleColor(Color.BLUE);
		p4.setBorder(new TitledBorder(new EtchedBorder(), ""));
		((javax.swing.border.TitledBorder) p4.getBorder()).setTitleFont(font);
		((javax.swing.border.TitledBorder) p4.getBorder()).setTitleColor(Color.BLUE);
		p5.setBorder(new TitledBorder(new EtchedBorder(), "Date and time"));
		((javax.swing.border.TitledBorder) p5.getBorder()).setTitleFont(font);
		((javax.swing.border.TitledBorder) p5.getBorder()).setTitleColor(Color.BLUE);
		// ** The Absolute positioning of JLabel and JTextField **//

		Insets insets = this.getInsets();
		cheer.setBounds(330 + insets.left, 37 + insets.top, 700, 45);
		createMember.setBounds(15 + insets.left, 37 + insets.top, 190, 45);
		createMember.setFont(font);
		createRide.setBounds(237 + insets.left, 37 + insets.top, 180, 45);
		createRide.setFont(font);
		search.setBounds(452 + insets.left, 37 + insets.top, 120, 45);
		search.setFont(font);
		update.setBounds(603 + insets.left, 37 + insets.top, 120, 45);
		delete.setBounds(758 + insets.left, 37 + insets.top, 120, 45);
		//carpool.setBounds(410 + insets.left, 0 + insets.top, 120, 120);
		picker.setBounds(110 + insets.left, 46 + insets.top, 280, 45);
		t2.setBounds(450 + insets.left, 35 + insets.top, 340, 61);
		setTitle("Group 9 Presentation");
		// ImageIcon image3 = new ImageIcon("D:/Spring
		// 2016/Res_management/carpool.png");
		ImageIcon image3 = new ImageIcon();
		setIconImage(image3.getImage());
		setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
		getContentPane().add(p1);
		getContentPane().add(p3);
		getContentPane().add(p4);
		getContentPane().add(p5);
		setSize(900 + insets.left + insets.right, 500 + insets.top + insets.bottom);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
	}

	class Listener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			Calendar calendar = Calendar.getInstance();
			int hours = calendar.get(Calendar.HOUR_OF_DAY);
			int minutes = calendar.get(Calendar.MINUTE);
			int seconds = calendar.get(Calendar.SECOND);
			t2.setText(hours + ":" + minutes + ":" + seconds);
			update.setEnabled(false);
		}
	}
}
