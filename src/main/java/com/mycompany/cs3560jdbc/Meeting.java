/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.cs3560jdbc;

public class Meeting {

    Employee owner;
    Employee[] employeeList;
    Room meetingLocation;
    boolean[] timeSlot = new boolean[24]; //24 for the ours in the day

    void notifyEmployees() {
        //Working on this
    }
    

    void confirmMeeting() {
        //working ont this one
    }

    void addEmployeeToMeeting(Employee addThis) {
        Employee[] newList = new Employee[employeeList.length + 1];
        System.arraycopy(employeeList, 0, newList, 0, employeeList.length);
        newList[employeeList.length] = addThis;
        employeeList = newList;
    }

    void deleteEmployeeFromMeeting(Employee deleteThis) {
        Employee[] newList = new Employee[employeeList.length - 1];
        for (int i = 0; i < employeeList.length - 1; i++) {
            //Not sure if this comparison works
           //.equals() methods
            if (employeeList[i] != deleteThis) {
                newList[i] = employeeList[i];
            }
        }
        employeeList = newList;
    }

}

