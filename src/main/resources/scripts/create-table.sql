CREATE TABLE crime_data (
    id BIGSERIAL PRIMARY KEY,
    state VARCHAR(100) NOT NULL,
    year INT NOT NULL,
    crime_type VARCHAR(255) NOT NULL,
    count INT NOT NULL,
    CONSTRAINT uq_state_year_crime_type UNIQUE (state, year, crime_type)
);
