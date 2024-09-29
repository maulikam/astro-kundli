import psycopg2
import csv
from timezonefinder import TimezoneFinder

# Database connection parameters
db_params = {
    'dbname': 'mydatabase',
    'user': 'myuser',
    'password': 'secret',
    'host': 'localhost',  # Update if needed when using Docker
    'port': '5432'
}

# Path to your CSV file
csv_file_path = 'cities_import.csv'  # Ensure this path is accessible from where you're running the script

try:
    # Connect to the PostgreSQL database
    connection = psycopg2.connect(**db_params)
    cursor = connection.cursor()

    # Insert cities data from CSV
    with open(csv_file_path, newline='', encoding='utf-8') as csvfile:
        reader = csv.DictReader(csvfile)
        for row in reader:
            country = row['COUNTRY']
            city_name = row['CITY']
            latitude = row['LATITUDE']
            longitude = row['LONGITUDE']

            if country and city_name and latitude and longitude:
                # Insert the city data into the database
                cursor.execute("""
                    INSERT INTO cities (country, city_name, latitude, longitude)
                    VALUES (%s, %s, %s, %s)
                    ON CONFLICT (city_name) DO NOTHING
                """, (country, city_name, float(latitude), float(longitude)))
                print(f"Inserted city: {city_name}, Country: {country}")

    # Fetch all cities without timezones
    cursor.execute("SELECT id, latitude, longitude FROM cities WHERE timezone IS NULL")
    cities = cursor.fetchall()

    tf = TimezoneFinder()

    # Update timezone for each city
    for city in cities:
        city_id, latitude, longitude = city
        if latitude is not None and longitude is not None:
            timezone = tf.timezone_at(lat=latitude, lng=longitude)
            if timezone:
                cursor.execute("UPDATE cities SET timezone = %s WHERE id = %s", (timezone, city_id))
                print(f"Updated city ID {city_id} with timezone {timezone}")

    # Commit the changes
    connection.commit()

except Exception as e:
    print(f"An error occurred: {e}")

finally:
    # Close the connection
    if cursor:
        cursor.close()
    if connection:
        connection.close()
