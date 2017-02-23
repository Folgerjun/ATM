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

class QueryResult extends JDialog implements ActionListener
{    
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JPanel p1=new JPanel(); //定义并建立面板
    JPanel p2=new JPanel();
    JPanel p3=new JPanel();
	JPanel p4=new JPanel();
	JPanel p5=new JPanel();
	JPanel p6=new JPanel();
	
	String[] ID = new String[3];
	String[] info = new String[6]; 
	String Card_number, Account_number, Loan_number, Customer_name,Card_style,Credit,Balance,Amount;
	JTextField customer_name=new JTextField(10);
	JTextField card_style=new JTextField(10); 
	JTextField credit=new JTextField(10); 
	JTextField balance=new JTextField(10); 
	JTextField amount=new JTextField(10); 
	JTextField card_number=new JTextField(10); 
	JTextField account_number=new JTextField(10); 
	JTextField loan_number=new JTextField(10); 

	JButton back=new JButton("返回");
	
	public QueryResult(String[] ID,String[] info,String Balance,String Amount){
		this.ID = ID;
		this.info = info;
		this.Balance = Balance;
		this.Amount = Amount;
		customer_name.setText(info[0]);
		card_style.setText(info[1]);
		credit.setText(info[2]);
		balance.setText(Balance);
		amount.setText(Amount);
		card_number.setText(ID[0]);
		account_number.setText(ID[1]);
		loan_number.setText(ID[2]);
		
		customer_name.setEditable(false);
		card_style.setEditable(false);
		credit.setEditable(false);
		balance.setEditable(false);
		amount.setEditable(false);
		card_number.setEditable(false);
		account_number.setEditable(false);
		loan_number.setEditable(false);
		
	    setModal(true); //设置模态
	    setBackground(Color.LIGHT_GRAY);//设置背景色
	    Container contentPane=this.getContentPane();//取出内容面板
	    contentPane.setLayout(new GridLayout(7,1)); //设置布局为7行1列
	    p1.add(new JLabel("您查询的相关信息如下：                                                                             "));				//将组件添加到中间容器
	    p2.add(new JLabel("客户名:      "));
	    p2.add(customer_name);
	    p2.add(new JLabel("          卡号:    "));
	    p2.add(card_number);
	    p3.add(new JLabel("卡类型:      "));
	    p3.add(card_style);
	    p3.add(new JLabel("      信用额度:"));
	    p3.add(credit);
	    p4.add(new JLabel("存款账号："));
	    p4.add(account_number);
	    p4.add(new JLabel("           存款:  "));
	    p4.add(balance);
	    p5.add(new JLabel("贷款账号："));
	    p5.add(loan_number);
	    p5.add(new JLabel("          贷款："));
	    p5.add(amount);
	    p6.add(back);
	    
	    back.addActionListener(this);
	    contentPane.add(p1);     //将面板添加到内容面板
	    contentPane.add(p2);
	    contentPane.add(p3);
	    contentPane.add(p4);
	    contentPane.add(p5);
	    contentPane.add(p6); 
	    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);//设置自动关闭窗口
	    setSize(600,400);
	    Dimension screen=Toolkit.getDefaultToolkit().getScreenSize();
	    setLocation((screen.width-600)/2,(screen.height-400)/2);
	    setTitle("查询成功显示信息");
	    setResizable(false); //不让用户改变窗口大小
	    setVisible(true);
	    }

	public void actionPerformed(ActionEvent e){
	    if(e.getSource()==back)   
	    { 
	    	dispose();
	    }
	    
	}
	
	@SuppressWarnings("unchecked")
	public static void main(String[] args) 
	{
	   JDialog.setDefaultLookAndFeelDecorated(true);
	   Font font=new Font("JFrame",Font.PLAIN,14);	   
	   Enumeration keys=UIManager.getLookAndFeelDefaults().keys();
	   while(keys.hasMoreElements())
	   {
		   Object key=keys.nextElement();
		   if(UIManager.get(key) instanceof Font)UIManager.put(key,font);
	   }
	   new BankSystem();
	}
	
}