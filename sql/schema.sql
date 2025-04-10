
    create table check_list (
        check_list_id integer not null unique,
        max_value numeric(38,2),
        min_value numeric(38,2),
        created timestamp(6) with time zone not null,
        id bigserial not null,
        updated timestamp(6) with time zone,
        created_by varchar(100) not null,
        updated_by varchar(100),
        value varchar(100) not null,
        name varchar(200) not null,
        type varchar(255) not null check (type in ('NUMBER','PASS_FALSE')),
        primary key (id)
    );

    create table check_list_process (
        check_list_id bigint not null,
        created timestamp(6) with time zone not null,
        id bigserial not null,
        process_id bigint not null,
        updated timestamp(6) with time zone,
        created_by varchar(100) not null,
        updated_by varchar(100),
        primary key (id)
    );

    create table form_load (
        form_load_id integer not null,
        status varchar(2),
        created timestamp(6) with time zone not null,
        id bigserial not null,
        updated timestamp(6) with time zone,
        created_by varchar(100) not null,
        updated_by varchar(100),
        name varchar(250) not null,
        path varchar(250),
        note varchar(255),
        primary key (id)
    );

    create table group_process (
        created timestamp(6) with time zone not null,
        id bigserial not null,
        updated timestamp(6) with time zone,
        created_by varchar(100) not null,
        updated_by varchar(100),
        code varchar(255) not null unique,
        description varchar(255),
        link_document varchar(255),
        name varchar(255) not null,
        type varchar(255) not null check (type in ('Production','OCC','KCS')),
        version varchar(255),
        primary key (id)
    );

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

    create table process (
        process_id integer not null unique,
        status boolean,
        created timestamp(6) with time zone not null,
        id bigserial not null,
        updated timestamp(6) with time zone,
        created_by varchar(100) not null,
        updated_by varchar(100),
        value varchar(100) not null,
        name varchar(200) not null,
        input_method varchar(255) not null check (input_method in ('GIA_CONG','CHECKLIST','KIEM_TRA')),
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
