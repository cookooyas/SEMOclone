-- 테이블 락 상황 START

-- USER 1
UPDATE LOCK_TEST
SET name = 'Lock Test'
WHERE id = 1;

-- USER 2
UPDATE LOCK_TEST
SET name = 'Waiting'
WHERE id = 1;

-- 테이블 락 상황 END