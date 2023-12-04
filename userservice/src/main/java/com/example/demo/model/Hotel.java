package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import com.fasterxml.jackson.annotation.JsonProperty;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class Hotel {
	
	
	@JsonProperty("id")
    private  String id;
	
	@JsonProperty("name")
    private  String name;
	
	@JsonProperty("location")
    private  String location;
	
	
	@JsonProperty("about")
    private  String about;


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getLocation() {
		return location;
	}


	public void setLocation(String location) {
		this.location = location;
	}


	public String getAbout() {
		return about;
	}


	public void setAbout(String about) {
		this.about = about;
	}
	
	
	
    
    

}
