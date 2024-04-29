package com.example.vrexpo;

import java.util.HashMap;
import java.util.Map;

public class Node {
    public String attribute;
    public String label;
    public Map<String, Node> children;

    public Node() {
        this.attribute = null;
        this.label = null;
        this.children = new HashMap<>();
    }
}

