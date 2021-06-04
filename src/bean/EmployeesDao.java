package bean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dao.EmployeesVo;

public class EmployeesDao
{
	private Connection con;
	EmployeesVo em = new EmployeesVo();
	private ArrayList<EmployeesVo> ae = new ArrayList<EmployeesVo>();

	private static final String SELECT_EMPLOYEE_SQL =
		"select "
		+" EMPLOYEEID"
		+",EMPLOYEENAME"
		+",HEIGHT"
		+",EMAIL"
		+",WEIGHT"
		+",HIREFISCALYEAR"
		+",BIRTHDAY"
		+",BLOODTYPE"
		+" from"
		+" EMPLOYEES"
		+" where"
		+" EMPLOYEEID=?";

	private static final String SELECT_EMPLOYEE_SQL1 =
		"select "
		+" EMPLOYEEID"
		+",EMPLOYEENAME"
		+",HEIGHT"
		+",EMAIL"
		+",WEIGHT"
		+",HIREFISCALYEAR"
		+",BIRTHDAY"
		+",BLOODTYPE"
		+" from"
		+" EMPLOYEES";

	public EmployeesDao(Connection con)
	{
		super();
		this.con = con;
	}

	//------------------------------


	public EmployeesVo getEmployee( int id) throws SQLException
	{


		/*Statementの作成*/
		try( PreparedStatement stmt= con.prepareStatement( SELECT_EMPLOYEE_SQL ) )
		{
			stmt.setInt( 1, id );

			/*ｓｑｌ実行*/
			try( ResultSet rset = stmt.executeQuery() )
			{
				/*取得したデータを表示します。*/
				while( rset.next() )
				{
					//em.setEmployeeid(       rset.getInt("EMPLOYEEID"));
					em.setEmployeeid(		rset.getInt(1)		);
					em.setEmployeename(		rset.getString(2)	);
					em.setHeight(			rset.getBigDecimal(3));
					em.setEmail(			rset.getString(4)	);
					em.setWeight(			rset.getBigDecimal(5));
					em.setHirefiscalyear(	rset.getInt(6)		);
					em.setBirthday(			rset.getDate(7)		);
					em.setBloodtype(		rset.getString(4)	);
				}
			}
		}

		return em;
	}

	//------------------------------
	// すべての従業員データを取得する

	public ArrayList<EmployeesVo> getAllEmployees() throws SQLException {


		try( PreparedStatement stmt = con.prepareStatement( SELECT_EMPLOYEE_SQL1 ) )
		{

			/*ｓｑｌ実行*/
			try( ResultSet rset = stmt.executeQuery() )
			{
				/*取得したデータを表示します。*/
				while( rset.next() )
				{

					EmployeesVo ev  = new EmployeesVo();

					//em.setEmployeeid(       rset.getInt("EMPLOYEEID"));
					ev.setEmployeeid(		rset.getInt(1)		);
					ev.setEmployeename(		rset.getString(2)	);
					ev.setHeight(			rset.getBigDecimal(3));
					ev.setEmail(			rset.getString(4)	);
					ev.setWeight(			rset.getBigDecimal(5));
					ev.setHirefiscalyear(	rset.getInt(6)		);
					ev.setBirthday(			rset.getDate(7)		);
					ev.setBloodtype(		rset.getString(4)	);

					ae.add(ev);
				}
			}

		}
		return ae;

	}

}
