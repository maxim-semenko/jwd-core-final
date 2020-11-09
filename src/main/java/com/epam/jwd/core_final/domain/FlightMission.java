package com.epam.jwd.core_final.domain;

import com.epam.jwd.core_final.util.PropertyReaderUtil;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * Expected fields:
 * <p>
 * missions name {@link String}
 * start date {@link java.time.LocalDate}
 * end date {@link java.time.LocalDate}
 * distance {@link Long} - missions distance
 * assignedSpaceShift {@link Spaceship} - not defined by default
 * assignedCrew {@link java.util.List<CrewMember>} - list of missions members based on ship capacity - not defined by default
 * missionResult {@link MissionResult}
 */
public class FlightMission extends AbstractBaseEntity {
    // todo
    private String missionName;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Long distance;
    private Spaceship assignedSpaceShift;
    private List<CrewMember> assignedCrew;
    private MissionResult missionResult;

    public FlightMission(String missionName, Long distance, LocalDateTime startDate, LocalDateTime endDate, MissionResult missionResult, Long id) {
        super.id = id;
        super.name = missionName;
        this.missionName = missionName;
        this.distance = distance;
        this.startDate = startDate;
        this.endDate = endDate;
        this.missionResult = missionResult;
    }

    public String getMissionName() {
        return missionName;
    }

    public void setMissionName(String missionName) {
        this.missionName = missionName;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public Long getDistance() {
        return distance;
    }

    public void setDistance(Long distance) {
        this.distance = distance;
    }

    public Spaceship getAssignedSpaceShift() {
        return assignedSpaceShift;
    }

    public void setAssignedSpaceShift(Spaceship assignedSpaceShift) {
        this.assignedSpaceShift = assignedSpaceShift;
    }

    public List<CrewMember> getAssignedCrew() {
        return assignedCrew;
    }

    public void setAssignedCrew(List<CrewMember> assignedCrew) {
        this.assignedCrew = assignedCrew;
    }

    public MissionResult getMissionResult() {
        return missionResult;
    }

    public void setMissionResult(MissionResult missionResult) {
        this.missionResult = missionResult;
    }

    @Override
    public String toString() {
        return "FlightMission{" +
                "missionName='" + missionName + '\'' +
                ", startDate=" + startDate.format(DateTimeFormatter.ofPattern(PropertyReaderUtil.applicationProperties.getDataTimeFormat())) +
                ", endDate=" + endDate.format(DateTimeFormatter.ofPattern(PropertyReaderUtil.applicationProperties.getDataTimeFormat())) +
                ", distance=" + distance +
                ",\nSpaceShift=" + assignedSpaceShift +
                ",\nassignedCrew=" + assignedCrew +
                ",\nmissionResult=" + missionResult +
                ",\nid=" + id +
                '}';
    }
}
