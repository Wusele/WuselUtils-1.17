# WuselUtilities

### Discord Support

1. _**Simple API for creating a Discord Bot using Javacord**_
   * Create an instance
     * ``
       DiscordBot bot = new DiscordBot(this, "<botToken>");
       ``
   * Start the bot
     * ``
       bot.start();
       ``
   * Stop the bot 
     * ``
       bot.stop();
       ``
   * Get Javacord's DiscordApi class created in DiscordBot
     * ``
       bot.getApi();
       ``




### Firebase Support

1. _**FireDataBase**_
    * Create an instance
      * ``
        FireDataBase fireDataBase = new FireDataBase("<path>"); 
        ``
    * Get a variable from a document
      * ``
        String phoneNumber = dataBase.getString("myCollection", "secretDocument", "phoneNumber");
        ``
    * Change document
      * ``dataBase.setField(hashMap, "myCollection", "secretDocument");``
    * Tool to create Hashmap quicker
      * ``dataBase.makeHashmap(Arrays.asList("name", "phoneNumber"), Arrays.asList("Anton", "911"));``
    * Set EventListeners
      * `dataBase.setEventListener("myCollection", "secretDocument", new EventData() {
           @Override
           public void onEvent(DocumentSnapshot value, FirestoreException error) {
                System.out.println("New Name: " + value.get("name"));
                System.out.println("New Phone Number: " + value.get("phoneNumber"));
           }
        });`