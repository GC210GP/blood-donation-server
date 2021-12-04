package com.gachonsw.blooddonation.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QAssociation is a Querydsl query type for Association
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QAssociation extends EntityPathBase<Association> {

    private static final long serialVersionUID = 1599474935L;

    public static final QAssociation association = new QAssociation("association");

    public final com.gachonsw.blooddonation.entity.common.QBaseTimeEntity _super = new com.gachonsw.blooddonation.entity.common.QBaseTimeEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdDate = _super.createdDate;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedDate = _super.modifiedDate;

    public final StringPath name = createString("name");

    public QAssociation(String variable) {
        super(Association.class, forVariable(variable));
    }

    public QAssociation(Path<? extends Association> path) {
        super(path.getType(), path.getMetadata());
    }

    public QAssociation(PathMetadata metadata) {
        super(Association.class, metadata);
    }

}

