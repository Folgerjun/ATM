package abc;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Enumeration;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;

import DB.DatabaseOperate;

class ComQuery extends JDialog implements ActionListener{	// 创建一个没有标题且没有指定Frame所有者的无模式对话框
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String[] ID = new String[3];
	String[] info = new String[3]; 
	String Balance,Amount;
	
	JPanel p1 = new JPanel();//JPanel 为javax.swing包中的，为面板容器，可以加入到JFrame中 ,
	//它自身是个容器，可以把其他compont加入到JPanel中,如JButton,JTextArea,JTextFiled等，另外也可以在它上面绘图
	JPanel p2 = new JPanel();
	JPanel p3 = new JPanel();
	JPanel p4 = new JPanel();
	JTextField num = new JTextField(15);//定义输入长度为15
	JButton ok = new JButton("确定");
	JButton cancel = new JButton("返回");
	
	public ComQuery(){
		setModal(true); 					// 设置模态
		setBackground(Color.LIGHT_GRAY);	// 设置背景色
		Container contentPane = this.getContentPane();
		contentPane.setLayout(new GridLayout(4,1));
		p2.add(new JLabel("请输入卡号"));
		p2.add(num);
		p3.add(ok);
		p3.add(cancel);
		
		num.addActionListener(this);
		ok.addActionListener(this);
		cancel.addActionListener(this);
		contentPane.add(p1);
		contentPane.add(p2);
		contentPane.add(p3);
		contentPane.add(p4);
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);// 设置自动关闭窗口
		setSize(500, 300);
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation((screen.width - 500) / 2, (screen.height - 300) / 2);
		setTitle("普通查询窗口");
		setResizable(false); 	// 不让用户改变窗口大小
		setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e){
		if(e.getSource() == ok){
			String[] a = new String[4];
			String[] b = new String[6];
			String[] c = new String[6];
			int d;
			DatabaseOperate MyComQuery = new DatabaseOperate();
			a = MyComQuery.login(num.getText().trim());
			b = MyComQuery.schbalance(num.getText().trim());
			c = MyComQuery.schamount(num.getText().trim());
			d = MyComQuery.schcredit(num.getText().trim());
			
			ID[0] = num.getText().trim();
			ID[1] = b[1];
			ID[2] = c[1];
			info[0] = a[0];
			info[1] = a[1];
			info[2] = Integer.toString(d);// 强制转换类型
			Balance = b[5];
			Amount = c[5];
			
			new QueryResult(ID,info,Balance,Amount);
		} else if (e.getSource() == cancel){
			dispose();
			new AdminFrame();
		}
	}
	
	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		JDialog.setDefaultLookAndFeelDecorated(true);
		Font font = new Font("JFrame", Font.PLAIN, 14);
		Enumeration keys = UIManager.getLookAndFeelDefaults().keys();
		while (keys.hasMoreElements()) {
			Object key = keys.nextElement();
			if (UIManager.get(key) instanceof Font)
				UIManager.put(key, font);
		}
		new BankSystem();
	}
	
}