package com.example.sep.model;

import java.io.Serializable;

public class Event implements Serializable {
    String id, recordNumber, clientName, eventType, fromDate, toDate, comments;
    int attendees, budget;
    boolean decorations, food, parties, drinks, photo;

    public Event(
            /* constructor */
            String newId,
            String newRecordNumber,
            String newClientName,
            String newEventType,
            String newFromDate,
            String newToDate,
            String newComments,
            int newAttendees,
            int newBudget,
            boolean newDecorations,
            boolean newFood,
            boolean newParties,
            boolean newDrinks,
            boolean newPhoto) {
        id = newId;
        recordNumber = newRecordNumber;
        clientName = newClientName;
        eventType = newEventType;
        fromDate = newFromDate;
        toDate = newToDate;
        comments = newComments;
        attendees = newAttendees;
        budget = newBudget;
        decorations = newDecorations;
        food = newFood;
        parties = newParties;
        drinks = newDrinks;
        photo = newPhoto;
    }

    public boolean isDecorations() {
        return decorations;
    }

    public void setDecorations(boolean decorations) {
        this.decorations = decorations;
    }

    public boolean isFood() {
        return food;
    }

    public void setFood(boolean food) {
        this.food = food;
    }

    public boolean isParties() {
        return parties;
    }

    public void setParties(boolean parties) {
        this.parties = parties;
    }

    public boolean isDrinks() {
        return drinks;
    }

    public void setDrinks(boolean drinks) {
        this.drinks = drinks;
    }

    public boolean isPhoto() {
        return photo;
    }

    public void setPhoto(boolean photo) {
        this.photo = photo;
    }

    public String getId() { return id; }

    public void setId(String id) {
        this.id = id;
    }

    public String getRecordNumber() { return recordNumber; }

    public void setRecordNumber(String recordNumber) { this.recordNumber = recordNumber; }

    public String getClientName() { return clientName; }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public String getFromDate() {
        return fromDate;
    }

    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }

    public String getToDate() {
        return toDate;
    }

    public void setToDate(String toDate) {
        this.toDate = toDate;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public int getAttendees() {
        return attendees;
    }

    public void setAttendees(int attendees) {
        this.attendees = attendees;
    }

    public int getBudget() {
        return budget;
    }

    public void setBudget(int budget) {
        this.budget = budget;
    }
}
