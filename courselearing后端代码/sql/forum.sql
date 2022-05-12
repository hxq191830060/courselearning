create table if not exists post(
    postId int auto_increment,
    courseId int,
    userId int,
    createTime DATETIME,
    title varchar(50),
    content varchar(1000),
    primary key (postId),
    key (courseId),
    key(userId)
);

create table if not exists reply(
    postId int,
    replyId int auto_increment,
    userId int,
    createTime DATETIME,
    content varchar(1000),
    primary key (replyId),
    key(postId)
);
