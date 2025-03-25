package CLIP.repository.Member;

import CLIP.Entity.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByUsername(String username);
    boolean existsByUsername(String username);
    void deleteByUsername(String username);

    @Modifying(clearAutomatically = true)
    @Query("update Member m set m.userEXP= :exp where m.username = :username")
    int updateExp(String username, int exp);

    @Modifying(clearAutomatically = true)
    @Query("update Member m set m.userLevel = m.userLevel + :level where m.username = :username")
    int updateLevel(String username, int level);

    @Query("SELECT m.userEXP FROM Member m WHERE m.username = :username")
    Integer findUserEXPByUsername(String username);

    List<Member> findAll();

    @Modifying(clearAutomatically = true)
    @Query("update Member m set m.profileImg = :profileImg where m.username = :username")
    void updateProfileImg(String username, String profileImg);


    @Query("SELECT m.profileImg FROM Member m WHERE m.username = :username")
    String findProfileImgByUsername(String username);

}
