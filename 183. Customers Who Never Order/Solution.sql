-- 183. 从不订购的客户
/*
  很自然的想到的方法
  但是not in我认为是不能命中索引的，因为它做笛卡尔积之后，要用不等于
  执行用时：788 ms, 在所有 MySQL 提交中击败了5.02%的用户
*/
# Write your MySQL query statement below
select name as Customers from Customers
where
id
not in
(
    select CustomerId from Orders
)
/*
  左连接！
  执行用时：678 ms, 在所有 MySQL 提交中击败了5.99%的用户
*/
# Write your MySQL query statement below
select name as Customers
from Customers as C
left join
Orders as O
on C.Id = O.CustomerId
where o.Id is null
