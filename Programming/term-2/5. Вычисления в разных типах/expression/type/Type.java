package expression.type;

/**
 * @author Popov Vladimir (vova_57@bk.ru)
 */
public interface Type<T extends Number> {
    T value();

    Type<T> add(Type<T> v);

    Type<T> subtract(Type<T> v);

    Type<T> multiply(Type<T> v);

    Type<T> divide(Type<T> v);

    Type<T> negate();

    Type<T> abs();

    Type<T> square();

    Type<T> mod(Type<T> v);

    Type<T> count();

    Type<T> minimum(Type<T> v);

    Type<T> maximum(Type<T> v);
}
