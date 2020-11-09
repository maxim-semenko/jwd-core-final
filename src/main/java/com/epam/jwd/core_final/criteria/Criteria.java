package com.epam.jwd.core_final.criteria;

import com.epam.jwd.core_final.domain.BaseEntity;

/**
 * Should be a builder for {@link BaseEntity} fields
 */
public abstract class Criteria<T extends BaseEntity> {

    private Long id;
    private String name;

    private CrewMemberCriteria crewMemberCriteria;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setCrewMemberCriteria(CrewMemberCriteria crewMemberCriteria) {
        this.crewMemberCriteria = crewMemberCriteria;
    }

    public CrewMemberCriteria getCrewMemberCriteria() {
        return crewMemberCriteria;
    }

    public static class CriteriaBuilder {
        private Long id;
        private String name;

        public CriteriaBuilder setId(final Long id) {
            this.id = id;
            return this;
        }

        public CriteriaBuilder setName(final String name) {
            this.name = name;
            return this;
        }
    }
}
