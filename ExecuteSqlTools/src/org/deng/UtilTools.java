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
    public static void showInfoMessageBox(String msg, Shell shell){
    	MessageBox messageBox = new MessageBox(shell, SWT.OK|SWT.ICON_INFORMATION);
    	messageBox.setMessage(msg);
    	messageBox.setText("提示");
    	messageBox.open();
    	
    }
}
