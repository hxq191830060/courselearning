create table if not exists question(
    questionId int primary key auto_increment,
    courseId int,
    description varchar(1000),
    answer varchar(1000),
    analysis varchar(1000)
);
