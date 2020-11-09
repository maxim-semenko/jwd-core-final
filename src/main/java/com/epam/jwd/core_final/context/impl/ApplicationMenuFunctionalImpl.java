package com.epam.jwd.core_final.context.impl;

import com.epam.jwd.core_final.context.ApplicationMenuFunctional;
import com.epam.jwd.core_final.criteria.CrewMemberCriteria;
import com.epam.jwd.core_final.criteria.Criteria;
import com.epam.jwd.core_final.criteria.FlightMissionCriteria;
import com.epam.jwd.core_final.criteria.SpaceshipCriteria;
import com.epam.jwd.core_final.domain.CrewMember;
import com.epam.jwd.core_final.domain.FlightMission;
import com.epam.jwd.core_final.domain.Rank;
import com.epam.jwd.core_final.domain.Role;
import com.epam.jwd.core_final.domain.Spaceship;
import com.epam.jwd.core_final.exception.InvalidStateException;
import com.epam.jwd.core_final.service.impl.CrewServiceImpl;
import com.epam.jwd.core_final.service.impl.MissionServiceImpl;
import com.epam.jwd.core_final.service.impl.MyHelpfulServiceImpl;
import com.epam.jwd.core_final.service.impl.SpaceshipServiceImpl;
import com.epam.jwd.core_final.util.PropertyReaderUtil;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Scanner;

public class ApplicationMenuFunctionalImpl implements ApplicationMenuFunctional {

    private static ApplicationMenuFunctionalImpl instance;

    public static ApplicationMenuFunctionalImpl getInstance() {
        if (instance == null) {
            instance = new ApplicationMenuFunctionalImpl();
        }
        return instance;
    }

    @Override
    public void printAllCrewMembers() {
        Collection<CrewMember> crewMembers = CrewServiceImpl.getInstance().findAllCrewMembers();
        System.out.println("Your crew member list:");
        int index = 0;
        for (CrewMember crewMember : crewMembers) {
            System.out.println("[" + ++index + "] = " + crewMember.toString());
        }
    }

    @Override
    public void printAllSpaceships() {
        Collection<Spaceship> spaceships = NassaContext.getInstance().retrieveBaseEntityList(Spaceship.class);
        System.out.println("Your spaceship list:");
        int index = 0;
        for (Spaceship spaceship : spaceships) {
            System.out.println("[" + ++index + "] = " + spaceship.toString());
        }
    }

    @Override
    public void printAllFlightMission() {
        Collection<FlightMission> spaceships = NassaContext.getInstance().retrieveBaseEntityList(FlightMission.class);
        if (spaceships.isEmpty()) {
            System.out.println("The flight mission list is EMPTY!");
        } else {
            System.out.println("Your flight mission list:");
            int index = 0;
            for (FlightMission spaceship : spaceships) {
                System.out.println("[" + ++index + "] = " + spaceship.toString());
            }
        }
    }

    @Override
    public void printAllAvailableCrewMembers() {
        Criteria<CrewMember> crewMemberCriteria = new CrewMemberCriteria.CrewMemberCriteriaBuilder()
                .setIsReadyForNextMissions(true)
                .build();
        Collection<CrewMember> crewMembers = CrewServiceImpl.getInstance().findAllCrewMembersByCriteria(crewMemberCriteria);
        System.out.println("Your available crew member list:");
        int index = 0;
        for (CrewMember crewMember : crewMembers) {
            System.out.println("[" + ++index + "] = " + crewMember.toString());
        }
    }

    @Override
    public void printAllAvailableSpaceships() {
        Criteria<Spaceship> spaceshipCriteria = new SpaceshipCriteria.SpaceshipCriteriaBuilder()
                .setReadyForNextMissions(true)
                .build();
        Collection<Spaceship> spaceships = SpaceshipServiceImpl.getInstance().findAllSpaceshipsByCriteria(spaceshipCriteria);
        System.out.println("Your available spaceship list:");
        int index = 0;
        for (Spaceship spaceship : spaceships) {
            System.out.println("[" + ++index + "] = " + spaceship.toString());
        }
    }

    @Override
    public void createInfoAboutFlightMission() throws InvalidStateException {
        Object[] inputData = inputMissionData();
        NassaContext.getInstance().retrieveBaseEntityList(FlightMission.class)
                .add(MissionServiceImpl.getInstance().createMission(
                        inputData[0], inputData[1], inputData[2], inputData[3], inputData[4]));
    }

    @Override
    public void updateInfoAboutFlightMissionByCriteria() {
        Scanner in = new Scanner(System.in);
        Object[] inputData = inputMissionData();
        System.out.println("Input id:");
        Long id = in.nextLong();
        try {
            FlightMission flightMission = MissionServiceImpl.getInstance().createMission(
                    inputData[0], inputData[1], inputData[2], inputData[3], inputData[4], id);
            MissionServiceImpl.getInstance().updateMissionDetails(flightMission);
        } catch (RuntimeException | InvalidStateException e) {
            System.out.println("Can't update flight mission");
        }

    }

    @Override
    public void updateCrewByCriteria() throws InvalidStateException {
        Scanner in = new Scanner(System.in);

        System.out.println("Input new name:");
        String newName = in.nextLine();

        System.out.println("Input new Role:MISSION_SPECIALIST(1),FLIGHT_ENGINEER(2),PILOT(3),COMMANDER(4)");
        Role newRole = Role.resolveRoleById(in.nextInt());

        System.out.println("Input new Rank:TRAINEE(1),SECOND_OFFICER(2),FIRST_OFFICER(3),CAPTAIN(4)");
        Rank newRank = Rank.resolveRankById(in.nextInt());

        System.out.println("Input id:");
        Long id = in.nextLong();

        try {
            CrewMember crewMember = CrewServiceImpl.getInstance().createCrewMember(newRole, newName, newRank, id);
            CrewServiceImpl.getInstance().updateCrewMemberDetails(crewMember);
            System.out.println("The crew member has been updated!");
        } catch (RuntimeException e) {
            System.out.println("Can't update crew member!");
        }
    }

    @Override
    public void updateSpaceshipByCriteria() {
        Scanner in = new Scanner(System.in);

        System.out.println("Input new name:");
        String newName = in.nextLine();

        System.out.println("Input new distance:");
        Long newDistance = in.nextLong();

        System.out.println("Input id:");
        Long id = in.nextLong();

        try {
            Spaceship spaceship = SpaceshipServiceImpl.getInstance().createSpaceship(newName, newDistance, null, id);
            SpaceshipServiceImpl.getInstance().updateSpaceshipDetails(spaceship);
        } catch (InvalidStateException | RuntimeException e) {
            System.out.println("Can't update crew member!");
        }
    }

    @Override
    public void writeFlightMissionInFile() {
        Scanner in = new Scanner(System.in);
        System.out.println("Input id:");
        Long id = in.nextLong();

        Criteria<FlightMission> flightMissionCriteria = new FlightMissionCriteria.FlightMissionCriteriaBuilder().setId(id).build();
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            System.out.println(MissionServiceImpl.getInstance().findMissionByCriteria(flightMissionCriteria).toString());
            FlightMission flightMission = MissionServiceImpl.getInstance().findMissionByCriteria(flightMissionCriteria).get();
            objectMapper.writeValue(new File
                    ("src\\main\\resources\\" + PropertyReaderUtil.applicationProperties.getOutputRootDir() + "\\" +
                            flightMission.getMissionName() + ".json"), flightMission);
        } catch (IOException e) {
            System.out.println("Can't write info in file!");
            NassaContext.LOGGER.error("Can't write info in file");
        }
    }

    @Override
    public Object[] inputMissionData() {
        Scanner in = new Scanner(System.in);
        Object[] inputData = new Object[5];

        System.out.println("Input missionName:");
        inputData[0] = in.nextLine();

        System.out.println("input distance:");
        inputData[1] = in.nextLong();

        LocalDateTime tempStartDate = LocalDateTime.now().plusDays((int) (Math.random() * 365 + 1));
        inputData[2] = tempStartDate;

        LocalDateTime tempEndDate = tempStartDate.plusDays((int) ((Long) inputData[1] / 100 * 0.66));
        inputData[3] = tempEndDate;

        System.out.println("Input missionResult:CANCELLED(1),FAILED(2), PLANNED(3), IN_PROGRESS(4), COMPLETED(5)");
        inputData[4] = MyHelpfulServiceImpl.getInstance().makeMissionResult(in.nextInt());

        return inputData;
    }
}
