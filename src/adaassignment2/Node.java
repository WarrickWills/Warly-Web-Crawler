/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adaassignment2;

import java.util.ArrayList;

/**
 *
 * @author Warrick Wills - 13831575
 * Billy Galera - 13835546
 */
public class Node {

    private int x, y;
    private String url;
    private int distance;
    private ArrayList<Node> child;

    public Node(String url, int distance) {
        this.url = url;
        this.distance = distance;
        child = new ArrayList<>();
    }

    public Node(String url) {
        this.distance = 0;
        this.url = url;
        child = new ArrayList<>();
    }

    public String getUrl() {
        return url;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public ArrayList<Node> getChildList() {
        return child;
    }

    public void addChild(Node u) {
        this.child.add(u);
    }

    @Override
    public boolean equals(Object obj) {
        return this.hashCode() == obj.hashCode();
    }

    @Override
    public int hashCode() {
        return url.hashCode();
    }

    @Override
    public String toString() {
        String ret = url;
        if (this.getChildList().size() > 0) {
            for (Node u : getChildList()) {
                ret += "\n";
                for (int i = 0; i <= getDistance(); i++) {
                    ret += "\t";
                }
                ret += u.toString();
            }
        }
        return ret;
    }
}
