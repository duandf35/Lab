package com.wenyu.brain.design.platform.dao;

import com.wenyu.brain.design.platform.model.User;

import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

import java.util.List;

/**
 * @author Wenyu
 * @since 9/27/16
 */
@Transactional
public interface UserDAO extends CrudRepository<User, Long> {

    User save(final User user);

    void delete(final User user);

    List<User> findByUsername(final String username);

    List<User> findAll();
}
