package com.example.timeline_app;

public class AppModel implements Comparable<AppModel>{

    private String name;
    private String description;
    private String created_at;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        if (description.equals("null")) {
            this.description = "No description";
        }
        else {
            this.description = description;
        }
    }

    public String getCreated_At() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    @Override
    public int compareTo(AppModel o) {
        return o.getCreated_At().compareTo(getCreated_At()); // used in the MainActivity to sort modelRecyclerArrayList by date
    }
}
