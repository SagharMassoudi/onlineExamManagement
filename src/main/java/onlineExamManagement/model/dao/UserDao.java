package onlineExamManagement.model.dao;

import com.mysql.cj.util.StringUtils;
import onlineExamManagement.model.entity.User;
import onlineExamManagement.model.enumeration.userEnum.UserRole;
import onlineExamManagement.model.enumeration.userEnum.UserStatus;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

@Repository
public interface UserDao extends CrudRepository<User, Long>, JpaSpecificationExecutor<User> {

    User findByEmailAddressAndPasswordAndRole(String emailAddress,
                                              String password,
                                              UserRole role);

    List<User> findAll();

    User findByToken(String token);

    User findByEmailAddress(String emailAddress);

    @Modifying
    @Query("update User set status=:newStatus where id=:id")
    void updateUserStatus(@Param("id") Long id, @Param("newStatus") UserStatus status);


    @Modifying
    @Query("update User set firstName=:newFirstName, lastName=:newLastName," +
            "role=:newRole, status=:newStatus where emailAddress=:emailAddress")
    void updateUser(@Param("emailAddress") String emailAddress,
                    @Param("newFirstName") String firstName,
                    @Param("newLastName") String lastName,
                    @Param("newRole") UserRole role,
                    @Param("newStatus") UserStatus status);


    static Specification<User> findMaxMatch(String firstName,
                                            String lastName, String emailAddress,
                                            UserRole role, UserStatus status) {

        return (Specification<User>) (root, criteriaQuery, builder) -> {
            CriteriaQuery<User> resultCriteria = builder.createQuery(User.class);

            List<Predicate> predicates = new ArrayList<>();
            if (!StringUtils.isNullOrEmpty(firstName)) {
                predicates.add(builder.equal(root.get("firstName"), firstName));
            }
            if (!StringUtils.isNullOrEmpty(lastName)) {
                predicates.add(builder.equal(root.get("lastName"), lastName));
            }
            if (!StringUtils.isNullOrEmpty(emailAddress)) {
                predicates.add(builder.equal(root.get("emailAddress"), emailAddress));
            }
            if (!StringUtils.isNullOrEmpty(String.valueOf(role))) {
                predicates.add(builder.equal(root.get("role"), role));
            }
            if (!StringUtils.isNullOrEmpty(String.valueOf(status))) {
                predicates.add(builder.equal(root.get("status"), status));
            }

            resultCriteria.select(root).where(predicates.toArray(new Predicate[0]));
            return resultCriteria.getRestriction();
        };
    }
}
