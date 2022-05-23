-- 608. 树节点
/*
  三种结点分开识别，然后用union连起来
  我的想法是left join一下就知道谁没有儿子，谁上有老下有小
  我看了下题解有些用in啊，distinct的方法，高下立判嗷
  执行用时：506 ms, 在所有 MySQL 提交中击败了29.23%的用户
*/
# Write your MySQL query statement below
select id, 'Root' as type from tree
where p_id is null
union
select t1.id as id, 'Leaf' as type
from tree as t1 left join tree as t2 on t1.id = t2.p_id
where t2.id is null and t1.p_id is not null
union
select t1.id as id, 'Inner' as type
from tree as t1 left join tree as t2 on t1.id = t2.p_id
where t1.p_id is not null and t2.id is not null
order by id
