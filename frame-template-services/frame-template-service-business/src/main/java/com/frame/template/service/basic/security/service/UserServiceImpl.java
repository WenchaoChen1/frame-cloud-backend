package com.frame.template.service.basic.security.service;


import com.frame.template.service.basic.security.domain.User;
import com.frame.template.service.basic.security.repository.UserRepository;
import com.gstdev.cloud.data.core.service.BaseServiceImpl;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
@Slf4j
@Transactional(readOnly = true)
@Service
public class UserServiceImpl extends BaseServiceImpl<User, String, UserRepository> implements UserService {

    private UserRepository userRepository;
    public UserServiceImpl(UserRepository baseRepository) {
        super(baseRepository);
        userRepository = baseRepository;
    }

    public User findByUser(User user){
        Specification<User> specification = (Root<User> root, CriteriaQuery<?> query, CriteriaBuilder cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            if(user.getId()!=null){
                predicates.add(cb.equal(root.get("id").as(Long.class), user.getId()));
            }
            if(user.getEmail()!=null){
                predicates.add(cb.equal(root.get("email").as(String.class), user.getEmail()));
            }
            if(user.getUserType()!=null){
                predicates.add(cb.equal(root.get("userType").as(String.class), user.getUserType()));
            }
            return cb.and(predicates.toArray(new Predicate[predicates.size()]));
        };
       return this.userRepository.findOne(specification).orElse(null);
    };
}
