package shawley.view;

import shawley.TimeSlot;

import java.util.List;

/**
 * Created by laura on 04/03/15.
 */
public interface IScheduleView {
    void setSchedule(List<TimeSlot> timeslotsInSchedule);
}
