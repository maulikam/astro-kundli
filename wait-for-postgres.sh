#!/bin/sh
# This script waits for the PostgreSQL server to be available before starting the application
while ! pg_isready -h postgres -p 5432 -U myuser; do
  sleep 1
done
