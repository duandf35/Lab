package com.wenyu.other;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.stream.Collectors;

/**
 * Create a trie for storing all the patterns.
 *
 * Each unique component of the path is stored in a map, so the path component matching takes O(1).
 *
 * If the input path matches one "branch" in the trie, the total number of the matching operations equals
 * to the number of the components of the input path.
 *
 * Take n as the number of components of one endpoint and take m as the number of paths:
 *
 * the time complexity of finding the endpoint is O(n)
 * the time complexity of building the trie is O(n * m)
 *
 * The space complexity depends on how many components are shared by the paths. In the wrost case, there's
 * no components are shared, then the space complexity will be (n * m).
 *
 * In the best scenario, all components are shared except the last one, so the space complexity will be (n + m).
 */
public class Trie {

    private static final String WILD_CARD_INDICATOR = "X";

    private TrieNode root = new TrieNode().withPathComponent("/").withIsLeaf(true);

    /**
     * Find the endpoint of the input path.
     *
     * @param path
     * @return the endpoint or 404 if the path doesn't match any pattern
     */
    public String endpoint(String path) {
        Queue<String> pathComponents = new LinkedList<>(Arrays.asList(path.split("/")));

        TrieNode matched = match(pathComponents);

        if (matched == null || matched.endpoint == null) {
            return "404";
        } else {
            return matched.endpoint;
        }
    }

    /**
     * Insert one pattern into the Trie.
     *
     * The endpoint is stored in the leaf node.
     *
     * @param route
     */
    public void add(Route route) {
        TrieNode visitor = root;
        // split the path into components
        Queue<String> newPathComponents = new LinkedList<>(Arrays.asList(route.path.split("/")));

        while (!newPathComponents.isEmpty()) {
            String newComponent = newPathComponents.poll();

            if (visitor.children.containsKey(newComponent)) {
                visitor = visitor.children.get(newComponent);
                continue;
            }

            visitor.withIsLeaf(false);

            TrieNode newChild = new TrieNode().withPathComponent(newComponent);
            visitor.children.put(newComponent, newChild);
            visitor = newChild;
        }

        // assign the endpoint to the leaf node
        visitor.withIsLeaf(true).withEndpoint(route.endpoint);
    }

    /**
     * Find the leaf node that represents the endpoint path.
     *
     * @param pathComponents
     * @return the leaf node or null if no leaf is found
     */
    private TrieNode match(Queue<String> pathComponents) {
        TrieNode visitor = root;
        while (!pathComponents.isEmpty() && visitor != null && !visitor.isLeaf) {
            String pathComponent = pathComponents.poll();

            // static match first
            if (visitor.children.containsKey(pathComponent)) {
                visitor = visitor.children.get(pathComponent);
            } else {
                visitor = visitor.children.getOrDefault(WILD_CARD_INDICATOR, null);
            }
        }

        // the path may be longer than the depth of the tree
        return pathComponents.isEmpty() ? visitor : null;
    }

    private static class TrieNode {
        private String pathComponent;
        private String endpoint;
        private Map<String, TrieNode> children = new HashMap<>();
        private boolean isLeaf = false;

        private TrieNode withPathComponent(String pathComponent) {
            this.pathComponent = pathComponent;
            return this;
        }

        private TrieNode withEndpoint(String endpoint) {
            this.endpoint = endpoint;
            return this;
        }

        private TrieNode withIsLeaf(boolean isLeaf) {
            this.isLeaf = isLeaf;
            return this;
        }
    }

    private static List<String> routeAll(List<Route> routes, List<String> paths) {
        Trie trie = new Trie();

        // build the trie based on the routes
        routes.forEach(trie::add);

        // find the endpoint of each path
        return paths.stream()
                .map(trie::endpoint)
                .collect(Collectors.toList());

    }

    static class Route {
        String path;
        String endpoint;

        public Route(String path, String endpoint) {
            this.path = path;
            this.endpoint = endpoint;
        }
    }

    private static List<Route> getRoutes(InputStream is) throws IOException {
        List<Route> routes = new ArrayList<Route>();
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        String line;
        while ((line = reader.readLine()) != null && line.length() != 0) {
            String[] tokenizedLine = line.split(" ");
            routes.add(new Trie.Route(tokenizedLine[0], tokenizedLine[1]));
        }
        return routes;
    }

    private static List<String> getPaths(InputStream is) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        List<String> paths = new ArrayList<>();
        String line;
        while ((line = reader.readLine()) != null && line.length() != 0) {
            paths.add(line);
        }
        return paths;
    }
}