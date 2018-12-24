CREATE TABLE line_delays (
 id BIGINT PRIMARY KEY,
 line_id BIGINT ,
 delay BIGINT
);

CREATE TABLE lines (
 line_id BIGINT PRIMARY KEY,
 line_name VARCHAR
);

CREATE TABLE stops (
 stop_id BIGINT PRIMARY KEY,
 x DECIMAL(5, 2),
 y DECIMAL(5, 2)
);

CREATE TABLE line_timings (
 id IDENTITY,
 line_id BIGINT,
 stop_id BIGINT,
 time TIMESTAMP
);

ALTER TABLE line_timings
ADD FOREIGN KEY (line_id)
REFERENCES lines(line_id);

ALTER TABLE line_timings
ADD FOREIGN KEY (stop_id)
REFERENCES stops(stop_id);


