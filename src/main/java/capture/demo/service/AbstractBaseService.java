package capture.demo.service;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import javax.persistence.LockModeType;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;

import lombok.extern.slf4j.Slf4j;


@Slf4j
public abstract class AbstractBaseService<T, K extends Serializable, R extends JpaRepository<T, K>> {

	protected R repo;


	public AbstractBaseService(R repo) {
		this.repo = repo;
	}
	public T save(T t) {
		log.info("save - {} - {}", this.getClass().getSimpleName(), t.toString());
		return repo.save(t);
	}

	public void delete(T t) {
		log.info("delete - {} ", this.getClass().getSimpleName());
		repo.delete(t);
	}


	public void deleteById(K k) {
		log.info("delete - {} - id: {}", this.getClass().getSimpleName(), k);
		repo.deleteById(k);
	}


	public T get(K id) {
		return repo.getOne(id);
	}

	public Optional<T> findById(K id) {
		return repo.findById(id);
	}

	@Lock(LockModeType.PESSIMISTIC_READ)
	public Optional<T> findAndLockById(K id) {
		return repo.findById(id);
	}

	public List<T> listAll() {
		return repo.findAll();
	}



}
