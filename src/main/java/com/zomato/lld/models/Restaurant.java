package com.zomato.lld.models;

import java.util.List;

public class Restaurant {
    private String id;
    private String name;
    private String location;
    private List<MenuItem> menu;

    public Restaurant(String id, String name, String location, List<MenuItem> menu) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.menu = menu;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    public List<MenuItem> getMenu() { return menu; }
    public void setMenu(List<MenuItem> menu) { this.menu = menu; }
}
