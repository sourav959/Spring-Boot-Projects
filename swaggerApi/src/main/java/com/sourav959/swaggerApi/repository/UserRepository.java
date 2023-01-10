package com.sourav959.swaggerApi.repository;

import com.sourav959.swaggerApi.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User,Integer> {
}
