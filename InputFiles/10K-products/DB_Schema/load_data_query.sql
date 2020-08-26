USE `10K-products`;

set unique_checks = 0;
set foreign_key_checks = 0;
set sql_log_bin=0;

LOAD DATA INFILE  'C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/10KDB/products.csv'
INTO TABLE products FIELDS TERMINATED BY ',' LINES TERMINATED BY '\r\n'  IGNORE 1 LINES
(
    product_id, name, price, subcategory, category, `ALL`
);

LOAD DATA INFILE  'C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/10KDB/dates.csv'
INTO TABLE dates FIELDS TERMINATED BY ',' LINES TERMINATED BY '\r\n'  IGNORE 1 LINES
(
    date_id, @var1, day, month, year, `ALL`
)
SET dates.date = STR_TO_DATE(@var1, '%d/%m/%Y');

LOAD DATA INFILE 'C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/10KDB/locations.csv'
INTO TABLE locations FIELDS TERMINATED BY ',' LINES TERMINATED BY '\r\n' IGNORE 1 LINES
(
    location_id, city, state, `ALL`
);

LOAD DATA INFILE  'C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/10KDB/sales.csv'
INTO TABLE sales FIELDS TERMINATED BY ',' LINES TERMINATED BY '\r\n'  IGNORE 1 LINES
(
    sale_id,location_id,product_id,date_id,sales
);