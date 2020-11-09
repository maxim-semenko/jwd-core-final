package com.epam.jwd.core_final.strategy;

import com.epam.jwd.core_final.domain.Spaceship;
import com.epam.jwd.core_final.exception.InvalidStateException;

import java.util.Collection;
import java.util.Scanner;

public interface ReadFromFileSpaceshipStrategy {
    void read(Scanner in, Collection<Spaceship> spaceships) throws InvalidStateException;
}
