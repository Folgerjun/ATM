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

class LogOffFrame extends JDialog implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JPanel p1 = new JPanel(); // 定义并建立面板
	JPanel p2 = new JPanel();
	JPanel p3 = new JPanel();
	JPanel p4 = new JPanel();
	JPanel p5 = new JPanel();
	JLabel label = new JLabel("请输入您想要注销的用户的卡号并点击“查询”：");
	JTextField card_number = new JTextField(15);
	JTextField balance = new JTextField(15);
	JTextField amount = new JTextField(15);
	JButton logoff = new JButton("注销");
	JButton back = new JButton("返回");
	JButton search = new JButton("查询");

	public LogOffFrame() {
		setModal(true); // 设置模态
		setBackground(Color.LIGHT_GRAY);// 设置背景色
		Container contentPane = this.getContentPane();// 取出内容面板
		contentPane.setLayout(new GridLayout(5, 1)); // 设置布局为5行1列
		label.setFont(new Font("Sans Serif", Font.BOLD, 15));
		balance.setEditable(false);
		amount.setEditable(false);
		p1.add(label);
		p2.add(new JLabel("卡       号:"));
		p2.add(card_number); // 将组件添加到中间容器
		p3.add(new JLabel("账户余额:"));
		p3.add(balance);
		p4.add(new JLabel("贷款总额:"));
		p4.add(amount);// 将组件添加到中间容器
		p5.add(logoff);
		p5.add(back);
		p5.add(search);

		logoff.addActionListener(this);
		back.addActionListener(this);
		search.addActionListener(this);
		contentPane.add(p1); // 将面板添加到内容面板
		contentPane.add(p2);
		contentPane.add(p3);
		contentPane.add(p4);
		contentPane.add(p5);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);// 设置自动关闭窗口
		setSize(500, 300);
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation((screen.width - 500) / 2, (screen.height - 300) / 2);
		setTitle("销户窗口");
		setResizable(false); // 不让用户改变窗口大小
		setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		DatabaseOperate myLogoff = new DatabaseOperate();
		String[] t = new String[6];
		String[] s = new String[6];
		String[] b = new String[6];
		String[] c = new String[6];

		if (e.getSource() == logoff) {
			s = myLogoff.schbalance(card_number.getText().trim());
			t = myLogoff.schamount(card_number.getText().trim());
			if (Double.parseDouble(s[5].trim()) >= Double.parseDouble(t[5]
					.trim())) {
				// 根据输入的卡号，找到相关信息
				b = myLogoff.schbalance(card_number.getText().trim());
				c = myLogoff.schamount(card_number.getText().trim());

				// 将找到的相关信息，删除
				String delLogin = "delete from login where card_number = "
						+ Integer.parseInt(card_number.getText().trim()) + "";
				String delDepositor = "delete from depositor where card_number = "
						+ Integer.parseInt(card_number.getText().trim()) + "";
				String delBorrower = "delete from borrower where card_number = "
						+ Integer.parseInt(card_number.getText().trim()) + "";
				String delLoan = "delete from loan where loan_number = "
						+ Integer.parseInt(c[1].trim()) + "";
				String delAccount = "delete from account where account_number = "
						+ Integer.parseInt(b[1].trim()) + "";
				String delCustomer = "delete from customer where customer_name = '"
						+ b[0] + "'  ";
				myLogoff.Insert(delLogin);
				myLogoff.Insert(delDepositor);
				myLogoff.Insert(delBorrower);
				myLogoff.Insert(delLoan);
				myLogoff.Insert(delAccount);
				myLogoff.Insert(delCustomer);
				JOptionPane.showMessageDialog(null, "客户所有信息已删除!");
			} else {
				JOptionPane.showMessageDialog(null, "该客户贷款金额大于帐户余额，无法注销！",
						"警告", JOptionPane.ERROR_MESSAGE);
			}

		} else if (e.getSource() == back) {
			dispose();
			new AdminFrame();
		} else if (e.getSource() == search) {
			t = myLogoff.schbalance(card_number.getText().trim());
			balance.setText(t[5]);
			t = myLogoff.schamount(card_number.getText().trim());
			amount.setText(t[5]);

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
