# forum-application

## Function
1. Users can create post in forum website.
2. Users can register keywords they are interested in, so they can receive notifications when related posts are posted in website.
3. When post is created with tag, servers check the tag data model and send notification to users who are interested in the tags.

## Server structure

![Server structure](images/server_structure.png)

### Main server
Main servers get the information of user and post from client and save it to database. When users create posts, the main servers analyze the tags and send message id to Message servers.

### Message id

message id (number) = post id (number) + user id (number)

### Message Queue (Kafka)
Main servers are not resposible for dedupulication of message about post id and user id. Main servers just send message to Message server when message is created. Every messages from main server will be send to Kafka and Kafka send it to message server directly. Kafka can treats massive number of messages simultaneously (100,000 TPS).

### Message server
Message servers receive message id from message queue. The message servers deduplicate the message by using message id (consist of post id and user id) not to send notifications again to user who already got notification for the same post. Message servers are connected to Firebase service to send notification to android device of user.

### Temporary Storage (Redis)
Redis plays a role of storing temporary data to recognize duplicate message. If message servers receive message id from Kafka, they check redis if it has message id. If redis already has message id in itself, the request of creating notification will be ignored. It not, the message servers create notifiction to notify android device of user.

![Data process](images/data_model.png)