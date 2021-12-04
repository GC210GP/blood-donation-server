package com.gachonsw.blooddonation.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QUserAssociation is a Querydsl query type for UserAssociation
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QUserAssociation extends EntityPathBase<UserAssociation> {

    private static final long serialVersionUID = 1843192108L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QUserAssociation userAssociation = new QUserAssociation("userAssociation");

    public final com.gachonsw.blooddonation.entity.common.QBaseTimeEntity _super = new com.gachonsw.blooddonation.entity.common.QBaseTimeEntity(this);

    public final QAssociation association;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdDate = _super.createdDate;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedDate = _super.modifiedDate;

    public final QUser user;

    public QUserAssociation(String variable) {
        this(UserAssociation.class, forVariable(variable), INITS);
    }

    public QUserAssociation(Path<? extends UserAssociation> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QUserAssociation(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QUserAssociation(PathMetadata metadata, PathInits inits) {
        this(UserAssociation.class, metadata, inits);
    }

    public QUserAssociation(Class<? extends UserAssociation> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.association = inits.isInitialized("association") ? new QAssociation(forProperty("association")) : null;
        this.user = inits.isInitialized("user") ? new QUser(forProperty("user")) : null;
    }

}

