package com.pismo.io.evaluation.gateways.providers.repositories;

/**
 * Signature of methods that will be implemented in the providers. All the methods are responsible
 * to convert domain database into entity.
 *
 * @param <E> result of repository will be convert into this entity
 * @param <I> type of collection identifier
 */
public interface DatabaseProvider<E, I> {

    /**
     * Method will convert domain database into entity to realize
     *
     * @param id collection identifier
     * @return entity
     */
    E findById(final I id);

    /**
     * Will save object received into database
     *
     * @param object entity to save
     * @return entity
     */
    E save(final E object);

}
