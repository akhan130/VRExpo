package com.example.vrexpo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DecisionTreeClassifier {
    public Node train(List<String[]> features, List<String> labels) {
        return buildTree(features, labels, new ArrayList<>(Arrays.asList("PatientCondition", "PatientPhobia", "PatientPTSD", "TherapSpecialization")));
    }

    private Node buildTree(List<String[]> features, List<String> labels, List<String> attributes) {
        // Create a new node
        Node node = new Node();

        // If all labels are the same, return a leaf node
        if (areAllLabelsSame(labels)) {
            node.label = labels.get(0);
            return node;
        }

        // If no attributes left to split on, return a leaf node with the majority label
        if (attributes.isEmpty()) {
            node.label = majorityVote(labels);
            return node;
        }

        // Find the best attribute to split on
        String bestAttribute = findBestAttribute(features, labels, attributes);
        node.attribute = bestAttribute;

        // Split the dataset based on the best attribute
        Map<String, List<String[]>> splitData = splitDataset(features, bestAttribute);

        // Recursively build subtrees
        for (String value : splitData.keySet()) {
            List<String[]> subsetFeatures = splitData.get(value);
            List<String> subsetLabels = new ArrayList<>();
            for (String[] instance : subsetFeatures) {
                subsetLabels.add(instance[instance.length - 1]);
            }
            List<String> remainingAttributes = new ArrayList<>(attributes);
            remainingAttributes.remove(bestAttribute);
            Node child = buildTree(subsetFeatures, subsetLabels, remainingAttributes);
            node.children.put(value, child);
        }

        return node;
    }

    private boolean areAllLabelsSame(List<String> labels) {
        String firstLabel = labels.get(0);
        for (String label : labels) {
            if (!label.equals(firstLabel)) {
                return false;
            }
        }
        return true;
    }

    private String majorityVote(List<String> labels) {
        Map<String, Integer> labelCounts = new HashMap<>();
        for (String label : labels) {
            labelCounts.put(label, labelCounts.getOrDefault(label, 0) + 1);
        }
        int maxCount = 0;
        String majorityLabel = null;
        for (String label : labelCounts.keySet()) {
            int count = labelCounts.get(label);
            if (count > maxCount) {
                maxCount = count;
                majorityLabel = label;
            }
        }
        return majorityLabel;
    }

    private String findBestAttribute(List<String[]> features, List<String> labels, List<String> attributes) {
        double maxInfoGain = Double.MIN_VALUE;
        String bestAttribute = null;
        for (String attribute : attributes) {
            double infoGain = calculateInformationGain(features, labels, attribute);
            if (infoGain > maxInfoGain) {
                maxInfoGain = infoGain;
                bestAttribute = attribute;
            }
        }
        return bestAttribute;
    }

    private double calculateInformationGain(List<String[]> features, List<String> labels, String attribute) {
        // Calculate entropy before split
        double entropyBefore = calculateEntropy(labels);

        // Calculate entropy after split
        Map<String, List<String[]>> splitData = splitDataset(features, attribute);
        double entropyAfter = 0.0;
        for (String value : splitData.keySet()) {
            List<String[]> subsetLabels = splitData.get(value);
            List<String> subset = new ArrayList<>();
            for (String[] instance : subsetLabels) {
                subset.add(instance[instance.length - 1]);
            }
            double probability = (double) subset.size() / labels.size();
            entropyAfter += probability * calculateEntropy(subset);
        }

        // Calculate information gain
        return entropyBefore - entropyAfter;
    }

    private double calculateEntropy(List<String> labels) {
        Map<String, Integer> labelCounts = new HashMap<>();
        for (String label : labels) {
            labelCounts.put(label, labelCounts.getOrDefault(label, 0) + 1);
        }
        double entropy = 0.0;
        for (String label : labelCounts.keySet()) {
            double probability = (double) labelCounts.get(label) / labels.size();
            entropy -= probability * log2(probability);
        }
        return entropy;
    }

    private double log2(double x) {
        return Math.log(x) / Math.log(2);
    }

    private Map<String, List<String[]>> splitDataset(List<String[]> features, String attribute) {
        Map<String, List<String[]>> splitData = new HashMap<>();
        int attributeIndex = Arrays.asList("PatientCondition", "PatientPhobia", "PatientPTSD", "TherapSpecialization").indexOf(attribute);
        for (String[] instance : features) {
            String value = instance[attributeIndex];
            if (!splitData.containsKey(value)) {
                splitData.put(value, new ArrayList<>());
            }
            splitData.get(value).add(instance);
        }
        return splitData;
    }

    public String predict(Node root, String[] instance) {
        Node currentNode = root;
        while (currentNode.children != null && !currentNode.children.isEmpty()) {
            String attributeValue = instance[Arrays.asList("PatientCondition", "PatientPhobia", "PatientPTSD", "TherapSpecialization").indexOf(currentNode.attribute)];
            currentNode = currentNode.children.get(attributeValue);
        }
        return currentNode.label;
    }
}
