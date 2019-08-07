/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.tmbackend.models;

import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

public class Lists {

    private List<String> types = Arrays.asList("Bug", "Improvement", "New Feature");
    private List<String> priorities = Arrays.asList("Blocker", "Critical", "Major", "Minor", "Trivial");
    private List<String> statuses = Arrays.asList("Open", "Re-open", "In-Progress", "Resolved", "Close", "QA Blocker", "UAT Phase");

    public Lists() {
    }

    public List<String> getTypes() {
        return types;
    }

    public void setTypes(List<String> types) {
        this.types = types;
    }

    public List<String> getPriorities() {
        return priorities;
    }

    public void setPriorities(List<String> priorities) {
        this.priorities = priorities;
    }

    public List<String> getStatuses() {
        return statuses;
    }

    public void setStatuses(List<String> statuses) {
        this.statuses = statuses;
    }

    @Override
    public String toString() {
        return "Lists{" + "types=" + types + ", priorities=" + priorities + ", statuses=" + statuses + '}';
    }

}
