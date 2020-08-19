DROP TABLE IF EXISTS users;
CREATE TABLE users (
   user_id INTEGER PRIMARY KEY AUTOINCREMENT,
   email text NOT NULL,
   password text NOT NULL
);

DROP TABLE IF EXISTS accounts;
CREATE TABLE accounts (
   account_id INTEGER PRIMARY KEY AUTOINCREMENT,
   accountType text NOT NULL,
   user_id INTEGER NOT NULL,
   balance INTEGER NOT NULL DEFAULT 0
);


