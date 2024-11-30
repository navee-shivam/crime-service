package com.crime.women.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(name = "crime_data", uniqueConstraints = @UniqueConstraint(columnNames = { "state", "year", "crime_type" }))
public class CrimeData {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "state", nullable = false, length = 100)
	private String state;

	@Column(name = "year", nullable = false)
	private Integer year;

	@Column(name = "crime_type", nullable = false, length = 255)
	private String crimeType;

	@Column(name = "count",nullable = false)
	private Integer count;

	// Getters and setters

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public String getCrimeType() {
		return crimeType;
	}

	public void setCrimeType(String crimeType) {
		this.crimeType = crimeType;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	@Override
	public String toString() {
		return "CrimeData{" + "id=" + id + ", state='" + state + '\'' + ", year=" + year + ", crimeType='" + crimeType
				+ '\'' + ", count=" + count + '}';
	}
}
