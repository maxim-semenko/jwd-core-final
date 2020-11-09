package com.epam.jwd.core_final.context.impl;

import com.epam.jwd.core_final.context.ApplicationContext;
import com.epam.jwd.core_final.domain.BaseEntity;
import com.epam.jwd.core_final.domain.CrewMember;
import com.epam.jwd.core_final.domain.FlightMission;
import com.epam.jwd.core_final.domain.Spaceship;
import com.epam.jwd.core_final.exception.InvalidStateException;
import com.epam.jwd.core_final.strategy.ReadFromFileCrewMemberStrategy;
import com.epam.jwd.core_final.strategy.ReadFromFileSpaceshipStrategy;
import com.epam.jwd.core_final.strategy.impl.crewmember.ReadAsNameRoleRank;
import com.epam.jwd.core_final.strategy.impl.crewmember.ReadAsRankRoleName;
import com.epam.jwd.core_final.strategy.impl.crewmember.ReadAsRoleNameRank;
import com.epam.jwd.core_final.strategy.impl.spaceship.ReadAsCrewDistanceName;
import com.epam.jwd.core_final.strategy.impl.spaceship.ReadAsDistanceNameCrew;
import com.epam.jwd.core_final.strategy.impl.spaceship.ReadAsNameDistanceCrew;
import com.epam.jwd.core_final.util.PropertyReaderUtil;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Scanner;

// todo
public class NassaContext implements ApplicationContext {


    private static NassaContext instance;
    public static final Logger LOGGER = Logger.getLogger(NassaContext.class);

    public static NassaContext getInstance() {
        if (instance == null) {
            instance = new NassaContext();
        }
        return instance;
    }

    // no getters/setters for them
    private Collection<CrewMember> crewMembers = new ArrayList<>();
    private Collection<Spaceship> spaceships = new ArrayList<>();
    private Collection<FlightMission> flightMissions = new ArrayList<>();

    @Override
    public <T extends BaseEntity> Collection<T> retrieveBaseEntityList(Class<T> tClass) {
        if (tClass.getName().equals(CrewMember.class.getName())) {
            return (Collection<T>) crewMembers;
        } else if (tClass.getName().equals(FlightMission.class.getName())) {
            return (Collection<T>) flightMissions;
        } else if (tClass.getName().equals(Spaceship.class.getName())) {
            return (Collection<T>) spaceships;
        }
        return null;
    }

    /**
     * You have to read input files, populate collections
     *
     */
    @Override
    public void init() {
        initCrewMembers("src\\main\\resources\\" + PropertyReaderUtil.applicationProperties.getInputRootDir() + "\\" + PropertyReaderUtil.applicationProperties.getCrewFileName());
        initSpaceShips("src\\main\\resources\\" + PropertyReaderUtil.applicationProperties.getInputRootDir() + "\\" + PropertyReaderUtil.applicationProperties.getSpaceshipsFileName());
    }

    private void initCrewMembers(String path) {
        try (Scanner in = new Scanner(new File(path))) {
            ReadFromFileCrewMemberStrategy readFromFileCrewMemberStrategy;
            switch (in.nextLine()) {
                case "#role,name,rank;":
                    readFromFileCrewMemberStrategy = new ReadAsRoleNameRank();
                    break;
                case "#name,role,rank;":
                    readFromFileCrewMemberStrategy = new ReadAsNameRoleRank();
                    break;
                case "#rank,role,name;":
                    readFromFileCrewMemberStrategy = new ReadAsRankRoleName();
                    break;
                default:
                    LOGGER.error("Incorrect crewMember file");
                    throw new InvalidStateException("Incorrect crewMember file!");
            }
            readFromFileCrewMemberStrategy.read(in, crewMembers);
        } catch (FileNotFoundException | InvalidStateException e) {
            e.printStackTrace();
        }
    }

    private void initSpaceShips(String path) {
        try (Scanner in = new Scanner(new File(path))) {
            ReadFromFileSpaceshipStrategy readFromFileSpaceshipStrategy;
            switch (in.nextLine()) {
                case "#name;distance;crew {roleid:count,roleid:count,roleid:count,roleid:count}":
                    readFromFileSpaceshipStrategy = new ReadAsNameDistanceCrew();
                    break;
                case "#distance;name;crew {roleid:count,roleid:count,roleid:count,roleid:count":
                    readFromFileSpaceshipStrategy = new ReadAsDistanceNameCrew();
                    break;
                case "#crew {roleid:count,roleid:count,roleid:count,roleid:count;distance;name":
                    readFromFileSpaceshipStrategy = new ReadAsCrewDistanceName();
                    break;
                default:
                    LOGGER.error("Incorrect spaceship file");
                    throw new InvalidStateException("Incorrect spaceship file!");
            }
            readFromFileSpaceshipStrategy.read(in, spaceships);
        } catch (FileNotFoundException | InvalidStateException e) {
            e.printStackTrace();
        }
    }

}
