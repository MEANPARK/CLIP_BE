package CLIP.Entity.Board;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "Comments_TB")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int commentSeq;

    private int postSeq;

    private String commentUploader;

    private String commentText;

    private LocalDateTime commentTimestamp;

    @Builder
    public Comment(int postSeq, String commentUploader, String commentText, LocalDateTime commentTimestamp) {
        this.postSeq = postSeq;
        this.commentUploader = commentUploader;
        this.commentText = commentText;
        this.commentTimestamp = commentTimestamp;
    }
}
