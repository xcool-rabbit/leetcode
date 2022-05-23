-- 1965. 丢失信息的雇员
/*
  可以说是非常的巧妙了，利用了union all不去重的特点
  统计在两张表里一共只出现过一次的，就是丢失信息的
  having是用在group by之后的筛选
  执行用时：571 ms, 在所有 MySQL 提交中击败了82.92%的用户
*/
# Write your MySQL query statement below
select employee_id
from
(
    select employee_id from Employees
    union all
    select employee_id from Salaries
) as merge
group by employee_id
having count(employee_id) = 1
order by employee_id
