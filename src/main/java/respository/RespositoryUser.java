package respository;

import java.util.List;

public interface RespositoryUser<T,K> {
    boolean add(T item);
    List<T> getAll();
    T getByUserName(K id);
}
