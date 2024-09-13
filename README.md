# Lancement infra

```
C:\Projects\seed-kafka > docker compose up -d
[+] Running 5/6
 - Volume "seed-kafka_klaw_data"  Created                                                                          3.3s
 ✔ Container klaw-core            Started                                                                          2.3s
 ✔ Container klaw-cluster-api     Started                                                                          2.3s
 ✔ Container kafka                Started                                                                          2.3s
 ✔ Container akhq                 Started                                                                          3.0s
 ✔ Container schema-registry      Started                                                                          3.0s
```

Une fois les conteneurs lancés, différents outils sont accessibles:
- Klaw: http://localhost:9097/
- AKHQ: http://localhost:9082/

# Création utilisateur

Une fois que Kafka est démarré, lancer une commande "exec" dans le container "kafka" et exécuter les commandes suivantes:
```
cd /etc/kafka/jaas

## ACL admin
/opt/kafka/bin/kafka-acls.sh --bootstrap-server localhost:9092 --command-config /etc/kafka/jaas/admin-client.properties --add --operation All --allow-principal User:admin --topic "*"
/opt/kafka/bin/kafka-acls.sh --bootstrap-server localhost:9092 --command-config /etc/kafka/jaas/admin-client.properties --list

## Add user "producer"
/opt/kafka/bin/kafka-configs.sh --bootstrap-server localhost:9092 --command-config /etc/kafka/jaas/admin-client.properties --alter --add-config 'SCRAM-SHA-512=[password=producer-secret]' --entity-type users --entity-name producer
/opt/kafka/bin/kafka-configs.sh --bootstrap-server localhost:9092 --command-config /etc/kafka/jaas/admin-client.properties --describe --entity-type users

## ACL "producer"
/opt/kafka/bin/kafka-acls.sh --bootstrap-server localhost:9092 --command-config /etc/kafka/jaas/admin-client.properties --add --operation describe --operation write --allow-principal User:producer --topic test
/opt/kafka/bin/kafka-console-producer.sh --bootstrap-server localhost:9092 --producer.config /etc/kafka/jaas/producer.properties --topic test
```
