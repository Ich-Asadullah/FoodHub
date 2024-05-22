package Services;


public interface Manageable<T> {
    void add(T item);
    void update(T item);
    void delete(int id);
}
