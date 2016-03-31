package br.edu.ifpb.pweb.calendar.dao;

import java.io.Serializable;

public interface GenericDAO<T, PK extends Serializable> {
    T insert(T t);
    T find(PK id);
    T update(T t);
    void delete(T t);
    void beginTransaction();
    void commit();
    void rollback();
}
