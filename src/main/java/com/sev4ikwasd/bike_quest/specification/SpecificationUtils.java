package com.sev4ikwasd.bike_quest.specification;

import com.sev4ikwasd.bike_quest.domain.dto.SearchCriteria;
import com.sev4ikwasd.bike_quest.domain.entity.Quest;
import com.sev4ikwasd.bike_quest.domain.entity.RestUser;
import com.sev4ikwasd.bike_quest.repository.QuestRepository;
import com.sev4ikwasd.bike_quest.repository.RestUserRepository;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
class SpecificationUtils {

    private static DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ss");

    //Really bad practise!!!
    static RestUserRepository restUserRepository;
    @Autowired
    public void setRestUserRepository(RestUserRepository restUserRepository){
        SpecificationUtils.restUserRepository = restUserRepository;
    }

    static QuestRepository questRepository;
    @Autowired
    public void setQuestRepository(QuestRepository questRepository){
        SpecificationUtils.questRepository = questRepository;
    }

    static <E> Predicate compare(SearchCriteria criteria, Root<E> root, CriteriaBuilder builder){
        if (criteria.getOperation().equalsIgnoreCase(">")) {
            if(root.get(criteria.getKey()).getJavaType() == DateTime.class){
                return builder.greaterThanOrEqualTo(root.get(criteria.getKey()), formatter.parseDateTime(criteria.getValue().toString()));
            }
            return builder.greaterThanOrEqualTo(root.get(criteria.getKey()), criteria.getValue().toString());
        }
        else if (criteria.getOperation().equalsIgnoreCase("<")) {
            if(root.get(criteria.getKey()).getJavaType() == DateTime.class){
                return builder.lessThanOrEqualTo(root.get(criteria.getKey()), formatter.parseDateTime(criteria.getValue().toString()));
            }
            return builder.lessThanOrEqualTo(root.get(criteria.getKey()), criteria.getValue().toString());
        }
        else if (criteria.getOperation().equalsIgnoreCase(":")) {
            if (root.get(criteria.getKey()).getJavaType() == String.class) {
                return builder.like(root.get(criteria.getKey()), "%" + criteria.getValue() + "%");
            }
            else if(root.get(criteria.getKey()).getJavaType() == DateTime.class){
                return builder.equal(root.get(criteria.getKey()), formatter.parseDateTime(criteria.getValue().toString()));
            }
            else if(root.get(criteria.getKey()).getJavaType() == RestUser.class){
                return builder.equal(root.get(criteria.getKey()), restUserRepository.getOne(UUID.fromString(criteria.getValue().toString())));
            }
            else if(root.get(criteria.getKey()).getJavaType() == Quest.class){
                return builder.equal(root.get(criteria.getKey()), questRepository.getOne(UUID.fromString(criteria.getValue().toString())));
            }
            return builder.equal(root.get(criteria.getKey()), criteria.getValue());
        }
        return null;
    }

    static <E> Specification<E> build(List<SearchCriteria> params){
        if (params.size() == 0) {
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
        return result;
    }
}
