package abc;
import DB.DatabaseOperate;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Enumeration;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UIManager;

class   OpenAccount extends JDialog implements ActionListener
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
	String names[] = {" ","农行信用卡","农行借记卡","中行信用卡","中信一卡通","工商信用卡","建设信用卡"};
	String names2[] = {" ","0","1","2","3","4","5"};
	String Card_style;
	String Credit;
	String Branch_name = "地大支行";
	String[] ID = new String[3];
	String[] info = new String[6]; 
	String Card_number, Account_number, Loan_number, Customer_name, Customer_city, Customer_street, Balance, Pwd;
	JTextField customer_name=new JTextField(15);
	JTextField customer_city=new JTextField(15);
	JTextField customer_street=new JTextField(15);
	JTextField balance=new JTextField(15); 
	JComboBox card_style = new JComboBox(names); 
	JComboBox credit = new JComboBox(names2); 
	JPasswordField txtPwd1=new JPasswordField(15);//密码框
	JPasswordField txtPwd2=new JPasswordField(15);//密码框
	JButton ok=new JButton("确认");
	JButton clean = new JButton("清空");
	JButton back=new JButton("返回");
	
	public OpenAccount(){
	    setModal(true); //设置模态
	    setBackground(Color.LIGHT_GRAY);//设置背景色
	    Container contentPane=this.getContentPane();//取出内容面板
	    contentPane.setLayout(new GridLayout(6,1)); //设置布局为5行1列
	    card_style.setSelectedIndex(0);
	    card_style.setMaximumRowCount(4);
	    card_style.addItemListener(new card_styleHandler());
	    credit.setSelectedIndex(0);
	    credit.setMaximumRowCount(4);
	    credit.addItemListener(new creditHandler());
	    p1.add(new JLabel("请填写客户相关信息："));				//将组件添加到中间容器
	    p2.add(new JLabel("客户名:"));
	    p2.add(customer_name); 
	    p2.add(new JLabel("          城市:"));
	    p2.add(customer_city); 
	    p3.add(new JLabel("   街道:"));
	    p3.add(customer_street);
	    p3.add(new JLabel("          存款:"));
	    p3.add(balance);
	    p5.add(new JLabel("卡类型:"));
	    p5.add(card_style);
	    p5.add(new JLabel("                                     信用额度:"));
	    p5.add(credit);
	    p4.add(new JLabel("密码："));
	    p4.add(txtPwd1);
	    p4.add(new JLabel("确认密码："));
	    p4.add(txtPwd2);
	    p6.add(ok);
	    p6.add(clean);
	    p6.add(back);
	    ok.addActionListener(this);
	    clean.addActionListener(this);
	    back.addActionListener(this);
	    customer_name.addActionListener(this);
	    customer_city.addActionListener(this);
	    customer_street.addActionListener(this);
	    txtPwd1.addActionListener(this);
	    txtPwd2.addActionListener(this);
	    contentPane.add(p1);   //将面板添加到内容面板
	    contentPane.add(p2);
	    contentPane.add(p3);
	    contentPane.add(p4);
	    contentPane.add(p5);
	    contentPane.add(p6);
	    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);//设置自动关闭窗口
	    setSize(600,400);
	    Dimension screen=Toolkit.getDefaultToolkit().getScreenSize();
	    setLocation((screen.width-600)/2,(screen.height-400)/2);
	    setTitle("开户窗口");
	    setResizable(false); //不让用户改变窗口大小
	    setVisible(true);
	    }

	private class card_styleHandler implements ItemListener
	{
		@SuppressWarnings("static-access")
		public void itemStateChanged(ItemEvent e)
		{
			if(e.getStateChange()==e.SELECTED)
			{
				Card_style = names[card_style.getSelectedIndex()];
			}
		}
	}
	private class creditHandler implements ItemListener
	{
		@SuppressWarnings("static-access")
		public void itemStateChanged(ItemEvent e)
		{
			if(e.getStateChange()==e.SELECTED)
			{
				Credit = names2[credit.getSelectedIndex()];
			}
		}
	}
	
	@SuppressWarnings("deprecation")
	public void actionPerformed(ActionEvent e){
	    if(e.getSource()==ok)   //单击确定按钮后，系统自动生成相关卡号，并将用户输入的信息，写入数据库中
	    { 
	    	String pas1 = new String(txtPwd1.getPassword());
	    	String pas2 = new String(txtPwd2.getPassword());
	    	if(pas1.equals(pas2))//判断用户两次输入的密码是否相同
	    	{
	    		//系统自动生成卡号、存款账号和贷款账号
		    	DatabaseOperate makeNum = new DatabaseOperate();
		    	
		    	//生成以1开头的5位随机数作为卡号
		    	int pcn=(int)(Math.random()*100)+10000;
		    	Card_number = makeNum.CnPreclude(Integer.toString(pcn));
		    	ID[0] = Card_number;
		    			   
		    	//生成以2开头的3位随机数作为存款账号
		    	int pan=(int)(Math.random()*100)+200;
		    	Account_number = makeNum.depositorNum(Integer.toString(pan));
		    	ID[1] = Account_number;
		    	
		    	//生成以3开头的3位随机数作为贷款账号
		    	int pln=(int)(Math.random()*100)+300;
		    	Loan_number = makeNum.borrowerNum(Integer.toString(pln));
		    	ID[2] = Loan_number;
		    	
		    	//将生成的卡号以及用户输入的信息写入数据库中
		    	info[0] = customer_name.getText().trim();
		    	info[1] = customer_city.getText().trim();
		    	info[2] = customer_street.getText().trim();
		    	info[3] = Card_style;
		    	info[4] = Credit;
		    	info[5] = pas1;
		    	Balance = balance.getText().trim();
		    	
		    	//System.out.println(info[0]+","+info[1]+","+info[2]+","+info[3]+","+info[4]+","+info[5]+","+Balance);
		    	
		    	DatabaseOperate insertTable = new DatabaseOperate();
		    	String sqlLogin = " insert into login(customer_name, card_style, card_number, password) " +
				 							  "values('"+info[0]+"', '"+info[3]+"', '"+ID[0]+"', '"+info[5]+"')";
		    	String sqlAccount = " insert into account(account_number, branch_name, balance)" +
				 								  "values('"+ID[1]+"', '"+Branch_name+"', '"+ Balance +"')";
		    	String sqlBorrower = " insert into borrower(customer_name, loan_number, card_number)" +
				  								   "values('"+info[0]+"', '"+ID[2]+"', '"+ ID[0] +"')";
		    	String sqlCustomer = " insert into customer(customer_name, customer_street, customer_city, credit)" +
				   									"values('"+info[0]+"', '"+info[2]+"', '"+info[1]+"', '"+ Credit +"')";
		    	String sqlDepositor = " insert into depositor(customer_name, account_number, card_number)" +
		    										  "values('"+info[0]+"', '"+ID[1]+"', '"+ ID[0] +"')";
		    	String sqlLoan = " insert into loan(loan_number, branch_name, amount)" +
		    								"values('"+ID[2]+"', '"+Branch_name+"', '"+ 0 +"')";
		    	//System.out.println(sqlLogin);
		    	
		    	insertTable.Insert(sqlLogin);
		    	insertTable.Insert(sqlAccount);
		    	insertTable.Insert(sqlBorrower);
		    	insertTable.Insert(sqlCustomer);
		    	insertTable.Insert(sqlDepositor);
		    	insertTable.Insert(sqlLoan);
		    	dispose();
		    	new OpenAccountSuccess(ID, info, Balance);
	    	}else
	    	{
	    		JOptionPane.showMessageDialog(null," 您再次输入的密码不一致，请重新输入！！");
	    	}
	    }
	    else if(e.getSource()==clean) //单击清空，重置所有的文本输入框
	    {
	    	customer_name.setText(null);
			customer_city.setText(null);
			customer_street.setText(null);
			balance.setText(null);
			txtPwd1.setText(null);
			txtPwd2.setText(null);
	    }
	    else if(e.getSource()==back) //单击返回
	    {
	    	dispose();
	    	new AdminFrame();
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