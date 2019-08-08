package com.bhupesh.cricapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bhupesh.cricapi.domain.CricPlayInformation;

@Repository
public interface CricPlayInformationRepository extends JpaRepository<CricPlayInformation, Integer> {

	CricPlayInformation findByCricplayerid(int cricplayerid);
}
