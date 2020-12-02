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

public class Password { // �н����� ã�� ����� �����ϱ� ���� ������ ������
	JFrame f;
	JLabel la_id, la_mail,la_pass;
	JTextField tf_id, tf_mail, tf_pass;
	JButton bt_ok,bt_back;
	Font f1 = new Font("����", Font.PLAIN, 15);
	
	Password(){
		f = new JFrame("��й�ȣ ã��");
		la_id = new JLabel("ID�� �Է����ּ���.         :");
		la_mail = new JLabel("�̸����� �Է����ּ���.  :");
		la_pass = new JLabel("��й�ȣ�� ��µ˴ϴ�.  :");
		tf_id = new JTextField();
		tf_mail = new JTextField();
		tf_pass = new JTextField();
		bt_ok = new JButton("ã ��");
		bt_back = new JButton("�ڷΰ���");
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
		bt_ok.addActionListener(new ActionListener() { // ��ư �Է� �� ��й�ȣ ã�� ��� ����
			public void actionPerformed(ActionEvent e) {
				try {
					String id = tf_id.getText(); // TF���� ������ �����ϴ� ��������
					String mail = tf_mail.getText();
					String sign_ID = ""; // DB���� ������ �����ϱ� ���� ��������
					String sign_MAIL = "";
					String sign_pass = "";
					
					MemberDAO dao = new MemberDAO();
					ArrayList<MemberVo> passFind = dao.passFind(id,mail); // ID�� EMAIL�� ����
					
					for(int i=0;i<passFind.size();i++) { //���ϵ� data�� ������ �Ľ�
						MemberVo data = (MemberVo) passFind.get(i);
						sign_ID = data.getSINGID();
						sign_pass = data.getSIGNPASS();
						sign_MAIL = data.getEMAIL();
					}
					if(id.equals(sign_ID)&&mail.equals(sign_MAIL)) {//DB���� ������ �Է¹��� ������ ���� ���
						JOptionPane.showMessageDialog(null, "��й�ȣ ã�⿡ �����Ͽ����ϴ�.");
						tf_pass.setText(sign_pass);
						
					}
					
				}catch(Exception e2) { // ������ ��ġ���� �ʰų�, ������ �߻��� ���
					JOptionPane.showMessageDialog(null,"���̵�, Ȥ�� E-MAIL�� �߸��Ǿ����ϴ�." ,"��й�ȣ ã�� ����",JOptionPane.ERROR_MESSAGE);
					tf_id.setText("");
					tf_mail.setText("");
				}
				
			}
		});
		
		bt_back.addActionListener(new ActionListener() { // ��й�ȣ ã�� �����ӿ���, Ÿ��Ʋ ���������� �̵�
			public void actionPerformed(ActionEvent e) {
				LoginFrame lf = new LoginFrame();
				lf.run();
				f.dispose();
				
			}
		});
		
		tf_mail.addKeyListener(new KeyAdapter() { //�ؽ�Ʈ �ʵ忡�� ����Ű�� �̿��Ͽ� ã�� ��ư Ȱ��ȭ
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == 10) {
					bt_ok.doClick();
				}
			}
		});
		
		tf_id.addKeyListener(new KeyAdapter() { // �н������ʵ忡�� ����Ű�� �Է¹��� ���, Ȯ�� ��ư�� Ȱ��ȭ
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == 10) {
					tf_mail.requestFocus(true);
				}
			}
		});
	}
}
