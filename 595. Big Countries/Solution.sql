-- 595. 大的国家
/*
  这个写法大家都能想到
  执行用时：238 ms, 在所有 MySQL 提交中击败了78.27%的用户
*/
# Write your MySQL query statement below
select name, population, area from World
where
area >= 3000000 or
population >= 25000000
/*
  用union连接，这样可以命中索引
  刚才那个or，是无法命中索引的
  执行用时：240 ms, 在所有 MySQL 提交中击败了72.55%的用户
*/
# Write your MySQL query statement below
select name, population, area from World
where
area >= 3000000
union
select name, population, area from World
where
population >= 25000000
