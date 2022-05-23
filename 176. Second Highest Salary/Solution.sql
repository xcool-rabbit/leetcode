-- 176. 第二高的薪水
/*
  就是要注意几个坑，包括null，包括distinct
  执行用时：275 ms, 在所有 MySQL 提交中击败了12.90%的用户
*/
# Write your MySQL query statement below
select
ifnull
(
    (select distinct salary from Employee
    order by salary desc
    limit 1,1), null
) as SecondHighestSalary
