USE `10K-products`;

set unique_checks = 0;
set foreign_key_checks = 0;
set sql_log_bin=0;

LOAD DATA INFILE  'C:/Users/kosta/Desktop/FinalVersionDCE/DelianCubeEngine/InputFiles/10K-products/Data/products.csv'
INTO TABLE products FIELDS TERMINATED BY ',' LINES TERMINATED BY '\r\n'  IGNORE 1 LINES
(
    product_id, name, price, subcategory, category
);

LOAD DATA INFILE  'C:/Users/kosta/Desktop/FinalVersionDCE/DelianCubeEngine/InputFiles/10K-products/Data/dates.csv'
INTO TABLE dates FIELDS TERMINATED BY ',' LINES TERMINATED BY '\r\n'  IGNORE 1 LINES
(
    date_id, @var1, day, month, year
)
SET dates.date = STR_TO_DATE(@var1, '%d/%m/%Y');

LOAD DATA INFILE 'C:/Users/kosta/Desktop/FinalVersionDCE/DelianCubeEngine/InputFiles/10K-products/Data/locations.csv'
INTO TABLE locations FIELDS TERMINATED BY ',' LINES TERMINATED BY '\r\n' IGNORE 1 LINES
(
    location_id, city, state
);

LOAD DATA INFILE  'C:/Users/kosta/Desktop/FinalVersionDCE/DelianCubeEngine/InputFiles/10K-products/Data/sales.csv'
INTO TABLE sales FIELDS TERMINATED BY ',' LINES TERMINATED BY '\r\n'  IGNORE 1 LINES
(
    sale_id,location_id,product_id,date_id,sales
);