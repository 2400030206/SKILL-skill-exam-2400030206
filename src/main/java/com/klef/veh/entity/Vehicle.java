package com.klef.veh.entity;

import java.time.LocalDateTime;
import java.util.jar.Attributes.Name;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.AnyDiscriminatorImplicitValues.Strategy;
import org.hibernate.annotations.Changelog.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Table(name = "vechicles_table")
@Entity
public class Vehicle {
	@Id
	@GeneratedValue(strategy =GenerationType.IDENTITY)
	@Column(name="id",nullable = false,length = 200)
  private	long vid;
	@Column(name="model",nullable = false,length = 200)
  private   String model;
	@Column(name="company",nullable = false,length = 200)
  private   String company;
	@Column(name="fueltype",nullable = false)
  private  String fuelType;
	@Column(name="color",nullable = false,length = 200)
  private String color;
	@Column(name="price",nullable = false)
  private double price;
	@CreationTimestamp
  private LocalDateTime createdAt ;
	@UpdateTimestamp
  private LocalDateTime updatedAt;
	public long getVid() {
		return vid;
	}
	public void setVid(long vid) {
		this.vid = vid;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getFuelType() {
		return fuelType;
	}
	public void setFuelType(String fuelType) {
		this.fuelType = fuelType;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}
	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}
	@Override
	public String toString() {
		return "Vehicle [vid=" + vid + ", model=" + model + ", company=" + company + ", fuelType=" + fuelType
				+ ", color=" + color + ", price=" + price + "]";
	}
}
