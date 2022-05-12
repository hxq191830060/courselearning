create table if not exists exam(
    examId int primary key auto_increment,
    courseId int,
    name varchar(100),
    description varchar(1000),
    startTime DATETIME,
    endTime DATETIME
);

create table if not exists exam_question(
    id int primary key auto_increment,
    examId int,
    questionId int
)
