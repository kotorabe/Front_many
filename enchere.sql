CREATE SEQUENCE admin_idadmin_seq;
CREATE TABLE IF NOT EXISTS public.admin
(
    idadmin integer NOT NULL DEFAULT nextval('admin_idadmin_seq'::regclass),
    nom text COLLATE pg_catalog."default" NOT NULL,
    mdp text COLLATE pg_catalog."default" NOT NULL,
    compte double precision DEFAULT 0,
    CONSTRAINT admin_pkey PRIMARY KEY (idadmin)
);

CREATE SEQUENCE categorie_idcategorie_seq;
CREATE TABLE IF NOT EXISTS public.categorie
(
    idcategorie integer NOT NULL DEFAULT nextval('categorie_idcategorie_seq'::regclass),
    categorie varchar(50),
    dureeencherecategorie double precision NOT NULL DEFAULT 0,
    CONSTRAINT categorie_pkey PRIMARY KEY (idcategorie)
);
INSERT INTO Categorie (categorie) values
                                      ('Bijoux'),
                                      ('Voiture');

CREATE SEQUENCE enchere_idenchere_seq;
CREATE TABLE IF NOT EXISTS public.enchere
(
    idenchere integer NOT NULL DEFAULT nextval('enchere_idenchere_seq'::regclass),
    idutilisateur integer NOT NULL,
    dureeenchere double precision NOT NULL DEFAULT 0,
    description text COLLATE pg_catalog."default",
    idcategorie integer NOT NULL,
    dateheureenchere timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    prixdevente double precision NOT NULL,
    prixminimum double precision NOT NULL,
    etat integer DEFAULT 0,
    datefinenchere timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT enchere_pkey PRIMARY KEY (idenchere),
    CONSTRAINT enchere_idcategorie_fkey FOREIGN KEY (idcategorie)
    REFERENCES public.categorie (idcategorie) MATCH SIMPLE
                               ON UPDATE NO ACTION
                               ON DELETE NO ACTION,
    CONSTRAINT enchere_idutilisateur_fkey FOREIGN KEY (idutilisateur)
    REFERENCES public.utilisateur (idutilisateur) MATCH SIMPLE
                               ON UPDATE NO ACTION
                               ON DELETE NO ACTION
);
INSERT INTO Enchere (idUtilisateur,dureeEnchere,idCategorie,prixdevente,prixminimum) values
                                                                                                   (2,2.0,1,2000,1500),
                                                                                                   (1,0.5,2,3000,2750);

CREATE TABLE IF NOT EXISTS public.commission
(
    idenchere integer NOT NULL,
    commission double precision NOT NULL DEFAULT 30000,
    CONSTRAINT commission_idenchere_fkey FOREIGN KEY (idenchere)
        REFERENCES public.enchere (idenchere) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);

CREATE SEQUENCE rechargement_idrechargement_seq;
CREATE TABLE IF NOT EXISTS public.rechargement
(
    idrechargement integer NOT NULL DEFAULT nextval('rechargement_idrechargement_seq'::regclass),
    idutilisateur integer NOT NULL,
    montantrecharge double precision,
    dateheurechargement timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    validation integer DEFAULT 0,
    CONSTRAINT rechargement_pkey PRIMARY KEY (idrechargement),
    CONSTRAINT rechargement_idutilisateur_fkey FOREIGN KEY (idutilisateur)
        REFERENCES public.utilisateur (idutilisateur) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);

CREATE SEQUENCE surencherir_idsurencherir_seq;
CREATE TABLE IF NOT EXISTS public.surencherir
(
    idsurencherir integer NOT NULL DEFAULT nextval('surencherir_idsurencherir_seq'::regclass),
    idenchere integer,
    idutilisateur integer,
    montant double precision NOT NULL,
    dateheuresurenchere timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT surencherir_pkey PRIMARY KEY (idsurencherir),
    CONSTRAINT surencherir_idenchere_fkey FOREIGN KEY (idenchere)
        REFERENCES public.enchere (idenchere) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT surencherir_idutilisateur_fkey FOREIGN KEY (idutilisateur)
        REFERENCES public.utilisateur (idutilisateur) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);

CREATE TABLE IF NOT EXISTS public.token
(
    token text COLLATE pg_catalog."default",
    expire date,
    idutilisateur integer
);

CREATE SEQUENCE utilisateur_idutilisateur_seq;
CREATE TABLE IF NOT EXISTS public.utilisateur
(
    idutilisateur integer NOT NULL DEFAULT nextval('utilisateur_idutilisateur_seq'::regclass),
    nom text COLLATE pg_catalog."default" NOT NULL,
    prenom text COLLATE pg_catalog."default" NOT NULL,
    email text COLLATE pg_catalog."default" NOT NULL,
    mdp text COLLATE pg_catalog."default" NOT NULL,
    solde_compte double precision DEFAULT 0,
    CONSTRAINT utilisateur_pkey PRIMARY KEY (idutilisateur)
);
INSERT INTO utilisateur (nom, prenom,email,mdp,solde_compte) values
                                                                 ('Rakoto', 'Hery', 'hery@gmail.com','hery123',1000.5),
                                                                 ('Jean','Marc','jean@gmail.com','marc123', 3500);


CREATE OR REPLACE VIEW public.enchere_solde
 AS
 SELECT v_enchere_surencherir.idenchere,
    max(v_enchere_surencherir.montant) AS montant,
    v_enchere_surencherir.dateheureenchere
   FROM v_enchere_surencherir
  GROUP BY v_enchere_surencherir.idenchere, v_enchere_surencherir.dateheureenchere;

CREATE OR REPLACE VIEW public.rechargement_non_valide
 AS
 SELECT v_utilisateur_rechargement.idutilisateur,
    v_utilisateur_rechargement.nom,
    v_utilisateur_rechargement.prenom,
    v_utilisateur_rechargement.email,
    v_utilisateur_rechargement.mdp,
    v_utilisateur_rechargement.solde_compte,
    v_utilisateur_rechargement.montantrecharge,
    v_utilisateur_rechargement.dateheurechargement,
    v_utilisateur_rechargement.validation
   FROM v_utilisateur_rechargement
  WHERE v_utilisateur_rechargement.validation = 0;

CREATE OR REPLACE VIEW public.v_enchere_categorie
 AS
 SELECT enchere.idenchere,
    enchere.idutilisateur,
    enchere.dureeenchere,
    enchere.description,
    enchere.idcategorie,
    enchere.dateheureenchere,
    enchere.prixdevente,
    enchere.prixminimum,
    enchere.etat,
    enchere.datefinenchere,
    categorie.categorie
   FROM enchere,
    categorie
  WHERE enchere.idcategorie = categorie.idcategorie;

CREATE OR REPLACE VIEW public.v_enchere_surencherir
 AS
 SELECT enchere.idenchere,
    enchere.dureeenchere,
    enchere.description,
    enchere.dateheureenchere,
    surencherir.montant
   FROM enchere,
    surencherir
  WHERE enchere.idenchere = surencherir.idenchere;

CREATE OR REPLACE VIEW public.v_utilisateur_rechargement
 AS
 SELECT utilisateur.idutilisateur,
    utilisateur.nom,
    utilisateur.prenom,
    utilisateur.email,
    utilisateur.mdp,
    utilisateur.solde_compte,
    rechargement.montantrecharge,
    rechargement.dateheurechargement,
    rechargement.validation
   FROM utilisateur,
    rechargement
  WHERE utilisateur.idutilisateur = rechargement.idutilisateur;

CREATE OR REPLACE VIEW public.v_utilisateur_token
 AS
 SELECT utilisateur.idutilisateur,
    utilisateur.nom,
    utilisateur.prenom,
    utilisateur.email,
    utilisateur.mdp,
    utilisateur.solde_compte,
    token.token,
    token.expire
   FROM token,
    utilisateur
  WHERE utilisateur.idutilisateur = token.idutilisateur;
