package version_01;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.Font;
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
	JLabel jl1;
	JButton bt_ok, bt_fa, bt_del, bt_go, bt_fa2, bt_go2,bt_re;
	JPanel p_main, p1, p2, p3, p4, cardp, cardp2;
	Font f1;
	CardLayout card;
	String ID;
	String[] title = { "�� ȣ", "�� �� ��", "�� �� ��", "�� ¥" };
	String[][] data;
	DefaultTableModel model1, model2;
	boolean table = true, panel = true, alpa;

	public TabClass2(String ID) {
		tf_sr = new JTextField();
		jl1 = new JLabel("�� ��");
		bt_ok = new JButton("�� ��");
		bt_fa = new JButton("���ã��");
		bt_fa2 = new JButton("�� ��");
		bt_del = new JButton("�� ��");
		bt_go = new JButton("ã ��");
		bt_go2 = new JButton("ã ��");
		bt_re = new JButton();
		p_main = new JPanel();
		p1 = new JPanel();
		p2 = new JPanel();
		p3 = new JPanel();
		p4 = new JPanel();
		cardp = new JPanel();
		cardp2 = new JPanel();
		f1 = new Font("����", Font.PLAIN, 20);
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

	void Panel1() {
		if (table != true)
			table = !table;
		table1 = new JTable(model1);
		scroll1 = new JScrollPane(table1);
		scroll1.setPreferredSize(new Dimension(800, 550));

		table1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table1.getColumn("�� ȣ").setPreferredWidth(90);
		table1.getColumn("�� �� ��").setPreferredWidth(248);
		table1.getColumn("�� �� ��").setPreferredWidth(248);
		table1.getColumn("�� ¥").setPreferredWidth(200);

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
		scroll2.setPreferredSize(new Dimension(802, 550));

		table2.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table2.getColumn("�� ȣ").setPreferredWidth(90);

		table2.getColumn("�� �� ��").setPreferredWidth(248);
		table2.getColumn("�� �� ��").setPreferredWidth(248);
		table2.getColumn("�� ¥").setPreferredWidth(200);

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
		bt_ok.setBounds(200, 50, 200, 100);
		bt_fa.setBounds(200, 250, 200, 100);
		bt_go.setBounds(200, 450, 200, 100);

		p3.add(bt_ok);
		p3.add(bt_fa);
		p3.add(bt_go);
	}

	void Panel4() {
		if (panel != false)
			panel = !panel;
		p4.setLayout(null);
		bt_del.setBounds(200, 50, 200, 100);
		bt_fa2.setBounds(200, 250, 200, 100);
		bt_go2.setBounds(200, 450, 200, 100);

		p4.add(bt_del);
		p4.add(bt_fa2);
		p4.add(bt_go2);
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
						JOptionPane.showMessageDialog(null, "�˻������ �����ϴ�.", "�ҷ����� ����", JOptionPane.ERROR_MESSAGE);
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
					JOptionPane.showMessageDialog(null, "�ý��� ����, ���ã�� �ҷ����� ����", "�ҷ����� ����", JOptionPane.ERROR_MESSAGE);
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
						JOptionPane.showMessageDialog(null, "�˻������ �����ϴ�.", "�ҷ����� ����", JOptionPane.ERROR_MESSAGE);
					} else {
						
						for (int i = data.length-1; i >= 0; i--) {
							Object[] mod = new Object[data[0].length];
							for (int y = 0; y < data[i].length; y++) {
								mod[y] = data[i][y];
							}
							model1.addRow(mod);
						}
						//�� �κп� TabClass2 bt_re ��ư�� ������ ���� �߰�---------------------------------------------------------------------------------------
					}
				} catch (Exception e2) {
					e2.printStackTrace();
					JOptionPane.showMessageDialog(null, "�ý��� ����, ��� �ҷ����� ����", "�ҷ����� ����", JOptionPane.ERROR_MESSAGE);
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
	}
}
