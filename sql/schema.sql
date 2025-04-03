
    create table model (
        active varchar(2) not null,
        category_id integer,
        created timestamp(6) with time zone not null,
        id bigserial not null,
        updated timestamp(6) with time zone,
        created_by varchar(100) not null,
        updated_by varchar(100),
        value varchar(100) not null,
        name varchar(200) not null,
        primary key (id)
    );

    create table product (
        active varchar(2),
        carton_char_qty integer,
        carton_label_qty integer,
        carton_qty integer,
        is_auto boolean not null,
        is_day boolean not null,
        is_month boolean not null,
        is_reject boolean not null,
        is_repair boolean not null,
        is_year boolean not null,
        product_id integer not null unique,
        uom_id integer not null,
        created timestamp(6) with time zone not null,
        id bigserial not null,
        model_id bigint not null,
        updated timestamp(6) with time zone,
        carton_name varchar(50),
        type varchar(50),
        created_by varchar(100) not null,
        updated_by varchar(100),
        value varchar(100) not null,
        name varchar(200) not null,
        primary key (id)
    );

    alter table if exists product 
       add constraint FKkj4rugu6x9sb4chv5msepdwmn 
       foreign key (model_id) 
       references model;
