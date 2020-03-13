- Steps to use it : 

1. Transfer the python script inside the /Data folder

2. Run on terminal :
python products_script.py

3. After the files have been created run :
sed -i '/^$/d' sales.csv dates.csv locations.csv products.csv

This step is to remove the empty lines. File gets smaller and also avoids
any errors when using the csv files.

4. Re-transfer the "products_scirpt.py" file back to the folder /script