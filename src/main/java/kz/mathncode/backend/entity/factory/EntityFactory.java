package kz.mathncode.backend.entity.factory;

public interface EntityFactory<T> {
    Class<T> getEntityClass();
    T produce();
}
