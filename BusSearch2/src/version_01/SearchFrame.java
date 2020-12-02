package version_01;

import java.awt.Font;

import javax.swing.*;

@SuppressWarnings("serial")
public class SearchFrame extends JFrame { // ����ȭ���� ���밡 �Ǵ� Ŭ����, �ܱ���� �����Ǿ� �ִ�.
	private JFrame f;
	TabClass tc1;
	TabClass2 tc2;
	JTabbedPane tab;
	String ID;
	Font f1 = new Font("����", Font.PLAIN, 20);
	
	

	SearchFrame(String ID) {
		this.ID = ID;
		f = new JFrame("Bus Searh");
	}
	void Set() {
		tc1 = new TabClass(ID);
		tc2 = new TabClass2(ID);
	}
	void Run() {
		this.createTabbedPane();
		tab.setFont(f1);
		f.add(tab);
		this.SetFrame();
	}

	void SetFrame() { // �����Ӹ� �����ϰ�, �ٸ� ����� �������� �ʴ´�.v
		
		f.setSize(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
		f.setLocationRelativeTo(null);
		f.setLayout(null);
		f.setResizable(false);
		f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public JTabbedPane createTabbedPane() {	// �ܱ�� ������, ������ Ŭ������ �г��� �����ӿ� ����
		tc1 = new TabClass(ID);
		tc2 = new TabClass2(ID);
		
		tab = new JTabbedPane();
		tab.addTab("���� Ž��", tc1.p_main);
		tab.addTab("��� / ���ã��", tc2.p_main);
		tab.setBounds(0, 0, 1500, 1000);
		return tab;
	}
}
