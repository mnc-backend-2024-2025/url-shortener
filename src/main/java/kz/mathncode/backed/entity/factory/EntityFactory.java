package kz.mathncode.backed.entity.factory;

public interface EntityFactory<T> {
    Class<T> getEntityClass();
    T produce();
}
