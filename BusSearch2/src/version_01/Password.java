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
import javax.swing.JTextField;

public class Password { // 패스워드 찾기 기능을 구현하기 위한 프레임 구동부
	JFrame f;
	JLabel la_id, la_mail,la_pass;
	JTextField tf_id, tf_mail, tf_pass;
	JButton bt_ok,bt_back;
	Font f1 = new Font("돋음", Font.PLAIN, 15);
	
	Password(){
		f = new JFrame("비밀번호 찾기");
		la_id = new JLabel("ID를 입력해주세요.         :");
		la_mail = new JLabel("이메일을 입력해주세요.  :");
		la_pass = new JLabel("비밀번호가 출력됩니다.  :");
		tf_id = new JTextField();
		tf_mail = new JTextField();
		tf_pass = new JTextField();
		bt_ok = new JButton("찾 기");
		bt_back = new JButton("뒤로가기");
	}
	
	void font() {
		la_id.setFont(f1);
		tf_id.setFont(f1);
		la_mail.setFont(f1);
		tf_mail.setFont(f1);
		la_pass.setFont(f1);
		tf_pass.setFont(f1);
		bt_ok.setFont(f1);
		bt_back.setFont(f1);
	}
	
	
	void setBounds() {
		la_id.setBounds(50, 20, 200, 50);
		tf_id.setBounds(250, 20, 200, 50);
		la_mail.setBounds(50, 70, 200, 50);
		tf_mail.setBounds(250, 70, 200, 50);
		la_pass.setBounds(50,250,200,50);
		tf_pass.setBounds(250, 250, 200, 50);
		bt_ok.setBounds(180, 150, 100, 50);
		bt_back.setBounds(180, 370, 100, 50);
		
	}
	
	void run() {
		this.eventUp();
		this.font();
		this.setBounds();
		this.FrameAdd();
		this.frameSet();
	}

	void FrameAdd() {
		f.setLayout(null);
		f.add(la_id);
		f.add(tf_id);
		f.add(la_mail);
		f.add(tf_mail);
		f.add(la_pass);
		f.add(tf_pass);
		f.add(bt_ok);
		f.add(bt_back);
	}
	
	void frameSet() {
		f.setSize(500, 500);
		f.setVisible(true);
		f.setLocationRelativeTo(null);
		f.setLayout(null);
		f.setResizable(false);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	void eventUp() {
		bt_ok.addActionListener(new ActionListener() { // 버튼 입력 시 비밀번호 찾기 기능 실행
			public void actionPerformed(ActionEvent e) {
				try {
					String id = tf_id.getText(); // TF상의 정보를 저장하는 지역변수
					String mail = tf_mail.getText();
					String sign_ID = ""; // DB상의 정보를 저장하기 위한 지역변수
					String sign_MAIL = "";
					String sign_pass = "";
					
					MemberDAO dao = new MemberDAO();
					ArrayList<MemberVo> passFind = dao.passFind(id,mail); // ID와 EMAIL을 전송
					
					for(int i=0;i<passFind.size();i++) { //리턴된 data의 정보를 파싱
						MemberVo data = (MemberVo) passFind.get(i);
						sign_ID = data.getSINGID();
						sign_pass = data.getSIGNPASS();
						sign_MAIL = data.getEMAIL();
					}
					if(id.equals(sign_ID)&&mail.equals(sign_MAIL)) {//DB상의 정보와 입력받은 정보가 같을 경우
						JOptionPane.showMessageDialog(null, "비밀번호 찾기에 성공하였습니다.");
						tf_pass.setText(sign_pass);
						
					}
					
				}catch(Exception e2) { // 정보가 일치하지 않거나, 오류가 발생할 경우
					JOptionPane.showMessageDialog(null,"아이디, 혹은 E-MAIL이 잘못되었습니다." ,"비밀번호 찾기 실패",JOptionPane.ERROR_MESSAGE);
					tf_id.setText("");
					tf_mail.setText("");
				}
				
			}
		});
		
		bt_back.addActionListener(new ActionListener() { // 비밀번호 찾기 프레임에서, 타이틀 프레임으로 이동
			public void actionPerformed(ActionEvent e) {
				LoginFrame lf = new LoginFrame();
				lf.run();
				f.dispose();
				
			}
		});
		
		tf_mail.addKeyListener(new KeyAdapter() { //텍스트 필드에서 엔터키를 이용하여 찾기 버튼 활성화
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == 10) {
					bt_ok.doClick();
				}
			}
		});
		
		tf_id.addKeyListener(new KeyAdapter() { // 패스워드필드에서 엔터키를 입력받을 경우, 확인 버튼이 활성화
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == 10) {
					tf_mail.requestFocus(true);
				}
			}
		});
	}
}
