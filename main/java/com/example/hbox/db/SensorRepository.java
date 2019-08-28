package com.example.hbox.db;


import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.mongodb.lang.NonNull;
import com.mongodb.stitch.android.core.Stitch;
import com.mongodb.stitch.android.core.StitchAppClient;
import com.mongodb.stitch.android.core.auth.StitchUser;
import com.mongodb.stitch.android.services.mongodb.remote.RemoteFindIterable;
import com.mongodb.stitch.android.services.mongodb.remote.RemoteMongoClient;
import com.mongodb.stitch.android.services.mongodb.remote.RemoteMongoCollection;
import com.mongodb.stitch.core.auth.providers.anonymous.AnonymousCredential;
import com.mongodb.stitch.core.services.mongodb.remote.sync.ChangeEventListener;
import com.mongodb.stitch.core.services.mongodb.remote.sync.DefaultSyncConflictResolvers;
import com.mongodb.stitch.core.services.mongodb.remote.sync.ErrorListener;
import com.mongodb.stitch.core.services.mongodb.remote.sync.internal.ChangeEvent;

import org.bson.BsonValue;
import org.bson.Document;

import java.util.Set;

public class SensorRepository {

    RemoteMongoCollection _remoteCollection;

    public void init() {

        // Create the StitchAppClient
        final StitchAppClient client = Stitch.initializeAppClient("box420app-ehwav");

        // Log-in using an Anonymous authentication provider from Stitch
        client.getAuth().loginWithCredential(new AnonymousCredential())
                .addOnCompleteListener(new OnCompleteListener<StitchUser>() {
                    @Override
                    public void onComplete(@NonNull Task<StitchUser> task) {

                        // Get a remote client
                        final RemoteMongoClient _remoteMongoClient = client.getServiceClient(RemoteMongoClient.factory, "mongodb-atlas");

                        // Set up the atlas collection
                        _remoteCollection = _remoteMongoClient.getDatabase("box420").getCollection("sensors");

                        // Configure automatic data sync between Atlas and local
                        // In this example, conflicts are resolved by giving preference
                        // to remote changes.
                        _remoteCollection.sync().configure(
                                DefaultSyncConflictResolvers.remoteWins(),
                                new MyUpdateListener(),
                                new MyErrorListener());
                        findAll();
                    }
                });

    }


    public void findAll() {



        RemoteFindIterable findTask = _remoteCollection.find();
        findTask.forEach(item -> {
            Log.d("app", String.format("successfully found:  %s", item.toString()));
        });

        init();


    }

    private class MyErrorListener implements ErrorListener {
        @Override
        public void onError(BsonValue documentId, Exception error) {
            Log.e("Stitch", error.getLocalizedMessage());
            Set<BsonValue> docsThatNeedToBeFixed = _remoteCollection.sync().getPausedDocumentIds();
            for (BsonValue doc_id : docsThatNeedToBeFixed) {
                // Add your logic to inform the user.
                // When errors have been resolved, call
                _remoteCollection.sync().resumeSyncForDocument(doc_id);
            }
            // refresh the app view, etc.
        }
    }

    private class MyUpdateListener implements ChangeEventListener<Document> {
        @Override
        public void onEvent(final BsonValue documentId, final ChangeEvent<Document> event) {
            if (!event.hasUncommittedWrites()) {
                // Custom actions can go here
            }
            // refresh the app view, etc.
        }
    }
}
