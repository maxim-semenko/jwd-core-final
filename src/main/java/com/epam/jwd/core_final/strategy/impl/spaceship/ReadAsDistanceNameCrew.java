package com.epam.jwd.core_final.strategy.impl.spaceship;

import com.epam.jwd.core_final.domain.Role;
import com.epam.jwd.core_final.domain.Spaceship;
import com.epam.jwd.core_final.exception.InvalidStateException;
import com.epam.jwd.core_final.service.impl.MyHelpfulServiceImpl;
import com.epam.jwd.core_final.service.impl.SpaceshipServiceImpl;
import com.epam.jwd.core_final.strategy.ReadFromFileSpaceshipStrategy;

import java.util.Collection;
import java.util.Map;
import java.util.Scanner;

public class ReadAsDistanceNameCrew implements ReadFromFileSpaceshipStrategy {
    @Override
    public void read(Scanner in, Collection<Spaceship> spaceships) throws InvalidStateException {
        while (in.hasNextLine()) {
            String[] tempArray = in.nextLine().split(";");

            Long tempDistance = Long.valueOf(tempArray[0]);
            String tempName = tempArray[1];
            Map<Role, Short> tempCrew = MyHelpfulServiceImpl.getInstance().makeCrew(tempArray[2]);

            spaceships.add(SpaceshipServiceImpl.getInstance().createSpaceship(tempName, tempDistance, tempCrew));
        }
    }
}
