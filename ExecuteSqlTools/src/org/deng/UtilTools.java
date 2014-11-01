package org.deng;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
/**
 * ������
 * @author deng
 *
 */
public class UtilTools {
	/**
	 * ��ʾ�Ի���
	 * @param msg
	 * @param shell
	 */
    public static void showInfoMessageBox(String msg, Shell shell){
    	MessageBox messageBox = new MessageBox(shell, SWT.OK|SWT.ICON_INFORMATION);
    	messageBox.setMessage(msg);
    	messageBox.setText("��ʾ");
    	messageBox.open();
    	
    }
    /**
     * ѯ�ʶԻ���
     * @param msg
     * @param shell
     */
    public static int showConfirmMessageBox(String msg, Shell shell){
    	MessageBox messageBox = new MessageBox(shell, SWT.YES|SWT.NO|SWT.CANCEL|SWT.ICON_QUESTION);
    	messageBox.setMessage(msg);
    	messageBox.setText("����");
    	int result = messageBox.open();
    	return result;
    	
    }
}
