DROP TABLE IF EXISTS tbllocation;
CREATE TABLE tbllocation(
	loc_id				INT PRIMARY KEY	NOT NULL,
	loc_name			VARCHAR(50)		NOT NULL,
	loc_lat				DECIMAL(8,6)	NOT NULL,
	loc_lon				DECIMAL(9,6)	NOT NULL
);
DROP TABLE IF EXISTS tbltelescope;
CREATE TABLE tbltelescope(
	tel_id				INT PRIMARY KEY	NOT NULL,
	tel_manufacturer	VARCHAR(50)		NOT NULL,
	tel_type			VARCHAR(50)		NOT NULL,
	tel_aperture		INT				NOT NULL,
	tel_foc_length		INT				NOT NULL
);
DROP TABLE IF EXISTS tblbinocular;
CREATE TABLE tblbinocular(
	bin_id				INT PRIMARY KEY NOT NULL,
	bin_manufacturer	VARCHAR(50)		NOT NULL,
	bin_magnification	INT				NOT NULL,
	bin_aperture		INT				NOT NULL,
	bin_fov				INT				NOT NULL
);
DROP TABLE IF EXISTS tblconstellation;
CREATE TABLE tblconstellation(
	con_id				INT PRIMARY KEY	NOT NULL,
	con_name			VARCHAR(50)		NOT NULL,
	con_abbrevation		VARCHAR(3)		NOT NULL,
	con_description		TEXT			NULL
);
DROP TABLE IF EXISTS tbleyepiece;
CREATE TABLE tbleyepiece(
	eye_id				INT PRIMARY KEY	NOT NULL,
	eye_manufacturer	VARCHAR(50)		NOT NULL,
	eye_type			VARCHAR(50)		NOT NULL,
	eye_focal_length	INT				NOT NULL,
	eye_apparent_fov	INT				NOT	NULL
);
DROP TABLE IF EXISTS tblobjecttype;
CREATE TABLE tblobjecttype(
	typ_id				INT PRIMARY KEY NOT NULL,
	typ_name			VARCHAR(50)		NOT NULL,
	typ_description		TEXT			NULL
);
DROP TABLE IF EXISTS tblseeing;
CREATE TABLE tblseeing(
	see_id				INT PRIMARY KEY NOT NULL,
	see_scale			VARCHAR(50)		NOT NULL,
	see_rating			VARCHAR(50)		NOT NULL,
	see_description		TEXT			NULL
);
DROP TABLE IF EXISTS tblequipment;
CREATE TABLE tblequipment(
	equ_id				INT PRIMARY KEY	NOT NULL,
	equ_tel_fk			INT				NULL,
	equ_eye_fk			INT				NULL,
	equ_bin_fk			INT				NULL
);
DROP TABLE IF EXISTS tblobservation;
CREATE TABLE tblobservation(
	obs_id				INT PRIMARY KEY	NOT NULL,
	obs_equ_fk			INT				NULL,
	obs_loc_fk			INT				NOT NULL,
	obs_object			VARCHAR(100)	NOT NULL,
	obs_ra				DECIMAL(8,6)	NULL,
	obs_dec				DECIMAL(8,6)	NULL,
	obs_typ_fk			INT				NULL,
	obs_con_fk			INT				NULL,
	obs_observer		VARCHAR(50)		NULL,
	obs_datetime		DATETIME		NOT NULL,
	obs_condition_txt	TEXT			NULL,
	obs_see_fk			INT				NULL,
	obs_description		TEXT			NULL,
	obs_magnification	INT				NULL,
	obs_fov				INT				NULL,
	obs_image			BLOB			NULL
);