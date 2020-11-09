package com.epam.jwd.core_final.service.impl;

import com.epam.jwd.core_final.context.impl.NassaContext;
import com.epam.jwd.core_final.criteria.CrewMemberCriteria;
import com.epam.jwd.core_final.criteria.Criteria;
import com.epam.jwd.core_final.domain.CrewMember;
import com.epam.jwd.core_final.exception.InvalidStateException;
import com.epam.jwd.core_final.factory.impl.CrewMemberFactory;
import com.epam.jwd.core_final.service.CrewService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class CrewServiceImpl implements CrewService {

    private static CrewServiceImpl instance;

    public static CrewServiceImpl getInstance() {
        if (instance == null) {
            instance = new CrewServiceImpl();
        }
        return instance;
    }

    @Override
    public List<CrewMember> findAllCrewMembers() {
        return (List<CrewMember>) NassaContext.getInstance().retrieveBaseEntityList(CrewMember.class);
    }

    @Override
    public List<CrewMember> findAllCrewMembersByCriteria(Criteria<? extends CrewMember> criteria) {
        CrewMemberCriteria crewMemberCriteria = (CrewMemberCriteria) criteria;

        List<CrewMember> list = NassaContext.getInstance().retrieveBaseEntityList(CrewMember.class)
                .stream()
                .filter(crewMember -> {
                    if (crewMemberCriteria.getRank() == null) {
                        return true;
                    } else return crewMember.getRank() == crewMemberCriteria.getRank();
                }).filter(crewMember -> {
                    if (crewMemberCriteria.getRole() == null) {
                        return true;
                    } else return crewMember.getRole() == crewMemberCriteria.getRole();
                }).filter(crewMember -> {
                    if (crewMemberCriteria.getName() == null) {
                        return true;
                    } else return crewMember.getName().equals(crewMemberCriteria.getName());
                }).filter(crewMember -> {
                    if (crewMemberCriteria.getId() == null) {
                        return true;
                    } else return crewMember.getId().equals(crewMemberCriteria.getId());
                }).filter(crewMember -> {
                    if (crewMemberCriteria.getReadyForNextMissions() == null) {
                        return true;
                    } else return crewMember.getReadyForNextMissions() == crewMemberCriteria.getReadyForNextMissions();
                }).collect(Collectors.toList());

        if (list.isEmpty()) {
            throw new NullPointerException("The list is empty!");
        } else {
            return list;
        }
    }

    @Override
    public Optional<CrewMember> findCrewMemberByCriteria(Criteria<? extends CrewMember> criteria) {
        CrewMemberCriteria crewMemberCriteria = (CrewMemberCriteria) criteria;

        Optional<CrewMember> optional = NassaContext.getInstance().retrieveBaseEntityList(CrewMember.class)
                .stream()
                .filter(crewMember -> {
                    if (crewMemberCriteria.getRank() == null) {
                        return true;
                    } else return crewMember.getRank() == crewMemberCriteria.getRank();
                }).filter(crewMember -> {
                    if (crewMemberCriteria.getRole() == null) {
                        return true;
                    } else return crewMember.getRole() == crewMemberCriteria.getRole();
                }).filter(crewMember -> {
                    if (crewMemberCriteria.getName() == null) {
                        return true;
                    } else return crewMember.getName().equals(crewMemberCriteria.getName());
                }).filter(crewMember -> {
                    if (crewMemberCriteria.getId() == null) {
                        return true;
                    } else return crewMember.getId().equals(crewMemberCriteria.getId());
                }).filter(crewMember -> {
                    if (crewMemberCriteria.getReadyForNextMissions() == null) {
                        return true;
                    } else return crewMember.getReadyForNextMissions() == crewMemberCriteria.getReadyForNextMissions();
                }).findAny();

        if (optional.isEmpty()) {
            throw new NullPointerException("Can't find this figure!");
        } else {
            return optional;
        }
    }

    @Override
    public CrewMember updateCrewMemberDetails(CrewMember crewMember) {
        Optional<CrewMember> optional = NassaContext.getInstance().retrieveBaseEntityList(CrewMember.class)
                .stream()
                .filter((p) -> (p).getId().equals(crewMember.getId())).peek((p) -> {
                    p.setRank(crewMember.getRank());
                    p.setRole(crewMember.getRole());
                    p.setName(crewMember.getName());
                }).findAny();

        if (optional.isEmpty()) {
            throw new RuntimeException("The crew member is not founded!");
        } else {
            return optional.get();
        }
    }

    @Override
    public void assignCrewMemberOnMission(CrewMember crewMember) throws RuntimeException {
        Optional<CrewMember> optional = NassaContext.getInstance().retrieveBaseEntityList(CrewMember.class)
                .stream()
                .filter((p) -> (p).getId().equals(crewMember.getId())).peek((p) -> {
                    p.setReadyForNextMissions(false);
                }).findAny();
        if (optional.isEmpty()) {
            throw new RuntimeException("The crew member is not founded!");
        }
    }

    @Override
    public CrewMember createCrewMember(Object... args) throws RuntimeException, InvalidStateException {
        CrewMember newCrewMember = CrewMemberFactory.getInstance().create(args);
        Optional<CrewMember> optional = NassaContext.getInstance().retrieveBaseEntityList(CrewMember.class)
                .stream()
                .filter(crewMember -> crewMember.equals(newCrewMember))
                .findAny();

        if (optional.isPresent()) {
            throw new RuntimeException("The object has already been created!");
        } else {
            return newCrewMember;
        }
    }
}
