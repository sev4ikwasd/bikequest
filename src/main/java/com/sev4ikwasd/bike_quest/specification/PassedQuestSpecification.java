package com.sev4ikwasd.bike_quest.specification;

import com.sev4ikwasd.bike_quest.domain.dto.SearchCriteria;
import com.sev4ikwasd.bike_quest.domain.entity.PassedQuest;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PassedQuestSpecification implements Specification<PassedQuest> {
    private SearchCriteria criteria;

    public PassedQuestSpecification(SearchCriteria criteria) {
        super();
        this.criteria = criteria;
    }

    public SearchCriteria getCriteria() {
        return criteria;
    }

    @Override
    public Predicate toPredicate
            (Root<PassedQuest> root, CriteriaQuery<?> query, CriteriaBuilder builder) {

        /*if (criteria.getOperation().equalsIgnoreCase(">")) {
            return builder.greaterThanOrEqualTo(
                    root.get(criteria.getKey()), criteria.getValue().toString());
        }
        else if (criteria.getOperation().equalsIgnoreCase("<")) {
            return builder.lessThanOrEqualTo(
                    root.get(criteria.getKey()), criteria.getValue().toString());
        }
        else if (criteria.getOperation().equalsIgnoreCase(":")) {
            if (root.get(criteria.getKey()).getJavaType() == String.class) {
                return builder.like(
                        root.get(criteria.getKey()), "%" + criteria.getValue() + "%");
            }
            else {
                return builder.equal(root.get(criteria.getKey()), criteria.getValue());
            }
        }
        return null;*/
        return SpecificationUtils.compare(criteria, root, builder);
    }

    public static class PassedQuestSpecificationsBuilder {

        private final List<SearchCriteria> params;

        public PassedQuestSpecificationsBuilder() {
            params = new ArrayList<SearchCriteria>();
        }

        public PassedQuestSpecificationsBuilder with(String key, String operation, Object value) {
            params.add(new SearchCriteria(key, operation, value));
            return this;
        }

        public Specification<PassedQuest> build() {
            /*if (params.size() == 0) {
                return null;
            }

            List<Specification> specs = params.stream()
                    .map(QuestSpecification::new)
                    .collect(Collectors.toList());

            Specification result = specs.get(0);
            for (int i = 1; i < params.size(); i++) {
                result = params.get(i)
                        .isOrPredicate()
                        ? Specification.where(result)
                        .or(specs.get(i))
                        : Specification.where(result)
                        .and(specs.get(i));
            }
            return result;*/
            return SpecificationUtils.build(params);
        }
    }
}
