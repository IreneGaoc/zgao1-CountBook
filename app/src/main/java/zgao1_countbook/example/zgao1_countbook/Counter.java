/*
 *  Copyright (c) Assign1. CMPUT 301. University of Alberta - All Rights Reserved. You may use, distribute, or modify this code under terms and conditions of the Code of Student Behaviour at University of Alberta.
 *
 *
 */

package zgao1_countbook.example.zgao1_countbook;
import android.content.Context;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by irene gao
 */

public class Counter {

    private String name;
    private Date date;
    private Integer currentvalue;
    private Integer initialvalue;
    private String comment;

    public Counter (String name, Integer currentvalue, Integer initialvalue, String comment) {
        this.name = name;
        this.date = new Date();
        this.currentvalue = currentvalue;
        this.initialvalue = initialvalue;
        this.comment = comment;
    }
    public Counter (String name, Integer initialvalue, String comment) {
        this.name = name;
        this.date = new Date();
        this.currentvalue = initialvalue;
        this.initialvalue = initialvalue;
        this.comment = comment;
    }

    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return name;
    }

    public void setDate(){
        this.date = new Date();
    }
    public Date getDate(){
        return this.date;

    }
    public Integer getCurrentvalue(){
        return this.currentvalue;
    }
    public void setCurrentvalue(int currentvalue){
        this.currentvalue = currentvalue;
    }
    public void increment(){
        this.currentvalue += 1;
    }
    public void decrement(){
        if (this.currentvalue > 0) {
            this.currentvalue -= 1;
        }
    }
    public Integer getInitialvalue(){

        return this.initialvalue;
    }
    public void setInitialvalue(int initialvalue){

        this.initialvalue = initialvalue;
    }
    public void reset(){
        this.currentvalue = this.initialvalue;
        this.date = new Date();
    }
    public void setComment(String comment){

        this.comment = comment;
    }
    public String getComment(){

        return this.comment;
    }
    public String toString(){

        return new SimpleDateFormat("yyyy-MM-dd").format(date)+"\n"+ "Name"+name+"\ncurrentvalue"+currentvalue;
    }



}
