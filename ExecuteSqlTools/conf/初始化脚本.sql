--ɾ��ǩԼ��
delete from yhqyzzzz ;
--ɾ���ʻ���
delete from yhzhzzzz ;
--ɾ�����ʻ���
delete from yhkzhzzz ;
--ɾ����ֵ��
delete from jykczjiz ;
--ɾ�����ѱ�
delete from jyxflszz;
--ɾ���賵��
delete from jyjclszz;
--ɾ��������
delete from jyhclszz;
--ɾ���쳣������־
delete from csycclrz;
--ɾ��ϵͳ��־
delete from system_log;
--ɾ��ԤԼ��Ϣ
delete from yhtkdjxx;
--ɾ��ǩԼ��Լ
delete from yhqyzzzz;
--ɾ���ʽ������Ϣ
delete from xm_fund_dispatch;
--ɾ��ͳ�Ʊ���
delete from xm_money_report;
--ɾ��������
delete from jyrhmdls;
delete from jyrhmdzz;
--ɾ����ʽ������
--ִ�к����������洢����
--�Ȳ���һ����¼���Ա�ִ�к���������
Insert into jyrhmdls
   (XHZZZ, KSKHZ, JSKHZ, ZHSJZ, LXZZZ, ZTZZZ, HKSLZ, HMDBB, CZYZZ)
 Values
   (1, '000000000000', '000000000000', '20141027', '0', 
    '1', 1, '20141031000000', 'admin');
    
--ִ�к������洢����    
begin
PROC_JYHMDSJ;
end;
/
commit;
exit;

