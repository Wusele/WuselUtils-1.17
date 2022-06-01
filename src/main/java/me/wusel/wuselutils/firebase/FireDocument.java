/*
 * Copyright (c) 2022.
 * Class Copyright is owned by Wusel
 */

package me.wusel.wuselutils.firebase;

public class FireDocument {

    private final String document;
    private final FireCollection collection;

    public FireDocument(FireCollection collection, String document) {
        this.collection = collection;
        this.document = document;
    }

    public String getString(String field) {
        return collection.getString(document, field);
    }
}
