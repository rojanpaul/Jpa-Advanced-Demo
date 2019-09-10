insert into course (id,name,created_date,last_updated_date,is_deleted) 
values (10001,'English 2.0',sysdate(),sysdate(),false);
insert into course (id,name,created_date,last_updated_date,is_deleted) 
values (10002,'Science 2.0',sysdate(),sysdate(),false);
insert into course (id,name,created_date,last_updated_date,is_deleted) 
values (10003,'Maths 1.0',sysdate(),sysdate(),false);

insert into passport (id,number,created_date,last_updated_date) 
values (40001,'E123456',sysdate(),sysdate());
insert into passport (id,number,created_date,last_updated_date) 
values (40002,'S123421',sysdate(),sysdate());
insert into passport (id,number,created_date,last_updated_date) 
values (40003,'J876321',sysdate(),sysdate());

insert into student (id,name,passport_id, created_date,last_updated_date) 
values (20001,'Rini',40001,sysdate(),sysdate());
insert into student (id,name,passport_id,created_date,last_updated_date) 
values (20002,'Rojan',40002,sysdate(),sysdate());
insert into student (id,name,passport_id,created_date,last_updated_date) 
values (20003,'Jancy',40003,sysdate(),sysdate());



insert into review (id,rating,description,course_id,created_date,last_updated_date) 
values (50001,'FIVE','Excellent',10001,sysdate(),sysdate());
insert into review (id,rating,description,course_id,created_date,last_updated_date) 
values (50002,'FOUR','Very Good',10001,sysdate(),sysdate());
insert into review (id,rating,description,course_id,created_date,last_updated_date) 
values (50003,'THREE','Good',10003,sysdate(),sysdate());

insert into student_course (student_id,course_id) 
values (20001,10001);
insert into student_course (student_id,course_id) 
values (20002,10001);
insert into student_course (student_id,course_id) 
values (20003,10001);
insert into student_course (student_id,course_id) 
values (20001,10003);