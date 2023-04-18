```sql
  CREATE PROCEDURE proc_vars()
  SPECIFIC proc_vars
  LANGUAGE SQL
  BEGIN
     
    DECLARE v_rcount INTEGER;

    DECLARE v_max DECIMAL (9,2);

    DECLARE v_adate, v_another  DATE;    		 

    DECLARE v_total INTEGER DEFAULT 0;           -- (1)

    DECLARE v_rowsChanged BOOLEAN DEFAULT FALSE; -- (2)

    SET v_total = v_total + 1;                   -- (3)
	
    SELECT MAX(salary)                           -- (4)
      INTO v_max FROM employee;  	    		 

    VALUES CURRENT_DATE INTO v_date;             -- (5)

    SELECT CURRENT DATE, CURRENT DATE            -- (6)
         INTO v_adate, v_another
    FROM SYSIBM.SYSDUMMY1;

    DELETE FROM T; 
    GET DIAGNOSTICS v_rcount = ROW_COUNT;        -- (7)

    IF v_rcount > 0 THEN                         -- (8)
      SET is_done = TRUE;
    END IF;
  END
  ```
