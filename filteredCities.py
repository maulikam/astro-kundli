import csv

input_file = 'allCountriesCSV.csv'
output_file = 'cities_import.csv'

with open(input_file, mode='r') as infile, open(output_file, mode='w', newline='') as outfile:
    reader = csv.DictReader(infile)
    writer = csv.writer(outfile)

    # Write header
    writer.writerow(['country', 'city_name', 'latitude', 'longitude'])

    # Filter rows and write the relevant columns to the output CSV
    for row in reader:
        country = row['COUNTRY']
        city_name = row['CITY']
        latitude = row['LATITUDE']
        longitude = row['LONGITUDE']

        # Only consider non-empty city_name
        if city_name:
            writer.writerow([country, city_name, latitude, longitude])
