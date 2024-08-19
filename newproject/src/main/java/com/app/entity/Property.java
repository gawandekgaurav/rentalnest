package com.app.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "properties")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Property {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int propertyID;

	@ManyToOne
	@JoinColumn(name = "landlord_id", nullable = false)
	private User landlord;

	@Column(nullable = false)
	private String address;

	@Column(nullable = false)
	private String description;

	@Column(nullable = false)
	private double rent;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private PropertyStatus status;

	@Column(nullable = false)
	private Date availableFrom;

	@Column(nullable = false)
	private Date availableTo;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private HouseType houseType;

	@Column(nullable = false)
	private String cityArea;

	@Column(nullable = false)
	private String imageData;

	public enum HouseType {
		ONE_BHK, TWO_BHK, THREE_BHK
	}

	public enum PropertyStatus {
		AVAILABLE, RENTED
	}
}
