package com.riemannroch.appointapi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Duration;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AmountOfTime {
    private int hours;
    private int minutes;
    private int seconds;

    public static AmountOfTime fromSeconds(int totalSeconds) {
        return new AmountOfTime(totalSeconds / 3600, (totalSeconds / 60) % 60, totalSeconds % 60);
    }

    public static AmountOfTime fromDuration(Duration duration) {
        return AmountOfTime.fromSeconds((int) duration.toSeconds());
    }

    public int toSeconds() {
        return this.seconds + this.minutes * 60 + this.hours * 3600;
    }

    public AmountOfTime plus(AmountOfTime amountOfTime) {
        return AmountOfTime.fromSeconds(this.toSeconds() + amountOfTime.toSeconds());
    }

    boolean isValid() {
        return 0 <= this.minutes
                && this.minutes < 60
                && 0 <= this.seconds
                && this.seconds < 60;
    }
}
