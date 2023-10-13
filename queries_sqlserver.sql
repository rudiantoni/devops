--
-- Create a function that receives a string and returns all numbers inside that string
-- or null if there's no numbers otherwise
--

DROP FUNCTION dbo.get_numbers_or_null;
CREATE FUNCTION dbo.get_numbers_or_null(@STR_INPUT VARCHAR(MAX))
RETURNS VARCHAR(MAX)
AS
BEGIN
  DECLARE @RESULT VARCHAR(MAX) = '';
  DECLARE @SEARCH_IDX INT = 1;
  DECLARE @FOUND_IDX INT = 0;
  
  WHILE @SEARCH_IDX < LEN(@STR_INPUT) + 1 BEGIN
    SET @FOUND_IDX = PATINDEX('%[0-9]%', SUBSTRING(@STR_INPUT, @SEARCH_IDX, 1));
    IF @FOUND_IDX > 0 BEGIN
          SET @RESULT += SUBSTRING(@STR_INPUT, @SEARCH_IDX, 1);
        END
    
  SET @SEARCH_IDX += 1;
  END

    IF @RESULT = ''
        SET @RESULT = NULL;
    
    RETURN @RESULT;

END

-- Testing queries
SELECT dbo.get_numbers_or_null('a205s 1as56d1a 06d 1sklndajklnd\ sd! \@ #~') as func_result
SELECT dbo.get_numbers_or_null('as asda d sklndajklnd\ sd! \@ #~') as func_result
