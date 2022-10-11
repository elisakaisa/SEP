package com.example.sep.model;

import java.io.Serializable;

public class Event implements Serializable {
    String recordNumber, clientName, eventType, fromDate, toDate, comments, FMreview, status;
    int attendees, budget, level, id;
    boolean decorations, food, parties, drinks, photo;
    // level: variable to set at which level of the company the request is going

    // level variables
    public static final int CS_CREATED = 0;
    public static final int SCS_APPROVED = 1;
    public static final int FM_REVIEWED = 2;
    public static final int AM_APPROVED = 3;

    // status variables
    public static final String PRELIMINARY = "Preliminary";
    public static final String APPROVED = "Approved";
    public static final String IN_PROGRESS = "In progress";
    public static final String ARCHIVED = "Archived";
    public static final String DISMISSED = "Dismissed";

    public Event(
            /* constructor */
            int newId,
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
            boolean newPhoto,
            int newLevel,
            String newStatus) {
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
        level = newLevel;
        status = newStatus;
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

    public int getId() { return id; }

    public void setId(int id) {
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

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void addLevel() { this.level = level + 1; }

    public void setFMReview(String review) {
        FMreview = review;
    }
    public String getFMReview() { return FMreview; }

    public void setStatus(String status) { this.status = status; }
    public String getStatus() { return status; }
}
