-- 1667. 修复表中的名字
/*
  又学会了sql中的字符处理，字符串索引是1-index
  执行用时：655 ms, 在所有 MySQL 提交中击败了38.27%的用户
*/
# Write your MySQL query statement below
select user_id,
concat(upper(left(name, 1)), lower(substring(name, 2))) as name
from Users
order by user_id
