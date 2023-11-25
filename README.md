Pour exécuter le projet, vous devez installer Docker et exécuter les commandes suivantes :

1.Exécutez la commande suivante pour créer et démarrer un conteneur MySQL :
docker run -p 3310:3306 --name mysql -e MYSQL_ROOT_PASSWORD=root -d mysql

2.Ensuite, exécutez la commande suivante pour créer et démarrer un conteneur phpMyAdmin, lié au conteneur MySQL précédemment créé :
docker run --name phpmyadmin -d --link mysql:db -p 8081:80 phpmyadmin

Après avoir exécuté ces commandes, ouvrez votre navigateur et accédez à phpMyAdmin en utilisant l'URL suivante : http://localhost:8081/ (nom d'utilisateur : root, mot de passe : root).

Créez un nouvel utilisateur en exécutant la requête SQL suivante dans phpMyAdmin :
CREATE USER 'test'@'%' IDENTIFIED WITH caching_sha2_password BY 'test';
GRANT USAGE ON *.* TO 'test'@'%';
ALTER USER 'test'@'%' REQUIRE NONE WITH MAX_QUERIES_PER_HOUR 0 MAX_CONNECTIONS_PER_HOUR 0 MAX_UPDATES_PER_HOUR 0 MAX_USER_CONNECTIONS 0;

