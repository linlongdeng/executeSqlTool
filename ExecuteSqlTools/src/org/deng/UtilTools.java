package org.deng;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
/**
 * 工具类
 * @author deng
 *
 */
public class UtilTools {
	/**
	 * 提示对话框
	 * @param msg
	 * @param shell
	 */
    public static void showInfoMessageBox(String msg, Shell shell){
    	MessageBox messageBox = new MessageBox(shell, SWT.OK|SWT.ICON_INFORMATION);
    	messageBox.setMessage(msg);
    	messageBox.setText("提示");
    	messageBox.open();
    	
    }
    /**
     * 询问对话框
     * @param msg
     * @param shell
     */
    public static int showConfirmMessageBox(String msg, Shell shell){
    	MessageBox messageBox = new MessageBox(shell, SWT.YES|SWT.NO|SWT.CANCEL|SWT.ICON_QUESTION);
    	messageBox.setMessage(msg);
    	messageBox.setText("警告");
    	int result = messageBox.open();
    	return result;
    	
    }
}
