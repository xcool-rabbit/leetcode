-- 1873. 计算特殊奖金
/*
  sql语句里条件句的写法
  执行用时：580 ms, 在所有 MySQL 提交中击败了73.50%的用户
*/
# Write your MySQL query statement below
select
employee_id,
if (employee_id % 2 = 1 and name not like 'M%', salary, 0) as bonus
from Employees
order by employee_id
/*
  条件语句的另外一种写法，这个明显没有if简单啊
  执行用时：587 ms, 在所有 MySQL 提交中击败了66.00%的用户
*/
# Write your MySQL query statement below
select
employee_id,
case
when employee_id % 2 = 1 and name not like 'M%'
then salary
else 0
end
as bonus
from Employees
order by employee_id
