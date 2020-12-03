package version_01;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

@SuppressWarnings("serial")
public class TabClass2 extends JFrame {
	JTable table1, table2;
	JScrollPane scroll1, scroll2;
	JTextField tf_sr;
	JLabel jl1,la1,la2;
	JButton bt_ok, bt_fa, bt_del, bt_go, bt_fa2, bt_go2,bt_re,bt_all, bt_all2;
	JPanel p_main, p1, p2, p3, p4, cardp, cardp2;
	Font f1;
	CardLayout card;
	String ID;
	String[] title = { "번 호", "출 발 지", "목 적 지", "날 짜" };
	String[][] data;
	DefaultTableModel model1, model2;
	boolean table = true, panel = true, alpa;

	public TabClass2(String ID) {
		tf_sr = new JTextField();
		jl1 = new JLabel("검 색");
		bt_ok = new JButton("등 록");
		bt_fa = new JButton("즐겨찾기");
		bt_fa2 = new JButton("기 록");
		bt_del = new JButton("삭 제");
		bt_go = new JButton("찾 기");
		bt_go2 = new JButton("찾 기");
		bt_re = new JButton();
		bt_all = new JButton("초 기 화");
		bt_all2 = new JButton("초 기 화");
		p_main = new JPanel();
		p1 = new JPanel();
		p2 = new JPanel();
		p3 = new JPanel();
		p4 = new JPanel();
		cardp = new JPanel();
		cardp2 = new JPanel();
		f1 = new Font("돋음", Font.PLAIN, 20);
		card = new CardLayout();
		this.ID = ID;

		model2 = new DefaultTableModel(title, 0) {
			public boolean isCellEditable(int i, int c) {
				return false;
			}
		};
		model1 = new DefaultTableModel(title, 0) {
			public boolean isCellEditable(int i, int c) {
				return false;
			}
		};

		this.Pmain();
	}
	
	void Print() {
		try {
		ImageIcon icon = new ImageIcon(Main.class.getResource("../Image/green.png"));
		Image img = icon.getImage();
		Image changeImg = img.getScaledInstance(1500, 180, Image.SCALE_SMOOTH);
		ImageIcon changIcon = new ImageIcon(changeImg);
		la1 = new JLabel(changIcon);
		la1.setBounds(0, 720, 1500, 200);
		p_main.add(la1);
		icon = new ImageIcon(Main.class.getResource("../Image/LOGO.png"));
		img = icon.getImage();
		changeImg = img.getScaledInstance(1500, 200, Image.SCALE_SMOOTH);
		changIcon = new ImageIcon(changeImg);
		la2 = new JLabel(changIcon);
		la2.setBounds(0, 10, 1500, 180);
		p_main.add(la2);
		
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	void Panel1() {
		if (table != true)
			table = !table;
		table1 = new JTable(model1);
		scroll1 = new JScrollPane(table1);
		scroll1.setPreferredSize(new Dimension(800, 500));

		table1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table1.getColumn("번 호").setPreferredWidth(90);
		table1.getColumn("출 발 지").setPreferredWidth(248);
		table1.getColumn("목 적 지").setPreferredWidth(248);
		table1.getColumn("날 짜").setPreferredWidth(200);

		table1.setRowHeight(40);
		table1.setFont(f1);

		table1.getTableHeader().setReorderingAllowed(false);
		table1.getTableHeader().setResizingAllowed(false);

		p1.add(scroll1);
		p1.setBounds(0, 0, 800, 600);
		table1.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				JTable jtable = (JTable) e.getSource();
				int row = jtable.getSelectedRow();
				int col = jtable.getSelectedColumn();
				DefaultTableModel model = (DefaultTableModel) table1.getModel();

				System.out.println(model.getValueAt(row, 0));
				System.out.println(model.getValueAt(row, col));
			}
		});
		table1.getTableHeader().setReorderingAllowed(false);
		table1.getTableHeader().setResizingAllowed(false);

		DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();
		dtcr.setHorizontalAlignment(SwingConstants.CENTER);
		TableColumnModel tcm = table1.getColumnModel();
		for (int i = 0; i < tcm.getColumnCount(); i++) {
			tcm.getColumn(i).setCellRenderer(dtcr);
		}
	}

	void Panel2() {
		if (table != false)
			table = !table;

		table2 = new JTable(model2);
		scroll2 = new JScrollPane(table2);
		scroll2.setPreferredSize(new Dimension(802, 500));

		table2.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table2.getColumn("번 호").setPreferredWidth(90);

		table2.getColumn("출 발 지").setPreferredWidth(248);
		table2.getColumn("목 적 지").setPreferredWidth(248);
		table2.getColumn("날 짜").setPreferredWidth(200);

		table2.setRowHeight(40);
		table2.setFont(f1);

		table2.getTableHeader().setReorderingAllowed(false);
		table2.getTableHeader().setResizingAllowed(false);

		p2.add(scroll2);
		p2.setBounds(0, 0, 800, 600);

		table2.getTableHeader().setReorderingAllowed(false);
		table2.getTableHeader().setResizingAllowed(false);

		DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();
		dtcr.setHorizontalAlignment(SwingConstants.CENTER);
		TableColumnModel tcm = table2.getColumnModel();
		for (int i = 0; i < tcm.getColumnCount(); i++) {
			tcm.getColumn(i).setCellRenderer(dtcr);
		}
	}

	void Panel3() {
		if (panel != true)
			panel = !panel;
		p3.setLayout(null);
		bt_ok.setBounds(200, 0, 200, 70);
		bt_fa.setBounds(200, 135, 200, 70);
		bt_go.setBounds(200, 285, 200, 70);
		bt_all.setBounds(200, 435, 200, 70);

		p3.add(bt_ok);
		p3.add(bt_fa);
		p3.add(bt_go);
		p3.add(bt_all);
	}

	void Panel4() {
		if (panel != false)
			panel = !panel;
		p4.setLayout(null);
		bt_del.setBounds(200, 0, 200, 70);
		bt_fa2.setBounds(200, 135, 200, 70);
		bt_go2.setBounds(200, 285, 200, 70);
		bt_all2.setBounds(200, 435, 200, 70);

		p4.add(bt_del);
		p4.add(bt_fa2);
		p4.add(bt_go2);
		p4.add(bt_all2);
	}

	void Panelcard() {

		cardp.setLayout(card);
		cardp.setBounds(100, 200, 800, 600);
		cardp.add(p1, "Record");
		cardp.add(p2, "favorit");
	}

	void Panelcard2() {
		cardp2.setLayout(card);
		cardp2.setBounds(900, 200, 500, 600);
		cardp2.add(p3, "Record");
		cardp2.add(p4, "favorit");
	}

	void Pmain() {
		p_main.setLayout(null);
		bt_re.setBounds(200, 600, 100, 100);
		bt_re.setVisible(false);
		p_main.add(bt_re);
		
		this.Print();
		this.EventUp();
		bt_re.doClick();
		this.Panel1();
		this.Panel2();
		this.Panel3();
		this.Panel4();
		this.Panelcard();
		this.Panelcard2();
		p_main.add(cardp);
		p_main.add(cardp2);
		p_main.setBounds(10, 10, 1400, 1000);
	}

	void ReverseCard() {
		if (table == false && panel == false) {
			card.previous(cardp);
			card.previous(cardp2);
		} else {
			card.next(cardp);
			card.next(cardp2);
		}
	}

	void EventUp() {
		bt_fa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				model2.setNumRows(0);
				ReverseCard();
				try {
					RecodDAO dao = new RecodDAO();
					ArrayList<RecodVO> rec2 = dao.rec2(ID);
					for (int i = 0; i < rec2.size(); i++) {
						RecodVO Data = (RecodVO) rec2.get(i);
						data = Data.getResult();
					}
					if (data == null) {
						JOptionPane.showMessageDialog(null, "검색기록이 없습니다.", "불러오기 실패", JOptionPane.ERROR_MESSAGE);
					} else {

						for (int i = data.length-1; i >= 0; i--) {
							Object[] mod = new Object[data[0].length];
							for (int y = 0; y < data[i].length; y++) {
								mod[y] = data[i][y];
							}
							model2.addRow(mod);
						}

					}
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, "시스템 오류, 즐겨찾기 불러오기 실패", "불러오기 실패", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		bt_fa2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				model1.setNumRows(0);
				ReverseCard();
				bt_re.doClick();
			}
		});
		
		bt_re.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				model1.setNumRows(0);
				try {
					RecodDAO dao = new RecodDAO();
					ArrayList<RecodVO> rec = dao.rec(ID);
					for (int i = 0; i < rec.size(); i++) {
						RecodVO Data = (RecodVO) rec.get(i);
						data = Data.getResult();
					}
					if (data == null) {
						JOptionPane.showMessageDialog(null, "검색기록이 없습니다.", "불러오기 실패", JOptionPane.ERROR_MESSAGE);
					} else {
						
						for (int i = data.length-1; i >= 0; i--) {
							Object[] mod = new Object[data[0].length];
							for (int y = 0; y < data[i].length; y++) {
								mod[y] = data[i][y];
							}
							model1.addRow(mod);
						}
						//이 부분에 TabClass2 bt_re 버튼을 누르는 동작 추가---------------------------------------------------------------------------------------
					}
				} catch (Exception e2) {
					e2.printStackTrace();
					JOptionPane.showMessageDialog(null, "기록이 없거나, 오류입니다.", "검색 기록 없음", JOptionPane.YES_OPTION);
				}
			}
		});
		
		bt_ok.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				String start = (String) table1.getValueAt(table1.getSelectedRow(), 1);
				String goal = (String) table1.getValueAt(table1.getSelectedRow(), 2);
				String id = ID;
				RecodDAO dao = new RecodDAO();
				dao.Insert(start, goal, id);
			}
		});
		
		bt_go.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				SearchFrame sf = new SearchFrame(ID);
				sf.tab.getSelectedIndex();
				
			}
		});
		
		bt_del.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				String start = (String) table2.getValueAt(table2.getSelectedRow(), 1);
				String goal = (String) table2.getValueAt(table2.getSelectedRow(), 2);
				String time = (String) table2.getValueAt(table2.getSelectedRow(), 3);
				String id = ID;
				RecodDAO dao = new RecodDAO();
				dao.Delite(start, goal, id, time);
			}
		});
		
		bt_all.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				RecodDAO dao = new RecodDAO();
				dao.Delall();
				bt_re.doClick();
			}
		});
		bt_all2.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				RecodDAO dao = new RecodDAO();
				dao.Delall2();
				bt_re.doClick();
			}
		});
	}
}
