package com.mvgrebenyuk.task.services.impl;

import com.mvgrebenyuk.task.entity.User;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

@Component
public class UserSpecification  {

    public Specification<User> nameLike(String name){
        return new Specification<User>() {
            @Override
            public Predicate toPredicate(Root<User> root,
                                         CriteriaQuery<?> query,
                                         CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.like(root.get("name"), "%"+name+"%");
            }
        };
    }

    public Specification<User> surnameLike(String surname){
        return new Specification<User>() {
            @Override
            public Predicate toPredicate(Root<User> root,
                                         CriteriaQuery<?> query,
                                         CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.like(root.get("surname"), "%"+surname+"%");
            }
        };
    }

    public Specification<User> lastNameLike(String lastName){
        return new Specification<User>() {
            @Override
            public Predicate toPredicate(Root<User> root,
                                         CriteriaQuery<?> query,
                                         CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.like(root.get("lastName"), "%"+lastName+"%");
            }
        };
    }

    public Specification<User> phoneLike(String phone){
        return new Specification<User>() {
            @Override
            public Predicate toPredicate(Root<User> root,
                                         CriteriaQuery<?> query,
                                         CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.like(root.get("phone"), "%"+phone+"%");
            }
        };
    }

    public Specification<User> emailLike(String email){
        return new Specification<User>() {
            @Override
            public Predicate toPredicate(Root<User> root,
                                         CriteriaQuery<?> query,
                                         CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.like(root.get("email"), "%"+email+"%");
            }
        };
    }

    public Specification<User> getUserSpecification(String surname, String lastName, String name, String phone, String email) {
        UserSpecification userSpecification = new UserSpecification();
        Specification<User> specification = Specification.where(null);
        if(surname != null){
            specification.and(userSpecification.surnameLike(surname));
        }

        if(lastName != null){
            specification.and(userSpecification.lastNameLike(lastName));
        }

        if(name != null){
            specification.and(userSpecification.nameLike(name));
        }

        if(phone != null){
            specification.and(userSpecification.phoneLike(phone));
        }

        if(email != null){
            specification.and(userSpecification.emailLike(email));
        }
        return specification;
    }

}
