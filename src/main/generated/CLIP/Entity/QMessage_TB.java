package CLIP.Entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMessage_TB is a Querydsl query type for Message_TB
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMessage_TB extends EntityPathBase<Message_TB> {

    private static final long serialVersionUID = 191650874L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMessage_TB message_TB = new QMessage_TB("message_TB");

    public final QChatRooms_TB chatRooms_tb;

    public final NumberPath<Long> messageID = createNumber("messageID", Long.class);

    public final StringPath messageText = createString("messageText");

    public final StringPath sender = createString("sender");

    public QMessage_TB(String variable) {
        this(Message_TB.class, forVariable(variable), INITS);
    }

    public QMessage_TB(Path<? extends Message_TB> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QMessage_TB(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QMessage_TB(PathMetadata metadata, PathInits inits) {
        this(Message_TB.class, metadata, inits);
    }

    public QMessage_TB(Class<? extends Message_TB> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.chatRooms_tb = inits.isInitialized("chatRooms_tb") ? new QChatRooms_TB(forProperty("chatRooms_tb")) : null;
    }

}

