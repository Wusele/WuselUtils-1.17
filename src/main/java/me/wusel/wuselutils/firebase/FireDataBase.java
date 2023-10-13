/*
 * Copyright (c) 2022.
 * Class Copyright is owned by Wusel
 */

package me.wusel.wuselutils.firebase;

import com.google.api.core.ApiFuture;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.*;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;

import javax.annotation.Nullable;
import java.io.FileInputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/*
    Base Class for FireCollection and FireDocument

 */

public class FireDataBase {

    private Firestore db;

    public FireDataBase(String accountUrl) {
        FileInputStream serviceAccount = null;
        FirebaseOptions options = null;
        try {
            serviceAccount = new FileInputStream(accountUrl);
            options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .build();
            FirebaseApp.initializeApp(options);
            this.db = FirestoreClient.getFirestore();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public FireCollection getFireCollection(String collection) {
        return new FireCollection(this, collection);
    }

    public FireDocument getFireDocument(String collection, String document) {
        return new FireDocument(new FireCollection(this, collection), document);
    }

    public DocumentSnapshot getDocument(String collection, String document) {
        DocumentReference docRef = db.collection(collection).document(document);
        ApiFuture<DocumentSnapshot> future = docRef.get();
        DocumentSnapshot doc = null;
        try {
            doc = future.get();
        }catch (Exception e) {
            e.printStackTrace();
        }
        if (doc.exists())
            return doc;
        return null;
    }

    public boolean docExists(String collection, String document) {
        DocumentSnapshot doc = getDocument(collection, document);
        return (doc.exists());
    }
    public boolean docExists(DocumentSnapshot doc) {
        return (doc.exists());
    }


    public Boolean contains(String collection, String document, String field) {
        DocumentSnapshot doc = getDocument(collection, document);
        if (docExists(doc)) {
            return doc.contains(field);
        }
        return false;
    }

    public String getString(String collection, String document, String field) {
        DocumentSnapshot doc = getDocument(collection, document);
        if (docExists(doc)) {
            return doc.getString(field);
        }
        return "";
    }

    public Object get(String collection, String document, String field) {
        DocumentSnapshot doc = getDocument(collection, document);
        if (docExists(doc))
            return doc.get(field);
        return null;
    }

    public String getId(String collection, String document) {
        DocumentSnapshot doc = getDocument(collection, document);
        if (docExists(doc))
            return doc.getId();
        return "";
    }

    public void setField(HashMap<String, String> hashMap, String collection, String document) {
        ApiFuture<WriteResult> future = this.db.collection(collection).document().set(hashMap);
    }

    public void setEventListener(String collection, String document, EventData eventData) {
        DocumentReference docRef = db.collection(collection).document(document);
        docRef.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirestoreException error) {
                eventData.onEvent(value, error);
            }
        });
    }

    public Firestore getFirestore() {
        return db;
    }

    public HashMap<String, String> makeHashmap(List<String> keys, List<String> values) {
        HashMap<String, String> hashMap = new HashMap<>();
        for (int i = 0; i < keys.size(); i++) {
            hashMap.put(keys.get(i), values.get(i));
        }
        return hashMap;
    }

    public Lis<String> makeList(String... content) {
        return Arrays.asList(content);
    }


}
