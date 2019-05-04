

INSERT INTO users values (1, 'admin', '$2a$10$LzTBhVkAhXiEEtoQXG.7tuI7PbstJsLo1TgCReROa8bxSq2KdM.Qe');
INSERT INTO users VALUES (2, 'user', '$2a$10$uEKREqKx3pq4kC4Q1EqIxuwGtTVGyPT9YllAK0A.HHl6lenaW0fVq');

INSERT INTO user_permition VALUES  (1,'ROLE_GET');
INSERT INTO user_permition VALUES  (2,'ROLE_POST');

insert into users_x_user_permition values (1,1); 
insert into users_x_user_permition values (1,2);
