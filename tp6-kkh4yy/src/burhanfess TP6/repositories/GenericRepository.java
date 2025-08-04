package burhanfess.repositories;

import java.util.List;

public interface GenericRepository<T> {
    public void addItem(T item);
    public List<T> getData();
    public void removeItem(T item);
}
