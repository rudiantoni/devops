-- ##################################################
-- Annotations
-- 
-- Default datatype for the function NOW(): TIMESTAMP WITH TIMEZONE (TIMESTAMPTZ)
-- Default datatype for date subtraction: INTERVAL
--
-- ##################################################

--
-- Current time
--
-- Using function NOW()
SELECT NOW()

-- Using variable CURRENT_TIMESTAMP
SELECT CURRENT_TIMESTAMP

--
-- Count a column by its unique values amount
-- 
--
-- Using subquery with GROUP BY
SELECT COUNT(1) FROM (
	SELECT column_name FROM table_name GROUP BY column_name
) AS unique_result

-- Using subquery with DISTINCT
SELECT COUNT(1) FROM (
	SELECT DISTINCT column_name FROM table_name
) AS unique_count

-- Using COUNT DISTINCT
SELECT COUNT(DISTINCT column_name) AS unique_count FROM table_name;

--
-- Mathematics
-- 
--
-- Return the absolute value (without positive or negative signal) from a number
SELECT ABS(-10)
SELECT ABS(10)
SELECT ABS(-10.5)
SELECT ABS(10.5)


--
-- Other operations
--
--
-- Convert VARCHAR (string) to TIMESTAMP WITH TIMEZONE (TIMESTAMPTZ)
SELECT '2005-01-01 00:00:00'::TIMESTAMPTZ

-- Convert VARCHAR (string) to INTERVAL
SELECT '3 years'::INTERVAL
SELECT '3 years 2 months'::INTERVAL
SELECT '3 years 2 months 9 days'::INTERVAL
SELECT '3 years 2 months 9 days 14 hours'::INTERVAL
SELECT '3 years 2 months 9 days 14 hours 25 minutes'::INTERVAL
SELECT '3 years 2 months 9 days 14 hours 25 minutes 35 seconds'::INTERVAL
SELECT '3 years 2 months 9 days 14 hours 25 minutes 35 seconds 258 milliseconds'::INTERVAL
SELECT '3 years 2 months 9 days 14 hours 25 minutes 35 seconds 258 milliseconds 359 microseconds'::INTERVAL
-- Other ways to write INTERVALs
SELECT '3 y 2 mon 9 d 14 h 25 min 35 sec'::INTERVAL

-- Detect a type from a data
SELECT PG_TYPEOF(your_data_or_column)
SELECT PG_TYPEOF(NOW())

-- Calc difference between dates as INTERVAL, (differencing negative and positive)
SELECT '2005-01-01 00:00:00'::TIMESTAMPTZ - '2000-01-01 00:00:00'::TIMESTAMPTZ
SELECT '2000-01-01 00:00:00'::TIMESTAMPTZ - '2005-01-01 00:00:00'::TIMESTAMPTZ

-- Extract certain datadays portion from an INTERVAL
-- EPOCH (total number of seconds in the INTERVAL)
SELECT EXTRACT(EPOCH FROM '3 years 2 months 3 days 14 hours 25 minutes 35 seconds'::INTERVAL);
SELECT EXTRACT(CENTURY FROM '3 years 2 months 3 days 14 hours 25 minutes 35 seconds'::INTERVAL);
SELECT EXTRACT(DECADE FROM '3 years 2 months 3 days 14 hours 25 minutes 35 seconds'::INTERVAL);
SELECT EXTRACT(YEAR FROM '3 years 2 months 3 days 14 hours 25 minutes 35 seconds'::INTERVAL);
SELECT EXTRACT(QUARTER FROM '3 years 2 months 3 days 14 hours 25 minutes 35 seconds'::INTERVAL);
SELECT EXTRACT(MONTH FROM '3 years 2 months 3 days 14 hours 25 minutes 35 seconds'::INTERVAL);
SELECT EXTRACT(DAY FROM '3 years 2 months 3 days 14 hours 25 minutes 35 seconds'::INTERVAL);
SELECT EXTRACT(HOUR FROM '3 years 2 months 3 days 14 hours 25 minutes 35 seconds'::INTERVAL);
SELECT EXTRACT(MINUTE FROM '3 years 2 months 3 days 14 hours 25 minutes 35 seconds'::INTERVAL);
SELECT EXTRACT(SECOND FROM '3 years 2 months 3 days 14 hours 25 minutes 35 seconds'::INTERVAL);
SELECT EXTRACT(MILLISECOND FROM '3 years 2 months 3 days 14 hours 25 minutes 35 seconds'::INTERVAL);
SELECT EXTRACT(MICROSECOND FROM '3 years 2 months 3 days 14 hours 25 minutes 35 seconds'::INTERVAL);







