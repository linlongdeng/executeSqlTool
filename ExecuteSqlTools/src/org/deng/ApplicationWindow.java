package org.deng;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.wb.swt.SWTResourceManager;

public class ApplicationWindow {
	
	private Logger logger = Logger.getLogger(ApplicationWindow.class);
	
	static{
		
		PropertyConfigurator.configure(ApplicationWindow.class.getResource("/conf/log4j.properties"));
	}
	

	protected Shell shell;
	private Text text;
	
	private Display display;
	
	private Thread thread;

	private  Button button;
	/**
	 * Launch the application.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			ApplicationWindow window = new ApplicationWindow();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		display= Display.getDefault();
		createContents();
		//居中
		shell.setLocation(display.getClientArea().width / 2
				- shell.getShell().getSize().x / 2,
				display.getClientArea().height / 2 - shell.getSize().y / 2);
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
		shell.setSize(540, 302);
		shell.setText("SWT Application");
		shell.setLayout(new FormLayout());

		Label label = new Label(shell, SWT.NONE);
		FormData fd_label = new FormData();
		fd_label.top = new FormAttachment(0, 28);
		fd_label.left = new FormAttachment(0, 10);
		label.setLayoutData(fd_label);
		label.setText("\u8981\u6267\u884C\u7684\u811A\u672C\u6587\u4EF6\uFF1A");

		text = new Text(shell, SWT.BORDER);
		FormData fd_text = new FormData();
		fd_text.top = new FormAttachment(0, 25);
		fd_text.left = new FormAttachment(label, 18);
		text.setLayoutData(fd_text);

		Button btnNewButton = new Button(shell, SWT.NONE);
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				FileDialog fileDialog = new FileDialog(shell, SWT.OPEN);
				fileDialog.setText("请选择要打开的单个文件");
				fileDialog.setFilterPath("");
				fileDialog.setFilterExtensions(new String[] { "*.sql" });
				fileDialog.setFilterNames(new String[] { "Sql Files (*.sql)" });
				String fileName = fileDialog.open();
				if(fileName != null){
					text.setText(fileName);
					
				}

			}
		});
		fd_text.right = new FormAttachment(100, -158);
		FormData fd_btnNewButton = new FormData();
		fd_btnNewButton.top = new FormAttachment(label, -5, SWT.TOP);
		fd_btnNewButton.left = new FormAttachment(text, 6);
		fd_btnNewButton.right = new FormAttachment(100, -95);
		btnNewButton.setLayoutData(fd_btnNewButton);
		btnNewButton.setText("\u6D4F \u89C8");

		final Button btnNewButton_1 = new Button(shell, SWT.NONE);
		btnNewButton_1.setFont(SWTResourceManager.getFont("微软雅黑", 13,
				SWT.NORMAL));
		btnNewButton_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				final String fileName = text.getText();
				if("".equals(fileName)){
					UtilTools.showInfoMessageBox("脚本文件不能为空", shell);
					return;
				}
								//执行脚本文件
					thread = new Thread( new Runnable() {
						public void run() {
							try {
								display.syncExec(new Runnable() {
									public void run() {
										btnNewButton_1.setText("执行中");
										button.setEnabled(true);
										btnNewButton_1.setEnabled(false);
										
									}
								});
								ExecuteSql.getInstance().execute(fileName);
								
								display.syncExec(new Runnable() {
									public void run() {
										btnNewButton_1.setText("开始");
										btnNewButton_1.setEnabled(true);
										
									}
								});
								
							} catch (Exception e) {
								// TODO Auto-generated catch block
								logger.error("执行SQL脚本文件出错", e);
							}
							
						}
					});
					thread.start();
					
			}
		});
		FormData fd_btnNewButton_1 = new FormData();
		fd_btnNewButton_1.left = new FormAttachment(0, 128);
		fd_btnNewButton_1.top = new FormAttachment(text, 71);
		btnNewButton_1.setLayoutData(fd_btnNewButton_1);
		btnNewButton_1.setText("\u5F00 \u59CB");

		 button= new Button(shell, SWT.NONE);
		button.setEnabled(false);
		fd_btnNewButton_1.right = new FormAttachment(100, -329);
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if(thread != null && thread.isAlive()){
					ExecuteSql.getInstance().stop();
					btnNewButton_1.setText("开始");
					btnNewButton_1.setEnabled(true);
					button.setEnabled(false);
				}
			}
		});
		button.setFont(SWTResourceManager.getFont("微软雅黑", 13, SWT.NORMAL));
		FormData fd_button = new FormData();
		fd_button.left = new FormAttachment(btnNewButton_1, 10);
		fd_button.top = new FormAttachment(text, 71);
		button.setLayoutData(fd_button);
		button.setText(" \u4E2D \u6B62");

		Button button_1 = new Button(shell, SWT.NONE);
		button_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				shell.close();
				System.exit(0);
			}
		});
		fd_button.right = new FormAttachment(100, -247);
		button_1.setFont(SWTResourceManager.getFont("微软雅黑", 13, SWT.NORMAL));
		FormData fd_button_1 = new FormData();
		fd_button_1.right = new FormAttachment(text, 0, SWT.RIGHT);
		fd_button_1.top = new FormAttachment(text, 71);
		fd_button_1.left = new FormAttachment(button, 19);
		button_1.setLayoutData(fd_button_1);
		button_1.setText("\u9000 \u51FA");

	}
}
