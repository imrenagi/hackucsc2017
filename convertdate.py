import csv
import sys

f = open('test.csv', 'rt')
try:
    reader = csv.reader(f)
    for row in reader:
    	parts = row.split(",");
        id, timestamp, year, day, holidays, local_event, item_id, price = parts;
        print(id);
finally:
    f.close()