package com.epam.jwd.core_final.domain;

import java.util.Map;

/**
 * crew {@link java.util.Map<Role, Short>}
 * flightDistance {@link Long} - total available flight distance
 * isReadyForNextMissions {@link Boolean} - true by default. Set to false, after first failed mission
 */
public class Spaceship extends AbstractBaseEntity {
    //todo
    private Map<Role, Short> crew;
    private Long flightDistance;
    private Boolean isReadyForNextMissions;


    public Spaceship(String name, Long flightDistance, Map<Role, Short> crew, Long id) {
        super.name = name;
        super.id = id;
        this.crew = crew;
        this.flightDistance = flightDistance;
        this.isReadyForNextMissions = true;
    }

    public Map<Role, Short> getCrew() {
        return crew;
    }

    public void setCrew(Map<Role, Short> crew) {
        this.crew = crew;
    }

    public Long getFlightDistance() {
        return flightDistance;
    }

    public void setFlightDistance(Long flightDistance) {
        this.flightDistance = flightDistance;
    }

    public Boolean getReadyForNextMissions() {
        return isReadyForNextMissions;
    }

    public void setReadyForNextMissions(Boolean readyForNextMissions) {
        isReadyForNextMissions = readyForNextMissions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Spaceship spaceship = (Spaceship) o;
        return crew.equals(spaceship.crew) &&
                flightDistance.equals(spaceship.flightDistance) &&
                name.equals(spaceship.name);
    }

    @Override
    public String toString() {
        return "Spaceship{" +
                "name='" + name +
                ", crew=" + crew +
                ", flightDistance=" + flightDistance +
                ", isReadyForNextMissions=" + isReadyForNextMissions +
                ", id=" + id + '\'' +
                '}';
    }

}
