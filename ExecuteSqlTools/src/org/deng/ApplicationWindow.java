package org.deng;


import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
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
import org.eclipse.swt.events.ShellAdapter;
import org.eclipse.swt.events.ShellEvent;
import org.eclipse.wb.swt.SWTResourceManager;

public class ApplicationWindow {

	private Logger logger = Logger.getLogger(ApplicationWindow.class);

	static {

		PropertyConfigurator.configure(ApplicationWindow.class
				.getResource("/conf/log4j.properties"));
	}

	protected Shell shell;
	private Text text;

	private Display display;

	private Thread thread;
	
	private OracleDialog oracleDialog;

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
		display = Display.getDefault();
		createContents();
		// 居中
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
		shell = new Shell(SWT.DIALOG_TRIM);
		shell.setSize(540, 302);
		shell.setText("SWT Application");
		shell.setLayout(new FormLayout());
		//创建菜单
		createMenu(shell);
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
				if (fileName != null) {
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
				if ("".equals(fileName)) {
					UtilTools.showInfoMessageBox("脚本文件不能为空", shell);
					return;
				}
				// 执行脚本文件
				thread = new Thread(new Runnable() {
					public void run() {
						try {
							display.syncExec(new Runnable() {
								public void run() {
									btnNewButton_1.setText("执行中");
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
							//由于和UI线程不是同一线程，要用这种方式来打开提示框
							display.syncExec(new Runnable() {
								public void run() {
									UtilTools.showInfoMessageBox(
											"程序执行出错，请查询日志文件", shell);
									btnNewButton_1.setText("开始");
									btnNewButton_1.setEnabled(true);

								}
							});
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
		fd_btnNewButton_1.right = new FormAttachment(100, -329);

		Button button_1 = new Button(shell, SWT.NONE);
		button_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				shell.close();
			}
		});
		button_1.setFont(SWTResourceManager.getFont("微软雅黑", 13, SWT.NORMAL));
		FormData fd_button_1 = new FormData();
		fd_button_1.right = new FormAttachment(100, -158);
		fd_button_1.left = new FormAttachment(btnNewButton_1, 101);
		fd_button_1.top = new FormAttachment(text, 71);
		button_1.setLayoutData(fd_button_1);
		button_1.setText("\u9000 \u51FA");
		shell.addShellListener(new ShellAdapter() {

			@Override
			public void shellClosed(ShellEvent e) {

				if (!btnNewButton_1.isEnabled()) {
					int answer = UtilTools.showConfirmMessageBox(
							"程序正在执行中，是否要强制退出？", shell);
					if (answer == SWT.YES) {
						e.doit = true;
						System.exit(0);
					}
					// 否则不让退出
					else {
						e.doit = false;
					}
				}
			}

		});

	}
	
	public void createMenu(final Shell shell){
		Menu menu = new Menu(shell, SWT.BAR);
		MenuItem menuItem = new MenuItem(menu,SWT.CASCADE);
		menuItem.setText("设置");
		Menu confureMenu = new Menu(shell, SWT.DROP_DOWN);
		menuItem.setMenu(confureMenu);;
		MenuItem confiureItem = new MenuItem(confureMenu, SWT.PUSH);
		confiureItem.setText("配置数据库");
		confiureItem.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				if(oracleDialog == null){
					oracleDialog = new OracleDialog(shell,SWT.DIALOG_TRIM);
				}
				oracleDialog.open();
			}
			
			
		});
		MenuItem exitItem = new MenuItem(confureMenu, SWT.PUSH);
		exitItem.setText("退出");
		exitItem.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				shell.close();
			}

		
		});
		shell.setMenuBar(menu);
		
	}
}
