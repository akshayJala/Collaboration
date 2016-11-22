package com.niit.collaboration.model;

import javax.persistence.Id;

public class Event {
	@Id
private int eventId;
private String eventLocation;
private String eventDate;
private String description;
public int getEventId() {
	return eventId;
}
public void setEventId(int eventId) {
	this.eventId = eventId;
}
public String getEventLocation() {
	return eventLocation;
}
public void setEventLocation(String eventLocation) {
	this.eventLocation = eventLocation;
}
public String getEventDate() {
	return eventDate;
}
public void setEventDate(String eventDate) {
	this.eventDate = eventDate;
}
public String getDescription() {
	return description;
}
public void setDescription(String description) {
	this.description = description;
}
}
