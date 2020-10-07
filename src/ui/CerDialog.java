package ui;

import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import swing2swt.layout.BorderLayout;
import util.DBHelper;

import org.eclipse.swt.widgets.Table;

import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.TableItem;

import bean.Certificate;

import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.TableColumn;

public class CerDialog extends Dialog {
	
	private static String sname = MainWin.getname();

	protected Object result;
	protected Shell shell;
	private Table table;
	protected TableItem item;
	public TableItem getItem() { return item; }
	public void setItem(TableItem item) { this.item = item; }

	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	public CerDialog(Shell parent, int style) {
		super(parent, style);
		setText("证书详情");
	}

	/**
	 * Open the dialog.
	 * @return the result
	 */
	public Object open() {
		createContents();
		shell.open();
		shell.layout();
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
		shell = new Shell(getParent(), SWT.DIALOG_TRIM | SWT.MIN | SWT.RESIZE);
		shell.setSize(450, 300);
		shell.setText("证书审批");
		shell.setLayout(new BorderLayout(0, 0));
		
		table = new Table(shell, SWT.BORDER | SWT.FULL_SELECTION);
		table.setLayoutData(BorderLayout.CENTER);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		
		TableColumn tableColumn_4 = new TableColumn(table, SWT.NONE);
		tableColumn_4.setWidth(100);
		tableColumn_4.setText("证书名字");
		
		TableColumn tableColumn = new TableColumn(table, SWT.NONE);
		tableColumn.setWidth(100);
		tableColumn.setText("证书");
		
		TableColumn tableColumn_1 = new TableColumn(table, SWT.NONE);
		tableColumn_1.setWidth(100);
		tableColumn_1.setText("获得者");
		
		TableColumn tableColumn_2 = new TableColumn(table, SWT.NONE);
		tableColumn_2.setWidth(100);
		tableColumn_2.setText("申请时间");
		
		TableColumn tableColumn_3 = new TableColumn(table, SWT.NONE);
		tableColumn_3.setWidth(100);
		tableColumn_3.setText("审批时间");
		
		Composite composite = new Composite(shell, SWT.NONE);
		composite.setLayoutData(BorderLayout.SOUTH);
		composite.setLayout(new GridLayout(11, false));
		new Label(composite, SWT.NONE);
		new Label(composite, SWT.NONE);
		new Label(composite, SWT.NONE);
		new Label(composite, SWT.NONE);
		new Label(composite, SWT.NONE);
		new Label(composite, SWT.NONE);
		new Label(composite, SWT.NONE);
		new Label(composite, SWT.NONE);
		
		Button button = new Button(composite, SWT.NONE);
		button.setText("查看详情");
		new Label(composite, SWT.NONE);
		
		Button button_1 = new Button(composite, SWT.NONE);
		button_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				shell.dispose();
			}
		});
		GridData gd_button_1 = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_button_1.widthHint = 77;
		button_1.setLayoutData(gd_button_1);
		button_1.setText("返回");
		
		getStuCer();
		
	}
	
	public void getStuCer() {
		try {
			String sql = "select * from certificate where sname like ?";
			DBHelper dbh = new DBHelper();
			List<Certificate> list = dbh.query(sql, Certificate.class,sname);
			table.removeAll();
			
			for(Certificate cer : list) {
				TableItem tbItem = new TableItem(table, SWT.NONE);
				tbItem.setText(new String[] {
						""+cer.getZname(),
						""+cer.getSname(),
						""+cer.getTime(),
						""+cer.getSptime()
				});
			}
			
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	public void setName(String sname) {
		// TODO Auto-generated method stub
		
	}

}
