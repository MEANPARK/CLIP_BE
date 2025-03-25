package CLIP.Entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QChatRooms_TB is a Querydsl query type for ChatRooms_TB
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QChatRooms_TB extends EntityPathBase<ChatRooms_TB> {

    private static final long serialVersionUID = 1568856609L;

    public static final QChatRooms_TB chatRooms_TB = new QChatRooms_TB("chatRooms_TB");

    public final NumberPath<Long> chatRoomID = createNumber("chatRoomID", Long.class);

    public final StringPath chatUserID_1 = createString("chatUserID_1");

    public final StringPath chatUserID_2 = createString("chatUserID_2");

    public final StringPath name = createString("name");

    public QChatRooms_TB(String variable) {
        super(ChatRooms_TB.class, forVariable(variable));
    }

    public QChatRooms_TB(Path<? extends ChatRooms_TB> path) {
        super(path.getType(), path.getMetadata());
    }

    public QChatRooms_TB(PathMetadata metadata) {
        super(ChatRooms_TB.class, metadata);
    }

}

