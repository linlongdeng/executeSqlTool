--删除签约表
delete from yhqyzzzz ;
--删除帐户表
delete from yhzhzzzz ;
--删除卡帐户表
delete from yhkzhzzz ;
--删除充值表
delete from jykczjiz ;
--删除消费表
delete from jyxflszz;
--删除借车表
delete from jyjclszz;
--删除还车表
delete from jyhclszz;
--删除异常处理日志
delete from csycclrz;
--删除系统日志
delete from system_log;
--删除预约信息
delete from yhtkdjxx;
--删除签约解约
delete from yhqyzzzz;
--删除资金调度信息
delete from xm_fund_dispatch;
--删除统计报表
delete from xm_money_report;
--删除黑名单
delete from jyrhmdls;
delete from jyrhmdzz;
--删除正式黑名单
--执行黑名单升级存储过程
--先插入一条记录，以便执行黑名单升级
Insert into jyrhmdls
   (XHZZZ, KSKHZ, JSKHZ, ZHSJZ, LXZZZ, ZTZZZ, HKSLZ, HMDBB, CZYZZ)
 Values
   (1, '000000000000', '000000000000', '20141027', '0', 
    '1', 1, '20141031000000', 'admin');
    
--执行黑名单存储过程    
begin
PROC_JYHMDSJ;
end;
/
commit;
exit;

