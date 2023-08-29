# forum-application

## Function
1. Users can create post in forum website.
2. Users can register keywords they are interested in, so they can receive notifications when related posts are posted.
3. When post is created with tag, servers check the tag data model and send notification to users who are interested in the tag.

## Server structure
### Main server
Main servers get the information of user and post from client and save it to database. When users create posts, the main servers analyze tag and send notification to message servers to extract post id and user id with deduplication.

### Message server
Message servers get notification from main servers. The message servers deduplicate the message by using message id(which is consist of post id and user id) 