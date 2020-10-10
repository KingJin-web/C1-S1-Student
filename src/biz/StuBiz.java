package biz;

import java.security.GeneralSecurityException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

import javax.mail.MessagingException;

import bean.Student;
import dao.StuDao;
import util.DBHelper;
import util.EmailHelper;

public class StuBiz {
	private StuDao sDao = new StuDao();
	private static int radomInt = new Random().nextInt(999999);;

	// 更改密码
	public void modifyPassword(String newPwd) {

	}

	// 根据选择的商品消费，从余额中扣除对应金额
	public void consume() {

	}

	// 显示充值、消费记录
	public String[][] checkHistory() {

		return null;
	}

	// 验证账号密码合法性然后登陆
	public boolean login(String Sname, String Spw) throws BizException{
		if (Sname == null || Sname.trim().isEmpty()) {
			throw new BizException("请输入用户名 ! ");
		}

		if (Spw == null || Spw.trim().isEmpty()) {
			throw new BizException("请输入密码! ");
		}

		// 需要基本的判断
		String sql = "select * from student where Sname=? and Spw=?";
		List<Map<String, Object>> list = new DBHelper().query(sql, Sname, Spw);

		if (list.size() == 1) {
			return true;
		} else {
			throw new BizException("用户名或密码错误 ! ");
		}

	}

	/**
	 * 判断是否在这个用户里面
	 * 
	 * @param Sno
	 * @return
	 * @throws BizException
	 */
	public boolean select(String Sno, int i) throws BizException {
		String sql = "select * from student where Sname=? ";
		List<Map<String, Object>> list = new DBHelper().query(sql, Sno);
		System.out.println(list);
		if (list.size() == 1) {
			return true;
		} else {
			throw new BizException("您不是此用户表用户请重新选择 ");
		}
	}

	/**
	 * 显示学生信息
	 * 
	 * @return
	 * @throws BizException
	 */
	public List<Student> select(String sname) throws BizException {
		String sql = "select * from student where 1 = 1 and Sname=?";
		List<Student> list = new DBHelper().query(sql, Student.class, sname);

		System.out.println(list);
//        Student email = list.getSma();
		return list;
	}

	/**
	 * UPDATE 表名称 SET 列名称 = 新值 WHERE 列名称 = 某值 修改密码
	 * 
	 * @param newPw1
	 * @param newPw2
	 * @return
	 */
	public boolean changePw(String sno, String newPw1, String newPw2, int YanZhengma)
			throws BizException, GeneralSecurityException, MessagingException {
		StuBiz studentBiz = new StuBiz();

		if (newPw1 == null || newPw1.trim().isEmpty()) {
			throw new BizException("密码为空 ! ");
		}
		if (newPw2 == null || newPw2.trim().isEmpty()) {
			throw new BizException("密码为空 ! ");
		}
		if (!newPw1.equals(newPw2)) {
			throw new BizException("两次密码不一致请重新输入 ! ");
		}
		if (YanZhengma != 0) {
			if (radomInt != YanZhengma) {
				throw new BizException("验证码不一致请重新获取 ! ");
				
			}
		} else {
			
		}

		// UPDATE 表名称 SET 列名称 = 新值 WHERE 列名称 = 某值
		String sql = "update student set spw = ? where sname = ?";
		new DBHelper().update(sql, newPw1, sno);
		return true;
	}

	/**
	 * 发送验证码
	 *
	 * @param Sno
	 * @return
	 * @throws GeneralSecurityException
	 * @throws MessagingException
	 */
	public int YanZhengma(String Sno) throws GeneralSecurityException, MessagingException {
		radomInt = new Random().nextInt(999999);
		EmailHelper eh = new EmailHelper();
		String sql = "select * from student where Sname=? ";
		List<Student> list = new DBHelper().query(sql, Student.class, Sno);
		String email = null;
		for (Student stu : list) {
			email = stu.getSma();
		}
		System.out.println(email + " " + radomInt);

		eh.email(email, String.valueOf(radomInt));

		return radomInt;
	}

	public static void main(String[] args) throws BizException, GeneralSecurityException, MessagingException {
		StuBiz studentBiz = new StuBiz();
		int list = studentBiz.YanZhengma("蔡徐坤");
//		String email = null;
//		for (Student stu : list) {
//			email = stu.getSma();
//		}
//		System.out.println(email);

	}

	/**
	 * 返回图片名
	 * 
	 * @param name 学生姓名
	 * @return 图片名
	 * @throws BizException
	 */
	public String RetFile(String name) throws BizException {
		String File = null;
		StuBiz studentBiz = new StuBiz();
		List<Student> list = studentBiz.select(name);
		for (Student stu : list) {
			File = stu.getImgfile();
		}
		System.out.println(File);
		return File;
	}

	// UPDATE 表名称 SET 列名称 = 新值 WHERE 列名称 = 某值
	public void updaeImg(String fileName, String name) {
		String sql = "update student set imgFile = ? where Sname = ?";
		DBHelper dbh = new DBHelper();
		dbh.update(sql, fileName, name);
	}

	String[] college = new String[] { "外国语学院", "建工学院", "数能学院", "机械学院", "材化学院", "电信学院", "经管学院", "计信学院" };
}
