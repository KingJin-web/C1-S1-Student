package biz;

import java.util.List;
import java.util.Map;

import util.DBHelper;

public class AdminBiz {
	public boolean login(String aname,String apwd) throws BizException{
		if (aname == null || aname.trim().isEmpty() ){
            throw new BizException("请输入用户名！");
        }

        if (apwd == null || apwd.trim().isEmpty() ){
            throw new BizException("请输入密码！");
        }

        String sql = "select * from admin where aname=? and apwd=?";
        List<Map<String,Object>> list = new DBHelper().query(sql,aname,apwd);
        if (list.size() == 1){
            return true;
        }else {
            throw new BizException("用户名或密码输入错误!");
        }
	}
	
	public static void main(String[] args) {
		AdminBiz a = new AdminBiz();
		try {
			a.login("yc", "123");
		} catch (BizException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
