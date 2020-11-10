package domain;

import com.epam.jwd.core_final.domain.FlightMission;
import com.epam.jwd.core_final.domain.MissionResult;
import com.epam.jwd.core_final.exception.InvalidStateException;
import com.epam.jwd.core_final.service.impl.MissionServiceImpl;
import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDateTime;

public class FlightMissionTest {

    @Test
    public void setMissionNameTest() throws InvalidStateException {
        FlightMission flightMission = MissionServiceImpl.getInstance()
                .createMission("Test", 1000L, LocalDateTime.now(), LocalDateTime.now(), MissionResult.COMPLETED);
        flightMission.setMissionName("Test1");

        Assert.assertEquals(flightMission.getMissionName(), "Test1");
    }

    @Test
    public void setStartDateTest() throws InvalidStateException {
        FlightMission flightMission = MissionServiceImpl.getInstance()
                .createMission("Test", 1000L, LocalDateTime.now(), LocalDateTime.now(), MissionResult.COMPLETED);

        flightMission.setStartDate(
                LocalDateTime.of(2020, 1, 1, 12, 30, 30));

        Assert.assertEquals(
                flightMission.getStartDate(),
                LocalDateTime.of(2020, 1, 1, 12, 30, 30));
    }

    @Test
    public void setEndDateTest() throws InvalidStateException {
        FlightMission flightMission = MissionServiceImpl.getInstance()
                .createMission("Test", 1000L, LocalDateTime.now(), LocalDateTime.now(), MissionResult.COMPLETED);

        flightMission.setEndDate(
                LocalDateTime.of(2020, 1, 1, 12, 30, 30));

        Assert.assertEquals(
                flightMission.getEndDate(),
                LocalDateTime.of(2020, 1, 1, 12, 30, 30));
    }

    @Test
    public void setDistanceTest() throws InvalidStateException {
        FlightMission flightMission = MissionServiceImpl.getInstance()
                .createMission("Test", 1000L, LocalDateTime.now(), LocalDateTime.now(), MissionResult.COMPLETED);

        flightMission.setDistance(2000L);
        long expected = flightMission.getDistance();

        Assert.assertEquals(expected, 2000L);
    }

    @Test
    public void setAssignedCrewTest() throws InvalidStateException {
        FlightMission flightMission = MissionServiceImpl.getInstance()
                .createMission("Test", 1000L, LocalDateTime.now(), LocalDateTime.now(), MissionResult.COMPLETED);

    }

    @Test
    public void setMissionResultTest() throws InvalidStateException {
        FlightMission flightMission = MissionServiceImpl.getInstance()
                .createMission("Test", 1000L, LocalDateTime.now(), LocalDateTime.now(), MissionResult.COMPLETED);

        flightMission.setMissionResult(MissionResult.IN_PROGRESS);

        Assert.assertEquals(flightMission.getMissionResult(), MissionResult.IN_PROGRESS);
    }

    @Test
    public void toStringTest() throws InvalidStateException {

    }
}
