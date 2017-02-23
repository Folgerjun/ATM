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
import javax.swing.UIManager;

class MainFrame extends JDialog implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JPanel p1 = new JPanel();
	JPanel p2 = new JPanel();
	JPanel p3 = new JPanel();
	
	JLabel label1 = new JLabel("欢迎使用银行管理系统");

	JButton deposit = new JButton("存款");
	JButton get = new JButton("取款");
	JButton revise = new JButton("修改密码");
	JButton transfer = new JButton("转账");
	JButton exit = new JButton("取回磁卡");

	public MainFrame() {
		setModal(true); // 设置模态
		setBackground(Color.LIGHT_GRAY);// 设置背景色
		Container contentPane = this.getContentPane();// 取出内容面板(获取当前类的窗口)
		contentPane.setLayout(new GridLayout(3, 1));
		label1.setFont(new Font("Sans Serif", Font.BOLD, 20));// 设置字体

		p2.add(label1);
		p3.add(new JLabel("请选择操作："));
		p3.add(deposit);//存款
		p3.add(get);//取款
		p3.add(revise);//修改密码
		p3.add(transfer);//转账
		p3.add(exit); // 将组件添加到中间容器（取回磁条）
		deposit.addActionListener(this);
		get.addActionListener(this);
		revise.addActionListener(this);
		transfer.addActionListener(this);
		exit.addActionListener(this);
		contentPane.add(p1); // 将面板添加到内容面板
		contentPane.add(p2);
		contentPane.add(p3);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);// 设置自动关闭窗口
		setSize(500, 300);
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation((screen.width - 500) / 2, (screen.height - 300) / 2);
		setTitle("主操作窗口");
		setResizable(false); // 不让用户改变窗口大小
		setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == deposit) {
			dispose();
			new DepositFrame();
		} else if (e.getSource() == get) {
			dispose();
			new GetFrame();
		} else if (e.getSource() == revise) {
			dispose();
			new RevisePwd();
		}else if (e.getSource() == transfer) {
		dispose();
			new TransferFrame();
		} else if (e.getSource() == exit) {
			dispose();
			new BankSystem();
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