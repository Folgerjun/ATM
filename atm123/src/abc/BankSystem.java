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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UIManager;

import DB.DatabaseOperate;

class BankSystem extends JDialog implements ActionListener {
	
	static String[] s = new String[4];
	
	private static final long serialVersionUID = 1L;
	JPanel p1 = new JPanel(); // 定义并建立面板
	JPanel p2 = new JPanel();
	JPanel p3 = new JPanel();
	JPanel p4 = new JPanel();
	JPanel p5 = new JPanel();
	JPanel p6 = new JPanel();
	JLabel label = new JLabel("请输入卡号和密码，管理员输入相应管理用户名和密码：");
	JTextField txtUserName = new JTextField(15); // 用户名文本框
	JPasswordField txtPwd = new JPasswordField(15);// 密码框
	JButton ok = new JButton("确定");
	JButton cancel = new JButton("取消");

	public BankSystem() {
		setModal(true); // 设置模态
		setBackground(Color.LIGHT_GRAY);// 设置背景色
		Container contentPane = this.getContentPane();// 取出内容面板
		contentPane.setLayout(new GridLayout(6, 1)); // 设置布局为5行1列
		label.setFont(new Font("Sans Serif", Font.BOLD, 15));
		p2.add(label);
		p3.add(new JLabel("用户名:"));
		p3.add(txtUserName); // 将组件添加到中间容器
		p4.add(new JLabel("密    码:"));
		p4.add(txtPwd); // 将组件添加到中间容器
		p5.add(ok);
		p5.add(cancel);
		ok.addActionListener(this);
		cancel.addActionListener(this);
		txtUserName.addActionListener(this);
		txtPwd.addActionListener(this);
		contentPane.add(p1); // 将面板添加到内容面板
		contentPane.add(p2);//初始化一个容器，用来在容器上添加一些控件
		contentPane.add(p3);
		contentPane.add(p4);
		contentPane.add(p5);
		contentPane.add(p6);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);// 设置自动关闭窗口(x号)
		setSize(500, 300);
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation((screen.width - 500) / 2, (screen.height - 300) / 2);
		setTitle("登录窗口");
		setResizable(false); // 不让用户改变窗口大小
		setVisible(true);
	}

	@SuppressWarnings("deprecation")
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == ok || e.getSource() == txtPwd) // 单击确定按钮后
		{
			String password = new String(txtPwd.getPassword());
			
			if ((txtUserName.getText().trim().equals("admin"))
					&& (password.equals("admin"))) {
				JOptionPane.showMessageDialog(null,
						"您好,Administrator!");
				dispose(); // 关闭登录窗口
				new AdminFrame(); // 调出管理员操作窗口
			} 
			else {
				DatabaseOperate myLogin = new DatabaseOperate();
				s = myLogin.login(txtUserName.getText().trim());
				if ((txtUserName.getText().trim().equals(s[2].trim()))
						&& (password.equals(s[3].trim()))) {
					JOptionPane.showMessageDialog(null, "欢迎普通用户，登录成功!");
					dispose(); // 关闭登录窗口
					new MainFrame(); // 调出主操作窗口
				} 
				else {
					JOptionPane.showMessageDialog(null, "用户名或密码错误，请重新登录！",
							"警告", JOptionPane.ERROR_MESSAGE);
				}
			}
		}
		else if (e.getSource() == cancel) // 单击取消
		{
			dispose();
			System.exit(0);
		} else if (e.getSource() == txtUserName) // 在用户名文本框按回车移动焦点到密码框
		{
			txtPwd.requestFocus();
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
