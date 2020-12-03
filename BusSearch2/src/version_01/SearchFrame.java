package version_01;

import java.awt.Font;
import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.swing.*;

@SuppressWarnings("serial")
public class SearchFrame extends JFrame { // ����ȭ���� ���밡 �Ǵ� Ŭ����, �ܱ���� �����Ǿ� �ִ�.
	private JFrame f;
	TabClass tc1;
	TabClass2 tc2;
	JTabbedPane tab;
	JLabel la1, la2;
	String ID;
	Font f1 = new Font("����", Font.PLAIN, 20);
	BufferedImage ii;
	Image dimg;


	
	

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
	
	void Default_Image() { // ī�巹�̾ƿ����� �̸� ��������, �ʿ��� �κ��� ���߿� �ҷ����� ������ ������ �����غ���.
		try {
			String str = "10000";
		Imageclass ic = new Imageclass(str);
		ii = ImageIO.read(ic.getImage());

		dimg = ii.getScaledInstance(la1.getWidth(), la1.getHeight(), Image.SCALE_SMOOTH);
		ImageIcon Ii = new ImageIcon(dimg);
		la1 = new JLabel(Ii);
		la1.setBounds(0, 700, 1500, 300);
		f.add(la1);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
