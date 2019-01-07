CREATE TABLE Question (
  id int(11) NOT NULL AUTO_INCREMENT,
  title varchar(64) NOT NULL,
  content varchar(128) NOT NULL,
  step int(3) NOT NULL,
  args varchar(64) NOT NULL,
  stdout text NOT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE Components (
  id int(11) NOT NULL AUTO_INCREMENT,
  type tinyint unsigned NOT NULL,
  name varchar(64) NOT NULL,
  PRIMARY KEY (id)
);