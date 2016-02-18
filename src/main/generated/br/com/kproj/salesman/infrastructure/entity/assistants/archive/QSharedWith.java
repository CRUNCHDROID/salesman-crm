package br.com.kproj.salesman.infrastructure.entity.assistants.archive;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QSharedWith is a Querydsl query type for SharedWith
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QSharedWith extends EntityPathBase<SharedWith> {

    private static final long serialVersionUID = -950970079L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QSharedWith sharedWith = new QSharedWith("sharedWith");

    public final br.com.kproj.salesman.infrastructure.entity.QIdentifiable _super = new br.com.kproj.salesman.infrastructure.entity.QIdentifiable(this);

    public final QFileInfo fileInfo;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final EnumPath<br.com.kproj.salesman.infrastructure.entity.enums.SharedType> type = createEnum("type", br.com.kproj.salesman.infrastructure.entity.enums.SharedType.class);

    public final br.com.kproj.salesman.infrastructure.entity.QUser user;

    public QSharedWith(String variable) {
        this(SharedWith.class, forVariable(variable), INITS);
    }

    public QSharedWith(Path<? extends SharedWith> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QSharedWith(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QSharedWith(PathMetadata<?> metadata, PathInits inits) {
        this(SharedWith.class, metadata, inits);
    }

    public QSharedWith(Class<? extends SharedWith> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.fileInfo = inits.isInitialized("fileInfo") ? new QFileInfo(forProperty("fileInfo"), inits.get("fileInfo")) : null;
        this.user = inits.isInitialized("user") ? new br.com.kproj.salesman.infrastructure.entity.QUser(forProperty("user"), inits.get("user")) : null;
    }

}

