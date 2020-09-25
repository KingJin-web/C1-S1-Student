package dao;

import java.util.List;
import java.util.Map;

import bean.Message;
import util.DBHelper;

public class StuDao {
	public String showMessage() {
		DBHelper dbh = new DBHelper();
		String sql = "select sname,mdate,message from messageboard order by Mdate desc limit 3;";
		List<Message> list = dbh.query(sql, Message.class);
		String ms = "学生留言：";
		for (Message mss : list) {
			ms = ms + String.valueOf(mss);
		}
//		String ms = String.valueOf(list);
		System.out.println(ms);
		return ms;
	}

	public static void main(String[] args) {
		StuDao s = new StuDao();
		s.showMessage();
	}
}
