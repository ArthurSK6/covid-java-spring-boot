# Lien vers le front

[Angular](https://github.com/ArthurSK6/covid_angular)

# Projet réalisé par :

Nicolas KIEGER
Arthur HURDEBOURG

# Set up du projet

Afin que le projet fonctionne correctement, il vous faudra avoir une base de données PostgreSQL d'initialisé contenant une base vide nommé covid-db.  
Vous pourrez indiquer dans le fichier `covid-java-spring-boot\covid-api\src\main\resources\application.yaml` le nom d'utilisateur ainsi que le mot de passe nécessaire afin de se connecter à la base.  

Le Backend est configuré sur le port 8081 soit : `http://localhost:8081/`  

# API REST

Tous les endpoints suivant commenceront par : `http://localhost:8081/api/`  
De plus, tous les mots entre crochets **{}** représente des variables à insérer.  
Pour finir:
* Les dates sont au format AAAA/MM/JJ
* Les mails doivent être formater comme un vrai mail
* Un mot de passe doit avoir au moins 8 caractères dont une Majuscule, une minuscule, un caractère spécial et un chiffre

Par defaut, 3 utilisateurs (Super administrateur, Administrateur et Docteur) sont créés sans centre attribué. Leurs identifiants sont:
* Email : `super@admin.com`  Mdp : `super@admin.com1`
* Email : `admin@admin.com`  Mdp : `admin@admin.com1
* Email : `docteur@docteur.com`  Mdp : `docteur@docteur.com1`

De plus, un centre est également créé:
  ```json
  { 
      "id": 1,
      "name": "CHRU Brabois",
      "postalCode": "54000",
      "address": "10 rue Brabois",
      "city": "Brabois"
  }

## PUBLIC

#### Création d'un rendez-vous

Permet de créer un nouveau rendez-vous dans un centre précis:
* Endpoint : **POST** `public/rdv/add?idCenter={idCentre}`
```json
{  
    "nom":"John",  
    "prenom":"Doe",  
    "email":"johndoe@rdv.com",  
    "telephone":"0600000000",  
    "date":"2024-01-31"  
}
```

#### Récupération de la liste de tous les centres de vaccinations

Permet d'obtenir la liste de tous les centres de vaccinations:
* Endpoint : **GET** `public/centre/all`

#### Récupération de la liste des centres de vaccinations par:

* Id :  **GET** `public/centre/id/{id}`
* Ville : **GET** `public/centre/city/{city}`
* Code postal : **GET** `public/centre/postalcode/{postalcode}`
* Nom : **GET** `public/centre/name/{name}`

## PRIVE

On précisera que l'administrateur à également accès aux endpoints docteur et que le super administrateur à accès à tous les endpoints.

### Docteur

#### Rendez-vous :

* Récupérer tous les rendez-vous à une date d'un centre: **GET** `doctor/rdv/date?date={date}&idCenter={idCentre}`
* Récupérer le rendez-vous par id : **GET** `doctor/rdv/id/{id}`
* Valider le rendez-vous (et donc avoir vacciné) par id : **PUT** `doctor/rdv/validate/{id}`
* Supprimer le rendez-vous par id : **DELETE** `doctor/rdv/delete/{id}`

#### Utilisateur:

* Récupérer l'utilisateur par id : **GET** `doctor/user/id/{id}`

---

### Administrateur

#### Utilisateur:

* Récupérer l'utilisateur par rôle : **GET** `admin/user/role/{role}`
* Récupérer l'utilisateur par rôle et centre de vaccination : **GET** `admin/user/role/{role}/{centerId}`
* Supprimer par id : **DELETE** `admin/user/delete/{id}`
* Supprimer le centre de vaccination de l'utilisateur par id : **PUT** `admin/user/deletecenter/{id}`
* Modifier un utilisateur : **PUT** `admin/user/update`
  ```json
    {
        "id": "3",
        "nom": "Docteur",
        "prenom": "Modifie",
        "email": "docteur@modifie.com",
        "password": "docteur@modifie.com1",
        "role": "DOCTOR"
    }
  ```

#### Centre:

* Lier un utilisateur à un centre : **PUT** `admin/centre/utilisateur/add?idUser={idUser}&idCenter={idCentre}`
* Récupérer tous les utilisateurs d'un centre par id :**GET** `admin/centre/utilisateur/{idCenter}`

---

### Super Administrateur

#### Utilisateur:

* Obtenir la liste de tous les utilisateurs : **GET** `superadmin/user/all`

#### Centre:

* Supprimer un centre : **DELETE** `superadmin/centre/delete/{id}`
* Créer un centre : **POST** `superadmin/centre/add`
  ```json
  {
      "name": "CHRU Mercy",
      "postalCode": "57000",
      "address": "11 rue à Metz",
      "city": "Metz"
  }
  ```
* Modifier un centre : **PUT** `superadmin/centre/update`
  ```json
  { 
      "id": 1,
      "name": "CHRU Brabois",
      "postalCode": "54000",
      "address": "10 rue à Nancy",
      "city": "Nancy"
  }
  ```
