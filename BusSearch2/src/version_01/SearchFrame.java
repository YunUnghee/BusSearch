package version_01;

import java.awt.Font;
import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.swing.*;

@SuppressWarnings("serial")
public class SearchFrame extends JFrame { // 메인화면의 뼈대가 되는 클레스, 텝기능이 구현되어 있다.
	private JFrame f;
	TabClass tc1;
	TabClass2 tc2;
	JTabbedPane tab;
	JLabel la1, la2;
	String ID;
	Font f1 = new Font("돋음", Font.PLAIN, 20);
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

	void SetFrame() { // 프레임만 존재하고, 다른 기능은 존재하지 않는다.v
		
		f.setSize(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
		f.setLocationRelativeTo(null);
		f.setLayout(null);
		f.setResizable(false);
		f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public JTabbedPane createTabbedPane() {	// 텝기능 구현부, 구현된 클래스의 패널을 프레임에 저장
		tc1 = new TabClass(ID);
		tc2 = new TabClass2(ID);
		
		tab = new JTabbedPane();
		tab.addTab("버스 탐색", tc1.p_main);
		tab.addTab("기록 / 즐겨찾기", tc2.p_main);
		tab.setBounds(0, 0, 1500, 1000);
		return tab;
	}
	
	void Default_Image() { // 카드레이아웃으로 미리 만들어놓고, 필요한 부분을 나중에 불러오는 식으로 구현을 생각해보자.
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
