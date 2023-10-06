package data_analytics_hub.dao;

import java.util.ArrayList;
import java.util.Optional;

public interface DAO<T> {

    Optional<T> get(int id);

    ArrayList<T> getAll();

    void save(T t);

    void update(T t);

    void delete(T t);
}
