-- 627. 变更性别
/*
  用case也可以
  执行用时：283 ms, 在所有 MySQL 提交中击败了26.09%的用户
*/
# Write your MySQL query statement below
update Salary
set
sex = if (sex = 'f', 'm', 'f')
