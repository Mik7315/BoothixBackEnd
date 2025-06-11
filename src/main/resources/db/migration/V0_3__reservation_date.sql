ALTER TABLE reservations
    ADD COLUMN event_time VARCHAR(50),
    ADD COLUMN installation_time VARCHAR(50),
    ADD COLUMN pick_up_time VARCHAR(50);