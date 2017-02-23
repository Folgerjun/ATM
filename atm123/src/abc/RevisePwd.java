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

class RevisePwd extends JDialog implements ActionListener {
	
	
	
	private static final long serialVersionUID = 1L;
	JPanel p1 = new JPanel(); // 定义并建立面板
	JPanel p2 = new JPanel();
	JPanel p3 = new JPanel();
	JPanel p4 = new JPanel();
	JPanel p5 = new JPanel();
	JPanel p6 = new JPanel();
	JLabel label = new JLabel("请输入旧密码和新密码：");
	JPasswordField txtPwd1 = new JPasswordField(15);// 密码框
	JPasswordField txtPwd2 = new JPasswordField(15);// 密码框
	JPasswordField txtPwd3 = new JPasswordField(15);// 密码框
	JButton ok = new JButton("确定");
	JButton cancel = new JButton("取消");

	public RevisePwd() {
		setModal(true); // 设置模态
		setBackground(Color.LIGHT_GRAY);// 设置背景色
		Container contentPane = this.getContentPane();// 取出内容面板
		contentPane.setLayout(new GridLayout(6, 1)); // 设置布局为5行1列
		label.setFont(new Font("Sans Serif", Font.BOLD, 15));
		p2.add(label);	
		p3.add(new JLabel("旧  密  码:"));
		p3.add(txtPwd1); // 将组件添加到中间容器
		p4.add(new JLabel("新  密  码:"));
		p4.add(txtPwd2); // 将组件添加到中间容器
		p5.add(new JLabel("确认新密码:"));
		p5.add(txtPwd3); // 将组件添加到中间容器
		p6.add(ok);
		p6.add(cancel);
		ok.addActionListener(this);
		cancel.addActionListener(this);
		txtPwd1.addActionListener(this);
		txtPwd2.addActionListener(this);
		txtPwd3.addActionListener(this);
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
		setTitle("修改密码窗口");
		setResizable(false); // 不让用户改变窗口大小
		setVisible(true);
	}

	@SuppressWarnings("deprecation")
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == ok || e.getSource() == txtPwd2) // 单击确定按钮后
		{
			String password1 = new String(txtPwd1.getPassword());
			String password2 = new String(txtPwd2.getPassword());
			String password3 = new String(txtPwd2.getPassword());
			String[] t = new String[4];
			DatabaseOperate myLogin = new DatabaseOperate();
			t = myLogin.login(BankSystem.s[2].trim());
			
			if (password2.equals(password3) && (password1.equals(t[3].trim()))) {
				myLogin.revise(BankSystem.s[2].trim(), password2);
				JOptionPane.showMessageDialog(null,
						"修改成功！");
				dispose(); // 关闭登录窗口
				new BankSystem(); // 调出管理员操作窗口
			} 
			
				else {
					JOptionPane.showMessageDialog(null, "用户名或密码错误！",
							"警告", JOptionPane.ERROR_MESSAGE);
				}
			
		}
		else if (e.getSource() == cancel) // 单击取消
		{
			dispose();
			System.exit(0);
		}else if (e.getSource() == txtPwd1) 
		{
			txtPwd2.requestFocus();
		}else if (e.getSource() == txtPwd2) 
		{
			txtPwd3.requestFocus();
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
