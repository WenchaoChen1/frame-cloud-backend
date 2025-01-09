-- public.musician definition

-- Drop table

-- DROP TABLE public.musician;

CREATE TABLE public.musician (
	musician_id varchar(64) NOT NULL,
	first_name varchar(64) NULL,
	last_name varchar(60) NULL,
	gender int4 NULL,
	latest_draft varchar(256) NULL,
	play_times int4 NULL,
	tune_coins int4 NULL,
	z_created_date timestamptz(6) NULL,
	z_created_user varchar(64) NULL,
	z_updated_account varchar(64) NULL,
	z_updated_date timestamptz(6) NULL,
	z_updated_user varchar(64) NULL,
	avatar varchar(36) NULL,
	description varchar(500) NULL,
	z_created_account varchar(64) NULL,
	"role" varchar(64) NULL,
	CONSTRAINT musician_pkey PRIMARY KEY (musician_id)
);


-- public.musician_award definition

-- Drop table

-- DROP TABLE public.musician_award;

CREATE TABLE public.musician_award (
	award_id varchar(64) NOT NULL,
	musician_id varchar(64) NOT NULL,
	award_name varchar(100) NULL,
	avatar varchar(36) NULL,
	description varchar(500) NULL,
	z_created_date timestamptz(6) NULL,
	z_created_user varchar(64) NULL,
	z_updated_account varchar(64) NULL,
	z_updated_date timestamptz(6) NULL,
	z_updated_user varchar(64) NULL,
	z_created_account varchar(64) NULL,
	CONSTRAINT musician_award_pkey PRIMARY KEY (award_id),
	CONSTRAINT fkscfkjl2n11lu6rgphx5uqsv8r FOREIGN KEY (musician_id) REFERENCES public.musician(musician_id)
);


-- public.musician_work definition

-- Drop table

-- DROP TABLE public.musician_work;

CREATE TABLE public.musician_work (
	work_id varchar(64) NOT NULL,
	musician_id varchar(64) NOT NULL,
	work_status varchar(50) NULL,
	work_name varchar(100) NULL,
	avatar varchar(36) NULL,
	play_times int4 NULL,
	tune_coins int4 NULL,
	description varchar(500) NULL,
	z_created_date timestamptz(6) NULL,
	z_created_user varchar(64) NULL,
	z_updated_account varchar(64) NULL,
	z_updated_date timestamptz(6) NULL,
	z_updated_user varchar(64) NULL,
	z_created_account varchar(64) NULL,
	CONSTRAINT musician_work_pkey PRIMARY KEY (work_id),
	CONSTRAINT fkjmytsljtjv02erq5teuxo75g3 FOREIGN KEY (musician_id) REFERENCES public.musician(musician_id)
);