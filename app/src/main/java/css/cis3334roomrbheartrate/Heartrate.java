package css.cis3334roomrbheartrate;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import java.sql.Date;
@Entity
public class Heartrate {
    @PrimaryKey(autoGenerate = true)
    public int id;
    public Integer pulse;       // actual rate in beats per minute
    public Integer age;            // age when heart rate measurement was taken
    public Double maxHeartRate;    // calculated maximum rate based on age
    public Double percent;         // percent of maximum rate
    //public Date date;
    public Integer range;          // which range this heart rate is in, used as index into arrays below
    @Ignore
    final String rangeNames[] = {"Resting", "Moderate", "Endurance", "Aerobic","Anaerobic","Red zone"};
    @Ignore
    final String rangeDescriptions[] = {"In active or resting", "Weight maintenance and warm up", "Fitness and fat burning", "Cardio training and endurance","Hardcore interval training","Maximum Effort"};
    @Ignore
    final Double rangeBounds[] = {.50, .60, .70, .80, .90, 1.00};


    public Heartrate(Integer pulse, Integer age) {
        this.pulse = pulse;
        this.age = age;
        calcHeartRange(age);
    }

    public Integer calcHeartRange(Integer age) {
        maxHeartRate = 220.0 - age;        // from  http://www.cdc.gov/physicalactivity/basics/measuring/heartrate.htm
        percent = pulse /maxHeartRate;
        for (int i=0; i<rangeNames.length; i++) {
            if ( percent < rangeBounds[i] ) {
                // heartrate is in this range
                range = i;
                return range;          // break out of this loop
            }
        }
        return rangeNames.length-1;                      // this should never happen
    }

    public String getRangeName() {
        calcHeartRange(age);
        return rangeNames[range];
    }

    public String getRangeDescrtiption() {
        calcHeartRange(age);
        return rangeDescriptions[range];
    }

    public String toString() {
        return "Pulse of " + pulse + " - Cardio level: " + getRangeName();
    }


}
