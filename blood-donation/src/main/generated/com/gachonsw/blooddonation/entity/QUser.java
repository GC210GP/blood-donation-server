package com.gachonsw.blooddonation.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QUser is a Querydsl query type for User
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QUser extends EntityPathBase<User> {

    private static final long serialVersionUID = -221268971L;

    public static final QUser user = new QUser("user");

    public final com.gachonsw.blooddonation.entity.common.QBaseTimeEntity _super = new com.gachonsw.blooddonation.entity.common.QBaseTimeEntity(this);

    public final DatePath<java.time.LocalDate> birthdate = createDate("birthdate", java.time.LocalDate.class);

    public final EnumPath<BloodType> bloodType = createEnum("bloodType", BloodType.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdDate = _super.createdDate;

    public final StringPath email = createString("email");

    public final NumberPath<Long> frequency = createNumber("frequency", Long.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final BooleanPath isDonated = createBoolean("isDonated");

    public final BooleanPath isDormant = createBoolean("isDormant");

    public final StringPath job = createString("job");

    public final StringPath location = createString("location");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedDate = _super.modifiedDate;

    public final StringPath name = createString("name");

    public final StringPath nickname = createString("nickname");

    public final StringPath password = createString("password");

    public final StringPath phoneNumber = createString("phoneNumber");

    public final StringPath profileImageLocation = createString("profileImageLocation");

    public final DatePath<java.time.LocalDate> recency = createDate("recency", java.time.LocalDate.class);

    public final EnumPath<Sex> sex = createEnum("sex", Sex.class);

    public final ListPath<UserAuthority, QUserAuthority> userAuthorityList = this.<UserAuthority, QUserAuthority>createList("userAuthorityList", UserAuthority.class, QUserAuthority.class, PathInits.DIRECT2);

    public QUser(String variable) {
        super(User.class, forVariable(variable));
    }

    public QUser(Path<? extends User> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUser(PathMetadata metadata) {
        super(User.class, metadata);
    }

}

