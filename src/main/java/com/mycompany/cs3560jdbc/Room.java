/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.cs3560jdbc;


public class Room {

    boolean[] timeSlot = new boolean[24]; //24 for the ours in the day
    int employeeCapacity; //change this for the room

    void updateTimeSlot(int startTime, int endTime) {
        // for cases where they start at 23 and end at 1 in the morning
        if (startTime > 24 || endTime > 24 || startTime < 0 || endTime < 0) {
            System.out.println("Error not a valid Time Slot");
        } else if (endTime < startTime) {
            int i = 0;
            while (endTime < 24) {
                timeSlot[endTime] = true;
                endTime++;
            }
            while (i < startTime) {
                timeSlot[i] = true;
                i++;
            }
        } else {
            while (startTime < endTime) {
                timeSlot[startTime] = true;
                startTime++;
            }
        }
    }

    void addRoom() {
        //access the database to create a new room
    }

    void deleteRoom() {
        //access the database to delete a room
    }

}
