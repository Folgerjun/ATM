package DB;

import java.sql.ResultSet;

import javax.swing.JOptionPane;

public class DatabaseOperate {

	/**
	 * @param args
	 */
	String sql, sql1;
	ResultSet rs = null;
	String account_number, branch_name, card_style, balance, customer_name,
			loan_number, card_number, branch_city, assets;
	String customer_street, customer_city, credit, amount, password;

	// 登录，根据用户输入卡号查询得到密码，并验证
	public String[] login(String num) {
		DatabaseConn DB = new DatabaseConn();
		this.card_number = num;
		String[] s = new String[4];
		sql = "select * from login where card_number = "
				+ Integer.parseInt(card_number.trim())+" ";
		try {
			DB.OpenConn();
			rs = DB.executeQuery(sql);
			if (rs.next()) {
				s[0] = rs.getString("customer_name");
				s[1] = rs.getString("card_style");
				s[2] = rs.getString("card_number");
				s[3] = rs.getString("password");
			} else
				s = null;

		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			DB.closeStmt();
			DB.closeConn();
		}
		return s;
	}

	// 查询存、取款账户相关信息
	public String[] schbalance(String num) {
		DatabaseConn DB = new DatabaseConn();
		this.card_number = num;
		String[] s = new String[6];
		sql = "select * from depositor,account where (card_number = "
				+ Integer.parseInt(card_number)
				+ ") and depositor.account_number = account.account_number ";
		try {
			DB.OpenConn();
			rs = DB.executeQuery(sql);
			if (rs.next()) {
				s[0] = rs.getString(1); // customer_name
				s[1] = rs.getString(2); // depositor.account_number
				s[2] = rs.getString(3); // card_number
				s[3] = rs.getString(4); // account.account_number
				s[4] = rs.getString(5); // branch_name
				s[5] = rs.getString(6); // balance
			} else
				s = null;
		} catch (Exception e) {

		} finally {
			DB.closeStmt();
			DB.closeConn();
		}
		return s;
	}

	// 查询贷款账户相关信息
	public String[] schamount(String num) {
		DatabaseConn DB = new DatabaseConn();
		this.card_number = num;
		String[] s = new String[6];
		sql = "select * from borrower,loan where (card_number = "
				+ Integer.parseInt(card_number)
				+ ") and loan.loan_number = borrower.loan_number ";
		try {
			DB.OpenConn();
			rs = DB.executeQuery(sql);
			if (rs.next()) {
				s[0] = rs.getString(1); // customer_name
				s[1] = rs.getString(2); // borrower.loan_number
				s[2] = rs.getString(3); // card_number
				s[3] = rs.getString(4); // loan.loan_number
				s[4] = rs.getString(5); // branch_name
				s[5] = rs.getString(6); // amount
			} else
				s = null;
		} catch (Exception e) {

		} finally {
			DB.closeStmt();
			DB.closeConn();
		}
		return s;
	}

	// 查询客户信用额度
	public int schcredit(String name) {
		DatabaseConn DB = new DatabaseConn();
		this.customer_name = name;
		int Credit = 0;
		sql = "select credit from customer where customer_name =  '"
				+ customer_name + "' ";
		try {
			DB.OpenConn();
			rs = DB.executeQuery(sql);
			while (rs.next()) {
				Credit = rs.getInt(1);
			}
		} catch (Exception e) {

		} finally {
			DB.closeStmt();
			DB.closeConn();
		}
		return Credit;
	}

	// 存款，根据用户输的金额更新账户
	public String[] deposit(String money, String num) {
		DatabaseConn DB = new DatabaseConn();
		this.balance = money;
		this.card_number = num;
		String[] t = new String[6];
		t = schbalance(card_number);

		double temp = Double.parseDouble(t[5].trim())
				+ Double.parseDouble(balance);
		sql = "update account set balance = " + temp
				+ " where account_number = " + Integer.parseInt(t[1].trim())
				+ " ";
		try {
			DB.OpenConn();
			DB.executeUpdate(sql);
		} catch (Exception e) {
			System.out.println(e);
			JOptionPane.showMessageDialog(null, "存款失败，请重新输入金额点击确定！", "错误",
					JOptionPane.ERROR_MESSAGE);

		} finally {
			DB.closeStmt();
			DB.closeConn();
		}
		return t;
	}
	// 修改密码
		public void revise(String num, String password) {
			DatabaseConn DB = new DatabaseConn();
			this.password = password;
			this.card_number = num;
			sql = "update login set password = " + password
					+ " where card_number = " + Integer.parseInt(card_number.trim())
					+ " ";
			//System.out.println(sql);
			try {

					DB.OpenConn();
					DB.executeUpdate(sql);
	
			} catch (Exception e) {
				System.out.println(e);
				JOptionPane.showMessageDialog(null, "修改失败，请重新输入密码点击确定！", "错误",
						JOptionPane.ERROR_MESSAGE);

			} finally {
				DB.closeStmt();
				DB.closeConn();
			}
			
		}
	// 取款 根据用户输的金额跟信用额度 更新账户
	public String[] get(String money, String num) {
		DatabaseConn DB = new DatabaseConn();
		this.card_number = num;
		this.balance = money;
		String[] t = new String[6];
		t = schbalance(card_number);

		double temp = Double.parseDouble(t[5].trim())
				- Double.parseDouble(balance);
		sql = "update account set balance = " + temp
				+ " where account_number = " + Integer.parseInt(t[1].trim())
				+ " ";
		try {
			DB.OpenConn();
			DB.executeUpdate(sql);
		} catch (Exception e) {
			System.out.println(e);
			JOptionPane.showMessageDialog(null, "取款失败，您取款金额过大请重新填写！", "错误",
					JOptionPane.ERROR_MESSAGE);

		} finally {
			DB.closeStmt();
			DB.closeConn();
		}
		return t;
	}


	// 验证卡号是否唯一，若不唯一，则生成新的卡号
	public String CnPreclude(String testcn) {
		String a = testcn;
		DatabaseConn DB = new DatabaseConn();
		String sql = "select * from login";
		try {
			DB.OpenConn();
			rs = DB.executeQuery(sql);
			while (rs.next()) {
				if ((testcn).equals(rs.getString("card_number").trim())) {
					String next = null;
					next = Integer.toString(Integer.parseInt(testcn) + 1);
					a = CnPreclude(next);
				}
			}
		} catch (Exception e) {

		} finally {
			DB.closeStmt();
			DB.closeConn();
		}
		return a;
	}

	// 验证存款账号是否唯一，若不唯一，则生成新的账号
	public String depositorNum(String num) {
		String a = num;
		DatabaseConn DB = new DatabaseConn();
		String sql = "select * from account";
		try {
			DB.OpenConn();
			rs = DB.executeQuery(sql);
			while (rs.next()) {
				if ((num).equals(rs.getString("account_number").trim())) {
					String next = null;
					next = Integer.toString(Integer.parseInt(num) + 1);
					a = depositorNum(next);
				}
			}
		} catch (Exception e) {

		} finally {
			DB.closeStmt();
			DB.closeConn();
		}
		return a;
	}

	// 验证贷款账号是否唯一，若不唯一，则生成新的账号
	public String borrowerNum(String num) {
		String a = num;
		DatabaseConn DB = new DatabaseConn();
		String sql = "select * from loan";
		try {
			DB.OpenConn();
			rs = DB.executeQuery(sql);
			while (rs.next()) {
				if ((num).equals(rs.getString("loan_number").trim())) {
					String next = null;
					next = Integer.toString(Integer.parseInt(num) + 7);
					a = borrowerNum(next);
				}
			}
		} catch (Exception e) {

		} finally {
			DB.closeStmt();
			DB.closeConn();
		}
		return a;
	}

	// 利用传入的sql语句，向表插入数据
	public void Insert(String mySql) {
		DatabaseConn DB = new DatabaseConn();
		sql = mySql;
		try {
			DB.OpenConn();
			DB.executeUpdate(sql);

		} catch (Exception e) {

		} finally {
			DB.closeStmt();
			DB.closeConn();
		}
	}

}
