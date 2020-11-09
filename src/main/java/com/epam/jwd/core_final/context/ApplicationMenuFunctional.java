package com.epam.jwd.core_final.context;


import com.epam.jwd.core_final.exception.InvalidStateException;

public interface ApplicationMenuFunctional {

    void printAllCrewMembers();

    void printAllSpaceships();

    void printAllFlightMission();

    void printAllAvailableCrewMembers();

    void printAllAvailableSpaceships();

    void createInfoAboutFlightMission() throws InvalidStateException;

    void updateInfoAboutFlightMissionByCriteria();

    void updateCrewByCriteria() throws InvalidStateException;

    void updateSpaceshipByCriteria();

    void writeFlightMissionInFile();

    Object[] inputMissionData();
}
