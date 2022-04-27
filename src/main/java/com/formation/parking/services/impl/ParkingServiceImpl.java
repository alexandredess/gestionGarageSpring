package com.formation.parking.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.formation.parking.models.Parking;
import com.formation.parking.services.ParkingService;

@Service
public class ParkingServiceImpl implements ParkingService {

	@Override
	public List<Parking> getListeParkings() {
		Parking parkingTest = new Parking();
		parkingTest.setNom("Parking de test");
		parkingTest.setNbPlaceTotal(300);
		parkingTest.setNbPlaceDispo(125);
		parkingTest.setStatut("FERME");
		parkingTest.setHeureMaj("20h15");
		ArrayList<Parking> liste = new ArrayList<Parking>();
		liste.add(parkingTest);
		return liste;
	}

}
