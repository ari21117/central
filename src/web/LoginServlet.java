package web;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.DBUtil;
import bean.EmployeesDao;
import bean.MainBean;
import dao.EmployeesVo;
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet
{
	protected void doPost(
			HttpServletRequest request,
			HttpServletResponse response
			) throws ServletException, IOException
	{
		//画面から入力したデータを取得する
		String idStr = request.getParameter("ID");
		String peStr = request.getParameter("PW");

		int id = Integer.parseInt(idStr);

		//とりあえずPwはつかわない
		EmployeesVo emp = null;
		emp = getEmployeesVo( id );

		//パスワードチェック　実装しない　オプション課題

		//セッションに保存
		HttpSession session =request.getSession();
		session.setAttribute( "EmployeesVo", emp );

		//次の画面遷移の処理
		MainBean bean = new MainBean();
		bean.setShainName( emp.getEmployeename() );

		request.setAttribute("bean", bean);

		//JSPに遷移する
		RequestDispatcher disp = request.getRequestDispatcher("/main.jsp");
		disp.forward(request, response);
	}

	//DBから従業員を取得する
	private EmployeesVo getEmployeesVo( int id )
	{
		EmployeesVo emp = null;
		Connection c  =null;

		DBUtil db = new DBUtil();

		try {
			c = db.getConnection();
		} catch (ClassNotFoundException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

		EmployeesDao ed = new EmployeesDao(c);

		try {
			emp = ed.getEmployee(id);
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

		return emp;
	}

}
