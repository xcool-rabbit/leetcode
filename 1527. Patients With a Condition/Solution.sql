-- 1527. 患某种疾病的患者
/*
  学习了sql中的正则表达式
  执行用时：349 ms, 在所有 MySQL 提交中击败了79.30%的用户
*/
# Write your MySQL query statement below
select patient_id, patient_name, conditions from Patients
where
conditions regexp '^DIAB1|.*\\sDIAB1'
