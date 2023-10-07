DROP PROCEDURE IF EXISTS my_procedure;
create
definer = root@localhost procedure my_procedure()
BEGIN
SELECT GROUP_CONCAT(table_schema, '.', table_name) INTO @tables
FROM information_schema.tables
WHERE table_schema = 'wanted_labs'; -- specify DB name here.
IF @tables IS NOT NULL THEN
SET @tables = CONCAT('DROP TABLE ', @tables);
PREPARE stmt FROM @tables;
EXECUTE stmt;
DEALLOCATE PREPARE stmt;
END IF;
END;