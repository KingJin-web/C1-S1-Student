package dao;

import java.util.List;
import java.util.Map;

import bean.Message;
import util.DBHelper;

public class StuDao {
	public String showMessage() {
		DBHelper dbh = new DBHelper();
		String sql = "select sname,mdate,message from messageboard order by Mdate desc";
		List<Message> list = dbh.query(sql,Message.class);
		String ms = "学生留言：";
		for(int i=0;i<3;i++) {
			Message mss=list.get(i);
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
