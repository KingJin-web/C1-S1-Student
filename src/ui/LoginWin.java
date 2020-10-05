package ui;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.wb.swt.SWTResourceManager;

import biz.AdminBiz;
import biz.BizException;
import biz.StuBiz;
import biz.TeacherBiz;

import util.SwtHelper;

import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;

import java.sql.SQLException;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.PaintEvent;
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
		shell = new Shell();
		shell.setImage(SWTResourceManager.getImage(LoginWin.class, "/javax/swing/plaf/basic/icons/JavaCup16.png"));
		shell.setSize(410, 363);
		shell.setText("登录窗口");
		shell.setLayout(new FormLayout());
		SwtHelper.center(shell);

		Label label = new Label(shell, SWT.NONE);
		FormData fd_label = new FormData();
		fd_label.left = new FormAttachment(0, 65);
		label.setLayoutData(fd_label);
		label.setText("用户名:");

		Label label_1 = new Label(shell, SWT.NONE);
		fd_label.bottom = new FormAttachment(label_1, -48);
		FormData fd_label_1 = new FormData();
		fd_label_1.left = new FormAttachment(0, 65);
		label_1.setLayoutData(fd_label_1);
		label_1.setText("密   码:");

		textNo = new Text(shell, SWT.BORDER);
		fd_label.right = new FormAttachment(textNo, -12);
		FormData fd_textNo = new FormData();
		fd_textNo.right = new FormAttachment(100, -96);
		fd_textNo.left = new FormAttachment(0, 126);
		fd_textNo.top = new FormAttachment(label, -3, SWT.TOP);
		textNo.setLayoutData(fd_textNo);

		textPwd = new Text(shell, SWT.BORDER | SWT.PASSWORD);
		fd_label_1.right = new FormAttachment(textPwd, -12);
		FormData fd_textPwd = new FormData();
		fd_textPwd.top = new FormAttachment(label_1, -3, SWT.TOP);
		fd_textPwd.right = new FormAttachment(textNo, 0, SWT.RIGHT);
		fd_textPwd.left = new FormAttachment(0, 126);
		textPwd.setLayoutData(fd_textPwd);

		Combo combo = new Combo(shell, SWT.NONE);
		combo.setItems(new String[] { "教师", "管理员", "学生" });
		FormData fd_combo = new FormData();
		fd_combo.right = new FormAttachment(100, -130);
		combo.setLayoutData(fd_combo);

		Label label_2 = new Label(shell, SWT.NONE);
		fd_combo.top = new FormAttachment(label_2, -3, SWT.TOP);
		fd_combo.left = new FormAttachment(label_2, 26);
		fd_label_1.bottom = new FormAttachment(100, -173);
		label_2.setText("用户权限:");
		FormData fd_label_2 = new FormData();
		fd_label_2.left = new FormAttachment(0, 65);
		fd_label_2.top = new FormAttachment(0, 186);
		label_2.setLayoutData(fd_label_2);

		Button btnNewButton = new Button(shell, SWT.NONE);
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				name = textNo.getText();
				String pwd = textPwd.getText();
				try {

					String str = String.valueOf(combo);
					if((str.contains("学生")) && (sBiz.select(name,1))) {
						
						sBiz.login(name, pwd);						
						System.out.println("成功");
						LoginWin.this.shell.dispose();
	                    new StudentCard().open();
					}else if(str.contains("教师")&&tBiz.select(name) ) {
						tBiz.login(name, pwd);
						System.out.println("成功");
					}else if(str.contains("管理员")) {
						aBiz.login(name, pwd);
						System.out.println("成功");
					}else {
						MessageBox mb = new MessageBox(shell);
						mb.setText("系统提示");
						mb.setMessage("叼你妈!");
						mb.open();
					}
				} catch (BizException | SQLException e1) {
					e1.getMessage();
					MessageBox mb = new MessageBox(shell);
					mb.setText("系统提示");
					mb.setMessage(e1.getMessage());
					mb.open();
				}finally {
					returnName();
				}
			}
		});
		FormData fd_btnNewButton = new FormData();
		fd_btnNewButton.bottom = new FormAttachment(100, -28);
		fd_btnNewButton.right = new FormAttachment(label_2, 17, SWT.RIGHT);
		fd_btnNewButton.left = new FormAttachment(0, 51);
		btnNewButton.setLayoutData(fd_btnNewButton);
		btnNewButton.setText("登录");

		Button btnNewButton_1 = new Button(shell, SWT.NONE);
		btnNewButton_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				shell.dispose();
			}
		});
		btnNewButton_1.setText("取消");
		FormData fd_btnNewButton_1 = new FormData();
		fd_btnNewButton_1.top = new FormAttachment(btnNewButton, 0, SWT.TOP);
		fd_btnNewButton_1.left = new FormAttachment(btnNewButton, 88);
		fd_btnNewButton_1.right = new FormAttachment(100, -63);
		btnNewButton_1.setLayoutData(fd_btnNewButton_1);
		
		Button button = new Button(shell, SWT.NONE);
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				new PwdChangeWin(shell,SWT.NONE).open();
			}
		});
		
		button.setImage(SWTResourceManager.getImage(LoginWin.class, "/org/eclipse/jface/dialogs/images/message_info.png"));
		FormData fd_button = new FormData();
		fd_button.bottom = new FormAttachment(combo, -68, SWT.BOTTOM);
		fd_button.top = new FormAttachment(label_1, 0, SWT.TOP);
		fd_button.right = new FormAttachment(textNo, 34, SWT.RIGHT);
		fd_button.left = new FormAttachment(textPwd, 6);
		button.setLayoutData(fd_button);
		button.setToolTipText("忘记密码");

	}
	
	public static String returnName() {
        return name;
    }
	
}
