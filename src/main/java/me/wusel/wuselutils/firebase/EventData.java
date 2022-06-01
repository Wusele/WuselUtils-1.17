/*
 * Copyright (c) 2022.
 * Class Copyright is owned by Wusel
 */

package me.wusel.wuselutils.firebase;

import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.FirestoreException;

public interface EventData {

    public void onEvent(DocumentSnapshot value, FirestoreException error);
}
