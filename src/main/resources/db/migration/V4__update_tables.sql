alter table USERS add column post_id int not null;
alter table USERS add foreign key (post_id) references POSTS(ID);
alter table POSTS add column comment_id int not null;
alter table POSTS add foreign key (comment_id) references COMMENTS(ID);