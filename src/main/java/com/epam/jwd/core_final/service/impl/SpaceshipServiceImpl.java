package com.epam.jwd.core_final.service.impl;

import com.epam.jwd.core_final.context.impl.NassaContext;
import com.epam.jwd.core_final.criteria.Criteria;
import com.epam.jwd.core_final.criteria.SpaceshipCriteria;
import com.epam.jwd.core_final.domain.Spaceship;
import com.epam.jwd.core_final.exception.InvalidStateException;
import com.epam.jwd.core_final.factory.impl.SpaceShipFactory;
import com.epam.jwd.core_final.service.SpaceshipService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class SpaceshipServiceImpl implements SpaceshipService {

    private static SpaceshipServiceImpl instance;

    public static SpaceshipServiceImpl getInstance() {
        if (instance == null) {
            instance = new SpaceshipServiceImpl();
        }
        return instance;
    }

    @Override
    public List<Spaceship> findAllSpaceships() {
        return (List<Spaceship>) NassaContext.getInstance().retrieveBaseEntityList(Spaceship.class);
    }

    @Override
    public List<Spaceship> findAllSpaceshipsByCriteria(Criteria<? extends Spaceship> criteria) {
        SpaceshipCriteria spaceshipCriteria = (SpaceshipCriteria) criteria;

        List<Spaceship> list = NassaContext.getInstance().retrieveBaseEntityList(Spaceship.class)
                .stream()
                .filter(spaceship -> {
                    if (spaceshipCriteria.getCrew() == null) {
                        return true;
                    } else return spaceship.getCrew() == spaceshipCriteria.getCrew();
                }).filter(spaceship -> {
                    if (spaceshipCriteria.getFlightDistance() == null) {
                        return true;
                    } else return spaceship.getFlightDistance().equals(spaceshipCriteria.getFlightDistance());
                }).filter(spaceship -> {
                    if (spaceshipCriteria.getName() == null) {
                        return true;
                    } else return spaceship.getName().equals(spaceshipCriteria.getName());
                }).filter(spaceship -> {
                    if (spaceshipCriteria.getId() == null) {
                        return true;
                    } else return spaceship.getId().equals(spaceshipCriteria.getId());
                }).filter(spaceship -> {
                    if (spaceshipCriteria.getReadyForNextMissions() == null) {
                        return true;
                    } else return spaceship.getReadyForNextMissions() == spaceshipCriteria.getReadyForNextMissions();
                }).collect(Collectors.toList());

        if (list.isEmpty()) {
            throw new NullPointerException("The list is empty!");
        } else {
            return list;
        }
    }

    @Override
    public Optional<Spaceship> findSpaceshipByCriteria(Criteria<? extends Spaceship> criteria) {
        SpaceshipCriteria spaceshipCriteria = (SpaceshipCriteria) criteria;

        Optional<Spaceship> optional = NassaContext.getInstance().retrieveBaseEntityList(Spaceship.class)
                .stream()
                .filter(spaceship -> {
                    if (spaceshipCriteria.getCrew() == null) {
                        return true;
                    } else return spaceship.getCrew() == spaceshipCriteria.getCrew();
                }).filter(spaceship -> {
                    if (spaceshipCriteria.getFlightDistance() == null) {
                        return true;
                    } else return spaceship.getFlightDistance().equals(spaceshipCriteria.getFlightDistance());
                }).filter(spaceship -> {
                    if (spaceshipCriteria.getName() == null) {
                        return true;
                    } else return spaceship.getName().equals(spaceshipCriteria.getName());
                }).filter(spaceship -> {
                    if (spaceshipCriteria.getId() == null) {
                        return true;
                    } else return spaceship.getId().equals(spaceshipCriteria.getId());
                }).filter(spaceship -> {
                    if (spaceshipCriteria.getReadyForNextMissions() == null) {
                        return true;
                    } else return spaceship.getReadyForNextMissions() == spaceshipCriteria.getReadyForNextMissions();
                }).findAny();

        if (optional.isEmpty()) {
            throw new NullPointerException("Can't find this figure!");
        } else {
            return optional;
        }
    }

    @Override
    public Spaceship updateSpaceshipDetails(Spaceship spaceship) {
        Optional<Spaceship> optional = NassaContext.getInstance().retrieveBaseEntityList(Spaceship.class)
                .stream()
                .filter((p) -> (p).getId().equals(spaceship.getId())).peek((p) -> {
                    p.setCrew(p.getCrew());
                    p.setFlightDistance(spaceship.getFlightDistance());
                    p.setName(spaceship.getName());
                }).findAny();

        if (optional.isEmpty()) {
            throw new RuntimeException("The spaceship is not founded!");
        } else {
            return optional.get();
        }

    }

    @Override
    public void assignSpaceshipOnMission(Spaceship spaceship) throws RuntimeException {
        Optional<Spaceship> optional = NassaContext.getInstance().retrieveBaseEntityList(Spaceship.class)
                .stream()
                .filter((p) -> (p).getId().equals(spaceship.getId())).peek((p) -> {
                    p.setReadyForNextMissions(false);
                }).findAny();
        if (optional.isEmpty()) {
            throw new RuntimeException("The spaceship is not founded!");
        }
    }

    @Override
    public Spaceship createSpaceship(Object... args) throws InvalidStateException {
        Spaceship newSpaceship = SpaceShipFactory.getInstance().create(args);
        Optional<Spaceship> optional = NassaContext.getInstance().retrieveBaseEntityList(Spaceship.class)
                .stream()
                .filter(spaceship -> spaceship.equals(newSpaceship))
                .findAny();

        if (optional.isPresent()) {
            throw new RuntimeException("The object has already been created!");
        } else {
            return newSpaceship;
        }
    }
}
