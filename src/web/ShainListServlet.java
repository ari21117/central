package web;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.DBUtil;
import bean.ShainListBean;
import dao.EmployeesVo;

@WebServlet("/ShainListServlet")
public class ShainListServlet extends HttpServlet {

	protected void doPost(
			HttpServletRequest request,
			HttpServletResponse response
			) throws ServletException, IOException
	{
		//社員リストwoDBから取得　課題
		List<EmployeesVo> shainList = null;
		try {
			shainList = getEmployeesVoList();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

		ShainListBean bean = new ShainListBean();

		bean.setMsg(			"社員リストを表示します");
		bean.setShainList( 		shainList );

		//セッションからログインユーザーを取得
		HttpSession session = request.getSession();
	    EmployeesVo emp  = (EmployeesVo)session.getAttribute("EmployeesVo");

	    bean.setLoginShainName(	emp.getEmployeename() );

		request.setAttribute("bean", bean);

		//JSPに遷移する
		RequestDispatcher disp = request.getRequestDispatcher("/shainlist.jsp");
		disp.forward(request, response);
	}

	//DBから従業員を取得する
	private static List<EmployeesVo> getEmployeesVoList() throws ClassNotFoundException, SQLException
	{
		Connection c  =null;

		DBUtil db = new DBUtil();
		c = db.getConnection();

		bean.EmployeesDao dao = new bean.EmployeesDao( c );
		List<EmployeesVo> empList = dao.getAllEmployees();

		//ここにDBアクセス処理を作ってみましょう。　課題


		return empList;
	}


}
