package version_01;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class SignUp {
	private JFrame f = new JFrame("회원가입");
	JTextField sign = new JTextField(15);
	JTextField email = new JTextField(30);
	JPasswordField password = new JPasswordField(15);
	JLabel sign_id = new JLabel("I   D  :  ");
	JLabel sign_pass = new JLabel("PASS WORD  :  ");
	JLabel sign_email = new JLabel("E-MAIL  :  ");
	JButton sign_ok = new JButton("OK");
	JButton sign_cl = new JButton("CANCLE");
	JButton id_ch = new JButton("CHECK");
	Font f1 = new Font("돋음", Font.PLAIN, 15);

	{
		sign.setFont(f1);
		email.setFont(f1);
		sign_id.setFont(f1);
		sign_pass.setFont(f1);
		sign_ok.setFont(f1);
		sign_cl.setFont(f1);
		sign_email.setFont(f1);
		id_ch.setFont(f1);
	}

	void setBound() {

		sign_id.setBounds(127, 50, 100, 50);
		sign.setBounds(190, 50, 150, 50);

		sign_pass.setBounds(60, 150, 150, 50);
		password.setBounds(190, 150, 150, 50);

		sign_email.setBounds(110, 250, 100, 50);
		email.setBounds(190, 250, 150, 50);

		sign_ok.setBounds(100, 400, 100, 50);
		sign_cl.setBounds(300, 400, 100, 50);
		
		id_ch.setBounds(360, 50, 100, 50);

	}

	void addCon() {
		f.setLayout(null);
		f.add(email);
		f.add(sign);
		f.add(password);
		f.add(sign_id);
		f.add(sign_pass);
		f.add(sign_email);
		f.add(sign_ok);
		f.add(sign_cl);
		f.add(id_ch);
	}

	void run() {
		this.setBound();
		this.addCon();
		this.eventUp();

//		f.getContentPane().setBackground();
		f.setSize(500, 500);
		f.setVisible(true);
		f.setLocationRelativeTo(null);
		f.setLayout(null);
		f.setResizable(false);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	void eventUp() {
		sign_ok.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String id = sign.getText();
					String pass = new String(password.getPassword());
					String mail = email.getText();
					String sign_ID = "";

					MemberDAO dao = new MemberDAO();
					ArrayList<MemberVo> login = dao.signup(id, pass, mail);

					for (int i = 0; i < login.size(); i++) {
						MemberVo data = (MemberVo) login.get(i);
						sign_ID = data.getSINGID();
					}
					if (id.equals(sign_ID)) {
						JOptionPane.showMessageDialog(null, "회원가입에 성공하였습니다.");
						LoginFrame lf = new LoginFrame();
						lf.run();
						f.dispose();
					}

				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, "회원가입에 실패하였습니다.", "가입 실패", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		sign_cl.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				LoginFrame lf = new LoginFrame();
				lf.run();
				f.dispose();
			}
		});
		
		id_ch.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
			 try {	
				String id = sign.getText();
				String ch_id = "";
				
				MemberDAO dao = new MemberDAO();
				ArrayList<MemberVo> check = dao.idcheck(id);
				
				for (int i = 0; i < check.size(); i++) {
					MemberVo data = (MemberVo) check.get(i);
					ch_id = data.getID();
				}
				if(id.equals(ch_id)) {
					JOptionPane.showMessageDialog(null, id+"는 사용할 수 있는 ID 입니다.");
				}
			}catch(Exception e2) {
				JOptionPane.showMessageDialog(null, "이미 사용된 ID입니다.", "ID 중복", JOptionPane.ERROR_MESSAGE);
			}
			}
		});
		email.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == 10) {
					sign_ok.doClick();
				}
			}
		});
		sign.addKeyListener(new KeyAdapter() { // 패스워드필드에서 엔터키를 입력받을 경우, 확인 버튼이 활성화
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == 10) {
					id_ch.doClick();
					sign_pass.requestFocus(true);
				}
			}
		});
		sign_pass.addKeyListener(new KeyAdapter() { // 패스워드필드에서 엔터키를 입력받을 경우, 확인 버튼이 활성화
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == 10) {
					sign_email.requestFocus(true);
				}
			}
		});
	}
}
