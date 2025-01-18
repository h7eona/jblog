

select * from user;
select * from category;
select * from blog;
select * from post;


desc category;
desc blog;
desc post;

delete from user where id='park';

select 
a.id, a.title, a.contents, 
a.reg_date as regDate, a.category_id as categoryId,
b.id
from post a
inner join category b on a.category_id = b.id
where b.id = 'youyou'
order by a.reg_date;

select * from blog where blog_id = 'youyou';



