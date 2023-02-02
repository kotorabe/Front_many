INSERT INTO utilisateur (nom, prenom,email,mdp,solde_compte) values
('Rabe', 'Hery', 'rabe@gmail.com','hery123',1000.5),
('Ravao','Marc','ravao@gmail.com','marc123', 3500),
('Rasoa', 'Hery', 'rasoa@gmail.com','hery123',1000.5),
('Rambola','Marc','rambola@gmail.com','marc123', 3500);



INSERT INTO admin(nom,mdp,compte) values
('Manitra','manitra123', 40000),
('Mani','mani123',40000);


INSERT INTO Categorie (categorie) values
('Tableau'),
('Maison');

INSERT INTO rechargementcompte (idUtilisateur, montantrecharge) values
(3, 2000),
(4, 3000);

INSERT INTO Enchere (idUtilisateur,dureeEnchere,idCategorie,prixdevente,prixminimum) values
(2,2.0,1,3000,1500),
(1,0.5,2,3000,2750),
(2,2.0,1,4000,1500),
(3,0.5,2,3000,2750);


INSERT INTO surencherir (idEnchere,idUtilisateur,montant) values
(2,2,800),
(1,1,750);
(2,2,800),
(1,1,750),
(2,2,800),
(1,1,750);

iNSERT INTO commission (idEnchere,commission) values
(3,10),
(4,20),
(5,10);