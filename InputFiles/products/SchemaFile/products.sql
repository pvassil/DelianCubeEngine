CREATE DATABASE  IF NOT EXISTS `products` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `products`;
LOAD DATA INFILE  'C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/users.csv'
INTO TABLE users FIELDS TERMINATED BY ',' LINES TERMINATED BY '\r\n'  IGNORE 1 LINES
(
    user_id, username, age, location, budget
)

CREATE DATABASE  IF NOT EXISTS `products` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `products`;
LOAD DATA INFILE  'C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/products.csv'
INTO TABLE products FIELDS TERMINATED BY ',' LINES TERMINATED BY '\r\n'  IGNORE 1 LINES
(
    product_id, name, price, subcategory, category
)

CREATE DATABASE  IF NOT EXISTS `products` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `products`;
LOAD DATA INFILE  'C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/dates.csv'
INTO TABLE dates FIELDS TERMINATED BY ',' LINES TERMINATED BY '\r\n'  IGNORE 1 LINES
(
    date_id, @var1, day, month, year
)
SET dates.date = STR_TO_DATE(@var1, '%d/%m/%Y')

CREATE DATABASE  IF NOT EXISTS `products` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `products`;
LOAD DATA INFILE  'C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/whishlists.csv'
INTO TABLE whishlists FIELDS TERMINATED BY ',' LINES TERMINATED BY '\r\n'  IGNORE 1 LINES
(
    whishlist_id,user_id,product_id,date_added_id
)

CREATE DATABASE  IF NOT EXISTS `products` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `products`;
LOAD DATA INFILE  'C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/sales.csv'
INTO TABLE sales FIELDS TERMINATED BY ',' LINES TERMINATED BY '\r\n'  IGNORE 1 LINES
(
    sale_id,location_id,product_id,date_added,sales
)

use `products`;
LOAD DATA INFILE 'C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/location.csv'
INTO TABLE location FIELDS TERMINATED BY ',' LINES TERMINATED BY '\r\n' IGNORE 1 LINES
(
    location_id, city, state
)

--> For mac
--> /usr/local/mysql/data/csvs/

--> For Windows
--> C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/