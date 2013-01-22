package com.sokoban.game;

import java.util.*;
import java.io.*;

public class LevelReader {
    
    public ArrayList<Level> levels;
    
    public Contents convert(char c) {
        if(c == '#') return Contents.WALL;
        if(c == '@') return Contents.PLAYER;
        if(c == '$') return Contents.BOX;
        if(c == '.') return Contents.GOAL;
        if(c == '*') return Contents.BOXONGOAL;
        if(c == '+') return Contents.PLAYERONGOAL;        
        return Contents.EMPTY;
    }


    int readLevels(String fileName) {
        
        levels = new ArrayList<Level>();
        Level currentLevel = new Level();
        String description = "";
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(fileName));
            String line = br.readLine();

            while(line != null) {
//                System.out.println(line);
                if(line.trim().equals("")) {
                    line = br.readLine();
                    continue;
                }
                if(line.startsWith(";")) {
                    if(currentLevel.getHeight() > 0) {
                        levels.add(currentLevel);
                        currentLevel = new Level();
                    }
                    currentLevel.setDescription(line.substring(1).trim());                    
                }
                else {
                    currentLevel.addRow(line);
                }
                line = br.readLine();
            }
            levels.add(currentLevel);
        }
        catch(IOException e) {
            System.out.println("Error reading level file!");
        }
        finally { try { if(br != null) { br.close(); }} catch(IOException e) { } }
        return levels.size();
    }

    public int getHeight(int level) { return levels.get(level).getHeight(); }
    public int getWidth(int level) { return levels.get(level).getWidth(); }
    public String getDescription(int level) { return levels.get(level).getDescription(); }
    public Contents getTile(int level, int x, int y) { 
        return levels.get(level).getCell(x,y);
    }
}