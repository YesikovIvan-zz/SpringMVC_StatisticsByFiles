create table statistics_file (
   id INT NOT NULL AUTO_INCREMENT,
   name VARCHAR(50) NOT NULL,
   content VARCHAR(10000) NOT NULL,
   number_of_lines INT NOT NULL,
   longest_word INT NOT NULL,
   shortest_word INT NOT NULL,
   total_length INT NOT NULL,
   average_word_length DOUBLE NOT NULL,
   file LONGBLOB NOT NULL,
   PRIMARY KEY (id)
);