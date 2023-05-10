CREATE TABLE `users` (
    `username`      VARCHAR(64)     NOT NULL,
    `password`      VARCHAR(128)    NOT NULL,
    `role`          VARCHAR(8)      NOT NULL,
    `disabled`      BOOLEAN         DEFAULT FALSE,
    PRIMARY KEY (`username`)
);

INSERT INTO `users`
(`username`, `password`, `role`, `disabled`)
VALUES
('admin@example.com','{bcrypt}$2a$12$.dT5J2XuDrAH7Q3Uc.B9Quc855XzELiIyM0QeaRkZxn7G7YBZhyNO','ADMIN', FALSE),
('staff@example.com','{bcrypt}$2a$12$N5e6.2VF101ZKa1KKqFb5.4Eizm8Fv9gSwSFA1UTaMv11LVc.SAzK','STAFF', FALSE),
('user@example.com','{noop}user-password','USER', FALSE),
('disabled@example.com','{noop}disabled-password','USER', TRUE);
