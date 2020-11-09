package com.epam.jwd.core_final.factory.impl;

import com.epam.jwd.core_final.domain.Role;
import com.epam.jwd.core_final.domain.Spaceship;
import com.epam.jwd.core_final.exception.InvalidStateException;
import com.epam.jwd.core_final.factory.EntityFactory;

import java.util.Map;

public class SpaceShipFactory implements EntityFactory<Spaceship> {

    private static Long ID = 0L;
    private static SpaceShipFactory instance;

    public static SpaceShipFactory getInstance() {
        if (instance == null) {
            instance = new SpaceShipFactory();
        }
        return instance;
    }

    @Override
    public Spaceship create(Object... args) throws InvalidStateException {
        if (args.length == 3) {
            return new Spaceship((String) args[0], (Long) args[1], (Map<Role, Short>) args[2], ++ID);
        } else if (args.length == 4) {
            return new Spaceship((String) args[0], (Long) args[1], (Map<Role, Short>) args[2], (Long) args[3]);
        } else {
            throw new InvalidStateException("The count of args is not correct!");
        }
    }

}
