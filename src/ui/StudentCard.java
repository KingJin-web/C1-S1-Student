package ui;



import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.wb.swt.SWTResourceManager;


import bean.Student;
import biz.BizException;
import biz.StuBiz;
import swing2swt.layout.BorderLayout;
import util.IOHelper;
import util.SwtHelper;

import org.eclipse.swt.widgets.Composite;

import java.io.IOException;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;

import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.FormData;

public class StudentCard {

	private static String name = LoginWin.returnName();
	protected Shell shell;
	private Text text;
	private Text text_1;
	private Text text_2;
	private Text text_3;
	private Text text_4;
	private Text text_5;
	private Text text_6;
	private Text text_7;
	
	

	/**
	 * Launch the application.
	 *
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			StudentCard window = new StudentCard();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 * @throws BizException 
	 */
	public void open() throws BizException {
		Display display = Display.getDefault();
		createContents();
		SwtHelper.center(shell);
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
	 * @throws BizException 
	 */
	protected void createContents() throws BizException {
		shell = new Shell();
		shell.setImage(SWTResourceManager.getImage(StudentCard.class, "/imges/xs.jpg"));
		shell.setSize(675, 371);
		shell.setText("欢迎" + name + "同学");
		shell.setLayout(new BorderLayout(0, 0));

		Composite composite = new Composite(shell, SWT.NONE);
		composite.setLayoutData(BorderLayout.WEST);
		composite.setLayout(new GridLayout(1, false));

		Label lblNewLabel = new Label(composite, SWT.NONE);
		GridData gd_lblNewLabel = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_lblNewLabel.widthHint = 110;
		gd_lblNewLabel.heightHint = 164;
		lblNewLabel.setLayoutData(gd_lblNewLabel);
		lblNewLabel.setImage(SWTResourceManager.getImage("D:\\stuImg\\" + new StuBiz().RetFile(name)));
		new Label(composite, SWT.NONE);

		Button button_1 = new Button(composite, SWT.NONE);
		button_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				StuBiz sb = new StuBiz();
				String url = "", fileName = null;
				FileDialog fileselect = new FileDialog(shell);
				fileselect.setFilterPath("C:\\Users\\82427\\Desktop\\img");// 设置默认的路径
				fileselect.setText("选择图片");// 设置对话框的标题
				fileselect.setFilterNames(new String[] { "文本文件 (*.jpg*)", "所有文件(*.*)" });// 设置扩展名
				fileselect.setFilterExtensions(new String[] { "*.jpg", "*.*" });// 设置文件扩展名
				url = fileselect.open();
				fileName =IOHelper.retFileName(url);

				try {

					// IOHelper.copyFile(url, System.getProperty("user.dir") + "\\src\\img\\" +
					// fileName);
					if(fileName!=null && !"".equals(fileName)) {
						IOHelper.copyFile(url, "D:\\stuImg\\" + fileName);
						sb.updaeImg(fileName, name);
						query(name);
					}
				} catch (IOException ioException) {
					ioException.printStackTrace();
				} catch (BizException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} finally {
					try {
						lblNewLabel.setImage(SWTResourceManager.getImage("D:\\stuImg\\" + new StuBiz().RetFile(name)));
					} catch (BizException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				System.out.println("选择的图片路径：" + url);
			}
		});

		button_1.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, false, false, 1, 1));
		button_1.setText("更换照片");
		lblNewLabel.addPaintListener(new PaintListener() {
			public void paintControl(PaintEvent e) {
				// 代码如下 https://blog.csdn.net/qq_39047789/article/details/100503878

				// 获取到控件中的图片
				org.eclipse.swt.graphics.Image image = lblNewLabel.getImage();
				int h = lblNewLabel.getBounds().height; // 获取控件的高
				int w = lblNewLabel.getBounds().width; // 获取控件的宽度
				int height = image.getBounds().height; // 获取原图片的高度
				int width = image.getBounds().width; // 获取原图片的初始宽度
				// 绘制图片，将原图片按照控件的高度和宽度进行重绘
				e.gc.drawImage(image, 0, 0, width, height, 0, 0, w, h);
			}
		});

		Composite composite_1 = new Composite(shell, SWT.NONE);
		composite_1.setLayoutData(BorderLayout.CENTER);
		composite_1.setLayout(new GridLayout(5, false));
		new Label(composite_1, SWT.NONE);

		Label label = new Label(composite_1, SWT.NONE);
		label.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		label.setText("姓名");

		text = new Text(composite_1, SWT.BORDER);
		text.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		new Label(composite_1, SWT.NONE);
		new Label(composite_1, SWT.NONE);
		new Label(composite_1, SWT.NONE);

		Label label_1 = new Label(composite_1, SWT.NONE);
		label_1.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		label_1.setText("学院");

		text_1 = new Text(composite_1, SWT.BORDER);
		text_1.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Label label_5 = new Label(composite_1, SWT.NONE);
		label_5.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		label_5.setText("性别");

		text_5 = new Text(composite_1, SWT.BORDER);
		text_5.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		new Label(composite_1, SWT.NONE);

		Label label_2 = new Label(composite_1, SWT.NONE);
		label_2.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		label_2.setText("班级");

		text_2 = new Text(composite_1, SWT.BORDER);
		text_2.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Label label_6 = new Label(composite_1, SWT.NONE);
		label_6.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		label_6.setText("年龄");

		text_6 = new Text(composite_1, SWT.BORDER);
		text_6.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		new Label(composite_1, SWT.NONE);

		Label label_3 = new Label(composite_1, SWT.NONE);
		label_3.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		label_3.setText("学号");

		text_3 = new Text(composite_1, SWT.BORDER);
		text_3.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		new Label(composite_1, SWT.NONE);
		new Label(composite_1, SWT.NONE);
		new Label(composite_1, SWT.NONE);

		Label label_4 = new Label(composite_1, SWT.NONE);
		label_4.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		label_4.setText("余额");

		text_4 = new Text(composite_1, SWT.BORDER);
		text_4.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Button button_2 = new Button(composite_1, SWT.NONE);
		button_2.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				inMoney();
			}
		});
		button_2.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		button_2.setText("充值");
		new Label(composite_1, SWT.NONE);
		new Label(composite_1, SWT.NONE);

		Label label_7 = new Label(composite_1, SWT.NONE);
		label_7.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		label_7.setText("邮箱");

		text_7 = new Text(composite_1, SWT.BORDER);
		text_7.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Button button = new Button(composite_1, SWT.NONE);
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				ChangeEmailDialog cd = new ChangeEmailDialog(shell, SWT.NONE);
				cd.setEmail(text_7.getText());
				cd.setName(name);
				cd.open();
			}
		});
		button.setText("修改邮箱");
		new Label(composite_1, SWT.NONE);

		Composite composite_2 = new Composite(shell, SWT.NONE);
		composite_2.setLayoutData(BorderLayout.SOUTH);
		composite_2.setLayout(new GridLayout(8, false));

		Composite composite_3 = new Composite(composite_2, SWT.NONE);
		GridData gd_composite_3 = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_composite_3.widthHint = 217;
		composite_3.setLayoutData(gd_composite_3);

		Button btnNewButton_2 = new Button(composite_2, SWT.NONE);
		btnNewButton_2.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {

				StudentCard.this.shell.dispose();
				returnName();
				new CertificateWin().open();

			}
		});
		btnNewButton_2.setText("荣誉查询");
		new Label(composite_2, SWT.NONE);

		Button btnNewButton = new Button(composite_2, SWT.NONE);
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				StudentCard.this.shell.dispose();
				new SMessageWin().open();
			}
		});
		btnNewButton.setText("我要吐槽");
		new Label(composite_2, SWT.NONE);

		Button btnNewButton_1 = new Button(composite_2, SWT.NONE);
		btnNewButton_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				StudentCard.this.shell.dispose();

				new SEmailWin().open();
			}
		});
		btnNewButton_1.setText("校长信箱");
		new Label(composite_2, SWT.NONE);
		
		Button button_3 = new Button(composite_2, SWT.NONE);
		GridData gd_button_3 = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_button_3.widthHint = 80;
		button_3.setLayoutData(gd_button_3);
		button_3.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				shell.dispose();
				new LoginWin().open();
			}
		});
		button_3.setText("返回");
		query(name);
		// (name);
		// query(name);
	}

	protected void inMoney() {
		inMoneyDialog inmg = new inMoneyDialog(shell, SWT.NONE);
		inmg.setName(name);
		inmg.open();
	}

	String[] college = new String[] { "计信学院", "经管学院", "材化学院", "数能学院", "电信学院", "建工学院", "外国语学院", "机械学院" };

	public void query(String sname) throws BizException {
		StuBiz sBiz = new StuBiz();
		List<Student> list = sBiz.select(sname);
		for (Student student : list) {
			text.setText(student.getSname());
			text_1.setText(college[student.getCid() - 1]);
			text_2.setText(student.getSclass());
			text_3.setText(String.valueOf(student.getSno()));
			text_4.setText(String.valueOf(student.getSmo()));
			text_5.setText(student.getSsex());
			text_6.setText(String.valueOf(student.getSage()));
			text_7.setText(student.getSma());
		}
	}

	public void changeImg() {
		String url = "", fileName = null;
		FileDialog fileselect = new FileDialog(shell);
		fileselect.setFilterPath("F:\\java\\eclipse-workspace\\Yc-s1\\src\\img");// 设置默认的路径
		fileselect.setText("选择图片");// 设置对话框的标题
		fileselect.setFilterNames(new String[] { "文本文件 (*.jpg*)", "所有文件(*.*)" });// 设置扩展名
		fileselect.setFilterExtensions(new String[] { "*.jpg", "*.*" });// 设置文件扩展名

		fileName = IOHelper.retFileName(url);
		url = fileselect.open();
		try {
			IOHelper.copyFile(url, System.getProperty("user.dir") + "\\img\\" + fileName);

		} catch (IOException ioException) {
			ioException.printStackTrace();
		}
		System.out.println("选择的图片路径：" + url);
		
		try {
			query(name);
		} catch (BizException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static String returnName() {
		return name;
	}

}
