-- 196. 删除重复的电子邮箱
/*
  注意连接的时候，如果没有on，那就是笛卡尔积，p1中的每一项都会和p2全表组成一行
  妙哇
  执行用时：900 ms, 在所有 MySQL 提交中击败了36.08%的用户
*/
# Please write a DELETE statement and DO NOT write a SELECT statement.
# Write your MySQL query statement below
delete p1
from Person as p1 join Person as p2
where p1.email = p2.email and
p1.id > p2.id
