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
-- Mathematical operations
-- 
--

-- Return the absolute value (without positive or negative signal) from a number
SELECT ABS(-10)
SELECT ABS(10)
SELECT ABS(-10.5)
SELECT ABS(10.5)


