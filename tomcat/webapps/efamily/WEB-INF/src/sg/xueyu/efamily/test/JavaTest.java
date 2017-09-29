package sg.xueyu.efamily.test;

import java.sql.SQLException;
import java.text.ParseException;

import sg.xueyu.dbhandler.handler.AbstractEntity;
import sg.xueyu.efamily.base.NoPoolConnection;
import sg.xueyu.efamily.base.dbhandler.LoginUser;
import sg.xueyu.efamily.base.dbhandler.LoginUserHandler;
import sg.xueyu.efamily.base.dbhandler.LoginUserSearchKey;

public class JavaTest {

	public static void main(String[] args) throws ClassNotFoundException, SQLException, InstantiationException,
			IllegalAccessException, ParseException {

		LoginUserHandler handler = new LoginUserHandler(NoPoolConnection.getConnection());
		LoginUserSearchKey searchKey = new LoginUserSearchKey();
		searchKey.setUserId("xueyu");
		AbstractEntity[] results = handler.query(searchKey);
		System.out.println(((LoginUser) results[0]).getUserName());
	}

}
