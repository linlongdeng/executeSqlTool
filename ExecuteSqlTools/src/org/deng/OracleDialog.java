package org.deng;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

public class OracleDialog extends Dialog {

	protected Object result;
	protected Shell shell;
	private Text text;
	private Label label;
	private Text text_1;

	/**
	 * Create the dialog.
	 * 
	 * @param parent
	 * @param style
	 */
	public OracleDialog(Shell parent, int style) {
		super(parent, style);
		setText("SWT Dialog");
	}

	/**
	 * Open the dialog.
	 * 
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
		shell = new Shell(getParent(), getStyle());

		shell.setSize(431, 279);
		/*
		 * Display display = getParent().getDisplay();
		 * shell.setLocation(display.getClientArea().width / 2 -
		 * shell.getShell().getSize().x / 2, display.getClientArea().height / 2
		 * - shell.getSize().y / 2);
		 */

		Rectangle parentBounds = getParent().getBounds();
		Rectangle shellBounds = shell.getBounds();
		//把对话框位置放在在父窗口上面一点
		shell.setLocation(parentBounds.x
				+ (parentBounds.width - shellBounds.width) / 2 -100, parentBounds.y
				+ (parentBounds.height - shellBounds.height) / 2 -100);
		shell.setText("\u6570\u636E\u5E93\u914D\u7F6E");

		GridLayout gl_shell = new GridLayout();
		gl_shell.numColumns = 2;
		gl_shell.marginTop = 10;
		gl_shell.marginRight = 30;
		gl_shell.marginLeft = 10;
		gl_shell.verticalSpacing = 10;
		gl_shell.horizontalSpacing = 10;
		shell.setLayout(gl_shell);
		shell.setLayoutData(new GridData(SWT.FILL, SWT.BOTTOM, true, true, 2, 3));
		Label lblNewLabel = new Label(shell, SWT.NONE);
		GridData gd_lblNewLabel = new GridData(SWT.RIGHT, SWT.CENTER, false,
				false, 1, 1);
		gd_lblNewLabel.widthHint = 50;
		gd_lblNewLabel.minimumWidth = 30;
		lblNewLabel.setLayoutData(gd_lblNewLabel);
		lblNewLabel.setText("IP:");

		text = new Text(shell, SWT.BORDER);
		text.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		label = new Label(shell, SWT.NONE);
		label.setText("\u7AEF\u53E3\uFF1A");

		text_1 = new Text(shell, SWT.BORDER);
		GridData gd_text_1 = new GridData(SWT.LEFT, SWT.CENTER, true, false, 1,
				1);
		gd_text_1.widthHint = 100;
		text_1.setLayoutData(gd_text_1);

	}

}
