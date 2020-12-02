package version_01;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

@SuppressWarnings("serial")

public class LoginFrame extends JFrame {
	
	private JFrame frame;
	private JPanel jp_1, jp_2, jp_3;
	private JLabel la_title, la_ID, la_PASS;
	private JButton bt_AD, bt_FIN;
	private JButton bt_OK;
	private JTextField tf_ID;
	private JPasswordField pass;
	private Font f1;
	private ImageIcon I_title, I_ID, I_PASS, I_OK;
	private Color c;
	private String ID;
	

	public LoginFrame() { //������� �ʱ�ȭ
		frame = new JFrame("�α��� ȭ��");
		I_title = new ImageIcon(Main.class.getResource("..//Image/title.png"));
		I_ID = new ImageIcon(Main.class.getResource("../Image/ID.png"));
		I_PASS = new ImageIcon(Main.class.getResource("../Image/Pass.png"));
		I_OK = new ImageIcon(Main.class.getResource("../Image/login.png"));
		la_title = new JLabel(I_title);
		la_PASS = new JLabel(I_PASS);
		la_ID = new JLabel(I_ID);
		bt_OK = new JButton(I_OK);
		bt_AD = new JButton("ȸ������");
		bt_FIN = new JButton("ã ��");
		tf_ID = new JTextField(15);
		tf_ID.setLocation(100, 100);
		pass = new JPasswordField(15);
		jp_1 = new JPanel();
		jp_2 = new JPanel();
		jp_3 = new JPanel();
		f1 = new Font("����", Font.PLAIN, 15);
		c = new Color(051, 153, 051);

	}

	String getID() {
		return ID;
	}
	
	public void run() { // ������ Ȱ��ȭ
		frame.getContentPane().setBackground(c);
		frame.setSize(600, 1000);
		frame.setLocationRelativeTo(null);
		frame.setLayout(null);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		this.setBounds();
		this.SetFont();
		this.PanelAdd();
		this.FrameAdd();
		this.eventUp();

	}
	
	void SetFont() { // ��Ʈ ����
		tf_ID.setFont(f1);
		pass.setFont(f1);
		la_ID.setFont(f1);
		la_PASS.setFont(f1);
	}

	void setBounds() { // �����̳� ��ġ ����
		bt_OK.setBounds(220, 450, 150, 150);
		bt_OK.setBorderPainted(false);
		bt_OK.setContentAreaFilled(false);
		bt_OK.setOpaque(false);
		bt_AD.setBounds(100, 800, 100, 100);
		bt_FIN.setBounds(400, 800, 100, 100);
		jp_1.setBounds(100, 300, 400, 50);
		jp_2.setBounds(100, 350, 400, 50);
		jp_1.setBackground(c);
		jp_2.setBackground(c);

		la_title.setBounds(0, 50, 600, 100);
	}

	void PanelAdd() { //�гο� �����̳� ����
		jp_1.add(la_ID);
		jp_1.add(tf_ID);
		jp_2.add(la_PASS);
		jp_2.add(pass);
		jp_3.setBackground(c);

	}

	void FrameAdd() { // �����ӿ� �г� ����
		frame.add(la_title);
		frame.add(jp_1);
		frame.add(jp_2);
		frame.add(bt_AD);
		frame.add(bt_FIN);
		frame.add(bt_OK);
		frame.setVisible(true);

	}

	void eventUp() {
		bt_OK.addActionListener(new ActionListener() { // Ȯ�ι�ư�� ������ �α��� �Ǵ� ��� ����
			public void actionPerformed(ActionEvent e) {
				try {
					String id = tf_ID.getText(); // �ؽ�Ʈ �ʵ� ���� ID
					String pw = new String(pass.getPassword()); //�ؽ�Ʈ �ʵ� ���� PW
					String login_ID = ""; //�����ͺ��̽� ���� ID�� ������ ����
					String login_PASS = ""; // �����ͺ��̽� ���� PW�� ������ ����
					ID = id;
					
					
					MemberDAO dao = new MemberDAO();
					ArrayList<MemberVo> login = dao.login(id,pw);
				
					for(int i=0;i<login.size();i++) { //������ ���̽� ���� ������ �Ľ��Ͽ� ����
						MemberVo data = (MemberVo) login.get(i);
						login_ID = data.getID();
						login_PASS = data.getPASSWORD();
						}
				
					if(id.equals(login_ID)&&pw.equals(login_PASS)) { //������ ���̽� ���� ������ �Է¹��� ������ ���� ��� ���� ���α׷� ����
						JOptionPane.showMessageDialog(null,"�α��ο� �����Ͽ����ϴ�.");
						SearchFrame sf = new SearchFrame(ID);
						sf.Run();
						frame.dispose();
					}else { // �ٸ� ��� ���â
						JOptionPane.showMessageDialog(null,"���̵�, Ȥ�� �н����尡 �߸��Ǿ����ϴ�." ,"�α��� ����",JOptionPane.ERROR_MESSAGE);
					}
				}catch(Exception e1) {
					e1.printStackTrace();
					JOptionPane.showMessageDialog(null,"���̵�, Ȥ�� �н����尡 �߸��Ǿ����ϴ�." ,"�α��� ����",JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		pass.addKeyListener(new KeyAdapter() { // �н������ʵ忡�� ����Ű�� �Է¹��� ���, Ȯ�� ��ư�� Ȱ��ȭ
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == 10) {
					bt_OK.doClick();
				}
			}
		});
		
		bt_AD.addActionListener(new ActionListener() { // ��ư�� ������� ȸ������ �������� Ȱ��ȭ
			public void actionPerformed(ActionEvent e) {
				System.out.println("clicked!");
				SignUp lg = new SignUp();
				lg.run();
				frame.dispose();
			}
		});
		
		bt_FIN.addActionListener(new ActionListener() { // ��ư�� ������� ��й�ȣ ã�� �������� Ȱ��ȭ
			public void actionPerformed(ActionEvent e) {
				System.out.println("clicked!");
				Password pw = new Password();
				pw.run();
				frame.dispose();
			}
		});
	}
}
