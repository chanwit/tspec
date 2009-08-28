package org.tspec.runtime;

import java.util.ArrayList;
import java.util.List;

import org.tspec.report.dom.Story;

public class ErrorListener {

    private static ThreadLocal<ErrorListener> _instance = new ThreadLocal<ErrorListener>(){
        protected ErrorListener initialValue() {
            return new ErrorListener();
        }
    };
    private List<Story> stories = new ArrayList<Story>();
    private List<String> errors=new ArrayList<String>();
    private String storyName;
    private int count = 0;

    private ErrorListener() {}

    public static ErrorListener v() {
        return _instance.get();
    }

    public void setStoryName(String name){
        stories.add(new Story(name));
    }

    public void reset() {
        this.errors.clear();
    }

    public void addError(String message) {
        this.errors.add(message);
    }

    public boolean hasErrors() {
        return this.errors.size() > 0;
    }

    public void count() {
        this.count++;
    }

    public void print() {
        System.out.println("เรื่อง " + storyName);
        if(hasErrors()) {
            System.out.println("เจอข้อผิดพลาด");
            System.out.println("");
            for(String s: this.errors) {
                System.out.println(s);
            }
        } else {
            System.out.println("จำนวนสถานการณ์ทั้งหมด: "+ count + "ผ่าน");
        }
    }

}
