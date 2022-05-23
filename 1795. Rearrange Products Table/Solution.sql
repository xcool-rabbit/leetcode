-- 1795. 每个产品在不同商店的价格
/*
  拓展了一种新的思路，原来sql还能这么写
  执行用时：529 ms, 在所有 MySQL 提交中击败了85.03%的用户
*/
# Write your MySQL query statement below
select product_id, 'store1' as store, store1 as price from Products
where store1 is not null
union all
select product_id, 'store2' as store, store2 as price from Products
where store2 is not null
union all
select product_id, 'store3' as store, store3 as price from Products
where store3 is not null
