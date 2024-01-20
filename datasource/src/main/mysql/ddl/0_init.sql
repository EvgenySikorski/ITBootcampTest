USE testbootcamp;
CREATE TABLE users (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    firstname VARCHAR(255) NOT NULL,
    lastname VARCHAR(255) NOT NULL,
    surname VARCHAR(255) NOT NULL,
	email VARCHAR(255) NOT NULL,
    role VARCHAR(255) NOT NULL,
    UNIQUE (email)
);

