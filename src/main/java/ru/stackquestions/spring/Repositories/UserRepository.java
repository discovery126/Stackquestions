package ru.stackquestions.spring.Repositories;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.stackquestions.spring.Models.MyUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<MyUser,Integer> {
    Optional<MyUser> findByEmail(String email);

    @Modifying
    void deleteByUserId(int userId);
    @Modifying
    @Query(value = "INSERT INTO users (email,name,password) VALUES (:EMAIL,:NAME,:PASSWORD);", nativeQuery = true)
    void createUser(@Param("EMAIL") String email, @Param("NAME") String name,@Param("PASSWORD") String password);

//    @Query("select us.nameUser,us.date_registration, us.questionsList,us.answersList from User")
//    List<?> getInformation(@Param("id") int id);


}
