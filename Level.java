package com.sokoban.game;

import java.util.ArrayList;

public class Level {
    
	private int width = 0;
    public ArrayList<String> rows = new ArrayList<String>();
    private String description = "";
    
    public void addRow(String row) {
        rows.add(row);
        if(row.length() > width) { width = row.length(); }
    }
    
    public void setDescription(String desc) {
        description = desc;
    }
    
    public int getWidth() { return width; }
    public int getHeight() { return rows.size(); }
    public String getDescription() { return description; }
    
    public Contents getCell(int x, int y) {
        String row = rows.get(y);
        if(x >= row.length()) return Contents.EMPTY;
        else return convert(row.charAt(x));             
    }
    
    public Contents convert(char c) {
        if(c == '#') return Contents.WALL;
        if(c == '@') return Contents.PLAYER;
        if(c == '$') return Contents.BOX;
        if(c == '.') return Contents.GOAL;
        if(c == '*') return Contents.BOXONGOAL;
        if(c == '+') return Contents.PLAYERONGOAL;        
        return Contents.EMPTY;
    }
}