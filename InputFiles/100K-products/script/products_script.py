# Created by Konstantinos Kadoglou (kitos14)
# For the purpose of my thesis "Incorporation of Spark functionality in an On-Line Analytical Processing system"

import random
import decimal
import csv
import datetime

N = 100000

def main():
    products()
    sales()
    dates()
    locations()

def products():
    with open('products.csv', 'w') as writeFile:
        writer = csv.writer(writeFile)
        row = ["product_id", "name", "price", "subcategory", "category"]
        writer.writerow(row)

        for i in range (0,N):
            # 1. Product ID, Fixed : (0,1,2,...)
            # From 0 to N-1
            product_id = i
            # 2. Product Name, Example : (product37,product65,...)
            # From 1 to 100
            name = "product" + str(random.randint(1,100))
            # 3. Product Price, Runs 10 times to become random, Example : (124,359,...)
            # From 1 to 500
            times = 0
            while(times<10):
                price = decimal.Decimal(random.randrange(100,50000))/100
                if (price < 50):
                    times += 10
                elif(price < 100):
                    times += 10
                elif(price < 200):
                    times += 10
                elif(price < 300):
                    times += 10
                elif(price < 400):
                    times += 5
                else:
                    times += 4
            # 4. Product Subcategory, Example : (subcategory2,subcategory4,...)
            # From 1 to 5
            subcategory = "subcategory" + str(random.randint(1,5))
            # 5. Product Category, Example : (category8,category3,...)
            # From 1 to 10
            category = "category" + str(random.randint(1,10))
            # Export final object
            # Repeated for N times
            row = [product_id, name, price, subcategory, category]
            writer.writerow(row)
    writeFile.close()

def sales():
    with open('sales.csv', 'w') as writeFile:
        writer = csv.writer(writeFile)
        row = ["sale_id", "location_id", "product_id", "date_id", "sales"]
        writer.writerow(row)

        for i in range (0,N):
            # 1. Sales ID, FIXED : (0,1,2,...)
            # From 0 to N-1
            sale_id = i
            # 2. Sales Location, Example : (32,57,...)
            # From 0 to N-1
            # Its a foreign key from the location table
            location_id = random.randint(0,N-1)
            # 3. Sales Product, Example : (101,303,...)
            # From 0 to N-1
            # Its a foreign key from the product table
            product_id = random.randint(0,N-1)
            # 4. Sales Date, Example : (22,76,...)
            # From 0 to N-1
            # Its a foreign key from the date table
            date_id = i
            # 5. Sales Sales, Example : (3,5,...)
            # From 0 to 10000
            # The number of sales this sale has.
            sales = random.randint(1,10000)
            # Export final object
            # Repeated for N times
            row = [sale_id, location_id, product_id, date_id, sales]
            writer.writerow(row)
    writeFile.close()

def dates():
    with open('dates.csv', 'w') as writeFile:
        writer = csv.writer(writeFile)
        row = ["date_id", "date", "day", "month", "year"]
        writer.writerow(row)

        for i in range (0,N):
            # 1. Date ID, FIXED : (0,1,2,...)
            # From 0 to N-1
            date_id = i
            # 2. Day, Example : (20,7,...)
            # From 1 to 27
            day = random.randint(1,27)
            # 3. Month, Example : (1,4,...)
            # From 1 to 11
            month = random.randint(1,11)
            # 4. Year, Example : (2010,2012,...)
            # From 2010 to 2019
            year = random.randint(2010,2019)
            # 5. Date, Example : (20/3/2014,9/5/2012,...)
            # From the above
            date = str(day) + "/" + str(month) + "/" + str(year)
            # Export final object
            # Repeated for N times
            row = [date_id, date, day, month, year]
            writer.writerow(row)
    writeFile.close()

def locations():
    with open('locations.csv', 'w') as writeFile:
        writer = csv.writer(writeFile)
        row = ["location_id", "city", "state"]
        writer.writerow(row)

        for i in range (0,N):
            # 1. Location ID, FIXED : (0,1,2,...)
            # From 0 to N-1
            location_id = i
            # 2. City, Example : (city3,city1,...)
            # From 0 to 10
            city = "city" + str(random.randint(0,10))
            # 3. State, Example : (state3,state5,...)
            # From 0 to 10
            state = "state" + str(random.randint(0,10))
            # Export final object
            # Repeated for N times
            row = [location_id, city, state]

            writer.writerow(row)
    writeFile.close()

if __name__ == '__main__':
   main()
