-- 1484. 按日期分组销售产品
/*
  学习了group by的用法，注意distinct
  执行用时：442 ms, 在所有 MySQL 提交中击败了54.70%的用户
*/
# Write your MySQL query statement below
select
sell_date,
count(distinct product) as num_sold,
group_concat(distinct product) as products
from Activities
group by sell_date
order by sell_date
