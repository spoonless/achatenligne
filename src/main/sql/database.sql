drop table if exists ligne_commande;
drop table if exists commande;
drop table if exists produit;

create table produit (
  id int(11) not null auto_increment,
  code varchar(100),
  libelle varchar(100),
  prix double(10,2),
  primary key(id)
);

insert into produit values (1, 'XTV12', 'Pomme', 1.12);
insert into produit values (2, 'WTV99', 'Poire', 1.30);

create table commande(
  id int(11) not null auto_increment,
  date_creation datetime default NOW(),
  primary key(id)
);

create table ligne_commande(
  commande_id int(11) not null,
  produit_id int(11) not null,
  foreign key commande_fk(commande_id) references commande(id),
  foreign key produit_fk(produit_id) references produit(id)
);
