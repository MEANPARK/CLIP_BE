package CLIP.repository.Items;

import CLIP.Entity.Items.Items;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ItemsRepository extends JpaRepository<Items, Long> {
    List<Items> findByItemUpLoaderUuid(String itemUpLoaderUuid);

    void deleteByItemUpLoaderUuid(String itemUpLoaderUuid);
    @Override
    Optional<Items> findById(Long aLong);

    @Query("SELECT m FROM Items m WHERE m.itemUpLoaderUuid not in :itemUpLoaderUuid and m.itemStatus = :itemStatus")
    List<Items> findAllByItemUpLoaderUuid(String itemUpLoaderUuid, String itemStatus);

    @Modifying(clearAutomatically = true)
    @Query("update Items m Set m.itemStatus = :itemStatus where m.itemUpLoaderUuid = :itemUpLoaderUuid and m.itemSeq = :itemSeq")
    int updateStatusBy(String itemUpLoaderUuid, int itemSeq, String itemStatus);



    @Query("SELECT m FROM Items m WHERE m.itemUpLoaderUuid not in :itemUpLoaderUuid and m.itemSeq IN :itemSeqs and m.itemStatus = :itemStatus")
    List<Items> findByItemSeqIn(String itemUpLoaderUuid ,List<Long> itemSeqs, String itemStatus);

}
