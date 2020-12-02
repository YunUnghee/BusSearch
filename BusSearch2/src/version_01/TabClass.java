package version_01;

import java.awt.*;

import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.table.*;

@SuppressWarnings("serial")
public class TabClass extends JFrame { // 버스찾기 기능을 구현한 탭 클레스.
	
	Image dimg;
	BufferedImage ii;
	JTable table;
	JScrollPane scroll1, scroll2;
	JTextArea ta1;
	JTextField tf_st, tf_go, tf_sn;
	JLabel jl1, jl2, jl3, il1, il2, il3;
	JButton bt_ex, bt_sc, bt_ch, bt_reco, bt_ok, bt_sc2;
	JButton bt_ch2, bt_reco2;
	JPanel p_main, p1, p2, p3, p4, cardp;
	Font f1;
	JCheckBox cb;
	JLabel l_title;
	CardLayout card;
	String[] bus_info = { "번 호", "버 스 번 호" };
	DefaultTableModel model;
	JComboBox<String> jcb1, jcb2, jcb3;
	recod info = new recod();
	String ID;
	boolean ch;

	TabClass(String ID) {
		scroll1 = new JScrollPane();
		ta1 = new JTextArea("세부정보 출력", 10, 50);
		jl1 = new JLabel("출 발 지");
		jl2 = new JLabel("목 적 지");
		jl3 = new JLabel("정류장 이름");
		il1 = new JLabel();
		bt_ex = new JButton("Exchange");
		bt_sc = new JButton("Search");
		bt_ch = new JButton("Change");
		bt_ch2 = new JButton("Change");
		bt_reco = new JButton("Record");
		bt_reco2 = new JButton("Record");
		bt_ok = new JButton("OK");
		bt_sc2 = new JButton("Search");
		p_main = new JPanel();
		p1 = new JPanel();
		p2 = new JPanel();
		p3 = new JPanel();
		p4 = new JPanel();
		jcb1 = this.Comboset(jcb1);
		jcb2 = this.Comboset(jcb2);
		jcb3 = this.Comboset(jcb3);
		cardp = new JPanel();
		f1 = new Font("돋음", Font.PLAIN, 20);
		cb = new JCheckBox();
		l_title = new JLabel("Bus Search");
		card = new CardLayout();
		model = new DefaultTableModel(bus_info, 0) {
			public boolean isCellEditable(int i, int c) {
				return false;
			}
		};
		this.Pmain();
		ch = true;
		this.ID = ID;
	}

	void Default_Image(String str) { // 카드레이아웃으로 미리 만들어놓고, 필요한 부분을 나중에 불러오는 식으로 구현을 생각해보자.
		try {
		Imageclass ic = new Imageclass(str);
		ii = ImageIO.read(ic.getImage());

		dimg = ii.getScaledInstance(il1.getWidth(), il1.getHeight(), Image.SCALE_SMOOTH);
		ImageIcon Ii = new ImageIcon(dimg);
		il2 = new JLabel(Ii);
		il2.setBounds(600, 150, 500, 200);
		p_main.add(il2);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	void Panel1() {
		jl1.setFont(f1);
		jl2.setFont(f1);

		table = new JTable(model);
		scroll1 = new JScrollPane(table);
		scroll1.setPreferredSize(new Dimension(500, 500));

		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

		table.getColumn("번 호").setPreferredWidth(100);
		table.getColumn("버 스 번 호").setPreferredWidth(397);

		table.setRowHeight(40);
		table.setFont(f1);

		table.getTableHeader().setReorderingAllowed(false);
		table.getTableHeader().setResizingAllowed(false);

		p1.add(scroll1);
		p1.setSize(500, 700);
		p1.setLocation(0, 200);

		table.addMouseListener(new MouseAdapter() {
			

			public void mouseClicked(MouseEvent e) {
				if (ch == true) {
					String str = (String) table.getValueAt(table.getSelectedRow(), 1);
					Default_Image(str);
					
					
					try {
						String info = str;
						String start = jcb1.getSelectedItem().toString();
						String goal = jcb2.getSelectedItem().toString();
						BusDAO dao = new BusDAO();
						
						ArrayList<BusVo> information = dao.information(info, start, goal);
						for (int i = 0; i < information.size(); i++) {
							BusVo data = (BusVo) information.get(i);
							bus_info = data.getResult();
						}
						if (bus_info == null) {
//					dao = new BusDAO();
//					ArrayList<BusVo> transinfo = dao.transinfo(info, start, goal);

						} else {
							String print = "";
							ta1.setText(print);
							for (int i = 0; i < bus_info.length; i++) {
								ta1.append(bus_info[i] + "\n");
								System.out.println(bus_info[i]);
							}
						}
					} catch (Exception e2) {
						JOptionPane.showMessageDialog(null, "검색결과가 없습니다.", "검색결과", JOptionPane.ERROR_MESSAGE);
					}
					
				} else {
					String str = (String) table.getValueAt(table.getSelectedRow(), 1);
					Default_Image(str);
					try {
						String info = str;
						BusDAO dao = new BusDAO();
						ArrayList<BusVo> stationAll = dao.stationAll(info);
						for (int i = 0; i < stationAll.size(); i++) {
							BusVo data = (BusVo) stationAll.get(i);
							bus_info = data.getResult();
						}
						String print = "";
						ta1.setText(print);
						for (int i = 0; i < bus_info.length; i++) {
							ta1.append(bus_info[i] + "\n");
							System.out.println(bus_info[i]);
						}
					} catch (Exception e3) {
						JOptionPane.showMessageDialog(null, "검색결과가 없습니다.", "검색결과", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});

		DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();
		dtcr.setHorizontalAlignment(SwingConstants.CENTER);
		TableColumnModel tcm = table.getColumnModel();
		for (int i = 0; i < tcm.getColumnCount(); i++) {
			tcm.getColumn(i).setCellRenderer(dtcr);
		}
	}

	void Panel2() {
		ta1.setFont(f1);
		p2.setLayout(null);
		scroll2 = new JScrollPane(ta1);
		scroll2.setLocation(0, 0);
		scroll2.setSize(500, 300);
		p2.add(scroll2);
		p2.setBounds(600, 400, 500, 500);
	}

	void Panel3() {
		if (ch == false) {
			ch = !ch;
		} else {
			ch = true;
		}
		p3.setLayout(null);
		jl1.setBounds(0, 0, 100, 50);
		jl2.setBounds(0, 100, 100, 50);
		jcb1.setBounds(0, 50, 200, 50);
		jcb2.setBounds(0, 150, 200, 50);
		bt_ex.setBounds(220, 100, 100, 50);
		bt_sc.setBounds(0, 250, 200, 50);
		bt_ch.setBounds(0, 350, 200, 50);
		bt_reco.setBounds(0, 450, 200, 50);

		p3.add(jl1);
		p3.add(jl2);
		p3.add(jcb1);
		p3.add(jcb2);
		p3.add(bt_ex);
		p3.add(bt_sc);
		p3.add(bt_ch);
		p3.add(bt_reco);

		p3.setBounds(0, 0, 500, 700);
	}

	void Panel4() {
		jl3.setFont(f1);
		if (ch == true) {
			ch = !ch;
		} else {
			ch = false;
		}
		p4.setLayout(null);
		p4.setBounds(0, 0, 500, 700);

		jl3.setBounds(0, 0, 200, 50);
		bt_sc2.setBounds(0, 250, 200, 50);
		bt_ch2.setBounds(0, 350, 200, 50);
		bt_reco2.setBounds(0, 450, 200, 50);
		jcb3.setBounds(0, 50, 200, 50);

		p4.add(jl3);
		p4.add(bt_sc2);
		p4.add(bt_ch2);
		p4.add(bt_reco2);
		p4.add(jcb3);

	}

	void Panelcard() {
		this.Panel4();
		this.Panel3();

		cardp.setLayout(card);
		cardp.setBounds(1150, 200, 500, 700);
		cardp.add(p3, "BusN");
		cardp.add(p4, "Station");
	}

	JComboBox<String> Comboset(JComboBox<String> box) {
		BusDAO dao = new BusDAO();
		String[] list = new String[0];

		ArrayList<BusVo> Station = dao.list();
		for (int i = 0; i < Station.size(); i++) {
			BusVo data = (BusVo) Station.get(i);
			list = data.getResult();
		}
		box = new JComboBox<String>(list);
		box.setEditable(true);
		return box;
	}

	void Pmain() {
		this.ButtonEvent();
		p_main.setLayout(null);
		il1.setBounds(600, 150, 500, 250);
		bt_ok.setBounds(400, 150, 100, 50);
		l_title.setBounds(0, 0, 1500, 120);
		p_main.add(l_title);
		ComboAgent agent1 = new ComboAgent(jcb1);
		ComboAgent agent2 = new ComboAgent(jcb2);
		ComboAgent agent3 = new ComboAgent(jcb3);
		
		String str = "9999";
		this.Default_Image(str);
		
		agent1.toString();
		agent2.toString();
		agent3.toString();

		this.Panel1();
		this.Panel2();
		this.Panelcard();
		p_main.add(il1);
		p_main.add(p1, BorderLayout.WEST);
		p_main.add(p2, BorderLayout.CENTER);
		p_main.add(cardp, BorderLayout.EAST);
		p_main.setBounds(10, 10, 1400, 1000);

	}

	void ButtonEvent() {

		bt_ex.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String start = jcb1.getSelectedItem().toString();
				String goal = jcb2.getSelectedItem().toString();

				jcb1.setSelectedItem(goal);
				jcb2.setSelectedItem(start);

			}
		});

		bt_ch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				model.setNumRows(0);

				if (ch = false) {
					card.previous(cardp);
				} else {
					card.next(cardp);
				}
			}
		});
		bt_ch2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				model.setNumRows(0);

				if (ch = false) {
					card.previous(cardp);
				} else {
					card.next(cardp);
				}
			}
		});

		bt_sc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String str = "9999";
				Default_Image(str);
				ta1.setText("");
				model.setNumRows(0);
				il1.setBounds(600, 150, 500, 200);
				p_main.add(il1);
				try {
					String start = jcb1.getSelectedItem().toString();
					String goal = jcb2.getSelectedItem().toString();

					info.setinfo(start, goal, ID);

					BusDAO dao = new BusDAO();
					ArrayList<BusVo> find = dao.find(start, goal);
					for (int i = 0; i < find.size(); i++) {
						BusVo data = (BusVo) find.get(i);
						bus_info = data.getResult();
					}
					if (bus_info == null) {
						JOptionPane.showMessageDialog(null, "검색결과가 없습니다.", "검색결과", JOptionPane.ERROR_MESSAGE);
					} else {
						JOptionPane.showMessageDialog(null, bus_info.length + " 개의 결과가 나왔습니다.");
						int i = 1;
						for (String N : bus_info) {
							String check = String.valueOf(i++);
							Object[] obj = { check, N };
							model.addRow(obj);
						}
					}
					
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, "검색결과가 없습니다.", "검색결과", JOptionPane.ERROR_MESSAGE);
				}
			}

		});

		bt_sc2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String str = "9999";
				Default_Image(str);
				ta1.setText("");
				model.setNumRows(0);
				try {
					String station = jcb3.getSelectedItem().toString();

					BusDAO dao = new BusDAO();
					ArrayList<BusVo> Station = dao.station(station);
					for (int i = 0; i < Station.size(); i++) {
						BusVo data = (BusVo) Station.get(i);
						bus_info = data.getResult();
					}
					if (bus_info == null) {
						JOptionPane.showMessageDialog(null, "검색결과가 없습니다.", "검색결과", JOptionPane.ERROR_MESSAGE);
					} else {
						JOptionPane.showMessageDialog(null, bus_info.length + " 개의 결과가 나왔습니다.");
						for (String N : bus_info) {
							String check = "";
							Object[] obj = { check, N };
							model.addRow(obj);
						}
					}
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, "검색결과가 없습니다.", "검색결과", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
	}

	class ComboAgent extends KeyAdapter {
		JComboBox<String> combo;
		JTextField editor;

		public ComboAgent(JComboBox<String> box) {
			this.combo = box;
			editor = (JTextField) combo.getEditor().getEditorComponent();
			editor.setFocusable(true);
			editor.setText("");
			editor.addKeyListener(this);
		}

		public void keyReleased(KeyEvent e) {
			char ch = e.getKeyChar();
			if (ch == KeyEvent.CHAR_UNDEFINED || Character.isISOControl(ch))
				return;

			int pos = editor.getCaretPosition();
			String str = editor.getText();

			if (str.length() == 0)
				return;
			for (int k = 0; k < combo.getItemCount(); k++) {
				String item = combo.getItemAt(k).toString();
				// 조건체크. 입력한 문자열이 리스트에 있는 아이템의 첫머리로 일치하는지
				if (item.startsWith(str)) {
					// 일치한다면 field에 매치된 아이템을 셋팅
					// 자동으로 완성된 부분을 선택표시로하여 강조
					editor.setText(item);
					editor.setCaretPosition(item.length());
					editor.moveCaretPosition(pos);
					break;
				}
			}
		}
	}
}
