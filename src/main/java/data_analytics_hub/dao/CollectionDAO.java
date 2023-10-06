package data_analytics_hub.dao;

import data_analytics_hub.Session;
import data_analytics_hub.functions.TxtFileExecutor;
import data_analytics_hub.modal.Collection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;
import java.util.Optional;

public class CollectionDAO extends SuperDAO<Collection> implements DAO<Collection> {

    public ArrayList<Collection> initData() {
        ArrayList<Collection> collections = new ArrayList<>();
        ArrayList<String[]> fileContent = Session.txtFileExecutor.readFileToArray(TxtFileExecutor.COLLECTIONS);
        for(String[] ele : fileContent){
            Collection collection;
            if (ele.length > 1) {
                ArrayList<String> postIds = new ArrayList<>();
                String[] postIdsArray = ele[1].split(":");
                Collections.addAll(postIds, postIdsArray);
                collection = new Collection(Integer.parseInt(ele[0]), postIds);
            } else {
                collection = new Collection(Integer.parseInt(ele[0]));
            }
            collections.add(collection);
        }
        return collections;
    }

    public void saveAll(){
        ArrayList<String[]> collectionsContent = new ArrayList<>();
        for (Collection collection : Session.collections) {
            collectionsContent.add(collection.toStringArray());
        }
        Session.txtFileExecutor.rewriteFile(TxtFileExecutor.COLLECTIONS, collectionsContent);
    }

    public Collection getCollectionById(int id) {
        for (Collection collection : Session.collections) {
            if (collection.getCollectionId() == id) {
//                System.out.println("Found collection with id: " + collection.getPostIds());
                return collection;
            }
        }
        //create new collection if not found
        Collection collection = new Collection(id);
        save(collection);
        return collection;
    }

    @Override
    public Optional<Collection> get(int id) {
        for (Collection collection : Session.collections) {
            if (collection.getCollectionId() == id) {
                return Optional.of(collection);
            }
        }
        return Optional.empty();
    }

    @Override
    public ArrayList<Collection> getAll() {
        return Session.collections;
    }

    @Override
    public void save(Collection collection) {
        Session.collections.add(collection);
    }

    @Override
    public void update(Collection collection) {
        // do nothing
    }

    @Override
    public void delete(Collection collection) {
        Session.collections.remove(collection);
    }
}
