/*
 * Copyright (c) 2022.
 * Class Copyright is owned by Wusel
 */

package me.wusel.wuselutils.firebase;

import java.util.HashMap;
import java.util.List;

/*
    Works using the FireDataBase class
 */

public class FireCollection {

    private FireDataBase fireDataBase;
    private String collection;

    public FireCollection(FireDataBase dataBase, String collection) {
        this.fireDataBase = dataBase;
        this.collection = collection;
    }

    public Boolean contains(String document, String field) {
        return fireDataBase.contains(collection, document, field);
    }

    public boolean docExists(String document) {
        return fireDataBase.docExists(collection, document);
    }

    public String getString(String document, String field) {
        return fireDataBase.getString(collection, document, field);
    }

    public FireDocument getFireDocument(String document) {
        return new FireDocument(this, document);
    }

    public Object get(String document, String field) {
        return fireDataBase.get(collection, document, field);
    }

    public String getId(String document) {
        return fireDataBase.getId(collection, document);
    }

    public void setEventListener(String document, EventData eventData) {
        fireDataBase.setEventListener(collection, document, eventData);
    }

    public HashMap<String, String> makeHashmap(List<String> keys, List<String> values) {
        return fireDataBase.makeHashmap(keys, values);
    }


}
