-- 1757. 可回收且低脂的产品
/*
  太简单了，没什么好说的
  执行用时：544 ms, 在所有 MySQL 提交中击败了68.48%的用户
*/
# Write your MySQL query statement below
select product_id from Products
where low_fats = 'Y' and recyclable = 'Y'
