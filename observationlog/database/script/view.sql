DROP VIEW OBSERVATIONS;

CREATE VIEW OBSERVATIONS AS
    SELECT tblobservation.obs_id,
           tblobservation.obs_equ_fk,
           tbllocation.loc_name,
           tblobservation.obs_object,
           tblobservation.obs_ra,
           tblobservation.obs_dec,
           tblobservation.obs_typ_fk,
           tblconstellation.con_abbrevation,
           tblobservation.obs_observer,
           tblobservation.obs_datetime,
           tblobservation.obs_condition_txt,
           tblseeing.see_scale,
           tblseeing.see_rating,
           tblobservation.obs_description,
           tblobservation.obs_magnification,
           tblobservation.obs_fov,
           tblobservation.obs_image
      FROM tblobservation,
           tbllocation,
           tblconstellation,
           tblseeing
     WHERE tblobservation.obs_loc_fk = tbllocation.loc_id AND 
           tblobservation.obs_con_fk = tblconstellation.con_id AND 
           tblobservation.obs_see_fk = tblseeing.see_id;

DROP VIEW SEEING;

CREATE VIEW SEEING AS
    SELECT tblseeing.see_id,
           tblseeing.see_scale,
           tblseeing.see_rating,
           tblseeing.see_description
      FROM tblseeing;
