package com.epam.jwd.core_final.service.impl;

import com.epam.jwd.core_final.context.impl.NassaContext;
import com.epam.jwd.core_final.criteria.Criteria;
import com.epam.jwd.core_final.criteria.FlightMissionCriteria;
import com.epam.jwd.core_final.domain.CrewMember;
import com.epam.jwd.core_final.domain.FlightMission;
import com.epam.jwd.core_final.domain.Role;
import com.epam.jwd.core_final.domain.Spaceship;
import com.epam.jwd.core_final.exception.InvalidStateException;
import com.epam.jwd.core_final.factory.impl.FlightMissionFactory;
import com.epam.jwd.core_final.service.MissionService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class MissionServiceImpl implements MissionService {

    private static MissionServiceImpl instance;

    public static MissionServiceImpl getInstance() {
        if (instance == null) {
            instance = new MissionServiceImpl();
        }
        return instance;
    }

    @Override
    public List<FlightMission> findAllMissions() {
        return (List<FlightMission>) NassaContext.getInstance().retrieveBaseEntityList(FlightMission.class);
    }

    @Override
    public List<FlightMission> findAllMissionsByCriteria(Criteria<? extends FlightMission> criteria) {
        FlightMissionCriteria flightMissionCriteria = (FlightMissionCriteria) criteria;

        List<FlightMission> list = NassaContext.getInstance().retrieveBaseEntityList(FlightMission.class)
                .stream()
                .filter(flightMission -> {
                    if (flightMissionCriteria.getMissionName() == null) {
                        return true;
                    } else return flightMission.getMissionName().equals(flightMissionCriteria.getMissionName());

                }).filter(flightMission -> {
                    if (flightMissionCriteria.getDistance() == null) {
                        return true;
                    } else return flightMission.getDistance().equals(flightMissionCriteria.getDistance());

                }).filter(flightMission -> {
                    if (flightMissionCriteria.getStartDate() == null) {
                        return true;
                    } else return flightMission.getStartDate().equals(flightMissionCriteria.getStartDate());

                }).filter(flightMission -> {
                    if (flightMissionCriteria.getEndDate() == null) {
                        return true;
                    } else return flightMission.getEndDate().equals(flightMissionCriteria.getEndDate());

                }).filter(flightMission -> {
                    if (flightMissionCriteria.getMissionResult() == null) {
                        return true;
                    } else return flightMission.getId().equals(flightMissionCriteria.getId());

                }).filter(flightMission -> {
                    if (flightMissionCriteria.getId() == null) {
                        return true;
                    } else return flightMission.getId().equals(flightMissionCriteria.getId());

                }).collect(Collectors.toList());

        if (list.isEmpty()) {
            throw new NullPointerException("Can't find this figure!");
        } else {
            return list;
        }
    }

    @Override
    public Optional<FlightMission> findMissionByCriteria(Criteria<? extends FlightMission> criteria) {
        FlightMissionCriteria flightMissionCriteria = (FlightMissionCriteria) criteria;

        Optional<FlightMission> optional = NassaContext.getInstance().retrieveBaseEntityList(FlightMission.class)
                .stream()
                .filter(flightMission -> {
                    if (flightMissionCriteria.getMissionName() == null) {
                        return true;
                    } else return flightMission.getMissionName().equals(flightMissionCriteria.getMissionName());

                }).filter(flightMission -> {
                    if (flightMissionCriteria.getDistance() == null) {
                        return true;
                    } else return flightMission.getDistance().equals(flightMissionCriteria.getDistance());

                }).filter(flightMission -> {
                    if (flightMissionCriteria.getStartDate() == null) {
                        return true;
                    } else return flightMission.getStartDate().equals(flightMissionCriteria.getStartDate());

                }).filter(flightMission -> {
                    if (flightMissionCriteria.getEndDate() == null) {
                        return true;
                    } else return flightMission.getEndDate().equals(flightMissionCriteria.getEndDate());

                }).filter(flightMission -> {
                    if (flightMissionCriteria.getMissionResult() == null) {
                        return true;
                    } else return flightMission.getId().equals(flightMissionCriteria.getId());

                }).filter(flightMission -> {
                    if (flightMissionCriteria.getId() == null) {
                        return true;
                    } else return flightMission.getId().equals(flightMissionCriteria.getId());

                }).findAny();

        if (optional.isEmpty()) {
            throw new NullPointerException("Can't find this figure!");
        } else {
            return optional;
        }
    }

    @Override
    public FlightMission updateMissionDetails(FlightMission flightMission) {
        Optional<FlightMission> optional = NassaContext.getInstance().retrieveBaseEntityList(FlightMission.class)
                .stream()
                .filter((p) -> (p).getId().equals(flightMission.getId())).peek((p) -> {
                    p.setMissionName(flightMission.getMissionName());
                    p.setDistance(flightMission.getDistance());
                    p.setStartDate(flightMission.getStartDate());
                    p.setEndDate(flightMission.getEndDate());
                    p.setMissionResult(flightMission.getMissionResult());
                }).findAny();

        if (optional.isEmpty()) {
            throw new RuntimeException("The mission is not founded!");
        } else {
            return optional.get();
        }
    }

    @Override
    public FlightMission createMission(Object... args) throws InvalidStateException {
        FlightMission newFlightMission = FlightMissionFactory.getInstance().create(args);
        Optional<FlightMission> optional = NassaContext.getInstance().retrieveBaseEntityList(FlightMission.class)
                .stream()
                .filter(crewMember -> crewMember.equals(newFlightMission))
                .findAny();
        if (optional.isPresent()) {
            throw new RuntimeException("The object has already been created!");
        } else {
            newFlightMission.setAssignedSpaceShift(setSpaceshipToMission(newFlightMission));
            newFlightMission.setAssignedCrew(setCrewMemberToMission(newFlightMission.getAssignedSpaceShift()));
            return newFlightMission;
        }
    }

    private Spaceship setSpaceshipToMission(FlightMission flightMission) throws InvalidStateException {
        Optional<Spaceship> optional = NassaContext.getInstance().retrieveBaseEntityList(Spaceship.class).stream()
                .filter(spaceship -> spaceship.getFlightDistance() >= flightMission.getDistance())
                .filter(Spaceship::getReadyForNextMissions)
                .findAny();
        if (optional.isPresent()) {
            SpaceshipServiceImpl.getInstance().assignSpaceshipOnMission(optional.get());
            return optional.get();
        } else {
            throw new InvalidStateException("The spaceship was not founded!");
        }
    }

    private List<CrewMember> setCrewMemberToMission(Spaceship spaceship) {
        List<CrewMember> list = new ArrayList<>();
        for (Role key : spaceship.getCrew().keySet()) {
            List<CrewMember> tempList = NassaContext.getInstance().retrieveBaseEntityList(CrewMember.class)
                    .stream()
                    .filter(crewMember -> crewMember.getRole() == key)
                    .filter(CrewMember::getReadyForNextMissions)
                    .peek(crewMember -> CrewServiceImpl.getInstance().assignCrewMemberOnMission(crewMember))
                    .limit(spaceship.getCrew().get(key))
                    .collect(Collectors.toList());
            list.addAll(tempList);
        }
        return list;
    }

}
