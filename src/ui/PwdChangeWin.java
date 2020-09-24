package ui;

import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;

import java.security.GeneralSecurityException;

import javax.mail.MessagingException;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Text;

import biz.BizException;
import biz.StuBiz;
import util.SwtHelper;

import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormAttachment;

public class PwdChangeWin extends Dialog {

	protected Object result;
	protected Shell shell;
	private Text textName;
	private Text textPwd;
	private Text textPwd2;
	private Text textYzm;

	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	public PwdChangeWin(Shell parent, int style) {
		super(parent, style);
		setText("SWT Dialog");
	}

	/**
	 * Open the dialog.
	 * @return the result
	 */
	public Object open() {
		createContents();
		shell.open();
		shell.layout();
		SwtHelper.center(shell);
		Display display = getParent().getDisplay();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		return result;
	}

	/**
	 * Create contents of the dialog.
	 */
	private void createContents() {
		shell = new Shell(getParent(), SWT.DIALOG_TRIM | SWT.PRIMARY_MODAL);
		shell.setSize(413, 338);
		shell.setLayout(new FormLayout());
		SwtHelper.center(shell);
		
		Label label = new Label(shell, SWT.NONE);
		FormData fd_label = new FormData();
		label.setLayoutData(fd_label);
		label.setBounds(63, 31, 54, 20);
		label.setText("用户名:");
		
		Label label_1 = new Label(shell, SWT.NONE);
		FormData fd_label_1 = new FormData();
		fd_label_1.right = new FormAttachment(label, 0, SWT.RIGHT);
		label_1.setLayoutData(fd_label_1);
		label_1.setText("新密码:");
		label_1.setBounds(63, 88, 54, 20);
		
		Label label_1_1 = new Label(shell, SWT.NONE);
		FormData fd_label_1_1 = new FormData();
		fd_label_1_1.top = new FormAttachment(label, 85);
		fd_label_1_1.right = new FormAttachment(label, 0, SWT.RIGHT);
		label_1_1.setLayoutData(fd_label_1_1);
		label_1_1.setText("确认密码:");
		label_1_1.setBounds(63, 142, 71, 20);
		
		Label label_1_2 = new Label(shell, SWT.NONE);
		FormData fd_label_1_2 = new FormData();
		fd_label_1_2.top = new FormAttachment(label_1_1, 26);
		fd_label_1_2.left = new FormAttachment(label, 0, SWT.LEFT);
		label_1_2.setLayoutData(fd_label_1_2);
		label_1_2.setText("验证码:");
		label_1_2.setBounds(63, 194, 54, 20);
		
		textName = new Text(shell, SWT.BORDER);
		fd_label.top = new FormAttachment(textName, 0, SWT.TOP);
		fd_label.right = new FormAttachment(textName, -24);
		FormData fd_textName = new FormData();
		fd_textName.top = new FormAttachment(0, 64);
		fd_textName.right = new FormAttachment(100, -159);
		textName.setLayoutData(fd_textName);
		textName.setBounds(123, 25, 73, 26);
		
		textPwd = new Text(shell, SWT.BORDER);
		fd_label_1.top = new FormAttachment(textPwd, 3, SWT.TOP);
		FormData fd_textPwd = new FormData();
		fd_textPwd.top = new FormAttachment(textName, 27);
		fd_textPwd.right = new FormAttachment(textName, 0, SWT.RIGHT);
		textPwd.setLayoutData(fd_textPwd);
		textPwd.setBounds(123, 82, 73, 26);
		
		textPwd2 = new Text(shell, SWT.BORDER);
		FormData fd_textPwd2 = new FormData();
		fd_textPwd2.top = new FormAttachment(label_1_1, -3, SWT.TOP);
		fd_textPwd2.left = new FormAttachment(textName, 0, SWT.LEFT);
		textPwd2.setLayoutData(fd_textPwd2);
		textPwd2.setBounds(140, 136, 73, 26);
		
		textYzm = new Text(shell, SWT.BORDER);
		FormData fd_textYzm = new FormData();
		fd_textYzm.top = new FormAttachment(label_1_2, 0, SWT.TOP);
		fd_textYzm.right = new FormAttachment(textName, 0, SWT.RIGHT);
		textYzm.setLayoutData(fd_textYzm);
		textYzm.setBounds(140, 191, 73, 26);
		
		Button button = new Button(shell, SWT.NONE);
		FormData fd_button = new FormData();
		fd_button.top = new FormAttachment(label_1_2, -5, SWT.TOP);
		fd_button.left = new FormAttachment(textYzm, 30);
		button.setLayoutData(fd_button);
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				StuBiz sb = new StuBiz();
				try {
					sb.YanZhengma(textName.getText());
				} catch (GeneralSecurityException | MessagingException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		button.setBounds(246, 189, 98, 30);
		button.setText("获取验证码");
		
		Button button_1 = new Button(shell, SWT.NONE);
		button_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				StuBiz sb = new StuBiz();
				try {
					if (sb.changePw(textName.getText(), textPwd.getText(), textPwd2.getText(),Integer.valueOf(textYzm.getText()))) {
						
					} else {

					}
				} catch (BizException | GeneralSecurityException | MessagingException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		FormData fd_button_1 = new FormData();
		fd_button_1.bottom = new FormAttachment(100, -10);
		fd_button_1.left = new FormAttachment(label, 0, SWT.LEFT);
		button_1.setLayoutData(fd_button_1);
		button_1.setBounds(57, 246, 98, 30);
		button_1.setText("确认");
		
		Button btnNewButton = new Button(shell, SWT.NONE);
		FormData fd_btnNewButton = new FormData();
		fd_btnNewButton.top = new FormAttachment(button_1, 0, SWT.TOP);
		fd_btnNewButton.left = new FormAttachment(button_1, 98);
		btnNewButton.setLayoutData(fd_btnNewButton);
		btnNewButton.setBounds(246, 246, 98, 30);
		btnNewButton.setText("取消");

	}
}
