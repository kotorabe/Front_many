Create database enchere;
Create role enchere login password 'enchere';
Alter database enchere owner to enchere;
\c enchere enchere


drop table surencherir;
drop table Produit_image;
drop table Produitenenchere;
drop table enchere;
drop table rechargementcompte;
drop table Produit;
drop table categorie;
drop table utilisateur;

-- 1
Create table utilisateur(
    idUtilisateur serial primary key not null,
    nom varchar(20) not null,
    prenom varchar(20) not null,
    email varchar(20) not null,
    mdp text not null,
    solde_compte float default 0
);
INSERT INTO utilisateur (nom, prenom,email,mdp,solde_compte) values
('Rakoto', 'Hery', 'hery@gmail.com','hery123',1000.5),
('Jean','Marc','jean@gmail.com','marc123', 3500);


-- 2
Create table admin(
    idAdmin serial primary key not null,
    nom text not null,
    mdp text not null,
    compte float default 0
);

INSERT INTO admin(nom,mdp,compte) values
('Jennifer','jenn123', 40000),
('Mianta','mianta123',40000);


-- 3
Create table Categorie(
    idCategorie serial primary key not null,
    categorie text not null,
    dureeEnchereCategorie double precision default 0 not null
);
INSERT INTO Categorie (categorie) values
('Bijoux'),
('Voiture');

-- 4
-- 5
Create table rechargement(
    idRechargement serial primary key not null,
    idUtilisateur int not null references Utilisateur(idUtilisateur),
    montantrecharge float,
    dateheurechargement timestamp default current_timestamp,
    validation int default 0
);
INSERT INTO rechargement (idUtilisateur, montantrecharge) values
(2, 2000),
(1, 3000);


-- 6
Create table Enchere(
    idEnchere serial primary key not null,
    idUtilisateur int  not null references utilisateur(idUtilisateur),
    dureeEnchere double precision default 0 not null,
    description TEXT,
    idCategorie int not null references categorie(idCategorie),
    dateheureenchere timestamp default current_timestamp,
    prixdevente float not null,
    prixminimum float not null
);
INSERT INTO Enchere (idEnchere,idUtilisateur,dureeEnchere,idCategorie,prixdevente,prixminimum) values
(2,2,2.0,1,2000,1500),
(1,1,0.5,2,3000,2750);

-- 7
Create table surencherir(
    idSurencherir serial primary key not null,
    idEnchere int references Enchere(idEnchere),
    idUtilisateur int references Utilisateur(idUtilisateur),
    montant float not null,
    dateheuresurenchere timestamp default current_timestamp
);
INSERT INTO surencherir (idEnchere,idUtilisateur,montant) values
(2,2,800),
(1,1,750);

-- 8 ATAO MongoDb
Create table Produit_image(
    idEnchere int not null references enchere(idEnchere),
    image TEXT
);
INSERT INTO Produit_image (idEnchere) values
(1),
(2);
-- 9
Create table commission(
    idEnchere int not null references Enchere(idEnchere),
    commission float not null default 30000
);
INSERT INTO commission (idEnchere,commission) values
(1,1000),
(2,1000);

--  rehefa valider de manao payement anleh enchere de misy ho any comission de misy ho an'ny admincreate table payement();


 create table token(
    token text,expire date,idutilisateur int
);

create or replace view v_utilisateur_rechargement as select utilisateur.*,montantrecharge,dateheurechargement,validation from utilisateur,rechargement where utilisateur.idutilisateur = rechargement.idutilisateur;

create or replace view rechargement_non_valide as select * from v_utilisateur_rechargement where validation = 0;

create or replace view v_enchere_surencherir as select enchere.idenchere,dureeenchere,description,dateheureenchere,montant from enchere,surencherir where enchere.idenchere = surencherir.idenchere;

create or replace view enchere_solde as select idenchere,max(montant) as montant,dateheureenchere from v_enchere_surencherir group by idenchere,dateheureenchere;

create or replace view v_utilisateur_token as select utilisateur.*,token,expire from token,utilisateur where utilisateur.idutilisateur = token.idutilisateur;

create or replace view v_enchere_categorie as select enchere.*,categorie from enchere,categorie where enchere.idCategorie = categorie.idCategorie;