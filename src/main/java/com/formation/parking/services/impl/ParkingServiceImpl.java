package com.formation.parking.services.impl;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.formation.parking.dao.ParkingAPIDAO;
import com.formation.parking.dao.entity.RecordEntity;
import com.formation.parking.dao.entity.ReponseParkingAPIEntity;
import com.formation.parking.models.Parking;
import com.formation.parking.services.ParkingService;

@Service
public class ParkingServiceImpl implements ParkingService {

	@Autowired
	public ParkingAPIDAO parkingAPIDAO;

	@Override
	public List<Parking> getListeParkings() {
		ReponseParkingAPIEntity reponse = parkingAPIDAO.getListeParkings();
		return transformEntityToModel(reponse);
	}

	private List<Parking> transformEntityToModel(ReponseParkingAPIEntity reponse) {
		List<Parking> resultat = new ArrayList<Parking>();
		for (RecordEntity record : reponse.getRecords()) {
			Parking parking = new Parking();
			parking.setNom(record.getFields().getGrpNom());
			parking.setStatut(getLibelleStatut(record));
			parking.setNbPlaceDispo(record.getFields().getGrpDisponible());
			parking.setNbPlaceTotal(record.getFields().getGrpExploitation());
			parking.setHeureMaj(getHeurMaj(record));
			resultat.add(parking);
		}
		return resultat;
	}

	private String getHeurMaj(RecordEntity record) {
		OffsetDateTime dateMaj = OffsetDateTime.parse(record.getFields().getGrpHorodatage());
		OffsetDateTime dateMajWithOffsetPlus1 = dateMaj.withOffsetSameInstant(ZoneOffset.of("+01:00"));
		return dateMajWithOffsetPlus1.getHour()+ "h"+dateMajWithOffsetPlus1.getMinute() ;
	}

	private String getLibelleStatut(RecordEntity record) {
		switch (record.getFields().getGrpStatut()) {
			case "1": {
				return "FERME";
			}
			case "2": {
				return "ABONNES";
			}
			case "5": {
				return "OUVERT";
			}
		}
		return "Données non disponibles";

	}

}
