drop table if exists commande;
drop table if exists produit;

create table produit (
  id int(11) not null auto_increment,
  code varchar(100),
  libelle varchar(100),
  prix double(10,2),
  primary key(id)
);

create table commande(
  id int(11) not null auto_increment,
  produit_id int(11) not null,
  numero varchar(50) not null,
  primary key(id),
  foreign key produit_fk(produit_id) references produit(id)
);