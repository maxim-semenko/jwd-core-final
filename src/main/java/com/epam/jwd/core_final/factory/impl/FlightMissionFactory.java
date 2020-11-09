package com.epam.jwd.core_final.factory.impl;

import com.epam.jwd.core_final.domain.FlightMission;
import com.epam.jwd.core_final.domain.MissionResult;
import com.epam.jwd.core_final.exception.InvalidStateException;
import com.epam.jwd.core_final.factory.EntityFactory;

import java.time.LocalDateTime;

public class FlightMissionFactory implements EntityFactory<FlightMission> {

    private Long ID = 0L;

    private static FlightMissionFactory instance;

    public static FlightMissionFactory getInstance() {
        if (instance == null) {
            instance = new FlightMissionFactory();
        }
        return instance;
    }

    @Override
    public FlightMission create(Object... args) throws InvalidStateException {
        if (args.length == 5) {
            return new FlightMission(
                    (String) args[0],
                    (Long) args[1],
                    (LocalDateTime) args[2],
                    (LocalDateTime) args[3],
                    (MissionResult) args[4],
                    ++ID);
        } else if (args.length == 6) {
            return new FlightMission(
                    (String) args[0],
                    (Long) args[1],
                    (LocalDateTime) args[2],
                    (LocalDateTime) args[3],
                    (MissionResult) args[4],
                    (Long) args[5]);
        } else {
            throw new InvalidStateException("The count of args is not correct!");
        }
    }
}
