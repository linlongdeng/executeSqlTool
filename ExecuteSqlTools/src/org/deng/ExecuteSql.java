package org.deng;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

/**
 * ִ��SQL�ļ��ű���
 * 
 * @author deng
 * 
 */
public class ExecuteSql {
	private static final Logger logger = Logger.getLogger(ExecuteSql.class);;

	private static String ORACLE_FILE = "conf/oracle.properties";
	private static String USERNAME;
	private static String PASSWORD;
	private static String IP;
	private static String PORT;
	private static String SERVICENAME;
	private static ExecuteSql instance;
	
	private Boolean stopFlag = false;
	public void stop(){
		stopFlag = true;
	}
	
	private void start(){
		stopFlag = false;
	}

	private ExecuteSql() {
	}

	/**
	 * ��ȡִ��SQL�ű��ļ���ʵ��
	 * 
	 * @return
	 */
	public static ExecuteSql getInstance() {
		if (instance == null) {
			instance = new ExecuteSql();
		}
		return instance;
	}

	/**
	 * ʹ��sqlplus ����Oracleִ�нű�
	 * 
	 * @param userName
	 * @param password
	 * @param ip
	 * @param port
	 * @param serviceName
	 * @param fileName
	 * @throws Exception
	 */
	private void executeCmd(String userName, String password, String ip,
			String port, String serviceName, String fileName) throws Exception {
		start();
		Runtime runtime = Runtime.getRuntime();
		String cmd = "sqlplus " + userName + "/" + password + "@//" + ip + ":"
				+ port + "/" + serviceName + "  @" + fileName + " ";
		logger.debug("�����ǣ�" + cmd);
		Process process = runtime.exec(cmd);
		InputStream is = process.getInputStream();
		InputStreamReader ir = new InputStreamReader(is);
		BufferedReader br = new BufferedReader(ir);
		String line = null;
		while ((line = br.readLine()) != null && !stopFlag) {
			logger.debug(line);
		}

		br.close();

	}

	/**
	 * ��ʼ��Oracle�����ļ�
	 * 
	 * @throws IOException
	 */
	public void init() throws IOException {
		File file = new File("conf/oracle.properties");
		FileInputStream fs = new FileInputStream(ORACLE_FILE);
		Properties properties = new Properties();
		properties.load(fs);
		fs.close();
		USERNAME = properties.getProperty("USERNAME");
		PASSWORD = properties.getProperty("PASSWORD");
		IP = properties.getProperty("IP");
		PORT = properties.getProperty("PORT");
		SERVICENAME = properties.getProperty("SERVICENAME");
	}

	/**
	 * ִ�нű��ļ�
	 * 
	 * @param fileName
	 *            �ű��ļ�
	 * @throws Exception
	 */
	public void execute(String fileName) throws Exception {
		init();
		executeCmd(USERNAME, PASSWORD, IP, PORT, SERVICENAME, fileName);
	}

}
