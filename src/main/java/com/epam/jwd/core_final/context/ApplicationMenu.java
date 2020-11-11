package com.epam.jwd.core_final.context;

import com.epam.jwd.core_final.context.impl.ApplicationMenuFunctionalImpl;
import com.epam.jwd.core_final.context.impl.NassaContext;
import com.epam.jwd.core_final.exception.InvalidStateException;

// todo replace Object with your own types
@FunctionalInterface
public interface ApplicationMenu {

    ApplicationContext getApplicationContext();

    default void printAvailableOptions() {
        System.out.println("***************************************************\n" +
                "0. Exit\n" +
                "1. Print all crew members.\n" +
                "2. Print all spaceships\n" +
                "3. Print all mission flight\n" +
                "4. Create info about flight mission\n" +
                "5. Update info about flight mission by id\n" +
                "6. Update crew member by id\n" +
                "7. Update spaceship by id\n" +
                "8. Print all available crew members\n" +
                "9. Print all available spaceship\n" +
                "10. Write info about selected flight mission in file\n" +
                "***************************************************\n" +
                "Input your choose:");
    }

    default void handleUserInput(int userInput) throws InvalidStateException {
        switch (userInput) {
            case 0:
                System.out.println("The application was stop");
                NassaContext.LOGGER.info("The application was stop");
                break;
            case 1:
                ApplicationMenuFunctionalImpl.getInstance().printAllCrewMembers();
                NassaContext.LOGGER.info("User selected printAllCrewMembers()");
                break;
            case 2:
                ApplicationMenuFunctionalImpl.getInstance().printAllSpaceships();
                NassaContext.LOGGER.info("User selected printAllSpaceships()");
                break;
            case 3:
                ApplicationMenuFunctionalImpl.getInstance().printAllFlightMission();
                NassaContext.LOGGER.info("User selected printAllFlightMission()");
                break;
            case 4:
                ApplicationMenuFunctionalImpl.getInstance().createInfoAboutFlightMission();
                NassaContext.LOGGER.info("User selected createInfoAboutFlightMission()");
                break;
            case 5:
                ApplicationMenuFunctionalImpl.getInstance().updateInfoAboutFlightMissionByCriteria();
                NassaContext.LOGGER.info("User selected updateInfoAboutFlightMissionByCriteria()");
                break;
            case 6:
                ApplicationMenuFunctionalImpl.getInstance().updateCrewByCriteria();
                NassaContext.LOGGER.info("User selected updateCrewByCriteria()");
                break;
            case 7:
                ApplicationMenuFunctionalImpl.getInstance().updateSpaceshipByCriteria();
                NassaContext.LOGGER.info("User selected updateSpaceshipByCriteria()");
                break;
            case 8:
                ApplicationMenuFunctionalImpl.getInstance().printAllAvailableCrewMembers();
                NassaContext.LOGGER.info("User selected print AllAvailableCrewMember()");
                break;
            case 9:
                ApplicationMenuFunctionalImpl.getInstance().printAllAvailableSpaceships();
                NassaContext.LOGGER.info("User selected printAllAvailableSpaceships()");
                break;
            case 10:
                ApplicationMenuFunctionalImpl.getInstance().writeFlightMissionInFile();
                NassaContext.LOGGER.info("User selected writeFlightMissionInFile()");
                break;
            default:
                System.out.println("You input incorrect choose!");
                NassaContext.LOGGER.error("User input incorrect choose in UI menu");
                break;
        }
    }


}
