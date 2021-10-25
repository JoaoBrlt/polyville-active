package fr.unice.ps7.al1.common.service;

import java.util.Optional;

public interface PrivateService<T> extends SharedServices<T> {
	<S extends T> S save(S toRegister);
	void delete(T toDelete);
	void deleteById(Long id);
	Optional<T> findById(Long id);
	boolean existsById(Long id);
}
