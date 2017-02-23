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
import javax.swing.JTextField;
import javax.swing.UIManager;

import DB.DatabaseOperate;

class GetFrame extends JDialog implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JPanel p1 = new JPanel();
	JPanel p2 = new JPanel();
	JPanel p3 = new JPanel();
	JPanel p4 = new JPanel();
	JPanel p5 = new JPanel();

	JTextField money = new JTextField(15);
	JTextField balance = new JTextField(15);
	JButton search = new JButton("查询余额");
	JButton ok = new JButton("确定");
	JButton cancel = new JButton("返回");

	public GetFrame() {
		setModal(true); // 设置模态
		setBackground(Color.LIGHT_GRAY);// 设置背景色
		Container contentPane = this.getContentPane();// 取出内容面板
		contentPane.setLayout(new GridLayout(5, 1));
		balance.setEditable(false);
		p2.add(new JLabel("请输入取款金额："));
		p2.add(money); // 将组件添加到中间容器
		p3.add(new JLabel("   您当前可用的余额为："));
		p3.add(balance);
		p3.add(new JLabel("元!!"));
		p4.add(ok);
		p4.add(cancel);
		p4.add(search);

		search.addActionListener(this);
		ok.addActionListener(this);
		cancel.addActionListener(this);
		contentPane.add(p1);
		contentPane.add(p2);
		contentPane.add(p3); // 将面板添加到内容面板
		contentPane.add(p4);
		contentPane.add(p5);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);// 设置自动关闭窗口
		setSize(500, 300);
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation((screen.width - 500) / 2, (screen.height - 300) / 2);
		setTitle("取款窗口");
		setResizable(false); // 不让用户改变窗口大小
		setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		DatabaseOperate myGet = new DatabaseOperate();
		String[] t = new String[6];

		if (e.getSource() == ok) {

			t = myGet.schbalance(BankSystem.s[2].trim());
			if (Double.parseDouble(money.getText().trim()) <= Double
					.parseDouble(t[5].trim())) {
				myGet.get(money.getText().trim(), BankSystem.s[2].trim());
				JOptionPane.showMessageDialog(null, "取款成功！");
			} 
			else {
				
				int c;
				c = myGet.schcredit(BankSystem.s[0].trim());
				if (Double.parseDouble(money.getText().trim()) <= (Double
						.parseDouble(t[5].trim()) + c * 200)) {
					myGet.get(money.getText().trim(), BankSystem.s[2].trim());
					JOptionPane.showMessageDialog(null, "取款成功！");
				} else
					JOptionPane.showMessageDialog(null,
							"取款失败，由于您信用额度不足！", "警告",
							JOptionPane.ERROR_MESSAGE);
			}
		} else if (e.getSource() == cancel) {
			dispose();
			new MainFrame();
		} else if (e.getSource() == search) {
			t = myGet.schbalance(BankSystem.s[2].trim());
			balance.setText(t[5]);
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