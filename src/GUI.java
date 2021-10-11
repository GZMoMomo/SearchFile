package pane;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.eltima.components.ui.DatePicker;

public class GUI {

	public static void main(String[] args) {
		
		
		
		JFrame f=new JFrame("lol");
		f.setSize(450,300);
		f.setLocation(200, 200);
		f.setLayout(null);
		
		final DatePicker datepick;
		datepick=getDatePicker();
	    
		f.add(datepick);
		
		JButton b=new JButton("统计在E:/JAVA目录下，修改时间大于控件日期的文件总数");
		b.setBounds(20, 183, 400, 30);
		
		f.add(b);
		
		b.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int i=0;
				Date d0=new Date();
				d0=(Date) datepick.getValue();
				File fi=new File("E:\\JAVA");
				LinkedList list=new LinkedList();
				list=SFile(fi);
				for(Object f:list) {
					Long f1=Long.parseLong(f.toString());
					Date d=new Date(f1);
					if(d.before(d0)) {
						i++;
					}
				}
				JOptionPane jop=new JOptionPane();
				jop.showMessageDialog(f, "修改时间大于控件日期的文件总数为："+i);
				
			}
		});
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
	}
	
	private static DatePicker getDatePicker() {
		final DatePicker datepick;
		String DefaultFormat="yyyy-MM-dd HH:mm:ss";
		Date date=new Date();
		Font font=new Font("Times New Roman",Font.BOLD,14);
		Dimension dimension=new Dimension(177,24);
		int[] hilightDays= {1,3,5,7};
		int[] disabledDays= {4,6,5,9};
		datepick=new DatePicker(date,DefaultFormat,font,dimension);
		datepick.setLocation(137, 83);
		datepick.setBounds(137, 83, 177, 24);
		datepick.setHightlightdays(hilightDays, Color.red);
		datepick.setDisableddays(disabledDays);
		datepick.setLocale(Locale.CHINA);
		datepick.setTimePanleVisible(true);
		return datepick;
	}
	static LinkedList list=new LinkedList();
	private static LinkedList SFile(File file) {
		
		if(file.exists()) {
			File[] files=file.listFiles();
			if(null!=files) {
				for(File f:files) {
					if(f.isDirectory()) {
						SFile(f);
					}else {
						long time=f.lastModified();
					    list.add(time);
					    
					    
				}
			}
			
		}
		
	}else {
		System.out.println("文件不存在");
	}
		return list;
}
	}
