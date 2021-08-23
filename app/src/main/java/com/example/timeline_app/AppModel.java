package com.example.timeline_app;

public class AppModel implements Comparable<AppModel>{

    private String name;
    private String description;
    private String created_at;
    private String forks;
    private String open_issues;
    private String watchers;
    private String url;

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

    public String getForks() {
        return forks;
    }

    public void setForks(String forks) {
        this.forks = forks;
    }

    public String getOpen_issues() {
        return open_issues;
    }

    public void setOpen_issues(String open_issues) {
        this.open_issues = open_issues;
    }

    public String getWatchers() {
        return watchers;
    }

    public void setWatchers(String watchers) {
        this.watchers = watchers;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
