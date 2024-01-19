USE testbootcamp;
CREATE TABLE users (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    firstname VARCHAR(20) NOT NULL,
    lastname VARCHAR(40) NOT NULL,
    surname VARCHAR(40) NOT NULL,
	email VARCHAR(50) NOT NULL,
    role VARCHAR(50) NOT NULL,
    UNIQUE (email)
);

