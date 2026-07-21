package com.zomato.lld.models;

public class DeliveryAgent {
    private String id;
    private String name;
    private String contact;

    public DeliveryAgent(String id, String name, String contact) {
        this.id = id;
        this.name = name;
        this.contact = contact;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getContact() { return contact; }
    public void setContact(String contact) { this.contact = contact; }
}
