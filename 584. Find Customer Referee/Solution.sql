-- 584. 寻找用户推荐人
/*
  这里要注意mysql是三值逻辑，除了true和false，还有null
  而null需要用is null来判断
  还有就是SQL的题，执行时间都很不稳定
  看一下题解思路正确就行了，没必要纠结时间问题
  执行用时：509 ms, 在所有 MySQL 提交中击败了45.01%的用户
*/
# Write your MySQL query statement below
select name from customer
where referee_id != 2 or referee_id is null
/*
  这个其实是可以的，WA的原因是顺序
  WA
*/
# Write your MySQL query statement below
select name from customer
where referee_id != 2
union
select name from customer
where referee_id is null
