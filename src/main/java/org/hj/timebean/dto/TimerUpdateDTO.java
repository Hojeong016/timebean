package org.hj.timebean.dto;

public class TimerUpdateDTO {
    int timerPassword;

    public TimerUpdateDTO(int timerPassword) {
        this.timerPassword = timerPassword;
    }

    public int getTimerPassword() {
        return timerPassword;
    }

    public void setTimerPassword(int timerPassword) {
        this.timerPassword = timerPassword;
    }
}
