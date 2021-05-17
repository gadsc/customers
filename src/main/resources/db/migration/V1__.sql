CREATE TABLE customers(
	id uuid NOT NULL CONSTRAINT customer_pk PRIMARY KEY,
	birthdate DATE NOT NULL,
	civil_status varchar(55) NOT NULL,
	email varchar(255) NOT NULL,
	job_title varchar(255),
	father_full_name varchar(255),
	mother_full_name varchar(255),
	name varchar(255) NOT NULL,
	city_of_birth varchar(255),
	country_of_birth varchar(255),
	state_of_birth varchar(255),
	nationality varchar(255) NOT NULL,
	politically_exposed boolean,
	code varchar(255),
	main_document_type varchar(255),
	created_at TIMESTAMP NOT NULL DEFAULT now(),
	updated_at TIMESTAMP NOT NULL DEFAULT now(),
	deleted_at TIMESTAMP
);

create table customer_addresses (
    id UUID not null,
    address_type varchar(255) NOT NULL,
    city varchar(255) NOT NULL,
    complement varchar(255),
    neighborhood varchar(255) NOT NULL,
    state varchar(255) NOT NULL,
    street varchar(255) NOT NULL,
    zipcode varchar(255) NOT NULL,
    number varchar(50) NOT NULL,
    customer_id UUID,
    primary key (id)
);

create table customer_phones (
   id UUID not null,
   number varchar(255) NOT NULL,
   type varchar(255) NOT NULL,
   customer_id UUID,
   primary key (id)
);

alter table customer_addresses
   add constraint FK_address_customer
   foreign key (customer_id)
   references customers;

alter table customer_phones
   add constraint FK_phone_customer
   foreign key (customer_id)
   references customers;

