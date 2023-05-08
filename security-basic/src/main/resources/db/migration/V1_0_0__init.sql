CREATE TABLE `users` (
    `username` VARCHAR(64) NOT NULL,
    `password` VARCHAR(128) NOT NULL,
    `role` VARCHAR(8) NOT NULL,
    PRIMARY KEY (`username`)
);
