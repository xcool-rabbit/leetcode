-- 175. 组合两个表
/*
  左连接
  执行用时：496 ms, 在所有 MySQL 提交中击败了15.28%的用户
*/
# Write your MySQL query statement below
select firstName, lastName, city, state
from
Person as p left join Address as a on p.personId = a.personId
