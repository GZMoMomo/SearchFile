package pane;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class tool {

	public static void main(String[] args) {
	   JFrame f=new JFrame("lol");
	   f.setSize(400, 300);
	   f.setLocation(200, 200);
	   f.setLayout(new BorderLayout());
	   
	   final HeroTableModel htm=new HeroTableModel();
	   final JTable t=new JTable(htm);
	   t.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	   t.getSelectionModel().setSelectionInterval(0, 0);
	   JPanel p=new JPanel();
	   
	   JLabel lName=new JLabel("名称");
	   JTextField jtName=new JTextField("");
	   JLabel lHp=new JLabel("血量");
	   JTextField jtHp=new JTextField("");
	   JButton bAdd=new JButton("增加");
	   jtName.setPreferredSize(new Dimension(80,30));
	   jtHp.setPreferredSize(new Dimension(80,30));
	   p.add(lName);p.add(jtName);p.add(lHp);p.add(jtHp);
	   p.add(bAdd);
	   
	   bAdd.addActionListener(new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			HeroDao dao=new HeroDao();
			Hero h=new Hero();
			
			String name=jtName.getText();
			if(name.length()==0) {
				JOptionPane.showMessageDialog(f, "名称不能为空");
				jtName.grabFocus();
				return;
			}
			String hp=jtHp.getText().trim();
			try {
				Float.parseFloat(hp);
			}catch(NumberFormatException e1) {
				JOptionPane.showMessageDialog(f, "血量只能是小数");
				jtHp.grabFocus();
				return;
			}
			h.name=name;
			h.hp=Float.parseFloat(hp);
			
			dao.add(h);
			htm.heros=dao.list();
			t.updateUI();
			t.getSelectionModel().setSelectionInterval(0, 0);
		}
		   
	   });
	   
	   JScrollPane sp=new JScrollPane(t);
	   f.add(p,BorderLayout.NORTH);
	   f.add(sp,BorderLayout.CENTER);
	   f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	   f.setVisible(true);
	}

}
