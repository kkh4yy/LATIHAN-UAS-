package burhanfess.repositories;

import java.util.ArrayList;
import java.util.List;

public class GenericRepositoryImpl<T> implements GenericRepository<T> {
    private final List<T> data = new ArrayList<>();

    @Override
    public void addItem(T item) {
        data.add(item);
    }

    @Override
    public List<T> getData() {
        return data;
    }

    @Override
    public void removeItem(T item) {
        data.remove(item);
    }
    
}