INSERT INTO users
(username, password, role)
VALUES
('admin@example.com','{bcrypt}$2a$12$.dT5J2XuDrAH7Q3Uc.B9Quc855XzELiIyM0QeaRkZxn7G7YBZhyNO','ADMIN'),
('staff@example.com','{bcrypt}$2a$12$N5e6.2VF101ZKa1KKqFb5.4Eizm8Fv9gSwSFA1UTaMv11LVc.SAzK','STAFF'),
('user@example.com','{noop}user-password','USER');
