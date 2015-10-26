# microservices-blog
Ceci est une application basée sur une architecture en microservices
Elle est composée de 4 services:
- Un service d'authentification basé sur spring-security (spring-boot)
- Un service de gestion des articles en java (spring-boot)
- Un service de gestion des commentaires en java (spring-boot)
- Un service de Chat propulsé par https://www.firebase.com/
- Une passerelle qui est le seul point d'entrée entre les clients et les différents services (NodeJs + API REST)
- Un client AngularJS

Tous les services sont autonomes et c'est l'API REST qui ordonnance le flux d'exécution des programmes. 
Par exemple, si un client fait une requête HTTP vers un articles, c'est le front-end server (API REST en NodeJS) 
qui verifie les authorisations, fait deux requètes asynchrones sur les services de gestion des articles 
et des commentaires, agrège les differentes reponses, puis retourne un seul objet JSON au client avec
toutes les informations neccessaires.

Actuellement dans la version déployée, les différents services sont sur le cloud AMAZON (AWS) 
et le client angularJs est hébergé sur http://demkada.com. Ce qui fait de cette petite application,
un vrai logiciel distribué.

## Pour tester dans le cloud:
Soit vous vous rendez sur le lien http://microservices-blog.kadary.me

Soit vous pouvez déployer vous même l'image AMI (Amazone) que j'ai préparé avec toutes les dépendances néccessaires
sur votre compte d'essai AMAZON, ce qui vous donnera un accès root et vous verez comment les services sont managés.
Dans ce dernier cas, de votre console EC2 (https://eu-central-1.console.aws.amazon.com/ec2/v2/home?region=eu-central-1#Instances:sort=instanceId), 
créer une nouvelle installe à partir de l'image AMI nommée "ms-blog"
Une fois l'instance démarrée, lancez la commande bash:
``` 
./start-services.sh
```
Cette commande lancera tous les services pour vous, vous n'avez plus qu'à modifier l'URL de l'api-gateway dans votre client pour pouvoir utiliser cette architecture en backend

## Pour tester en local, vous aurez besoin de:
- Une version de java sur votre ordinateur
- Maven 3 installé et configuré
- la plateforme nodejs installé et npm fonctionnel
- le SDGB NoSQL MongoDB installé et fonctionnel

Puis il suffit de builder et exécuter chaque service à la manière spring-boot, lancez l'api-gateway avec node
et ouvrir le fichier index.html du repertoire angularjs-client dans votre navigateur web.

Teléchargez les versions packagées à cette URL: https://github.com/demkada/microservices-blog/releases
et exécutez les commandes suivantes:
```
java -jar authentication-service-0.0.1.jar
java -jar posts-service-1.2.6.RELEASE.jar
java -jar comments-service-1.2.6.RELEASE.jar
node ./api-gateway //Après avoir dezippé le fichier api-gateway.zip et je suppose que la plateforme nodeJs est fonctionnelle
```
Et lancez le fichier index.html présent dans le répertoire angularjs-client


