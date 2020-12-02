package version_01;

import java.awt.Font;

import javax.swing.*;

@SuppressWarnings("serial")
public class SearchFrame extends JFrame { // 메인화면의 뼈대가 되는 클레스, 텝기능이 구현되어 있다.
	private JFrame f;
	TabClass tc1;
	TabClass2 tc2;
	JTabbedPane tab;
	String ID;
	Font f1 = new Font("돋음", Font.PLAIN, 20);
	
	

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
}
