insert into users (username, password, enabled) values ('adam', '$2a$10$Ptvs9mLVFuEOXC4ckHi1weHGLQ7bGwigKQR5Uk9/aONvmKYPnRl8m', true);
insert into authorities (username, authority) values ('adam', 'ROLE_USER');

insert into users (username, password, enabled) values ('jeff', '$2a$10$SRzxfUhAPr3wDJwcIJ/b3ePcJlmaJWOZMafN0dDmAw0ispxdXFi6K', true);
insert into authorities (username, authority) values ('jeff', 'ROLE_ADMIN');