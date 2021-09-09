package tn.transport.colis.repository;

import java.util.Date;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import tn.transport.colis.entity.Colis;

@Repository
public interface IColisRepository extends CrudRepository<Colis, Integer>

{

	@Modifying
	@Transactional
	@Query("update Colis c set c.dateDebut = :dateStart ,c.datefin = :dateEnd  where c.id = :colisId")
	public void updateColisJPQL(@Param("dateStart") Date dateStart,@Param("dateEnd") Date dateEnd,@Param("colisId") int meetingId);
}
