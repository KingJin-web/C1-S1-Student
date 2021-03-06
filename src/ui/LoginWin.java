package ui;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.wb.swt.SWTResourceManager;

import biz.AdminBiz;
import biz.BizException;
import biz.StuBiz;
import biz.TeacherBiz;
import ui.AdminWin;
import ui.HelpWin;
import ui.StudentCard;
import util.SwtHelper;
import util.SwtLabelPaintListner;
import util.generateCode;

import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;

import java.awt.image.RenderedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class LoginWin {

	protected Shell shell;
	private Text textNo;
	private Text textPwd;
	public static String name;

	private StuBiz sBiz = new StuBiz();
	private TeacherBiz tBiz = new TeacherBiz();
	private AdminBiz aBiz = new AdminBiz();
	private Text text;
	private String url = "src//imges//1602326102963.jpg";
	private String Code = "T8MN";
	private Button btnNewButton_2;

	/**
	 * Launch the application.
	 *
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			LoginWin window = new LoginWin();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		grtCode();
		shell = new Shell();
		shell.setImage(SWTResourceManager.getImage(LoginWin.class, "/imges/login.jpg"));
		shell.setSize(410, 449);
		shell.setText("登录窗口");
		SwtHelper.center(shell);
		shell.setLayout(new FormLayout());

		Label label = new Label(shell, SWT.NONE);
		FormData fd_label = new FormData();
		fd_label.top = new FormAttachment(0, 55);
		fd_label.left = new FormAttachment(0, 65);
		label.setLayoutData(fd_label);
		label.setText("用户名:");

		Label label_1 = new Label(shell, SWT.NONE);
		FormData fd_label_1 = new FormData();
		fd_label_1.right = new FormAttachment(0, 114);
		fd_label_1.top = new FormAttachment(0, 123);
		fd_label_1.left = new FormAttachment(0, 65);
		label_1.setLayoutData(fd_label_1);
		label_1.setText("密   码:");

		textNo = new Text(shell, SWT.BORDER);
		FormData fd_textNo = new FormData();
		fd_textNo.right = new FormAttachment(0, 296);
		fd_textNo.top = new FormAttachment(0, 52);
		fd_textNo.left = new FormAttachment(0, 126);
		textNo.setLayoutData(fd_textNo);

		textPwd = new Text(shell, SWT.BORDER | SWT.PASSWORD);
		FormData fd_textPwd = new FormData();
		fd_textPwd.right = new FormAttachment(0, 296);
		fd_textPwd.top = new FormAttachment(0, 120);
		fd_textPwd.left = new FormAttachment(0, 126);
		textPwd.setLayoutData(fd_textPwd);

		Combo combo = new Combo(shell, SWT.NONE);
		FormData fd_combo = new FormData();
		fd_combo.right = new FormAttachment(0, 262);
		fd_combo.top = new FormAttachment(0, 183);
		fd_combo.left = new FormAttachment(0, 155);
		combo.setLayoutData(fd_combo);
		combo.setItems(new String[] { "教师", "管理员", "学生" });

		Label label_2 = new Label(shell, SWT.NONE);
		FormData fd_label_2 = new FormData();
		fd_label_2.top = new FormAttachment(0, 186);
		fd_label_2.left = new FormAttachment(0, 65);
		label_2.setLayoutData(fd_label_2);
		label_2.setText("用户权限:");

		Button btnNewButton = new Button(shell, SWT.NONE);
		FormData fd_btnNewButton = new FormData();
		fd_btnNewButton.right = new FormAttachment(0, 149);
		fd_btnNewButton.top = new FormAttachment(0, 326);
		fd_btnNewButton.left = new FormAttachment(0, 54);
		btnNewButton.setLayoutData(fd_btnNewButton);
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				name = textNo.getText().trim();
				String pwd = textPwd.getText().trim();
				String str = subString(String.valueOf(combo), "{", "}");
				System.out.println(str);
				String code = text.getText().trim();

				try {

					if (name == null || name.isEmpty()) {
						SwtHelper.mssage("请输入用户名 !", shell);
						return;
					}
					if (pwd == null || pwd.isEmpty()) {
						SwtHelper.mssage("请输入密码 !", shell);
						return;
					}
					if (str == null || str.isEmpty()) {
						SwtHelper.mssage("请选择登录权限 !", shell);
						return;
					}
					if (code == null || code.isEmpty()) {
						SwtHelper.mssage("请输入验证码 !", shell);
						return;
					}

					if (Code.equals(code.toUpperCase())) {
						System.out.println("验证码正确");
						if ((str.contains("学生")) && (sBiz.select(name, 1))) {
							sBiz.login(name, pwd);
							name = textNo.getText();
							grtCode();
							btnNewButton_2.setImage(SWTResourceManager.getImage(url));
							LoginWin.this.shell.dispose();
							new StudentCard().open();
						} else if (str.contains("教师") && tBiz.select(name)) {
							tBiz.login(name, pwd);
							name = textNo.getText();
							grtCode();
							btnNewButton_2.setImage(SWTResourceManager.getImage(url));
							LoginWin.this.shell.dispose();
							new MainWin().open();
						} else if (str.contains("管理员")) {
							aBiz.login(name, pwd);
							name = textNo.getText();
							grtCode();
							btnNewButton_2.setImage(SWTResourceManager.getImage(url));
							LoginWin.this.shell.dispose();
							new AdminWin().open();
						}
					} else {
						SwtHelper.mssage("验证码输入错误 !", shell);
						return;
					}
//					name = textNo.getText();
//					grtCode();
//					btnNewButton_2.setImage(SWTResourceManager.getImage(url));

				} catch (BizException e1) {
					SwtHelper.mssage(e1.getMessage(), shell);
					return;
				} finally {
//					name = textNo.getText();
//					grtCode();
//					btnNewButton_2.setImage(SWTResourceManager.getImage(url));
				}
			}
		});
		btnNewButton.setText("登录");

		Button btnNewButton_1 = new Button(shell, SWT.NONE);
		FormData fd_btnNewButton_1 = new FormData();
		fd_btnNewButton_1.right = new FormAttachment(0, 332);
		fd_btnNewButton_1.top = new FormAttachment(0, 326);
		fd_btnNewButton_1.left = new FormAttachment(0, 237);
		btnNewButton_1.setLayoutData(fd_btnNewButton_1);
		btnNewButton_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				shell.dispose();
			}
		});
		btnNewButton_1.setText("取消");

		Button button = new Button(shell, SWT.NONE);
		FormData fd_button = new FormData();
		fd_button.bottom = new FormAttachment(0, 143);
		fd_button.top = new FormAttachment(0, 123);
		fd_button.left = new FormAttachment(0, 302);
		button.setLayoutData(fd_button);
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				new PwdChangeWin(shell, SWT.NONE).open();
			}
		});

		button.setImage(
				SWTResourceManager.getImage(LoginWin.class, "/org/eclipse/jface/dialogs/images/message_info.png"));
		button.setToolTipText("忘记密码");

		Button button_1 = new Button(shell, SWT.NONE);
		FormData fd_button_1 = new FormData();
		fd_button_1.top = new FormAttachment(0, 357);
		fd_button_1.left = new FormAttachment(0, 355);
		button_1.setLayoutData(fd_button_1);
		button_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					new HelpWin().open();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		button_1.setImage(SWTResourceManager.getImage("C:\\Users\\82427\\Desktop\\img\\wenhao.jpg"));
		button_1.setToolTipText("操作指南");

		Label label_4 = new Label(shell, SWT.NONE);
		FormData fd_label_4 = new FormData();
		fd_label_4.top = new FormAttachment(0, 258);
		fd_label_4.left = new FormAttachment(0, 65);
		label_4.setLayoutData(fd_label_4);
		label_4.setText("验证码");

		text = new Text(shell, SWT.BORDER);
		FormData fd_text = new FormData();
		fd_text.right = new FormAttachment(0, 212);
		fd_text.top = new FormAttachment(0, 258);
		fd_text.left = new FormAttachment(0, 125);
		text.setLayoutData(fd_text);

		btnNewButton_2 = new Button(shell, SWT.NONE);
		FormData fd_btnNewButton_2 = new FormData();
		fd_btnNewButton_2.bottom = new FormAttachment(0, 286);
		fd_btnNewButton_2.right = new FormAttachment(0, 357);
		fd_btnNewButton_2.top = new FormAttachment(0, 250);
		fd_btnNewButton_2.left = new FormAttachment(0, 237);
		btnNewButton_2.setLayoutData(fd_btnNewButton_2);
		btnNewButton_2.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				grtCode();
				btnNewButton_2.setImage(SWTResourceManager.getImage(url));
			}
		});
		// btnNewButton_2.setText("New Button");
		btnNewButton_2.setImage(SWTResourceManager.getImage(url));
		btnNewButton_2.addPaintListener(new PaintListener() {
			public void paintControl(PaintEvent e) {
				// 代码如下 https://blog.csdn.net/qq_39047789/article/details/100503878

				// 获取到控件中的图片
				org.eclipse.swt.graphics.Image image = btnNewButton_2.getImage();
				int h = btnNewButton_2.getBounds().height; // 获取控件的高
				int w = btnNewButton_2.getBounds().width; // 获取控件的宽度
				int height = image.getBounds().height; // 获取原图片的高度
				int width = image.getBounds().width; // 获取原图片的初始宽度
				// 绘制图片，将原图片按照控件的高度和宽度进行重绘
				e.gc.drawImage(image, 0, 0, width, height, 0, 0, w, h);
			}
		});
		btnNewButton_2.setToolTipText("看不清？点击刷新 ");

		Label label_3 = new Label(shell, SWT.NONE);
		FormData fd_label_3 = new FormData();
		fd_label_3.top = new FormAttachment(0);
		fd_label_3.left = new FormAttachment(0);
		label_3.setLayoutData(fd_label_3);
		label_3.setLayoutData(new FormData());
		label_3.setImage(SWTResourceManager.getImage(LoginWin.class, "/imges/baishi.jpg"));
		label_3.addPaintListener(new SwtLabelPaintListner());

	}

	public String grtCode() {
		url = "src//imges//" + System.currentTimeMillis() + ".jpg";
		File file = new File(url);
		FileOutputStream out = null;
		try {
			if (!file.exists()) {
				// 先得到文件的上级目录，并创建上级目录，在创建文件
				file.getParentFile().mkdir();
				file.createNewFile();
			}
			out = new FileOutputStream(file);
			Map<String, Object> map = generateCode.generateCodeAndPic();
			ImageIO.write((RenderedImage) map.get("codePic"), "jpeg", out);
			Code = String.valueOf(map.get("code"));
			System.out.println("验证码的值为：" + map.get("code"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return url;
	}

	public static String returnName() {
		return name;
	}

	/**
	 * 截取字符串str中指定字符 strStart、strEnd之间的字符串
	 */
	public static String subString(String str, String strStart, String strEnd) {

		/* 找出指定的2个字符在 该字符串里面的 位置 */
		int strStartIndex = str.indexOf(strStart);
		int strEndIndex = str.indexOf(strEnd);

		/* index 为负数 即表示该字符串中 没有该字符 */
		if (strStartIndex < 0) {
			return "字符串 :---->" + str + "<---- 中不存在 " + strStart + ", 无法截取目标字符串";
		}
		if (strEndIndex < 0) {
			return "字符串 :---->" + str + "<---- 中不存在 " + strEnd + ", 无法截取目标字符串";
		}
		/* 开始截取 */
		String result = str.substring(strStartIndex, strEndIndex).substring(strStart.length());
		return result;
	}
}
