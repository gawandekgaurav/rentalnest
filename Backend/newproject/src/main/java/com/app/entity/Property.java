package com.app.entity;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "properties")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Property {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int propertyID;

	@ManyToOne(fetch = FetchType.LAZY)
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
	private PropertyStatus status; // Enum for status

	@Column(nullable = false)
	private LocalDate availableFrom;

	@Column(nullable = false)
	private LocalDate availableTo;

	@ElementCollection
	@CollectionTable(name = "property_images", joinColumns = @JoinColumn(name = "property_id"))
	@Column(name = "image_url")
	private List<String> imageURLs;

	public enum PropertyStatus {
		AVAILABLE, RENTED
	}
}
