package abc;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

class AdminFrame extends JDialog implements ActionListener {/**/
	private static final long serialVersionUID = 1L;//取消警告
	JPanel p1 = new JPanel(); // 定义并建立面板
	JPanel p2 = new JPanel();
	JPanel p3 = new JPanel();
	JPanel p4 = new JPanel();
	JLabel label = new JLabel("欢迎进入管理员界面");
	JButton common_search = new JButton("普通查询");

	JButton open = new JButton("开户");
	JButton close = new JButton("销户");
	JButton back = new JButton("返回");

	public AdminFrame() {
		setModal(true); 				// 设置模态
		setBackground(Color.LIGHT_GRAY);// 设置背景色
		Container contentPane = this.getContentPane();// 取出内容面板
		contentPane.setLayout(new GridLayout(4, 1)); // 设置布局为5行1列
		label.setFont(new Font("Sans Serif", Font.BOLD, 20));
		
		p2.add(label);
		p3.add(new JLabel("请选择查询方式操作"));
		p3.add(common_search);
		p4.add(new JLabel("其他操作选择           "));
		p4.add(open);
		p4.add(close);
		p4.add(back);
		
		common_search.addActionListener(this);
		open.addActionListener(this);
		close.addActionListener(this);
		back.addActionListener(this);
		contentPane.add(p1); // 将面板添加到内容面板
		contentPane.add(p2);
		contentPane.add(p3);
		contentPane.add(p4);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);// 设置自动关闭窗口
		setSize(500, 300);
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation((screen.width - 500) / 2, (screen.height - 300) / 2);
		setTitle("管理员窗口");
		setResizable(false); // 不让用户改变窗口大小
		setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == open){// 单击开户
			dispose();
			new OpenAccount();
		} else if (e.getSource() == close){ // 单击销户
			dispose();
			new LogOffFrame();
		} else if (e.getSource() == back) {
			dispose();
			new BankSystem();
		} else if (e.getSource() == common_search){//查询
			dispose();
			new ComQuery();
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